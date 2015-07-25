package com.gdut.dao;

import java.io.Serializable;
import java.util.List;

import com.gdut.bean.Page;

public interface BaseDao<T> {
	
	/**
	 * 保存
	 * 成功:对象持久化的id
	 * 失败:null*/
	public Serializable save(T entity);
	
	/**
	 * 删除
	 * entity:对象*/
	public boolean delete(T entity);
	
	/**
	 * 根据id删除
	 **/
	public boolean deleteById(Serializable id);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public T getById(Serializable id);
	
	public T loadById(Serializable id);
	
	/**
	 * 修改
	 * entity:对象*/
	public boolean update(T entity);
	
	/**
	 * 查询所有记录
	 * @return 查询成功 ： 对象数据库里的所有对象集合;	<br>
	 *         查询失败 ： null
	 */
	public List<T> findAll();
	
	/**
	 * 根据属性名和属性值获取实体对象集合
	 * @param propertyName 属性名称
	 * @param value	           属性值
	 * @return 查询成功 ： 对象数据库里的所有对象集合;	<br>
	 *         查询失败 ： null
	 */
	public List<T> query(String propertyName,Object value);
	
	/**
	 * 根据属性名集合和属性值集合获取实体对象集合
	 * @param propertyNameArray 属性名称集合
	 * @param valueArray	           属性值集合
	 * @return 查询成功 ： 对象数据库里的所有对象集合;	<br>
	 *         查询失败 ： null
	 */
	public List<T> query(String[] propertyNameArray, Object[] valueArray);
	
	
	/**
	 * 根据属性名和模糊属性值模糊获取实体对象集合
	 * @param propertyName 属性名称
	 * @param value	           属性值
	 * @return 查询成功 ： 对象数据库里的所有对象集合;	<br>
	 *         查询失败 ： null
	 */
	public List<T> queryDim(String propertyName,Object value);
	
	
	/**
	 * 根据属性名和属性值数组获取实体对象集合.
	 * 查询的是某属性值在一定范围内的对象的集合
	 * @param propertyName 属性名称集合
	 * @param valueArray 属性值范围集合
	 * @return 查询成功 ： 对象数据库里的所有对象集合;	<br>
	 *         查询失败 ： null
	 */
	@SuppressWarnings("rawtypes")
	public List<T> findAll(String propertyName, List valueList);
	
	
	/**
	 * 分页
	 */
	public Page findByPager(Page pager);
	
	
	/**
	 * 获取数据库表中所有记录的计数
	 * @return 查找结果总条数
	 */
	public Integer getAllCounts();

}
