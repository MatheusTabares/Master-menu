package br.com.mastermenu.commands.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import br.com.mastermenu.solicitation.model.Solicitation;

@Entity(name = "commands")
public class Commands {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private int idClient;
	
	private int idHouse;
	
	private int mesa;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="commands_has_solicitations")
	private List<Solicitation> solicitations;
	
	private float total;

	public int getIdHouse() {
		return idHouse;
	}

	public void setIdHouse(int idHouse) {
		this.idHouse = idHouse;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	
	public int getMesa() {
		return mesa;
	}

	public void setMesa(int mesa) {
		this.mesa = mesa;
	}
	
	public List<Solicitation> getSolicitations() {
		return solicitations;
	}

	public void setSolicitations(List<Solicitation> solicitations) {
		this.solicitations = solicitations;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
	
	
}
