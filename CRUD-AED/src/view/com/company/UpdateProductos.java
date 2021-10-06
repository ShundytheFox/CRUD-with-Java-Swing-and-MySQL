package view.com.company;

import Connecion.ConectionBD;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Statement;

public class UpdateProductos extends JDialog {
    private Statement stmt;
    private JPanel Panel;
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JTextField txtCantidad;
    private JLabel LPrecio;
    private JLabel LDescripcion;
    private JButton BtnAceptar;
    private JButton BtnCancelar;
    private String idEstanteria;
    private String idAlmacen;
    private String idPieza;

    public UpdateProductos(String idEstanteria, String idAlmacen, String idPieza)  {
        setContentPane(Panel);
        setModal(true);
        getRootPane().setDefaultButton(BtnAceptar);
        this.idEstanteria = idEstanteria;
        this.idAlmacen = idAlmacen;
        this.idPieza = idPieza;

        BtnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {onOK();}
        });

        BtnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {onCancel();}
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        Panel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }


    public void onOK() {
        String desc, precio;
        String sql1;

        try {

            desc = txtDescripcion.getText();
            precio = txtPrecio.getText();

            sql1 = "update Pieza set descripcion = '"+ desc +"',"+
                    "precio = " + precio + "where id_pieza = '" + idPieza + "'" ;

            stmt = ConectionBD.getStmt();
            stmt.executeUpdate(sql1);
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al modificar el almacén");
        }

    }

    public void onCancel() {
        dispose();
    }

    public JTextField getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(String txtDescripcion) {
        this.txtDescripcion.setText(txtDescripcion);
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(String txtPrecio) {
        this.txtPrecio.setText(txtPrecio);
    }

    public JTextField getTxtCantidad() {
        return txtCantidad;
    }

    public void setTxtCantidad(String txtCantidad) {
        this.txtCantidad.setText(txtCantidad);
    }

    public String getIdEstanteria() {
        return idEstanteria;
    }

    public void setIdEstanteria(String idEstanteria) {
        this.idEstanteria = idEstanteria;
    }

    public String getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(String idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(String idPieza) {
        this.idPieza = idPieza;
    }
}
