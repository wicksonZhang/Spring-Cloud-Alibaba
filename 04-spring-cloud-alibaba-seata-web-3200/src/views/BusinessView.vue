<template>
    <div class="app-container">
        <div class="row">
            <!-- Section 1: Add Order -->
            <div class="col">
                <el-card class="add-order-container">
                    <p class="info-title">Add Business Service</p>
                    <el-form :model="order" :rules="orderRules" ref="order" label-width="90px">
                        <el-form-item label="用户ID" prop="userId">
                            <el-input v-model="order.userId" style="width: 100%;"></el-input>
                        </el-form-item>
                        <el-form-item label="商品名称" prop="commodityCode">
                            <el-select v-model="order.commodityCode" @change="handleCommodityCodeChange"
                                       style="width: 100%;">
                                <el-option v-for="product in stocks" :key="product.commodityCode" :label="product.name"
                                           :value="product.commodityCode"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="商品单价" prop="price">
                            <el-input v-model="order.price" style="width: 100%;"></el-input>
                        </el-form-item>
                        <el-form-item label="商品数量" prop="count">
                            <el-input-number v-model="order.count" :min="1" :max="999"
                                             style="width: 100%;"></el-input-number>
                        </el-form-item>
                        <el-form-item label="商品总价" prop="totalPrice">
                            <span style="float: left;">{{ calculateTotalPrice() }}</span>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="addOrder" style="width: 45%;">提 交</el-button>
                            <el-button icon="el-icon-refresh" @click="resetForm" style="width: 45%;">重 置</el-button>
                        </el-form-item>
                    </el-form>
                </el-card>
            </div>

            <!-- Section 2: Display Stock Information -->
            <div class="col">
                <el-card class="stock-info-container">
                    <p class="info-title">Stock Service Information</p>
                    <el-table :data="stocks" border style="width: 100%;" class="el-table">
                        <el-table-column align="center" prop="commodityCode" label="商品编号"></el-table-column>
                        <el-table-column align="center" prop="name" label="商品名称"></el-table-column>
                        <el-table-column align="center" prop="count" label="商品库存"></el-table-column>
                    </el-table>
                </el-card>
            </div>
        </div>

        <div class="row">
            <!-- Section 3: Reduce Account Balance -->
            <div class="col">
                <el-card class="account-info-container">
                    <p class="info-title">Account Service Information</p>
                    <el-table :data="account" border style="width: 100%;" class="el-table">
                        <el-table-column align="center" prop="userId" label="用户ID"></el-table-column>
                        <el-table-column align="center" prop="amount" label="用户余额"></el-table-column>
                    </el-table>
                </el-card>
            </div>

            <!-- Section 4: Update Order Information -->
            <div class="col">
                <el-card class="order-info-container">
                    <p class="info-title">Order Service Information</p>
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
                    totalPrice: 0.0,
                },
                stocks: [],
                orders: [],
                account: [],
                orderRules: {
                    userId: [{required: true, message: '请输入用户ID', trigger: 'blur'}],
                    commodityCode: [{required: true, message: '请输入商品订单', trigger: 'blur'}],
                    name: [{required: true, message: '请输入商品名称', trigger: 'blur'}],
                    count: [
                        {required: true, message: '请输入商品数量', trigger: 'blur'},
                        {type: 'integer', message: '商品数量必须为整数', trigger: 'blur'},
                    ],
                    price: [{required: true, validator: this.validatePrice, trigger: 'blur'}],
                },
            };
        },
        methods: {
            validatePrice(rule, value, callback) {
                const decimalRegex = /^\d+(\.\d{1,2})?$/;
                if (!decimalRegex.test(value)) {
                    callback(new Error('请输入正确商品单价，且最多保留两位小数'));
                } else {
                    callback();
                }
            },
            handleCommodityCodeChange() {
                const selectedProduct = this.stocks.find(stock => stock.commodityCode === this.order.commodityCode);
                if (selectedProduct) {
                    this.order.name = selectedProduct.name;
                }
            },
            calculateTotalPrice() {
                const price = parseFloat(this.order.price) || 0;
                const count = parseInt(this.order.count) || 0;
                this.order.totalPrice = (price * count).toFixed(2);
                return this.order.totalPrice;
            },
            resetForm() {
                this.order = {
                    userId: '',
                    commodityCode: '',
                    name: '',
                    price: 0,
                    count: 1,
                    totalPrice: 0.0,
                };
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
            },
            // 获取库存信息
            listByStock() {
                this.axios.get('http://192.168.10.221:9527/stock/list')
                    .then(response => {
                        this.stocks = response.data.data;
                    })
                    .catch(error => {
                        this.$message.error(error);
                    });
            },
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
        padding: 20px;
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
        margin-bottom: 20px; /* Add margin to the bottom of each card */
    }

    @media (max-width: 767px) {
        .col {
            margin: 0; /* Remove horizontal margin on small screens */
        }
    }
</style>