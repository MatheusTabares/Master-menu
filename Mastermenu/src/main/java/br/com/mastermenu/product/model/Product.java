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
	private Optional<String> description;
	
	@Column(nullable = false)
	private double price = 0.0;
	
	@OneToMany(mappedBy = "product", targetEntity = Composition.class,
			fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Composition> compositions;
	private int quantity;
	private Date date;
	
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


	public Optional<String> getDescription() {
		return description;
	}


	public void setDescription(Optional<String> description) {
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


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public List<Composition> getOptionsComposition() {
		return optionsComposition;
	}


	public void setOptionsComposition(List<Composition> optionsComposition) {
		this.optionsComposition = optionsComposition;
	}
	
		
}
