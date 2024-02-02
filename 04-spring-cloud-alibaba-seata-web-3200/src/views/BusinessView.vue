<template>
    <div class="app-container">
        <div class="row">
            <!-- Section 1: Add Order -->
            <div class="col">
                <el-card class="add-order-container">
                    <p class="info-title">Add Business</p>
                    <el-form :model="order" :rules="orderRules" ref="order" label-width="90px">
                        <el-form-item label=用户ID prop="userId">
                            <el-input v-model="order.userId"></el-input>
                        </el-form-item>
                        <el-form-item label="商品编号" prop="commodityCode">
                            <el-input v-model="order.commodityCode"></el-input>
                        </el-form-item>
                        <el-form-item label="商品名称" prop="name">
                            <el-input v-model="order.name"></el-input>
                        </el-form-item>
                        <el-form-item label="商品单价" prop="price">
                            <el-input v-model="order.price"></el-input>
                        </el-form-item>
                        <el-form-item label="商品数量" prop="count">
                            <el-input-number v-model="order.count" :min="1" :max="999"
                                             style="float: left"></el-input-number>
                        </el-form-item>
                        <el-form-item label="商品总价" prop="totalPrice">
                            <span style="float: left">{{ calculateTotalPrice() }}</span>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="addOrder" style="float:left;width: 150px">提 交</el-button>
                            <el-button icon="el-icon-refresh" @click="resetForm" style="width: 150px">重 置</el-button>
                        </el-form-item>
                    </el-form>
                </el-card>
            </div>

            <!-- Section 2: Reduce Account Balance -->
            <div class="col">
                <el-card class="account-info-container">
                    <p class="info-title">Account Information</p>
                    <el-table :data="account" border style="width: 100%;" class="el-table">
                        <el-table-column align="center" prop="userId" label="用户ID"></el-table-column>
                        <el-table-column align="center" prop="amount" label="用户余额"></el-table-column>
                    </el-table>
                </el-card>
            </div>
        </div>

        <div class="row">
            <!-- Section 3: Display Stock Information -->
            <div class="col">
                <el-card class="stock-info-container">
                    <p class="info-title">Stock Information</p>
                    <el-table :data="stocks" border style="width: 100%;" class="el-table">
                        <el-table-column align="center" prop="commodityCode" label="商品编号"></el-table-column>
                        <el-table-column align="center" prop="name" label="商品名称"></el-table-column>
                        <el-table-column align="center" prop="count" label="商品库存"></el-table-column>
                    </el-table>
                </el-card>
            </div>

            <!-- Section 4: Update Order Information -->
            <div class="col">
                <el-card class="order-info-container">
                    <p class="info-title">Order Information</p>
                    <el-table :data="orders" border style="width: 100%;" class="el-table">
                        <el-table-column align="center" prop="commodityCode" label="商品编号"></el-table-column>
                        <el-table-column align="center" prop="userId" label="用户Id"></el-table-column>
                        <el-table-column align="center" prop="orderCount" label="订单数量"></el-table-column>
                        <el-table-column align="center" prop="price" label="商品单价"></el-table-column>
                        <el-table-column align="center" prop="orderAmount" label="订单总价"></el-table-column>
                    </el-table>
                </el-card>
            </div>
        </div>
    </div>
</template>

<script>

    export default {
        data() {
            return {
                order: {
                    userId: 2,
                    commodityCode: 'COM002',
                    name: '喜之郎果冻',
                    price: 10,
                    count: 10,
                    totalPrice: 0.0
                },
                stocks: [],
                orders: [],
                account: [],
                orderRules: {
                    userId: [
                        {required: true, message: '请输入用户ID', trigger: 'blur'},
                    ],
                    commodityCode: [
                        {required: true, message: '请输入商品订单', trigger: 'blur'},
                    ],
                    name: [
                        {required: true, message: '请输入商品名称', trigger: 'blur'},
                    ],
                    count: [
                        {required: true, message: '请输入商品数量', trigger: 'blur'},
                        {type: 'integer', message: '商品数量必须为整数', trigger: 'blur'},
                    ],
                    price: [
                        {required: true, validator: this.validatePrice, trigger: 'blur'},
                    ]
                },
            };
        },
        methods: {
            // 检验商品单价
            validatePrice(rule, value, callback) {
                const decimalRegex = /^\d+(\.\d{1,2})?$/;
                if (!decimalRegex.test(value)) {
                    callback(new Error('请输入正确商品单价，且最多保留两位小数'));
                } else {
                    callback();
                }
            },
            // 计算总数
            calculateTotalPrice() {
                const price = parseFloat(this.order.price) || 0;
                const count = parseInt(this.order.count) || 0;
                this.order.totalPrice = (price * count).toFixed(2);
                return this.order.totalPrice;
            },
            // 重置表单
            resetForm() {
                this.order = {};
                this.order.count = 0;
                this.order.totalPrice = 0.00;
            },
            // 采购
            addOrder() {
                this.$refs.order.validate(valid => {
                    if (valid) {
                        this.axios.post('http://192.168.10.221:9527/business/purchase', this.order)
                            .then(response => {
                                if (response.data.code === 1) {
                                    this.$message.success(response.data.message);
                                } else {
                                    this.$message.error(response.data.message);
                                }
                                this.listByAccount();
                                this.listByStock();
                                this.listByOrder();
                            })
                            .catch(error => {
                                this.$message.error(error);
                            });
                    }
                })
            },
            // 获取账户信息
            listByAccount() {
                this.axios.get('http://192.168.10.221:9527/account/list')
                    .then(response => {
                        this.account = response.data.data;
                    })
                    .catch(error => {
                        this.$message.error(error);
                    });
            }
            ,
            // 获取库存信息
            listByStock() {
                this.axios.get('http://192.168.10.221:9527/stock/list')
                    .then(response => {
                        this.stocks = response.data.data;
                    })
                    .catch(error => {
                        this.$message.error(error);
                    });
            }
            ,
            // 获取订单信息
            listByOrder() {
                this.axios.get('http://192.168.10.221:9527/order/list')
                    .then(response => {
                        this.orders = response.data.data;
                    })
                    .catch(error => {
                        this.$message.error(error);
                    });
            }
        },
        mounted() {
            this.listByAccount();
            this.listByStock();
            this.listByOrder();
        }
    };
</script>

<style scoped>
    .app-container {
        max-width: 1000px;
        margin: 0 auto;
    }

    .row {
        display: flex;
        flex-wrap: wrap;
        margin-bottom: 20px;
    }

    .col {
        flex: 1;
        margin: 0 10px;
    }

    .info-title {
        font-size: 18px;
        font-weight: bold;
        margin-bottom: 15px;
    }

    .el-table {
        margin-top: 20px;
    }

    .account-info-container,
    .add-order-container,
    .stock-info-container,
    .order-info-container {
        height: 100%;
        display: flex;
        flex-direction: column;
    }

</style>