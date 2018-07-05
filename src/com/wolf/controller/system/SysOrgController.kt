package com.wolf.controller.system

import com.jfinal.plugin.activerecord.Db
import com.jfinal.plugin.activerecord.Record
import com.wolf.controller.base.BaseController
import com.wolf.model.system.SysOrg
import com.wolf.service.common.CommonService
import com.wolf.service.system.SysOrgService
import com.wolf.utils.JsonResult
import com.wolf.utils.StringUtils
import java.util.*

class SysOrgController:BaseController(){
    @Throws(Exception::class)
    fun index() {
        render("/pages/system/sysOrg/sys_orgIndex.html")
    }

    /**
     * 异步获取权限树
     */
    @Throws(Exception::class)
    fun getOrgTreeAsync() {
        val id = getPara("id")
        val sysPrivService = enhance(SysOrgService::class.java)
        val list = sysPrivService.getSysOrgListAsync(id)
        renderJson(list)
    }

    /**
     * 进入编辑页面
     */
    @Throws(Exception::class)
    fun edit() {
        val commonService = enhance(CommonService::class.java)
        setAttr("nodeTypeList", commonService.sysOrgCodeNodeType)
        val id = getPara("id")
        val parent_id:String? = getPara("parent_id")
        if (StringUtils.isBlank(id)) {
            val p = SysOrg()
            p.setParentId(parent_id)
            setAttr("sysOrg", p)
        } else {
            val p: SysOrg = SysOrg.dao.findById( id );
            setAttr("sysOrg", p)
        }
        render("/pages/system/sysOrg/sys_orgEdit.html")
    }

    /**
     * 保存
     */
    @Throws(Exception::class)
    fun save() {
        val jr = JsonResult()
        val p = getModel(SysOrg::class.java)

        if (null == p.getId()) {
            p.setId(UUID.randomUUID().toString())
            p.save()
        } else {
            p.update()
        }
        jr.isSuccess = true
        jr.message = "保存成功"
        renderJson(jr)
    }

    /**
     * 删除节点
     */
    @Throws(Exception::class)
    fun delete() {
        val jr = JsonResult()
        val id = getPara("id")
        val sysOrgService = enhance(SysOrgService::class.java)
        if (sysOrgService.checkChildOrg(id) || "1" == id) {
            jr.isSuccess = false
            jr.message = "该节点下才存在子节点，不能删除"
        } else {
            Db.deleteById("sys_org","id", id)
//            delete("sys_org",id)
            jr.isSuccess = true
            jr.message = "删除成功"
        }

        renderJson(jr)
    }

    @Throws(Exception::class)
    fun findById() {
        val jr = JsonResult()
        val id = getPara("id")
        val sql = Db.getSqlPara("system.sys_org_findSysOrgById", hashMapOf("id" to id))
        val record: Record? = Db.findFirst(sql)
        if (null != record) {
            jr.isSuccess = true
            jr.o = record
        } else {
            jr.isSuccess = false
            jr.message = "查找失败"
        }
        renderJson(jr)
    }
}