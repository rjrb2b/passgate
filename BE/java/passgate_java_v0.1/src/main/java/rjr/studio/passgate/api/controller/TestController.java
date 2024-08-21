package rjr.studio.passgate.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	

	@GetMapping("/check/all")
	public String testAuthAll() {
		return "Applicazione online per tutti";
	}
	
	@GetMapping("/check/login")
	public String testAuthLogin() {
		return "Applicazione online per tutti gli utenti loggati";
	}
	
	@GetMapping("/check/roles")
	public String testAuthRoles() {
		try {
		return "Applicazione online per i ruoli SYSTEM e ADMIN";
		}
		catch (Exception e) {
			System.out.println("***************** ERRORE DA MODIFICARE *****************");
			System.out.println(e.getMessage());
			return null;
		}
	}

}
