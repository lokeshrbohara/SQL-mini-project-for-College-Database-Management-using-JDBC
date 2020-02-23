import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateCourse extends JFrame {

	private JPanel contentPane;
	public JTextField textField_2;
	public JTextField textField_1;
	public JTextField textField;

static String id="", name="", dept="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCourse frame = new UpdateCourse(name, id, dept);
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
	public UpdateCourse(String name, String id, String dept) {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("COURSE ID");
		label.setFont(new Font("Courier New", Font.BOLD, 18));
		label.setBackground(Color.CYAN);
		label.setBounds(37, 45, 201, 32);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("COURSE NAME");
		label_1.setFont(new Font("Courier New", Font.BOLD, 18));
		label_1.setBackground(Color.CYAN);
		label_1.setBounds(37, 97, 201, 25);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("COURSE DEPARTMENT");
		label_2.setFont(new Font("Courier New", Font.BOLD, 18));
		label_2.setBackground(Color.CYAN);
		label_2.setBounds(37, 145, 201, 32);
		contentPane.add(label_2);
		
		/*window a=new window();
		
		DefaultTableModel model=(DefaultTableModel)a.table.getModel();
		int row=a.table.getSelectedRow();
		String id=model.getValueAt(row, 0).toString();
		String name=(String)model.getValueAt(row, 1);
		String dept=(String)model.getValueAt(row,2);
		textField.setText(id);
		textField_1.setText(name);
		textField_2.setText(dept);
		*/
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(250, 150, 355, 32);
		contentPane.add(textField_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(250, 98, 355, 32);
		contentPane.add(textField_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(250, 47, 355, 32);
		contentPane.add(textField);
		
		window a=new window();
		
		textField.setText(a.id);
		textField_1.setText(a.name);
		textField_2.setText(a.dept);
		System.out.println(a.id);
		System.out.println(a.name);
		System.out.println(a.dept);
		
		JButton button = new JButton("SAVE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Update(textField.getText());
				
			}
		});
		button.setFont(new Font("Courier New", Font.BOLD, 18));
		button.setBackground(Color.CYAN);
		button.setBounds(337, 258, 111, 37);
		contentPane.add(button);
		
		JButton button_1 = new JButton("CANCEL");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new window().setVisible(true);
				UpdateCourse.this.setVisible(false);
			}
		});
		button_1.setFont(new Font("Courier New", Font.BOLD, 18));
		button_1.setBackground(Color.CYAN);
		button_1.setBounds(494, 258, 111, 37);
		contentPane.add(button_1);
		textField.setText(id);
		textField_1.setText(name);
		textField_2.setText(dept);
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
		String query="update course set course_id=?,cname=?,dname=? where course_id=?";
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
