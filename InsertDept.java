import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class InsertDept extends JFrame {

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
					InsertDept frame = new InsertDept();
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
	public InsertDept() {
		setForeground(new Color(0, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 320);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDepartmentName = new JLabel("DEPARTMENT NAME");
		lblDepartmentName.setFont(new Font("Courier New", Font.BOLD, 18));
		lblDepartmentName.setBounds(42, 35, 190, 26);
		contentPane.add(lblDepartmentName);
		
		JLabel lblBuilding = new JLabel("BUILDING");
		lblBuilding.setFont(new Font("Courier New", Font.BOLD, 18));
		lblBuilding.setBounds(42, 82, 190, 26);
		contentPane.add(lblBuilding);
		
		JLabel lblBudget = new JLabel("BUDGET");
		lblBudget.setFont(new Font("Courier New", Font.BOLD, 18));
		lblBudget.setBounds(42, 129, 190, 26);
		contentPane.add(lblBudget);
		
		textField = new JTextField();
		textField.setBounds(279, 37, 257, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(279, 82, 257, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(279, 129, 257, 24);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		window a=new window();
		JButton btnSave = new JButton("SAVE");
		btnSave.setBackground(new Color(0, 255, 255));
		btnSave.setForeground(new Color(0, 0, 0));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SaveToDatabase();
				InsertDept.this.setVisible(false);
				new window().setVisible(true);
				a.ShowData1();
			}
		});
		btnSave.setFont(new Font("Courier New", Font.BOLD, 18));
		btnSave.setBounds(279, 193, 105, 26);
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setBackground(new Color(0, 255, 255));
		btnCancel.setForeground(new Color(0, 0, 0));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new window().setVisible(true);
				InsertDept.this.setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Courier New", Font.BOLD, 18));
		btnCancel.setBounds(431, 193, 105, 26);
		contentPane.add(btnCancel);
	}
	
	private void SaveToDatabase()
	{
		Connection con=con();
		try {
			String query="insert into department values(?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,textField.getText() );
			ps.setString(2, textField_1.getText());
			ps.setString(3, textField_2.getText());
			ps.execute();
			JOptionPane.showMessageDialog(null, "DATA SAVED TO DATABASE");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
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
	
}
