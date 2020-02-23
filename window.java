import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Desktop;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class window extends JFrame 
{
	
	int c=1;
	private JPanel contentPane;
	public JTable table;
	private JTextField textField;
	public String id;
	public String name;
	public String dept;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window frame = new window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public window() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				ShowData();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 849, 644);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCourse = new JButton("COURSE");
		btnCourse.setBackground(new Color(0, 255, 255));
		btnCourse.setFont(new Font("Courier New", Font.BOLD, 25));
		btnCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowData();
				c=1;
			}
		});
		btnCourse.setBounds(27, 32, 143, 88);
		contentPane.add(btnCourse);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 207, 700, 272);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("DEPARTMENT");
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.setFont(new Font("Courier New", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowData1();
				c=2;
			}
		});
		btnNewButton.setBounds(213, 33, 162, 88);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("INSTRUCTOR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowData2();
				c=3;
			}
		});
		btnNewButton_1.setBackground(new Color(0, 255, 255));
		btnNewButton_1.setFont(new Font("Courier New", Font.BOLD, 20));
		btnNewButton_1.setBounds(430, 33, 162, 88);
		contentPane.add(btnNewButton_1);
		
		JButton btnSr = new JButton("STUDENT");
		btnSr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowData3();
				c=4;
			}
		});
		btnSr.setBackground(new Color(0, 255, 255));
		btnSr.setFont(new Font("Courier New", Font.BOLD, 20));
		btnSr.setBounds(637, 33, 150, 88);
		contentPane.add(btnSr);
		
		textField = new JTextField();
		textField.setBounds(57, 142, 535, 37);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(c==3)
				{
				searchinst();	
				}
				else if(c==4)
				{
					searchinst1();
				}
				else if(c==1)
				{
					searchinst2();		
				}
				else if(c==2)
				{
					searchinst3();		
				}
			}
		});
		btnSearch.setBackground(new Color(0, 255, 255));
		btnSearch.setFont(new Font("Courier New", Font.BOLD, 15));
		btnSearch.setBounds(622, 149, 97, 25);
		contentPane.add(btnSearch);
		
		JButton btnInsert = new JButton("INSERT");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c==1)
					new InsertCourse().setVisible(true);
				else if(c==2)
					new InsertDept().setVisible(true);
				else if(c==3)
					new InsertInst().setVisible(true);
				else 
					new InsertStudent().setVisible(true);
					
			}
		});
		btnInsert.setBackground(new Color(0, 255, 255));
		btnInsert.setForeground(new Color(0, 0, 0));
		btnInsert.setFont(new Font("Courier New", Font.BOLD, 15));
		btnInsert.setBounds(58, 526, 112, 37);
		contentPane.add(btnInsert);
	
	
		JButton btnEdit = new JButton("UPDATE");
		btnEdit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if(c==1)
				{
					DefaultTableModel model=(DefaultTableModel)table.getModel();
					int row=table.getSelectedRow();
					id=model.getValueAt(row, 0).toString();
					name=(String)model.getValueAt(row, 1);
					dept=(String)model.getValueAt(row,2);
					
					new UpdateCourse(name, id, dept).setVisible(true);
				}
				else if(c==2)
				{

					DefaultTableModel model=(DefaultTableModel)table.getModel();
					int row=table.getSelectedRow();
					String name=model.getValueAt(row, 0).toString();
					String building=(String)model.getValueAt(row, 1);
					String budget=(String)model.getValueAt(row,2);
					
					new UpdateDept(name,building,budget).setVisible(true);
				}
					
				else if(c==3) {

					DefaultTableModel model=(DefaultTableModel)table.getModel();
					int row=table.getSelectedRow();
					id=model.getValueAt(row, 0).toString();
					name=(String)model.getValueAt(row, 1);
					dept=(String)model.getValueAt(row,2);
					String course=(String)model.getValueAt(row,3);
					String salary=model.getValueAt(row, 4).toString();
					new UpdateInst(id,name,dept,course,salary).setVisible(true);
				}
				else 
				{
					DefaultTableModel model=(DefaultTableModel)table.getModel();
					int row=table.getSelectedRow();
					id=model.getValueAt(row, 0).toString();
					name=(String)model.getValueAt(row, 1);
					dept=(String)model.getValueAt(row,2);
					String course=(String)model.getValueAt(row,3);
					String credits=model.getValueAt(row, 4).toString();
					
					new UpdateStudent(id,name,dept,course,credits).setVisible(true);
				}
				
			}
		});
		btnEdit.setBackground(new Color(0, 255, 255));
		btnEdit.setFont(new Font("Courier New", Font.BOLD, 15));
		btnEdit.setForeground(new Color(0, 0, 0));
		btnEdit.setBounds(256, 525, 119, 37);
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(c==1)
				{ 
					try {
					DefaultTableModel model=(DefaultTableModel)table.getModel();
					int row=table.getSelectedRow();
					String id=model.getValueAt(row, 0).toString();
					Delete(id);
					}
					catch(Exception e)
					{
						System.out.println("PLEASE SELECT A ROW");
					}
				}
				else if(c==2)
				{
					DefaultTableModel model=(DefaultTableModel)table.getModel();
					int row=table.getSelectedRow();
					String id=(String)model.getValueAt(row, 0);
					Delete1(id);
				}
				else if(c==3)
				{
					DefaultTableModel model=(DefaultTableModel)table.getModel();
					int row=table.getSelectedRow();
					String id=(String)model.getValueAt(row, 0);
					Delete2(id);
					
				}
				else if(c==4)
				{
					DefaultTableModel model=(DefaultTableModel)table.getModel();
					int row=table.getSelectedRow();
					String id=(String)model.getValueAt(row, 0);
					Delete3(id);
					
				}
			}
			
		});
		btnDelete.setBackground(new Color(0, 255, 255));
		btnDelete.setFont(new Font("Courier New", Font.BOLD, 15));
		btnDelete.setForeground(new Color(0, 0, 0));
		btnDelete.setBounds(473, 525, 119, 37);
		contentPane.add(btnDelete);
		
		JButton btnLogs = new JButton("LOGS");
		btnLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
				Desktop.getDesktop().open(new java.io.File("File/logs.txt"));	
				}
				catch (Exception e) {
				    e.printStackTrace();
				}
				
			}
		});
		btnLogs.setBackground(new Color(0, 255, 255));
		btnLogs.setFont(new Font("Courier New", Font.BOLD, 15));
		btnLogs.setForeground(new Color(0, 0, 0));
		btnLogs.setBounds(685, 525, 102, 37);
		contentPane.add(btnLogs);
		
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


	


public void ShowData()
{
	Connection con=con();
	DefaultTableModel model=new DefaultTableModel();
	model.addColumn("COURSE ID");
	model.addColumn("COURSE NAME");
	model.addColumn("DEPARTMENT NAME");
	try {
		String query="select * from course order by course_id";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		while(rs.next()) {
			model.addRow(new Object[]{
				rs.getString("course_id"),
				rs.getString("cname"),
				rs.getString("dname"),
			});
		}
		rs.close();
		st.close();
		con.close();
		table.setModel(model);
		table.setAutoResizeMode(1);
		table.getColumnModel().getColumn(0);
		table.getColumnModel().getColumn(1);
		table.getColumnModel().getColumn(2);
		
	}
	catch(Exception ex) {
		System.out.println("error"+ex);
	}
	
}
public void ShowData1()
{
	Connection con=con();
	DefaultTableModel model=new DefaultTableModel();
	model.addColumn("DEPARTMENT NAME");
	model.addColumn("BUILDING");
	model.addColumn("BUDGET");
	try {
		String query="select * from department";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		while(rs.next()) {
			model.addRow(new Object[]{
				rs.getString("dept_name"),
				rs.getString("building"),
				rs.getString("budget"),
			});
		}
		rs.close();
		st.close();
		con.close();
		table.setModel(model);
		table.setAutoResizeMode(1);
		table.getColumnModel().getColumn(0);
		table.getColumnModel().getColumn(1);
		table.getColumnModel().getColumn(2);
		
	}
	catch(Exception ex) {
		System.out.println("error"+ex);
	}
	
}
public void ShowData2()
{
	Connection con=con();
	DefaultTableModel model=new DefaultTableModel();
	model.addColumn("ID");
	model.addColumn("NAME");
	model.addColumn("COURSE");
	model.addColumn("DEPARTMENT");
	model.addColumn("SALARY");
	
	
	try {
		String query="select * from instructor";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		while(rs.next()) {
			model.addRow(new Object[]{
				rs.getString("i_id"),
				rs.getString("i_name"),
				rs.getString("course_name"),
				rs.getString("d_name"),
				rs.getString("salary"),
			});
		}
		rs.close();
		st.close();
		con.close();
		table.setModel(model);
		table.setAutoResizeMode(1);
		table.getColumnModel().getColumn(0);
		table.getColumnModel().getColumn(1);
		table.getColumnModel().getColumn(2);
		table.getColumnModel().getColumn(3);
		table.getColumnModel().getColumn(4);
	}
	catch(Exception ex) {
		System.out.println("error"+ex);
	}
	
}
public void ShowData3()
{
	Connection con=con();
	DefaultTableModel model=new DefaultTableModel();
	model.addColumn("ID");
	model.addColumn("NAME");
	model.addColumn("COURSE");
	model.addColumn("DEPARTMENT");
	model.addColumn("CREDITS");
	
	
	try {
		String query="select * from student";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		while(rs.next()) {
			model.addRow(new Object[]{
				rs.getString("s_id"),
				rs.getString("s_name"),
				rs.getString("course_name"),
				rs.getString("d_name"),
				rs.getString("total_credit"),
			});
		}
		rs.close();
		st.close();
		con.close();
		table.setModel(model);
		table.setAutoResizeMode(1);
		table.getColumnModel().getColumn(0);
		table.getColumnModel().getColumn(1);
		table.getColumnModel().getColumn(2);
		table.getColumnModel().getColumn(3);
		table.getColumnModel().getColumn(4);
	}
	catch(Exception ex) {
		System.out.println("error"+ex);
	}
	
}
private void Delete(String id)
{
	Connection con=con();
	try {
		String query="delete from course where course_id=?";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1,id);
		ps.execute();
		ps.close();
		con.close();
		ShowData();
		JOptionPane.showMessageDialog(null,"DELETED SUCCESSFULLY");
	}
	catch(Exception e)
	{
		System.out.println(e);
		
	}
	}

private void Delete1(String id)
{
	Connection con=con();
	try {
		String query="delete from department where dept_name=?";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1,id);
		ps.execute();
		ps.close();
		con.close();
		ShowData1();
		JOptionPane.showMessageDialog(null,"DELETED SUCCESSFULLY");
	}
	catch(Exception e)
	{
		System.out.println(e);
		
	}
	}

private void Delete2(String id)
{
	Connection con=con();
	try {
		String query="delete from instructor where i_id=?";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1,id);
		ps.execute();
		ps.close();
		con.close();
		ShowData1();
		JOptionPane.showMessageDialog(null,"DELETED SUCCESSFULLY");
	}
	catch(Exception e)
	{
		System.out.println(e);
		
	}
	}
private void Delete3(String id)
{
	Connection con=con();
	try {
		String query="delete from student where s_id=?";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1,id);
		ps.execute();
		ps.close();
		con.close();
		ShowData1();
		JOptionPane.showMessageDialog(null,"DELETED SUCCESSFULLY");
	}
	catch(Exception e)
	{
		System.out.println(e);
		
	}
	}

private void searchinst()
{
	Connection con=con();
	DefaultTableModel model=new DefaultTableModel();
	model.addColumn("ID");
	model.addColumn("NAME");
	model.addColumn("COURSE");
	model.addColumn("DEPARTMENT");
	model.addColumn("SALARY");
	try {
		String a=textField.getText();
		String query="select * from instructor where i_name like '%"+a+"%' order by i_id";
		
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		while(rs.next()) {
			model.addRow(new Object[]{
				rs.getString("i_id"),
				rs.getString("i_name"),
				rs.getString("course_name"),
				rs.getString("d_name"),
				rs.getString("salary"),
			});
		}
		System.out.println(textField.getText());

	
		con.close();
		rs.close();
		st.close();
		table.setModel(model);
		table.setAutoResizeMode(1);
		table.getColumnModel().getColumn(0);
		table.getColumnModel().getColumn(1);
		table.getColumnModel().getColumn(2);
		table.getColumnModel().getColumn(3);
		table.getColumnModel().getColumn(4);

	}
	catch(Exception e)
	{
		System.out.println(e);
		
	}
}

private void searchinst1()
{
	Connection con=con();
	DefaultTableModel model=new DefaultTableModel();
	model.addColumn("ID");
	model.addColumn("NAME");
	model.addColumn("COURSE");
	model.addColumn("DEPARTMENT");
	model.addColumn("CREDITS");
	try {
		String a=textField.getText();
		String query="select * from student where s_name like '%"+a+"%' order by s_id";
		
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		while(rs.next()) {
			model.addRow(new Object[]{
				rs.getString("s_id"),
				rs.getString("s_name"),
				rs.getString("course_name"),
				rs.getString("dept_name"),
				rs.getString("credits"),
			});
		}
	

	
		con.close();
		rs.close();
		st.close();
		table.setModel(model);
		table.setAutoResizeMode(1);
		table.getColumnModel().getColumn(0);
		table.getColumnModel().getColumn(1);
		table.getColumnModel().getColumn(2);
		table.getColumnModel().getColumn(3);
		table.getColumnModel().getColumn(4);
		
	}
	catch(Exception e)
	{
		System.out.println(e);
		
	}
}

private void searchinst2()
{
	Connection con=con();
	DefaultTableModel model=new DefaultTableModel();
	model.addColumn("COURSE ID");
	model.addColumn("COURSE NAME");
	model.addColumn("DEPARTMENT");

	try {
		String a=textField.getText();
		String query="select * from course where cname like '%"+a+"%'order by course_id";
		
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		while(rs.next()) {
			model.addRow(new Object[]{
				rs.getString("course_id"),
				rs.getString("cname"),
				rs.getString("dname"),
				
			});
		}
		
	
		con.close();
		rs.close();
		st.close();
		table.setModel(model);
		table.setAutoResizeMode(1);
		table.getColumnModel().getColumn(0);
		table.getColumnModel().getColumn(1);
		table.getColumnModel().getColumn(2);
		
	}
	catch(Exception e)
	{
		System.out.println(e);
		
	}
}

private void searchinst3()
{
	Connection con=con();
	DefaultTableModel model=new DefaultTableModel();
	model.addColumn("DEPARTMENT NAME");
	model.addColumn("BUILDING");
	model.addColumn("BUDGET");

	try {
		String a=textField.getText();
		String query="select * from department where dept_name like '%"+a+"%'";
		
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		while(rs.next()) {
			model.addRow(new Object[]{
				rs.getString("dept_name"),
				rs.getString("building"),
				rs.getString("budget"),
				
			});
		}
		
	
		con.close();
		rs.close();
		st.close();
		table.setModel(model);
		table.setAutoResizeMode(1);
		table.getColumnModel().getColumn(0);
		table.getColumnModel().getColumn(1);
		table.getColumnModel().getColumn(2);
		
	}
	catch(Exception e)
	{
		System.out.println(e);
		
	}
}
}







