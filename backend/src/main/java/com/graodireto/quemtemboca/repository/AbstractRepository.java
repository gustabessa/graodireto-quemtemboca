package com.graodireto.quemtemboca.repository;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class AbstractRepository<T> {

	@PersistenceContext
	protected EntityManager entityManager;
	
	protected List<T> executeQuery(String hql, Map<String, Object> params) {
		
		Query query = entityManager.createQuery(hql);
		
		if (params != null) {
			for(Entry<String, Object> entry : params.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		
		return (List<T>) query.getResultList();
	}
	
}
