<template>
    <div>
        <web style="width: 750px"
             :style="{height:realHeight}"
             src="https://web.iapppay.com/h5/gateway?data=%7B%22app%22%3A%223019332996%22%2C%22url_r%22%3A%22http%3A%2F%2F58.250.160.241%3A8888%2FIapppayCpSyncForPHPDemo%2FTest.php%22%2C%22tid%22%3A%2232961805161021017003%22%2C%22url_h%22%3A%22http%3A%2F%2F58.250.160.241%3A8888%2FIapppayCpSyncForPHPDemo%2FTest.php%22%7D&sign=BD6k%2BbuKk0bwtlhO1OmJWk6%2FX0MXww4VewT3oBBKiXijNYJXBUfmMYWC9RE4PdGH31YPIFAdErgYzXPf60Ma89%2BvbDlPImtnMsrv9Ewmm3jU0jiEYFdKW3GzHDRZarX3yyhAujI96JHA2ncP9b4LZAGP1Yd%2F4PuEhF4xbeiZqYM%3D&signtype=RSA">
        </web>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                realHeight: 0,
            }
        },

        components: {},

        mounted() {
            this.realHeight = weex.config.eros.realDeviceHeight;
        },

        methods: {}
    }

</script>

<style lang="sass" src="./pay.scss" scoped></style>
