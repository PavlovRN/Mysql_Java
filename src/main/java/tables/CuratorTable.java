package tables;

import bd.MySQLConnector;

import java.sql.ResultSet;

public class CuratorTable extends AbsTable {

    private final static String TABLE_NAME = "Curator";
    public CuratorTable(MySQLConnector db){
        super(TABLE_NAME, db);
    }
    public void insertCurator(){
        final String sqlRequest = String.format("INSERT Curator(id , fio) VALUES(21, 'Logov')" +
                ", (22, 'Mohov'), (23, 'Loginov'), (24, 'Glushko');");
        ResultSet rs = db.executeRequest(sqlRequest);


    }

}
