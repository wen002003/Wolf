package com.wolf.controller.h5

import com.wolf.controller.base.BaseController

class H5Controller:BaseController(){

    fun index(){
        render("index.html")
    }

    fun about(){
        render("about.html")
    }

    fun contact(){
        render("contact.html")
    }
}