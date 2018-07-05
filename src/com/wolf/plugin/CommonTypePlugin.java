package com.wolf.plugin;

import com.jfinal.log.Log4jLog;
import com.jfinal.plugin.IPlugin;


/**
 * @date 2016年11月27日
 * @author wen00
 * @email:wenht3212@gmail.com
 * @类说明：
 *
 */
public class CommonTypePlugin implements IPlugin {

	private static final Log4jLog logger = Log4jLog.getLog ( CommonTypePlugin.class );
	
	@ Override
	public boolean start()
	{
//		ArticleType.Companion.getDao().addCache();
		logger.info( "Article 加入缓存" );
		return true;
	}

	@ Override
	public boolean stop()
	{
		return true;
	}
	
}