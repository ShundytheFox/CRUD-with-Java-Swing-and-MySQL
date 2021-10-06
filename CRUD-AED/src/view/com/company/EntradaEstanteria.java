package view.com.company;

import Connecion.ConectionBD;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Statement;

public class EntradaEstanteria extends JDialog {
    private JPanel panel1;
    private JLabel LCodigo;
    private JTextField txtCodigo;
    private JButton BtnCancelar;
    private JButton BtnAceptar;
    private Statement stmt;
    public String idAlmacen;

    public String getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(String idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public EntradaEstanteria(String idAlmacen) {
        setContentPane(panel1);
        setModal(true);
        getRootPane().setDefaultButton(BtnAceptar);
        this.idAlmacen = idAlmacen;

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
        panel1.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        String codigo;
        codigo = txtCodigo.getText();
        String sql;
        if (codigo.length()> 0) {

            try {

                sql = "insert into estanteria values('"+ codigo+"',"+ this.idAlmacen+")";

                stmt = ConectionBD.getStmt();
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,codigo+" ha sido añadido con éxito al almacén " + idAlmacen);
                dispose();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error al introducir una nueva estantería al almacén " + idAlmacen);
                System.out.println(e.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null,"Error al introducir una nueva estantería al almacén " + idAlmacen);
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
