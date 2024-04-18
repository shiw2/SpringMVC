package com.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.dao.IUserDao;
import com.demo.model.User;
import com.demo.model.UserExtend;
import com.demo.service.IUserService;
import com.demo.vo.UserRes;

@Service
public class UserServiceImpl implements IUserService {

	// 自动装配
	@Resource
	private IUserDao userDao;
	private int result;

	/**
	 * 分页
	 */
	public List<UserExtend> queryUserPager(int pageNO, int size) {
		int skip = (pageNO - 1) * size;
		return userDao.queryUserPager(skip, size);
	}

	/**
	 * 分页
	 */
	public List<UserRes> queryUserPager_1(int pageNO, int size) {

		List<UserRes> list = new ArrayList<UserRes>();

		int skip = (pageNO - 1) * size;
		List<UserExtend> userList = userDao.queryUserPager(skip, size);
		for (int i = 0; i < userList.size(); i++) {
			UserRes res = new UserRes();

			res.setUserId(userList.get(i).getUser_id());
			res.setUserName(userList.get(i).getUser_name());

			if (userList.get(i).getUser_sex().equals("1")) {
				res.setUserGender("男");

			} else if (userList.get(i).getUser_sex().equals("2")) {
				res.setUserGender("女");
			} else
				System.out.print("输入错误，请重试！");
			res.setUserBirth(userList.get(i).getUser_birthday());
			res.setUserEmail(userList.get(i).getUser_email());

			list.add(res);
		}

		return list;
	}

	/**
	 * 查询页数
	 */
	public int queryUserCountPageNO(int size, int pageNO) {
		int queryUserCount = userDao.queryUserCount();
		int a = queryUserCount % size;
		int b = queryUserCount / size;
		int result = 0;
		if (a == 0)
			result = b;
		else
			result = b + 1;
		int num = 0;
		if (pageNO < result)
			num = pageNO + 1;
		else
			num = 1;

		return num;
	}

	/**
	 * 翻页
	 * 
	 * 
	 * 
	 * public int queryUserCountCurrentPageNO(int pageNO) { int num = 0; if (pageNO
	 * < result) num = pageNO + 1; else num = 0;
	 * 
	 * return num;
	 * 
	 * }
	 */

	/**
	 * 查询用户总数
	 */
	public int queryUserCount() {
		return userDao.queryUserCount();
	}

	/**
	 * 根据用户id查询用户
	 */
	public User queryUserById(int userid) {
		return userDao.queryUserById(userid);
	}

	/**
	 * 新增用户
	 */
	public int insertUser(User user) {
		return userDao.insertUser(user);
	}

	/**
	 * 修改用户
	 */
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	/**
	 * 根据用户id删除用户
	 */
	public int deleteUserById(int user_id) {
		return userDao.deleteUserById(user_id);
	}

	/**
	 * 删除多个用户
	 */
	public int deleteUsers(int[] userIds) {
		return userDao.deleteUsers(userIds);
	}
}