package cn.chenxhusky.FileSpace.service;

import cn.chenxhusky.FileSpace.po.User;
import cn.chenxhusky.FileSpace.util.Pager;

/**
 * 为UserAction提供方法服务
 * @author husky
 *
 */
public interface UserService {

	/**
	 * 通过用户id找到该用户对象
	 * @param userid
	 * @return User,null
	 */
	public User findUserById(int userid);
	
	/**
	 * 通过用户名找到该用户对象
	 * @param uname
	 * @return User对象，null
	 */
	public User findUserByName(String uname);
	
	/**
	 * 添加用户，创建默认分类（在user service层实现）
	 * @param user
	 * @return true,false
	 */
	public boolean addUser(User user);
	
	/**
	 * 用户删除，会将与用户相关的文件一同删除（自动）
	 * 用户删除，会将与用户相关的分类一同删除（自动）
	 * 用户删除，会将type_file表中与该用户相关的全部删除（自动）
	 * @param userid
	 * @return true,false
	 */
	public boolean deleteUser(int userid);
	
	/**
	 * 修改用户信息，用户不能修改自己的名称（限制）
	 * @param user
	 * @return true,false
	 */
	public boolean modifyUserInfo(User user);
	
	/**
	 * 修改用户密码
	 * @param user
	 * @return true,false
	 */
	public boolean modifyUserPassword(User user);
	
	/**
	 * 获取当前页的所有用户列表
	 * @param pager
	 * @return Pager
	 */
	public Pager findAllUserByPager(Pager pager);
}
