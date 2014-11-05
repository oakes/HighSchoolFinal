import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OperatingSystem extends JApplet
{
	private Gui canvas;
	private Container c;
	
	public void init() 
	{ 
		canvas = new Gui();
		c = getContentPane();
		c.add(canvas);
		//c.setBackground(new Color(0,50,100));
		//addMouseMotionListener(this);
	}
	
	//public void paint(Graphics g) 
	//{ 
		//super.paint(g); 
		/*Container c = getContentPane();
		
		int width = c.getWidth();
		
		int x[] = new int[list.length];
		for(int eks=0; eks<list.length; eks++)
		  if(eks%2==0) //if it's 0 or 2
		    x[eks] = (width/3)+(width/18);
		  else if(eks==1)
		    x[eks] = 2*(width/3)+(width/18);
		  else //if (eks==3)
		    x[eks] = (width/18);
		
		int y[] = new int[list.length];
		for(int why=0; why<list.length; why++)
		  if(why%2!=0) //if it's 1 or 3
		    y[why] = (width/3)+(width/18);
		  else if(why==0)
		    y[why] = (width/18);
		  else //if (why==2)
		    y[why] = 2*(width/3)+(width/18);*/
		
		/*int x1 = (width/3)+(width/18);
		int y1 = 0+(width/18);
		int x2 = 2*(width/3)+(width/18);
		int y2 = (width/3)+(width/18);
		int x3 = (width/3)+(width/18);
		int y3 = 2*(width/3)+(width/18);
		int x4 = 0+(width/18);
		int y4 = (width/3)+(width/18);*/
		
		//if(isHovering==4)
		//  for(int norm=0; norm<list.length; norm++)
		//    list[norm] = new Ball(x[norm], y[norm], ((2*width)/9)/2, new Color(0, 100, 150));
		//if(isHovering!=4)
		//  list[isHovering] = new Ball(x[isHovering], y[isHovering], ((2*width)/9)/2, new Color(0, 150, 200));
		
		/*for(int i=0; i<list.length; i++)
		{
			list[i].draw(g);
		}*/
	//}
}