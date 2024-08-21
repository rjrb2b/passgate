package rjr.studio.passgate.api.controller.type;

import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import rjr.studio.passgate.api.view.type.TypeApp;

@RequestMapping(value = "/types/app")
public interface TypeAppController {

	@GetMapping(value = "", produces =  MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Find all types app", description = "Retrieves all types app")
	ResponseEntity<Set<TypeApp>> findAll();

	@GetMapping(value = "/byCode/{code}", produces =  MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Finds type app by code", description = "Retrieves an type app by its code")
	ResponseEntity<TypeApp> findByCode(
			@Parameter(in = ParameterIn.PATH, description = "Code of the type app to be retrieved", required=true)
			@PathVariable String code);
	
	@GetMapping(value = "/byName/{name}", produces =  MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Finds type app by name", description = "Retrieves an type app by its name")
	ResponseEntity<TypeApp> findByName(
			@Parameter(in = ParameterIn.PATH, description = "Name of the type app to be retrieved", required=true)
			@PathVariable String name);

}
