
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class NewUserLoginPage extends JFrame implements ActionListener {
    
    JLabel l1,l2,l3;
    JTextField t1;
    JPasswordField p1,p2;
    JButton b1,b2,b3;
    
    public NewUserLoginPage() {
        l1=new JLabel("Username");
        l2=new JLabel("Password");
        l3=new JLabel("Re-Type Password");
        t1=new JTextField(1);
        p1=new JPasswordField(1);
        p2=new JPasswordField(1);
        b1=new JButton("Submit");
        b2=new JButton("Back");
        b3=new JButton("Clear");
        setTitle("Student Management System");
        setSize(700,700);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l1.setFont(new Font("Comic Sans MS",Font.BOLD,30));
        l2.setFont(new Font("Comic Sans MS",Font.BOLD,30));
        l3.setFont(new Font("Comic Sans MS",Font.BOLD,30));
        t1.setToolTipText("Enter User Name");
        p1.setToolTipText("Enter Password");
        p2.setToolTipText("Re-Type Password");
        l1.setBounds(20,100,250,100);
        t1.setBounds(350,125,300,40);
        l2.setBounds(20,200,250,100);
        p1.setBounds(350,225,300,40);
        l3.setBounds(20,300,350,100);
        p2.setBounds(350,325,300,40);
        b1.setBounds(300,450,100,50);
        b2.setBounds(100,450,100,50);
        b3.setBounds(500,450,100,50);
        add(l1);
        add(l2);
        add(l3);
        add(t1);
        add(p1);
        add(p2);
        add(b1);
        add(b2);  
        add(b3);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
    }
    
   
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(b1)) {
            if(t1.getText().isEmpty() || p1.getPassword().length==0 || p2.getPassword().length==0) {
                JOptionPane.showMessageDialog(this,"All fields Required");
            } 
            else { 
                String username = t1.getText();
                String password = String.valueOf(p1.getPassword());
                String pass = String.valueOf(p2.getPassword());
                String uname="^[a-zA-Z0-9\\s]{3,35}$";
                Pattern p=Pattern.compile(uname);
                Matcher m=p.matcher(username);
                if(!m.matches())
                    JOptionPane.showMessageDialog(this,"Please enter correct Username");
                
                else if(password.equals(pass)) {
                    DatabaseConnection ob=new DatabaseConnection();
                    int res = ob.addUser(username, password);
                    if(res ==1) {
                        JOptionPane.showMessageDialog(this," Password matched & Registered Successfully");
                        setVisible(false);
                        LoginPage a=new LoginPage();
                        a.setVisible(true);
                    } else if (res ==2) {
                        JOptionPane.showMessageDialog(this, "Username already exists.");
                        p1.setText("");
                        p2.setText("");
                        
                    } else {
                        JOptionPane.showMessageDialog(this, "Couldn't save. Please try again later.");
                    }
                }
                else { 
                    JOptionPane.showMessageDialog(this,"Password doesn't Match");
                }   
            }
        }
    
        else if(e.getSource().equals(b2)) {
            setVisible(false);
            LoginPage b=new LoginPage();
            b.setVisible(true);
        }
        else if(e.getSource().equals(b3)) {
            t1.setText("");
            p1.setText("");
            p2.setText("");
        }
    }
}