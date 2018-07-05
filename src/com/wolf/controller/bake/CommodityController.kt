package com.wolf.controller.bake

import com.jfinal.kit.PathKit
import com.jfinal.plugin.activerecord.Db
import com.jfinal.plugin.activerecord.Page
import com.jfinal.plugin.activerecord.Record
import com.wolf.controller.base.BaseController
import com.wolf.utils.Constant
import com.wolf.utils.JsonResult
import com.wolf.utils.StringUtils
import java.io.File


class CommodityController : BaseController() {

    @Throws(Exception::class)
    fun index() {
        render("commodityIndex.html")
    }

    @Throws(Exception::class)
    fun list() {
        val page: Page<Record> = Db.paginate(pageIndex, pageSize, Db.getSqlPara("commodity.list_commodity",getQueryParam()));
        setAttr("page", page)
        render("commodityList.html")
    }

    @Throws(Exception::class)
    fun edit() {
        getPara("id")?.let {
            val record = Db.findById("inf_commodity", getParaToInt("id"))
            setAttr("commodity", record)
        }

        render("commodityEdit.html")
    }

    @Throws(Exception::class)
    fun save() {
        val filename = getPara("image");
        var image = filename
        if (StringUtils.isNotBlank(filename)) {
            PathKit.getWebRootPath()
            val file = File(PathKit.getWebRootPath() + "/" + Constant.baseUploadPath + "/" + filename)
            val target = File(PathKit.getWebRootPath() + "/" + Constant.COMMODITY_UPLOAD)
            println(target.exists())
            if (!target.exists())
                target.mkdirs()
            file.renameTo(File(PathKit.getWebRootPath() + "/" + Constant.COMMODITY_UPLOAD + filename))
            image = Constant.COMMODITY_UPLOAD + filename;
            file.delete()
        }
        if (null == getParaToInt("id")) {
            Db.save("inf_commodity", Record().set("name", getPara("name")).set("image", image))
        } else {
            val record = Db.findById("inf_commodity", getParaToInt("id")).set("name", getPara("name")).set("image", image)
            Db.update("inf_commodity", record)
        }
        renderJson(JsonResult.success("保存成功"))
    }

    @Throws(Exception::class)
    fun delete() {
        getPara("id")?.let {
            Db.deleteById("inf_commodity", getParaToInt("id"))
        }

        renderJson(JsonResult.success("删除成功"))
    }
}