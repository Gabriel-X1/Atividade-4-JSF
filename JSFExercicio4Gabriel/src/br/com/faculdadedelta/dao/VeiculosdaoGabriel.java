package br.com.faculdadedelta.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.VeiculosGabriel;
import br.com.faculdadedelta.util.Conexao;

public class VeiculosdaoGabriel {
	
	public void incluir (VeiculosGabriel veiculos) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql ="INSERT INTO carros(\r\n"
				+ "	 desc_nome_carro, desc_marca_carro, ano_fabricacao_carro, desc_placa_carro, data_cadastro_carro)\r\n"
				+ "	VALUES (?, ?, ?, ?, ?)";
		  PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, veiculos.getNome());
		ps.setString(2, veiculos.getMarca());
		ps.setInt(3, veiculos.getAno());
		ps.setString(4, veiculos.getPlaca());
		ps.setDate(5,new java.sql.Date(veiculos.getDatacadastro().getTime()));
		  ps.executeUpdate();
		  ps.close();
		  conn.close();

}
	public void alterar (VeiculosGabriel veiculos) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql ="UPDATE carros\r\n"
				+ "	SET desc_nome_carro=?, desc_marca_carro=?, ano_fabricacao_carro=?, desc_placa_carro=?, data_cadastro_carro=?\r\n"
				+ "	WHERE id_carro=?";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setString(1, veiculos.getNome());
			ps.setString(2, veiculos.getMarca());
			ps.setInt(3, veiculos.getAno());
			ps.setString(4, veiculos.getPlaca());
			ps.setDate(5,new java.sql.Date(veiculos.getDatacadastro().getTime()));
			ps.setLong(6, veiculos.getId());
		  ps.executeUpdate();
		  ps.close();
		  conn.close();

}
	public void exluir (VeiculosGabriel veiculos) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql ="DELETE FROM vendas WHERE id_carro=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setLong(1, veiculos.getId());
		 ps.executeUpdate();
		 ps.close();
		 conn.close();
		 
	}
	public List<VeiculosGabriel> listar() throws ClassNotFoundException, SQLException{
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql ="SELECT id_carro, desc_nome_carro, desc_marca_carro, ano_fabricacao_carro, desc_placa_carro, data_cadastro_carro\r\n"
				+ "	FROM carros;";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ResultSet rs = ps.executeQuery();
		  List<VeiculosGabriel> listaRetorno = new ArrayList<VeiculosGabriel>();
          while(rs.next()) {
        	  VeiculosGabriel veiculos = new VeiculosGabriel();
        	  veiculos.setId(rs.getLong("id_carro"));
        	  veiculos.setNome(rs.getString("desc_nome_carro"));
        	  veiculos.setMarca(rs.getString("desc_marca_carro"));
        	  veiculos.setAno(rs.getInt("ano_fabricacao_carro"));
        	  veiculos.setPlaca(rs.getString("desc_placa_carro"));
        	  veiculos.setDatacadastro(rs.getDate("data_cadastro_carro"));
        	  
        	  
        	  listaRetorno.add(veiculos);
        	  
          }
          
          rs.close();
          ps.close();
		  conn.close();
		return listaRetorno;
	}
}
