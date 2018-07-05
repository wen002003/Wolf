package com.wolf.controller.base

import com.jfinal.core.Controller
import com.jfinal.kit.StrKit
import com.jfinal.plugin.activerecord.ActiveRecordException
import com.jfinal.plugin.activerecord.Model
import com.jfinal.plugin.activerecord.Page
import com.jfinal.plugin.activerecord.TableMapping
import com.wolf.utils.Constant
import com.wolf.utils.StringUtils
import com.wolf.utils.TypeConverter
import java.util.*
import kotlin.collections.HashMap

open class BaseController :Controller(){
    open var pageSize:Int = 20
       get() {
            if(null!=getPara(Constant.PARAM_PAGESIZE))
            {
                field = getParaToInt(Constant.PARAM_PAGESIZE)
            }
            return field
        }

    open var pageIndex:Int =1
       get() {
            if(null!=getPara(Constant.PARAM_CURRENT_PAGE))
            {
                field = getParaToInt(Constant.PARAM_CURRENT_PAGE)
            }
            return field
        }


    @Throws(Exception::class)
    fun setPage(page: Page<*>): Map<*, *> {
        val map = HashMap<String, Any>()
        map.put("draw", getPara("draw"))
        map.put("recordsTotal", page.totalRow)
        map.put("recordsFiltered", page.totalRow)
        map.put("data", page.list)
        map.put("error", "")
        return map
    }


    @Throws(Exception::class)
    fun getQueryParam(): Map<*, *> {
        val resultMap = hashMapOf<String,Any?>()
        val requestNameEnu = request.parameterNames
        while (requestNameEnu.hasMoreElements()) {
            val key = requestNameEnu.nextElement().toString()
            if (key.indexOf("param.") > -1) {
                resultMap.put(key.substring(6, key.length), if (StringUtils.isBlank(getPara(key) as String)) null else getPara(key) as String)
            }
        }
        return resultMap

    }


    @Throws(Exception::class)
    fun <T> getModels(modelClass: Class<out Model<*>>): List<*> {
        val modelList = ArrayList<Model<*>>()

        val modelName = modelClass.simpleName

        val tempObj = modelClass.newInstance()

        if (tempObj is Model<*> == false) {
            throw IllegalArgumentException("getModel only support class of Model, using getBean for other class.")
        }

        val tempModel = tempObj as Model<*>
        val table = TableMapping.me().getTable(tempModel.javaClass) ?: throw ActiveRecordException("The Table mapping of model: " + modelClass.name + " not exists or the ActiveRecordPlugin not start.")

        val modelNameAndDot = if (StrKit.notBlank(modelName)) modelName + "." else null
        val parasMap = paraMap

        for ((paraName, paraValueArray) in parasMap) {
            val attrName: String
            if (modelNameAndDot != null) {
                if (paraName.startsWith(StrKit.firstCharToLowerCase(modelNameAndDot))) {
                    attrName = paraName.substring(modelNameAndDot.length)
                } else {
                    continue
                }
            } else {
                attrName = paraName
            }

            val colType = table.getColumnType(attrName) ?: throw ActiveRecordException("The model attribute $attrName is not exists.")

            try {
                for (i in paraValueArray!!.indices) {
                    if (modelList.size < paraValueArray.size) {
                        val temp2 = modelClass.newInstance()
                        val model = temp2 as Model<*>
                        modelList.add(model)
                    }
                    val paraValue = if (paraValueArray != null && paraValueArray.size > 0) paraValueArray[i] else null
                    val value = if (paraValue != null) TypeConverter.convert(colType, paraValue) else null
                    modelList[i].set(attrName, value)

                }
            } catch (e: Exception) {
                throw RuntimeException("Can not convert parameter: " + paraName, e)
            }

        }

        return modelList as List<T>
    }
}