package com.wolf.config;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.wolf.model.bake.*;
import com.wolf.model.system.*;

public class _MappingKit {

	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("bake_customer", "id", BakeCustomer.class);
		arp.addMapping("bake_materials", "id", BakeMaterials.class);
		arp.addMapping("bake_recipe", "id", BakeRecipe.class);
		arp.addMapping("bake_recipe_detail", "id", BakeRecipeDetail.class);
		arp.addMapping("article_type", "id", ArticleType.class);
		
		arp.addMapping("sys_org", "id", SysOrg.class);
		arp.addMapping("sys_priv", "id", SysPriv.class);
		arp.addMapping("sys_role", "id", SysRole.class);
		// Composite Primary Key order: priv_id,role_id
		arp.addMapping("sys_role_priv", "priv_id,role_id", SysRolePriv.class);
		arp.addMapping("sys_system", "id", SysSystem.class);
		arp.addMapping("sys_user", "id", SysUser.class);
		// Composite Primary Key order: role_id,user_id
		arp.addMapping("sys_user_role", "role_id,user_id", SysUserRole.class);
		
		arp.addMapping("article", "id", Article.class);
		arp.addMapping("articlecomment", "id", ArticleComment.class);
		arp.addMapping("order_form", "id", OrderForm.class);
		arp.addMapping("order_form_detail", "id", OrderFormDetail.class);
		arp.addMapping("inf_commodity", "id", Commodity.class);
	}
}