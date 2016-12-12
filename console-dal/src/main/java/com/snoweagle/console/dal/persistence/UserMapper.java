package com.snoweagle.console.dal.persistence;

import java.util.List;
import java.util.Map;

import com.snoweagle.console.common.domain.system.User;

/**
 * @author n.tang
 *
 */
public interface UserMapper {

    /**
     * Query user by userName & passWord for Login
     * @param String userName, Password
     * @return UserDO
     * */
    public User selectUserByUserNameAndPasswd(Map<String, String> map);

    /**
     * Query user by Primary Key - UserID
     * @param int UserId
     * @return User
     * */
    public User selectByPrimaryKey(Long userId);

    public List<User> selectUsersByDynamicParams(User user,boolean isNeedPage);

    public int insert(User user);

    public int updateByPrimaryKeySelective(User user);
}