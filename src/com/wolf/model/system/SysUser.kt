package com.wolf.model.system

import com.wolf.model.base.BaseSysUser

class SysUser: BaseSysUser<SysUser>() {
    companion object {

    val dao = SysUser()
    }
}