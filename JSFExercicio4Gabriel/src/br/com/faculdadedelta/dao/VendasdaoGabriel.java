package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.VendasGabriel;
import br.com.faculdadedelta.util.Conexao;

public class VendasdaoGabriel {

	public void incluir (VendasGabriel vendas) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql ="INSERT INTO vendas(\r\n"
				+ "	desc_cliente, desc_produto, valor_produto, data_venda)\r\n"
				+ "	VALUES ( ?, ?, ?, ?)";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setString(1, vendas.getCliente());
		  ps.setString(2, vendas.getProduto());
		  ps.setDouble(3, vendas.getValor());
		  ps.setDate(4, new java.sql.Date(vendas.getData().getTime()));
		  
		  ps.executeUpdate();
		  ps.close();
		  conn.close();
	}
	public void alterar (VendasGabriel vendas) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql ="UPDATE vendas\r\n"
				+ "	SET desc_cliente=?, desc_produto=?, valor_produto=?, data_venda=?\r\n"
				+ "	WHERE id_venda=?;";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setString(1, vendas.getCliente());
		  ps.setString(2, vendas.getProduto());
		  ps.setDouble(3, vendas.getValor());
		  ps.setDate(4, new java.sql.Date(vendas.getData().getTime()));
		  ps.setLong(5, vendas.getId());
		
		  ps.executeUpdate();
		  ps.close();
		  conn.close();

}
	
	public void exluir (VendasGabriel vendas) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql ="DELETE FROM vendas WHERE id_venda=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setLong(1, vendas.getId());
		 ps.executeUpdate();
		  ps.close();
		  conn.close();
		 
	}
	public List<VendasGabriel> listar() throws ClassNotFoundException, SQLException{
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql ="SELECT id_venda, desc_cliente, desc_produto, valor_produto, data_venda\r\n"
				+ "	FROM vendas;";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ResultSet rs = ps.executeQuery();
		  List<VendasGabriel> listaRetorno = new ArrayList<VendasGabriel>();
          while(rs.next()) {
        	  VendasGabriel vendas = new VendasGabriel();
        	  vendas.setId(rs.getLong("id_venda"));
        	  vendas.setCliente(rs.getString("desc_cliente"));
        	  vendas.setProduto(rs.getString("desc_produto"));
        	  vendas.setValor(rs.getDouble("valor_produto"));
        	  vendas.setData(rs.getDate("data_venda"));
        	  listaRetorno.add(vendas);
        	  
          }
          
          rs.close();
          ps.close();
		  conn.close();
		return listaRetorno;
	}
	
}
