package dao;

import conexao.Conexao;
import java.sql.*;
import java.util.*;
import modelo.Tutor;

public class TutorDAO {
    public void inserir(Tutor t) throws SQLException {
        String sql="INSERT INTO tutores(nome,cpf,telefone,endereco) VALUES(?,?,?,?)";
        try(Connection c=Conexao.conectar(); PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,t.getNome()); p.setString(2,t.getCpf()); p.setString(3,t.getTelefone()); p.setString(4,t.getEndereco()); p.executeUpdate();
        }
    }
    public Tutor buscarPorNome(String nome) throws SQLException {
        String sql="SELECT * FROM tutores WHERE LOWER(nome)=LOWER(?) LIMIT 1";
        try(Connection c=Conexao.conectar(); PreparedStatement p=c.prepareStatement(sql)){p.setString(1,nome);try(ResultSet r=p.executeQuery()){return r.next()?map(r):null;}}
    }
    public List<Tutor> buscar(String termo) throws SQLException {
        List<Tutor> lista=new ArrayList<>(); String sql="SELECT * FROM tutores WHERE nome LIKE ? OR cpf LIKE ? ORDER BY nome";
        try(Connection c=Conexao.conectar();PreparedStatement p=c.prepareStatement(sql)){String q="%"+termo+"%";p.setString(1,q);p.setString(2,q);try(ResultSet r=p.executeQuery()){while(r.next())lista.add(map(r));}} return lista;
    }
    public int contar() throws SQLException {try(Connection c=Conexao.conectar();Statement s=c.createStatement();ResultSet r=s.executeQuery("SELECT COUNT(*) FROM tutores")){r.next();return r.getInt(1);}}
    private Tutor map(ResultSet r)throws SQLException{return new Tutor(r.getInt("id"),r.getString("nome"),r.getString("cpf"),r.getString("telefone"),r.getString("endereco"));}
}
