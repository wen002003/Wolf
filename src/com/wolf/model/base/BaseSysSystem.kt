package com.wolf.model.base

import com.jfinal.plugin.activerecord.IBean
import com.jfinal.plugin.activerecord.Model

abstract  open  class BaseSysSystem<M:BaseSysSystem<M>>: Model<M>(), IBean {
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

    fun setVersion(version: java.lang.String) {
        set("version", version)
    }

    fun getVersion(): java.lang.String {
        return get("version")
    }

    fun setValidFlag(validFlag: java.lang.String) {
        set("valid_flag", validFlag)
    }

    fun getValidFlag(): java.lang.String {
        return get("valid_flag")
    }
}