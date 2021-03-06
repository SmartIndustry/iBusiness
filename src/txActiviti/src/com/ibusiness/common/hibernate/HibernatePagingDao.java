package com.ibusiness.common.hibernate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.mossle.core.hibernate.HibernateUtils;
import com.mossle.core.page.Page;
import com.ibusiness.common.page.IbPage;

/**
 * 提供分页查询功能.
 * 
 * @author Lingo
 */
public class HibernatePagingDao extends HibernateGenericDao {
    /** default constructor. */
    public HibernatePagingDao() {
    }

    /**
     * constructor.
     * 
     * @param sessionFactory
     *            SessionFactory
     */
    public HibernatePagingDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    // ============================================================================================
    // pagedQuery
    // ============================================================================================

    /**
     * 分页查询函数，使用hql.
     * 
     * @param hql
     *            HQL字符串
     * @param pageNo
     *            当前页号
     * @param pageSize
     *            每页最大记录数
     * @param values
     *            参数
     * @return 分页结果
     */
    @Transactional(readOnly = true)
    public Page pagedQuery(String hql, int pageNo, int pageSize,
            Object... values) {
        Assert.hasText(hql);
        Assert.isTrue(pageNo >= 1, "pageNo should be eg 1");

        // Count查询
        String countQueryString = "select count (*) "
                + HibernateUtils.removeSelect(HibernateUtils.removeOrders(hql));
        Integer totalCount = this.getCount(countQueryString, values);

        if (totalCount < 1) {
            return new Page();
        }

        // 实际查询返回分页对象
        Query query = createQuery(hql, values);
        int start = (pageNo - 1) * pageSize;
        List result = query.setFirstResult(start).setMaxResults(pageSize)
                .list();

        Page page = new Page(result, totalCount);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        return page;
    }

    /**
     * 分页查询函数，使用hql.
     * 
     * @param hql
     *            HQL字符串
     * @param pageNo
     *            当前页号
     * @param pageSize
     *            每页最大记录数
     * @param map
     *            Map
     * @return 分页结果
     */
    @Transactional(readOnly = true)
    public IbPage pagedQuery(String hql, int pageNo, int pageSize,
            Map<String, Object> map) {
        Assert.hasText(hql);
        Assert.isTrue(pageNo >= 1, "pageNo should be eg 1");

        // Count查询
        String countQueryString = "select count (*) "
                + HibernateUtils.removeSelect(HibernateUtils.removeOrders(hql));
        Integer totalCount = this.getCount(countQueryString, map);

        if (totalCount < 1) {
            return new IbPage();
        }

        // 实际查询返回分页对象
        Query query = createQuery(hql, map);
        int start = (pageNo - 1) * pageSize;
        List result = query.setFirstResult(start).setMaxResults(pageSize)
                .list();

        IbPage page = new IbPage(result, totalCount);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        return page;
    }

    /**
     * 分页查询函数，使用已设好查询条件与排序的<code>Criteria</code>.
     * 
     * @param criteria
     *            条件
     * @param pageNo
     *            当前页号
     * @param pageSize
     *            每页最大记录数
     * @return 含总记录数和当前页数据的Page对象.
     */
    @Transactional(readOnly = true)
    public IbPage pagedQuery(Criteria criteria, int pageNo, int pageSize) {
        Assert.notNull(criteria);
        Assert.isTrue(pageNo >= 1, "pageNo should be eg 1");
        Assert.isTrue(criteria instanceof CriteriaImpl);

        // 先把Projection和OrderBy条件取出来,清空两者来执行Count操作
        Projection projection = HibernateUtils.findProjection(criteria);

        List orderEntries = HibernateUtils.findOrderEntries(criteria);
        HibernateUtils.setOrderEntries(criteria, Collections.EMPTY_LIST);

        // 执行查询
        Integer totalCount = this.getCount(criteria);
        // 将之前的Projection和OrderBy条件重新设回去
        criteria.setProjection(projection);

        if (projection == null) {
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }

        HibernateUtils.setOrderEntries(criteria, orderEntries);

        // 返回分页对象
        if (totalCount < 1) {
            return new IbPage();
        }

        int start = (pageNo - 1) * pageSize;
        List result = criteria.setFirstResult(start).setMaxResults(pageSize)
                .list();

        IbPage page = new IbPage(result, totalCount);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        return page;
    }

    /**
     * 分页查询函数，根据entityClass和查询条件参数创建默认的<code>Criteria</code>.
     * 
     * @param <T>
     *            实体类型
     * @param entityClass
     *            实体类型
     * @param pageNo
     *            当前页号
     * @param pageSize
     *            每页最大记录数
     * @param criterions
     *            条件
     * @return 含总记录数和当前页数据的Page对象.
     */
    @Transactional(readOnly = true)
    public <T> IbPage pagedQuery(Class<T> entityClass, int pageNo, int pageSize,
            Criterion... criterions) {
        Criteria criteria = createCriteria(entityClass, criterions);

        return pagedQuery(criteria, pageNo, pageSize);
    }

    /**
     * 分页查询函数，根据entityClass和查询条件参数,排序参数创建默认的<code>Criteria</code>.
     * 
     * @param <T>
     *            实体类型
     * @param entityClass
     *            实体类型
     * @param pageNo
     *            当前页号
     * @param pageSize
     *            每页最大记录数
     * @param orderBy
     *            排序字段名
     * @param isAsc
     *            是否正序
     * @param criterions
     *            条件
     * @return 含总记录数和当前页数据的Page对象.
     */
    @Transactional(readOnly = true)
    public <T> IbPage pagedQuery(Class<T> entityClass, int pageNo, int pageSize,
            String orderBy, boolean isAsc, Criterion... criterions) {
        Criteria criteria = createCriteria(entityClass, orderBy, isAsc,
                criterions);

        IbPage page = this.pagedQuery(criteria, pageNo, pageSize);
        page.setOrderBy(orderBy);
        page.setOrder(isAsc ? "ASC" : "DESC");

        return page;
    }

    /**
     * 分页查询函数，根据entityClass和page参数进行查询.
     * 
     * @param <T>
     *            实体类型
     * @param entityClass
     *            实体类型
     * @param page
     *            分页里包含的各种参数
     * @return 含总记录数和当前页数据的Page对象.
     */
    @Transactional(readOnly = true)
    public <T> IbPage pagedQuery(Class<T> entityClass, Page page) {
        String orderBy = page.getOrderBy();
        String order = page.getOrder();

        Criteria criteria = null;

        if (page.isOrderEnabled()) {
            criteria = createCriteria(entityClass, orderBy, "ASC".equals(order));
        } else {
            criteria = createCriteria(entityClass);
        }

        IbPage resultPage = this.pagedQuery(criteria, page.getPageNo(),
                page.getPageSize());
        resultPage.setOrderBy(orderBy);
        resultPage.setOrder(order);

        return resultPage;
    }

    /**
     * 分页查询函数，根据entityClass和page参数进行查询.
     * 
     * @param <T>
     *            实体类型
     * @param entityClass
     *            实体类型
     * @param page
     *            分页里包含的各种参数
     * @param criterions
     *            条件
     * @return 含总记录数和当前页数据的Page对象.
     */
    @Transactional(readOnly = true)
    public <T> IbPage pagedQuery(Class<T> entityClass, IbPage page,
            Criterion... criterions) {
        String orderBy = page.getOrderBy();
        String order = page.getOrder();

        Criteria criteria = null;

        if (page.isOrderEnabled()) {
            criteria = createCriteria(entityClass, orderBy,
                    "ASC".equals(order), criterions);
        } else {
            criteria = createCriteria(entityClass, criterions);
        }

        IbPage resultPage = this.pagedQuery(criteria, page.getPageNo(),
                page.getPageSize());
        resultPage.setOrderBy(orderBy);
        resultPage.setOrder(order);

        return resultPage;
    }
}
