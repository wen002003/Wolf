package com.wolf.config

import com.jfinal.config.Routes
import com.wolf.controller.common.CommonController
import com.wolf.controller.system.*

class SystemRoutes:Routes(){
    override fun config() {
//        setBaseViewPath("/system")
        add("/", SystemController::class.java, "/index")
        add("/system", SystemController::class.java)
        add("/sysOrg", SysOrgController::class.java)
        add("/sysPriv", SysPrivController::class.java)
        add("/sysRole", SysRoleController::class.java)
        add("/sysUser", SysUserController::class.java)
        add("/common", CommonController::class.java)
    }

}