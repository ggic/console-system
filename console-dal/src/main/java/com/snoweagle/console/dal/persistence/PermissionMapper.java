package com.snoweagle.console.dal.persistence;

import com.snoweagle.console.common.domain.system.Permission;

import java.util.List;


public interface PermissionMapper {
    /**
     * select the menu with primary key - menuId from table t_sys_menu
     * select * from t_sys_menu where MENU_ID = menuId
     */
    public Permission selectByPrimaryKey(Long menuId);
    
    /**
     * select menus of a user with his/her id from tables of t_sys_user_role,t_sys_role_menu & t_sys_menu.
     * select * from t_sys_menu where menu_id in (
     * 	select MENU_ID from t_sys_role_menu where role_id in(
     * 		select ROLE_ID from t_sys_user_role where USER_ID = userId))  
     * 
     * */
    public List<Permission> selectByUserId(Long userId);

    public List<Permission> selectMenusByUserId(Long userId);
}