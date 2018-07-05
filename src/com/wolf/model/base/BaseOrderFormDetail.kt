package com.wolf.model.base

import com.jfinal.plugin.activerecord.IBean
import com.jfinal.plugin.activerecord.Model

abstract  open  class BaseOrderFormDetail<M:BaseOrderFormDetail<M>>: Model<M>(), IBean {
    fun setId(id: Int?) {
        set("id", id)
    }

    fun getId(): Int? {
        return get<Int>("id")
    }

    fun setOrderId(orderId: Int?) {
        set("orderId", orderId)
    }

    fun getOrderId(): Int? {
        return get<Int>("orderId")
    }

    fun setRecipeId(recipeId: Int?) {
        set("recipeId", recipeId)
    }

    fun getRecipeId(): Int? {
        return get<Int>("recipeId")
    }

    fun setCount(count: Int?) {
        set("count", count)
    }

    fun getCount(): Int? {
        return get<Int>("count")
    }

    fun setPrice(price: Double?) {
        set("price", price)
    }

    fun getPrice(): Double? {
        return get<Double>("price")
    }

    fun setTotalPrice(totalPrice: Double?) {
        set("totalPrice", totalPrice)
    }

    fun getTotalPrice(): Double? {
        return get<Double>("totalPrice")
    }

    fun setProfit(profit: Double?) {
        set("profit", profit)
    }

    fun getProfit(): Double? {
        return get<Double>("profit")
    }
}