package dao;
import conexao.Conexao;import java.sql.*;import modelo.Pagamento;
public class PagamentoDAO {
 public void inserir(Pagamento x)throws SQLException{String sql="INSERT INTO pagamentos(consulta_id,valor,efetuado,data_pagamento) VALUES(?,?,?,IF(?,NOW(),NULL))";try(Connection c=Conexao.conectar();PreparedStatement p=c.prepareStatement(sql)){p.setInt(1,x.getConsulta().getId());p.setBigDecimal(2,x.getValor());p.setBoolean(3,x.isEfetuado());p.setBoolean(4,x.isEfetuado());p.executeUpdate();}}
 public int contarPendentes()throws SQLException{try(Connection c=Conexao.conectar();Statement s=c.createStatement();ResultSet r=s.executeQuery("SELECT COUNT(*) FROM pagamentos WHERE efetuado=0")){r.next();return r.getInt(1);}}
}
