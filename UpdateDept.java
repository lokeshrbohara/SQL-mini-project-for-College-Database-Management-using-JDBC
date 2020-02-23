import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class UpdateDept extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateDept frame = new UpdateDept("","","");
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
	public UpdateDept(String name,String building,String budget) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("DEPARTMENT NAME");
		label.setFont(new Font("Courier New", Font.BOLD, 18));
		label.setBounds(52, 43, 190, 26);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("BUILDING");
		label_1.setFont(new Font("Courier New", Font.BOLD, 18));
		label_1.setBounds(52, 90, 190, 26);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("BUDGET");
		label_2.setFont(new Font("Courier New", Font.BOLD, 18));
		label_2.setBounds(52, 137, 190, 26);
		contentPane.add(label_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(289, 45, 257, 24);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(289, 90, 257, 26);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(289, 137, 257, 24);
		contentPane.add(textField_2);
		
		JButton button = new JButton("SAVE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Update(textField.getText());
			}
		});
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Courier New", Font.BOLD, 18));
		button.setBackground(Color.CYAN);
		button.setBounds(289, 201, 105, 26);
		contentPane.add(button);
		
		JButton button_1 = new JButton("CANCEL");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new window().setVisible(true);
				UpdateDept.this.setVisible(false);
			}
		});
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("Courier New", Font.BOLD, 18));
		button_1.setBackground(Color.CYAN);
		button_1.setBounds(441, 201, 105, 26);
		contentPane.add(button_1);
		
		textField.setText(name);
		textField_1.setText(building);
		textField_2.setText(budget);
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

	
	
	private void Update(String id)
	{
		Connection con=con();
		try {
			String query="update department set dept_name=?,building=?,budget=? where dept_name=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, textField.getText());
			ps.setString(2, textField_1.getText());
			ps.setString(3, textField_2.getText());
			ps.setString(4, id);
			ps.execute();
			ps.close();
			con.close();
			JOptionPane.showMessageDialog(null,"UPDATE SUCCCESSFULL");
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
	}
}
