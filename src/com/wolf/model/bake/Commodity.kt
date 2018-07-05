package com.wolf.model.bake

import com.jfinal.plugin.activerecord.Model

class Commodity: Model<Commodity>() {

    override fun dao(): Commodity {
        return super.dao()
    }
}