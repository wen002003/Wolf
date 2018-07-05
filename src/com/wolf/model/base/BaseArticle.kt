package com.wolf.model.base

import com.jfinal.plugin.activerecord.IBean
import com.jfinal.plugin.activerecord.Model

abstract open class BaseArticle<M: BaseArticle<M> > : Model<M>(), IBean {
    
    fun setId(id: Int?) {
        set("id", id)
    }

    fun getId(): Int? {
        return get<Int>("id")
    }

    fun setType(type: String) {
        set("type", type)
    }

    fun getType():String {
        return get("type")
    }

    fun setTitle(title:String) {
        set("title", title)
    }

    fun getTitle():String {
        return get("title")
    }

    fun setContent(content:String) {
        set("content", content)
    }

    fun getContent():String {
        return get("content")
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

    fun setValidFlag(validFlag:String) {
        set("validFlag", validFlag)
    }

    fun getValidFlag():String {
        return get("validFlag")
    }

    fun setUserId(userId:String) {
        set("userId", userId)
    }

    fun getUserId():String {
        return get("userId")
    }

    fun setImage(image:String) {
        set("image", image)
    }

    fun getImage():String {
        return get("image")
    }
}

