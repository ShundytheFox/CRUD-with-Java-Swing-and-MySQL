package model.com.company;

import Connecion.ConectionBD;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModelEstanterias {

    private Statement stmt;
    private String sqlQuery;

    public ModelEstanterias(String sql) {
        this.sqlQuery = sql;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public DefaultTableModel CargaDatos(DefaultTableModel m) {
        String[] titulos = { "Código","ID Almacén"};
        m = new DefaultTableModel(null, titulos);

        try {
            stmt = ConectionBD.getStmt();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            String[] fila = new String[9];

            while (rs.next()) {
                fila[0] = rs.getString("id_estanteria");
                fila[1] = rs.getString("id_almacen");
                m.addRow(fila);
            }
        } catch (SQLException e) {

        }
        return m;
    }
}
