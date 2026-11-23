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
    private JTextField txtBuscarTexto;
    private JButton btnBuscarTexto;
    private JTextArea txtResultados;
    private JTextField txtBuscarCodigo;
    private JButton btnBuscarCodigo;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnMeGusta;
    private JTextField txtId;
    private JTextField txtTituloEdit;
    private JTextField txtDirectorEdit;
    private JComboBox cmbGeneroEdit;
    private JTextField txtAnioEdit;
    private JTextField txtLikes;

    public Ventana() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnBuscarTexto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnBuscarCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnMeGusta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
