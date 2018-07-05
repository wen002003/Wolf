package com.wolf.model.system

import com.wolf.model.base.BaseSysPriv

class SysPriv : BaseSysPriv<SysPriv>() {
    companion object {
        val dao = SysPriv()
    }


    private var childList: MutableList<SysPriv> = mutableListOf()

    // 默认不是父节点
    private var isParent = false

    fun getChildList(): List<SysPriv> {
        return childList
    }

    fun setChildList(childList: MutableList<SysPriv>) {
        this.childList = childList
    }

    fun getIsParent(): Boolean {
        return isParent
    }

    fun setIsParent(isParent: Boolean) {
        this.isParent = isParent
    }
}