import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TelaAlugueis extends JFrame {

    private List<Imovel> listaImoveis;

    public TelaAlugueis() {
        // Configurações da janela
        setTitle("Plataforma de Alugueis");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Layout
        setLayout(new BorderLayout());

        // Painel superior com uma imagem
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(52, 152, 219)); // Cor de fundo
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margens
        ImageIcon logoIcon = new ImageIcon("src/images/system/casa.png");
        JLabel logoLabel = new JLabel(logoIcon);
        headerPanel.add(logoLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Painel central com botões ou outros componentes
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // Adiciona espaçamento entre os componentes
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margens
        centerPanel.setBackground(new Color(236, 240, 241)); // Cor de fundo
        JButton btnBuscar = new JButton("Buscar Aluguéis");
        JButton btnCadastrar = new JButton("Cadastrar Novo Aluguel");
        JButton btnVisualizar = new JButton("Visualizar Imóveis");

        centerPanel.add(btnBuscar);
        centerPanel.add(btnCadastrar);
        centerPanel.add(btnVisualizar);
        add(centerPanel, BorderLayout.CENTER);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirJanelaCadastroImovel();
            }
        });

        // Adiciona um ouvinte de ação ao botão "Visualizar Imóveis"
        btnVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirJanelaVisualizarImoveis();
            }
        });

        // Painel inferior com informações adicionais
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(44, 62, 80)); // Cor de fundo
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margens
        JLabel infoLabel = new JLabel("Bem-vindo à Plataforma de Alugueis");
        infoLabel.setForeground(Color.WHITE); // Cor do texto
        footerPanel.add(infoLabel);
        add(footerPanel, BorderLayout.SOUTH);

        listaImoveis = new ArrayList<>();
    }

    // Método para abrir a janela de cadastro de imóvel
    private void abrirJanelaCadastroImovel() {
        CadastroImovelDialog cadastroDialog = new CadastroImovelDialog(this);
        cadastroDialog.setVisible(true);

        // Adiciona o imóvel à lista após o cadastro
        Imovel novoImovel = cadastroDialog.getImovel();
        if (novoImovel != null) {
            listaImoveis.add(novoImovel);
        }
    }

    // Método para abrir a janela de visualização de imóveis
    private void abrirJanelaVisualizarImoveis() {
        VisualizarImoveisDialog visualizarDialog = new VisualizarImoveisDialog(this, listaImoveis);
        visualizarDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }

            TelaAlugueis tela = new TelaAlugueis();
            tela.setVisible(true);
        });
    }
}
