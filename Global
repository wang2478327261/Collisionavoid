package collisionavoid;

public class Global{
	////////////////////////////////////
	//这个类中用于    存储     计算避碰障碍物相关数据
	///////////////////////////////////
	public static int Number=0;//记录障碍物数量
	
	//insert a new int var,解决面板残留数据问题
	public static int oldnumber=Number;
	public static int oo=0;
	
	public static Obstacle index[] = new Obstacle[10];//存储产生的障碍物对象
	
	public static int[] flag={0,0,0,0};//障碍物对船舶避碰的协调
	public static synchronized void changeflag(int i, int j){//根据标志位判断船舶正在对哪个障碍物进行避碰
		flag[i]=j;
	}
	
	//无参数初始化基本信息
	public static String DefaultName="default";
	public static int Defaultx=250,Defaulty=200;
	public static double Defaultv=25,Defaultc=0;
	//船舶半径圆,危险半径，方向线长度
	public static int ShipRadius=10,DangerRadius=35,CourseLine=70;
	
	public static int ShowWidth=500, ShowHeight=450;//showpanel的尺寸
	public static int GWidth=1010, Gheight=535;//MyFrame的尺寸,加上35去除窗体标题栏占的高度
	//边界改向问题，在边界时进行反射运动，以下是实现的方法
	public static void shipreflect(Ship ship){//传进ship实例为了改变该对象的方向
		System.out.println("this is reflect method  ");//考虑用范式解决此类代码重复的问题
		int b;//判断边界，解决在边界的徘徊问题
		if(ship.getx()<=5) ship.ChangeParameter(b=1);
		else if(ship.getx()>=Global.ShowWidth-5) ship.ChangeParameter(b=2);
		else if(ship.gety()<=5) ship.ChangeParameter(b=3); 
		else if(ship.gety()>=5) ship.ChangeParameter(b=4);
		//其实整个判断的方法没必要，有比这个更简单的做法
		if(ship.getx()>=ship.gety()&&ShowWidth-ship.getx()>=ship.gety()){//up
			//ship.getx()<=ship.gety()&&ship.getx()>=ShowHeight-ship.gety()
			if(ship.getc()>=0&&ship.getc()<=90)//判断方向范围
				ship.ChangeDirection(180-2*ship.getc());
			if(ship.getc()>=270&&ship.getc()<360)
				ship.ChangeDirection(540-2*ship.getc());
		}
		if(ship.getx()<=ship.gety()&&ship.getx()>=ShowWidth-ship.gety()){//down
			if(ship.getc()>=90&&ship.getc()<=180)
				ship.ChangeDirection(180-2*ship.getc());
			if(ship.getc()>180&&ship.getc()<=270)
				ship.ChangeDirection(540-2*ship.getc());
		}
		if(ship.getx()>ship.gety()&&ship.gety()>ShowWidth-ship.getx()){//right
			if(ship.getc()>=0&&ship.getc()<=90)
				ship.ChangeDirection(-2*ship.getc());
			if(ship.getc()>90&&ship.getc()<180)
				ship.ChangeDirection(360-2*ship.getc());
		}
		if(ship.getx()<ship.gety()&&ship.getx()<ShowWidth-ship.gety()){//left
			if(ship.getc()>180&&ship.getc()<=270)
				ship.ChangeDirection(720-2*ship.getc());
			if(ship.getc()>270&&ship.getc()<360)
				ship.ChangeDirection(360-2*ship.getc());
		}
	}
		
		
	public static void obsreflect(Obstacle obstacle){//障碍物同上
		System.out.println("this is reflect method  ");
		int b;//判断边界，解决在边界的徘徊问题
		if(obstacle.getx()<=5) obstacle.ChangeParameter(b=1);
		else if(obstacle.getx()>=Global.ShowWidth-5) obstacle.ChangeParameter(b=2);
		else if(obstacle.gety()<=5) obstacle.ChangeParameter(b=3); 
		else if(obstacle.gety()>=5) obstacle.ChangeParameter(b=4);
		
		if(obstacle.getx()>=obstacle.gety()&&ShowWidth-obstacle.getx()>=obstacle.gety()){//up
			//ship.getx()<=ship.gety()&&ship.getx()>=ShowHeight-ship.gety()
			if(obstacle.getc()>=0&&obstacle.getc()<=90)//判断方向范围
				obstacle.ChangeDirection(180-2*obstacle.getc());
			if(obstacle.getc()>=270&&obstacle.getc()<360)
				obstacle.ChangeDirection(540-2*obstacle.getc());
		}
		if(obstacle.getx()<=obstacle.gety()&&obstacle.getx()>=ShowWidth-obstacle.gety()){//down
			if(obstacle.getc()>=90&&obstacle.getc()<=180)
				obstacle.ChangeDirection(180-2*obstacle.getc());
			if(obstacle.getc()>180&&obstacle.getc()<=270)
				obstacle.ChangeDirection(540-2*obstacle.getc());
		}
		if(obstacle.getx()>obstacle.gety()&&obstacle.gety()>ShowWidth-obstacle.getx()){//right
			if(obstacle.getc()>=0&&obstacle.getc()<=90)
				obstacle.ChangeDirection(-2*obstacle.getc());
			if(obstacle.getc()>90&&obstacle.getc()<180)
				obstacle.ChangeDirection(360-2*obstacle.getc());
		}
		if(obstacle.getx()<obstacle.gety()&&obstacle.getx()<ShowWidth-obstacle.gety()){//left
			if(obstacle.getc()>180&&obstacle.getc()<=270)
				obstacle.ChangeDirection(720-2*obstacle.getc());
			if(obstacle.getc()>270&&obstacle.getc()<360)
				obstacle.ChangeDirection(360-2*obstacle.getc());
		}
	}
	
	/*相关船舶避碰策略计算存储区*/
	//数组的长度自己定，我先跟你定100，空指针异常就是这个原因
	public static double distance[] = new double[10];//相对距离
	public static double course[] = new double[10];//真航向
	public static double vector[][] = new double[10][10];//先vr，后vc
	public static double DCPA[] = new double[10];//按当前状态航行，能与船舶距离的最近距离
	public static void caculateD(){
		for(int i=0;i<Number;i++)
			DCPA[i]=distance[i]*Math.sin(vector[i][1]-course[i]-Math.PI);
	}
	public static double TCPA[]  = new double[10];//到达DCPA位置所用的时间
	public static void caculateT(){
		for(int i=0;i<Number;i++)
		TCPA[i]=distance[i]*Math.cos(vector[i][1]-course[i]-Math.PI)/vector[i][0];
	}
	
	public static boolean adjust=true;//判断程序中断，解决程序结束问题
	
}
