package com.wolf.config

import com.jfinal.config.Routes
import com.wolf.controller.bake.CommodityController

class BakeRoutes : Routes(){
    override fun config() {

        /**
         * 配置烘焙模块的controller
         * */
        add("/server/commodity", CommodityController::class.java,"/pages/bake/commodity")

    }

}