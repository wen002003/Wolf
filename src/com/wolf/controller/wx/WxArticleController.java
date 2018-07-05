
package com.wolf.controller.wx;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.wolf.controller.base.BaseController;
import com.wolf.utils.Constant;
import com.wolf.utils.JsonResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @date 2016年9月26日
 * @author wen00
 * @email:wenht3212@gmail.com
 * @类说明：微信文章展示
 *
 */
public class WxArticleController extends BaseController
{
	public void index() throws Exception
	{
		render( "/wx/index.html" );
	}

	public void index2() throws Exception
	{
		List <Record>list = Db.find( "select t.title,t.image,t.id,t.addTime from article t order by addTime desc" );
		List resultList = new ArrayList();
		for(int i=0;i<list.size();i=i+3)
		{
			List subList = new ArrayList();
			if(null!=list.get( i ))
				subList.add(list.get( i ));
			if(list.size()>=(i+2) && null!=list.get( i +1))
				subList.add(list.get( i+1 ));
			if(list.size()>=(i+3)  && null!=list.get( i +2))
				subList.add(list.get( i +2));
			resultList.add( subList );
		}
		setAttr( "articleList" , resultList );
		renderJsp( "/wx/articleList.jsp" );
	}
	
	/**
	 * 获取内容页
	 */
	public void getContent() throws Exception
	{
		Integer id = getParaToInt( "id" );
		Record d = Db.findById( "article" , id );
		List<Record> list = Db.find( "select * from articlecomment t where t.articleId=? and t.validFlag=1" ,id);
		setAttr( "record" , d );
		setAttr( "commentList" , list);
		renderJsp( "/wx/articleContent.jsp" );
	}
	
	/**
	 * 保存评论
	 */
	public void saveComment() throws Exception
	{
		String message = getPara( "message" );
		Record r = new Record().set( "articleId" , Integer.parseInt( getPara( "articleId" ) ) ).set( "message" , message )
				.set( "ipAddress" , getRequest().getRemoteAddr()  ).set( "addTime" , new Date() ).set( "validFlag" , 0 ).set( "userId" , getSessionAttr(Constant.Companion.getSESSION_USERID() ) );
		Db.save( "articlecomment" , r );
		renderJson( JsonResult.success( "评论成功" ) );
	}
	
	public void contactUs() throws Exception
	{
		renderJsp( "/wx/contactUs.jsp" );
	}

}
