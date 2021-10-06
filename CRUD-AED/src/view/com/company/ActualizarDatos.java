package view.com.company;

import Connecion.ConectionBD;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Statement;

public class ActualizarDatos extends JDialog {
    private Statement stmt;
    private JPanel LId;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtDescripcion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private JLabel LDescripcion;
    private JLabel LDireccion;
    private JTextField txtDireccion;
    private String id;


    public ActualizarDatos() {
        setContentPane(LId);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {onOK();}
        });

        buttonCancel.addActionListener(new ActionListener() {
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
        LId.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }


    public void onOK() {
        String id,desc,direc;
        String sql;

        try {

            desc = txtDescripcion.getText();
            direc = txtDireccion.getText();
            id = getId();

            sql = "update almacen set descripcion = '"+ desc +"',"+
                    "direccion = '"+ direc +"' where id_almacen = " + id ;

            stmt = ConectionBD.getStmt();
            stmt.executeUpdate(sql);
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al modificar el almacén");
        }

    }

    public void onCancel() {
        dispose();
    }

    public void setTxtDescripcion(String nombre) {
        this.txtDescripcion.setText(nombre);
    }
    public void setTxtDireccion(String apellido1) {
        this.txtDireccion.setText(apellido1);
    }




}
