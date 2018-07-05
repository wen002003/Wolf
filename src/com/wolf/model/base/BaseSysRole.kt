package com.wolf.model.base

import com.jfinal.plugin.activerecord.IBean
import com.jfinal.plugin.activerecord.Model

abstract  open  class BaseSysRole<M:BaseSysRole<M>>: Model<M>(), IBean {
    fun setId(id: Int?) {
        set("id", id)
    }

    fun getId(): Int? {
        return get<Int>("id")
    }

    fun setName(name: String) {
        set("name", name)
    }

    fun getName(): String? {
        return get("name")
    }

    fun setValidFlag(validFlag: String) {
        set("valid_flag", validFlag)
    }

    fun getValidFlag(): String? {
        return get("valid_flag")
    }
}