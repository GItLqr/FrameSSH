package com.lqr.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {

	void save(T t);

	void saveOrUpdate(T t);

	void update(T t);

	void delete(T t);

	/**
	 * 根据主键得到实体数据
	 * 
	 * @param id
	 * @return
	 */
	T findById(Serializable id);

	/**
	 * 简单的查询所有数据
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * 根据条件得到对应条件的数据总记录数
	 * 
	 * @param condition
	 *            条件
	 * @param params
	 *            参数
	 * @return
	 */
	int getTotalRecord(String condition, Object[] params);

	/**
	 * 根据条件查询所有
	 * 
	 * @param condition
	 *            条件
	 * @param params
	 *            参数
	 * @return
	 */
	List<T> findAll(String condition, Object[] params);

	/**
	 * 条件+分页 查询所有
	 * 
	 * @param condition
	 *            条件
	 * @param params
	 *            参数
	 * @param startIndex
	 *            分页的起始
	 * @param pageSize
	 *            每次从数据库获得的数据量
	 * @return
	 */
	List<T> findAll(String condition, Object[] params, int startIndex,
			int pageSize);

	/**
	 * 离线条件查询，使用QBC
	 * 
	 * @param criteria
	 * @return
	 */
	List<T> findAll(DetachedCriteria criteria);

	/**
	 * 分页
	 * 
	 * @param criteria
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<T> findAll(DetachedCriteria criteria, int startIndex, int pageSize);
}
