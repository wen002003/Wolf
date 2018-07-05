package com.wolf.service.system

import com.jfinal.plugin.activerecord.Db
import com.jfinal.plugin.activerecord.Record

open class SysRoleService {
    /**
     * 查找所有角色
     */
    fun findRoleList(): List<Record> {
        return Db.find(Db.getSql("system.sys_role_findAllRole"))
    }
}