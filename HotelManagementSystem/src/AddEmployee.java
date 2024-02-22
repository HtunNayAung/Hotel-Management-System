import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class AddEmployee extends JFrame implements ActionListener{
    JTextField nameField, ageField, salaryField, phoneField, emailField;
    JButton submitButton;
    JRadioButton maleRBtn, femaleRBtn;
    JDateChooser calendar;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    JComboBox jobCBox ;
    public AddEmployee(){
        setSize(1280, 720);
        setLocation(100,100);

        ImageIcon backgroundImg =  new ImageIcon(ClassLoader.getSystemResource("images/dashboard.jpeg"));
        JLabel background= new JLabel(backgroundImg);
        add(background);
        
        JMenuBar menuBar = new JMenuBar();
        
        menuBar.setLocation(0, 0);
        menuBar.setSize(1280, 35);
        background.add(menuBar);

        JMenu hotelMng =  new JMenu("Hotel Management");
        hotelMng.setForeground(Color.BLACK);
        menuBar.add(hotelMng);

        JMenuItem reception = new JMenuItem("    Reception    ");
        reception.addActionListener(this);
        hotelMng.add(reception);

        JMenu admin =  new JMenu("          Admin          ");
        admin.setForeground(Color.BLACK);
        menuBar.add(admin);

        JMenuItem employees = new JMenuItem("Add Employees");
        employees.addActionListener(this);
        admin.add(employees);

        JMenuItem rooms = new JMenuItem("Add Rooms");
        rooms.addActionListener(this);
        admin.add(rooms);

        JPanel panel = new JPanel();
        panel.setLocation(400, 100);
        panel.setSize(490, 450);
        panel.setLayout(null);

        //name
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setLocation(75, 30);
        nameLabel.setSize(120, 30);
        nameLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setColumns(100);
        nameField.setLocation(165, 30);
        nameField.setSize(200, 30);
        panel.add(nameField);

        //age
        JLabel ageLabel = new JLabel("DOB");
        ageLabel.setLocation(75, 80);
        ageLabel.setSize(120, 30);
        ageLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(ageLabel);

        calendar = new JDateChooser();
        calendar.setLocation(165, 80);
        calendar.setSize(200, 30);
        panel.add(calendar);

        //gender
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setLocation(75, 130);
        genderLabel.setSize(120, 30);
        genderLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(genderLabel);

        maleRBtn = new JRadioButton("Male");
        maleRBtn.setLocation(165, 130);
        maleRBtn.setSize(70,30);
        maleRBtn.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(maleRBtn);

        femaleRBtn = new JRadioButton("Female");
        femaleRBtn.setLocation(250, 130);
        femaleRBtn.setSize(100,30);
        femaleRBtn.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(femaleRBtn);


        //job
        JLabel jobLabel = new JLabel("Job");
        jobLabel.setLocation(75, 180);
        jobLabel.setSize(120, 30);
        jobLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(jobLabel);

        String[] jobList = { "Manager", "Assistant Manager", "Chef", "Front Desk Clerk", "Housekeeping" , "Kitchen Hand",  "Floor Staff", "Driver", "Barista", "Cleaner"};
        jobCBox = new JComboBox<String>(jobList);
        jobCBox.setLocation(165, 180);
        jobCBox.setSize(180, 30);
        panel.add(jobCBox);

        //salary
        JLabel salaryLabel = new JLabel("Salary");
        salaryLabel.setLocation(75, 230);
        salaryLabel.setSize(120, 30);
        salaryLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(salaryLabel);

        salaryField = new JTextField(100);
        salaryField.setLocation(165, 230);
        salaryField.setSize(200, 30);
        panel.add(salaryField);

        //phone
        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setLocation(75, 280);
        phoneLabel.setSize(120, 30);
        phoneLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(phoneLabel);

        phoneField = new JTextField(100);
        phoneField.setLocation(165, 280);
        phoneField.setSize(200, 30);
        panel.add(phoneField);

        //email
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setLocation(75, 330);
        emailLabel.setSize(120, 30);
        emailLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(emailLabel);

        emailField = new JTextField(100);
        emailField.setLocation(165, 330);
        emailField.setSize(200, 30);
        panel.add(emailField);

        submitButton = new JButton("<html><span style='font-size:14px; font-family: serif'>Submit</span></html>");
        submitButton.setLocation(175,390);
        submitButton.setSize(120,35);
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        submitButton.setBackground(Color.decode("#424554"));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);
        panel.add(submitButton);



        background.add(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Add Employees")){
            new AddEmployee();
        } else if(e.getActionCommand().equals("Add Rooms")){
            new AddRoom();
        } else if(e.getSource() == submitButton){
            String name = nameField.getText();

            String dob = calendar.getDate()==null ? "" : sdf.format(calendar.getDate());
            
            double salary = 0;
            if(!salaryField.getText().equals("")){
                try {
                    salary = Double.parseDouble(salaryField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid salary value: " + salaryField.getText());
                }
            }
            String phone = phoneField.getText();
            
            boolean emailError = true;
            String email = "";
            if(emailField.getText().contains("@") && emailField.getText().contains(".com")){
                email = emailField.getText();
                emailError = false;
            }

            String gender = "Not Preferred";
            if(maleRBtn.isSelected()){
                gender = "male";
            } else if(femaleRBtn.isSelected()){
                gender = "female";
            }
            String job = (String)jobCBox.getSelectedItem();
            if(name != "" && dob != "" && salary != 0 && phone != "" && email != "" && job != ""){
                String query = "insert into employee values('" + name + "','" + dob + "','" + gender + "','" + job + "','" + salary + "','" + phone + "','" + email + "');";
                Dbconnect dbconnect = new Dbconnect();
                try{
                    
                    Statement stmt = dbconnect.con.createStatement();
                    stmt.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Employee added successfully");
                    setVisible(false);

                } catch (Exception err){
                    System.out.println(err);
                }
            } else{
                if(emailError){
                    JOptionPane.showMessageDialog(null, "Incorrect email format");
                }
                JOptionPane.showMessageDialog(null, "All fields must be filled");

            }
        }

        

    }
}