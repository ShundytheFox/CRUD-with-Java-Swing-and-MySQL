package model.com.company;

import Connecion.ConectionBD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModelAlmacenes {

    private Statement stmt;

    public ModelAlmacenes() {
        ConectionBD.OpenConn();
    }

    public DefaultTableModel CargaDatos(DefaultTableModel m) {
        String[] titulos = {"ID", "Descripción", "Dirección"};
        m = new DefaultTableModel(null, titulos);

        try {
            stmt = ConectionBD.getStmt();
            ResultSet rs = stmt.executeQuery("select * from almacen");
            String[] fila = new String[10];

            while (rs.next()) {
                fila[0] = rs.getString("id_almacen");
                fila[1] = rs.getString("descripcion");
                fila[2] = rs.getString("direccion");
                m.addRow(fila);
            }
        } catch (SQLException e) {

        }
        return m;
    }


}
