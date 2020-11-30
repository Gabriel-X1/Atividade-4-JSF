package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.EmpresasGabriel;
import br.com.faculdadedelta.util.Conexao;

public class EmpresasdaoGabriel {

	public void incluir (EmpresasGabriel empresas) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql ="INSERT INTO empresas(\r\n"
				+ " nome_fantasia_empresa, cnpj_empresa, endereco_empresa, data_cadastro_empresa)\r\n"
				+ "	VALUES (?, ?, ?, ?);";
		  PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, empresas.getNome());
		ps.setString(2, empresas.getCnpj());
		ps.setString(3, empresas.getEndereco());
		ps.setDate(4, new java.sql.Date(empresas.getDatacadastro().getTime()));
		  ps.executeUpdate();
		  ps.close();
		  conn.close();

}
	public void alterar (EmpresasGabriel empresas) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql ="UPDATE empresas\r\n"
				+ "	SET nome_fantasia_empresa=?, cnpj_empresa=?, endereco_empresa=?, data_cadastro_empresa=?\r\n"
				+ "	WHERE id_empresa=?";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setString(1, empresas.getNome());
			ps.setString(2, empresas.getCnpj());
			ps.setString(3, empresas.getEndereco());
			ps.setDate(4, new java.sql.Date(empresas.getDatacadastro().getTime()));
			ps.setLong(5, empresas.getId());
		  ps.executeUpdate();
		  ps.close();
		  conn.close();

}
	public void exluir (EmpresasGabriel empresas) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql ="DELETE FROM vendas WHERE id_empresa=? ";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setLong(1, empresas.getId());
		 ps.executeUpdate();
		 ps.close();
		 conn.close();
		 
	}
	public List<EmpresasGabriel> listar() throws ClassNotFoundException, SQLException{
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql ="SELECT id_empresa, nome_fantasia_empresa, cnpj_empresa, endereco_empresa, data_cadastro_empresa\r\n"
				+ "	FROM empresas;";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ResultSet rs = ps.executeQuery();
		  List<EmpresasGabriel> listaRetorno = new ArrayList<EmpresasGabriel>();
          while(rs.next()) {
        	  EmpresasGabriel empresas = new EmpresasGabriel();
        	  empresas.setId(rs.getLong("id_empresa"));
        	  empresas.setNome(rs.getString("nome_fantasia_empresa"));
        	  empresas.setCnpj(rs.getString("cnpj_empresa"));
        	  empresas.setEndereco(rs.getString("endereco_empresa"));
        	  empresas.setDatacadastro(rs.getDate("data_cadastro_empresa"));
        	  
        	  listaRetorno.add(empresas);
        	  
          }
          
          rs.close();
          ps.close();
		  conn.close();
		return listaRetorno;
	}
}
