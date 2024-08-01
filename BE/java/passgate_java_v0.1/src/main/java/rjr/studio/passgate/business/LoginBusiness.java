package rjr.studio.passgate.business;

import rjr.studio.passgate.api.view.model.LoginRequest;

public interface LoginBusiness {

	String passwordMatch(LoginRequest loginrRequest);

	void passwordChange(LoginRequest loginrRequest);

}
