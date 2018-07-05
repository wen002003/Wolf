package com.wolf.model.base

import com.jfinal.plugin.activerecord.IBean
import com.jfinal.plugin.activerecord.Model

abstract  open  class BaseSysPriv<M:BaseSysPriv<M>>: Model<M>(), IBean {
    fun setId(id: Int?) {
        set("id", id)
    }

    fun getId(): Int? {
        return get<Int>("id")
    }

    fun setCode(code: String?) {
        set("code", code)
    }

    fun getCode():String?{
        return get("code")
    }

    fun setName(name: String?) {
        set("name", name)
    }

    fun getName():String?{
        return get("name")
    }

    fun setParentId(parentId: Int?) {
        set("parent_id", parentId)
    }

    fun getParentId(): Int? {
        return get<Int>("parent_id")
    }

    fun setNodeType(nodeType: String?) {
        set("node_type", nodeType)
    }

    fun getNodeType():String?{
        return get("node_type")
    }

    fun setLevelCode(levelCode: String?) {
        set("level_code", levelCode)
    }

    fun getLevelCode():String?{
        return get("level_code")
    }

    fun setUrl(url: String?) {
        set("url", url)
    }

    fun getUrl():String?{
        return get("url")
    }

    fun setValidFlag(validFlag: String?) {
        set("valid_flag", validFlag)
    }

    fun getValidFlag():String?{
        return get("valid_flag")
    }

    fun getOrderCode():String?{
        return get("order_code")
    }

    fun setOrderCode(orderCode:String?){
        set("order_code",orderCode)
    }

    fun getIcon():String?{
        return get("icon")
    }

    fun setIcon(icon:String?){
        set("icon",icon)
    }
}