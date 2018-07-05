package com.wolf.utils

class Constant{


    companion object {

        var  baseUploadPath:String=""

        var  path:String=""
        /**
         * 空字符串
         */
        val  PARAM_PAGESIZE = "pageSize"
        /**
         * 空字符串
         */
        val PARAM_CURRENT_PAGE = "pageIndex"

        /**
         * 空字符串
         */
        val EMPTY_CHARS = ""

        /**
         * 权限nodetype为目录
         */
        val PRIV_NODETYPE_ML = "01"

        /**
         * 权限nodetype为权限
         */
        val PRIV_NODETYPE_QX = "02"

        val SESSION_USERNAME = "session_username"

        val SESSION_USERID = "session_usernid"

        val SESSION_NAME = "session_name"

        val SESSION_ORG_ID = "session_org_id"


        val UPLOAD = "/upload"

        /**
         * 材料图片上传地址
         */
        val BAKE_MATERIALS_UPLOAD = "/bake_materials"

        /**
         * 配方图片上传地址
         */
        val BAKE_RECIPE_UPLOAD = "/bake_recipe"

        val BAKE_ARTICLE_UPLOAD = "/bake_ariticle"

        val COMMODITY_UPLOAD = "upload/commodity/"

        val SYSTEM_NAME_KEY="system_name"

        var SYSTEM_NAME_VALUE:String=""
    }

}