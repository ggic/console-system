package com.snoweagle.console.service.system;

import java.util.*;

import com.snoweagle.console.common.domain.system.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snoweagle.console.common.domain.system.Permission;
import com.snoweagle.console.dal.persistence.PermissionMapper;

@Service
public class PermissionService {
    private static final Logger logger = LoggerFactory.getLogger(PermissionService.class);
	@Autowired
    PermissionMapper permissionMapper;

	/**
	 * Query permissions with userId
	 * @param Long userId
	 * @return List<permission>
	 * */
	public List<Permission> queryPermissionsByUserId(Long userId) {
		return permissionMapper.selectByUserId(userId);
	}

	/**
	 * Query permission with primary key - permissionId
	 * @param Long permissionId
	 * @return permission
	 * */
	public Permission queryByPrimaryKey(Long permissionId) {
		return permissionMapper.selectByPrimaryKey(permissionId);
	}

    /**
     * 获取所有权限菜单
     * @param userId
     * @return
     */
    public List<Menu> findUserMenuTree(Long userId) {
        List<Menu> menus = new ArrayList<Menu>();
        List<Permission> permissions = permissionMapper.selectMenusByUserId(userId);
        for (Permission p : permissions) {
            if (p.getParentId()==null) {
                Menu base = new Menu(p.getPermissionId(),p.getPermission(),p.getDescription(),p.getUrl(),p.getSort());
                setChildNode(base,permissions);
                menus.add(base);
            }
        }
        return menus;
    }

    /**
     * 递归设置子节点
     * @param parent
     * @param permissions
     */
    private void setChildNode(Menu parent,List<Permission> permissions){
        List<Menu> childs = new ArrayList<Menu>();
        Integer pPid = parent.getPermissionId();
        for (Permission p : permissions) {
            if(pPid==p.getParentId()){
                Menu child = new Menu(p.getPermissionId(),p.getPermission(),p.getDescription(),p.getUrl(),p.getSort());
                if(isParentNode(child,permissions)) {
                    setChildNode(child, permissions);
                }
                childs.add(child);
            }
        }
        parent.setChildMenus(childs);
    }

    /**
     * 是否父节点
     * @param menu
     * @param permissions
     * @return
     */
    private boolean isParentNode(Menu menu,List<Permission> permissions){
        Integer id =menu.getPermissionId();
        for(Permission p : permissions){
            if(id == p.getParentId()){
                return true;
            }
        }
        return  false;
    }
}
