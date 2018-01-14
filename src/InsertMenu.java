import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InsertMenu extends JFrame implements ActionListener {
    JLabel l1,l2,l3,l4,l5,l6,l7,l8;
    JTextField t1,t2,t3,t4,t5;
    JComboBox c1;
    JTextArea ta1;
    JButton b1,b2,b3;
    JDateChooser dc;
    
    public InsertMenu() {
        l1=new JLabel("Student Name");
        t1=new JTextField(1);
        l2=new JLabel(" Registration No.");
        t2=new JTextField(1);
        l3=new JLabel("Father's Name");
        t3=new JTextField(1);
        l4=new JLabel("Mother's Name");
        t4=new JTextField(1);
        l5=new JLabel("Branch");
        String branch[]={"Select", "Computer Science", "Mechanical", "Electrical", "Electronic", "Civil"};
        c1=new JComboBox(branch);
        l6=new JLabel("Address"); 
        ta1=new JTextArea(1,1);
        l7=new JLabel("Mobile No.");
        t5=new JTextField(1);
        l8=new JLabel("Date of Birth");
        dc=new JDateChooser();
        b1=new JButton("Back");
        b2=new JButton("Clear");
        b3=new JButton("Submit");
        ta1.setLineWrap(true);
        setTitle("Student Management System");
        setSize(700,700);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        l1.setBounds(100,15,150,35);
        t1.setBounds(300,20,300,30);
        l2.setBounds(100,65,150,35);
        t2.setBounds(300,70,300,30);
        l3.setBounds(100,115,150,35);
        t3.setBounds(300,120,300,30);
        l4.setBounds(100,165,150,35);
        t4.setBounds(300,170,300,30);
        l5.setBounds(100,215,150,35);
        c1.setBounds(300,220,300,30);
        l6.setBounds(100,265,150,35);
        ta1.setBounds(300,270,300,80);
        l7.setBounds(100,365,150,35);
        t5.setBounds(300,370,300,30);
        l8.setBounds(100,415,150,35);
        dc.setBounds(300,420,300,35);
        b1.setBounds(150,550,80,30);
        b2.setBounds(300,550,80,30);
        b3.setBounds(450,550,80,30);
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);
        add(l4);
        add(t4);
        add(l5);
        add(c1);
        add(l6);
        add(ta1);
        add(l7);
        add(t5);
        add(l8);
        add(dc);
        add(b1);
        add(b2);
        add(b3);
        JTextFieldDateEditor editor=(JTextFieldDateEditor) dc.getDateEditor();
        editor.setEditable(false);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
    } 
        
    
    public void actionPerformed(ActionEvent e)  {
        boolean flag = true;
        if(e.getSource().equals(b2)) {
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            t5.setText("");
            ta1.setText("");
            dc.setCalendar(null);
            c1.setSelectedItem("Select");
        }
        else if(e.getSource().equals(b1)) {
            setVisible(false);
            UserMenu b=new UserMenu();
            b.setVisible(true);
        }
        else if(e.getSource().equals(b3)) {
            if(t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty() || t4.getText().isEmpty() || t5.getText().isEmpty() ||  ta1.getText().isEmpty() || c1.getSelectedItem().equals("Select") || dc.getDate()==null) {
                JOptionPane.showMessageDialog(this, "All fields are required");  
            }
            else {
                String name=t1.getText();
                String regd=t2.getText();
                String fname=t3.getText();
                String mname=t4.getText();
                String mob=t5.getText();
                String br=c1.getSelectedItem().toString();
                String add=ta1.getText();
                java.util.Date utilDate = dc.getDate();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                String sname="^[a-zA-z\\s]{3,35}$";
                Pattern p=Pattern.compile(sname);
                Matcher m=p.matcher(name);
                if(!m.matches()) {
                    flag = false;
                    JOptionPane.showMessageDialog(this,"Please enter correct student name"); 
                }
                String register= "^[0-9]{11,11}$";
                p=Pattern.compile(register);
                m=p.matcher(regd);
                if(!m.matches() && flag) {
                    flag = false;
                    JOptionPane.showMessageDialog(this,"Please enter correct regd no."); 
                }
                String faname= "^[a-zA-Z\\s]{3,35}$";
                p=Pattern.compile(faname);
                m=p.matcher(fname);
                if(!m.matches() && flag) {
                    flag = false;
                    JOptionPane.showMessageDialog(this,"Please enter correct father name"); 
                }
                String maname= "^[a-zA-Z\\s]{3,35}$";
                p=Pattern.compile(maname);
                m=p.matcher(mname);
                if(!m.matches() && flag) {
                    flag = false;
                    JOptionPane.showMessageDialog(this,"Please enter correct mother name"); 
                }
                if(add.length() > 50) {
                    flag = false;
                    JOptionPane.showMessageDialog(this, "Only 50 characters are allowed for Address field");
                }
                String mobile= "^[0-9]{10,10}$";
                p=Pattern.compile(mobile);
                m=p.matcher(mob);
                if(!m.matches() && flag) {
                    flag = false;
                    JOptionPane.showMessageDialog(this,"Please enter correct Mobile no"); 
                }
                if(flag) {
                    DatabaseConnection obj=new DatabaseConnection();
                    
                    int res = obj.addStudent(name,regd,fname,mname,br,add,mob,sqlDate);
                    if(res == 1) {
                        JOptionPane.showMessageDialog(this,"Details added successfully");
                        t1.setText("");
                        t2.setText("");
                        t3.setText("");
                        t4.setText("");
                        t5.setText("");
                        ta1.setText("");
                        dc.setCalendar(null);
                        c1.setSelectedItem("Select");
                    }
                    else if (res == 2) {
                        JOptionPane.showMessageDialog(this, "Registration no. already exists.");
                    }
                }
            }
        }
    }
    
}