package Controler.com.company;

import Connecion.ConectionBD;
import model.com.company.ModelAlmacenes;
import model.com.company.ModelEstanterias;
import model.com.company.ModelProductos;
import view.com.company.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Statement;

public class ControllerAlmacenes implements ActionListener, WindowListener, MouseListener {

    private Statement stmt;
    private final ViewAlmacenes frentrada = new ViewAlmacenes();
    private DefaultTableModel m = null;
    private int almacenSeleccionado = -1;
    private int estanteriaSeleccionada = -1;


    // Constructor lanza cada uno de los procedimientos de la aplicación
    public ControllerAlmacenes() {
        IniciarVentana();
        IniciarEventos();
        PrepararBaseDatos();
    }

    public void IniciarVentana() {
        frentrada.setVisible(true);
    }

    public void IniciarEventos() {
        frentrada.getBtnEliminar().addActionListener(this::actionPerformed);
        frentrada.getBtnModificar().addActionListener(this::actionPerformed);
        frentrada.getBtnNuevo().addActionListener(this::actionPerformed);
        frentrada.getBtnEstanteria().addActionListener(this::actionPerformed);
        frentrada.getBtnEliminarEstanteria().addActionListener(this::actionPerformed);
        frentrada.getBtnAñadirProducto().addActionListener(this::actionPerformed);
        frentrada.getBtnEliminarProducto().addActionListener(this::actionPerformed);
        frentrada.getBtnModificarProducto().addActionListener(this::actionPerformed);
        frentrada.getTable1().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filSelect;
                filSelect = frentrada.getTable1().getSelectedRow();
                almacenSeleccionado = filSelect;
                ModelEstanterias me = new ModelEstanterias("select * from Estanteria where id_almacen = " + frentrada.getTable1().getModel().getValueAt(filSelect, 0));
                frentrada.getTable2().setModel(me.CargaDatos(m));
            }
        });
        frentrada.getTable2().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filSelect;
                filSelect = frentrada.getTable2().getSelectedRow();
                estanteriaSeleccionada = filSelect;
                ModelProductos me = new ModelProductos("select * from Pieza where id_pieza in (select id_pieza from PiezaEnEstanteria " +
                        "where id_estanteria = '" + frentrada.getTable2().getModel().getValueAt(filSelect, 0) +"'" +
                        "and id_almacen= " + frentrada.getTable2().getModel().getValueAt(filSelect, 1) + ")") ;
                frentrada.getTable3().setModel(me.CargaDatos(m));
                System.out.println(frentrada.getTable2().getModel().getValueAt(filSelect, 0));
            }
        });
        frentrada.addWindowListener(this);

    }

    public void PrepararBaseDatos() {
        ModelAlmacenes entrada = new ModelAlmacenes();
        frentrada.getTable1().setModel(entrada.CargaDatos(m));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String entrada = e.getActionCommand();

        switch (entrada) {
            case "Eliminar almacén":
                int filaSelec;
                String id_almacen;
                int resp;

                try {
                    filaSelec = frentrada.getTable1().getSelectedRow();

                    if (filaSelec == -1) {
                        JOptionPane.showMessageDialog(null,"Debes seleccionar el almacén a borrar");

                    } else {
                        resp = JOptionPane.showConfirmDialog(null,"¿Desea eliminar el almacén seleccionado?",
                                "Eliminar",JOptionPane.YES_NO_OPTION);

                        if (resp == JOptionPane.YES_OPTION) {
                            m = (DefaultTableModel) frentrada.getTable1().getModel();
                            id_almacen = (String) m.getValueAt(filaSelec,0);

                            stmt = ConectionBD.getStmt();

                            stmt.executeUpdate("delete from almacen where id_almacen = '" + id_almacen + "'");
                            PrepararBaseDatos();

                        }

                    }

                } catch (Exception x) {
                    JOptionPane.showMessageDialog(null,"Error al eliminar el almacén");
                    System.out.println(x.getMessage());
                }

                break;

            case "Modificar almacén":
                int filSelec;
                String id,desc,direcc;

                filSelec = frentrada.getTable1().getSelectedRow();

                if (filSelec == -1) {
                    JOptionPane.showMessageDialog(null,"Debes seleccionar el almacén a modificar");

                } else {
                    ActualizarDatos actualizarDatos = new ActualizarDatos();

                    m = (DefaultTableModel) frentrada.getTable1().getModel();

                    id = (String) m.getValueAt(filSelec,0);
                    desc = (String) m.getValueAt(filSelec,1);
                    direcc = (String) m.getValueAt(filSelec,2);

                    actualizarDatos.setTxtDescripcion(desc);
                    actualizarDatos.setTxtDireccion(direcc);
                    actualizarDatos.setId(id);

                    actualizarDatos.setBounds(700,300,400,400);
                    actualizarDatos.setVisible(true);
                    PrepararBaseDatos();

                }
                break;

            case "Añadir almacén":
                EntradaDatos entradaDatos = new EntradaDatos();
                entradaDatos.setBounds(700,300,400,400);
                entradaDatos.setVisible(true);
                PrepararBaseDatos();
                break;

            case "Añadir estantería":
                int filSelect;
                filSelect = frentrada.getTable1().getSelectedRow();
                if (filSelect == -1) {
                    JOptionPane.showMessageDialog(null,"Debes seleccionar un almacén");
                } else {
                    EntradaEstanteria entradaEstanteria = new EntradaEstanteria((String)frentrada.getTable1().getModel().getValueAt(filSelect, 0));
                    entradaEstanteria.setBounds(700, 300, 300, 150);
                    entradaEstanteria.setVisible(true);
                    System.out.println((String)frentrada.getTable1().getModel().getValueAt(filSelect, 0));
                    if (almacenSeleccionado != -1) {
                        ModelEstanterias me = new ModelEstanterias("select * from Estanteria where id_almacen = " + frentrada.getTable1().getModel().getValueAt(almacenSeleccionado, 0));
                        frentrada.getTable2().setModel(me.CargaDatos(m));
                    }
                    PrepararBaseDatos();
                }

                break;

            case "Eliminar estantería":
                int filaselecEstanteria;
                String idEstanteria;
                int respEstanteria;

                try {
                    filaselecEstanteria = frentrada.getTable2().getSelectedRow();

                    if (filaselecEstanteria == -1) {
                        JOptionPane.showMessageDialog(null,"Debes seleccionar la estantería a borrar");

                    } else {
                        respEstanteria = JOptionPane.showConfirmDialog(null,"¿Desea eliminar la estantería seleccionado?",
                                "Eliminar",JOptionPane.YES_NO_OPTION);

                        if (respEstanteria == JOptionPane.YES_OPTION) {
                            m = (DefaultTableModel) frentrada.getTable2().getModel();
                            idEstanteria = (String) m.getValueAt(filaselecEstanteria,0);

                            stmt = ConectionBD.getStmt();

                            stmt.executeUpdate("delete from estanteria where id_estanteria = '" + idEstanteria + "'");

                            if (almacenSeleccionado != -1) {
                                ModelEstanterias me = new ModelEstanterias("select * from Estanteria where id_almacen = " + frentrada.getTable1().getModel().getValueAt(almacenSeleccionado, 0));
                                frentrada.getTable2().setModel(me.CargaDatos(m));
                            }
                            PrepararBaseDatos();

                        }

                    }

                } catch (Exception x) {
                    System.out.println(x.getMessage());
                    JOptionPane.showMessageDialog(null,"Error al eliminar la estantería");
                }

                break;

            case "Añadir producto":
                int filSelectProduct;
                filSelectProduct = frentrada.getTable2().getSelectedRow();
                if (filSelectProduct == -1) {
                    JOptionPane.showMessageDialog(null,"Debes seleccionar una estantería");
                } else {
                    EntradaProductos entradaProducto = new EntradaProductos((String)frentrada.getTable2().getModel().getValueAt(filSelectProduct, 0),
                            (String)frentrada.getTable2().getModel().getValueAt(filSelectProduct, 1));
                    entradaProducto.setBounds(700, 300, 300, 300);
                    entradaProducto.setVisible(true);
                    System.out.println((String)frentrada.getTable2().getModel().getValueAt(filSelectProduct, 0));
                    if (estanteriaSeleccionada != -1) {
                        ModelProductos me = new ModelProductos("select * from Pieza where id_pieza in (select id_pieza from PiezaEnEstanteria " +
                                "where id_estanteria = '" + frentrada.getTable2().getModel().getValueAt(filSelectProduct, 0) +"'" +
                                "and id_almacen= " + frentrada.getTable2().getModel().getValueAt(filSelectProduct, 1) + ")");
                        frentrada.getTable3().setModel(me.CargaDatos(m));
                    }
                    PrepararBaseDatos();
                }

                break;

            case "Eliminar producto":
                int filSelectProductDelete;
                String idPieza;
                int respPieza;

                try {
                    filSelectProductDelete = frentrada.getTable3().getSelectedRow();

                    if (filSelectProductDelete == -1) {
                        JOptionPane.showMessageDialog(null,"Debes seleccionar el producto a borrar");

                    } else {
                        respPieza = JOptionPane.showConfirmDialog(null,"¿Desea eliminar el producto seleccionado?",
                                "Eliminar",JOptionPane.YES_NO_OPTION);

                        if (respPieza == JOptionPane.YES_OPTION) {
                            m = (DefaultTableModel) frentrada.getTable3().getModel();
                            idPieza = (String) m.getValueAt(filSelectProductDelete,0);

                            stmt = ConectionBD.getStmt();

                            stmt.executeUpdate("delete from Pieza where id_pieza = '" + idPieza + "'");
                            stmt.executeUpdate("delete from PiezaEnEstanteria where id_almacen = '" + frentrada.
                                    getTable2().getModel().getValueAt(filSelectProductDelete, 1) + "'" + " and id_pieza = '" + idPieza + "' and " +
                                    "id_estanteria = '" + frentrada.
                                    getTable2().getModel().getValueAt(filSelectProductDelete, 0)+ "'");

                            if (almacenSeleccionado != -1) {
                                ModelProductos me = new ModelProductos("select * from Pieza where id_pieza in (select id_pieza from PiezaEnEstanteria " +
                                        "where id_estanteria = '" + frentrada.getTable2().getModel().getValueAt(filSelectProductDelete, 0) +"'" +
                                        "and id_almacen= " + frentrada.getTable2().getModel().getValueAt(filSelectProductDelete, 1) + ")");
                                frentrada.getTable3().setModel(me.CargaDatos(m));
                            }
                            PrepararBaseDatos();

                        }

                    }

                } catch (Exception x) {
                    System.out.println(x.getMessage());
                    JOptionPane.showMessageDialog(null,"Error al eliminar el producto");
                }

                break;

            case "Modificar producto":
                int filSelecProductModify,filSelecProductModify2;
                String idPiezaModify, idEstanteriaModify, idAlmacenModify, descrip, precio, cantidad ;

                filSelecProductModify = frentrada.getTable2().getSelectedRow();
                filSelecProductModify2 = frentrada.getTable3().getSelectedRow();

                if (filSelecProductModify == -1) {
                    JOptionPane.showMessageDialog(null,"Debes seleccionar el producto a modificar");

                } else {
                    UpdateProductos actualizarProducto = new UpdateProductos((String)frentrada.getTable2().getModel().
                            getValueAt(filSelecProductModify, 0),
                            (String)frentrada.getTable2().getModel().getValueAt(filSelecProductModify, 1),
                            (String)frentrada.getTable3().getModel().getValueAt(filSelecProductModify2, 0));

                    m = (DefaultTableModel) frentrada.getTable3().getModel();

                    descrip = (String) m.getValueAt(filSelecProductModify,1);
                    precio = (String) m.getValueAt(filSelecProductModify,2);

                    JOptionPane.showMessageDialog(null, descrip);

                    actualizarProducto.setTxtPrecio(precio);
                    actualizarProducto.setTxtDescripcion(descrip);

                    actualizarProducto.setBounds(700,300,300,350);
                    actualizarProducto.setVisible(true);

                    if (estanteriaSeleccionada != -1) {
                        ModelProductos me = new ModelProductos("select * from Pieza where id_pieza in (select id_pieza from PiezaEnEstanteria " +
                                "where id_estanteria = '" + frentrada.getTable2().getModel().getValueAt(filSelecProductModify, 0) +"'" +
                                "and id_almacen= " + frentrada.getTable2().getModel().getValueAt(filSelecProductModify, 1) + ")");
                        frentrada.getTable3().setModel(me.CargaDatos(m));
                    }
                    PrepararBaseDatos();

                }
                break;

        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("ha salido del programa");
        frentrada.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ConectionBD.CloseConn();
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
