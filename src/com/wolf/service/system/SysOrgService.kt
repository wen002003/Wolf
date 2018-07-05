package com.wolf.service.system

import com.jfinal.kit.Kv
import com.jfinal.plugin.activerecord.Db
import com.jfinal.plugin.activerecord.Record
import com.wolf.utils.Constant

open class SysOrgService {

    private val ROOT_ID = 0

    fun getSysOrgListAsync(id: String?): List<Record> {
        var resultList = mutableListOf<Record>()

        if (null == id) {
            val root = Db.findById("sys_org", ROOT_ID)
            root.set("isParent", true)
            root.set("open", true)
            val sqlPara = Db.getSqlPara("system.sys_org_findSysOrgByParentId", Kv.by("id", ROOT_ID))
            val childList = Db.find(sqlPara)
            for (p in childList) {
                if (p.get<Any>("org_type") == Constant.PRIV_NODETYPE_ML) {
                    p.set("isParent", true)
                } else {
                    p.set("isParent", false)
                }
                resultList.add(p)
            }
            resultList.add(root)
        } else {
            val sqlPara = Db.getSqlPara("system.sys_org_findSysOrgByParentId", Kv.by("id", id))
            val childList = Db.find(sqlPara)
            for (p in childList) {
                if (p.get<Any>("org_type") == Constant.PRIV_NODETYPE_ML) {
                    p.set("isParent", true)
                } else {
                    p.set("isParent", false)
                }
            }
            resultList = childList
        }
        return resultList
    }

    /**
     * 检测是否存在子节点
     * true:存在 false:不存在
     */
    fun checkChildOrg(id: String): Boolean {
        val childList = Db.find(Db.getSqlPara("system.sys_org_findSysOrgIdByParentId", Kv.by("id", id)))
        return (null != childList && childList.size > 0)
    }
}