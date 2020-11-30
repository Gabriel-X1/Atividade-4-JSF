package br.com.faculdadedelta.contoller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.EmpresasdaoGabriel;
import br.com.faculdadedelta.modelo.EmpresasGabriel;

import javax.faces.application.FacesMessage;

@SessionScoped
@ManagedBean
public class EmpresasControllerGabriel {
	private EmpresasGabriel empresas = new EmpresasGabriel();
	private EmpresasdaoGabriel dao = new EmpresasdaoGabriel();

private Date dataatual;


public Date getDataatual() {
	return dataatual;
}
public void setDataatual(Date dataatual) {
	this.dataatual = dataatual;
}
public EmpresasGabriel getEmpresas() {
	return empresas;
}
public void setEmpresas(EmpresasGabriel empresas) {
	this.empresas = empresas;
}

public void limparCampos() {
	empresas = new EmpresasGabriel();
}
public String salvar() {
	try {
	if(empresas.getId() == null) {
			dao.incluir(empresas);
			FacesMessage mensagem = new FacesMessage("Inclusao realizada com sucesso!!");	
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            limparCampos();	
	}else {
		dao.alterar(empresas);
		FacesMessage mensagem = new FacesMessage("Alteracao realizada com sucesso!!");	
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
        limparCampos();
	}
} catch (ClassNotFoundException | SQLException e) {
	FacesMessage mensagem = new FacesMessage("Erro a realizar operaçao"
			+ " tente novamente mais tarde" + e.getMessage());	
    FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
} return "CadastroEmpresas.xhtml";
}
public List<EmpresasGabriel> getlista(){
	List<EmpresasGabriel> listaRetorno = new ArrayList<EmpresasGabriel>();
	
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
	return "CadastroEmpresas.xhtml";
}
public String Excluir() {
	try {
		dao.exluir(empresas);
		FacesMessage mensagem = new FacesMessage("exclusao realizada com sucesso!!");	
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
        limparCampos();
	} catch (ClassNotFoundException | SQLException e) {
		FacesMessage mensagem = new FacesMessage("Erro a realizar operaçao"
				+ " tente novamente mais tarde" + e.getMessage());	
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
		e.printStackTrace();
	} return null;
}
}
