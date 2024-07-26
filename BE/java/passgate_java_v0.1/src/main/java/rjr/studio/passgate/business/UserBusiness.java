package rjr.studio.passgate.business;

import java.util.List;

import rjr.studio.passgate.api.view.model.User;

public interface UserBusiness {

	List<User> findAll() throws Exception;
	
	User findById(Integer id) throws Exception;
	
	User findByUsername(String username) throws Exception;
	
	User save(User user) throws Exception;
	
	User put(Integer id, User user) throws Exception;
	
	Boolean deleteById(Integer id) throws Exception;
	
	Boolean deleteByUsername(String username) throws Exception;

}
