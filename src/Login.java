import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Image;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Login extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    // public static void main(String[] args) {
    // EventQueue.invokeLater(new Runnable() {
    // public void run() {
    // try {
    // Login frame = new Login();
    // frame.setVisible(true);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
    // });
    // }

    public Login() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("/images/img9.jpg"));
        setBounds(450, 319, 1014, 460);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Usertype");
        lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblNewLabel.setBounds(59, 25, 145, 32);
        // contentPane.add(lblNewLabel);
        // JTextArealoginname = new JTextArea();
        // loginname.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        // loginname.setBounds(235, 26, 166, 37);
        // contentPane.add(logintype);
        JRadioButton rdbtnNewRadioButton = new JRadioButton("Supervisor");
        rdbtnNewRadioButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        rdbtnNewRadioButton.setBounds(59, 60, 138, 32);
        // contentPane.add(rdbtnNewRadioButton);

        JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Associate");
        rdbtnNewRadioButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        rdbtnNewRadioButton_1.setBounds(59, 90, 138, 26);
        // contentPane.add(rdbtnNewRadioButton_1);
        // ButtonGroup btg = new ButtonGroup();
        // btg.add(rdbtnNewRadioButton_1);
        // btg.add(rdbtnNewRadioButton);

        JLabel pswrdLabel = new JLabel("Username");
        pswrdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        pswrdLabel.setBounds(100, 150, 145, 32);
        contentPane.add(pswrdLabel);
        JTextArea loginname = new JTextArea();
        loginname.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        loginname.setBounds(235, 150, 166, 37);
        contentPane.add(loginname);
        JLabel pswrd = new JLabel("Password");
        pswrd.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        pswrd.setBounds(100, 250, 145, 32);
        contentPane.add(pswrd);
        JTextArea login = new JTextArea();
        login.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        login.setBounds(235, 250, 166, 37);
        contentPane.add(login);
        JButton btnNewButton = new JButton("Login");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int f = 0;
                    System.out.println("add");

                    Class.forName("com.mysql.jdbc.Driver");

                    Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",
                            "root", "password");

                    String un = loginname.getText();
                    String password = login.getText();

                    Statement statement = con.createStatement();
                    // TODO add a column employee/supervisor in Database
                    ResultSet resultSet = statement
                            .executeQuery("select password,desg from login  where username='" + un + "'");
                    while (resultSet.next()) {
                        // System.out.println(resultSet.getString(1));
                        String p = (String) resultSet.getString(1);
                        String d = (String) resultSet.getString(2);
                        // System.out.println(password.equals(p));
                        if (password.equals(p)) {

                            if (d.equals("ED")) {
                                JOptionPane.showMessageDialog(btnNewButton, "Logged in");
                                Associate A = new Associate();
                                A.setVisible(true);
                                A.showdetails(un);
                            } else if (d.equals("SD")) {
                                JOptionPane.showMessageDialog(btnNewButton, "Logged in");
                                Supervisor S = new Supervisor();
                                S.setVisible(true);
                                S.getdata(un);
                                S.showOptions();
                            }

                        } else {
                            JOptionPane.showMessageDialog(btnNewButton,
                                    "Access Denied");
                        }
                        f = 1;
                    }
                    if (f == 0)
                        JOptionPane.showMessageDialog(btnNewButton,
                                "Invalid Username");

                } catch (Exception w1) {
                    System.out.println(w1);
                }

            }
        });
        btnNewButton.setFont(new Font("Serif", Font.BOLD, 30));
        btnNewButton.setBounds(376, 318, 197, 80);
        contentPane.add(btnNewButton);
    }
}