package collisionavoid;

public class ObstacleFactory {
	
	public Obstacle Create(ShowPanel showpanel){//解决没有参数办法，便于测试
		System.out.println("create a new obstacle default ");
		Obstacle newobstacle=new Obstacle(showpanel);
		Global.index[Global.Number++]=newobstacle;//将新建的障碍物存储到Global类中的数组中，方便调用分析
		return newobstacle;
	}
	public Obstacle Create(String name,int x,int y,double v, double c, ShowPanel showpanel){
		System.out.println("create a new obstacle  full");
		Obstacle newobstacle=new Obstacle(name,x,y,v,c,showpanel);
		Global.index[Global.Number++]=newobstacle;
		//int temp=Global.Number--;
		return newobstacle;
	}
	
	public void Delete(){//没有参数的情况,删除最新创建的障碍物,   需要返回删除状态信息吗？
		System.out.println("delete a lastest creating obstacle ");
		
		int temp=--Global.Number;
		Obstacle oo=Global.index[temp];
		System.out.println("your delete is " + oo.getName() +"\n"
				+ "position is "+oo.getx()+","+oo.gety()+";\n"+
				"speed is "+oo.getv());
		oo.stop=true;//oo是最后一个障碍物的引用
		System.gc();
	}
	public void Delete(String delName){//传入要删除的对象的名称,如果需要返回删除情况，可以再次更改
		System.out.println("delete a existed obstacle ");
		
		for(int i=0;i<Global.Number;i++){//找出需要删除的障碍物线程
			if(Global.index[i].getName().equals(delName))
				{
					Obstacle oo=Global.index[i];
					//Global.oo[i]=0;
					//显示删除的信息，用来判断删除的情况
					System.out.println("your delete is " + oo.getName() +"\n"
							+ "position is "+oo.getx()+","+oo.gety()+";\n"+
							"speed is "+oo.getv());
					oo.stop=true;//如果是这个线程，则将该线程的stop改为true
					break;
				}
			System.out.println("this is stop  :  " + Global.index[i].stop + "\n");//测试
		}
		System.gc();
		//判断鼠标点与存在船舶的距离，进行删除操作
	}
	
}
