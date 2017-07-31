package br.com.mastermenu.solicitation.model;

import java.util.Optional;

public class Solicitation {
	private Integer id;
	private String title;
	private Optional<String> description;
	
	public Solicitation(Integer id, String title, Optional<String> description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Optional<String> getDescription() {
		return description;
	}
	public void setDescription(Optional<String> description) {
		this.description = description;
	}
	
}
