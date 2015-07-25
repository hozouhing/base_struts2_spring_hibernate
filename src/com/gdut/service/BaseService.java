package com.gdut.service;

import java.io.Serializable;
import java.util.List;

import com.gdut.bean.Page;

public interface BaseService<T> {
	
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	public Serializable save(T entity);
	
	/**
	 * 根据对象删除
	 * @param entity
	 * @return
	 */
	public boolean delete(T entity);
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	public boolean deleteById(Serializable id);
	
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	public T getById(Serializable id);
	
	public T loadById(Serializable id);
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	public boolean update(T entity);
	
	/**
	 * 查询所有记录
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * 根据属性名和属性值查询
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<T> query(String propertyName,Object value);
	
	/**
	 * 根据属性名集合和属性值集合查询
	 * @param propertyNameArray
	 * @param valueArray
	 * @return
	 */
	public List<T> query(String[] propertyNameArray, Object[] valueArray);
	
	/**
	 * 模糊查询,%value%
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<T> queryDim(String propertyName,Object value);
	
	/**
	 * 根据属性名和属性值数组获取实体对象集合
	 * @param propertyName
	 * @param valueList
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<T> findAll(String propertyName, List valueList);
	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public Page findByPage(Page page);

}
