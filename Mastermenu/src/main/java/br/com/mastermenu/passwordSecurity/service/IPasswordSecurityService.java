package br.com.mastermenu.passwordSecurity.service;

import java.util.Optional;
import br.com.mastermenu.passwordSecurity.model.PasswordSecurity;

public interface IPasswordSecurityService {
	public void create(PasswordSecurity ps);
	
	public void update(PasswordSecurity ps);
	
	public boolean delete(PasswordSecurity ps);
	
	public Optional<PasswordSecurity> readByIdUser(int id);
}
