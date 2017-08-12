package br.com.mastermenu.product.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.mastermenu.composition.model.Composition;
import br.com.mastermenu.product.model.Product;
import br.com.mastermenu.product.model.ProductEnum;
import br.com.mastermenu.solicitation.model.Solicitation;

public class ProductDAOInMemory implements IProductDAO {
	private Product p1;
	private Product p2;
	private Product p3;
	private Product p4;
	private Composition c1;
	private Composition c2;
	private Composition c3;
	private Composition c4;
	private Composition c5;
	private Composition c6;
	private List<Composition> listCompositionP1;
	private List<Composition> listCompositionP2;
	private List<Composition> listCompositionP3;
	private List<Composition> listCompositionP4;
	private List<Product> listProduct;
	
	public ProductDAOInMemory() {
		this.c1 = new Composition(1, "Agua");
		this.c2 = new Composition(2, "Laranja");
		this.c3 = new Composition(3, "Uva");
		this.c4 = new Composition(4, "Arroz");
		this.c5 = new Composition(5, "Feijao");
		this.c6 = new Composition(6, "Bife de frango");
		
		this.listCompositionP1 = new ArrayList<Composition>();
		this.listCompositionP2 = new ArrayList<Composition>();
		this.listCompositionP3 = new ArrayList<Composition>();
		this.listCompositionP4 = new ArrayList<Composition>();
		
		this.listCompositionP1.add(c1);
		this.listCompositionP2.add(c1);
		this.listCompositionP2.add(c2);
		this.listCompositionP3.add(c1);
		this.listCompositionP3.add(c3);
		this.listCompositionP4.add(c4);
		this.listCompositionP4.add(c5);
		this.listCompositionP4.add(c6);
		
		this.p1 = new Product(1, ProductEnum.BEBIDA.toString(), "AGUA MINERAL", Optional.of("com gás"), listCompositionP1);
		this.p2 = new Product(2, ProductEnum.BEBIDA.toString(), "SUCO DE LARANJA", Optional.empty(), listCompositionP2);
		this.p3 = new Product(3, ProductEnum.BEBIDA.toString(), "SUCO DE UVA", Optional.empty(), listCompositionP3);
		this.p4 = new Product(4, ProductEnum.COMIDA.toString(), "ALAMINUTA", Optional.of("Deliciosa, feita com os melhores ingredientes e com um carinho de quem sabe."), listCompositionP4);
				
		this.listProduct = new ArrayList<Product>();
		this.listProduct.add(p1);
		this.listProduct.add(p2);
		this.listProduct.add(p3);
		this.listProduct.add(p4);
	}
	
	@Override
	public Product create(Product product) {
		listProduct.add(product);
		return product;
	}

	@Override
	public List<Product> read(Optional<String> filter) {
		return listProduct;
	}

	@Override
	public Product update(Product productUpdated) {
		//listProduct.set(productUpdated);
		//return listProduct.get(indexProduct);
		return null;
	}

	@Override
	public boolean delete(Product product) {
		int id = 1;
		listProduct.remove(id);
		return true;
	}

	@Override
	public Optional<Product> readById(int id) {
		for(Product product : listProduct) {
			if(product.getId().equals(id)) {
				return Optional.of(product);
			}
		}
		return Optional.empty();
	}

}
