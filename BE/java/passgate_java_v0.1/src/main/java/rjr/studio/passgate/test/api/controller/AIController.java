package rjr.studio.passgate.test.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import rjr.studio.passgate.api.view.LoginRequest;
import rjr.studio.passgate.service.impl.AuthService;

@RestController
@RequestMapping("/test/api/AI")
public class AIController {

	private final AuthService authService;

	@Autowired
	public AIController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@GetMapping("/test")
	public String test() {
		return "Applicazione online!";
	}

	@PostMapping("/login")
	public String login(@RequestBody LoginRequest loginRequest) throws Exception {
		// Esegui il login utilizzando il servizio di autenticazione
		return authService.authenticate(loginRequest);
	}
	
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String testAdmin() {
        return "Accesso consentito solo agli utenti con ruolo ADMIN";
    }
    
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String testUser() {
        return "Accesso consentito solo agli utenti con ruolo USER";
    }
}
