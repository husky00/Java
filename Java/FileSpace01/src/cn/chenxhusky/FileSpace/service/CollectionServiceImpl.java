package cn.chenxhusky.FileSpace.service;

import java.util.ArrayList;
import java.util.List;

import cn.chenxhusky.FileSpace.dao.CollectionDao;
import cn.chenxhusky.FileSpace.dao.CollectionDaoImpl;
import cn.chenxhusky.FileSpace.dao.FileDao;
import cn.chenxhusky.FileSpace.dao.FileDaoImpl;
import cn.chenxhusky.FileSpace.po.Collection;
import cn.chenxhusky.FileSpace.po.File;

public class CollectionServiceImpl implements CollectionService {

	private CollectionDao collectionDao = new CollectionDaoImpl();
	
	@Override
	public boolean add(Collection collection) {

		return collectionDao.add(collection);
	}

	@Override
	public boolean delete(int fid,int uid) {

		return collectionDao.delete(fid,uid);
	}

	@Override
	public List<File> findCollections(int uid) {
		List<Collection> collections = collectionDao.getCollections(uid);
		List<File> files = new ArrayList<File>();
		FileDao filedao = new FileDaoImpl();
		
		for (int i = 0; i < collections.size(); i++) {
			File file = filedao.getFileById(collections.get(i).getFid());
			files.add(file);
		}
		return files;
	}

	@Override
	public Collection findCollection(int fid, int uid) {

		return collectionDao.getCollection(fid, uid);
	}

}
