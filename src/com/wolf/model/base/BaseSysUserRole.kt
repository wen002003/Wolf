package com.wolf.model.base

import com.jfinal.plugin.activerecord.IBean
import com.jfinal.plugin.activerecord.Model

abstract  open  class BaseSysUserRole<M:BaseSysUserRole<M>>: Model<M>(), IBean {
    fun setUserId(userId: java.lang.String) {
        set("user_id", userId)
    }

    fun getUserId(): java.lang.String {
        return get("user_id")
    }

    fun setRoleId(roleId: java.lang.String) {
        set("role_id", roleId)
    }

    fun getRoleId(): java.lang.String {
        return get("role_id")
    }
}