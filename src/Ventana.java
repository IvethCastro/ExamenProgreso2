import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JList lstRanking;
    private JScrollPane spRanking;
    private JButton btnActualizarRanking;

    private CatalogoPeliculas catalogo = new CatalogoPeliculas();

    private DefaultListModel<Pelicula> modeloPeliculas = new DefaultListModel<>();
    private DefaultListModel<String> modeloRanking = new DefaultListModel<>();

    private Integer codigoSeleccionado = null;
    private String ultimaConsulta = null;
    private JLabel lblCantidad = new JLabel();

    public Ventana() {

        lstPeliculas.setModel(modeloPeliculas);
        lstRanking.setModel(modeloRanking);

        lblTotalPeliculas.add(lblCantidad);

        if (cmbGenero.getItemCount() == 0) {
            for (int i = 0; i < CatalogoPeliculas.generos.length; i++) {
                cmbGenero.addItem(CatalogoPeliculas.generos[i]);
            }
        }

        if (cmbGeneroEdit.getItemCount() == 0) {
            for (int i = 0; i < CatalogoPeliculas.generos.length; i++) {
                cmbGeneroEdit.addItem(CatalogoPeliculas.generos[i]);
            }
        }

        txtId.setEditable(false);
        txtLikes.setEditable(false);

        habilitarEdicion(false);

        refrescarListado();
        refrescarRanking();

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    catalogo.registrar(
                            txtCodigo.getText(),
                            txtTitulo.getText(),
                            txtDirector.getText(),
                            (String) cmbGenero.getSelectedItem(),
                            txtAnio.getText()
                    );

                    txtCodigo.setText("");
                    txtTitulo.setText("");
                    txtDirector.setText("");
                    txtAnio.setText("");

                    refrescarVistas();

                    JOptionPane.showMessageDialog(
                            panelPrincipal,
                            "Pelicula registrada correctamente."
                    );

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(
                            panelPrincipal,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        btnBuscarTexto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String consulta = txtBuscarTexto.getText();

                    ArrayList<Pelicula> resultados =
                            catalogo.buscarPorTexto(consulta);

                    ultimaConsulta = consulta;

                    mostrarResultados(resultados);

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(
                            panelPrincipal,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        btnBuscarCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                limpiarSeleccion();

                try {
                    int indice = catalogo.buscarIndicePorTexto(
                            txtBuscarCodigo.getText()
                    );

                    if (indice == -1) {
                        JOptionPane.showMessageDialog(
                                panelPrincipal,
                                "No existe una pelicula con ese codigo."
                        );
                        return;
                    }

                    Pelicula p = catalogo.listar().get(indice);

                    codigoSeleccionado = p.getNumeroID();

                    txtId.setText(String.valueOf(p.getNumeroID()));
                    txtTituloEdit.setText(p.getTitulo());
                    txtDirectorEdit.setText(p.getDirector());
                    cmbGeneroEdit.setSelectedItem(p.getGenero());
                    txtAnioEdit.setText(String.valueOf(p.getAnio()));
                    txtLikes.setText(String.valueOf(p.getMeGusta()));

                    habilitarEdicion(true);

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(
                            panelPrincipal,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (codigoSeleccionado == null) {
                    JOptionPane.showMessageDialog(
                            panelPrincipal,
                            "Primero busque una pelicula por codigo."
                    );
                    return;
                }

                try {
                    catalogo.editar(
                            codigoSeleccionado,
                            txtTituloEdit.getText(),
                            txtDirectorEdit.getText(),
                            (String) cmbGeneroEdit.getSelectedItem(),
                            txtAnioEdit.getText()
                    );

                    refrescarVistas();

                    if (ultimaConsulta != null) {

                        ArrayList<Pelicula> resultados =
                                catalogo.buscarPorTexto(ultimaConsulta);

                        boolean encontrado = false;

                        for (int i = 0; i < resultados.size(); i++) {
                            if (resultados.get(i).getNumeroID() == codigoSeleccionado) {
                                encontrado = true;
                            }
                        }

                        if (!encontrado) {
                            limpiarSeleccion();
                        }
                    }

                    JOptionPane.showMessageDialog(
                            panelPrincipal,
                            "Pelicula actualizada correctamente."
                    );

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(
                            panelPrincipal,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (codigoSeleccionado == null) {
                    JOptionPane.showMessageDialog(
                            panelPrincipal,
                            "Primero busque una pelicula por codigo."
                    );
                    return;
                }

                int respuesta = JOptionPane.showConfirmDialog(
                        panelPrincipal,
                        "¿Desea eliminar esta pelicula?",
                        "Confirmar",
                        JOptionPane.YES_NO_OPTION
                );

                if (respuesta == JOptionPane.YES_OPTION) {

                    catalogo.eliminar(codigoSeleccionado);

                    limpiarSeleccion();

                    refrescarVistas();

                    JOptionPane.showMessageDialog(
                            panelPrincipal,
                            "Pelicula eliminada correctamente."
                    );
                }
            }
        });

        btnMeGusta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (codigoSeleccionado == null) {
                    JOptionPane.showMessageDialog(
                            panelPrincipal,
                            "Primero busque una pelicula por codigo."
                    );
                    return;
                }

                int nuevo = catalogo.darMeGusta(codigoSeleccionado);

                txtLikes.setText(String.valueOf(nuevo));

                refrescarVistas();
            }
        });

        btnActualizarRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                refrescarRanking();

            }
        });
    }

    private void refrescarListado() {

        modeloPeliculas.clear();

        ArrayList<Pelicula> lista = catalogo.listar();

        for (int i = 0; i < lista.size(); i++) {
            modeloPeliculas.addElement(lista.get(i));
        }

        lblCantidad.setText(
                "Peliculas registradas: " + catalogo.cantidad()
        );
    }

    private void refrescarRanking() {

        modeloRanking.clear();

        ArrayList<Pelicula> lista = catalogo.ranking();

        for (int i = 0; i < lista.size(); i++) {

            Pelicula p = lista.get(i);

            modeloRanking.addElement(
                    p.getNumeroID() + " | " +
                            p.getTitulo() + " | Me gusta: " +
                            p.getMeGusta()
            );
        }
    }

    private void refrescarVistas() {

        refrescarListado();
        refrescarRanking();

        if (ultimaConsulta != null) {
            ArrayList<Pelicula> resultados =
                    catalogo.buscarPorTexto(ultimaConsulta);

            mostrarResultados(resultados);
        }
    }

    private void mostrarResultados(ArrayList<Pelicula> resultados) {

        txtResultados.setText("");

        if (resultados.isEmpty()) {
            txtResultados.setText("No se encontraron peliculas.");
            return;
        }

        for (int i = 0; i < resultados.size(); i++) {
            txtResultados.append(
                    resultados.get(i).toString() + "\n"
            );
        }
    }

    private void limpiarSeleccion() {

        codigoSeleccionado = null;

        txtId.setText("");
        txtTituloEdit.setText("");
        txtDirectorEdit.setText("");
        txtAnioEdit.setText("");
        txtLikes.setText("");

        habilitarEdicion(false);
    }

    private void habilitarEdicion(boolean activo) {

        txtTituloEdit.setEnabled(activo);
        txtDirectorEdit.setEnabled(activo);
        cmbGeneroEdit.setEnabled(activo);
        txtAnioEdit.setEnabled(activo);

        btnActualizar.setEnabled(activo);
        btnEliminar.setEnabled(activo);
        btnMeGusta.setEnabled(activo);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
