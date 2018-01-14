import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage extends JFrame implements ActionListener {
    JLabel l1,l2,l3;
    JTextField tf1;
    JPasswordField pf;
    JButton b1,b2;
    ImageIcon d;
    
    public LoginPage() {
        l1=new JLabel("User Name");
        l2=new JLabel("Password"); 
        d=new ImageIcon("src\\images\\sms.png");
        l3=new JLabel(d);
        tf1=new JTextField(1);
        pf=new JPasswordField(1);
        b1=new JButton("Login");
        b2=new JButton("New User");
        setTitle("Student Management System");
        setSize(700,700);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(null);
        l1.setFont(new Font("Comic Sans MS",Font.BOLD,30));
        l2.setFont(new Font("Comic Sans MS",Font.BOLD,30));
        tf1.setToolTipText("Please Enter User name");
        pf.setToolTipText("Enter Password");
        l1.setBounds(50,50,400,300);
        tf1.setBounds(350,185,200,30);
        l2.setBounds(50,150, 400,300);
        pf.setBounds(350,290,200,30);
        b1.setBounds(250,400,70,20);
        b2.setBounds(400,400,100,20);
        l3.setBounds(220,0,280,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(l1);
        add(l2);
        add(tf1);
        add(pf);
        add(b1);
        add(b2);
        add(l3);
        b1.addActionListener(this);
        b2.addActionListener(this);
        setResizable(false);
    }
    
    public static void main(String args[]) {
       new LoginPage();
    }    

    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(b1)) {
            if(tf1.getText().isEmpty() || pf.getPassword().length==0) {
                JOptionPane.showMessageDialog(this, "Both fields required.");
            } else {
                DatabaseConnection ee=new DatabaseConnection();
                String username = tf1.getText();
                String password = String.valueOf(pf.getPassword());
                if(ee.validateUser(username, password) == 1) {
                    setVisible(false);
                    UserMenu b=new UserMenu();
                    b.setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(this, "Invalid Username or password.");
                    pf.setText("");
                }
            }
        }
        else if(e.getSource().equals(b2)) {
           setVisible(false);
           NewUserLoginPage c=new NewUserLoginPage();
           c.setVisible(true);
            
        }
            
    }
}