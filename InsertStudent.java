import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsertStudent extends JFrame {

	private JPanel contentPane;
	private JTextField textField5;
	private JTextField textField4;
	private JTextField textField3;
	private JTextField textField2;
	private JTextField textField1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertStudent frame = new InsertStudent();
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
	public InsertStudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 532, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("ID");
		label.setFont(new Font("Courier New", Font.BOLD, 18));
		label.setBounds(22, 27, 184, 23);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("NAME");
		label_1.setFont(new Font("Courier New", Font.BOLD, 18));
		label_1.setBounds(22, 68, 184, 23);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("COURSE");
		label_2.setFont(new Font("Courier New", Font.BOLD, 18));
		label_2.setBounds(22, 106, 184, 23);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("DEPARTMENT");
		label_3.setFont(new Font("Courier New", Font.BOLD, 18));
		label_3.setBounds(22, 146, 184, 23);
		contentPane.add(label_3);
		
		JLabel lblCredits = new JLabel("CREDITS");
		lblCredits.setFont(new Font("Courier New", Font.BOLD, 18));
		lblCredits.setBounds(22, 190, 184, 23);
		contentPane.add(lblCredits);
		
		textField5 = new JTextField();
		textField5.setColumns(10);
		textField5.setBounds(236, 194, 249, 30);
		contentPane.add(textField5);
		
		textField4 = new JTextField();
		textField4.setColumns(10);
		textField4.setBounds(236, 150, 249, 30);
		contentPane.add(textField4);
		
		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(236, 106, 249, 30);
		contentPane.add(textField3);
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(236, 64, 249, 30);
		contentPane.add(textField2);
		
		textField1 = new JTextField();
		textField1.setColumns(10);
		textField1.setBounds(236, 23, 249, 30);
		contentPane.add(textField1);
		window a=new window();
		JButton button = new JButton("SAVE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SaveToDatabase();
				InsertStudent.this.setVisible(false);
				new window().setVisible(true);
				a.ShowData3();
			}
		});
		button.setFont(new Font("Courier New", Font.BOLD, 18));
		button.setBackground(Color.CYAN);
		button.setBounds(236, 251, 104, 35);
		contentPane.add(button);
		
		JButton button_1 = new JButton("CANCEL");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new window().setVisible(true);
				InsertStudent.this.setVisible(false);
			}
		});
		button_1.setBackground(new Color(0, 255, 255));
		button_1.setFont(new Font("Courier New", Font.BOLD, 18));
		button_1.setBounds(381, 251, 104, 35);
		contentPane.add(button_1);
	}
	
	private void SaveToDatabase()
	{
		Connection con=con();
		try {
			String query="insert into student values(?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,textField1.getText() );
			ps.setString(2, textField2.getText());
			ps.setString(3, textField3.getText());
			ps.setString(4, textField4.getText());
			ps.setString(5, textField5.getText());
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
