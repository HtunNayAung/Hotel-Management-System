import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
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
    JButton submitButton, backButton;
    JRadioButton maleRBtn, femaleRBtn;
    JDateChooser calendar;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    JComboBox jobCBox;
    Choice departmentBox;
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

        JMenuItem drivers = new JMenuItem("Add Drivers");
        drivers.addActionListener(this);
        admin.add(drivers);

        JPanel panel = new JPanel();
        panel.setLocation(400, 100);
        panel.setSize(490, 500);
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

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRBtn);
        genderGroup.add(femaleRBtn);

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


        //department
        JLabel departmentLabel = new JLabel("Department");
        departmentLabel.setLocation(75, 380);
        departmentLabel.setSize(120, 30);
        departmentLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(departmentLabel);

        departmentBox = new Choice();
        try {   
            Dbconnect dbconnect = new Dbconnect();
            Statement stmt = dbconnect.con.createStatement();
            String query = "select * from department";
            ResultSet resultSet = stmt.executeQuery(query);
            while(resultSet.next()){
                departmentBox.add(resultSet.getString("name"));
                
            }
            dbconnect.con.close();
        } catch (Exception e) {
            
        }
        departmentBox.setLocation(180, 380);
        departmentBox.setSize(180, 30);
        panel.add(departmentBox);

        submitButton = new JButton("<html><span style='font-size:14px; font-family: serif'>Submit</span></html>");
        submitButton.setLocation(90,440);
        submitButton.setSize(120,35);
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        submitButton.setBackground(Color.decode("#424554"));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);
        panel.add(submitButton);

        backButton = new JButton("<html><span style='font-size:14px; font-family: serif'>Back</span></html>");
        backButton.setLocation(250,440);
        backButton.setSize(120,35);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setBackground(Color.decode("#424554"));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        panel.add(backButton);




        background.add(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Add Employees")){
            setVisible(false);
            new AddEmployee();
        } else if(e.getActionCommand().equals("Add Rooms")){
            setVisible(false);
            new AddRoom();
        }  else if(e.getActionCommand().equals("Add Drivers")){
            setVisible(false);
            new PickUpService();
        } else if(e.getActionCommand().equals("    Reception    ")){
            setVisible(false);
            new Reception();
        } else if(e.getSource() == backButton){
            setVisible(false);
            new Reception();
        } else if(e.getSource() == submitButton){
            String name = nameField.getText();

            String dob = calendar.getDate()== null ? "" : sdf.format(calendar.getDate());
            
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
            if(emailField.getText().contains("@imperial.com.au")){
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
            String department = departmentBox.getSelectedItem();
            if(name != "" && dob != "" && salary != 0 && phone != "" && email != "" && job != ""){
                String query = "INSERT INTO employee (name, dob, gender, job, salary, phone, email, department) " +
                "VALUES ('" + name + "','" + dob + "','" + gender + "','" + job + "','" + salary + "','" + phone + "','" + email + "','" + department + "')";
                Dbconnect dbconnect = new Dbconnect();
                try{
                    
                    Statement stmt = dbconnect.con.createStatement();
                    stmt.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Employee added successfully");
                    setVisible(false);
                    dbconnect.con.close();
                    new AddEmployee();
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