package view.com.company;

import Connecion.ConectionBD;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Statement;

public class EntradaDatos extends JDialog {
    private Statement stmt;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtId;
    private JLabel LDescripcion;
    private JTextField txtDescripcion;
    private JLabel LDireccion;
    private JTextField txtDireccion;

    public EntradaDatos() {
        setContentPane(contentPane);
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
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        String desc,direc;
        String sql;

        try {
            desc = txtDescripcion.getText();
            direc = txtDireccion.getText();


            sql = "insert into almacen values(default,'"+ desc+"','"+ direc+"')";

            stmt = ConectionBD.getStmt();
            stmt.executeUpdate(sql);
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al introducir el nuevo almacén");
            System.out.println(e.getMessage());
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        EntradaDatos dialog = new EntradaDatos();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
