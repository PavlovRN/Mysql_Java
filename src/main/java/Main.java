import bd.MySQLConnector;
import tables.CuratorTable;
import tables.StudentGroupTable;
import tables.StudentTable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Main {


    public static Map<String, String> studentColumns = new HashMap<String, String>() {{
        put("id", "int(10)");
        put("fio", "varchar(10)");
        put("sex", "varchar(10)");
        put("id_studentgroup", "int(10)");
    }};

    public static Map<String, String> curatorColumns = new HashMap<String, String>() {{
        put("id", "int(10)");
        put("fio", "varchar(10)");
    }};
    public static Map<String, String> studentgroupColumns = new HashMap<String, String>() {{
        put("id", "int(10)");
        put("name", "varchar(10)");
        put("id_curator", "int(10)");
    }};

    public static void main(String[] args) {

        MySQLConnector db = new MySQLConnector();

        try {

            StudentTable table_st = new StudentTable(db);

            if (table_st.isTableExist("student")){
                table_st.deleteTable("student");
            }
            table_st.create(studentColumns);
            table_st.insertStudent();
            table_st.likeManStudent();
            table_st.likeWomanStudent();
            table_st.selectAllStudent();


            StudentGroupTable table_gr = new StudentGroupTable(db);

            if (table_st.isTableExist("studentgroup")){
                table_st.deleteTable("studentgroup");
            }
            table_gr.create(studentgroupColumns);
            table_gr.insertStudentGroup();
            table_gr.selectGroupForCurator();
            table_gr.seatchStudentByGroupNumber();



            CuratorTable table_cur = new CuratorTable(db);

            if (table_st.isTableExist("curator")){
                table_st.deleteTable("curator");
            }
            table_cur.create(curatorColumns);
            table_cur.insertCurator();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close();
        }

    }
}