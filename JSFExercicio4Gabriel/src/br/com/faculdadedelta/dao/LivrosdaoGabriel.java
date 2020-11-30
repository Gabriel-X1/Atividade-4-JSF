package br.com.faculdadedelta.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.LivrosGabriel;
import br.com.faculdadedelta.util.Conexao;

public class LivrosdaoGabriel {
	
	public void incluir (LivrosGabriel livros) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql ="INSERT INTO public.livros(\r\n"
				+ "nome_livro, desc_editora, valor_livro, data_cadastro_livro)\r\n"
				+ "	VALUES (?, ?, ?, ?)";
		  PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, livros.getNome());
		ps.setString(2, livros.getEditora());
		ps.setDouble(3, livros.getValor());
		ps.setDate(4, new java.sql.Date(livros.getDatacadastro().getTime()));
		
		  ps.executeUpdate();
		  ps.close();
		  conn.close();

}
	public void alterar (LivrosGabriel livros) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql ="UPDATE livros\r\n"
				+ "	SET nome_livro=?, desc_editora=?, valor_livro=?, data_cadastro_livro=?\r\n"
				+ "	WHERE id_livro=?";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setString(1, livros.getNome());
			ps.setString(2, livros.getEditora());
			ps.setDouble(3, livros.getValor());
			ps.setDate(4, new java.sql.Date(livros.getDatacadastro().getTime()));
			ps.setLong(5, livros.getId());
			
			
		  ps.executeUpdate();
		  ps.close();
		  conn.close();
	
	
}
	public void exluir (LivrosGabriel livros) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql ="DELETE FROM vendas WHERE id_livro=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setLong(1, livros.getId());
		 
		 ps.executeUpdate();
		 ps.close();
		 conn.close();
		 
	}
	public List<LivrosGabriel> listar() throws ClassNotFoundException, SQLException{
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql ="SELECT id_livro, nome_livro, desc_editora, valor_livro, data_cadastro_livro\r\n"
				+ "	FROM livros";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ResultSet rs = ps.executeQuery();
		  List<LivrosGabriel> listaRetorno = new ArrayList<LivrosGabriel>();
          while(rs.next()) {
        	  LivrosGabriel livros = new LivrosGabriel();
        	  livros.setId(rs.getLong("id_livro"));
        	  livros.setNome(rs.getString("nome_livro"));
        	  livros.setEditora(rs.getString("desc_editora"));
        	  livros.setValor(rs.getDouble("valor_livro"));
        	  livros.setDatacadastro(rs.getDate("data_cadastro_livro"));
        	  
        	  listaRetorno.add(livros);
        	  
          }
          
          rs.close();
          ps.close();
		  conn.close();
		return listaRetorno;
	}
	
}
