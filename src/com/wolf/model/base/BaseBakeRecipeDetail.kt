package com.wolf.model.base

import com.jfinal.plugin.activerecord.IBean
import com.jfinal.plugin.activerecord.Model

abstract  open  class BaseBakeRecipeDetail<M:BaseBakeRecipeDetail<M>>: Model<M>(), IBean {
    fun setId(id: Int?) {
        set("id", id)
    }

    fun getId(): Int? {
        return get<Int>("id")
    }

    fun setRecipeId(recipeId: Int?) {
        set("recipeId", recipeId)
    }

    fun getRecipeId(): Int? {
        return get<Int>("recipeId")
    }

    fun setMaterialId(materialId: Int?) {
        set("materialId", materialId)
    }

    fun getMaterialId(): Int? {
        return get<Int>("materialId")
    }

    fun setPrice(price: Double?) {
        set("price", price)
    }

    fun getPrice(): Double? {
        return get<Double>("price")
    }

    fun setQuantity(quantity: Double?) {
        set("quantity", quantity)
    }

    fun getQuantity(): Double? {
        return get<Double>("quantity")
    }

    fun setTotalPrice(totalPrice: Double?) {
        set("totalPrice", totalPrice)
    }

    fun getTotalPrice(): Double? {
        return get<Double>("totalPrice")
    }

}