package com.wolf.controller.common

import com.jfinal.core.Controller
import net.coobird.thumbnailator.Thumbnails
import java.io.File
import java.util.*

class CommonController : Controller() {

    @Throws(Exception::class)
    fun index() {
        render("/login.html")
    }

    @Throws(Exception::class)
    fun getImage() {
        var targetFile: File? = null
        var rootPath = ""
        try {
            val path = getPara("path")
            val width = getParaToInt("width")
            val height = getParaToInt("height")
            rootPath = File(CommonController::class.java.getResource("/").toURI().path).parentFile.parentFile.canonicalPath
            val pathArr = path.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val thumbnailsFile = rootPath + File.separator + pathArr[1] + File.separator + pathArr[2] + "_thumbnails"
            val file = File(thumbnailsFile)
            if (!file.exists())
                file.mkdirs()
            val sourceFile = File(rootPath + path)
            targetFile = File(thumbnailsFile + File.separator + sourceFile.name + "_thumbnails.jpg")
            if (!targetFile.exists()) {
                Thumbnails.of(sourceFile).size(width!!, height!!).toFile(targetFile)
            }
        } catch (e: Exception) {
            targetFile = File(rootPath + File.separator + "images" + File.separator + "NoImage.png")
        }
        renderFile(targetFile)
    }

    /**
     * 上传文件
     */
    fun upload(){
        val file = getFile()
        val suffix = file.fileName.substring(file.fileName.lastIndexOf(".") + 1)
        val filename =UUID.randomUUID().toString()+"."+suffix
        file.file.renameTo(File(file.uploadPath+"/"+filename))
        file.file.delete()
        renderJson(hashMapOf("fileName" to filename))
    }
}