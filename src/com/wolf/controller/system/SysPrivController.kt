package com.wolf.controller.system

import com.jfinal.plugin.activerecord.Db
import com.wolf.controller.base.BaseController
import com.wolf.model.system.SysPriv
import com.wolf.service.common.CommonService
import com.wolf.service.system.SysPrivService
import com.wolf.utils.JsonResult
import com.wolf.utils.StringUtils

class SysPrivController:BaseController(){
    @Throws(Exception::class)
    fun index() {
        render("/pages/system/sysPriv/sys_privIndex.html")
    }

    /**
     * 异步获取权限树
     */
    @Throws(Exception::class)
    fun getPrivTreeAsync() {
        val id = getPara("id")
        val sysPrivService = enhance(SysPrivService::class.java)
        val list = sysPrivService.getSysPrivListAsync(id)
        renderJson(list)
    }

    /**
     * 获取所有的权限LIST
     */
    @Throws(Exception::class)
    fun getAllPrivTree() {
        val sysPrivService = enhance(SysPrivService::class.java)
        val list = sysPrivService.getAllSysPrivList()
        renderJson(list)
    }

    /**
     * 进入编辑页面
     */
    @Throws(Exception::class)
    fun edit() {
        val commonService = enhance(CommonService::class.java)
        setAttr("nodeTypeList", commonService.sysPrivCodeNodeType)

        val id = getPara("id")
        val parent_id = getPara("parent_id")
        if (StringUtils.isBlank(id)) {
            val p = SysPriv()
            p.setParentId(Integer.parseInt(parent_id))
            setAttr("sysPriv", p)
        } else {
            val p = SysPriv.dao.findById(id)
            setAttr("sysPriv", p)
        }
        render("/pages/system/sysPriv/sys_privEdit.html")
    }

    @Throws(Exception::class)
    fun save() {
        val jr = JsonResult()
        val p = getModel(SysPriv::class.java)
        if (null == p.getId()) {
            p.setValidFlag("1")
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
        val sysPrivService = enhance(SysPrivService::class.java)
        if (sysPrivService.checkChildPriv(id) || 1 == Integer.parseInt(id)) {
            jr.isSuccess = false
            jr.message = "该节点下才存在子节点，不能删除"
        } else {
            Db.deleteById("sys_priv","id", id)
            jr.isSuccess = true
            jr.message = "删除成功"
        }

        renderJson(jr)
    }

    @Throws(Exception::class)
    fun findById() {
        val jr = JsonResult()
        val id = getPara("id")
        val record = Db.findFirst( Db.getSqlPara( "system.sys_priv_findSysPrivById" , hashMapOf("id" to id)) );
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