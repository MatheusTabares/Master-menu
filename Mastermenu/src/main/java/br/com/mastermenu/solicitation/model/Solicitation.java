package br.com.mastermenu.solicitation.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.com.mastermenu.model.House;
import br.com.mastermenu.product.model.Product;


@Entity(name = "solicitation")
public class Solicitation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Product product;
	
	private String selectedCompositions;
	
	private int quantity = 1;
	
	private int idClient;
	
	private String typeCategory;
	
	private String status;
	@ManyToOne
	@JoinColumn(name="house_id")
	private House house;
	
	private LocalDateTime currentDate;
	
	private LocalDateTime estimatedDate;
	
	private LocalDateTime finalized;
	
	private long productionTime;
	
	private boolean visibility = true;
	
	public boolean isVisibility() {
		return visibility;
	}
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}
	public long getProductionTime() {
		return productionTime;
	}
	public void setProductionTime(long productionTime) {
		this.productionTime = productionTime;
	}
	public LocalDateTime getFinalized() {
		return finalized;
	}
	public void setFinalized(LocalDateTime finalized) {
		this.finalized = finalized;
	}
	public String getSelectedCompositions() {
		return selectedCompositions;
	}
	public void setSelectedCompositions(String selectedCompositions) {
		this.selectedCompositions = selectedCompositions;
	}
	public LocalDateTime getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(LocalDateTime currentDate) {
		this.currentDate = currentDate;
	}
	public LocalDateTime getEstimatedDate() {
		return estimatedDate;
	}
	public void setEstimatedDate(LocalDateTime estimatedDate) {
		this.estimatedDate = estimatedDate;
	}
	public String getTypeCategory() {
		return typeCategory;
	}
	public void setTypeCategory(String typeCategory) {
		this.typeCategory = typeCategory;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
