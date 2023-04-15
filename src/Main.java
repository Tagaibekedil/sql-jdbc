import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    /*
    1. Нужно одним селектом взять все данные из двух таблиц
2. Создать 2 класса - User, Account. Account является полем User.
3. Вывести в консоль
     */

    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "postgres";
    static final String QUERY = "select ua.id,ua.account_number,ua.currency,ua.created_dt,u.id,u.login,u.fulname," +
            "u.email,u.age,u.gender,u.created_dt from users u join users_account ua on u.id=ua.id";

    public static void main(String[] args) {
        Users users = new Users();
        Account account = new Account();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY)) {
            //Extract data from result
            while (rs.next()) {
                account.setId(rs.getInt("id"));
                account.setAccount_number(rs.getString("account_number"));
                account.setCurrency(rs.getDouble("currency"));
                account.setCreated_dt(rs.getString("created_dt"));

                users.setId(rs.getInt("id"));
                users.setLogin(rs.getString("login"));
                users.setFulname(rs.getString("fulname"));
                users.setEmail(rs.getString("email"));
                users.setAge(rs.getInt("age"));
                users.setGender(rs.getString("gender"));
                users.setCreated_dt(rs.getString("created_dt"));
                System.out.println(users.toString());
                System.out.println(account.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
