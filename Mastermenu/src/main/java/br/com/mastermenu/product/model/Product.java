package br.com.mastermenu.product.model;

import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.mastermenu.composition.model.Composition;

public class Product {
	
	private Integer id;
	private String category;
	private String title;
	private Optional<String> description;
	private List<Composition> listComposition;
	
	public Product() {}
	
	public Product(int id, String category, String title, Optional<String> description, List<Composition> listComposition) {
		this.id = id;
		this.category = category;
		this.title = title;
		this.description = description;
		this.listComposition = listComposition;
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public List<Composition> getListComposition() {
		return listComposition;
	}
	public void setListComposition(List<Composition> listComposition) {
		this.listComposition = listComposition;
	}
	
	
}
