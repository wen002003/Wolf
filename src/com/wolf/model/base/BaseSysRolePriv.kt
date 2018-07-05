package com.wolf.model.base

import com.jfinal.plugin.activerecord.IBean
import com.jfinal.plugin.activerecord.Model

abstract  open  class BaseSysRolePriv<M:BaseSysRolePriv<M>>: Model<M>(), IBean {
    fun setRoleId(roleId: java.lang.String) {
        set("role_id", roleId)
    }

    fun getRoleId(): java.lang.String {
        return get("role_id")
    }

    fun setPrivId(privId: java.lang.String) {
        set("priv_id", privId)
    }

    fun getPrivId(): java.lang.String {
        return get("priv_id")
    }
}