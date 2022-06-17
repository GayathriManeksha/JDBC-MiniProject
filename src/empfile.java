import java.io.File; // Import the File class
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;

public class empfile {
    String filename;

    void create_file(String eid) {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",
                    "root", "password");
            Statement statement = con.createStatement();
            ResultSet resultSet = statement
                    .executeQuery(
                            " select firstName,lastName,phno,address,superid from employees where empid='"
                                    + eid
                                    + "'");
            resultSet.next();

            String empid = resultSet.getString(1);
            String first_name = resultSet.getString(2);
            String last_name = resultSet.getString(3);
            String phno = resultSet.getString(4);
            String address = resultSet.getString(5);

            filename = eid + ".txt";
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter myWriter = new FileWriter(filename, false);
            myWriter.write("Employee Name\n" + empid + "First Name\n" + first_name + "Last Name\n" + last_name
                    + "Phone Number\n" + phno + "Address\n" + address);
            myWriter.close();
        } catch (Exception e8) {
            System.out.println(e8);
        }

    }

    void write_to_file() {

    }
}
