package com.wolf.model.base

import com.jfinal.plugin.activerecord.IBean
import com.jfinal.plugin.activerecord.Model

abstract  open  class BaseBakeCustomer<M:BaseBakeCustomer<M>>:Model<M>(),IBean{
    fun setId(id: Int?) {
        set("id", id)
    }

    fun getId(): Int? {
        return get<Int>("id")
    }

    fun setName(name: java.lang.String) {
        set("name", name)
    }

    fun getName(): java.lang.String {
        return get("name")
    }

    fun setCellphone(cellphone: java.lang.String) {
        set("cellphone", cellphone)
    }

    fun getCellphone(): java.lang.String {
        return get("cellphone")
    }

    fun setAddTime(addTime: java.util.Date) {
        set("addTime", addTime)
    }

    fun getAddTime(): java.util.Date {
        return get("addTime")
    }

    fun setUpdateTime(updateTime: java.util.Date) {
        set("updateTime", updateTime)
    }

    fun getUpdateTime(): java.util.Date {
        return get("updateTime")
    }

    fun setValidFlag(validFlag: java.lang.String) {
        set("validFlag", validFlag)
    }

    fun getValidFlag(): java.lang.String {
        return get("validFlag")
    }

    fun setUserId(userId: java.lang.String) {
        set("userId", userId)
    }

    fun getUserId(): java.lang.String {
        return get("userId")
    }
}