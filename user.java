import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class user extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					user frame = new user();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public user() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 669, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(12, 13, 632, 316);
		contentPane.add(panel);
		
		JButton button = new JButton("SAVE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveToDatabase();
				user.this.setVisible(false);
				
			}
			
			
		});
		button.setFont(new Font("Courier New", Font.BOLD, 18));
		button.setBackground(Color.CYAN);
		button.setBounds(331, 217, 111, 37);
		panel.add(button);
		
		JButton button_1 = new JButton("CANCEL");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				user.this.setVisible(false);
				new login().setVisible(true);
			}
		});
		button_1.setFont(new Font("Courier New", Font.BOLD, 18));
		button_1.setBackground(Color.CYAN);
		button_1.setBounds(486, 217, 111, 37);
		panel.add(button_1);
		
		JLabel lblUserId = new JLabel("USER ID");
		lblUserId.setFont(new Font("Courier New", Font.BOLD, 18));
		lblUserId.setBackground(Color.CYAN);
		lblUserId.setBounds(40, 36, 201, 32);
		panel.add(lblUserId);
		
		JLabel lblUserName = new JLabel("USER NAME");
		lblUserName.setFont(new Font("Courier New", Font.BOLD, 18));
		lblUserName.setBackground(Color.CYAN);
		lblUserName.setBounds(40, 88, 201, 25);
		panel.add(lblUserName);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(253, 38, 355, 32);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(253, 89, 355, 32);
		panel.add(textField_1);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Courier New", Font.BOLD, 18));
		lblPassword.setBackground(Color.CYAN);
		lblPassword.setBounds(40, 136, 201, 32);
		panel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(253, 146, 355, 32);
		panel.add(passwordField);
	}
	static Connection con()
	{
		try {
			String driver="com.mysql.jdbc.Driver";
			String url="jdbc:mysql://localhost/college";
			Class.forName(driver);
			return DriverManager.getConnection(url,"root","12345678");
		}
		catch(Exception e) {
			System.out.println("Connection Not done"+e);
		}
		return null;
	}
	private void SaveToDatabase()
	{
		Connection con=con();
		try {
			String query="insert into test values(?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,textField.getText() );
			ps.setString(2, textField_1.getText());
			ps.setString(3, passwordField.getText());
			ps.execute();
			JOptionPane.showMessageDialog(null, "DATA SAVED TO DATABASE");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
}
