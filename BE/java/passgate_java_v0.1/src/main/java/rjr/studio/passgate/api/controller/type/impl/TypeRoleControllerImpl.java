package rjr.studio.passgate.api.controller.type.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import rjr.studio.passgate.api.controller.type.TypeRoleController;
import rjr.studio.passgate.api.view.type.TypeRole;
import rjr.studio.passgate.business.type.TypeBaseBusiness;
import rjr.studio.passgate.dao.entity.type.TypeRoleEntity;

@RestController
public class TypeRoleControllerImpl implements TypeRoleController {

	private TypeBaseBusiness<TypeRoleEntity, TypeRole, String> business;

	@Autowired
	public TypeRoleControllerImpl(TypeBaseBusiness<TypeRoleEntity, TypeRole, String> business) {
		this.business = business;
	}

	@Override
	public ResponseEntity<Set<TypeRole>> findAll() {
		Set<TypeRole> rtn = business.findAll();
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TypeRole> findByCode(String code) {
		TypeRole rtn = business.findByCode(code);
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<TypeRole> findByName(String name) {
		TypeRole rtn = business.findByName(name);
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}

}
