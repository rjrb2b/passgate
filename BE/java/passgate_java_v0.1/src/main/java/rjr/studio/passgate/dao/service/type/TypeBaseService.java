package rjr.studio.passgate.dao.service.type;

import java.io.Serializable;
import java.util.List;

import rjr.studio.passgate.dao.entity.type.TypeBaseEntity;

public interface TypeBaseService<E extends TypeBaseEntity, C extends Serializable> {

	List<E> findAll();
	
	E findByCode(Class<E> clazz, C code);
	
	E findByName(Class<E> clazz, String name);
	
	E save(E entity);
	
	void deleteByCode(Class<E> clazz, C code);

}
