package br.com.faculdadedelta.contoller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.VendasdaoGabriel;
import br.com.faculdadedelta.modelo.VendasGabriel;

@SessionScoped
@ManagedBean
public class VendasControllerGabriel {

	private VendasGabriel vendas = new VendasGabriel();
	private VendasdaoGabriel dao = new VendasdaoGabriel();
	
	private Date atual;
	private Date maxima;
	
	
	public VendasGabriel getVendas() {
		return vendas;
	}
	public void setVendas(VendasGabriel vendas) {
		this.vendas = vendas;
	}
	public Date getAtual() {
		return atual;
	}
	public void setAtual(Date atual) {
		this.atual = atual;
	}
	public Date getMaxima() {
		return maxima;
	}
	public void setMaxima(Date maxima) {
		this.maxima = maxima;
	}
	public void limparCampos() {
		vendas = new VendasGabriel();
	}
	public String salvar() {
		try {
		if(vendas.getId() == null) {
			
				dao.incluir(vendas);
				 FacesMessage mensagem = new FacesMessage("Inclusao realizada com sucesso!!");	
	                FacesContext.getCurrentInstance().addMessage(null, mensagem);
	                limparCampos();
		}else {
			dao.alterar(vendas);
			FacesMessage mensagem = new FacesMessage("Alteracao realizada com sucesso!!");	
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            limparCampos();
		}
	    } catch (ClassNotFoundException | SQLException e) {
		FacesMessage mensagem = new FacesMessage("Erro a realizar operaçao"
				+ " tente novamente mais tarde" + e.getMessage());	
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
				e.printStackTrace();
		
	    }
	return "CadastroVendas.xhtml";
}
public List<VendasGabriel> getlista(){
	List<VendasGabriel> listaRetorno = new ArrayList<VendasGabriel>();
	try {
		listaRetorno = dao.listar();
	} catch (ClassNotFoundException | SQLException e) {
		FacesMessage mensagem = new FacesMessage("Erro a realizar operaçao"
				+ " tente novamente mais tarde" + e.getMessage());	
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
		e.printStackTrace();
	}
	return listaRetorno;
}
public String editar() {
	return "CadastroVendas.xhtml";
}
public String excluir() {
	try {
		dao.exluir(vendas);
		FacesMessage mensagem = new FacesMessage("exclusao realizada com sucesso!!");	
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
        limparCampos();
	} catch (ClassNotFoundException | SQLException e) {
		FacesMessage mensagem = new FacesMessage("Erro a realizar operaçao"
				+ " tente novamente mais tarde" + e.getMessage());	
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
		e.printStackTrace();
	}
	return "ListaVendas.xhtml";
}
}

