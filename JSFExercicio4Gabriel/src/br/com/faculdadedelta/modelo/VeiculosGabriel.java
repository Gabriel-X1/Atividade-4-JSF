package br.com.faculdadedelta.modelo;

import java.util.Date;

public class VeiculosGabriel {
private Long id;
private String nome;
private String marca;
private int ano ;
private String placa;
private Date datacadastro;


public VeiculosGabriel() {
}


public VeiculosGabriel(Long id, String nome, String marca, int ano, String placa, Date datacadastro) {
	this.id = id;
	this.nome = nome;
	this.marca = marca;
	this.ano = ano;
	this.placa = placa;
	this.datacadastro = datacadastro;
}


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public String getNome() {
	return nome;
}


public void setNome(String nome) {
	this.nome = nome;
}


public String getMarca() {
	return marca;
}


public void setMarca(String marca) {
	this.marca = marca;
}


public int getAno() {
	return ano;
}


public void setAno(int ano) {
	this.ano = ano;
}


public String getPlaca() {
	return placa;
}


public void setPlaca(String placa) {
	this.placa = placa;
}


public Date getDatacadastro() {
	return datacadastro;
}


public void setDatacadastro(Date datacadastro) {
	this.datacadastro = datacadastro;
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	VeiculosGabriel other = (VeiculosGabriel) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}



}
