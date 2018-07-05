
package com.wolf.config;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.TxByMethodRegex;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.wolf.Interceptor.CommonInterceptor;
import com.wolf.controller.h5.H5Controller;
import com.wolf.controller.wx.WxArticleController;
import com.wolf.engine.FrostwolfListUrl;
import com.wolf.plugin.CommonTypePlugin;
import com.wolf.utils.Constant;
import com.wolf.utils.ImageUtils;

import java.util.List;

public class SystemConfig extends JFinalConfig
{
	
	@ Override
	public void configConstant( Constants me )
	{

		me.setDevMode( true );
		me.setViewType( ViewType.JFINAL_TEMPLATE );
		me.setError404View( "/pages/404.html" );
		me.setError500View( "/pages/404.html" );
		me.setBaseUploadPath("upload/temp");
		Constant.Companion.setBaseUploadPath("upload/temp");

	}

	@ Override
	public void configRoute( Routes me )
	{

		me.add(new SystemRoutes()); // 系统路由器配置
		me.add(new BakeRoutes()); // 烘焙路由器配置

		/**微信**/
		me.add( "/wx/article",WxArticleController.class  );
		me.add( "/h5",H5Controller.class  );
	}

	@ Override
	public void configPlugin( Plugins me )
	{
		Prop p = PropKit.use( "mysql_config.properties" );
		DruidPlugin c3p0Plugin = new DruidPlugin( p.get( "jdbcUrl" ) , p.get( "user" ) , p.get( "password" ) );
		me.add( c3p0Plugin );

		ActiveRecordPlugin arp = new ActiveRecordPlugin( c3p0Plugin );
		arp.setBaseSqlTemplatePath( PathKit.getRootClassPath() +"/sqls");
		arp.addSqlTemplate("all.sql");
		arp.setDevMode( true );
		arp.setShowSql( true );
		
		_MappingKit.mapping( arp );
		me.add( arp );
		me.add(new EhCachePlugin(  ));
		me.add( new CommonTypePlugin() );

		// 配置缓存插件

	}

	@ Override
	public void configInterceptor( Interceptors me )
	{
		// 配置拦截器
		me.add( new CommonInterceptor() );
		me.add( new TxByMethodRegex( "(.*save.*|.*update.*|.*delete.*)" ) );
		me.add(new SessionInViewInterceptor());
	}

	@ Override
	public void configHandler( Handlers me )
	{

	}

	@ Override
	public void configEngine( Engine me )
	{
		// 配置前台渲染引擎相关
		me.addDirective("url", new FrostwolfListUrl());
		me.addSharedObject("ctx", JFinal.me().getContextPath());
		me.setDevMode(true);
		me.addSharedMethod(ImageUtils.Companion);
		me.addSharedMethod(Constant.Companion);
		me.addSharedFunction("/common/template/pargingBar.html");

	}

	@Override
	public void afterJFinalStart() {
		// 设置系统参数
		List<Record> list=Db.find("select name,value from sys_config");
		for(Record r:list)
		{
			if(r.get("name").equals(Constant.Companion.getSYSTEM_NAME_KEY()))
			{
				Constant.Companion.setSYSTEM_NAME_VALUE(r.get("value"));
			}
		}
	}
}
