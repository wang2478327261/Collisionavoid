package collisionavoid;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Controller implements ActionListener{
	//程序的中枢控制
	private Ship ship;
	private ObstacleFactory obstaclefactory;
	//private Global global;
	private ShowPanel showpanel;
	private Simulate sim;
	
	public Controller(){
		System.out.println("this is empty Controller");
	}
	public Controller(Ship ship, ObstacleFactory obstaclefactory, ShowPanel showpanel){
		this.ship = ship;//多写的这个重构用于测试showpanel的功能，便于测试模块
		
		this.obstaclefactory = obstaclefactory;
		this.showpanel = showpanel;
	}
	public Controller(Ship ship, ObstacleFactory obstaclefactory,ShowPanel showpanel, Simulate sim) {
		super();
		this.ship = ship;
		
		this.obstaclefactory = obstaclefactory;
		this.showpanel = showpanel;
		this.sim=sim;
		System.out.println("controller init ok!!");
	}
	
	public void resultdistance(){
		double grax,gray;
		for(int i=0;i<Global.Number;i++){
			grax=(double)Math.abs(ship.getx()-Global.index[i].getx());
			gray=(double)Math.abs(ship.gety()-Global.index[i].gety());
			Global.distance[i]=Math.sqrt(grax*grax+gray*gray);
		}
	}
	public void vectorcourse(){
		double vxr,vyr,vr,cr,a = 0,b = 0;
		for(int i=0;i<Global.Number;i++){
			Obstacle temp=Global.index[i];
			vxr=temp.getv()*Math.sin(temp.getc())-ship.getv()*Math.sin(ship.getc());
			vyr=temp.getv()*Math.cos(temp.getc())-ship.getv()*Math.cos(ship.getc());
			if(vxr>0&&vyr<=0) a=180;//a是矫正角度
			else if(vxr>=0&&vyr>0||vxr<0&&vyr<0) a=0;
			else if(vxr<0&&vyr>0) a=360;
			vr=Math.sqrt(vxr*vxr+vyr*vyr);//relation speed大小
			cr=Math.atan2(vxr, vyr)+a;//relation course方向
			Global.vector[i][0]=vr;	Global.vector[i][1]=cr;
			//相对于本船的真方位
			if(temp.gety()-ship.gety()<0&&temp.getx()-ship.getx()>=0) b=180;
			else if(temp.getx()-ship.getx()>=0&&temp.gety()-ship.gety()>0
					||temp.getx()-ship.getx()<0&&temp.gety()-ship.gety()<0) b=0;
			else if(temp.getx()-ship.getx()<0&&temp.gety()-ship.gety()>0) b=360;
			
			Global.course[i]=Math.atan2(temp.getx()-ship.getx(), temp.gety()-ship.gety())+b;
		}
	}
	
	//以前对接口的实现---现在变成了普通的方法
	public void ShipMoved(){
		System.out.println("i'm in the shipmoved");
		
		shipFresh();
		//计算距离，存储在Global全局变量中distance[]数组
		resultdistance();
		
		//程序结束判断
		for(int k=0;k<Global.Number;k++){//程序结束标志
			if(Global.distance[k]<20){
				System.out.println("collision avoid failed\nwill break.............");
				Global.adjust=false;
				break;
			}
		}
		//Global.caculateD();//只有出现障碍物时才有计算DCPA的必要
	}
	public void ObstacleMoved(){
		System.out.println("i'm in the obstaclemoved");
		
		obsFresh();
		vectorcourse();
		Global.caculateD();//不需要这么频繁的计算DCPA？
	}
	
	public String[] caculateDT(int i){//计算DCPA和TCPA的值
		Obstacle temp=Global.index[i];//只计算i值所指定对象
		String caculateDT[]={temp.getName(), String.valueOf(Global.DCPA[i]),
				String.valueOf(Global.TCPA[i])};
		return caculateDT;
	}
	public Color setcolor(int i){//根据距离设置危险程度，颜色变换
		Color temp[]=new Color[4];
		double dis=Global.distance[i];
		if(dis<=30){
			temp[0]=Color.RED;
		}
		else if(dis<=70){
			temp[0]=Color.YELLOW;
		}
		else if(dis<=100){
			temp[0]=Color.CYAN;
		}
		else {
			temp[0]=Color.GREEN;
		}
		return temp[0];
	}
	//以下两个方法是用来刷新显示界面参数的
	public void shipFresh(){//船舶信息刷新
		String[] freshused=new String[4];//船舶资料数组
		freshused=ship.freshused();//将船舶的信息传递到这里
		//复制完毕，开始刷新
		for(int i=0;i<4;i++){
			sim.getMyInfo()[i].setText(freshused[i]);//此处修改传递变量
		}
	}
	public void obsFresh(){//障碍物信息刷新
		String[] freshused=new String[4];
		String[] statusused=new String[3];
		
		//复制完毕，开始刷新，对面板上要显示的数据进行刷新
		for(int i=0;i<Global.Number;i++){//8.24晚上：：：此处需要改          //9.8 已改正
			freshused=Global.index[i].freshused();//需要检查障碍物的数量
			statusused=caculateDT(i);
			Color color=setcolor(i);
			for(int j=0;j<4;j++){
				sim.getobsInfo()[i][j].setText(freshused[j]);//填写障碍物信息
				if(j<3){                         //填充计算后的信息
				sim.getStatus()[i][j].setText(statusused[j]);
				}
				else{
				sim.getStatus()[i][j].setBackground(color);//将颜色变化单独出来，不能一起
				}
			}
		}
	}
	
	public void empty(){//现在只能对部分实现
		for(int i=Global.Number;i<Global.oldnumber;i++){//8.24晚上：：：此处需要改          //9.8 已改正
			for(int j=0;j<4;j++){
				sim.getobsInfo()[i][j].setText("");//填写障碍物信息
				if(j<3){                         //填充计算后的信息
				sim.getStatus()[i][j].setText("");
				}
				else{
				sim.getStatus()[i][j].setBackground(Color.GREEN);//将颜色变化单独出来，不能一起
				}
			}
		}
	}
	
	//对按键动作的反应程序
	@Override
	public void actionPerformed(ActionEvent e) {
		//因为需要传进来按钮的引用，所以用getActionCommand方法,不能使用getsource方法,不方便
		String event=e.getActionCommand();
		switch(event){
		case "Create":
			System.out.println("this is create actionperform  ");
			if(Global.Number>=3){//数组是从0开始的
				System.out.println("you cannot create ,it had fulled.");
				sim.getcreate().dispose();
			}
			else
			{
				//int型转换问题，分离用","隔开的问题,前期处理数据,    得到坐标写x，y
				int temp1[]=new int[10],i1=0;
				//这是怎么回事，为什么要new一下temp才能运行？？？？一定要初始化吗？
					Scanner scanner1=new Scanner(sim.getcreate().getnewp());
					scanner1.useDelimiter(",");//设置分隔符 
					while(scanner1.hasNext()){
						if(scanner1.hasNextInt())
							temp1[i1++]=scanner1.nextInt();//虽然初始化数组为10，但实际上存储2个
					}
				i1=0;//使i值归零，重新索引
				if(sim.getcreate().getnewn().length()==0||sim.getcreate().getnewp().length()==0
						||sim.getcreate().getnewv().length()==0
						||sim.getcreate().getnewc().length()==0)
					{//如有空值则进行默认操作
						Obstacle obstacle=obstaclefactory.Create(showpanel);
						
						Global.oldnumber++;//更新       障碍物数量比较      参考量
						
						showpanel.Display(obstacle);
						sim.getcreate().dispose();
					}
				else{
					Obstacle obstacle=obstaclefactory.Create(sim.getcreate().getnewn(), temp1[i1++], 
							Global.ShowHeight-temp1[i1++], 
							Double.parseDouble(sim.getcreate().getnewv()), 
							Double.parseDouble(sim.getcreate().getnewc()), showpanel);
					
					Global.oldnumber++;//更新       障碍物数量比较      参考量
					
					showpanel.Display(obstacle);
					sim.getcreate().dispose();
				}
			}
			break;
		case "OK":
			System.out.println("this is delete listening  ");
			if(Global.Number<=0){
				System.out.println("you have nothing obstacle， you can't delete ");
				sim.getdelete().dispose();
			}
			else
			{
				if(sim.getdelete().getDelName().length()==0){
					System.out.println("your input nothing ,i'll delete latest create!!");
					obstaclefactory.Delete();
					}
				else
					obstaclefactory.Delete(sim.getdelete().getDelName());
				
				sim.getdelete().dispose();
				obsFresh();
				for(int i=0;i<Global.Number;i++){//重新绘制所有障碍物
					showpanel.Display(Global.index[i]);
				}
			}
			break;
		case "Change":
			System.out.println("this is change listening  ");
			
			int temp2[]=new int[10],i2=0;
				Scanner scanner2=new Scanner(sim.getchange().getMyPosition());
				scanner2.useDelimiter(",");//设置分隔符 
				while(scanner2.hasNext()){
					if(scanner2.hasNextInt())
						temp2[i2++]=scanner2.nextInt();//虽然初始化数组为10，但实际上存储2个
				}
			i2=0;
			if(sim.getchange().getMyName().length()==0||sim.getchange().getMyPosition().length()==0
					||sim.getchange().getMySpeed().length()==0
					||sim.getchange().getMyCourse().length()==0)
				{
				System.out.println("your input is not complete!");
				sim.getchange().dispose();
				}
			else//改变后的参数传递给ship类型
				{
				ship.ChangeParameter(sim.getchange().getMyName(), 
						temp2[i2++], Global.ShowHeight-temp2[i2++], 
						Double.parseDouble(sim.getchange().getMySpeed()), 
						Double.parseDouble(sim.getchange().getMyCourse())
						);
				sim.getchange().dispose();
				}
			showpanel.Display(ship);
			//改变面板的显示
			shipFresh();
			break;
		}
	}
	
	/*@Override
	public void keyPressed(KeyEvent e) {//需要时加入手动操作功能
		super.keyPressed(e);
		switch(e.getKeyChar()){
		case KeyEvent.VK_W:
			System.out.println("ship will speedup  ");
			ship.ChangeSpeed();
			break;
		case KeyEvent.VK_S:
			System.out.println("ship will speeddown  ");
			ship.ChangeSpeed();
			break;
		case KeyEvent.VK_A:
			System.out.println("ship will turn left  ");
			ship.ChangeDirection();
			break;
		case KeyEvent.VK_D:
			System.out.println("ship will turn right   ");
			ship.ChangeDirection();
			break;
		}
		showpanel.Display(ship, obstacle);
	}*/
}
