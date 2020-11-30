package br.com.faculdadedelta.contoller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.LivrosdaoGabriel;
import br.com.faculdadedelta.modelo.LivrosGabriel;

@SessionScoped
@ManagedBean
public class LivrosControllerGabriel {
	private LivrosGabriel livros = new LivrosGabriel();
	private LivrosdaoGabriel dao = new LivrosdaoGabriel();
	
	
	public LivrosGabriel getLivros() {
		return livros;
	}
	public void setLivros(LivrosGabriel livros) {
		this.livros = livros;
	}
	
	public void limparCampos() {
		livros = new LivrosGabriel();
	}
	public String Salvar() {
		try {
		if(livros.getId() == null) {
				dao.incluir(livros);
				FacesMessage mensagem = new FacesMessage("Inclusao realizada com sucesso!!");	
	            FacesContext.getCurrentInstance().addMessage(null, mensagem);
	            limparCampos();	
		}else {
			dao.alterar(livros);
			FacesMessage mensagem = new FacesMessage("Alteracao realizada com sucesso!!");	
	        FacesContext.getCurrentInstance().addMessage(null, mensagem);
	        limparCampos();
		}
		} catch (ClassNotFoundException | SQLException e) {
			FacesMessage mensagem = new FacesMessage("Erro a realizar operaçao"
					+ " tente novamente mais tarde" + e.getMessage());	
		    FacesContext.getCurrentInstance().addMessage(null, mensagem);
				e.printStackTrace();
	} return null;
}
public List<LivrosGabriel> getlista(){
	List<LivrosGabriel> listaRetorno = new ArrayList<LivrosGabriel>();
	
	try {
		listaRetorno = dao.listar();
		
	} catch (ClassNotFoundException | SQLException e) {
		FacesMessage mensagem = new FacesMessage("Erro a realizar operaçao"
				+ " tente novamente mais tarde" + e.getMessage());	
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
		e.printStackTrace();
	} return listaRetorno;
}
public String editar() {
	return null;
}
public String Excluir() {
	try {
		dao.exluir(livros);
		FacesMessage mensagem = new FacesMessage("exclusao realizada com sucesso!!");	
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
        limparCampos();
	} catch (ClassNotFoundException | SQLException e) {
		FacesMessage mensagem = new FacesMessage("Erro a realizar operaçao"
				+ " tente novamente mais tarde" + e.getMessage());	
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
		e.printStackTrace();
	} return "ListaLivros.xhtml";
}
}
