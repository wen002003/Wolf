package com.wolf.engine

import com.jfinal.template.Directive
import com.jfinal.template.Env
import com.jfinal.template.expr.ast.ExprList
import com.jfinal.template.io.Writer
import com.jfinal.template.stat.Scope

/**
 * 分页查询列表url
 */
class FrostwolfListUrl:Directive(){

    var url:String=""

    var autoQuery:Boolean=true

    /**
     * 处理参数
     * 0：url
     * 1：开启自动查询
     */
    override fun setExprList(exprList: ExprList) {
        url = exprList.getExpr(0).toString()
        val tep:String = exprList.getExpr(1).toString()
        autoQuery=tep.toBoolean()
    }
    override fun exec(env: Env?, scope: Scope?, writer: Writer?) {
        var resultStr="<script type=\"text/javascript\">\n" +   "var  frostwolf_query_url='"+url+"';\n"
        if(autoQuery)
        {
            resultStr+="query();\n"
        }
        resultStr+="</script>"
        write(writer, resultStr)
    }

    /**
     * 不需要end字符
     */
    override fun hasEnd(): Boolean {
        return false
    }

}