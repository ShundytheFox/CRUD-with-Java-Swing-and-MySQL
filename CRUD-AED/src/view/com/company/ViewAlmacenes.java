package view.com.company;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewAlmacenes extends JFrame {
    private JButton btnEliminar;
    private JButton btnModificar;
    private JPanel PanelEntrada;
    private JButton btnNuevo;
    private JTable table1;
    private JButton personasButton;
    private JPanel panelAcciones;
    private JButton BtnEstanteria;
    private JTable table2;
    private JButton BtnEliminarEstanteria;
    private JTable table3;
    private JButton BtnAñadirProducto;
    private JButton BtnModificarProducto;
    private JButton BtnEliminarProducto;

    public JTable getTable2() {
        return table2;
    }

    public void setTable2(JTable table2) {
        this.table2 = table2;
    }

    public ViewAlmacenes() {

        super("Almacen");
        setContentPane(PanelEntrada);
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        setSize(ancho, alto);
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }



    public JTable getTable3() {
        return table3;
    }

    public void setTable3(JTable table3) {
        this.table3 = table3;
    }

    public JButton getBtnAñadirProducto() {
        return BtnAñadirProducto;
    }

    public void setBtnAñadirProducto(JButton btnAñadirProducto) {
        BtnAñadirProducto = btnAñadirProducto;
    }

    public JButton getBtnModificarProducto() {
        return BtnModificarProducto;
    }

    public void setBtnModificarProducto(JButton btnModificarProducto) {
        BtnModificarProducto = btnModificarProducto;
    }

    public JButton getBtnEliminarProducto() {
        return BtnEliminarProducto;
    }

    public void setBtnEliminarProducto(JButton btnEliminarProducto) {
        BtnEliminarProducto = btnEliminarProducto;
    }

    public JButton getBtnEliminarEstanteria() {
        return BtnEliminarEstanteria;
    }

    public void setBtnEliminarEstanteria(JButton btnEliminarEstanteria) {
        BtnEliminarEstanteria = btnEliminarEstanteria;
    }

    public JButton getBtnEstanteria() {
        return BtnEstanteria;
    }

    public void setBtnEstanteria(JButton btnEstanteria) {
        BtnEstanteria = btnEstanteria;
    }

    public JPanel getPanelEntrada() {
        return PanelEntrada;
    }

    public void setPanelEntrada(JPanel panelEntrada) {
        PanelEntrada = panelEntrada;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JButton getPersonasButton() {
        return personasButton;
    }

    public void setPersonasButton(JButton personasButton) {
        this.personasButton = personasButton;
    }

    public JButton getBtnEliminar() {return btnEliminar;}

    public void setBtnEliminar(JButton btnEliminar) {this.btnEliminar = btnEliminar;}

    public JPanel getPanelAcciones() {return panelAcciones;}

    public void setPanelAcciones(JPanel panelAcciones) {this.panelAcciones = panelAcciones;}

    public JButton getBtnNuevo() {return btnNuevo;}

    public void setBtnNuevo(JButton btnNuevo) {this.btnNuevo = btnNuevo;}

    public JButton getBtnModificar() {return btnModificar;}

    public void setBtnModificar(JButton btnModificar) {this.btnModificar = btnModificar;}


}
