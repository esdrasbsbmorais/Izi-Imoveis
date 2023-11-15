
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VisualizarImoveisDialog extends JDialog {

    public VisualizarImoveisDialog(Frame owner, List<Imovel> imoveis) {
        super(owner, "Lista de Imóveis", true);
        setSize(400, 300);
        setLocationRelativeTo(owner);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> imoveisList = new JList<>(listModel);

        // Preenche a lista com os imóveis
        for (Imovel imovel : imoveis) {
            listModel.addElement(imovel.getNome() + " - " + imovel.getTipo());
        }

        JScrollPane scrollPane = new JScrollPane(imoveisList);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose()); // Fecha a janela de visualização de imóveis

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnFechar, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }
}
