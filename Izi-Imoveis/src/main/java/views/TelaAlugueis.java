package views;
import javax.swing.*;

import controllers.PerfilController;
import models.Imoveis;
import util.DBO;
import util.DBUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TelaAlugueis extends JFrame {

    private PerfilController perfilController;
    private List<Imoveis> listaImoveis;

    public TelaAlugueis(int usuarioLogadoId) {
        this.usuarioLogadoId = usuarioLogadoId;
        setTitle("Plataforma de Alugueis");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Painel superior com imagem
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(52, 152, 219)); // Cor de fundo
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margens
        ImageIcon logoIcon = new ImageIcon("src/main/java/images/system/casa.png");
        JLabel logoLabel = new JLabel(logoIcon);
        headerPanel.add(logoLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Painel central com botões
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // Espaçamento entre os componentes
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margens
        centerPanel.setBackground(new Color(236, 240, 241)); // Cor de fundo
        JButton btnBuscar = new JButton("Buscar Imóveis Para Aluguel");
        JButton btnCadastrar = new JButton("Cadastrar Novo Aluguel");
        JButton btnVisualizar = new JButton("Visualizar Imóveis");
        JButton btnPerfil = new JButton("Perfil");
        centerPanel.add(btnPerfil);

        btnPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirJanelaPerfil();
            }
        });

        centerPanel.add(btnBuscar);
        centerPanel.add(btnCadastrar);
        centerPanel.add(btnVisualizar);
        add(centerPanel, BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> abrirJanelaBuscaImoveis());
        centerPanel.add(btnBuscar);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirJanelaCadastroImovel();
            }
        });

        btnVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirJanelaVisualizarImoveis();
            }
        });

        JButton btnGerenciarImoveis = new JButton("Gerenciar Meus Imóveis");
        btnGerenciarImoveis.addActionListener(e -> {
            TelaGerenciarImoveis dialog = new TelaGerenciarImoveis(this, usuarioLogadoId);
            dialog.setVisible(true);
        });

        centerPanel.add(btnGerenciarImoveis);

        JButton btnGerenciarReservas = new JButton("Gerenciar Minhas Reservas");
        btnGerenciarReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirJanelaGerenciarReservas();
            }
        });

        centerPanel.add(btnGerenciarReservas);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(44, 62, 80));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel infoLabel = new JLabel("Bem-vindo à Plataforma de Alugueis");
        infoLabel.setForeground(Color.WHITE);
        footerPanel.add(infoLabel);
        add(footerPanel, BorderLayout.SOUTH);

        listaImoveis = new ArrayList<>();
        perfilController = new PerfilController(new DBO(DBUtil.conectar()));
    }

    private String loginAtual;
    private int usuarioLogadoId;

    public void setLoginAtual(String loginAtual) {
        this.loginAtual = loginAtual;
    }

    public void setUsuarioLogadoId(int usuarioLogadoId) {
        this.usuarioLogadoId = usuarioLogadoId;
    }

    private void abrirJanelaPerfil() {
        TelaPerfil perfilDialog = new TelaPerfil(this, usuarioLogadoId, perfilController.getDBO());
        perfilDialog.setVisible(true);
    }

    private void abrirJanelaCadastroImovel() {
        TelaCadastroImoveis telaCadastroImoveis = new TelaCadastroImoveis(this, usuarioLogadoId);
        telaCadastroImoveis.setVisible(true);
    }    

    private void abrirJanelaVisualizarImoveis() {
        VisualizarImoveisDialog visualizarDialog = new VisualizarImoveisDialog(this, listaImoveis);
        visualizarDialog.setVisible(true);
    }

    private void abrirJanelaBuscaImoveis() {
        TelaBuscaImoveis buscaImoveisDialog = new TelaBuscaImoveis(this, new DBO(DBUtil.conectar()), usuarioLogadoId);
        buscaImoveisDialog.setVisible(true);
    }    

    private void abrirJanelaGerenciarReservas() {
        TelaGerenciarReservas gerenciarReservasDialog = new TelaGerenciarReservas(this, usuarioLogadoId);
        gerenciarReservasDialog.setVisible(true);
    }
}
