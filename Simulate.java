package collisionavoid;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Simulate extends JFrame {//输入坐标时用，分隔
	
	private JPanel golbal;
	private ShowPanel showpanel;//显示图形界面
	private JPanel obsInfo = new JPanel();
	private JPanel Status = new JPanel();
	private JPanel Option = new JPanel();
	private JTextField ShipPosition,Speed,Course,NowOption;
	
	private JTextField obsN1,obsN2,obsN3,obsN4;//面板显示更新时要检查是否有障碍物线程已经结束
	private JTextField obsP1,obsP2,obsP3,obsP4;//存储障碍物的信息
	private JTextField obsv1,obsv2,obsv3,obsv4;
	private JTextField obsc1,obsc2,obsc3,obsc4;
	
	private JTextField s1,s2,s3,s4;//存储各个障碍物的避碰计算信息，并显示
	private JTextField D1,D2,D3,D4;
	private JTextField T1,T2,T3,T4;
	private JTextField Danger1,Danger2,Danger3,Danger4;
	
	public Controller controller;
	private CreateDialog create;
	private DeleteDialog delete;
	private ChangeDialog change;
	
	public CreateDialog getcreate(){//调用create弹出窗口
		return this.create;
	}
	public DeleteDialog getdelete(){
		return this.delete;
	}
	public ChangeDialog getchange(){
		return this.change;
	}
	
	public JTextField[] getMyInfo(){//显示问题//注意返回值的写法JTextField[]
		JTextField temp[]={ShipPosition,Speed,Course,NowOption};
		return temp;
	}
	public JTextField[][] getobsInfo(){//方便刷新
		JTextField temp[][]={
				{obsN1,obsP1,obsv1,obsc1},
				{obsN2,obsP2,obsv2,obsc2},
				{obsN3,obsP3,obsv3,obsc3},
				{obsN4,obsP4,obsv4,obsc4}
		};
		return temp;
	}
	public JTextField[][] getStatus(){//刷新面板数据用
		JTextField temp[][]={
				{s1,D1,T1,Danger1},
				{s2,D2,T2,Danger2},
				{s3,D3,T3,Danger3},
				{s4,D4,T4,Danger4}
		};
		return temp;
	}
	public int number=0;//计算障碍物的数量
	
	
	public Simulate(ShowPanel showpanel) {//主界面所有组件
		
		this.setTitle("Simulation");
		this.setFont(new Font("Adobe 仿宋 Std R", Font.PLAIN, 18));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, Global.GWidth, Global.Gheight);
		golbal = new JPanel();
		golbal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(golbal);
		golbal.setLayout(null);
		
		this.showpanel=showpanel;//将外界的参数传进来
		showpanel.setBounds(0, 0, Global.ShowWidth, Global.ShowHeight);
		golbal.add(showpanel);
		
		JPanel myInfo = new JPanel();
		myInfo.setBounds(0, Global.ShowHeight, Global.ShowWidth, 50);
		golbal.add(myInfo);
		myInfo.setLayout(null);
		
		ShipPosition = new JTextField();
		ShipPosition.setFont(new Font("宋体", Font.PLAIN, 18));
		ShipPosition.setBounds(10, 10, 150, 30);
		myInfo.add(ShipPosition);
		ShipPosition.setColumns(10);
		
		Speed = new JTextField();
		Speed.setFont(new Font("宋体", Font.PLAIN, 18));
		Speed.setBounds(170, 10, 100, 30);
		myInfo.add(Speed);
		Speed.setColumns(10);
		
		Course = new JTextField();
		Course.setFont(new Font("宋体", Font.PLAIN, 18));
		Course.setBounds(280, 10, 100, 30);
		myInfo.add(Course);
		Course.setColumns(10);
		
		NowOption = new JTextField();
		NowOption.setFont(new Font("宋体", Font.PLAIN, 18));
		NowOption.setBounds(390, 10, 100, 30);
		myInfo.add(NowOption);
		NowOption.setColumns(10);
		obsInfo.setBounds(Global.ShowWidth+2, 0, 500, 220);
		
		golbal.add(obsInfo);
		obsInfo.setLayout(null);
		
		obsN1 = new JTextField();
		obsN1.setFont(new Font("宋体", Font.PLAIN, 18));
		obsN1.setBounds(10, 10, 100, 30);
		obsInfo.add(obsN1);
		obsN1.setColumns(10);
		
		obsP1 = new JTextField();
		obsP1.setFont(new Font("宋体", Font.PLAIN, 18));
		obsP1.setBounds(120, 10, 150, 30);
		obsInfo.add(obsP1);
		obsP1.setColumns(10);
		
		obsv1 = new JTextField();
		obsv1.setBounds(280, 10, 100, 30);
		obsInfo.add(obsv1);
		obsv1.setColumns(10);
		
		obsc1 = new JTextField();
		obsc1.setColumns(10);
		obsc1.setBounds(390, 10, 100, 30);
		obsInfo.add(obsc1);
		
		obsN2 = new JTextField();
		obsN2.setFont(new Font("宋体", Font.PLAIN, 18));
		obsN2.setColumns(10);
		obsN2.setBounds(10, 65, 100, 30);
		obsInfo.add(obsN2);
		
		obsP2 = new JTextField();
		obsP2.setFont(new Font("宋体", Font.PLAIN, 18));
		obsP2.setColumns(10);
		obsP2.setBounds(120, 65, 150, 30);
		obsInfo.add(obsP2);
		
		obsv2 = new JTextField();
		obsv2.setColumns(10);
		obsv2.setBounds(280, 65, 100, 30);
		obsInfo.add(obsv2);
		
		obsc2 = new JTextField();
		obsc2.setColumns(10);
		obsc2.setBounds(390, 65, 100, 30);
		obsInfo.add(obsc2);
		
		obsN3 = new JTextField();
		obsN3.setFont(new Font("宋体", Font.PLAIN, 18));
		obsN3.setColumns(10);
		obsN3.setBounds(10, 120, 100, 30);
		obsInfo.add(obsN3);
		
		obsP3 = new JTextField();
		obsP3.setFont(new Font("宋体", Font.PLAIN, 18));
		obsP3.setColumns(10);
		obsP3.setBounds(120, 120, 150, 30);
		obsInfo.add(obsP3);
		
		obsv3 = new JTextField();
		obsv3.setColumns(10);
		obsv3.setBounds(280, 120, 100, 30);
		obsInfo.add(obsv3);
		
		obsc3 = new JTextField();
		obsc3.setColumns(10);
		obsc3.setBounds(390, 120, 100, 30);
		obsInfo.add(obsc3);
		
		obsN4 = new JTextField();
		obsN4.setFont(new Font("宋体", Font.PLAIN, 18));
		obsN4.setColumns(10);
		obsN4.setBounds(10, 175, 100, 30);
		obsInfo.add(obsN4);
		
		obsP4 = new JTextField();
		obsP4.setFont(new Font("宋体", Font.PLAIN, 18));
		obsP4.setColumns(10);
		obsP4.setBounds(120, 175, 150, 30);
		obsInfo.add(obsP4);
		
		obsv4 = new JTextField();
		obsv4.setColumns(10);
		obsv4.setBounds(280, 175, 100, 30);
		obsInfo.add(obsv4);
		
		obsc4 = new JTextField();
		obsc4.setColumns(10);
		obsc4.setBounds(390, 175, 100, 30);
		obsInfo.add(obsc4);
		Status.setBounds(502, 225, 500, 220);
		
		golbal.add(Status);
		Status.setLayout(null);
		
		JLabel StatusName = new JLabel("Name");
		StatusName.setForeground(Color.BLACK);
		StatusName.setBackground(Color.WHITE);
		StatusName.setHorizontalAlignment(SwingConstants.CENTER);
		StatusName.setFont(new Font("宋体", Font.PLAIN, 18));
		StatusName.setBounds(20, 10, 100, 30);
		Status.add(StatusName);
		
		JLabel StatusD = new JLabel("DCPA");
		StatusD.setHorizontalAlignment(SwingConstants.CENTER);
		StatusD.setFont(new Font("宋体", Font.PLAIN, 18));
		StatusD.setBounds(140, 10, 100, 30);
		Status.add(StatusD);
		
		JLabel StatusT = new JLabel("TCPA");
		StatusT.setHorizontalAlignment(SwingConstants.CENTER);
		StatusT.setFont(new Font("宋体", Font.PLAIN, 18));
		StatusT.setBounds(260, 10, 100, 30);
		Status.add(StatusT);
		
		JLabel Danger = new JLabel("Danger?");
		Danger.setHorizontalAlignment(SwingConstants.CENTER);
		Danger.setFont(new Font("宋体", Font.PLAIN, 18));
		Danger.setBounds(380, 10, 100, 30);
		Status.add(Danger);
		
		s1 = new JTextField();
		s1.setFont(new Font("宋体", Font.PLAIN, 18));
		s1.setBounds(20, 49, 100, 30);
		Status.add(s1);
		s1.setColumns(10);
		
		s2 = new JTextField();
		s2.setFont(new Font("宋体", Font.PLAIN, 18));
		s2.setColumns(10);
		s2.setBounds(20, 93, 100, 30);
		Status.add(s2);
		
		s3 = new JTextField();
		s3.setFont(new Font("宋体", Font.PLAIN, 18));
		s3.setColumns(10);
		s3.setBounds(20, 132, 100, 30);
		Status.add(s3);
		
		s4 = new JTextField();
		s4.setFont(new Font("宋体", Font.PLAIN, 18));
		s4.setColumns(10);
		s4.setBounds(20, 176, 100, 30);
		Status.add(s4);
		
		D1 = new JTextField();
		D1.setBackground(Color.WHITE);
		D1.setFont(new Font("宋体", Font.PLAIN, 18));
		D1.setColumns(10);
		D1.setBounds(140, 50, 100, 30);
		Status.add(D1);
		
		D2 = new JTextField();
		D2.setFont(new Font("宋体", Font.PLAIN, 18));
		D2.setColumns(10);
		D2.setBackground(Color.WHITE);
		D2.setBounds(140, 93, 100, 30);
		Status.add(D2);
		
		D3 = new JTextField();
		D3.setFont(new Font("宋体", Font.PLAIN, 18));
		D3.setColumns(10);
		D3.setBackground(Color.WHITE);
		D3.setBounds(140, 132, 100, 30);
		Status.add(D3);
		
		D4 = new JTextField();
		D4.setFont(new Font("宋体", Font.PLAIN, 18));
		D4.setColumns(10);
		D4.setBackground(Color.WHITE);
		D4.setBounds(140, 176, 100, 30);
		Status.add(D4);
		
		T1 = new JTextField();
		T1.setFont(new Font("宋体", Font.PLAIN, 18));
		T1.setColumns(10);
		T1.setBackground(Color.WHITE);
		T1.setBounds(260, 50, 100, 30);
		Status.add(T1);
		
		T2 = new JTextField();
		T2.setFont(new Font("宋体", Font.PLAIN, 18));
		T2.setColumns(10);
		T2.setBackground(Color.WHITE);
		T2.setBounds(260, 93, 100, 30);
		Status.add(T2);
		
		T3 = new JTextField();
		T3.setFont(new Font("宋体", Font.PLAIN, 18));
		T3.setColumns(10);
		T3.setBackground(Color.WHITE);
		T3.setBounds(260, 132, 100, 30);
		Status.add(T3);
		
		T4 = new JTextField();
		T4.setFont(new Font("宋体", Font.PLAIN, 18));
		T4.setColumns(10);
		T4.setBackground(Color.WHITE);
		T4.setBounds(260, 176, 100, 30);
		Status.add(T4);
		
		Danger1 = new JTextField();
		Danger1.setFont(new Font("宋体", Font.PLAIN, 18));
		Danger1.setColumns(10);
		Danger1.setBackground(Color.GREEN);
		Danger1.setBounds(380, 49, 100, 30);
		Status.add(Danger1);
		
		Danger2 = new JTextField();
		Danger2.setFont(new Font("宋体", Font.PLAIN, 18));
		Danger2.setColumns(10);
		Danger2.setBackground(Color.GREEN);
		Danger2.setBounds(380, 93, 100, 30);
		Status.add(Danger2);
		
		Danger3 = new JTextField();
		Danger3.setFont(new Font("宋体", Font.PLAIN, 18));
		Danger3.setColumns(10);
		Danger3.setBackground(Color.GREEN);
		Danger3.setBounds(380, 132, 100, 30);
		Status.add(Danger3);
		
		Danger4 = new JTextField();
		Danger4.setFont(new Font("宋体", Font.PLAIN, 18));
		Danger4.setColumns(10);
		Danger4.setBackground(Color.GREEN);
		Danger4.setBounds(380, 176, 100, 30);
		Status.add(Danger4);
		Option.setBounds(Global.ShowWidth+2, showpanel.getHeight(), 500, 50);
		
		golbal.add(Option);
		Option.setLayout(null);
		
		JButton New = new JButton("New");
		New.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				create=new CreateDialog(controller);
				create.setVisible(true);
				System.out.println("you click new button!!");
			}
		});
		New.setFont(new Font("宋体", Font.PLAIN, 18));
		New.setBounds(20, 10, 100, 30);
		Option.add(New);
		
		JButton Delete = new JButton("Delete");
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete=new DeleteDialog(controller);
				delete.setVisible(true);
				System.out.println("you click the delete button!!");
			}
		});
		Delete.setFont(new Font("宋体", Font.PLAIN, 18));
		Delete.setBounds(150, 10, 100, 30);
		Option.add(Delete);
		
		JButton Change = new JButton("Change MyParameter");
		Change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change=new ChangeDialog(controller);
				change.setVisible(true);
				System.out.println("you click the change parameter button!!");
			}
		});
		Change.setFont(new Font("宋体", Font.PLAIN, 18));
		Change.setBounds(280, 10, 200, 30);
		Option.add(Change);
		
		JSeparator up = new JSeparator();
		up.setBounds(502, 221, 500, 3);
		golbal.add(up);
		
		JSeparator down = new JSeparator();
		down.setBounds(502, 448, 500, 3);
		golbal.add(down);
		
		
		this.setVisible(true);
	}
	
	
	public static void main(String[] args){
		//初始化
		ObstacleFactory obstaclefactory=new ObstacleFactory();
		ShipBirth shipbirth=new ShipBirth();
		ShowPanel showpanel=new ShowPanel();
		Global Global=new Global();
		Simulate sim=new Simulate(showpanel);
		
		//初始化完成，准备生成图形
		Ship ship=shipbirth.Birth(showpanel);//生成船舶线程
		Controller controller=new Controller(ship, obstaclefactory, showpanel, sim);
		sim.controller=controller;
		
		showpanel.Display(ship);//显示创建结果//在面板上画出船舶图形
		//将计算避碰参数的code放在这里
		
		while(Global.adjust){
			controller.shipFresh();//刷新面板数据
			controller.ObstacleMoved();
			
			/*for(int k=0;k<Global.Number;k++){//程序结束标志
				if(Global.distance[k]<20){
					System.out.println("collision avoid failed\nwill break.............");
					Global.adjust=false;
					break;
				}
			}*/
			//可以在此处加入      面板对象的参数保留问题    的解决方法
			
			if(Global.oldnumber>Global.Number){
				controller.empty();
			}
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//循环计算
		
		for(int i=0;i<Global.Number;i++){//最后清空界面
			if(Global.index[i].stop!=true)
				Global.index[i].stop=true;
		}
		
		showpanel.Gameend();
		
		System.out.println("simulate failed!! game over!!\n");
	}
}
