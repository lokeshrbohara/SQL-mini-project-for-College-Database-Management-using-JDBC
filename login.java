import java.awt.EventQueue;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JPasswordField pass;
	public int w;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public login() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 461, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon("C:\\Users\\91877\\eclipse-workspace\\MYPROJECT\\image\\labell.png"));
		label.setBounds(31, 23, 65, 69);
		contentPane.add(label);
		
		JLabel lblFcritVashi = new JLabel("FATHER AGNELS VASHI");
		lblFcritVashi.setHorizontalAlignment(SwingConstants.CENTER);
		lblFcritVashi.setForeground(Color.BLACK);
		lblFcritVashi.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblFcritVashi.setBounds(108, 23, 315, 69);
		contentPane.add(lblFcritVashi);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(132, 85, 157, 81);
		contentPane.add(lblLogin);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblUsername.setBounds(41, 293, 100, 16);
		contentPane.add(lblUsername);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("C:\\Users\\91877\\eclipse-workspace\\MYPROJECT\\image\\user.png"));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(120, 149, 193, 145);
		contentPane.add(label_1);
		
		username = new JTextField();
		username.setBounds(41, 311, 366, 36);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblPassword.setBounds(41, 360, 100, 16);
		contentPane.add(lblPassword);
		
		pass = new JPasswordField();
		pass.setBounds(41, 376, 366, 43);
		contentPane.add(pass);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\91877\\eclipse-workspace\\MYPROJECT\\image\\ok.png"));

		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/college","root","12345678");
					Statement stmt=con.createStatement();
					String sql="Select * from test where username='"+username.getText()+"' and password='"+pass.getText().toString()+"'";
					ResultSet rs=stmt.executeQuery(sql);
					if(rs.next())
						{
						JOptionPane.showMessageDialog(null,"LOGIN SUCCESSFULLY");
						w=1;
						new window().setVisible(true);
						}
					else
						{
						JOptionPane.showMessageDialog(null,"INCORRECT USERNAME OR PASSWORD");
						w=0;
						}
					con.close();
				}
				catch(Exception u){
					System.out.print(u);
					
				}
				DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
				Calendar calobj = Calendar.getInstance();
				String b=df.format(calobj.getTime());
				String a=username.getText();
				String c;
				if(w==1)
					c="LOGIN WAS SUCCESSFUL";
				else
					c="LOGIN FAILED";
				try {
				
					BufferedWriter out = new BufferedWriter( 
			                   new FileWriter("File/logs.txt", true)); 
			            out.write(a+' '+b+' '+c); 
			            out.append('\n');
			            out.close(); 
			           
				}
				catch(IOException q)
				{
					System.out.println(q);
				}
			}
		});
		btnNewButton.setBounds(41, 458, 112, 28);
		contentPane.add(btnNewButton);
		
		JButton btnCreateUser = new JButton("CREATE USER");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				login.this.setVisible(false);
				new user().setVisible(true);
			}
		});
		btnCreateUser.setBackground(new Color(0, 255, 255));
		btnCreateUser.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnCreateUser.setBounds(270, 459, 137, 26);
		contentPane.add(btnCreateUser);
	}
}
