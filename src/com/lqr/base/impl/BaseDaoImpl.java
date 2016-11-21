package com.lqr.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lqr.base.BaseDao;
import com.lqr.page.PageHibernateCallback;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	// T 编译时，只是变量。在运行时，才可以获取具体的类型
	private Class<?> entityClass;

	/**
	 * 1、通过this.getClass().getGenericSuperclass()得到type --> BaseDaoImpl<User>
	 * 
	 * 2、再通过 type.getActualTypeArguments()[0]得到所有的范型类型 --> User
	 */
	public BaseDaoImpl() {
		// 获得运行时的类型，BaseDaoImpl<User>被参数化的类型
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		// 获得实际参数的类型。该方法获得所有，但此时只有一个（如Map则可以获得2个）
		entityClass = (Class<?>) type.getActualTypeArguments()[0];
	}

	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void saveOrUpdate(T t) {
		this.getHibernateTemplate().saveOrUpdate(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		// entityClass.getName()：得到的是类的全限定名，如:com.lqr.domain.User
		return this.getHibernateTemplate()
				.find("from " + entityClass.getName());
	}

	@Override
	public int getTotalRecord(String condition, Object[] params) {
		String hql = "select count(c) from " + entityClass.getName()
				+ " c where 1=1 " + condition;
		List<Long> list = getHibernateTemplate().find(hql, params);
		return list.get(0).intValue();
	}

	@Override
	public List<T> findAll(String condition, Object[] params) {
		return getHibernateTemplate().find(
				"from " + entityClass.getName() + " where 1=1" + condition,
				params);
	}

	@Override
	public List<T> findAll(String condition, Object[] params, int startIndex,
			int pageSize) {

		// 创建自定义查询对象
		PageHibernateCallback<T> callback = new PageHibernateCallback<T>();

		// 设置查询的条件、参数、分页信息并查询
		return getHibernateTemplate().execute(
				callback.setHql(
						"from " + entityClass.getName() + " where 1=1 "
								+ condition).setParams(params)
						.setStartIndex(startIndex).setPageSize(pageSize));

	}

	@Override
	public List<T> findAll(DetachedCriteria criteria) {
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public List<T> findAll(DetachedCriteria criteria, int startIndex,
			int pageSize) {
		return getHibernateTemplate().findByCriteria(criteria, startIndex,
				pageSize);
	}

}
