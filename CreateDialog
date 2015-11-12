package collisionavoid;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CreateDialog extends JDialog {
	private JTextField newn;
	private JTextField newp;
	private JTextField newv;
	private JTextField newc;
	
	public String getnewn(){//设置成public类型的话就不用方法向外传递数据了
		return newn.getText();
	}
	public String getnewp(){
		return newp.getText();
	}
	public String getnewv(){
		return newv.getText();
	}
	public String getnewc(){
		return newc.getText();
	}
	
	public CreateDialog(Controller controller) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("New Obstacle");
		setFont(new Font("Dialog", Font.PLAIN, 18));
		getContentPane().setFont(new Font("宋体", Font.PLAIN, 18));
		setBounds(200, 200, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel NewName = new JLabel("Name");
		NewName.setHorizontalAlignment(SwingConstants.RIGHT);
		NewName.setFont(new Font("宋体", Font.PLAIN, 18));
		NewName.setBounds(50, 25, 100, 30);
		getContentPane().add(NewName);
		
		JLabel NewPosition = new JLabel("Position");
		NewPosition.setHorizontalAlignment(SwingConstants.RIGHT);
		NewPosition.setFont(new Font("宋体", Font.PLAIN, 18));
		NewPosition.setBounds(50, 80, 100, 30);
		getContentPane().add(NewPosition);
		
		JLabel NewSpeed = new JLabel("Speed");
		NewSpeed.setHorizontalAlignment(SwingConstants.RIGHT);
		NewSpeed.setFont(new Font("宋体", Font.PLAIN, 18));
		NewSpeed.setBounds(50, 135, 100, 30);
		getContentPane().add(NewSpeed);
		
		JLabel NewCourse = new JLabel("Course");
		NewCourse.setHorizontalAlignment(SwingConstants.RIGHT);
		NewCourse.setFont(new Font("宋体", Font.PLAIN, 18));
		NewCourse.setBounds(50, 190, 100, 30);
		getContentPane().add(NewCourse);
		
		newn = new JTextField();
		newn.setFont(new Font("宋体", Font.PLAIN, 18));
		newn.setBounds(200, 25, 150, 30);
		getContentPane().add(newn);
		newn.setColumns(10);
		
		newp = new JTextField();
		newp.setFont(new Font("宋体", Font.PLAIN, 18));
		newp.setColumns(10);
		newp.setBounds(200, 80, 150, 30);
		getContentPane().add(newp);
		
		newv = new JTextField();
		newv.setFont(new Font("宋体", Font.PLAIN, 18));
		newv.setColumns(10);
		newv.setBounds(200, 135, 150, 30);
		getContentPane().add(newv);
		
		newc = new JTextField();
		newc.setFont(new Font("宋体", Font.PLAIN, 18));
		newc.setColumns(10);
		newc.setBounds(200, 190, 150, 30);
		getContentPane().add(newc);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(controller);/////////////////////////
		//按键的响应在controller里定义
		btnCreate.setFont(new Font("宋体", Font.PLAIN, 18));
		btnCreate.setBounds(100, 230, 100, 30);
		getContentPane().add(btnCreate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CreateDialog.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				CreateDialog.this.dispose();//经过多次实验，这个可以
				System.out.println("you have exit the create dialog");
				//结束该对话框进程
			}
		});
		btnCancel.setFont(new Font("宋体", Font.PLAIN, 18));
		btnCancel.setBounds(250, 230, 100, 30);
		getContentPane().add(btnCancel);
		
		setVisible(false);
	}
}
