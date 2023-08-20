package tables;

import bd.IDBConnector;
import bd.MySQLConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentTable extends AbsTable {
    private final static String TABLE_NAME = "Student";

    public StudentTable(MySQLConnector db) {
        super(TABLE_NAME, db);
    }

    public void insertStudent() {
        final String sqlRequest = String.format("INSERT Student (id, fio, sex, id_studentgroup) VALUES (1, 'Ivanov', 'man', 100), (2, 'Pivin', 'man', 100), (3, 'Lobkov', 'man', 100), (3, 'Girnova', 'woman', 100), (4, 'Li', 'man', 100), (5, 'Pavlov', 'man', 100), (6, 'Kornev', 'man', 101), (7, 'Wushina', 'woman', 101), (9, 'Zun', 'man', 101), (10, 'Bokin', 'man', 101), (11, 'Vinima', 'woman', 102), (12, 'Sumik', 'man', 102), (13, 'Mutantov', 'man', 102), (14, 'Pegov', 'man', 102), (15, 'Antonov', 'man', 102);");
        ResultSet rs = db.executeRequest(sqlRequest);
    }

    public void selectAllStudent() {
        final String sqlRequest = String.format("select sg.name, s.sex, s.id, s.fio, sg.id, c.fio from studentgroup as sg left join student as s on sg.id = s.id_studentgroup left join curator as c on sg.id_curator= c.id");
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

    public void likeManStudent() {
        final String sqlRequest = String.format("SELECT * FROM STUDENT WHERE SEX LIKE 'man'");
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

    public void likeWomanStudent() {
        final String sqlRequest = String.format("SELECT * FROM STUDENT WHERE SEX LIKE 'woman'");
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
