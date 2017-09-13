package br.com.mastermenu.product.model;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import br.com.mastermenu.composition.model.Composition;

@Entity(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	private String description;
	
	@Column(nullable = false)
	private double price = 0.0;
	
	@ManyToMany
    @JoinTable(name="product_has_composition")
	private List<Composition> compositions;
	
	@ManyToMany
    @JoinTable(name="product_has_optionsComposition")
	private List<Composition> optionsComposition;
	
	public Product() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public List<Composition> getCompositions() {
		return compositions;
	}


	public void setCompositions(List<Composition> compositions) {
		this.compositions = compositions;
	}

	public List<Composition> getOptionsComposition() {
		return optionsComposition;
	}


	public void setOptionsComposition(List<Composition> optionsComposition) {
		this.optionsComposition = optionsComposition;
	}
	
		
}
