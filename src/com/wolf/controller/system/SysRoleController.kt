package com.wolf.controller.system

import com.jfinal.plugin.activerecord.Db
import com.jfinal.plugin.activerecord.Record
import com.wolf.controller.base.BaseController
import com.wolf.model.system.PageInfo
import com.wolf.model.system.SysRole
import com.wolf.utils.JsonResult
import com.wolf.utils.StringUtils

class SysRoleController:BaseController(){
    @Throws(Exception::class)
    fun index() {
        render("/pages/system/sysRole/sys_roleIndex.html")
    }

    /**
     * 获取角色列表
     */
    @Throws(Exception::class)
    fun list() {
        val page = Db.paginate(pageIndex, pageSize, "select t.*", "from sys_role t ")
        renderJson(PageInfo(page))
    }

    /**
     * 进入编辑页面
     */
    @Throws(Exception::class)
    fun edit() {

        val id = getPara("id")
        if (StringUtils.isBlank(id)) {
            val p = SysRole()
            setAttr("sysRole", p)
        } else {
            val p = SysRole.dao.findById(id)
            setAttr("sysRole", p)
        }
        render("/pages/system/sysRole/sys_roleEdit.html")
    }

    /**
     * 保存
     */
    @Throws(Exception::class)
    fun save() {
        val jr = JsonResult()
        val p = getModel(SysRole::class.java)
        if (null == p.getId()) {
            p.setValidFlag("Y")
            p.save()
        } else {
            p.update()
        }
        jr.isSuccess = true
        jr.message = "保存成功"
        renderJson(jr)
    }

    /**
     * 删除角色
     */
    @Throws(Exception::class)
    fun delete() {
        val jr = JsonResult()
        val id = getPara("id")
        val record = Record().set("role_id", id)
        Db.delete("sys_role_priv", "role_id", record)

        Db.deleteById("sys_role","id", id)
        jr.isSuccess = true
        jr.message = "删除成功"
        renderJson(jr)
    }

    fun findById() {
        val jr = JsonResult()
        val id = getPara("id")
        //		Record record = Db.findFirst(Db.getSql( "sys_role_findSysRoleById" , new Record().set( "id" , id ) ) );
        val record: Record? = null
        if (null != record) {
            jr.isSuccess = true
            jr.o = record
        } else {
            jr.isSuccess = false
            jr.message = "查找失败"
        }
        renderJson(jr)
    }

    fun findPrivByRole() {
        val roleId = getPara("roleId")
        val list = Db.find(Db.getSqlPara( "system.sys_role_findPrivByRole" , hashMapOf("roleId" to roleId)) );
        renderJson(list)
    }

    /**
     * 设置角色权限
     */
    @Throws(Exception::class)
    fun saveRolePriv() {
        val roleId = getPara("roleId")
        val privId = getPara("privId")
        val record = Record().set("role_id", roleId)
        Db.delete("sys_role_priv", "role_id", record)
        if (StringUtils.isBlank(privId)) {
            val jr = JsonResult()
            jr.isSuccess = true
            renderJson(jr)
        }
        val privIdArr = privId.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i in privIdArr.indices) {
            Db.save("sys_role_priv", Record().set("role_id", roleId).set("priv_id", privIdArr[i]))
        }
        val jr = JsonResult()
        jr.isSuccess = true
        renderJson(jr)
    }

    /**
     * 获取角色列表
     */
    @Throws(Exception::class)
    fun findRoleList() {
        val list = Db.find( Db.getSqlPara( "system.sys_role_findAllRole"  ) )
        setAttr("roleList", list)
        renderJson(list)
    }
}