package com.gdut.bean;

import java.util.List;



public class Page {
	// 排序方式
	public enum OrderType{
		asc, desc
	}
	
	public static final Integer MAX_PAGE_SIZE = 500000;	// 每页最大记录数限制

	private Integer pageNumber = 1;	// 当前页码
	private Integer pageSize = 20;	// 每页记录数
	private Integer totalCount = 0;	// 总记录数
	private Integer pageCount = 0;	// 总页数
	private String[] property = null;	// 查找属性名称(列名)
	private Object[] keyword = null;	// 查找关键字
	private String orderBy = "";	// 排序字段
	private OrderType orderType = OrderType.asc;	// 排序方式,默认倒序
	@SuppressWarnings("rawtypes")
	private List list;	// 数据List

	/**
	 * 获取当前页码
	 * @return 当前页码
	 */
	public Integer getPageNumber() {
		return pageNumber;
	}

	/**
	 * 设置当前页码
	 * @param pageNumber 当前页码
	 */
	public void setPageNumber(Integer pageNumber) {
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		this.pageNumber = pageNumber;
	}

	/**
	 * 获取每页记录数
	 * @return 每页记录数
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页记录数
	 * @param pageSize 每页记录数
	 */
	public void setPageSize(Integer pageSize) {
		if (pageSize < 1) {
			pageSize = 1;
		} else if(pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}
	
	/**
	 * 获取总记录数
	 * @return 总记录数
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总记录数
	 * @param totalCount 总记录数
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 获取总页数
	 * @return 总页数
	 */
	public Integer getPageCount() {
		pageCount = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			pageCount ++;
		}
		return pageCount;
	}

	/**
	 * 设置总页数
	 * @param pageCount 总页数
	 */
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * 获取查找属性名称
	 * @return 查找属性名称
	 */
	public String[] getProperty() {
		return property;
	}

	/**
	 * 设置查找属性名称
	 * @param property 查找属性名称
	 */
	public void setProperty(String[] property) {
		this.property = property;
	}
	
	/**
	 * 获取查找关键字
	 * @return 查找关键字
	 */
	public Object[] getKeyword() {
		return keyword;
	}

	/**
	 * 设置查找关键字
	 * @param keyword 查找关键字
	 */
	public void setKeyword(Object[] keyword) {
		this.keyword = keyword;
	}
	
	/**
	 * 获取排序字段
	 * @return 
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置排序字段
	 * @param orderBy 排序字段
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	/**
	 * 获取排序类型
	 * @return 排序类型
	 */
	public OrderType getOrderType() {
		return orderType;
	}

	/**
	 * 设置排序类型
	 * @param orderType 排序类型
	 */
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	/**
	 * 获取查询到的对象的集合
	 * @return List 取查询到的对象的集合
	 */
	@SuppressWarnings("rawtypes")
	public List getList() {
		return list;
	}

	public void setList(@SuppressWarnings("rawtypes") List list) {
		this.list = list;
	}

}
