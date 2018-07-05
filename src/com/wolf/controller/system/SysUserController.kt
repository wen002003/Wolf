package com.wolf.controller.system

import com.jfinal.plugin.activerecord.Db
import com.jfinal.plugin.activerecord.Page
import com.jfinal.plugin.activerecord.Record
import com.wolf.controller.base.BaseController
import com.wolf.model.system.SysUser
import com.wolf.model.system.SysUserRole
import com.wolf.utils.Encrypt
import com.wolf.utils.JsonResult
import com.wolf.utils.StringUtils
import java.util.*

class  SysUserController:BaseController(){
    @Throws(Exception::class)
    fun index() {
        render("/pages/system/sysUser/sys_userIndex.html")
    }

    @Throws(Exception::class)
    fun editPassword() {
        val sysUser = SysUser.dao.findById(getPara("id"))
        setAttr("sysUser", sysUser)
        render("/pages/system/sysUser/editPassword.html")
    }

    @Throws(Exception::class)
    fun savePassword() {
        val newPassword = getPara("newPassword")
        val u = SysUser.dao.findById(getPara("id"))
        u.setPassword(Encrypt.md5(newPassword))
        u.update()
        renderJson(JsonResult.success("修改成功 "))
    }

    @Throws(Exception::class)
    fun list() {

        var orgId:String? = getPara("orgId")
        if(null==orgId)
            orgId=""

        val page: Page<Record>? = Db.paginate( pageIndex , pageSize , Db.getSqlPara( "system.sys_user_list_select" , hashMapOf("id" to orgId) ) );
        val list = page!!.list
        for (i in list.indices) {
            val record = list[i] as Record
            val roleList: List<Record>? =Db.find( Db.getSqlPara( "system.sys_user_findRoleByUser",hashMapOf("user_id" to record.get<String>("id") ) ))
            roleList?.forEach {
                record["roleName"]=record.getStr("roleName")?:"" +it.getStr("roleName")+" "
            }
        }
       /* val pageinfo=PageInfo(page)
        renderJson(pageinfo)*/
        setAttr("page", page)
        render("/pages/system/sysUser/sys_userList.html")

    }

    /**
     * 进入编辑页面
     */
    @Throws(Exception::class)
    fun edit() {
        // 获取角色列表
        val id = getPara("id")
        val org_id = getPara("org_id")
        if (StringUtils.isBlank(id)) {
            val sysUser = SysUser()
            sysUser.setOrgId(org_id)
            setAttr("sysUser", sysUser)
        } else {
            val sysUser = SysUser.dao.findById(id)
            val list = Db.find( Db.getSqlPara( "system.sys_user_findRoleIdsByUser" , hashMapOf("user_id" to id))  )
            setAttr("sysUser", sysUser)
//            setAttr("roleList", list)
        }

        val list = Db.find( Db.getSqlPara( "system.sys_role_findAllRole"  ) )
        setAttr("roleList", list)

        render("/pages/system/sysUser/sys_userEdit.html")
    }

    /**
     * 保存
     */
    @Throws(Exception::class)
    fun save() {
        val jr = JsonResult()
        val roles = getParaValues("roles")

        val p = getModel(SysUser::class.java)
        if (null == p.getId()) {
            p.setValidFlag("Y")
            p.setId(UUID.randomUUID().toString())
            p.save()
            if (null != roles && !StringUtils.isBlank(roles[0])) {
                for (i in roles.indices) {
                    Db.save("sys_user_role", Record().set("role_id", roles[i]).set("user_id", p.getId()))
                }
            }
        } else {
            p.update()
            val list = SysUserRole.dao.find( Db.getSqlPara( "system.sys_user_findRoleIdsByUser" , hashMapOf("user_id" to p.getId()) )  );
            for (r in list!!) {
                r.delete()
            }
            if (null != roles && roles.size > 0) {
                for (i in roles.indices) {
                    Db.save("sys_user_role", Record().set("role_id", roles[i]).set("user_id", p.getId()))
                }
            }

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
        Db.deleteById("sys_user", "id",id)
        // 删除用户所对应的角色
        var list = SysUserRole.dao.find( Db.getSqlPara( "system.sys_user_findRoleIdsByUser" , hashMapOf("user_id" to id))  );
        for (r in list!!) {
            r.delete()
        }
        jr.isSuccess = true
        jr.message = "删除成功"
        renderJson(jr)
    }
}