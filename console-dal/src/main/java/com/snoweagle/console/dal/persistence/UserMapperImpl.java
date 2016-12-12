package com.snoweagle.console.dal.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import com.snoweagle.console.common.domain.system.User;

/**
 * @author n.tang
 *
 */
@Repository
public class UserMapperImpl extends BaseDao implements UserMapper {

    /**
     *
     * @param user
     * @param isNeedPage
     * @return
     */
	@Override
	public List<User> selectUsersByDynamicParams(User user,boolean isNeedPage) {

        if(isNeedPage){
            return sqlSessionTemplate.selectList("UserMapper.selectUserByDynamicParams",
                    user,new RowBounds(user.getPageNum(), user.getPageSize()));
        }else{
            return sqlSessionTemplate.selectList("UserMapper.selectUserByDynamicParams", user);
        }

	}


    /**
	 * select the user from table t_sys_user which meet the userName & passWd
	 * @param userMap<userName,passWord>
	 * @return User
	 * */
	@Override
	public User selectUserByUserNameAndPasswd(Map<String,String> userMap) {

		return sqlSessionTemplate.selectOne("UserMapper.login", userMap);
	}

	/**
	 * select the user from table t_sys_user by primary key userID
	 * @param int userId
	 * @return User
	 * */
	@Override
	public User selectByPrimaryKey(Long userId) {
		return sqlSessionTemplate.selectOne("UserMapper.selectByPrimaryKey", userId);
	}


    @Override
    public int insert(User user) {
        return sqlSessionTemplate.insert("UserMapper.insert", user);
    }

//    @Override
//    public int insertSelective(User user) {
//        return sqlSessionTemplate.insert("UserMapper.insertSelective", user);
//    }
    @Override
    public int updateByPrimaryKeySelective(User user) {
        return sqlSessionTemplate.update("UserMapper.updateByPrimaryKeySelective", user);
    }

}
