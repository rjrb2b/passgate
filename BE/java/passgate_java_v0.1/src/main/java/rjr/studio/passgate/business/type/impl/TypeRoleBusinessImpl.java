package rjr.studio.passgate.business.type.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rjr.studio.passgate.api.view.model.type.TypeRole;
import rjr.studio.passgate.conf.mapping.Entity2Model;
import rjr.studio.passgate.dao.entity.type.TypeRoleEntity;
import rjr.studio.passgate.dao.service.type.TypeBaseService;

@Component
public class TypeRoleBusinessImpl extends TypeBaseBusinessImpl<TypeRoleEntity, TypeRole, String> {

	@Autowired
	public TypeRoleBusinessImpl(Entity2Model entity2Model, TypeBaseService<TypeRoleEntity, String> service) {
		super(entity2Model, service);
	}

	@Override
	protected Class<TypeRoleEntity> getEntityClass() {
		return TypeRoleEntity.class;
	}

	@Override
	protected Class<TypeRole> getModelClass() {
		return TypeRole.class;
	}

}