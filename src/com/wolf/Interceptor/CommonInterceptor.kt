package com.wolf.Interceptor

import com.jfinal.aop.Interceptor
import com.jfinal.aop.Invocation
import com.jfinal.log.Log4jLog
import com.wolf.utils.Constant
import com.wolf.utils.StringUtils
import java.io.PrintWriter
import java.io.StringWriter
import java.util.*

class CommonInterceptor : Interceptor {
    private val logger = Log4jLog.getLog(CommonInterceptor::class.java)

    private val checkPath = arrayOf("server", "system", "sysOrg", "sysPriv", "sysRole", "sysUser")

    override fun intercept(inv: Invocation?) {

        val path = inv?.getViewPath()
        val c = inv?.getController()
        for (i in checkPath.indices) {
            if (path?.split("/".toRegex())?.dropLastWhile { it.isEmpty() }!!.toTypedArray()[1] == checkPath[i]) {
                val username = c?.getSessionAttr<String>(Constant.SESSION_USERNAME)
                if (StringUtils.isBlank(username) && "login" != inv?.getMethodName()) {
                    c?.render("/login.html")
                    return
                }
                val pageSize = c?.request?.getParameter(Constant.PARAM_PAGESIZE)
                val currentPage = c?.request?.getParameter(Constant.PARAM_CURRENT_PAGE)
                if (null == pageSize || StringUtils.isBlank(pageSize))
                    c?.request?.setAttribute("pageSize", Constant.PARAM_PAGESIZE)
                if (null == currentPage || StringUtils.isBlank(currentPage))
                    c?.request?.setAttribute("currentPage", 1)
            }
        }
        try {
            inv?.invoke()
        } catch (e: Exception) {
            val uuid = UUID.randomUUID().toString()
            val sw = StringWriter()
            e.printStackTrace(PrintWriter(sw, true))
            var str = sw.toString()
            str = "序列号：$uuid &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$str"
            val ee = str.replace("at ".toRegex(), " <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;at ")
            c?.setAttr("errorInfo", ee)
            c?.setAttr("errorNumber", "系统发生错误：错误码：$uuid")
            c?.render("/pages/error.html")
            logger.error(uuid, e)
        }

    }

}