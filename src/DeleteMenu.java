
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class DeleteMenu extends JFrame implements ActionListener {
    JLabel l1;
    JTextField t1;
    JButton b1,b2,b3;
    public DeleteMenu() {
        l1=new JLabel("Registration No.");
        t1=new JTextField(1);
        b1=new JButton("Back");
        b2=new JButton("Delete");
        b3=new JButton("Clear");
        setTitle("Student Management System");
        setSize(700,700);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l1.setFont(new Font("Comic Sans MS",Font.BOLD,30));
        l1.setBounds(20,100,250,100);
        t1.setBounds(350,125,300,40);
        b1.setBounds(120,350,100,50);
        b2.setBounds(320,350,100,50);
        b3.setBounds(520,350,100,50);
        add(l1);
        add(t1);
        add(b1);
        add(b2);
        add(b3);
        t1.setToolTipText("Enter Registration No.");
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
    }
   

    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(b1)) {
            setVisible(false);
            UserMenu b=new UserMenu();
            b.setVisible(true);
        } 
        else if(e.getSource().equals(b3)) {
            t1.setText("");
        }
        else if(e.getSource().equals(b2)) {
            if(t1.getText().isEmpty())
                JOptionPane.showMessageDialog(this,"Enter Registration No.");
            else{
                String regd=t1.getText();
                DatabaseConnection obj=new DatabaseConnection();
                if(obj.delStudent(regd)==1) {
                    JOptionPane.showMessageDialog(this,"Record Deleted Successfully");
                    t1.setText("");
                } 
                else {
                     JOptionPane.showMessageDialog(this,"No records Found");
                }
             
            }
        }
    }
}
    

