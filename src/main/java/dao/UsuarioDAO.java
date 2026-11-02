package dao;
import conexao.Conexao;import java.sql.*;import modelo.Usuario;
public class UsuarioDAO {
 public Usuario autenticar(String login,String senha)throws SQLException{String sql="SELECT * FROM usuarios WHERE login=? AND senha=? AND ativo=1 LIMIT 1";try(Connection c=Conexao.conectar();PreparedStatement p=c.prepareStatement(sql)){p.setString(1,login);p.setString(2,senha);try(ResultSet r=p.executeQuery()){return r.next()?new Usuario(r.getInt("id"),r.getString("nome"),r.getString("login"),r.getString("senha"),r.getString("perfil")):null;}}}
}
