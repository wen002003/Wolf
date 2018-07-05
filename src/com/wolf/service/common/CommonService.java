package com.wolf.service.common;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class CommonService
{
	/**
	 * 获取节点类型
	 */
	public List getSysPrivCodeNodeType()
	{
		List<Record> list = Db.find( "select * from sys_priv_code_node_type" );
		return list;
	}
	
	public List getSysOrgCodeNodeType()
	{
		List<Record> list = Db.find( "select * from sys_org_code_node_type" );
		return list;
	}
}
