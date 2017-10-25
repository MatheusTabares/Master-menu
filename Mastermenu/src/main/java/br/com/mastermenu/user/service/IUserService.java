package br.com.mastermenu.user.service;

import java.util.List;
import java.util.Optional;

import br.com.mastermenu.user.model.User;

public interface IUserService {
	public void create(User u);
	
	public void update(User u);
	
	public boolean delete(User u);
	
	public Optional<User> findById(int id);
	
	public User findByEmail(String email);
	
	public List<User> findByIdHouse(int idHouse);
}
