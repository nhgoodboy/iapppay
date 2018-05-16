<template>
    <div>
        <text class="title">爱贝SDK接入测试</text>
        <div class="button" @click="order('h5')">
            <text>H5支付</text>
        </div>

        <div class="button" @click="order('pc')">
            <text>PC支付</text>
        </div>

        <div class="button" @click="test">
            <text>Android聚合支付</text>
        </div>
    </div>
</template>

<script>
    // if (process.env.NODE_ENV === 'development') require('Config');

    let _this;

    export default {
        data() {
            return {
                realHeight: 0,
            }
        },

        components: {},

        mounted() {
            _this = this;
        },

        methods: {
            test() {

            },

            H5_order() {
                this.$fetch({
                    method: 'GET',    // 大写
                    name: 'h5_order',
                    data: {
                        waresId: 1
                    }
                }).then(resData => {
                    this.$notice.alert({
                        title: '支付URL',
                        message: resData,
                        okTitle: '去支付',
                        callback() {
                            _this.$router.openBrowser(resData);
                        }
                    })
                }, error => {
                    this.$notice.alert({
                        title: '这是一个弹窗',
                        message: error,
                        okTitle: 'ok',
                        callback() {

                        }
                    })
                })
            },

            order(type) {
                let name = null;
                if(type === 'h5'){
                    name = 'h5_order'
                }else if (type === 'pc'){
                    name = 'pc_order'
                }
                this.$fetch({
                    method: 'GET',    // 大写
                    name: name,
                    data: {
                        waresId: 1
                    }
                }).then(resData => {
                    this.$notice.alert({
                        title: '支付URL',
                        message: resData,
                        okTitle: '去支付',
                        callback() {
                            _this.$router.openBrowser(resData);
                        }
                    })
                }, error => {
                    this.$notice.alert({
                        title: '有错误',
                        message: error,
                        okTitle: 'ok',
                        callback() {

                        }
                    })
                })
            }

        }
    }


</script>

<style lang="sass" src="./home.scss" scoped></style>
