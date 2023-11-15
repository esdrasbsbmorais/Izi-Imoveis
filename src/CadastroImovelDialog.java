
import javax.swing.*;
import java.awt.*;

public class CadastroImovelDialog extends JDialog {

    private JTextField txtNome;
    private JTextField txtTipo;
    private Imovel imovel;

    public CadastroImovelDialog(Frame owner) {
        super(owner, "Cadastro de Imóvel", true);
        setSize(400, 300);
        setLocationRelativeTo(owner);

        txtNome = new JTextField();
        txtTipo = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            salvarImovel();
            dispose(); // Fecha a janela de cadastro após salvar
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose()); // Fecha a janela de cadastro sem salvar

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Nome do Imóvel:"));
        panel.add(txtNome);
        panel.add(new JLabel("Tipo do Imóvel:"));
        panel.add(txtTipo);
        panel.add(btnSalvar);
        panel.add(btnCancelar);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

    // Método para obter o imóvel cadastrado
    public Imovel getImovel() {
        return imovel;
    }

    // Método para salvar o imóvel
    private void salvarImovel() {
        String nome = txtNome.getText();
        String tipo = txtTipo.getText();
        
        // Validação básica
        if (!nome.isEmpty() && !tipo.isEmpty()) {
            imovel = new Imovel(nome, tipo);
            JOptionPane.showMessageDialog(this, "Imóvel cadastrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
        }
    }
}
