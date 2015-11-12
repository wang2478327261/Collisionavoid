package collisionavoid;

import javax.swing.JDialog;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangeDialog extends JDialog {
	private JTextField MyName;
	private JTextField MyPosition;
	private JTextField MySpeed;
	private JTextField MyCourse;
	
	public String getMyName(){
		return MyName.getText();
	}
	public String getMyPosition(){
		return MyPosition.getText();
	}
	public String getMySpeed(){
		return MySpeed.getText();
	}
	public String getMyCourse(){
		return MyCourse.getText();
	}
	
	
	
	public ChangeDialog(Controller controller) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setFont(new Font("Dialog", Font.PLAIN, 18));
		setTitle("Change My Parameter");
		setBounds(200, 200, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("宋体", Font.PLAIN, 18));
		lblName.setBounds(50, 30, 100, 30);
		getContentPane().add(lblName);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPosition.setFont(new Font("宋体", Font.PLAIN, 18));
		lblPosition.setBounds(50, 90, 100, 30);
		getContentPane().add(lblPosition);
		
		JLabel lblSpeed = new JLabel("Speed");
		lblSpeed.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSpeed.setFont(new Font("宋体", Font.PLAIN, 18));
		lblSpeed.setBounds(50, 150, 100, 30);
		getContentPane().add(lblSpeed);
		
		JLabel lblCourse = new JLabel("Course");
		lblCourse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCourse.setFont(new Font("宋体", Font.PLAIN, 18));
		lblCourse.setBounds(50, 210, 100, 30);
		getContentPane().add(lblCourse);
		
		MyName = new JTextField();
		MyName.setBounds(170, 30, 150, 30);
		getContentPane().add(MyName);
		MyName.setColumns(10);
		
		MyPosition = new JTextField();
		MyPosition.setBounds(170, 90, 150, 30);
		getContentPane().add(MyPosition);
		MyPosition.setColumns(10);
		
		MySpeed = new JTextField();
		MySpeed.setColumns(10);
		MySpeed.setBounds(170, 150, 150, 30);
		getContentPane().add(MySpeed);
		
		MyCourse = new JTextField();
		MyCourse.setColumns(10);
		MyCourse.setBounds(170, 210, 150, 30);
		getContentPane().add(MyCourse);
		
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(controller);/////////////////////////////
		//在controller类里进行处理按键事件
		btnChange.setFont(new Font("宋体", Font.PLAIN, 18));
		btnChange.setBounds(335, 30, 90, 90);
		getContentPane().add(btnChange);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangeDialog.this.dispose();
				System.out.println("you close your change parameter window!!");
			}
		});
		btnBack.setFont(new Font("宋体", Font.PLAIN, 18));
		btnBack.setBounds(335, 150, 90, 90);
		getContentPane().add(btnBack);
		
		setVisible(false);
	}

}
