package view;

import dao.*;
import java.sql.SQLException;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.plaf.basic.BasicButtonUI;
import modelo.*;

public class MainFrame extends JFrame {
    private final CardLayout cards=new CardLayout(); private final JPanel content=new JPanel(cards);
    private final TutorDAO tutorDAO=new TutorDAO(); private final AnimalDAO animalDAO=new AnimalDAO(); private final VeterinarioDAO veterinarioDAO=new VeterinarioDAO(); private final ConsultaDAO consultaDAO=new ConsultaDAO(); private final PagamentoDAO pagamentoDAO=new PagamentoDAO();
    private final Color NAV=new Color(23,50,77), BLUE=new Color(37,99,235), BG=new Color(246,248,251);
    public MainFrame(){setTitle("VetCare - Clínica Veterinária");setSize(1180,760);setLocationRelativeTo(null);setDefaultCloseOperation(EXIT_ON_CLOSE);setLayout(new BorderLayout());add(nav(),BorderLayout.WEST);
        content.setBackground(BG); addScreen("Início",dashboard()); addScreen("Tutores",tutor()); addScreen("Animais",animal()); addScreen("Consultas",consulta()); addScreen("Atendimento",atendimento()); addScreen("Busca",busca()); addScreen("Pagamentos",pagamento());add(content,BorderLayout.CENTER);}
    private void addScreen(String n,JPanel p){
        JScrollPane sp=new JScrollPane(p);
        sp.setBorder(null);
        sp.getVerticalScrollBar().setUnitIncrement(16);
        content.add(sp,n);
    }
    private JButton navBtn(String n){
        JButton b=new JButton(n);
        b.setUI(new BasicButtonUI());
        b.setMaximumSize(new Dimension(180,42));
        b.setPreferredSize(new Dimension(180,42));
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setFont(new Font("SansSerif",Font.PLAIN,14));
        b.setForeground(Color.WHITE);
        b.setBackground(NAV);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(8,14,8,14));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addActionListener(e->cards.show(content,n));
        return b;
    }
    private JPanel nav(){JPanel p=new JPanel();p.setPreferredSize(new Dimension(210,0));p.setBackground(NAV);p.setBorder(BorderFactory.createEmptyBorder(28,20,20,20));p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));JLabel l=new JLabel("VetCare");l.setForeground(Color.WHITE);l.setFont(new Font("SansSerif",Font.BOLD,25));p.add(l);p.add(Box.createVerticalStrut(30));for(String n:new String[]{"Início","Tutores","Animais","Consultas","Atendimento","Busca","Pagamentos"}){p.add(navBtn(n));p.add(Box.createVerticalStrut(8));}p.add(Box.createVerticalGlue());JButton sair=navBtn("Sair");sair.addActionListener(e->{dispose();new LoginFrame().setVisible(true);});p.add(sair);return p;}
    private JPanel base(String title){JPanel p=new JPanel();p.setBackground(BG);p.setBorder(BorderFactory.createEmptyBorder(35,40,40,40));p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));JLabel h=new JLabel(title);h.setFont(new Font("SansSerif",Font.BOLD,28));h.setAlignmentX(Component.LEFT_ALIGNMENT);p.add(h);p.add(Box.createVerticalStrut(25));return p;}
    private JPanel dashboard(){JPanel p=base("Visão geral");JPanel r=new JPanel(new GridLayout(1,3,18,0));r.setOpaque(false);r.setMaximumSize(new Dimension(850,130));r.setAlignmentX(Component.LEFT_ALIGNMENT);int consultas=0,animais=0,pendentes=0;try{consultas=consultaDAO.contar();animais=animalDAO.contar();pendentes=pagamentoDAO.contarPendentes();}catch(SQLException e){warnDb(e);}r.add(card("Consultas",String.valueOf(consultas)));r.add(card("Animais cadastrados",String.valueOf(animais)));r.add(card("Pagamentos pendentes",String.valueOf(pendentes)));p.add(r);p.add(Box.createVerticalStrut(30));JLabel q=new JLabel("Ações rápidas");q.setFont(new Font("SansSerif",Font.BOLD,19));q.setAlignmentX(Component.LEFT_ALIGNMENT);p.add(q);p.add(Box.createVerticalStrut(12));JPanel a=new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));a.setOpaque(false);a.setAlignmentX(Component.LEFT_ALIGNMENT);for(String n:new String[]{"Tutores","Animais","Consultas"}){JButton b=primary("Abrir "+n);b.addActionListener(e->cards.show(content,n));a.add(b);}p.add(a);return p;}
    private JPanel card(String a,String b){JPanel p=new JPanel();p.setBackground(Color.WHITE);p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(203,213,225)),BorderFactory.createEmptyBorder(18,18,18,18)));p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));p.add(new JLabel(a));JLabel v=new JLabel(b);v.setFont(new Font("SansSerif",Font.BOLD,32));p.add(Box.createVerticalStrut(12));p.add(v);return p;}
    private JButton primary(String s){
        JButton b=new JButton(s);
        b.setUI(new BasicButtonUI());
        b.setFont(new Font("SansSerif",Font.BOLD,14));
        b.setBackground(BLUE);
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBorder(BorderFactory.createEmptyBorder(10,18,10,18));
        b.setPreferredSize(new Dimension(Math.max(145,b.getPreferredSize().width+20),40));
        return b;
    }
    private JPanel form(String title,String[] labels,JTextField[] fields){JPanel p=base(title);JPanel f=new JPanel(new GridLayout(labels.length,2,14,14));f.setOpaque(false);f.setMaximumSize(new Dimension(720,labels.length*48));f.setAlignmentX(Component.LEFT_ALIGNMENT);for(int i=0;i<labels.length;i++){f.add(new JLabel(labels[i]));f.add(fields[i]);}p.add(f);p.add(Box.createVerticalStrut(20));return p;}
    private JPanel tutor(){JTextField nome=new JTextField(),cpf=new JTextField(),tel=new JTextField(),endereco=new JTextField();JPanel p=form("Cadastro de Tutor",new String[]{"Nome completo","CPF","Telefone","Endereço"},new JTextField[]{nome,cpf,tel,endereco});JButton s=primary("Salvar tutor");s.setAlignmentX(Component.LEFT_ALIGNMENT);s.addActionListener(e->{if(nome.getText().isBlank()||cpf.getText().isBlank()){warn("Nome e CPF são obrigatórios.");return;}try{tutorDAO.inserir(new Tutor(0,nome.getText().trim(),cpf.getText().trim(),tel.getText().trim(),endereco.getText().trim()));ok("Tutor cadastrado no banco de dados!");for(JTextField x:new JTextField[]{nome,cpf,tel,endereco})x.setText("");}catch(SQLException ex){warnDb(ex);}});p.add(s);return p;}
    private JPanel animal(){JTextField nome=new JTextField(),tipo=new JTextField(),raca=new JTextField(),idade=new JTextField(),tutor=new JTextField();JPanel p=form("Cadastro de Animal",new String[]{"Nome","Tipo","Raça","Idade","Tutor (nome)"},new JTextField[]{nome,tipo,raca,idade,tutor});JButton s=primary("Salvar animal");s.setAlignmentX(Component.LEFT_ALIGNMENT);s.addActionListener(e->{try{Tutor t=tutorDAO.buscarPorNome(tutor.getText().trim());if(t==null){warn("Tutor não encontrado. Cadastre o tutor primeiro.");return;}int i=Integer.parseInt(idade.getText().trim());animalDAO.inserir(new Animal(0,nome.getText().trim(),tipo.getText().trim(),raca.getText().trim(),i,t));ok("Animal cadastrado no banco de dados!");}catch(NumberFormatException ex){warn("Idade deve ser numérica.");}catch(SQLException ex){warnDb(ex);}});p.add(s);return p;}
    private JPanel consulta(){JTextField animal=new JTextField(),vet=new JTextField("Dr. Carlos"),data=new JTextField("30/10/2026 14:00"),obs=new JTextField();JPanel p=form("Agendar Consulta",new String[]{"Animal","Veterinário","Data e hora (dd/MM/yyyy HH:mm)","Motivo/observação"},new JTextField[]{animal,vet,data,obs});JButton s=primary("Agendar consulta");s.setAlignmentX(Component.LEFT_ALIGNMENT);s.addActionListener(e->{try{Animal a=animalDAO.buscarPorNome(animal.getText().trim());if(a==null){warn("Animal não encontrado.");return;}Veterinario v=veterinarioDAO.buscarPorNome(vet.getText().trim());if(v==null)v=veterinarioDAO.primeiro();if(v==null){warn("Nenhum veterinário cadastrado.");return;}LocalDateTime dt=LocalDateTime.parse(data.getText().trim(),DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));consultaDAO.inserir(new Consulta(0,a,v,dt,"",obs.getText().trim()));ok("Consulta gravada no banco de dados!");}catch(DateTimeParseException ex){warn("Data inválida. Use dd/MM/yyyy HH:mm.");}catch(SQLException ex){warnDb(ex);}});p.add(s);return p;}
    private JPanel atendimento(){JTextField animal=new JTextField(),trat=new JTextField(),obs=new JTextField();JPanel p=form("Registro da Consulta",new String[]{"Animal","Tratamento","Observações"},new JTextField[]{animal,trat,obs});JButton s=primary("Salvar atendimento");s.setAlignmentX(Component.LEFT_ALIGNMENT);s.addActionListener(e->{try{Consulta c=consultaDAO.buscarMaisRecentePorAnimal(animal.getText().trim());if(c==null){warn("Consulta não encontrada para este animal.");return;}consultaDAO.atualizarAtendimento(c.getId(),trat.getText().trim(),obs.getText().trim());ok("Atendimento atualizado no banco de dados!");}catch(SQLException ex){warnDb(ex);}});p.add(s);return p;}
    private JPanel busca(){JPanel p=base("Busca");JTextField q=new JTextField();q.setMaximumSize(new Dimension(600,34));q.setAlignmentX(Component.LEFT_ALIGNMENT);p.add(q);p.add(Box.createVerticalStrut(10));DefaultTableModel m=new DefaultTableModel(new String[]{"Nome","Tipo","Informação"},0);JTable table=new JTable(m);JScrollPane sp=new JScrollPane(table);sp.setMaximumSize(new Dimension(820,300));sp.setAlignmentX(Component.LEFT_ALIGNMENT);JButton b=primary("Buscar");b.setAlignmentX(Component.LEFT_ALIGNMENT);b.addActionListener(e->{m.setRowCount(0);try{String termo=q.getText().trim();for(Animal a:animalDAO.buscar(termo))m.addRow(new Object[]{a.getNome(),"Animal","Tutor: "+a.getTutor().getNome()});for(Tutor t:tutorDAO.buscar(termo))m.addRow(new Object[]{t.getNome(),"Tutor",t.getTelefone()});}catch(SQLException ex){warnDb(ex);}});p.add(b);p.add(Box.createVerticalStrut(16));p.add(sp);return p;}
    private JPanel pagamento(){JTextField animal=new JTextField(),valor=new JTextField(),situacao=new JTextField("Pago");JPanel p=form("Registro de Pagamento",new String[]{"Animal/consulta","Valor (ex.: 150.00)","Situação (Pago/Pendente)"},new JTextField[]{animal,valor,situacao});JButton s=primary("Registrar pagamento");s.setAlignmentX(Component.LEFT_ALIGNMENT);s.addActionListener(e->{try{Consulta c=consultaDAO.buscarMaisRecentePorAnimal(animal.getText().trim());if(c==null){warn("Consulta não encontrada.");return;}BigDecimal v=new BigDecimal(valor.getText().trim().replace(",","."));pagamentoDAO.inserir(new Pagamento(0,c,v,situacao.getText().trim().equalsIgnoreCase("Pago")));ok("Pagamento gravado no banco de dados!");}catch(NumberFormatException ex){warn("Informe um valor válido.");}catch(SQLException ex){warnDb(ex);}});p.add(s);return p;}
    private void ok(String s){JOptionPane.showMessageDialog(this,s,"Sucesso",JOptionPane.INFORMATION_MESSAGE);} private void warn(String s){JOptionPane.showMessageDialog(this,s,"Atenção",JOptionPane.WARNING_MESSAGE);}
    private void warnDb(SQLException e){warn("Erro ao acessar o MySQL. Verifique se o banco foi importado e se usuário/senha estão corretos.\nDetalhe: "+e.getMessage());}
}
