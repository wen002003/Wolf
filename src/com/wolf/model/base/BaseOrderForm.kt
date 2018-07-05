package com.wolf.model.base

import com.jfinal.plugin.activerecord.IBean
import com.jfinal.plugin.activerecord.Model

abstract  open  class BaseOrderForm<M:BaseOrderForm<M>>: Model<M>(), IBean {
    fun setId(id: Int?) {
        set("id", id)
    }

    fun getId(): Int? {
        return get<Int>("id")
    }

    fun setCustomerCellphone(customerCellphone: java.lang.String) {
        set("customerCellphone", customerCellphone)
    }

    fun getCustomerCellphone(): java.lang.String {
        return get("customerCellphone")
    }

    fun setCustmerId(custmerId: Int?) {
        set("custmerId", custmerId)
    }

    fun getCustomerId(): Int? {
        return get<Int>("customerId")
    }

    fun setCustomerName(customerName: java.lang.String) {
        set("customerName", customerName)
    }

    fun getCustomerName(): java.lang.String {
        return get("customerName")
    }

    fun setPrice(price: Double?) {
        set("price", price)
    }

    fun getPrice(): Double? {
        return get<Double>("price")
    }

    fun setSaleTime(saleTime: java.util.Date) {
        set("saleTime", saleTime)
    }

    fun getSaleTime(): java.util.Date {
        return get("saleTime")
    }

    fun setProfit(profit: Double?) {
        set("profit", profit)
    }

    fun getProfit(): Double? {
        return get<Double>("profit")
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