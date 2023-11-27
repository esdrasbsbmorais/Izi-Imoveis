package views;

import controllers.RecuperaSenha;
import controllers.SalvarSenha;
import models.Pessoa;
import util.DBO;
import util.DBUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TelaLogin extends JFrame {

    private JTextField loginField;
    private JPasswordField senhaField;
    private String loginAtual;

    public TelaLogin() {
        super("Login");

        loginField = new JTextField(20);
        senhaField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }
        });

        JButton criarContaButton = new JButton("Criar Conta");
        criarContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaCadastroUsuario();
            }
        });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(criarContaButton, BorderLayout.SOUTH);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.add(new JLabel("Login:"));
        panel.add(loginField);
        panel.add(new JLabel("Senha:"));
        panel.add(senhaField);
        panel.add(new JLabel());
        panel.add(loginButton);

        getContentPane().add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public String getLoginAtual() {
        return loginAtual;
    }

    private void realizarLogin() {
        String login = loginField.getText();
        String senha = new String(senhaField.getPassword());

        DBO dbo = new DBO(DBUtil.conectar());

        try {
            Pessoa pessoa = dbo.buscarLogin(login);

            if (pessoa != null && RecuperaSenha.checkPassword(senha, pessoa.getSenha())) {
                this.loginAtual = login;
                abrirTelaAlugueis();
            } else {
                JOptionPane.showMessageDialog(this, "Login falhou. Verifique suas credenciais.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao realizar login.", "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            dbo.desconectar();
        }
    }

    private void abrirTelaCadastroUsuario() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }

            TelaCadastroUsuario telaCadastroUsuario = new TelaCadastroUsuario();
            telaCadastroUsuario.setVisible(true);
        });
    }

    private void abrirTelaAlugueis() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }

            TelaAlugueis telaAlugueis = new TelaAlugueis();
            telaAlugueis.setLoginAtual(this.loginAtual);
            this.dispose();
            telaAlugueis.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }

            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
        });
    }    
}
