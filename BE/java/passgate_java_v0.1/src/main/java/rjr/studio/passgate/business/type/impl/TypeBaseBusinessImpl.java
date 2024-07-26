package rjr.studio.passgate.business.type.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rjr.studio.passgate.api.view.model.type.TypeBaseModel;
import rjr.studio.passgate.business.type.TypeBaseBusiness;
import rjr.studio.passgate.conf.mapping.Entity2Model;
import rjr.studio.passgate.dao.entity.type.TypeBaseEntity;
import rjr.studio.passgate.dao.service.type.TypeBaseService;
import rjr.studio.passgate.utility.ObjectUtility;

@Component
public abstract class TypeBaseBusinessImpl<E extends TypeBaseEntity, M extends TypeBaseModel, C extends Serializable>
		implements TypeBaseBusiness<E, M, C> {

	private Entity2Model entity2Model;
	private TypeBaseService<E, C> service;

	@Autowired
	protected TypeBaseBusinessImpl(Entity2Model entity2Model, TypeBaseService<E, C> service) {
		this.entity2Model = entity2Model;
		this.service = service;
	}

	public List<M> findAll() {
		List<M> rtn = new ArrayList<>();
		List<E> found = service.findAll();
		found.forEach(f -> rtn.add(entity2Model.mapper(f, getModelClass())));
		return rtn;
	}

	public M findByCode(C code) {
		E entity = service.findByCode(getEntityClass(), code);
		return entity != null ? entity2Model.mapper(entity, getModelClass()) : null;
	}
	
	public M findByName(String name) {
		E entity = service.findByName(getEntityClass(), name);
		return entity != null ? entity2Model.mapper(entity, getModelClass()) : null;
	}

	@SuppressWarnings("unchecked")
	public C save(M model) {
		E entityNew = entity2Model.mapper(model, getEntityClass());
		return (C) service.save(entityNew).getCode();
	}

	@SuppressWarnings("unchecked")
	public C updateByCode(C code, M model) throws Exception {
		E entityOld = service.findByCode(getEntityClass(), code);
		E entityNew = entity2Model.mapper(model, getEntityClass());
		E entityUpdate = ObjectUtility.mergeOldNew(entityOld, entityNew);
		return (C) service.save(entityUpdate).getCode();
	}

	public C deleteByCode(C code) {
		this.findByCode(code);
		service.deleteByCode(getEntityClass(), code);
		return code;
	}

	protected abstract Class<E> getEntityClass();

	protected abstract Class<M> getModelClass();

}
