package com.wolf.controller.system

import com.jfinal.core.Controller
import com.wolf.model.system.SysUser
import com.wolf.service.system.SysPrivService
import com.wolf.service.system.SysUserService
import com.wolf.utils.Constant
import com.wolf.utils.Encrypt
import com.wolf.utils.JsonResult
import com.wolf.utils.StringUtils

class SystemController:Controller(){

//    @ActionKey("/aa")
    @Throws(Exception::class)
    fun index() {
        render("/login.html")
    }

    @Throws(Exception::class)
    fun login() {
        val user = getModel(SysUser::class.java, "user")
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            setAttr("username", user.getUsername())
            setAttr("message", "用户名或密码不能为空")
            render("/login.html")
            return
        }
        val sysUserService = enhance(SysUserService::class.java)
        var currentUser: SysUser? = null
        try {
            currentUser = sysUserService.login(user)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (null != currentUser) {
            // 放置 SESSION
            setSessionAttr(Constant.SESSION_USERNAME, currentUser.getUsername())
            setSessionAttr(Constant.SESSION_USERID, currentUser.getId())
            setSessionAttr(Constant.SESSION_NAME, currentUser.getName())
            setSessionAttr(Constant.SESSION_ORG_ID, currentUser.getOrgId())

            redirect("/system/home")
        } else {
            setAttr("username", user.getUsername())
            setAttr("message", "用户名或密码不正确!")
            render("/login.html")
        }
    }

    @Throws(Exception::class)
    fun quit() {
        removeSessionAttr(Constant.SESSION_USERNAME)
        removeSessionAttr(Constant.SESSION_USERID)
        removeSessionAttr(Constant.SESSION_NAME)
        removeSessionAttr(Constant.SESSION_ORG_ID)
        render("/login.html")
    }

    @Throws(Exception::class)
    fun reLogin() {
        setAttr("username", getSessionAttr(Constant.SESSION_USERNAME))
        removeSessionAttr(Constant.SESSION_USERNAME)
        removeSessionAttr(Constant.SESSION_USERID)
        removeSessionAttr(Constant.SESSION_NAME)
        removeSessionAttr(Constant.SESSION_ORG_ID)
        render("/login.html")
    }

    @Throws(Exception::class)
    fun home() {
        // 查找权限树
        val id = getSessionAttr<Any>(Constant.SESSION_USERID) as String
        if (null != id) {
            val currentUser:SysUser? = SysUser.dao.findById(id)
            currentUser?.let {
                val sysPrivService = enhance(SysPrivService::class.java)
                val list = sysPrivService.getSysPrivListByUser(currentUser)
                setAttr("privList", list)
                render("/pages/main.html")
                return
            }
            render("/login.html")
            return

        } else {
            render("/login.html")
        }

    }

    @Throws(Exception::class)
    fun editPassword() {
        render("/pages/editPassword.html")
    }

    @Throws(Exception::class)
    fun savePassword() {
        val oldPassword = getPara("oldPassword")
        val newPassword = getPara("newPassword")
        val id = Integer.parseInt(getSessionAttr<Any>(Constant.SESSION_USERID) as String)
        val u = SysUser.dao.findById(id)
        if (u.getPassword() == Encrypt.md5(oldPassword)) {
            u.setPassword(Encrypt.md5(newPassword))
            u.update()
            renderJson(JsonResult.success("修改成功 "))
        } else {
            renderJson(JsonResult.fail("原密码不正确"))
        }
    }

    @Throws(Exception::class)
    fun welcome() {
        render("/pages/welcome.html")
    }
}