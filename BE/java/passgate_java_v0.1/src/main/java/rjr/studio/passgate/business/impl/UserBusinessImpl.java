package rjr.studio.passgate.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rjr.studio.passgate.api.view.model.User;
import rjr.studio.passgate.business.UserBusiness;
import rjr.studio.passgate.conf.mapping.Entity2Model;
import rjr.studio.passgate.dao.entity.UserEntity;
import rjr.studio.passgate.dao.service.UserService;

@Component
public class UserBusinessImpl implements UserBusiness {

	private Entity2Model entity2Model;
	private UserService userService;

	@Autowired
	public UserBusinessImpl(Entity2Model entity2Model, UserService userService) {
		this.entity2Model = entity2Model;
		this.userService = userService;
	}

	@Override
	public List<User> findAll() throws Exception {
		return entity2Model.mapperList(userService.findAll(), User.class);
	}

	@Override
	public User findById(Integer id) throws Exception {
		return entity2Model.mapper(userService.findById(id), User.class);
	}

	@Override
	public User findByUsername(String username) throws Exception {
		return entity2Model.mapper(userService.findByUsername(username), User.class);
	}

	@Override
	public User save(User user) throws Exception {
		UserEntity userEntity = entity2Model.mapper(user, UserEntity.class);
		return entity2Model.mapper(userService.save(userEntity), User.class);
	}

	@Override
	public User put(Integer id, User user) throws Exception {
		UserEntity userEntity = entity2Model.mapper(user, UserEntity.class);
		return entity2Model.mapper(userService.put(id, userEntity), User.class);
	}

	@Override
	public Boolean deleteById(Integer id) throws Exception {
		userService.deleteById(id);
		return true;
	}

	@Override
	public Boolean deleteByUsername(String username) throws Exception {
		userService.deleteByUsername(username);
		return true;
	}

}
