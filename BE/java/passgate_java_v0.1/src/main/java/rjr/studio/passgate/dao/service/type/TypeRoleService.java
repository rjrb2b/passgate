package rjr.studio.passgate.dao.service.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rjr.studio.passgate.dao.entity.type.TypeRoleEntity;
import rjr.studio.passgate.dao.repository.type.TypeBaseRepository;

@Service
public class TypeRoleService extends TypeBaseCrudService<TypeRoleEntity, String> {

	@Autowired
	public TypeRoleService(TypeBaseRepository<TypeRoleEntity, String> repo) {
		super(repo);
	}

}