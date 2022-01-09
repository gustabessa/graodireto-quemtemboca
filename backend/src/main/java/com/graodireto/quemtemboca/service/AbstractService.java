package com.graodireto.quemtemboca.service;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractService<T> {
	
	/**
	 * Important: heir classes should override this method.
	 * It allows the use of all implemented methods of this class, since a JpaRepository is mandatory.
	 * Returns a reference to the repository which the service is mainly responsible for.
	 * In example: UserService is responsible for UserRepository, therefore it overrides this method, returning an instance of UserRepository. 
	 *
	 * @return a reference to a JpaRepository.
	 */
	public abstract JpaRepository<T, Long> getRepository();
	
	public T save(T entity) {
		return getRepository().save(entity);
	}
	
	public T getById(Long id) {
		return getRepository().getById(id);
	}
	
	public void deleteById(Long id) {
		getRepository().deleteById(id);
	}
}
