package br.com.mastermenu.product.model;

public enum ProductEnum {
	COMIDA(1), BEBIDA(2);
	
	private final int valor;
	
	ProductEnum(int valorOpcao) {
		valor = valorOpcao;
	}
	
	public int getValor() {
		return valor;
	}
}
