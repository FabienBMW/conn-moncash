package fr.smartds.connmoncash.service;


import fr.smartds.connmoncash.exceptions.DAOException;
import fr.smartds.connmoncash.exceptions.FormValidationException;

import java.util.List;

public interface ICrudService<T, ID> {
	List<T> getAll() throws DAOException;
	void add(T entity) throws DAOException;
//	void update(T entity) throws DAOException;
	void delete(ID id) throws DAOException;
	T find(ID id)throws DAOException;
//	void saveAll(Iterable<T> iterable) throws DAOException;
	T findId(Long id) throws FormValidationException;
}
