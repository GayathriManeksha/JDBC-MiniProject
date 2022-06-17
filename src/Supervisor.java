import java.awt.Component;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Supervisor extends JFrame {
    String sid, fname, lname, username;
    String empids[] = new String[5];
    Connection con;
    Statement statement;
    JComboBox Employees;

    Supervisor() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",
                    "root", "password");
            statement = con.createStatement();
        } catch (Exception e3) {
            System.out.println(e3);
        }

    }

    public void getdata(String un) {
        // Get data from database
        username = un;
        try {
            // Class.forName("com.mysql.jdbc.Driver");

            // Connection con = (Connection)
            // DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",
            // "root", "password");
            // Statement statement = con.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("select * from supervisor where username='" + un + "'");
            while (resultSet.next()) {
                // System.out.println(resultSet.getString(1));
                sid = resultSet.getString(1);
                fname = resultSet.getString(2);
                lname = resultSet.getString(3);
            }

            // ResultSet resultSet2 = statement
            // .executeQuery("select empid,status from Employees where superid='" + sid +
            // "'");
            // int i = 0;
            // while (resultSet2.next()) {
            // System.out.println(resultSet2.getString(1));
            // int status = resultSet2.getInt(2);
            // if (status == 1)
            // empids[i] = resultSet2.getString(1);

            // i++;
            // }
            getEmployees();
        } catch (Exception e4) {
            System.out.println(e4);
        }

    }

    public void getEmployees() {
        try {
            ResultSet resultSet2 = statement
                    .executeQuery("select empid,status from Employees where superid='" + sid + "'");
            int i = 0;
            while (resultSet2.next()) {
                System.out.println(resultSet2.getString(1) + "  " + resultSet2.getString(2));
                int status = resultSet2.getInt(2);
                if (status == 1)
                    empids[i] = resultSet2.getString(1);
                else
                    empids[i] = "Null";

                i++;
            }
        } catch (Exception e8) {
            System.out.println(e8);
        }
    }

    public void showOptions() {
        // Shows three options
        // view details of Employee when empid is entered?
        // call function writefile()
        // delete box along with employee details(Employee id) to dismiss Employee
        // status (Refresh button)

        setBounds(550, 319, 1014, 460);
        setResizable(false);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        Employees = new JComboBox();
        Employees.setBounds(739, 32, 75, 27);
        try {
            for (String x : empids)
                if (!x.equals("Null"))
                    Employees.addItem(x);
        } catch (Exception e) {

        }

        contentPane.add(Employees);

        JRadioButton rdbtnNewRadioButton = new JRadioButton("See");
        rdbtnNewRadioButton.setFont(new Font("Dauphin", Font.PLAIN, 20));
        rdbtnNewRadioButton.setBounds(59, 60, 138, 32);
        contentPane.add(rdbtnNewRadioButton);

        JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("delete");
        rdbtnNewRadioButton_1.setFont(new Font("Dauphin", Font.PLAIN, 20));
        rdbtnNewRadioButton_1.setBounds(59, 90, 138, 32);
        contentPane.add(rdbtnNewRadioButton_1);

        JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Create a file");
        rdbtnNewRadioButton_2.setFont(new Font("Dauphin", Font.PLAIN, 20));
        rdbtnNewRadioButton_2.setBounds(59, 120, 138, 32);
        contentPane.add(rdbtnNewRadioButton_2);

        ButtonGroup btg = new ButtonGroup();
        btg.add(rdbtnNewRadioButton_1);
        btg.add(rdbtnNewRadioButton);
        btg.add(rdbtnNewRadioButton_2);

        JButton btnNewButton = new JButton("Select");
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (rdbtnNewRadioButton.isSelected()) {
                    JTextArea lbl = new JTextArea("Employee ID");
                    lbl.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                    lbl.setBounds(59, 184, 120, 32);
                    contentPane.add(lbl);

                    viewdetails();
                } else if (rdbtnNewRadioButton_1.isSelected()) {
                    JTextArea lbl = new JTextArea("Employee ID");
                    lbl.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                    lbl.setBounds(59, 284, 120, 32);
                    contentPane.add(lbl);

                    // delete
                    JButton btnRefresh = new JButton("REFRESH");
                    delete();
                    refresh(btnRefresh);
                    btnRefresh.setFont(new Font("Segoe UI", Font.BOLD, 10));
                    btnRefresh.setBounds(376, 338, 200, 10);
                    contentPane.add(btnRefresh);

                    return;
                } else if (rdbtnNewRadioButton_2.isSelected()) {
                    writefile((String) Employees.getSelectedItem());
                }
            }
        }); // TODO JFrame , JComobobox made global, use viewdeatils() to see
            // delete()
        if (rdbtnNewRadioButton_1.isSelected()) {
            return;
        }
        btnNewButton.setFont(new Font("Serif", Font.PLAIN, 20));
        btnNewButton.setBounds(376, 318, 200, 30);
        contentPane.add(btnNewButton);
    }

    public void viewdetails() {
        // viewdetails of all employees probably with a delete button
        try {
            String eid = (String) Employees.getSelectedItem();
            ResultSet resultSet = statement
                    .executeQuery(
                            " select firstName,lastName,phno,address from Employees where empid='"
                                    + eid
                                    + "'");
            System.out.print("Jcombobox" + eid);
            resultSet.next();

            String first_name = resultSet.getString(1);
            String last_name = resultSet.getString(2);
            String phno = resultSet.getString(3);
            String address = resultSet.getString(4);

            // while (resultSet.next()) {
            System.out.println(last_name + " " + first_name + " "
                    + phno
                    + " " + address + " ");

            // Printing details

            JLabel lblempid = new JLabel("Employee ID");
            lblempid.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            lblempid.setBounds(59, 184, 120, 32);
            add(lblempid);
            JTextField fieldempid = new JTextField();
            fieldempid.setText(eid);
            fieldempid.setFont(new Font("Segoe UI", Font.ITALIC, 20));
            fieldempid.setBounds(180, 184, 100, 32);
            fieldempid.setEditable(false);
            add(fieldempid);
            JLabel lblfname = new JLabel("First Name");
            lblfname.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            lblfname.setBounds(59, 234, 120, 32);
            add(lblfname);
            JTextField fieldfname = new JTextField();
            fieldfname.setText(first_name);
            fieldfname.setFont(new Font("Segoe UI", Font.ITALIC, 20));
            fieldfname.setBounds(180, 234, 120, 32);
            fieldfname.setEditable(false);
            add(fieldfname);
            JLabel lbllname = new JLabel("Last Name");
            lbllname.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            lbllname.setBounds(59, 284, 120, 32);
            add(lbllname);
            JTextField fieldlname = new JTextField();
            fieldlname.setText(last_name);
            fieldlname.setFont(new Font("Segoe UI", Font.ITALIC, 20));
            fieldlname.setBounds(180, 284, 120, 32);
            fieldlname.setEditable(false);
            add(fieldlname);
            JLabel lblphno = new JLabel("PhoneNumber");
            lblphno.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            lblphno.setBounds(499, 184, 130, 32);
            add(lblphno);
            JTextField fieldphno = new JTextField();
            fieldphno.setText(phno);
            fieldphno.setFont(new Font("Segoe UI", Font.ITALIC, 20));
            fieldphno.setBounds(680, 184, 120, 32);
            fieldphno.setEditable(false);
            add(fieldphno);
            JLabel lbladdr = new JLabel("Address");
            lbladdr.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            lbladdr.setBounds(499, 184, 150, 132);
            add(lbladdr);
            JTextField fieldaddr = new JTextField();
            fieldaddr.setText(address);
            fieldaddr.setFont(new Font("Segoe UI", Font.ITALIC, 20));
            fieldaddr.setBounds(680, 234, 250, 32);
            fieldaddr.setEditable(false);
            add(fieldaddr);
        } catch (Exception e5) {
            System.out.println(e5);
        }
    }

    public void delete() {
        // Execute query for delete or dismissing employee status

        String eid = (String) Employees.getSelectedItem();

        try {
            int resultSet = statement
                    .executeUpdate(
                            " UPDATE Employees SET status=0 where empid='"
                                    + eid
                                    + "'");
            System.out.println(resultSet);

            if (resultSet == 1) {
                JButton btnNewButton = new JButton();
                JOptionPane.showMessageDialog(btnNewButton,
                        "Deleted Successfully");
                getEmployees();
            }
        } catch (Exception e5) {
            System.out.println(e5);
        }
        // showOptions();
    }

    public void refresh(JButton btnNewButton) {
        // JButton btnNewButton = new JButton("REFRESH");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    showOptions();

                } catch (Exception e9) {
                    System.out.println(e9);
                }
            }
        });
        // btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 10));
        // btnNewButton.setBounds(376, 338, 200, 10);
        // contentPane.add(btnNewButton);
    }

    // void getEmplData(String eid) {
    // try {
    // ResultSet resultSet2 = statement
    // .executeQuery("select firstName,lastName,phno,address from Employees where
    // empid='" + eid + "'");

    // while (resultSet2.next()) {

    // }
    // } catch (Exception e8) {
    // System.out.println(e8);
    // }
    // }

    public void writefile(String eid) {
        // TODO write to file
        empfile File = new empfile();
        File.create_file(eid);
    }
}
