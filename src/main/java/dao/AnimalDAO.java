package dao;

import conexao.Conexao;
import java.sql.*;
import java.util.*;
import modelo.*;

public class AnimalDAO {
    public void inserir(Animal a)throws SQLException{String sql="INSERT INTO animais(nome,tipo,raca,idade,tutor_id) VALUES(?,?,?,?,?)";try(Connection c=Conexao.conectar();PreparedStatement p=c.prepareStatement(sql)){p.setString(1,a.getNome());p.setString(2,a.getTipo());p.setString(3,a.getRaca());p.setInt(4,a.getIdade());p.setInt(5,a.getTutor().getId());p.executeUpdate();}}
    public Animal buscarPorNome(String nome)throws SQLException{String sql="SELECT a.*,t.nome tn,t.cpf,t.telefone,t.endereco FROM animais a JOIN tutores t ON t.id=a.tutor_id WHERE LOWER(a.nome)=LOWER(?) LIMIT 1";try(Connection c=Conexao.conectar();PreparedStatement p=c.prepareStatement(sql)){p.setString(1,nome);try(ResultSet r=p.executeQuery()){return r.next()?map(r):null;}}}
    public List<Animal> buscar(String termo)throws SQLException{List<Animal> l=new ArrayList<>();String sql="SELECT a.*,t.nome tn,t.cpf,t.telefone,t.endereco FROM animais a JOIN tutores t ON t.id=a.tutor_id WHERE a.nome LIKE ? OR t.nome LIKE ? ORDER BY a.nome";try(Connection c=Conexao.conectar();PreparedStatement p=c.prepareStatement(sql)){String q="%"+termo+"%";p.setString(1,q);p.setString(2,q);try(ResultSet r=p.executeQuery()){while(r.next())l.add(map(r));}}return l;}
    public int contar()throws SQLException{try(Connection c=Conexao.conectar();Statement s=c.createStatement();ResultSet r=s.executeQuery("SELECT COUNT(*) FROM animais")){r.next();return r.getInt(1);}}
    private Animal map(ResultSet r)throws SQLException{Tutor t=new Tutor(r.getInt("tutor_id"),r.getString("tn"),r.getString("cpf"),r.getString("telefone"),r.getString("endereco"));return new Animal(r.getInt("id"),r.getString("nome"),r.getString("tipo"),r.getString("raca"),r.getInt("idade"),t);}
}
