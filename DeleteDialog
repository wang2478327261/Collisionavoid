package collisionavoid;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteDialog extends JDialog {
	private JTextField DelName;
	
	public String getDelName(){
		return DelName.getText();
	}
	
	
	
	public DeleteDialog(Controller controller) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Delete Obstacle");
		setBounds(200, 200, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel Declare = new JLabel("Please Input Needed Delete Obstacle");
		Declare.setHorizontalAlignment(SwingConstants.CENTER);
		Declare.setFont(new Font("宋体", Font.PLAIN, 18));
		Declare.setBounds(50, 30, 350, 30);
		getContentPane().add(Declare);
		
		DelName = new JTextField();
		DelName.setFont(new Font("宋体", Font.PLAIN, 18));
		DelName.setBounds(100, 100, 250, 30);
		getContentPane().add(DelName);
		DelName.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(controller);///////////////////////////////
		//在controller里处理相关动作
		btnOk.setFont(new Font("宋体", Font.PLAIN, 18));
		btnOk.setBounds(100, 200, 100, 30);
		getContentPane().add(btnOk);
		
		JLabel labName = new JLabel("NAME");
		labName.setHorizontalAlignment(SwingConstants.RIGHT);
		labName.setFont(new Font("宋体", Font.PLAIN, 18));
		labName.setBounds(20, 100, 50, 30);
		getContentPane().add(labName);
		
		JButton btnCancel = new JButton("NO");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteDialog.this.dispose();
				System.out.println("you had exit the delete dialog!!");
			}
		});
		btnCancel.setFont(new Font("宋体", Font.PLAIN, 18));
		btnCancel.setBounds(250, 200, 100, 30);
		getContentPane().add(btnCancel);
		
		setVisible(false);
	}
}
