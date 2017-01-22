package cn.chenxhusky.FileSpace.dao;

import cn.chenxhusky.FileSpace.po.User;
import cn.chenxhusky.FileSpace.util.Pager;

/**
 * 用于User的数据封装
 * @author husky
 *
 */
public interface UserDao {

	/**
	 * 根据用户id获取该用户对象
	 * @param Userid
	 * @return User,null
	 */
	public User getUserById(int Userid);
	
	/**
	 * 根据用户name获取该用户对象
	 * @param uname
	 * @return User , null
	 */
	public User getUserByName(String uname);
	
	/**
	 * 添加一个用户
	 * @param user
	 * @return true ,false
	 */
	public boolean addUser(User user);
	
	/**
	 * 删除一个用户
	 * @param userid
	 * @return true,false
	 */
	public boolean deleteUser(int userid);
	
	/**
	 * 修改该用户信息
	 * @param user
	 * @return true ,false
	 */
	public boolean modifyUserInfo(User user);
	
	/**
	 * 修改该用户密码
	 * @param user
	 * @return true ,false
	 */
	public boolean modifyUserPassword(User user);
	
	/**
	 * 获取总共的用户数目，不包括管理员用户
	 * @return
	 */
	public int getCountsOfUsers();
	
	/**
	 * 分页获取该页的用户集合
	 * @param pager
	 * @return Pager
	 */
	public Pager getUserByPager(Pager pager);
	
}
