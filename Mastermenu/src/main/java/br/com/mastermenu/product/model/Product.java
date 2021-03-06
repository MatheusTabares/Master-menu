package br.com.mastermenu.product.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import br.com.mastermenu.categoria.model.Categoria;
import br.com.mastermenu.composition.model.Composition;
import br.com.mastermenu.model.House;

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
	
	@ManyToOne
    @JoinColumn(name="categoria_id")
    private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="house_id")
	private House house;
	
	private Long happyInit;
	
	private Long happyEnd;
	
	private Long happyWeek;
	
	private double priceHappy;
	
	private boolean menu = true;
	
	public boolean isMenu() {
		return menu;
	}
	
	
	public double getPriceHappy() {
		return priceHappy;
	}


	public void setPriceHappy(double priceHappy) {
		this.priceHappy = priceHappy;
	}


	public void setMenu(boolean menu) {
		this.menu = menu;
	}
	
	public Long getHappyInit() {
		return happyInit;
	}

	public void setHappyInit(Long happyInit) {
		this.happyInit = happyInit;
	}

	public Long getHappyEnd() {
		return happyEnd;
	}

	public void setHappyEnd(Long happyEnd) {
		this.happyEnd = happyEnd;
	}

	public Long getHappyWeek() {
		return happyWeek;
	}

	public void setHappyWeek(Long happyWeek) {
		this.happyWeek = happyWeek;
	}

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
	
	


	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
