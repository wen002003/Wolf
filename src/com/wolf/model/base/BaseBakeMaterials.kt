package com.wolf.model.base

import com.jfinal.plugin.activerecord.IBean
import com.jfinal.plugin.activerecord.Model

abstract  open  class BaseBakeMaterials<M:BaseBakeMaterials<M>>: Model<M>(), IBean {
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

    fun setBuyPrice(buyPrice: Double?) {
        set("buyPrice", buyPrice)
    }

    fun getBuyPrice(): Double? {
        return get<Double>("buyPrice")
    }

    fun setUnits(units: java.lang.String) {
        set("units", units)
    }

    fun getUnits(): java.lang.String {
        return get("units")
    }

    fun setImage(image: java.lang.String) {
        set("image", image)
    }

    fun getImage(): java.lang.String {
        return get("image")
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