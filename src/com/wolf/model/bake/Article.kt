package com.wolf.model.bake

import com.wolf.model.base.BaseArticle

class Article: BaseArticle<Article>() {

    companion object {

        val dao:Article = Article()
    }
}