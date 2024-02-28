import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class AddCustomer extends JFrame implements ActionListener{
    JComboBox idCBox, countryCBox;
    JTextField idNumField, nameField, checkinField;
    JRadioButton maleRBtn, femaleRBtn;
    Choice allocatedRoomBox;
    JButton addButton, cancelButton;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public AddCustomer(){
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
        panel.setSize(490, 460);
        panel.setLayout(null);

        //name
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setLocation(75, 30);
        nameLabel.setSize(120, 30);
        nameLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setColumns(100);
        nameField.setLocation(200, 30);
        nameField.setSize(200, 30);
        panel.add(nameField);

        //id type
        JLabel idLabel = new JLabel("ID Type");
        idLabel.setLocation(75, 80);
        idLabel.setSize(120, 30);
        idLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(idLabel);

        String[] idList = {"Passport", "Driving License", "Photo Card", "Medicare Card"};
        idCBox = new JComboBox<String>(idList);
        idCBox.setLocation(200, 80);
        idCBox.setSize(180, 30);
        panel.add(idCBox);

        //id number
        JLabel idNumLabel = new JLabel("ID Number");
        idNumLabel.setLocation(75, 130);
        idNumLabel.setSize(120, 30);
        idNumLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(idNumLabel);

        idNumField = new JTextField();
        idNumField.setColumns(100);
        idNumField.setLocation(200, 130);
        idNumField.setSize(200, 30);
        panel.add(idNumField);

        //gender
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setLocation(75,180);
        genderLabel.setSize(120, 30);
        genderLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(genderLabel);

        maleRBtn = new JRadioButton("Male");
        maleRBtn.setLocation(200, 180);
        maleRBtn.setSize(70,30);
        maleRBtn.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(maleRBtn);

        femaleRBtn = new JRadioButton("Female");
        femaleRBtn.setLocation(285, 180);
        femaleRBtn.setSize(100,30);
        femaleRBtn.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(femaleRBtn);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRBtn);
        genderGroup.add(femaleRBtn);

        //country
        JLabel countryLabel = new JLabel("Country");
        countryLabel.setLocation(75, 230);
        countryLabel.setSize(120, 30);
        countryLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(countryLabel);

        String[] countriesList = {
        "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria",
        "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan",
        "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor (Timor-Leste)", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar (Burma)", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "North Macedonia", "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines",
        "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States of America", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe" };
        countryCBox = new JComboBox<String>(countriesList);
        countryCBox.setLocation(200, 230);
        countryCBox.setSize(180, 30);
        panel.add(countryCBox);

        //allocated room number
        JLabel allocatedLabel = new JLabel("Allocated room");
        allocatedLabel.setLocation(75, 280);
        allocatedLabel.setSize(120, 30);
        allocatedLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(allocatedLabel);

        allocatedRoomBox = new Choice();
        try {   
            Dbconnect dbconnect = new Dbconnect();
            Statement stmt = dbconnect.con.createStatement();
            String query = "select * from room where availability = 'Available'";
            ResultSet resultSet = stmt.executeQuery(query);
            while(resultSet.next()){
                allocatedRoomBox.add(resultSet.getString("roomNumber") + " ("+resultSet.getString("roomType") + ")");
            }
            dbconnect.con.close();
        } catch (Exception e) {
            
        }
        allocatedRoomBox.setLocation(200, 280);
        allocatedRoomBox.setSize(180, 30);
        panel.add(allocatedRoomBox);


        JLabel checkinLabel = new JLabel("Checkin Time");
        checkinLabel.setLocation(75, 330);
        checkinLabel.setSize(120, 30);
        checkinLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(checkinLabel);

        checkinField = new JTextField();
        checkinField.setColumns(100);
        checkinField.setLocation(200, 330);
        checkinField.setSize(200, 30);
        checkinField.setText(sdf.format(new Date()));
        panel.add(checkinField);

        //for login button
        addButton = new JButton("<html><span style='font-size:14px; font-family: serif'>Add</span></html>");
        addButton.setLocation(100,390);
        addButton.setSize(120,30);
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);
        addButton.setBackground(Color.decode("#424554"));
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(this);
        panel.add(addButton);

        //for cancel button
        cancelButton = new JButton("<html><span style='font-size:14px; font-family: serif'>Cancel</span></html>");
        cancelButton.setLocation(250,390);
        cancelButton.setSize(120,30);
        cancelButton.setOpaque(true);
        cancelButton.setBorderPainted(false);
        cancelButton.setBackground(Color.decode("#424554"));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(this);
        panel.add(cancelButton);

        

        background.add(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e){
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
        } else if(e.getSource() == addButton){
            String name = nameField.getText();
            String idType = (String)idCBox.getSelectedItem();
            String idNum = idNumField.getText();
            String gender = "";
            if(maleRBtn.isSelected()){
                gender = "male";
            } else if(femaleRBtn.isSelected()){
                gender = "female";
            }
            String country = (String)countryCBox.getSelectedItem();
            String allocatedRoom = allocatedRoomBox.getSelectedItem();
            String room = allocatedRoom.substring(0,3);
            String checkinTime = checkinField.getText();
            if(!name.equals("") && !idNum.equals("") && gender != null){
                try {
                    Dbconnect dbconnect = new Dbconnect();
                    Statement stmt = dbconnect.con.createStatement();
                    String query = "insert into customer values ('" + name + "','" +idType + "','" + idNum + "','" + gender + "','" + country + "','" + room + "','" + checkinTime + "');";
                    String availUpdateQuery = "update room set availability = 'Occupied' where roomNumber  = '" + room + "';";
                    String stayQuery = "insert into staying values ('" + name + "','" + room + "');";
                    stmt.executeUpdate(query);
                    stmt.executeUpdate(availUpdateQuery);
                    stmt.executeUpdate(stayQuery);
                    JOptionPane.showMessageDialog(null, "New customer added successfully");
                    setVisible(false);
                    new AddCustomer();
                    dbconnect.con.close();
                } catch (Exception err) {
                    System.err.println(err);
                }
            } else{
                JOptionPane.showMessageDialog(null, "All fields must be filled");
            }
            
        } else if(e.getSource() == cancelButton){
            setVisible(false);
            new Reception();
        }
    }
}
