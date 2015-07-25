package com.gdut.service.impl;


import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.gdut.bean.Page;
import com.gdut.dao.BaseDao;
import com.gdut.service.BaseService;



public class BaseServiceImpl<T> implements BaseService<T> {
	
	private BaseDao<T> baseDao;
	
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	// 获取HttpSession
	public HttpSession getHttpSession(){
		return getRequest().getSession();
	}

	// 获取Request
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	
	
	//保存
	public Serializable save(T entity){
		return baseDao.save(entity);
	}

	//根据对象删除
	public boolean delete(T entity) {
		return baseDao.delete(entity);
	}

	//根据id删除
	public boolean deleteById(Serializable id) {
		return baseDao.deleteById(id);
	}

	//查询
	public T getById(Serializable id){
		return baseDao.getById(id);
	}

	public T loadById(Serializable id){
		return baseDao.loadById(id);
	}

	//修改
	public boolean update(T entity) {
		return baseDao.update(entity);
	}

	//查询所有记录
	public List<T> findAll(){
		return baseDao.findAll();
	}

	//根据属性名和属性值查询
	public List<T> query(String propertyName, Object value){
		return baseDao.query(propertyName, value);
	}

	//根据属性名集合和属性值集合查询
	public List<T> query(String[] propertyNameArray, Object[] valueArray) {
		return baseDao.query(propertyNameArray, valueArray);
	}

	//模糊查询,%value%
	public List<T> queryDim(String propertyName, Object value){
		return baseDao.queryDim(propertyName, value);
	}

	//根据属性名和属性值数组获取实体对象集合
	@SuppressWarnings("rawtypes")
	public List<T> findAll(String propertyName, List valueList) {
		return baseDao.findAll(propertyName, valueList);
	}
	
	
	public Page findByPage(Page page){
		return baseDao.findByPager(page);
	}

}
