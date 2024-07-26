package rjr.studio.passgate.dao.service.type.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import rjr.studio.passgate.dao.entity.type.TypeBaseEntity;
import rjr.studio.passgate.dao.repository.type.TypeBaseRepository;
import rjr.studio.passgate.dao.service.type.TypeBaseService;

public abstract class TypeBaseServiceImpl<E extends TypeBaseEntity, C extends Serializable>
		implements TypeBaseService<E, C> {

	protected TypeBaseRepository<E, C> repo;

	@Autowired
	protected TypeBaseServiceImpl(TypeBaseRepository<E, C> repo) {
		this.repo = repo;
	}

	public List<E> findAll() {
		return repo.findAll();
	}

	public E findByCode(Class<E> clazz, C code) {
		E rtn = null;
		rtn = repo.findById(code).orElseThrow(
				() -> new EntityNotFoundException(clazz.getSimpleName() + " CODE " + code.toString() + " not found"));
		return rtn;
	}

	public E findByName(Class<E> clazz, String name) {
		E rtn = null;
		rtn = repo.findByName(name)
				.orElseThrow(() -> new EntityNotFoundException(clazz.getSimpleName() + " NAME " + name + " not found"));
		return rtn;
	}

	public E save(E entity) {
		return repo.save(entity);
	}

	public void deleteByCode(Class<E> clazz, C code) {
		this.findByCode(clazz, code);
		repo.deleteById(code);
	}

}
