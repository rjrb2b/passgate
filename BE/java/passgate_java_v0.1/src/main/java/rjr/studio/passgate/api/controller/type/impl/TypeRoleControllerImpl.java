package rjr.studio.passgate.api.controller.type.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import rjr.studio.passgate.api.controller.type.TypeRoleController;
import rjr.studio.passgate.dao.entity.type.TypeRoleEntity;
import rjr.studio.passgate.dao.service.type.TypeBaseCrudService;

@RestController
public class TypeRoleControllerImpl implements TypeRoleController {

	private TypeBaseCrudService<TypeRoleEntity, String> service;

	@Autowired
	public TypeRoleControllerImpl(TypeBaseCrudService<TypeRoleEntity, String> service) {
		this.service = service;
	}

	@Override
	public ResponseEntity<List<TypeRoleEntity>> findAll() {
		List<TypeRoleEntity> entities = service.findAll();
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TypeRoleEntity> findByCode(String code) {
		TypeRoleEntity entity = service.findByCode(TypeRoleEntity.class, code);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<TypeRoleEntity> findByName(String name) {
		TypeRoleEntity entity = service.findByName(TypeRoleEntity.class, name);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

}
