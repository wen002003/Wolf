package com.wolf.model.base

import com.jfinal.plugin.activerecord.IBean
import com.jfinal.plugin.activerecord.Model

open abstract class BaseArticleComment<M : BaseArticleComment<M>> : Model<M>(), IBean {

    var id: Int?
        get() = get<Int>("id")
        set(id) {
            set("id", id)
        }

    var message: java.lang.String
        get() = get("message")
        set(message) {
            set("message", message)
        }

    var replyId: Int?
        get() = get<Int>("replyId")
        set(replyId) {
            set("replyId", replyId)
        }

    var ipAddress: java.lang.String
        get() = get("ipAddress")
        set(ipAddress) {
            set("ipAddress", ipAddress)
        }

    var addTime: java.util.Date
        get() = get("addTime")
        set(addTime) {
            set("addTime", addTime)
        }

    var updateTime: java.util.Date
        get() = get("updateTime")
        set(updateTime) {
            set("updateTime", updateTime)
        }

    var validFlag: java.lang.String
        get() = get("validFlag")
        set(validFlag) {
            set("validFlag", validFlag)
        }

    var userId: java.lang.String
        get() = get("userId")
        set(userId) {
            set("userId", userId)
        }

}
