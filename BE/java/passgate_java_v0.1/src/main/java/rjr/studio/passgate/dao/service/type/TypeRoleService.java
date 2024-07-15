package rjr.studio.passgate.dao.service.type;

import java.util.List;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rjr.studio.passgate.dao.entity.type.TypeRoleEntity;
import rjr.studio.passgate.dao.repository.type.TypeRoleRepository;

@Service
public class TypeRoleService {

	private final TypeRoleRepository typeRoleRepository;

	@Autowired
	public TypeRoleService(TypeRoleRepository typeRoleRepository) {
		super();
		this.typeRoleRepository = typeRoleRepository;
	}

	public List<TypeRoleEntity> findAll() {
		return typeRoleRepository.findAll();
	}

	public TypeRoleEntity findByCode(String code) {
		return typeRoleRepository.findById(code)
				.orElseThrow(() -> new EntityNotFoundException("Typerole not found with code: " + code));
	}

	public TypeRoleEntity findByName(String name) {
		return typeRoleRepository.findByName(name)
				.orElseThrow(() -> new EntityNotFoundException("Typerole not found with code: " + name));
	}


}