package br.com.faculdadedelta.contoller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.AlunodaoGabriel;
import br.com.faculdadedelta.modelo.AlunoGabriel;

@SessionScoped
@ManagedBean
public class AlunoControllerGabriel {
	private AlunoGabriel aluno = new AlunoGabriel();
	private AlunodaoGabriel dao = new AlunodaoGabriel();
public AlunoGabriel getAluno() {
	return aluno;
}
public void setAluno(AlunoGabriel aluno) {
	this.aluno = aluno;
}
 
 public void limparCampos() {
	 aluno = new AlunoGabriel();
 }
 public String salvar() {
	 try {
	 if(aluno.getId() == null) {
		 
			dao.incluir(aluno);
			FacesMessage mensagem = new FacesMessage("Inclusao realizada com sucesso!!");	
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            limparCampos();	
		
	 }else {
		 dao.alterar(aluno);
		 FacesMessage mensagem = new FacesMessage("Alteracao realizada com sucesso!!");	
	        FacesContext.getCurrentInstance().addMessage(null, mensagem);
	        limparCampos();
	 }
	 } catch (ClassNotFoundException | SQLException e) {
		 FacesMessage mensagem = new FacesMessage("Erro a realizar operaçao"
					+ " tente novamente mais tarde" + e.getMessage());	
		    FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
 }return "CadastroAluno.xhtml";
}
 public List<AlunoGabriel> getlistar(){
	 List<AlunoGabriel> listaRetorno = new ArrayList<AlunoGabriel>();
	 
	 try {
		listaRetorno = dao.listar();
	} catch (ClassNotFoundException | SQLException e) {
		FacesMessage mensagem = new FacesMessage("Erro a realizar operaçao"
				+ " tente novamente mais tarde" + e.getMessage());	
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
		e.printStackTrace();
	} return listaRetorno;
 }
 public String Editar() {
	 return "CadastroAluno.xhtml"; 
 }
 public String Excluir() {
	 try {
		dao.exluir(aluno);
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
