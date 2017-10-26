package br.com.mastermenu.passwordSecurity.service;

import java.util.Optional;
import br.com.mastermenu.passwordSecurity.dao.IPasswordSecurityDAO;
import br.com.mastermenu.passwordSecurity.dao.PasswordSecurityDAO;
import br.com.mastermenu.passwordSecurity.model.PasswordSecurity;

public class PasswordSecurityService implements IPasswordSecurityService{
	private IPasswordSecurityDAO psDAO = new PasswordSecurityDAO();
	
	@Override
	public void create(PasswordSecurity ps) {
		psDAO.create(ps);
	}

	@Override
	public void update(PasswordSecurity ps) {
		psDAO.update(ps);
	}

	@Override
	public boolean delete(PasswordSecurity ps) {
		return psDAO.delete(ps);
	}

	@Override
	public Optional<PasswordSecurity> readByIdUser(int id) {
		return psDAO.readByIdUser(id);
	}
	
}
