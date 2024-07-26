package rjr.studio.passgate.dao.service.type.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rjr.studio.passgate.dao.entity.type.TypeRoleEntity;
import rjr.studio.passgate.dao.repository.type.TypeBaseRepository;

@Service
public class TypeRoleServiceImpl extends TypeBaseServiceImpl<TypeRoleEntity, String> {

	@Autowired
	public TypeRoleServiceImpl(TypeBaseRepository<TypeRoleEntity, String> repo) {
		super(repo);
	}

}