package tables;

import bd.IDBConnector;
import bd.MySQLConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbsTable {
    private String tableName;
    private Map<String, String> columns;
    protected IDBConnector db;

    public AbsTable(String tableName, MySQLConnector db) {
        this.tableName = tableName;
        this.db = db;

    }

    public void create(Map<String, String> columns) {
        this.columns = columns;
        String sqlRequest = String.format("CREATE TABLE  %s (%s)", this.tableName, convertMapColumnsToString());
        db.executeRequest(sqlRequest);

    }

    private String convertMapColumnsToString() {

        final String result = columns.entrySet().stream().map((Map.Entry entry) -> String.format("%s %s", entry.getKey()
                , entry.getValue())).collect(Collectors.joining(", "));
        return result;
    }

    public void selectAll() {
        final String sqlRequest = String.format("SELECT * FROM %s", tableName);
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        try {
            int columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.println(rs.getString(i) + " \t");
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

        }

    }

    public boolean isTableExist(String tableName) throws SQLException {
        ResultSet rs = db.executeRequestWithAnswer("SHOW TABLES");
        while (rs.next()) {
            String mysqlTableName = rs.getString(1);
            if (mysqlTableName.equals(tableName)) {
                return true;
            }
        }
        return false;
    }

    public void deleteTable(String tableName) {
        db.executeRequest(String.format("DROP TABLE  %s", tableName));

    }
}
