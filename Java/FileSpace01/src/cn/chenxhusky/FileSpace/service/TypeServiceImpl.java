package cn.chenxhusky.FileSpace.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.chenxhusky.FileSpace.dao.TypeDao;
import cn.chenxhusky.FileSpace.dao.TypeDaoImpl;
import cn.chenxhusky.FileSpace.po.Type;

/**
 * 对TypeService方法的实现
 * @author husky
 *
 */
public class TypeServiceImpl implements TypeService {

	//用到的对象
	private TypeDao typedao = new TypeDaoImpl();
	
	@Override
	public boolean addType(Type type) {

		return typedao.addType(type);
	}

	@Override
	public boolean deleteType(int userid, int tid, boolean flag) {
		
		return typedao.deleteType(userid, tid, flag);
	}

	@Override
	public boolean modifyType(Type type) {
		
		return typedao.modifyType(type);
	}

	@Override
	public List<Type> findTypesByUserAndParent(int uid, int parentid) {
		
		return typedao.getTypesByUserAndParent(uid, parentid);
	}

	@Override
	public List<Type> findTypesByUser(int uid) {
		
		return typedao.getTypesByUser(uid);
	}

	@Override
	public Type findTypeById(int tid) {
		
		return typedao.getTypeById(tid);
	}

	@Override
	public Map<Integer,String> findParentTypes(int tid) {
		
		Map<Integer,String> result = new LinkedHashMap<Integer,String>();
		String str = typedao.getParentTypes(tid);
		String[] strs = str.split(",");
		int[] types = new int[strs.length];
		for (int i = 0 ; i < strs.length; i ++){
			types[i] = Integer.parseInt(strs[i]);
			if (types[i] != 0) {
				result.put(types[i], typedao.getTypeById(types[i]).getTname());
			}
		}
		return result;
	}

	
//	public static void main(String[] args) {
//		TypeService typeservice = new TypeServiceImpl();
//		System.out.println(typeservice.findParentTypes(9).keySet());
//	}
}
