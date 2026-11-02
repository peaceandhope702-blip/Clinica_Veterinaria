package view;

import dao.UsuarioDAO;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import modelo.Usuario;

public class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("Clínica Veterinária - Login"); setSize(560,430); setLocationRelativeTo(null); setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel root=new JPanel(new GridBagLayout()); root.setBackground(new Color(246,248,251));
        JPanel card=new JPanel(); card.setPreferredSize(new Dimension(390,310)); card.setBorder(BorderFactory.createEmptyBorder(28,35,28,35)); card.setLayout(new BoxLayout(card,BoxLayout.Y_AXIS));
        JLabel title=new JLabel("Clínica Veterinária"); title.setFont(new Font("SansSerif",Font.BOLD,26)); title.setAlignmentX(Component.LEFT_ALIGNMENT); card.add(title); card.add(Box.createVerticalStrut(20));
        JTextField user=new JTextField("admin"); JPasswordField pass=new JPasswordField("admin123");
        card.add(new JLabel("Usuário")); card.add(user); card.add(Box.createVerticalStrut(12)); card.add(new JLabel("Senha")); card.add(pass); card.add(Box.createVerticalStrut(22));
        JButton entrar=new JButton("Entrar"); entrar.setUI(new BasicButtonUI()); entrar.setAlignmentX(Component.LEFT_ALIGNMENT); entrar.setFont(new Font("SansSerif",Font.BOLD,14)); entrar.setBackground(new Color(37,99,235)); entrar.setForeground(Color.WHITE); entrar.setOpaque(true); entrar.setContentAreaFilled(true); entrar.setBorderPainted(false); entrar.setFocusPainted(false); entrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); entrar.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        entrar.addActionListener(e->{if(user.getText().isBlank()||pass.getPassword().length==0){JOptionPane.showMessageDialog(this,"Informe usuário e senha.","Atenção",JOptionPane.WARNING_MESSAGE);return;}try{Usuario u=new UsuarioDAO().autenticar(user.getText().trim(),new String(pass.getPassword()));if(u==null){JOptionPane.showMessageDialog(this,"Usuário ou senha inválidos.","Acesso negado",JOptionPane.WARNING_MESSAGE);return;}dispose();new MainFrame().setVisible(true);}catch(SQLException ex){JOptionPane.showMessageDialog(this,"Não foi possível conectar ao MySQL. Importe database/clinica_veterinaria.sql e confira a configuração da conexão.\n"+ex.getMessage(),"Erro de banco de dados",JOptionPane.ERROR_MESSAGE);}}); card.add(entrar);
        JLabel hint=new JLabel("Teste inicial: admin / admin123"); hint.setForeground(Color.GRAY); hint.setAlignmentX(Component.LEFT_ALIGNMENT); card.add(Box.createVerticalStrut(12)); card.add(hint);
        root.add(card); setContentPane(root);
    }
}
