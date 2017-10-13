package br.com.mastermenu.commands.service;

import java.util.List;
import java.util.Optional;

import br.com.mastermenu.commands.dao.CommandsDAO;
import br.com.mastermenu.commands.dao.ICommandsDAO;
import br.com.mastermenu.commands.model.Commands;

public class CommandsService implements ICommandsService{
	private ICommandsDAO commandsDAO = new CommandsDAO();
	
	@Override
	public void create(Commands c) {
		commandsDAO.create(c);
	}

	@Override
	public List<Commands> read(int idHouse) {
		return commandsDAO.read(idHouse);
	}

	@Override
	public void update(Commands c) {
		commandsDAO.update(c);
	}

	@Override
	public boolean delete(Commands c) {
		return commandsDAO.delete(c);
	}

	@Override
	public Optional<Commands> readById(int id) {
		return commandsDAO.readById(id);
	}

}
