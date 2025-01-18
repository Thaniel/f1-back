package com.f1.Formula1.service;

import org.springframework.stereotype.Service;

import com.f1.Formula1.model.User;
import com.f1.Formula1.repository.IUserRepository;

@Service // Spring service
public class UserService extends AbstractCRUDService<User, IUserRepository> {

	public UserService(IUserRepository repository) {
		super(repository);
	}

	@Override
	protected Long getEntityId(User user) {
		return user.getId();
	}

	// TODO
	/*
	 * public UserDetailsService userDetailsService() { return new
	 * UserDetailsService() {
	 * 
	 * @Override public UserDetails loadUserByUsername(String mail) { User user =
	 * repository.findByMail(mail) .orElseThrow(() -> new
	 * UsernameNotFoundException("User not found")); UserLogin userDTO = new
	 * UserLogin(user); return userDTO; } }; }
	 */
}
