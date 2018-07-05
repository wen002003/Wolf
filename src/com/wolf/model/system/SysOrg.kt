package com.wolf.model.system

import com.wolf.model.base.BaseSysOrg

class SysOrg: BaseSysOrg<SysOrg>() {
    companion object {

    val dao:SysOrg = SysOrg()
    }

    private var childList: List<*> = mutableListOf(0)

    fun getChildList(): List<*> {
        return childList
    }

    fun setChildList(childList: List<*>) {
        this.childList = childList
    }
}