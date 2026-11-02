package dao;
import conexao.Conexao;import java.sql.*;import modelo.Veterinario;
public class VeterinarioDAO {
 public Veterinario buscarPorNome(String nome)throws SQLException{String sql="SELECT u.id,u.nome,u.login,u.senha,v.crmv FROM veterinarios v JOIN usuarios u ON u.id=v.usuario_id WHERE LOWER(u.nome)=LOWER(?) LIMIT 1";try(Connection c=Conexao.conectar();PreparedStatement p=c.prepareStatement(sql)){p.setString(1,nome);try(ResultSet r=p.executeQuery()){return r.next()?new Veterinario(r.getInt("id"),r.getString("nome"),r.getString("login"),r.getString("senha"),r.getString("crmv")):null;}}}
 public Veterinario primeiro()throws SQLException{String sql="SELECT u.id,u.nome,u.login,u.senha,v.crmv FROM veterinarios v JOIN usuarios u ON u.id=v.usuario_id ORDER BY u.id LIMIT 1";try(Connection c=Conexao.conectar();Statement s=c.createStatement();ResultSet r=s.executeQuery(sql)){return r.next()?new Veterinario(r.getInt("id"),r.getString("nome"),r.getString("login"),r.getString("senha"),r.getString("crmv")):null;}}
}
