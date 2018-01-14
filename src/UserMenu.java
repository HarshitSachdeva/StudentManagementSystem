
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UserMenu extends JFrame implements ActionListener {
    JButton b1,b2,b3,b4,b5;
    ImageIcon i1;
    JLabel l1;
    
    public UserMenu() {
        b1=new JButton("Enter a new Record");
        b2=new JButton("Delete a Record");
        b3=new JButton("View a  Record");
        b4=new JButton("Update a Record");
        i1= new ImageIcon("src\\images\\sms.png");
        l1=new JLabel(i1);
        b5=new JButton("Logout"); 
        setTitle("Student Management System");
        setSize(700,700);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(l1);
        add(b5);
        b1.setForeground(Color.white);
        b1.setBackground(Color.black);
        b2.setBackground(Color.black);
        b2.setForeground(Color.white);
        b3.setBackground(Color.black);
        b3.setForeground(Color.white);
        b4.setBackground(Color.black);
        b4.setForeground(Color.white);
        b1.setBounds(125,200,150,150);
        b2.setBounds(400,200,150,150);
        b3.setBounds(125,400,150,150);
        b4.setBounds(400,400,150,150);
        l1.setBounds(20,10,300,150);
        b5.setBounds(550,10,100,50);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
    
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(b1)) {
            setVisible(false);
            InsertMenu d=new InsertMenu();
            d.setVisible(true);
        }
        if(e.getSource().equals(b2)) {
            setVisible(false);
            DeleteMenu f=new DeleteMenu();
            f.setVisible(true);   
        }
        if(e.getSource().equals(b3)) {
            setVisible(false);
            ViewMenu g=new ViewMenu();
            g.setVisible(true);   
        }
        if(e.getSource().equals(b3)) {
            setVisible(false);
            ViewMenu g=new ViewMenu();
            g.setVisible(true);   
        }
        if(e.getSource().equals(b4)) {
            setVisible(false);
            ViewForUpdate h=new ViewForUpdate();
            h.setVisible(true);   
        }
        if(e.getSource().equals(b5)) {
            setVisible(false);
            LoginPage a=new LoginPage();
            a.setVisible(true);
        }
    
    }     
}
    
    



    
    

