package rjr.studio.passgate.business;

import rjr.studio.passgate.api.view.LoginRequest;

public interface LoginBusiness {

	String passwordMatch(LoginRequest loginrRequest);

	void passwordChange(LoginRequest loginrRequest);

}
