package com.gdut.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.gdut.bean.Page;
import com.gdut.bean.Page.OrderType;
import com.gdut.dao.BaseDao;



public class BaseDaoImpl<T> implements BaseDao<T> {
	
	private Class<T> clazz;
	protected HibernateTemplate hibernateTemplate;
	private Logger log = Logger.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass(){
		if(this.clazz == null)
			this.clazz = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return this.clazz;
	}	
	
	protected Session getMySession(){
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}	

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}



	//保存
	public Serializable save(T entity) {
		try{
			Serializable id = hibernateTemplate.save(entity);
			return id;
		}catch(Exception e){
			log.error(getEntityClass() + " [保存]异常信息  : ", e);
			e.printStackTrace();
		}
		return null;
	}

	//删除
	public boolean delete(T entity) {
		try{
			hibernateTemplate.delete(entity);
			return true;
		}catch(Exception e){
			log.error(getEntityClass() + " [删除]异常信息 ： ", e);
			e.printStackTrace();
		}
		return false;
	}

	//根据id删除
	public boolean deleteById(Serializable id) {
		try{
			hibernateTemplate.delete(hibernateTemplate.load(getEntityClass(), id));
			return true;
		}catch(Exception e){
			log.error(getEntityClass() + " [删除]异常信息 ： ", e);
			e.printStackTrace();
		}
		return false;
	}

	//查询
	@SuppressWarnings("unchecked")
	public T getById(Serializable id) {
		try{
			return (T)hibernateTemplate.get(getEntityClass(), id);
		}catch(Exception e){
			log.error(getEntityClass() + " [id查询]异常信息 ： ", e);
			e.printStackTrace();
		}
		return null;
		
	}

	@SuppressWarnings("unchecked")
	public T loadById(Serializable id) {
		try{
			return (T)hibernateTemplate.load(getEntityClass(), id);
		}catch(Exception e){
			log.error(getEntityClass() + " [id查询]异常信息 ： ", e);
			e.printStackTrace();
		}
		return null;
		
	}

	//修改
	public boolean update(T entity) {
		try{
			hibernateTemplate.update(entity);
			return true;
		}catch(Exception e){
			log.error(getEntityClass() + " [对象修改]异常信息 ： ", e);
			e.printStackTrace();
		}
		return false;
		
	}
	
	//查询所有
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		List<T> list = null;
		try{
			list = hibernateTemplate.find("from " + getEntityClass().getName());
		}catch(Exception e){
			log.error(getEntityClass() + " [查询所有记录]异常信息 ： ", e);
			e.printStackTrace();
		}
		return list;
	}
	

	
	@SuppressWarnings("unchecked")
	public List<T> query(String propertyName, Object value) {
		List<T> list = null;
		try{
			list = hibernateTemplate.find("from " + getEntityClass().getName() + " as model where model." + propertyName + " = ?",value);
		}catch(Exception e){
			log.error(getEntityClass() + " [查询]异常信息 ： ", e);
			e.printStackTrace();
		}
		return list;
	}
	
	
	//根据属性名集合和属性值集合获取实体对象集合
	@SuppressWarnings("unchecked")
	public List<T> query(String[] propertyNameArray, Object[] valueArray) {
		List<T> list = null;
		try{
			String hql = "from " + getEntityClass().getName() + " as model where model." + propertyNameArray[0] + " = ?";
			
			for(int i = 1; i < propertyNameArray.length; i++) {
				hql = hql + " and  model." + propertyNameArray[i] + " = ?";
			}
			list = hibernateTemplate.find(hql, valueArray);
		}catch(Exception e){
			log.error(getEntityClass() + " [查询]异常信息 ： ", e);
			e.printStackTrace();
		}
		return list;
	}


	//模糊查询
	@SuppressWarnings("unchecked")
	public List<T> queryDim(String propertyName, Object value) {
		List<T> list =null;
		try{
			list = hibernateTemplate.find("from " + getEntityClass().getName() + " as model where model." + propertyName + " like ?",value);
		}catch(Exception e){
			log.error(getEntityClass() + " [模糊查询]异常信息 ： ", e);
			e.printStackTrace();
		}
		return list;
	}
	
	//根据属性名和属性值集合获取实体对象集合，查询的是某属性值在一定范围内的对象的集合
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll(String propertyName, List valueList) {
		List<T> list = null;
		try{
			String hql = "from " + getEntityClass().getName() + " as model where model." + propertyName + " in (:valueList)";
			list = getMySession().createQuery(hql).setParameterList("valueList", valueList).list();
		}catch(Exception e){
			log.error(getEntityClass() + " [查询]异常信息 ： ", e);
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 有分页支持的HQL查询
	 * 
	 * @param hql
	 *            查询用hql语句
	 * @param params参数列表
	 * @param start
	 *            开始下标
	 * @param limit
	 *            每页记录数
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAllByHQLPage(final String hql, final Object[] params,
			final int start, final int limit) {
		return (List<T>) hibernateTemplate
				.executeWithNativeSession(new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						if (params != null && params.length > 0) {
							for (int i = 0; i < params.length; i++) {
								query.setParameter(i, params[i]);
							}
						}
						// 表示是分页查询
						if (start != -1 && limit != -1) {
							query.setFirstResult(start);
							query.setMaxResults(limit);
						}
						return query.list();
					}
				});
	}

	/**
	 * 查找指定属性集集（逻辑与）的实体集合
	 * 
	 * @param entityClass
	 *            实体
	 * @param propertyNames
	 *            属性名数组
	 * @param propertyValues
	 *            属性值数组
	 * @return 实体集合
	 */
	public Page findByPager(Page pager) {
		String orderBy = pager.getOrderBy(); // 排列字段
		OrderType orderType = pager.getOrderType(); // 排列方法
		String[] property = pager.getProperty(); // 查找属性名称
		Object[] keyword = pager.getKeyword(); // 查询关键字
		if (!(property != null && property != null && keyword.length == property.length)) {
			throw new RuntimeException(
					"请提供正确的参数值！propertyNames与propertyValues必须一一对应!");
		}

		String queryString = "from " + getEntityClass().getName()
				+ " as model where ";
		for (int i = 0; i < keyword.length; i++) {
			queryString += " model." + property[i] + " = ? ";
			if (i != keyword.length - 1) {
				queryString += " and ";
			}
		}
		Query query = getMySession().createQuery(
				"select count(*) " + queryString);
		for (int i = 0; i < keyword.length; i++) {
			query.setParameter(i, keyword[i]);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		if (StringUtils.isNotEmpty(orderBy) && orderType != null) {
			if (orderType == OrderType.asc) {
				queryString += " order by " + pager.getOrderBy() + " asc";
			} else {
				queryString += " order by " + pager.getOrderBy() + " desc";
			}
		}
		pager.setTotalCount(count);
		pager.setList(this.findAllByHQLPage(queryString, keyword,
				(pager.getPageNumber() - 1) * pager.getPageSize(),
				pager.getPageSize()));
		return pager;
	}


	//获取数据库表中所有记录的计数
	public Integer getAllCounts(){
		int count = 0;
		try{
			String countString = getMySession().createQuery("select count(*) from " + getEntityClass().getName()).uniqueResult().toString();
			count = Integer.parseInt(countString);
		}catch(Exception e){
			log.error(getEntityClass() + " [获取数据库表中所有记录的计数]异常信息 ： ", e);
			e.printStackTrace();
		}
		return count;		
	}

}
