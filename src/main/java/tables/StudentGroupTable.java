package tables;

import bd.MySQLConnector;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentGroupTable extends AbsTable {
    private final static String TABLE_NAME = "StudentGroup";

    public StudentGroupTable(MySQLConnector db) {
        super(TABLE_NAME, db);
    }
    public void insertStudentGroup(){
        final String sqlRequest = String.format("INSERT StudentGroup (id, name, id_curator)" +
                " VALUES (100, 'ballet', 21), (101, 'singing', 22), (102, 'box', 23) ;");
        ResultSet rs = db.executeRequest(sqlRequest);
    }
    public void selectGroupForCurator() {
        final String sqlRequest = String.format("select sg.name, sg.id_curator, sg.id, c.fio from studentgroup as sg inner join curator as c on sg.id_curator=c.id");
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

    }public void seatchStudentByGroupNumber() {
        final String sqlRequest = String.format("select * from student where id_studentgroup in(select id_studentgroup from student where id_studentgroup = 102)");
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

}
