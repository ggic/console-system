package com.snoweagle.console.dal.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.snoweagle.console.common.domain.system.Permission;

/**
 * @author n.tang
 *
 */
@Repository
public class PermissionMapperImpl extends BaseDao implements PermissionMapper {

	/**
	 * Query permission by primary key - permissionId
	 * @param Long permissionId
	 * @return permission permission
	 * */
	@Override
	public Permission selectByPrimaryKey(Long permissionId) {
		
		return sqlSessionTemplate.selectOne("PermissionMapper.selectByPrimaryKey", permissionId);
	}

	/**
	 * Query permissions by userId 
	 * @param Long userId
	 * @return List<permission> permissionList
	 * */
	@Override
	public List<Permission> selectByUserId(Long userId) {
		
		return sqlSessionTemplate.selectList("PermissionMapper.selectByUserId", userId);
	}

    @Override
    public List<Permission> selectMenusByUserId(Long userId) {

        return sqlSessionTemplate.selectList("PermissionMapper.selectMenusByUserId", userId);
    }
}
