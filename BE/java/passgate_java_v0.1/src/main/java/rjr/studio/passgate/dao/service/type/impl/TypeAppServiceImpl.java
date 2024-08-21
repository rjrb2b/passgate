package rjr.studio.passgate.dao.service.type.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rjr.studio.passgate.dao.entity.type.TypeAppEntity;
import rjr.studio.passgate.dao.repository.type.TypeBaseRepository;

@Service
public class TypeAppServiceImpl extends TypeBaseServiceImpl<TypeAppEntity, String> {

	@Autowired
	public TypeAppServiceImpl(TypeBaseRepository<TypeAppEntity, String> repo) {
		super(repo);
	}

}