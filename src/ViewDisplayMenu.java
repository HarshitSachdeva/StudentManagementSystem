
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ViewDisplayMenu extends JFrame implements ActionListener  {
    JLabel l1,l2,l3,l4,l5,l6,l7,l8;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    JTextArea ta1;
    JButton b1;
    public ViewDisplayMenu(ResultSet rs) {
        try {
            while(rs.next()) {
                l1=new JLabel("Student Name");
                t1=new JTextField(rs.getString(2), 1);
                l2=new JLabel(" Registration No.");
                t2=new JTextField(rs.getString(3), 1);
                l3=new JLabel("Father's Name");
                t3=new JTextField(rs.getString(4), 1);
                l4=new JLabel("Mother's Name");
                t4=new JTextField(rs.getString(5), 1);
                l5=new JLabel("Branch");
                t5=new JTextField(rs.getString(6),1);
                l6=new JLabel("Address"); 
                ta1=new JTextArea(rs.getString(7), 1, 1);
                l7=new JLabel("Mobile No.");
                t6=new JTextField(rs.getString(8),1);
                l8=new JLabel("Date of Birth");
                t7=new JTextField(""+rs.getDate(9),1);
                
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        b1=new JButton("Back");
        ta1.setLineWrap(true);
        setTitle("Student Management System");
        setSize(700,700);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        l1.setBounds(10,10,150,150);
        t1.setBounds(180,70,300,30);
        l2.setBounds(10,70,150,150);
        t2.setBounds(180,130,300,30);
        l3.setBounds(10,130,150,150);
        t3.setBounds(180,190,300,30);
        l4.setBounds(10,190,150,150);
        t4.setBounds(180,250,300,30);
        l5.setBounds(10,250,150,150);
        t5.setBounds(180,310,300,30);
        l6.setBounds(10,310,150,150);
        ta1.setBounds(180,370,300,80);
        l7.setBounds(10,410,150,150);
        t6.setBounds(180,470,300,30);
        l8.setBounds(10,500,150,150);
        t7.setBounds(180,560,300,30);
        b1.setBounds(250,630,80,30);
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);
        add(l4);
        add(t4);
        add(l5);
        add(t6);
        add(l6);
        add(ta1);
        add(l7);
        add(t5);
        add(l8);
        add(t7);
        add(b1);
        t2.setEditable(false);
        t1.setEditable(false);
        t3.setEditable(false);
        t4.setEditable(false);
        ta1.setEditable(false);
        t5.setEditable(false);
        b1.addActionListener(this);
        t6.setEditable(false);
        t7.setEditable(false);
       
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(b1)) {
            setVisible(false);
            ViewMenu g=new ViewMenu();
            g.setVisible(true);
        }
    }
   
}
            
