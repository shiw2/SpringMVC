package com.demo.service;

import java.util.List;

import com.demo.model.User;
import com.demo.model.UserExtend;
import com.demo.vo.UserRes;

/**
 * 用户业务接口
 * 
 * @author shiw2
 *
 */
public interface IUserService {

	/**
	 * 分页
	 * 
	 * @param pageNO
	 * @param size
	 * @return
	 */
	public List<UserExtend> queryUserPager(int pageNO, int size);
	
	public List<UserRes> queryUserPager_1(int pageNO, int size);

	/**
	 * 查询用户总数
	 * 
	 * @return
	 */
	public int queryUserCount();
	/**public int queryUserCountCurrentPageNO(int pageNO); */
	public int queryUserCountPageNO(int size, int pageNO);

	/**
	 * 根据用户id查询用户
	 * 
	 * @param userid
	 * @return
	 */
	public User queryUserById(int userid);

	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */
	public int insertUser(User user);

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @return
	 */
	public int updateUser(User user);

	/**
	 * 根据用户id删除用户
	 * 
	 * @param user_id
	 * @return
	 */
	public int deleteUserById(int user_id);

	/**
	 * 删除多个用户
	 * 
	 * @param userIds
	 * @return
	 */
	public int deleteUsers(int[] userIds);
}