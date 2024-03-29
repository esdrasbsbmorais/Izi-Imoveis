package views;

import javax.swing.*;
import models.Imoveis;
import util.DBO;
import util.DBUtil;

import java.awt.*;
import java.sql.SQLException;

public class TelaCadastroImoveis extends JDialog {

    private JComboBox<String> cboTipo;
    private JTextField txtDescricao;
    private JTextField txtGaragem;
    private JTextField txtPreco_aluguel;
    private JTextField txtIdade;
    // private JTextField txtFoto;
    private JTextField txtEndereco;
    private JTextField txtMetrosQuadrados;
    // private JButton btnCarregarFoto;

    private Imoveis imovel;
    private int usuarioLogadoId;

    public TelaCadastroImoveis(Frame owner, int usuarioLogadoId) {
        super(owner, "Cadastro de Imóvel", true);
        this.usuarioLogadoId = usuarioLogadoId;
        setSize(400, 500);
        setLocationRelativeTo(owner);
        cboTipo = new JComboBox<>(new String[]{"Casa", "Apartamento", "Comercial"});
        txtDescricao = new JTextField();
        txtGaragem = new JTextField();
        txtPreco_aluguel = new JTextField();
        txtIdade = new JTextField();
        // txtFoto = new JTextField();
        txtEndereco = new JTextField();
        txtMetrosQuadrados = new JTextField();
        // btnCarregarFoto = new JButton("Carregar Foto");

        // btnCarregarFoto.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         carregarFoto();
        //     }
        // });

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            salvarImovel();
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new GridLayout(14, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Tipo do Imóvel:"));
        panel.add(cboTipo);
        panel.add(new JLabel("Descrição do Imóvel:"));
        panel.add(txtDescricao);
        panel.add(new JLabel("Vagas de Garagem:"));
        panel.add(txtGaragem);
        panel.add(new JLabel("Preço:"));
        panel.add(txtPreco_aluguel);
        panel.add(new JLabel("Idade do Imóvel:"));
        panel.add(txtIdade);
        // panel.add(new JLabel("Caminho da Foto:"));
        // JPanel fotoPanel = new JPanel(new BorderLayout());
        // fotoPanel.add(btnCarregarFoto, BorderLayout.EAST);
        // panel.add(fotoPanel);
        panel.add(new JLabel("Endereço do Imóvel:"));
        panel.add(txtEndereco);
        panel.add(new JLabel("Metros Quadrados:"));
        panel.add(txtMetrosQuadrados);

        panel.add(btnSalvar);
        panel.add(btnCancelar);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

    public Imoveis getImovel() {
        return imovel;
    }

    private void salvarImovel() {
        try {
            String tipo = cboTipo.getSelectedItem().toString();
            String descricao = txtDescricao.getText();
            int garagem = Integer.parseInt(txtGaragem.getText());
            float Preco_aluguel = Float.parseFloat(txtPreco_aluguel.getText());
            int idade = Integer.parseInt(txtIdade.getText());
            // String foto = txtFoto.getText();
            String endereco = txtEndereco.getText();
            int metrosQuadrados = Integer.parseInt(txtMetrosQuadrados.getText());

            // String caminhoFotoOriginal = txtFoto.getText();
            // String caminhoFotoSalva = dbo.salvarImagemLocalmente(caminhoFotoOriginal);
    
            Imoveis imovel = new Imoveis(tipo, descricao, garagem, Preco_aluguel, idade, endereco, metrosQuadrados);
            DBO dbo = new DBO(DBUtil.conectar());
            dbo.cadastrarImovel(imovel, usuarioLogadoId);

            JOptionPane.showMessageDialog(this, "Imóvel cadastrado com sucesso!");
            dispose();
    
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Certifique-se de preencher todos os campos corretamente!");
            e.printStackTrace();
        }
    }    

    // private void carregarFoto() {
    //     JFileChooser fileChooser = new JFileChooser();
    //     FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png");
    //     fileChooser.setFileFilter(filter);

    //     int returnVal = fileChooser.showOpenDialog(this);

    //     if (returnVal == JFileChooser.APPROVE_OPTION) {
    //         String selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
    //         txtFoto.setText(selectedFile);
    //     }
    // }
}
