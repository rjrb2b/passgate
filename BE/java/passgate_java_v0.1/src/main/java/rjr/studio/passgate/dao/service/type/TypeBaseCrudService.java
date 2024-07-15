package rjr.studio.passgate.dao.service.type;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import rjr.studio.passgate.dao.entity.type.TypeBaseEntity;
import rjr.studio.passgate.dao.repository.type.TypeBaseRepository;

public abstract class TypeBaseCrudService<E extends TypeBaseEntity, CODE extends Serializable> {

	private TypeBaseRepository<E, CODE> repo;

	@Autowired
	protected TypeBaseCrudService(TypeBaseRepository<E, CODE> repo) {
		this.repo = repo;
	}

	public List<E> findAll() {
		return repo.findAll();
	}

	public E findByCode(Class<E> clazz, CODE code) {
		return repo.findById(code).orElseThrow(() -> new EntityNotFoundException(clazz.getSimpleName()+" code '"+code+"' not found"));
	}
	
	public E findByName(Class<E> clazz, String name) {
		return repo.findByName(name).orElseThrow(() -> new EntityNotFoundException(clazz.getSimpleName()+" name '"+name+"' not found"));
	}

	public E save(E entity) {
		return repo.save(entity);
	}

	public void deleteById(Class<E> clazz, CODE code) {
		this.findByCode(clazz, code);
		repo.deleteById(code);
	}

}
