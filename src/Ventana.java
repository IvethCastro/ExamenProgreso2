import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {
    private JPanel panelPrincipal;
    private JTabbedPane tabbedPane;
    private JList lstPeliculas;
    private JTextField txtCodigo;
    private JTextField txtTitulo;
    private JTextField txtDirector;
    private JComboBox cmbGenero;
    private JTextField txtAnio;
    private JButton btnRegistrar;
    private JPanel lblTotalPeliculas;

    public Ventana() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
