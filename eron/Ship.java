package collisionavoid;

import java.awt.Color;
import java.awt.Graphics;

public class Ship {
	
	private String name;//基本参数信息
	private int x,y;
	private double v,c;
	
	private ShowPanel showpanel;
	
	Ship(ShowPanel showpanel){    //适应无参数的情况
		this.name=Global.DefaultName;
		this.x=Global.Defaultx;this.y=Global.Defaulty;
		this.v=Global.Defaultv;this.c=Global.Defaultc;
		
		this.showpanel=showpanel;//显示面板调用
		System.out.println("ship's listener get it ,ok ");
		new Thread(new ShipDriver()).start();//新建一个线程
	}
	Ship(String name,int x,int y,double v,double c, ShowPanel showpanel){//有参数的重构
		this.name=name;this.x=x;this.y=y;this.v=v;this.c=c;
		
		this.showpanel=showpanel;
		System.out.println("ship's listener get it ,ok ");
		new Thread(new ShipDriver()).start();
	}
	
	public void ChangeParameter(String name,int x,int y,double v,double c){//改变参数
		System.out.println("change ship's parameter!!,"
				+ "i'm in ship change parameter method");
		this.name=name;this.x=x;this.y=y;this.v=v;this.c=c;
	}
	public void ChangeParameter(int b){//边界改向解决方案，同障碍物的方法一样
		if(b==1) this.x=5;
		else if(b==2) this.x=Global.ShowWidth-5;
		else if(b==3) this.y=5;
		else if(b==4) this.y=Global.ShowHeight-5;
	}
	
	public String getName(){
		return this.name;
	}
	public int getx(){
		return this.x;
	}
	public int gety(){
		return this.y;
	}
	public double getv(){
		return this.v;//线程的休眠时间才是速度，刷新速度，这个v代表休眠时的前进距离
	}
	public double getc(){
		return this.c;
	}
	public String[] freshused(){//刷新时需要的更新数据（字符串类型）
		String linshi=this.x+","+(Global.ShowHeight-this.y);
		String[] freshused={linshi, 
				String.valueOf(this.v), String.valueOf(this.c), this.nowOption()};
		oldc=newc;
		oldv=newv;
		gradirection=0;//解决刷新时状态不变的错误
		return freshused;
	}
	
	double oldc,newc,oldv,newv;//临时增加的，比较改变的参数,得出参数的变化趋势
	
	public String nowOption(){//当垂直反射时出现turnright，这就不改了，因为实际不存在瞬间变向的情况
		if(newc>oldc&&gradirection>0) return "TurnRight";
		else if(newc<oldc&&gradirection<0) return "TurnLeft";
		else if(newv>oldv&&graspeed>0) return "SpeedUp";
		else if(newv<oldv&&graspeed<0) return "SpeedDown";
		else return "GoAhead";//初始化时两个变量并没有赋值，但是可以运行到这里
	}
	
	public void goAhead(){
		//船舶将自动向计算的方向前进
		System.out.println("this is goahead ");
		
		double grax=this.v*Math.sin(c*(Math.PI/180));
		double gray=this.v*Math.cos(c*(Math.PI/180));
		this.x+=grax;  this.y-=gray;//注意此处坐标的变换，我坐标:左下角;默认左上角
		//我的y值:shopanel.getheight()-y,两次变换，输进变为默认，输出变为我的坐标系
		
		/*for(int i=0;i<4;i++){//并不会对船舶的数据产生影响，只是在每次船舶休眠后，前进的同时计算哪些障碍物需要进行避碰
			int f=Global.flag[i];
			if(f==1){
				Global.index[i].ChangeDirection(20);//如果正在避碰，障碍物相应的采取右转协调动作
				Global.changeflag(i,0);
			}
		}*/
	}
	
	double gradirection;//临时加的，将改变量传进来，没其他用，不用管
	public void ChangeDirection(double gradirection){//gradirection 相对于当前方向的变化角度
		this.gradirection=gradirection;
		
		System.out.println("this is change direction ");
		
		oldc=this.c;//可以比较新旧方向
		this.c+=gradirection;
		newc=this.c;
		while(this.c>=360){//多次检测是否过大，直至范围在0-360之间
			if(this.c>=360)  this.c-=360;
			else break;
		}
		while(this.c<0){
			if(this.c<0)  this.c+=360;
			else break;
		}
	}
	double graspeed;//速度变化量
	public void ChangeSpeed(double graspeed){
		System.out.println("this is change speed ,i;m in ship's  speed change method ");
		
		this.graspeed=graspeed;
		oldv=this.v;
		this.v+=graspeed;
		newv=this.v;
		//速度超限后的处理
		if(this.v<0)  {
			System.out.println("the ship's cannot astern! speed < 0!");
			this.v=10;
		}
		if(this.v>300)  {
			System.out.println("the ship's speed too fast! speed > 300");
			this.v=300;
		}
	}
	
	public void DrawMe(Graphics g){//画出船舶自己的图形
		System.out.println("draw ship in panel ,i'm in ship drawme");
		Graphics shipg=g;
		shipg.setColor(Color.BLUE);//设置新颜色以免画成白色
		
		int gsx=this.x-Global.ShipRadius,gsy=this.y-Global.ShipRadius;
		shipg.drawRect(gsx, gsy, 2*Global.ShipRadius, 2*Global.ShipRadius);
		shipg.fillRect(gsx, gsy, 2*Global.ShipRadius, 2*Global.ShipRadius);
		//还需要画出方向线，危险半径元//也可以用gdx,gdy代替下面的基准坐标
		shipg.drawOval(this.x-Global.DangerRadius, this.y-Global.DangerRadius, 
				2*Global.DangerRadius, 2*Global.DangerRadius);
		//计算方向线的末端坐标,,,末端坐标
		int glx=(int) (this.x+Global.CourseLine*Math.sin(c*Math.PI/180));
		int gly=(int) (this.y-Global.CourseLine*Math.cos(c*Math.PI/180));
		shipg.drawLine(this.x, this.y, glx, gly);
		//试着改变线条的颜色
	}
	
	public class ShipDriver implements Runnable{
		//新建一个线程，使船自动按照计算方向前进
		double gra=20;
		@Override
		public void run() {
			while(true){//条件判断是否遇到障碍物或者到达边界
				goAhead();
				if(Ship.this.x<=5||Ship.this.x>=Global.ShowWidth-5
						||Ship.this.y<=5||Ship.this.y>=Global.ShowHeight-5)
					Global.shipreflect(Ship.this);     //边界转向,5px边界宽度
				showpanel.Display(Ship.this);
				if(Ship.this.getv()>100)
					Global.oo=1;
				//根据计算结果判断动作
				Action(gra);//需要加入程序结束的控制标志
				if(Global.oo==1)
					Ship.this.v=Global.Defaultv;
				try {
					Thread.sleep(1000);  //Thread.sleep()是静态方法
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(Global.adjust==false)
					break;
			}
		}
		
	}
	
	/*public void distance(){//相对距离存储diatance[]///////////////////////////////////////////////
		double grax,gray;
		for(int i=0;i<Global.Number;i++){
			grax=(double)Math.abs(this.x-Global.index[i].getx());
			gray=(double)Math.abs(this.y-Global.index[i].gety());
			Global.distance[i]=Math.sqrt(grax*grax+gray*gray);
		}
	}*/
	
	//这个方法并没有用到，不用管
	public double[] sortdistance(){//返回一个排好序的距离数组,由小到大,,由于距离一直变化，此方法没用
		double[] temp=Global.distance;
		for(int i=0;i<Global.Number;i++){
			for(int j=i;j<Global.Number-i;j++){//冒泡method，多看看其他的快速方法
				if(temp[i]>temp[j]){
					double min=temp[j];temp[j]=temp[i];temp[i]=min;
				}
			}
		}
		return temp;//返回排好顺序的数组
	}
	public int mindistance(){//返回距离我最近的障碍物    在   数组中的位置，根据返回数判断
		if(Global.Number>0){
		double min=Global.distance[0];
		int k=0;
		for(int i=1;i<Global.Number;i++){
			if(min>Global.distance[i])
				{min=Global.distance[i];	k=i;}
			}
			return k;
		}
		return -1;//错误
	}
	
	/*public void vector(){//相对矢量存储到vector，相对真方位存储到course
		double vxr,vyr,vr,cr,a,b;
		for(int i=0;i<Global.Number;i++){
			Obstacle temp=Global.index[i];
			vxr=temp.getv()*Math.sin(temp.getc())-this.v*Math.sin(this.c);
			vyr=temp.getv()*Math.cos(temp.getc())-this.v*Math.cos(this.c);
			if(vyr<=0) a=180;//a是矫正角度
			else if(vxr>=0) a=0;
			else a=360;
			vr=Math.sqrt(vxr*vxr+vyr*vyr);//relation speed
			cr=Math.atan2(vxr, vyr)+a;//relation course
			Global.vector[i][0]=vr;	Global.vector[i][1]=cr;
			//相对于本船的真方位
			if(temp.gety()-this.y<0) b=180;
			else if(temp.getx()-this.x>=0) b=0;
			else b=360;
			Global.course[i]=Math.atan2(temp.getx()-this.x, temp.gety()-this.y)+b;
		}
	}*/
	
	
	public void Action(double gra){//当到达设定距离后再分析动作CourseLined's distance
		System.out.println("if obstacle, i'll change my direction .");
		//参数gra代表避碰步进的幅度大小，以后可以加入对其的分析
		int a=mindistance();
		if(a==-1) return;
		if(Global.distance[a]>70) return;//判断是否危险，如果不危险，则继续前进
		//找出存在危险的障碍物
		
		double analyse=Global.course[a]-Global.vector[a][1];
		//判断船舶相对方向与真方位
		/////////////////////////////////////////////////////////////////
		if(analyse>=0&&analyse<=6||analyse>=354&&analyse<360){//对遇
			ChangeDirection(gra);//全局变量的值是10，可以用，gra=10，这个不用管
			//Global.changeflag(a,1);
		}
		else if(analyse>=247.5&&analyse<354){//左舷交叉相遇
			ChangeDirection(gra);
			ChangeSpeed(gra);
			//Global.changeflag(a,1);//该变量表示碰到的是哪个障碍物
		}
		else if(analyse>6&&analyse<=67.5){//右舷小角度交叉相遇
			ChangeDirection(gra);
			ChangeSpeed(-gra);
			//Global.changeflag(a,1);
		}
		else if(analyse>67.5&&analyse<=112.5){//右舷大角度交叉相遇
			ChangeDirection(-gra);
			ChangeSpeed(gra);
			//Global.changeflag(a,1);
		}
		else if(analyse>112.5&&analyse<247.5){//进一步判断
			ChangeSpeed(gra);
			//Global.changeflag(a,1);
		}
		
	}
	
	
}
