package com.wolf.service.system

import com.jfinal.kit.Kv
import com.jfinal.plugin.activerecord.Db
import com.jfinal.plugin.activerecord.Record
import com.wolf.model.system.SysPriv
import com.wolf.model.system.SysUser
import com.wolf.utils.Constant

open class SysPrivService {

    fun getSysPrivListByUser(user: SysUser): List<SysPriv> {
        val sqlId = "system.sys_priv_SysPrivListByUser"
        val sql1 = Db.getSqlPara(sqlId,  Kv.by("user_id", user.getId()).set("node_type", Constant.PRIV_NODETYPE_ML))
        val sql2 = Db.getSqlPara(sqlId, Kv.by("user_id", user.getId()).set("node_type", Constant.PRIV_NODETYPE_QX))
        // 目录列表
        val mlList = SysPriv.dao.find(sql1)
        // 权限列表
        val qxList = SysPriv.dao.find(sql2)
        for (mlPriv in mlList) {
            val qx = qxList.filter { mlPriv.getId() === it.getParentId() }
            mlPriv.setChildList(qx.toMutableList())
        }
        return mlList
    }



    fun getAllSysPrivList(): List<*> {

        val sqlId = "system.sys_priv_findAllSysPriv"

        val list = Db.find(Db.getSqlPara(sqlId))
        val mlList:MutableList<HashMap<String, Any>> = mutableListOf<HashMap<String, Any>>()
        val qxList:MutableList<HashMap<String, Any>> = mutableListOf<HashMap<String, Any>>()
        // 循环，形成树
        for (r in list) {
            val map = hashMapOf<String,Any>()
            if (r.getStr("node_type") == Constant.PRIV_NODETYPE_ML) {
                map.put("isParent", true)
                map.put("open", true)
                map.put("chkDisabled", false)
                map.put("id", r.getStr("id"))
                map.put("name", r.getStr("name"))
                map.put("node_type", r.getStr("node_type"))
                map.put("parent_id", r.getStr("parent_id"))
                map.put("children", mutableListOf<Map<*,*>>())
                mlList.add(map)
            } else {
                map.put("isParent", false)
                map.put("open", false)
                map.put("chkDisabled", false)
                map.put("id", r.getStr("id"))
                map.put("name", r.getStr("name"))
                map.put("node_type", r.getStr("node_type"))
                map.put("parent_id", r.getStr("parent_id"))
                qxList.add(map)
            }
        }
        for (mlPriv in mlList) {
            val qx: List<Map<*, *>> = qxList.filter { mlPriv["id"] == it["parent_id"] }
            mlPriv["children"]=qx.toMutableList()
        }
        return mlList
    }



    fun getSysPrivListAsync(id: String?): List<Record> {
        var resultList = mutableListOf<Record>()
        val sqlId="system.sys_priv_findSysPrivByParentId"
        if (null == id) {
            val root = Db.findById("sys_priv", 1)
            root.set("isParent", true)
            root.set("open", true)

            val childList = Db.find(Db.getSqlPara(sqlId, Kv.by("id", 1)))
            for (p in childList) {
//                p.set("iconSkin","iconfont "+p.getStr("icon"))
                if (p.get<Any>("node_type") == Constant.PRIV_NODETYPE_ML) {
                    p.set("isParent", true)
                } else {
                    p.set("isParent", false)
                }
                resultList.add(p)
            }
            resultList.add(root)
        } else {
            val childList = Db.find(Db.getSqlPara(sqlId, Kv.by("id", id)))
            for (p in childList) {
                if (p.get<Any>("node_type") == Constant.PRIV_NODETYPE_ML) {
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
     * 检测是否存在子节点 true:存在 false:不存在
     */
    fun checkChildPriv(id: String): Boolean {
        val childList = Db.find(Db.getSqlPara("system.sys_prv_findSysPrivIdByParentId", Kv.by("id", id)))
        return (null != childList && childList.size > 0)

    }

}