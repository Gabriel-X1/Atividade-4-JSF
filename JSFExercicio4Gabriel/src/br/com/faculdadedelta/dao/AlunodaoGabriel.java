package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.AlunoGabriel;
import br.com.faculdadedelta.util.Conexao;

public class AlunodaoGabriel {
	
	public void incluir (AlunoGabriel aluno) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql ="INSERT INTO alunos(\r\n"
				+ "	 nome_aluno, idade_aluno, grau_instrucao_aluno, data_cadastro_aluno)\r\n"
				+ "	VALUES (?, ?, ?, ?)";
		  PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, aluno.getNome());
		 ps.setInt(2, aluno.getIdade());
		 ps.setString(3, aluno.getGrau());
		 ps.setDate(4, new java.sql.Date(aluno.getDatacadastro().getTime()));
		  ps.executeUpdate();
		  ps.close();
		  conn.close();

}
	public void alterar (AlunoGabriel aluno) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql ="UPDATE alunos\r\n"
				+ "	SET nome_aluno=?, idade_aluno=?, grau_instrucao_aluno=?, data_cadastro_aluno=?\r\n"
				+ "	WHERE id_aluno=?;";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setString(1, aluno.getNome());
			 ps.setInt(2, aluno.getIdade());
			 ps.setString(3, aluno.getGrau());
			 ps.setDate(4, new java.sql.Date(aluno.getDatacadastro().getTime()));
			 ps.setLong(5, aluno.getId());
		  ps.executeUpdate();
		  ps.close();
		  conn.close();
	}
	public void exluir (AlunoGabriel aluno) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql ="DELETE FROM vendas WHERE id_aluno=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setLong(1, aluno.getId());
		 ps.executeUpdate();
		 ps.close();
		 conn.close();
		 
	}
	
	public List<AlunoGabriel> listar() throws ClassNotFoundException, SQLException{
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql ="SELECT id_aluno, nome_aluno, idade_aluno, grau_instrucao_aluno, data_cadastro_aluno\r\n"
				+ "	FROM alunos";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ResultSet rs = ps.executeQuery();
		  List<AlunoGabriel> listaRetorno = new ArrayList<AlunoGabriel>();
          while(rs.next()) {
        	  AlunoGabriel aluno = new AlunoGabriel();
        	  aluno.setId(rs.getLong("id_aluno"));
        	  aluno.setNome(rs.getString("nome_aluno"));
        	  aluno.setIdade(rs.getInt("idade_aluno"));
        	  aluno.setGrau(rs.getString("grau_instrucao_aluno"));
        	  aluno.setDatacadastro(rs.getDate("data_cadastro_aluno"));
        	  listaRetorno.add(aluno);
        	  
          }
          
          rs.close();
          ps.close();
		  conn.close();
		return listaRetorno;
	}


}
