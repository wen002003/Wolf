package com.wolf.model.base

import com.jfinal.plugin.activerecord.IBean
import com.jfinal.plugin.activerecord.Model

abstract  open  class BaseSysUser<M:BaseSysUser<M>>: Model<M>(), IBean {
    fun setId(id: String?) {
        set("id", id)
    }

    fun getId(): String? {
        return get("id")
    }

    fun setOrgId(orgId: String?) {
        set("org_id", orgId)
    }

    fun getOrgId(): String? {
        return get("org_id")
    }

    fun setQxOrgid(qxOrgid: String?) {
        set("qx_orgid", qxOrgid)
    }

    fun getQxOrgid(): String? {
        return get("qx_orgid")
    }

    fun setName(name: String?) {
        set("name", name)
    }

    fun getName(): String? {
        return get("name")
    }

    fun setUsername(username: String?) {
        set("username", username)
    }

    fun getUsername(): String? {
        return get("username")
    }

    fun setPassword(password: String?) {
        set("password", password)
    }

    fun getPassword(): String? {
        return get("password")
    }

    fun setValidStart(validStart: java.util.Date) {
        set("valid_start", validStart)
    }

    fun getValidStart(): java.util.Date {
        return get("valid_start")
    }

    fun setValidEnd(validEnd: java.util.Date) {
        set("valid_end", validEnd)
    }

    fun getValidEnd(): java.util.Date {
        return get("valid_end")
    }

    fun setIdentity(identity: String?) {
        set("identity", identity)
    }

    fun getIdentity(): String? {
        return get("identity")
    }

    fun setAddress(address: String?) {
        set("address", address)
    }

    fun getAddress(): String? {
        return get("address")
    }

    fun setPostcode(postcode: String?) {
        set("postcode", postcode)
    }

    fun getPostcode(): String? {
        return get("postcode")
    }

    fun setTelephone(telephone: String?) {
        set("telephone", telephone)
    }

    fun getTelephone(): String? {
        return get("telephone")
    }

    fun setCellphone(cellphone: String?) {
        set("cellphone", cellphone)
    }

    fun getCellphone(): String? {
        return get("cellphone")
    }

    fun setEmail(email: String?) {
        set("email", email)
    }

    fun getEmail(): String? {
        return get("email")
    }

    fun setValidFlag(validFlag: String?) {
        set("valid_flag", validFlag)
    }

    fun getValidFlag(): String? {
        return get("valid_flag")
    }
}