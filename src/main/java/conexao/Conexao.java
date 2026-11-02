package conexao;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public final class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/clinica_veterinaria?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Sao_Paulo";
    private static String usuario;
    private static String senha;

    private Conexao() {}

    private static synchronized void solicitarCredenciais() throws SQLException {
        if (usuario != null) return;

        JTextField campoUsuario = new JTextField("root", 18);
        JPasswordField campoSenha = new JPasswordField(18);
        JPanel painel = new JPanel(new GridLayout(0, 1, 4, 4));
        painel.add(new JLabel("Usuário MySQL:"));
        painel.add(campoUsuario);
        painel.add(new JLabel("Senha MySQL:"));
        painel.add(campoSenha);

        int opcao = JOptionPane.showConfirmDialog(
                null, painel, "Conexão com MySQL",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (opcao != JOptionPane.OK_OPTION) {
            throw new SQLException("Conexão cancelada pelo usuário.");
        }

        usuario = campoUsuario.getText().trim();
        senha = new String(campoSenha.getPassword());
        if (usuario.isBlank()) usuario = "root";
    }

    public static Connection conectar() throws SQLException {
        solicitarCredenciais();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Connector/J não foi carregado pelo Maven.", e);
        }
        try {
            return DriverManager.getConnection(URL, usuario, senha);
        } catch (SQLException e) {
            usuario = null;
            senha = null;
            throw e;
        }
    }

    public static boolean testar() {
        try (Connection c = conectar()) {
            return c != null && !c.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
