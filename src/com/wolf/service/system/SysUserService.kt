package com.wolf.service.system

import com.jfinal.kit.Kv
import com.jfinal.plugin.activerecord.Db
import com.wolf.model.system.SysUser
import com.wolf.utils.Encrypt

open class  SysUserService {

    fun login(sysUser: SysUser): SysUser? {
        val sqlPara = Db.getSqlPara("system.getUserByLogin", Kv.by("username", sysUser.getUsername()).set("password", Encrypt.md5(sysUser.getPassword())))
        val list = SysUser.dao.find(sqlPara)
        return if (list.count()>0) list.first() else null
    }
}