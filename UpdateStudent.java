import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateStudent extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateStudent frame = new UpdateStudent("","","","","");
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
	public UpdateStudent(String id, String name,String dept,String course,String credits) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("ID");
		label.setFont(new Font("Courier New", Font.BOLD, 18));
		label.setBounds(45, 32, 184, 23);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("NAME");
		label_1.setFont(new Font("Courier New", Font.BOLD, 18));
		label_1.setBounds(45, 73, 184, 23);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("COURSE");
		label_2.setFont(new Font("Courier New", Font.BOLD, 18));
		label_2.setBounds(45, 111, 184, 23);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("DEPARTMENT");
		label_3.setFont(new Font("Courier New", Font.BOLD, 18));
		label_3.setBounds(45, 151, 184, 23);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("CREDITS");
		label_4.setFont(new Font("Courier New", Font.BOLD, 18));
		label_4.setBounds(45, 195, 184, 23);
		contentPane.add(label_4);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(259, 28, 249, 30);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(259, 69, 249, 30);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(259, 111, 249, 30);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(259, 155, 249, 30);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(259, 199, 249, 30);
		contentPane.add(textField_4);
		
		JButton button = new JButton("SAVE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Update(textField.getText());
			}
		});
		button.setFont(new Font("Courier New", Font.BOLD, 18));
		button.setBackground(Color.CYAN);
		button.setBounds(259, 256, 104, 35);
		contentPane.add(button);
		
		JButton button_1 = new JButton("CANCEL");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new window().setVisible(true);
				UpdateStudent.this.setVisible(false);
			}
		});
		button_1.setFont(new Font("Courier New", Font.BOLD, 18));
		button_1.setBackground(Color.CYAN);
		button_1.setBounds(404, 256, 104, 35);
		contentPane.add(button_1);
		textField.setText(id);
		textField_1.setText(name);
		textField_2.setText(dept);
		textField_3.setText(course);
		textField_4.setText(credits);
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
			String query="update student set s_id=?,s_name=?,course_name=?,dept_name=?,credits=? where s_id=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, textField.getText());
			ps.setString(2, textField_1.getText());
			ps.setString(3, textField_2.getText());
			ps.setString(4, textField_2.getText());
			ps.setString(5, textField_2.getText());
			ps.setString(6, id);
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
