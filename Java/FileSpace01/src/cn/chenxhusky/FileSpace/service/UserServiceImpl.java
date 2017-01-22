package cn.chenxhusky.FileSpace.service;

import cn.chenxhusky.FileSpace.dao.TypeDao;
import cn.chenxhusky.FileSpace.dao.TypeDaoImpl;
import cn.chenxhusky.FileSpace.dao.UserDao;
import cn.chenxhusky.FileSpace.dao.UserDaoImpl;
import cn.chenxhusky.FileSpace.po.Type;
import cn.chenxhusky.FileSpace.po.User;
import cn.chenxhusky.FileSpace.util.Pager;

/**
 * 对UserService方法的实现
 * @author husky
 *
 */
public class UserServiceImpl implements UserService {

	
	//用到的对象
	private UserDao userdao = new UserDaoImpl();
	private TypeDao typedao = new TypeDaoImpl();
	
	//方法实现
	@Override
	public User findUserByName(String uname) {

		return userdao.getUserByName(uname);
	}

	@Override
	public boolean addUser(User user) {

		boolean result = false;
		//添加用户到数据库
		result = userdao.addUser(user);
		//定义默认Type
		Type type = new Type(userdao.getUserByName(user.getUname()).getUserid(),"默认",0);
		//插入默认Type
		result = typedao.addType(type);
		return result;
	}

	@Override
	public boolean deleteUser(int userid) {

		return userdao.deleteUser(userid);
	}

	@Override
	public boolean modifyUserInfo(User user) {
	
		return userdao.modifyUserInfo(user);
	}
	
	@Override
	public boolean modifyUserPassword(User user) {

		return userdao.modifyUserPassword(user);
	}

	@Override
	public Pager findAllUserByPager(Pager pager) {
		
		//填写，完善pager属性
		int totalItem = userdao.getCountsOfUsers();
		pager.setTotalItem(totalItem);
		
		pager.setTotalPage(totalItem%pager.getItemOfPage()==0?(totalItem/pager.getItemOfPage()):(totalItem/pager.getItemOfPage()+1));
		
		return userdao.getUserByPager(pager);
	}

	@Override
	public User findUserById(int userid) {

		return userdao.getUserById(userid);
	}

}
