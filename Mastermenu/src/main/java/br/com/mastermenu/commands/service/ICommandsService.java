package br.com.mastermenu.commands.service;

import java.util.List;
import java.util.Optional;
import br.com.mastermenu.commands.model.Commands;

public interface ICommandsService {
	public void create(Commands c);
	
	public List<Commands> read(int idHouse);
	
	public void update(Commands c);
	
	public boolean delete(Commands c);
	
	public Optional<Commands> readById(int id);
	
}
