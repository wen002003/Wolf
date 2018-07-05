package com.wolf.utils

import com.jfinal.kit.PathKit
import net.coobird.thumbnailator.Thumbnails
import java.io.File

/**
 * 获取缩略图的方法
 */
class ImageUtils(){
    companion object {
       public final  fun getImage( width:Int=100,height:Int=100,path:String="images/default.jpg"):String{
            try {
                val file=File(PathKit.getWebRootPath()+"/"+path)
                if(!file.exists())
                    return "images/default.jpg"
                val pathArr = path.split("/")
                val suffix = file.name.substring(file.name.lastIndexOf(".") + 1)
                val thumbnails = pathArr.first()+ File.separator + pathArr[pathArr.count()-2] + "_thumbnails"
                val thumbnailsFilePath = PathKit.getWebRootPath() + File.separator + thumbnails
                val thumbnailsFile = File(thumbnailsFilePath)
                if (!thumbnailsFile.exists())
                    thumbnailsFile.mkdirs()
                val targetFileName = file.name.split(".").dropLast(1)[0]+ "_"+width+"_"+height + "_thumbnails."+suffix;
                val targetFile = File(thumbnailsFilePath + File.separator +targetFileName)
                if (!targetFile.exists()) {
                    Thumbnails.of(file).size(width, height).toFile(targetFile)
                }
                return thumbnails + File.separator +targetFileName
            } catch (e: Exception) {
                return "images/default.jpg"
            }
        }
    }
}

