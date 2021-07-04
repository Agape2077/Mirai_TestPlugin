import me.agape.example.propties.JDBCProperties;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        ResultSet rs = JDBCProperties.statement().executeQuery("select * from xiuwei_list");
        while (rs.next()){
            System.out.println(rs.getString(1)+" "+rs.getString(2));
        }
    }
}
