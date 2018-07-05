package com.wolf.model.system

import com.jfinal.plugin.activerecord.Page

open class PageInfo<T>(var page:Page<T>){
    var code:String="0"
    var msg:String="success"
    var count:Int=page.totalRow
    var data:MutableList<T> = page.list

}