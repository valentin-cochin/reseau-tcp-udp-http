package com.cda.sujets.suivi.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {
	T save(T o);
	boolean removeById(int id);
	T update(T o);
	Optional<T> findById(int id);
	List<T> getAll();
	int removeAll();
}
