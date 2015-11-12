package collisionavoid;

import java.awt.Color;
import java.awt.Graphics;

public class Obstacle {

	private String name;//基本参数
	private int x,y;
	private double v,c;
	
	private ShowPanel showpanel;//用于调用显示面板将障碍物画在上边
	
	Obstacle(ShowPanel showpanel){
		this.name=Global.DefaultName;
		this.x=Global.Defaultx;this.y=Global.Defaulty;
		this.v=Global.Defaultv;this.c=Global.Defaultc;
		
		this.showpanel=showpanel;
		System.out.println("i'm in obstacle init method default,ok//");
		new Thread(new ObstacleDriver()).start();
		
	}
	Obstacle(String name,int x,int y,double v,double c, ShowPanel showpanel){
		this.name=name;this.x=x;this.y=y;this.v=v;this.c=c;
		
		this.showpanel=showpanel;
		System.out.println("i'm in obstacle init method ,ok//");
		new Thread(new ObstacleDriver()).start();
		//需要新建一个线程吗？
	}
	
	public String getName(){
		System.out.println("get the obstacle name! ");
		return this.name;
	}
	public int getx(){
		return this.x;
	}
	public int gety(){
		return this.y;
	}
	public double getv(){
		return this.v;
	}
	public double getc(){
		return this.c;
	}
	public String[] freshused(){//刷新面板显示所需的数据
		String linshi=this.x+","+(Global.ShowHeight-this.y);
		String[] freshused={this.name, linshi, 
				String.valueOf(this.v), String.valueOf(this.c)};
		return freshused;
	}
	
	public void Move(){//沿着当前方向前进
		System.out.println("the obstacle is moving , same to ship's goahead");
		//同Ship类的方法
		double x=this.v*Math.sin(c*(Math.PI/180));
		double y=this.v*Math.cos(c*(Math.PI/180));
		this.x+=x;  this.y-=y;
	}
	
	public void ChangeParameter(String name, int x, int y, double v, double c){
		//根据名字改变参数
		if(this.name==name){//根据传过来的参数改变障碍物信息
			this.x=x;this.y=y;this.v=v;this.c=c;
		}
		else{
			this.x=Global.Defaultx;this.y=Global.Defaulty;
			this.v=Global.Defaultv;this.c=Global.Defaultc;
		}
	}
	public void ChangeParameter(int b){//解决边界问题，这个方法没什么大作用，不用管
		if(b==1) this.x=5;
		else if(b==2) this.x=Global.ShowWidth-5;
		else if(b==3) this.y=5;
		else if(b==4) this.y=Global.ShowHeight-5;
	}
	public void ChangeDirection(double gradirection){//gradirection 相对于当前方向的    变化角度
		System.out.println("this is change direction ");
		
		this.c+=gradirection;
		while(this.c>=360){//多次检测是否过大，直至范围在0-360之间
			if(this.c>=360)  this.c-=360;
			else break;
		}
		while(this.c<0){//同上
			if(this.c<0)  this.c+=360;
			else break;
		}
	}
	
	
	public void DrawMe(Graphics g){//画出障碍物
		System.out.println("this is obstacle's drawme method..");
		//方形障碍物
		Graphics obstacleg=g;
		obstacleg.setColor(Color.RED);//设置新颜色
		
		int gox=this.x-Global.ShipRadius,goy=this.y-Global.ShipRadius;
		obstacleg.drawOval(gox, goy, 2*Global.ShipRadius, 2*Global.ShipRadius);
		obstacleg.fillOval(gox, goy, 2*Global.ShipRadius, 2*Global.ShipRadius);
		//危险半径元，同Ship类的半径
		obstacleg.drawOval(this.x-Global.DangerRadius, this.y-Global.DangerRadius, 
				2*Global.DangerRadius, 2*Global.DangerRadius);
		//方向线
		int glx=(int) (this.x+Global.CourseLine*Math.sin(this.c*Math.PI/180));
		int gly=(int) (this.y-Global.CourseLine*Math.cos(this.c*Math.PI/180));
		obstacleg.drawLine(this.x, this.y, glx, gly);
	}
	
	//新的程序结构
	/*public void DrawMe(Graphics g, String name, int x,int y,double v, double c){
		System.out.println("this is obstacle's drawme method..");
		Graphics obstacleg=g;
		obstacleg.setColor(Color.RED);
		int gox=x-Global.ShipRadius,goy=y-Global.ShipRadius;
		obstacleg.drawOval(gox, goy, 2*Global.ShipRadius, 2*Global.ShipRadius);
		obstacleg.fillOval(gox, goy, 2*Global.ShipRadius, 2*Global.ShipRadius);
		obstacleg.drawOval(x-Global.DangerRadius, y-Global.DangerRadius, 
				2*Global.DangerRadius, 2*Global.DangerRadius);
		int glx=(int) (x+Global.CourseLine*Math.sin(c*Math.PI/180));
		int gly=(int) (y-Global.CourseLine*Math.cos(c*Math.PI/180));
		obstacleg.drawLine(x, y, glx, gly);
	}*/
	
	
	public boolean stop=false;//用此决定线程存亡
	public class ObstacleDriver implements Runnable{
		@Override
		public void run() {
			while(!stop){
				Move();
				if(Obstacle.this.x<=5||Obstacle.this.x>=Global.ShowWidth-5
						||Obstacle.this.y<=5||Obstacle.this.y>=Global.ShowHeight-5)
					Global.obsreflect(Obstacle.this);//边界反应，在边界反弹运动
				showpanel.Display(Obstacle.this);//调用显示方法，将障碍物图形画出
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();	stop=true;
				}
			}
		}
	}
	
	
	/*public Controller controller;
	public void importcontroller(Controller controller){
		this.controller=controller;
	}*/
	
}
