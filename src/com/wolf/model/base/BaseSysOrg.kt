package com.wolf.model.base

import com.jfinal.plugin.activerecord.IBean
import com.jfinal.plugin.activerecord.Model

abstract  open  class BaseSysOrg<M:BaseSysOrg<M>>: Model<M>(), IBean {
    fun setId(id: String?) {
        set("id", id)
    }

    fun getId():String? {
        return get("id")
    }

    fun setName(name:String) {
        set("name", name)
    }

    fun getName():String? {
        return get("name")
    }

    fun setSimpleName(simpleName:String) {
        set("simple_name", simpleName)
    }

    fun getSimpleName():String? {
        return get("simple_name")
    }

    fun setParentId(parentId:String?) {
        set("parent_id", parentId)
    }

    fun getParentId():String? {
        return get("parent_id")
    }


    fun setOrgLevel(orgLevel:String) {
        set("org_level", orgLevel)
    }

    fun getOrgLevel():String? {
        return get("org_level")
    }

    fun setDigit(digit: Int?) {
        set("digit", digit)
    }

    fun getDigit(): Int? {
        return get<Int>("digit")
    }

    fun setAddress(address:String) {
        set("address", address)
    }

    fun getAddress():String? {
        return get("address")
    }

    fun setPostcode(postcode:String) {
        set("postcode", postcode)
    }

    fun getPostcode():String? {
        return get("postcode")
    }

    fun setTelephone(telephone:String) {
        set("telephone", telephone)
    }

    fun getTelephone():String? {
        return get("telephone")
    }

    fun setFax(fax:String) {
        set("fax", fax)
    }

    fun getFax():String? {
        return get("fax")
    }

    fun setEmail(email:String) {
        set("email", email)
    }

    fun getEmail():String? {
        return get("email")
    }

    fun setValidFlag(validFlag:String) {
        set("valid_flag", validFlag)
    }

    fun getValidFlag():String? {
        return get("valid_flag")
    }

    fun setOrgType(orgType:String) {
        set("org_type", orgType)
    }

    fun getOrgType():String? {
        return get("org_type")
    }
}