package collisionavoid;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class ShowPanel extends JPanel{//每次动画后都要更新面板数据
	
	private Ship ship;
	private Obstacle obstacle=null;
	
	public ShowPanel(){
		setSize(Global.ShowWidth, Global.ShowHeight);//换成全局变量调整时好调整
		setVisible(true);//在重构函数里不用在前边加this
	}
	
	public void Display(Obstacle obstacle){//将船舶的刷新与障碍物的刷新分离
		this.obstacle=obstacle;
		
		System.out.println("this display to draw obstacle only");
		this.repaint();
	}
	public void Display(Ship ship){//解决显示传值问题
		this.ship=ship;
		System.out.println("this is only display ship object");
		this.repaint();
	}
	
	public void Gameend(){//程序结束
		System.out.println("game over!\n");
		this.repaint();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Global.ShowWidth, Global.ShowHeight);
		if(ship!=null){
			System.out.println("ship != null");
			if(Global.adjust==true)
				ship.DrawMe(g);
		}
		if(obstacle!=null){//ship一定存在，障碍物可能不存在
			System.out.println("obstacle != null ");
			if(obstacle.stop!=true)
				obstacle.DrawMe(g);
		}
	}
	
}
