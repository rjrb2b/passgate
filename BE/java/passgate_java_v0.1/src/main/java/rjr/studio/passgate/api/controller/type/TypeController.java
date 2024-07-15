package rjr.studio.passgate.api.controller.type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rjr.studio.passgate.dao.entity.type.TypeRoleEntity;
import rjr.studio.passgate.dao.service.type.TypeRoleService;

@RestController
@RequestMapping("/type")
public class TypeController {
	
	private final TypeRoleService typeRoleService;

	@Autowired
	public TypeController(TypeRoleService typeRoleService) {
		super();
		this.typeRoleService = typeRoleService;
	}

	@GetMapping("/roles")
	public ResponseEntity<List<TypeRoleEntity>> findAll() throws Exception {
		List<TypeRoleEntity> entities = typeRoleService.findAll();
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}

	@GetMapping("/role/byCode/{code}")
	public ResponseEntity<TypeRoleEntity> findByCode(@PathVariable(value = "code", required = true) String code)
			throws Exception {
		TypeRoleEntity entity = typeRoleService.findByCode(code);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@GetMapping("/role/byName/{name}")
	public ResponseEntity<TypeRoleEntity> findByName(@PathVariable(value = "name", required = true) String name)
			throws Exception {
		TypeRoleEntity entity = typeRoleService.findByName(name);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}


}
