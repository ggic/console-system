package com.snoweagle.console.common.domain.system;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangshaobo on 2015/8/19.
 */
public class Menu implements Serializable {
    private Integer permissionId;
    private String permission;
    private String description;
    private String url;
    private Integer sort; 	// 排序
    private List<Menu> childMenus;
    public Menu(){}

    public Menu(Integer permissionId, String permission, String description, String url, Integer sort) {
        this.permissionId = permissionId;
        this.permission = permission;
        this.description = description;
        this.url = url;
        this.sort = sort;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<Menu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<Menu> childMenus) {
        this.childMenus = childMenus;
    }
}
