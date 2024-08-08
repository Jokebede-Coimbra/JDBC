package application;

import db.DB;

import java.sql.*;
import java.text.SimpleDateFormat;

public class InserirDados {
    public static void main(String[] args) {

        // Demo - inserir dados

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection connection = null;
        PreparedStatement st = null;

        try {
            connection = DB.getConnection();
/*
            st = connection.prepareStatement(
                    "INSERT INTO seller "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                            + "VALUES "
                            + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, "Jayanne Mirelly");
            st.setString(2, "jay@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("08/08/2024").getTime()));
            st.setDouble(4, 3000.0);
            st.setInt(5, 4);
*/
            st = connection.prepareStatement("insert into department (Name) values ('D1'), ('D2')",
                    Statement.RETURN_GENERATED_KEYS);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = st.getGeneratedKeys();
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    System.out.println("Done! Id = " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

}
