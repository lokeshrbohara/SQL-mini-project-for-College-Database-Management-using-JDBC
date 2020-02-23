import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class InsertInst extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertInst frame = new InsertInst();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InsertInst() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Courier New", Font.BOLD, 18));
		lblNewLabel.setBounds(29, 30, 184, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setFont(new Font("Courier New", Font.BOLD, 18));
		lblName.setBounds(29, 68, 184, 23);
		contentPane.add(lblName);
		
		JLabel lblC = new JLabel("COURSE");
		lblC.setFont(new Font("Courier New", Font.BOLD, 18));
		lblC.setBounds(29, 109, 184, 23);
		contentPane.add(lblC);
		
		JLabel lblDepartment = new JLabel("DEPARTMENT");
		lblDepartment.setFont(new Font("Courier New", Font.BOLD, 18));
		lblDepartment.setBounds(29, 149, 184, 23);
		contentPane.add(lblDepartment);
		
		JLabel lblSalary = new JLabel("SALARY");
		lblSalary.setFont(new Font("Courier New", Font.BOLD, 18));
		lblSalary.setBounds(29, 193, 184, 23);
		contentPane.add(lblSalary);
		window a=new window();
		JButton btnSave = new JButton("SAVE");
		btnSave.setBackground(new Color(0, 255, 255));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SaveToDatabase();
				InsertInst.this.setVisible(false);
				new window().setVisible(true);
				a.ShowData2();
			}
		});
		btnSave.setFont(new Font("Courier New", Font.BOLD, 18));
		btnSave.setBounds(225, 268, 104, 35);
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setBackground(new Color(0, 255, 255));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new window().setVisible(true);
				InsertInst.this.setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Courier New", Font.BOLD, 18));
		btnCancel.setBounds(370, 268, 104, 35);
		contentPane.add(btnCancel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(247, 30, 249, 30);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(247, 71, 249, 30);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(247, 113, 249, 30);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(247, 157, 249, 30);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(247, 201, 249, 30);
		contentPane.add(textField_4);
	}
	
	private void SaveToDatabase()
	{
		Connection con=con();
		try {
			String query="insert into instructor values(?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,textField.getText() );
			ps.setString(2, textField_1.getText());
			ps.setString(3, textField_2.getText());
			ps.setString(4, textField_3.getText());
			ps.setString(5, textField_4.getText());
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
