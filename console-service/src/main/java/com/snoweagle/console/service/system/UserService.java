package com.snoweagle.console.service.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.snoweagle.console.common.domain.pagination.PageResult;
import com.snoweagle.console.common.utils.ResultConvert;
import org.springframework.stereotype.Service;

import com.snoweagle.console.common.domain.system.User;
import com.snoweagle.console.dal.persistence.UserMapper;

/**
 * @author n.tang
 *
 */
@Service("userService")
public class UserService {

	@Resource
    UserMapper userMapper;
	
	/**
	 * query the list of user by Dynamic parameters
	 * @param  user
	 * @return list
	 * */
	public List<User> queryUserByDynamicParams(User user) {
		List<User>  list = userMapper.selectUsersByDynamicParams(user,false);
		return list;
	}
	
	/**
	 * when the user is exist return the user info
	 * @param  userName,passWord
	 * @return User 
	 * */
	public User queryUserByUserNameAndPasswd(String userName, String Passwd) {
		Map<String ,String> userMap = new HashMap<String, String>();
		userMap.put("userName", userName);
		userMap.put("passWord", Passwd);
		User user = userMapper.selectUserByUserNameAndPasswd(userMap);		
		return user;
	}
	
	/**
	 * Query the user info by primary key - userId
	 * @param userId
	 * @return User
	 * */
	public User queryUserByPrimaryKey(Long userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		return user;
	}


    public PageResult queryUserPagesByDynamicParams(User user) {

        List list = userMapper.selectUsersByDynamicParams(user, true);
        PageResult pageResult = ResultConvert.ConvertToPageResult(list);

        return pageResult;

    }

    public void addUser(User user){
        user.setCreateDate(new Date());
        user.setModifyDate(new Date());
        userMapper.insert(user);
    }

    public void modifyUser(User user){
        user.setModifyDate(new Date());
        userMapper.updateByPrimaryKeySelective(user);
    }
}
