package rjr.studio.passgate.api.controller.type.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import rjr.studio.passgate.api.controller.type.TypeRoleController;
import rjr.studio.passgate.api.view.model.type.TypeRole;
import rjr.studio.passgate.business.type.TypeBaseBusiness;
import rjr.studio.passgate.dao.entity.type.TypeRoleEntity;

@RestController
public class TypeRoleControllerImpl implements TypeRoleController {

	private TypeBaseBusiness<TypeRoleEntity, TypeRole, String> service;

	@Autowired
	public TypeRoleControllerImpl(TypeBaseBusiness<TypeRoleEntity, TypeRole, String> service) {
		this.service = service;
	}

	@Override
	public ResponseEntity<List<TypeRole>> findAll() {
		List<TypeRole> rtn = service.findAll();
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TypeRole> findByCode(String code) {
		TypeRole rtn = service.findByCode(code);
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<TypeRole> findByName(String name) {
		TypeRole rtn = service.findByName(name);
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}

}
