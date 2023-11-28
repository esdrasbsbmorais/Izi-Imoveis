package views;

import controllers.SalvarSenha;
import models.Pessoa;
import util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TelaCadastroUsuario extends JFrame {

    private JTextField nomeField, loginField, telefoneField, emailField, senhaField;

    public TelaCadastroUsuario() {
        super("Cadastro de Conta");

        nomeField = new JTextField(20);
        loginField = new JTextField(20);
        telefoneField = new JTextField(20);
        emailField = new JTextField(20);
        senhaField = new JPasswordField(20);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrar();
            }
        });

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("Login:"));
        panel.add(loginField);
        panel.add(new JLabel("Telefone:"));
        panel.add(telefoneField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Senha:"));
        panel.add(senhaField);
        panel.add(new JLabel());
        panel.add(cadastrarButton);

        getContentPane().add(panel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void cadastrar() {
        String nome = nomeField.getText().trim();
        String login = loginField.getText().trim().toUpperCase();
        String telefone = telefoneField.getText().trim();
        String email = emailField.getText().trim();
        String senha = senhaField.getText().trim();
    
        if (nome.isEmpty() || login.isEmpty() || telefone.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        Pessoa novaPessoa = new Pessoa(nome, login, telefone, email, SalvarSenha.hashPassword(senha));
    
        DBO dbo = new DBO(DBUtil.conectar());
    
        try {
            dbo.cadastrarPessoa(novaPessoa);
            JOptionPane.showMessageDialog(this, "Conta criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar conta.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
