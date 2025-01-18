package com.f1.Formula1.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;

import com.f1.Formula1.model.ICopyable;

public abstract class AbstractCRUDService<T extends ICopyable<T>, R extends JpaRepository<T, Long>> {

	protected R repository;

	public AbstractCRUDService(R repository) {
		this.repository = repository;
	}

	public T create(T entity) {
		return repository.save(entity);
	}

	public T delete(long id) {
		T entity = repository.findById(id).orElse(null);
		repository.deleteById(id);
		return entity;
	}

	public T getById(long id) {
		return repository.findById(id).orElse(null);
	}

	public T update(T entity) {
		T existingEntity = getById(getEntityId(entity));
		if (existingEntity != null) {
			existingEntity.copyProperties(entity);
			return repository.save(existingEntity);
		}
		return null;
	}

	public List<T> getAll() {
		return repository.findAll(Sort.by(Sort.Order.asc("id")));
	}

	protected abstract Long getEntityId(T entity);
}
