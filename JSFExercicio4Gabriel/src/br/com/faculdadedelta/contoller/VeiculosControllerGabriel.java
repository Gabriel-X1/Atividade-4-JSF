package br.com.faculdadedelta.contoller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.VeiculosdaoGabriel;
import br.com.faculdadedelta.modelo.VeiculosGabriel;

@SessionScoped
@ManagedBean
public class VeiculosControllerGabriel {
	private VeiculosGabriel veiculos = new VeiculosGabriel();
	private VeiculosdaoGabriel dao = new VeiculosdaoGabriel();
	
	public VeiculosGabriel getVeiculos() {
		return veiculos;
	}
	public void setVeiculos(VeiculosGabriel veiculos) {
		this.veiculos = veiculos;
	}
	public void limparCampos() {
		veiculos = new VeiculosGabriel() ;
	}
	public String Salvar() {
		try {
		if(veiculos.getId() == null) {
				dao.incluir(veiculos);
				FacesMessage mensagem = new FacesMessage("Inclusao realizada com sucesso!!");	
	            FacesContext.getCurrentInstance().addMessage(null, mensagem);
	            limparCampos();	
			
		}else {
			dao.alterar(veiculos);
			FacesMessage mensagem = new FacesMessage("Alteracao realizada com sucesso!!");	
	        FacesContext.getCurrentInstance().addMessage(null, mensagem);
	        limparCampos();
		}
		} catch (ClassNotFoundException | SQLException e) {
			FacesMessage mensagem = new FacesMessage("Erro a realizar operaçao"
					+ " tente novamente mais tarde" + e.getMessage());	
		    FacesContext.getCurrentInstance().addMessage(null, mensagem);
				e.printStackTrace();
			}return "CadastroVeiculos.xhtml";
	}
	public List<VeiculosGabriel>getlistar(){
		List<VeiculosGabriel> listaRetorno = new ArrayList<VeiculosGabriel>();
		
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			FacesMessage mensagem = new FacesMessage("Erro a realizar operaçao"
					+ " tente novamente mais tarde" + e.getMessage());	
	        FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
		}return listaRetorno;
	}
	public String Editar() {
		return "CadastroVeiculos.xhtml";
	}
 public String Excluir() {
	 try {
		dao.exluir(veiculos);
		FacesMessage mensagem = new FacesMessage("exclusao realizada com sucesso!!");	
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
        limparCampos();
	} catch (ClassNotFoundException | SQLException e) {
		FacesMessage mensagem = new FacesMessage("Erro a realizar operaçao"
				+ " tente novamente mais tarde" + e.getMessage());	
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
		e.printStackTrace();
	}return "ListaVeiculos.xhtml";
 }
}
