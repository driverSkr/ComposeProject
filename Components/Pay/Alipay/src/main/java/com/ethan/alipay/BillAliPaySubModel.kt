package com.ethan.alipay

data class BillAliPaySubModel(
    var return_json: Boolean? = null,
    var custom_oid: String? = null,
    var body: SubModel? = null,
    var scene: String? = null,
    var token: String? = null,
    var create_time: Long? = null,
    var goods_name: String? = null,
    var amount: String? = null,
    var vendor: String? = null,
    var cart: String? = null
) {
    data class SubModel(var order_str: String? = null,
                        var custom_oid: String? = null,
                        var amount: String? = null,
                        var goods_name: String? = null,
                        var view: String?=  null,
                        var site_id: Int?=  null,
                        var brand_name: String?=  null,
                        var site_url: String?=  null,
                        var api: String?=  null,
                        var token: String? = null)
}