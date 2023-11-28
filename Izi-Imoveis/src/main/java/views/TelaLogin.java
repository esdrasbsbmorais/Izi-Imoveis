package views;

import controllers.RecuperaSenha;
import models.Pessoa;
import util.DBO;
import util.DBUtil;

import javax.swing.*;
import java.awt.*;
// import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class TelaLogin extends JFrame {

    private JTextField loginField;
    private JPasswordField senhaField;
    private int usuarioLogadoId;

    public TelaLogin() {
        super("Login");

        loginField = new JTextField(20);
        senhaField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> realizarLogin());

        JButton criarContaButton = new JButton("Criar Conta");
        criarContaButton.addActionListener(e -> abrirTelaCadastroUsuario());

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

    private void realizarLogin() {
        String login = loginField.getText().toUpperCase();
        String senha = new String(senhaField.getPassword());

        DBO dbo = new DBO(DBUtil.conectar());

        try {
            Pessoa pessoa = dbo.buscarLogin(login);

            if (pessoa != null && RecuperaSenha.checkPassword(senha, pessoa.getSenha())) {
                this.usuarioLogadoId = pessoa.getPessoa_id();
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

    private void abrirTelaAlugueis() {
        SwingUtilities.invokeLater(() -> {
            TelaAlugueis telaAlugueis = new TelaAlugueis(usuarioLogadoId);
            telaAlugueis.setVisible(true);
            this.dispose();
        });
    }

    private void abrirTelaCadastroUsuario() {
        TelaCadastroUsuario telaCadastro = new TelaCadastroUsuario();
        telaCadastro.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }
            new TelaLogin().setVisible(true);
        });
    }    
}