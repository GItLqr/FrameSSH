package com.lqr.page;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * @author CSDN_LQR
 * @工程 FrameSSH
 * @包名 com.lqr.page
 * @TODO 自定义可以设置limit分页的查询（默认的hql查询不支持分页）
 */
public class PageHibernateCallback<T> implements HibernateCallback<List<T>> {

	private int startIndex;
	private int pageSize;
	private String hql;
	private Object[] params;

	// ==========================链式设置 begin==========================
	public PageHibernateCallback setStartIndex(int startIndex) {
		this.startIndex = startIndex;
		return this;
	}

	public PageHibernateCallback setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public PageHibernateCallback setHql(String hql) {
		this.hql = hql;
		return this;
	}

	public PageHibernateCallback setParams(Object[] params) {
		this.params = params;
		return this;
	}

	// ==========================链式设置 end==========================

	@Override
	public List<T> doInHibernate(Session session) throws HibernateException,
			SQLException {
		// 1 通过hql语句，获得Query对象
		Query queryObject = session.createQuery(hql);
		// 2 条件设置
		for (int i = 0; i < params.length; i++) {
			queryObject.setParameter(i, params[i]);
		}
		// 3 分页
		queryObject.setFirstResult(startIndex);
		queryObject.setMaxResults(pageSize);
		// 4 查询所有
		return queryObject.list();
	}

}
