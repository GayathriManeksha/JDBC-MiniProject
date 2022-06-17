import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.mysql.jdbc.Connection;

import javax.swing.ButtonGroup;
import java.awt.Font;

public class Associate extends JFrame {
    private JPanel contentPane;
    String empid, first_name, last_name, phno, address, spid, user_name;

    // public Associate() {
    // setBounds(550, 319, 1014, 460);
    // setResizable(false);
    // contentPane = new JPanel();
    // contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    // setContentPane(contentPane);
    // contentPane.setLayout(null);

    // }
    // Not used as of now
    // Creates an edit screen with updated results
    public void getdetails(String un) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",
                    "root", "password");
            Statement statement = con.createStatement();
            ResultSet resultSet = statement
                    .executeQuery(
                            " select empid,firstName,lastName,phno,address,superid from employees where username='"
                                    + un
                                    + "'");
            resultSet.next();
            user_name = un;
            empid = resultSet.getString(1);
            first_name = resultSet.getString(2);
            last_name = resultSet.getString(3);
            phno = resultSet.getString(4);
            address = resultSet.getString(5);
            spid = resultSet.getString(6);

            System.out.println(last_name + " " + first_name + " "
                    + phno
                    + " " + address + " "
                    + spid
                    + " " + empid);
            AssociateEdit Ae = new AssociateEdit(empid, first_name, last_name, phno, address, un);
            Ae.edit();
            Ae.setVisible(true);

        } catch (Exception w1) {
            System.out.println(w1);
        }
    }

    public void showdetails(String un) {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",
                    "root", "password");
            Statement statement = con.createStatement();
            ResultSet resultSet = statement
                    .executeQuery(
                            " select empid,firstName,lastName,phno,address,superid from employees where username='"
                                    + un
                                    + "'");
            resultSet.next();
            user_name = un;
            empid = resultSet.getString(1);
            first_name = resultSet.getString(2);
            last_name = resultSet.getString(3);
            phno = resultSet.getString(4);
            address = resultSet.getString(5);
            spid = resultSet.getString(6);

            // while (resultSet.next()) {
            System.out.println(last_name + " " + first_name + " "
                    + phno
                    + " " + address + " "
                    + spid
                    + " " + empid);

        } catch (Exception w1) {
            System.out.println(w1);
        }
        setBounds(550, 319, 1014, 460);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JRadioButton rdbtnNewRadioButton = new JRadioButton("Edit my details");
        rdbtnNewRadioButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        rdbtnNewRadioButton.setBounds(59, 60, 250, 32);
        contentPane.add(rdbtnNewRadioButton);

        JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("See my details");
        rdbtnNewRadioButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        rdbtnNewRadioButton_1.setBounds(59, 90, 250, 26);
        contentPane.add(rdbtnNewRadioButton_1);
        ButtonGroup btg = new ButtonGroup();
        btg.add(rdbtnNewRadioButton_1);
        btg.add(rdbtnNewRadioButton);
        // try {
        // Class.forName("com.mysql.jdbc.Driver");

        // Connection con = (Connection)
        // DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",
        // "root", "password");
        // Statement statement = con.createStatement();
        JButton btnNewButton = new JButton("PROCEED");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rdbtnNewRadioButton_1.isSelected()) {
                    try {

                        JLabel lblempid = new JLabel("Employee ID");
                        lblempid.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                        lblempid.setBounds(59, 184, 120, 32);
                        add(lblempid);
                        JTextField fieldempid = new JTextField();
                        fieldempid.setText(empid);
                        fieldempid.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                        fieldempid.setBounds(180, 184, 100, 32);
                        fieldempid.setEditable(false);
                        add(fieldempid);
                        JLabel lblfname = new JLabel("First Name");
                        lblfname.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                        lblfname.setBounds(59, 234, 120, 32);
                        add(lblfname);
                        JTextField fieldfname = new JTextField();
                        fieldfname.setText(first_name);
                        fieldfname.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                        fieldfname.setBounds(180, 234, 120, 32);
                        fieldfname.setEditable(false);
                        add(fieldfname);
                        JLabel lbllname = new JLabel("Last Name");
                        lbllname.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                        lbllname.setBounds(59, 284, 120, 32);
                        add(lbllname);
                        JTextField fieldlname = new JTextField();
                        fieldlname.setText(last_name);
                        fieldlname.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                        fieldlname.setBounds(180, 284, 120, 32);
                        fieldlname.setEditable(false);
                        add(fieldlname);
                        JLabel lblphno = new JLabel("PhoneNumber");
                        lblphno.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                        lblphno.setBounds(499, 184, 130, 32);
                        add(lblphno);
                        JTextField fieldphno = new JTextField();
                        fieldphno.setText(phno);
                        fieldphno.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                        fieldphno.setBounds(680, 184, 120, 32);
                        fieldphno.setEditable(false);
                        add(fieldphno);
                        JLabel lbladdr = new JLabel("Address");
                        lbladdr.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                        lbladdr.setBounds(499, 184, 150, 132);
                        add(lbladdr);
                        JTextField fieldaddr = new JTextField();
                        fieldaddr.setText(address);
                        fieldaddr.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                        fieldaddr.setBounds(680, 234, 250, 32);
                        fieldaddr.setEditable(false);
                        add(fieldaddr);
                        // }
                    } catch (Exception w2) {
                        System.out.println(w2);
                    }
                } else {
                    // System.out.println("No");
                    AssociateEdit Ae = new AssociateEdit(empid, first_name, last_name, phno,
                            address, un);
                    Ae.edit();
                    Ae.setVisible(true);
                    setVisible(false);
                }

            }
        });
        btnNewButton.setFont(new Font("AvantGarde", Font.PLAIN, 20));
        btnNewButton.setBounds(376, 318, 200, 30);
        contentPane.add(btnNewButton);
        // } catch (Exception w1) {
        // System.out.println(w1);
        // }

    }

}
