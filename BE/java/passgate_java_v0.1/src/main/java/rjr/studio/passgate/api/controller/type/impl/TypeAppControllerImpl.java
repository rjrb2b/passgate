package rjr.studio.passgate.api.controller.type.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import rjr.studio.passgate.api.controller.type.TypeAppController;
import rjr.studio.passgate.api.view.type.TypeApp;
import rjr.studio.passgate.business.type.TypeBaseBusiness;
import rjr.studio.passgate.dao.entity.type.TypeAppEntity;

@RestController
public class TypeAppControllerImpl implements TypeAppController {

	private TypeBaseBusiness<TypeAppEntity, TypeApp, String> business;

	@Autowired
	public TypeAppControllerImpl(TypeBaseBusiness<TypeAppEntity, TypeApp, String> business) {
		this.business = business;
	}

	@Override
	public ResponseEntity<Set<TypeApp>> findAll() {
		Set<TypeApp> rtn = business.findAll();
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TypeApp> findByCode(String code) {
		TypeApp rtn = business.findByCode(code);
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<TypeApp> findByName(String name) {
		TypeApp rtn = business.findByName(name);
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}

}
