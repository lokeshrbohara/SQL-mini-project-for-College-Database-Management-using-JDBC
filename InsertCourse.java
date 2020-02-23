import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsertCourse extends JFrame {
	
	window obj=new window();
	private JPanel contentPane;
	private JTextField courseidtextfield;
	private JTextField coursenametextfield;
	private JTextField coursedepttextfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertCourse frame = new InsertCourse();
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
	public InsertCourse() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		window a=new window();
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveToDatabase();
				InsertCourse.this.setVisible(false);
				new window().setVisible(true);
				a.ShowData();
				
			}
			
		});
		btnSave.setBackground(new Color(0, 255, 255));
		btnSave.setFont(new Font("Courier New", Font.BOLD, 18));
		btnSave.setBounds(340, 249, 111, 37);
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InsertCourse.this.setVisible(false);
				new window().setVisible(true);
			}
		});
		btnCancel.setBackground(new Color(0, 255, 255));
		btnCancel.setFont(new Font("Courier New", Font.BOLD, 18));
		btnCancel.setBounds(497, 249, 111, 37);
		contentPane.add(btnCancel);
		
		JLabel lblCourseId = new JLabel("COURSE ID");
		lblCourseId.setBackground(new Color(0, 255, 255));
		lblCourseId.setFont(new Font("Courier New", Font.BOLD, 18));
		lblCourseId.setBounds(40, 36, 201, 32);
		contentPane.add(lblCourseId);
		
		JLabel lblCourseName = new JLabel("COURSE NAME");
		lblCourseName.setBackground(new Color(0, 255, 255));
		lblCourseName.setFont(new Font("Courier New", Font.BOLD, 18));
		lblCourseName.setBounds(40, 88, 201, 25);
		contentPane.add(lblCourseName);
		
		courseidtextfield = new JTextField();
		courseidtextfield.setBounds(253, 38, 355, 32);
		contentPane.add(courseidtextfield);
		courseidtextfield.setColumns(10);
		
		coursenametextfield = new JTextField();
		coursenametextfield.setBounds(253, 89, 355, 32);
		contentPane.add(coursenametextfield);
		coursenametextfield.setColumns(10);
		
		JLabel lblCourseDepartment = new JLabel("COURSE DEPARTMENT");
		lblCourseDepartment.setBackground(new Color(0, 255, 255));
		lblCourseDepartment.setFont(new Font("Courier New", Font.BOLD, 18));
		lblCourseDepartment.setBounds(40, 136, 201, 32);
		contentPane.add(lblCourseDepartment);
		
		coursedepttextfield = new JTextField();
		coursedepttextfield.setBounds(253, 141, 355, 32);
		contentPane.add(coursedepttextfield);
		coursedepttextfield.setColumns(10);
	}
	private void SaveToDatabase()
	{
		Connection con=con();
		try {
			String query="insert into course values(?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,courseidtextfield.getText() );
			ps.setString(2, coursenametextfield.getText());
			ps.setString(3, coursedepttextfield.getText());
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
