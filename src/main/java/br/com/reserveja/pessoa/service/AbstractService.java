package br.com.reserveja.pessoa.service;

import java.io.Serializable;

import br.com.reserveja.pessoa.dao.GenericDao;

public abstract class AbstractService<T extends Object> {

	private GenericDao<T, Serializable> dao;

    
    public T insert(T t) {
    	t = getDao().salvar(t);
    	
    	return t;
    }


    public T list(Long id) {
    	
    	
    	return getDao().encontrar(id);
    }
    
    
	public GenericDao<T, Serializable> getDao() {
		return dao;
	}


	public void setDao(GenericDao<T, Serializable> dao) {
		this.dao = dao;
	}
}
