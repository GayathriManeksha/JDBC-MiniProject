
//import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.awt.event.ActionEvent;

import java.awt.Font;

public class AssociateEdit extends Associate {

    private JPanel contentPane;

    AssociateEdit(String empid, String first_name, String last_name, String phno, String address, String un) {
        this.empid = empid;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.phno = phno;
        this.user_name = un;
    }

    public void edit() {
        setBounds(550, 319, 1014, 460);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // edit fields

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
        fieldlname.setEditable(true);
        add(fieldlname);
        JLabel lblphno = new JLabel("PhoneNumber");
        lblphno.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblphno.setBounds(499, 184, 130, 32);
        add(lblphno);
        JTextField fieldphno = new JTextField();
        fieldphno.setText(phno);
        fieldphno.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        fieldphno.setBounds(680, 184, 120, 32);
        fieldphno.setEditable(true);
        add(fieldphno);
        JLabel lbladdr = new JLabel("Address");
        lbladdr.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lbladdr.setBounds(499, 184, 150, 132);
        add(lbladdr);
        JTextField fieldaddr = new JTextField();
        fieldaddr.setText(address);
        fieldaddr.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        fieldaddr.setBounds(680, 234, 250, 32);
        fieldaddr.setEditable(true);
        add(fieldaddr);

        // last_name=fieldlname.getText();
        // UPDATE
        JButton btnNewButton = new JButton("UPDATE");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                last_name = fieldlname.getText();
                phno = fieldphno.getText();
                address = fieldaddr.getText();

                try {
                    Class.forName("com.mysql.jdbc.Driver");

                    Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",
                            "root", "password");
                    Statement statement = con.createStatement();
                    int resultSet = statement
                            .executeUpdate(
                                    "UPDATE employees SET lastName ='" + last_name + "',phno='" + phno + "',address='"
                                            + address + "' WHERE empid='" + empid + "'");
                    // System.out.println(resultSet); == 1
                    JOptionPane.showMessageDialog(btnNewButton,
                            "Updated Successfully");
                    // setVisible(false);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }
        });
        btnNewButton.setFont(new Font("Serif", Font.BOLD, 10));
        btnNewButton.setBounds(376, 318, 200, 10);
        contentPane.add(btnNewButton);
        refresh(user_name);
    }

    public void refresh(String un) {
        JButton btnNewButton = new JButton("REFRESH");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    // For giving a refreshing effect by deleting the screen that existed before
                    setVisible(false);
                    // revalidate();
                    showdetails(un);
                    setVisible(true);
                } catch (Exception e1) {

                }
            }
        });
        btnNewButton.setFont(new Font("Serif", Font.BOLD, 10));
        btnNewButton.setBounds(376, 338, 200, 10);
        contentPane.add(btnNewButton);
    }
}
