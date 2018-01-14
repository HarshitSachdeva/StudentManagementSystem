
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class UpdateMenu extends JFrame implements ActionListener{
    
    
    JLabel l1,l2,l3,l4,l5,l6,l7,l8;
    JTextField t1,t2,t3,t4,t5;
    JComboBox c1;
    JTextArea ta1;
    JButton b1,b2,b3;
    JDateChooser dc;
    
    public UpdateMenu(ResultSet rs) {
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
                String branch[]={"Select", "Computer Science", "Mechanical", "Electrical", "Electronic", "Civil"};
                c1=new JComboBox(branch);
                c1.setSelectedItem(rs.getString(6));
                l6=new JLabel("Address"); 
                ta1=new JTextArea(rs.getString(7), 1, 1);
                l7=new JLabel("Mobile No.");
                t5=new JTextField(rs.getString(8),1);
                l8=new JLabel("Date of Birth");
                java.util.Date utilDate = new java.util.Date(rs.getDate(9).getTime());
                dc=new JDateChooser(utilDate);
                JTextFieldDateEditor editor=(JTextFieldDateEditor) dc.getDateEditor();
                editor.setEditable(false);
                
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        b1=new JButton("Update");
        b2=new JButton("Clear");
        b3=new JButton("Back");
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
        c1.setBounds(180,310,300,30);
        l6.setBounds(10,310,150,150);
        ta1.setBounds(180,370,300,80);
        l7.setBounds(10,410,150,150);
        t5.setBounds(180,470,300,30);
        l8.setBounds(10,500,150,150);
        dc.setBounds(180,560,300,30);
        b2.setBounds(100,630,80,30);
        b1.setBounds(250,630,80,30);
        b3.setBounds(400,630,80,30);
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
        t2.setEditable(false);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this); 
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
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
        else if(e.getSource().equals(b3)) {
            setVisible(false);
            ViewForUpdate h=new ViewForUpdate();
            h.setVisible(true);
        } 
        else if(e.getSource().equals(b1)) {
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
                    if(obj.updateStudent(name,regd,fname,mname,br,add,mob,sqlDate)==1) {
                        JOptionPane.showMessageDialog(this,"Details updated successfully");
                        setVisible(false);
                        new ViewForUpdate().setVisible(true);
                    }
                }
            }
        }
    }
}