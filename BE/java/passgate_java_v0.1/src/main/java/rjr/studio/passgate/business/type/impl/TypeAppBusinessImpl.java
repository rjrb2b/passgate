package rjr.studio.passgate.business.type.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rjr.studio.passgate.api.view.type.TypeApp;
import rjr.studio.passgate.conf.mapping.Entity2Model;
import rjr.studio.passgate.dao.entity.type.TypeAppEntity;
import rjr.studio.passgate.dao.service.type.TypeBaseService;

@Component
public class TypeAppBusinessImpl extends TypeBaseBusinessImpl<TypeAppEntity, TypeApp, String> {

	@Autowired
	public TypeAppBusinessImpl(Entity2Model entity2Model, TypeBaseService<TypeAppEntity, String> service) {
		super(entity2Model, service);
	}

	@Override
	protected Class<TypeAppEntity> getEntityClass() {
		return TypeAppEntity.class;
	}

	@Override
	protected Class<TypeApp> getModelClass() {
		return TypeApp.class;
	}

}