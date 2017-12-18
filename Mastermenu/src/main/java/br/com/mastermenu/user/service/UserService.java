package br.com.mastermenu.user.service;

import java.util.List;
import java.util.Optional;

import br.com.mastermenu.user.dao.IUserDAO;
import br.com.mastermenu.user.dao.UserDAO;
import br.com.mastermenu.user.model.User;

public class UserService implements IUserService {
	
	private IUserDAO userDAO = new UserDAO();
	
	@Override
	public User create(User u) {
		return userDAO.create(u);
	}

	@Override
	public void update(User u) {
		userDAO.update(u);
	}

	@Override
	public boolean delete(User u) {
		userDAO.update(u);
		return true;
	}

	@Override
	public Optional<User> findById(int id) {
		return userDAO.findById(id);
	}

	@Override
	public User findByEmail(String email) {
		return userDAO.findByEmail(email);
	}

	@Override
	public List<User> findByIdHouse(int idHouse) {
		return userDAO.findByIdHouse(idHouse);
	}

}
