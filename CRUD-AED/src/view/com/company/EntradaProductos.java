package view.com.company;

import Connecion.ConectionBD;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Statement;

public class EntradaProductos extends JDialog {
    private JPanel ContentPane;
    private JLabel LCodigoPieza;
    private JLabel LDescripcion;
    private JTextField txtPrecio;
    private JTextField txtDescripcion;
    private JLabel LPrecio;
    private JTextField txtIdPieza;
    private JButton BtnAceptar;
    private JButton BtnCancelar;
    private JLabel LCantidad;
    private JTextField txtCantidad;
    private Statement stmt;
    private String idEstanteria;
    private String idAlmacen;

    public EntradaProductos(String idEstanteria, String idAlmacen) {
        setContentPane(ContentPane);
        setModal(true);
        getRootPane().setDefaultButton(BtnAceptar);
        this.idEstanteria = idEstanteria;
        this.idAlmacen = idAlmacen;

        BtnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        BtnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        ContentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        String codigo, descripcion, precio, cantidad;
        codigo = txtIdPieza.getText();
        descripcion = txtDescripcion.getText();
        precio = txtPrecio.getText();
        cantidad = txtCantidad.getText();
        String sql1,sql2;
        if (codigo.length() > 0) {

            try {

                sql1 = "insert into Pieza values('" + codigo + "','" + descripcion + "', " + precio + ")";
                sql2 = "insert into PiezaEnEstanteria values ( " + idAlmacen + ",'" + codigo + "','" + idEstanteria + "'," + cantidad + ")";

                stmt = ConectionBD.getStmt();
                stmt.executeUpdate(sql1);
                stmt.executeUpdate(sql2);
                JOptionPane.showMessageDialog(null, codigo + " ha sido añadido con éxito a la estantería " + idEstanteria);
                dispose();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al introducir un nuevo producto a la estantería " + idEstanteria);
                System.out.println(e.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error al introducir un nuevo producto a la estantería " + idEstanteria);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


}
