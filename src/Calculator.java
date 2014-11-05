import java.awt.*;
import javax.swing.*;

public class Calculator extends Ball
{
	private Ball connect;
	private int strX, strY;
	
	public Calculator(int xx, int yy, int r, Color c, String s, Ball con, int aa)
	{		
		super(xx, yy, r, c, s, con, aa);
		
		xCoor = xx;
		yCoor = yy;
		radius = r;
		str = s;
		color = c;
		connect = con;
		strX = xx;
		strY = yy+2*radius+30;
		a = aa;
		
		n1 = "";
		n2 = "";
		oper = "";
		answer = false;
	}
	
	public boolean isInside(int xx, int yy)
  	{
    	return (xx > xCoor && xx < xCoor+radius*2) && (yy > yCoor && yy < yCoor+radius*2);
  	}
  	
  	public void draw(Graphics g, boolean hover, boolean transition, int integer)
	{
		if(hover && integer!=1)
		{
			draw(g, true, false, 1);
			if(radius==66) //label only big balls
    		{
    			strX = xCoor;
				strY = yCoor+2*radius+30;
    			g.setFont(Font.decode("Trebuchet MS-BOLD-14"));
    			g.setColor(Color.white);
    			g.drawString(str, strX, strY);
    		}    
			g.setColor(Color.white);
    		g.drawRect(xCoor, yCoor, 2*radius, 2*radius);
		}
		else if(integer == 1) //normal
		{
			if(radius==66 && !transition && !hover) //label only big ball
    		{
    			strX = xCoor;
				strY = yCoor+2*radius+30;
    			g.setFont(Font.decode("Trebuchet MS-BOLD-14"));
    			g.setColor(Color.lightGray);
    			g.drawString(str, strX, strY);
    		}    		
			int width = (((2*radius)/3-2*(radius/20))-3*radius/20)/4;
			
			g.setColor(Color.gray);
			g.fillRect(xCoor, yCoor, 2*radius, 2*radius);
			g.setColor(Color.white);
			g.fillRect(xCoor+(2*radius)/3, yCoor+(2*radius)/3, (2*radius)/3, 5*width+8*(radius/20));
			g.setColor(Color.black);
			g.drawRect(xCoor+(2*radius)/3+radius/20, yCoor+(2*radius)/3+radius/20, (2*radius)/3-2*(radius/20), radius/20);
			
			int x[] = new int[4];
			int y[] = new int[5];
			
			for(int p=0; p<4; p++)
			{
				x[p] = xCoor+p*width+p*radius/20+(2*radius)/3+radius/20;
				for(int m=0; m<5; m++)
				{
					y[m] = yCoor+(2*radius)/3+m*width+(m+3)*radius/20;
					if(click>0 && click<10)
					if(p==butX && m==butY)
					{
						g.setColor(Color.gray);
						g.fillRect(x[p], y[m], width, width);
						click++;
					}
					else if(click==3)
					  click=0;
					g.setColor(Color.black);
					g.drawRect(x[p], y[m], width, width);
				}
			}
			
			/*g.drawRect(xCoor+(2*radius)/3+radius/20, yCoor+(2*radius)/3+3*radius/20, width, width);
			g.drawRect(xCoor+(2*radius)/3+radius/20, yCoor+(2*radius)/3+width+4*radius/20, width, width);
			g.drawRect(xCoor+(2*radius)/3+radius/20, yCoor+(2*radius)/3+2*width+5*radius/20, width, width);
			g.drawRect(xCoor+(2*radius)/3+radius/20, yCoor+(2*radius)/3+3*width+6*radius/20, width, width);
			
			g.drawRect(xCoor+width+radius/20+(2*radius)/3+radius/20, yCoor+(2*radius)/3+3*radius/20, width, width);
			g.drawRect(xCoor+width+radius/20+(2*radius)/3+radius/20, yCoor+(2*radius)/3+width+4*radius/20, width, width);
			g.drawRect(xCoor+width+radius/20+(2*radius)/3+radius/20, yCoor+(2*radius)/3+2*width+5*radius/20, width, width);
			g.drawRect(xCoor+width+radius/20+(2*radius)/3+radius/20, yCoor+(2*radius)/3+3*width+6*radius/20, width, width);
			
			g.drawRect(xCoor+2*width+2*radius/20+(2*radius)/3+radius/20, yCoor+(2*radius)/3+3*radius/20, width, width);
			g.drawRect(xCoor+2*width+2*radius/20+(2*radius)/3+radius/20, yCoor+(2*radius)/3+width+4*radius/20, width, width);
			g.drawRect(xCoor+2*width+2*radius/20+(2*radius)/3+radius/20, yCoor+(2*radius)/3+2*width+5*radius/20, width, width);
			g.drawRect(xCoor+2*width+2*radius/20+(2*radius)/3+radius/20, yCoor+(2*radius)/3+3*width+6*radius/20, width, width);

			g.drawRect(xCoor+3*width+3*radius/20+(2*radius)/3+radius/20, yCoor+(2*radius)/3+3*radius/20, width, width);
			g.drawRect(xCoor+3*width+3*radius/20+(2*radius)/3+radius/20, yCoor+(2*radius)/3+width+4*radius/20, width, width);
			g.drawRect(xCoor+3*width+3*radius/20+(2*radius)/3+radius/20, yCoor+(2*radius)/3+2*width+5*radius/20, width, width);
			g.drawRect(xCoor+3*width+3*radius/20+(2*radius)/3+radius/20, yCoor+(2*radius)/3+3*width+6*radius/20, width, width);
			
			g.drawRect(xCoor+(2*radius)/3+radius/20, yCoor+(2*radius)/3+4*width+7*radius/20, width, width);
			g.drawRect(xCoor+width+radius/20+(2*radius)/3+radius/20, yCoor+(2*radius)/3+4*width+7*radius/20, width, width);
			g.drawRect(xCoor+2*width+2*radius/20+(2*radius)/3+radius/20, yCoor+(2*radius)/3+4*width+7*radius/20, width, width);
			g.drawRect(xCoor+3*width+3*radius/20+(2*radius)/3+radius/20, yCoor+(2*radius)/3+4*width+7*radius/20, width, width);			
			*/
			
			if(radius==300)
			{
				if(answer)
				  num=String.valueOf(ans);
				else if(oper.equals(""))
				  num=n1;
				else if(!oper.equals(""))
				  num=n2;
				
				g.setFont(Font.decode("Courier New-BOLD-14"));
				g.drawString(num, xCoor+(2*radius)/3+radius/20+(2*radius)/3-2*(radius/20)-9*num.length(), yCoor+(2*radius)/3+radius/20+radius/25);

				/*g.drawString("C", xCoor+(2*radius)/3+radius/20+width/2-5, yCoor+(2*radius)/3+3*radius/20+2*width/3);
				g.drawString("Sq", xCoor+(2*radius)/3+radius/20+width/2-10, yCoor+(2*radius)/3+width+4*radius/20+2*width/3);
				g.drawString("%", xCoor+(2*radius)/3+radius/20+width/2-5, yCoor+(2*radius)/3+2*width+5*radius/20+2*width/3);
				g.drawString("=", xCoor+(2*radius)/3+radius/20+width/2-5, yCoor+(2*radius)/3+3*width+6*radius/20+2*width/3);
				
				g.drawString("7", xCoor+width+radius/20+(2*radius)/3+radius/20+width/2-5, yCoor+(2*radius)/3+3*radius/20+2*width/3);
				g.drawString("4", xCoor+width+radius/20+(2*radius)/3+radius/20+width/2-5, yCoor+(2*radius)/3+width+4*radius/20+2*width/3);
				g.drawString("1", xCoor+width+radius/20+(2*radius)/3+radius/20+width/2-5, yCoor+(2*radius)/3+2*width+5*radius/20+2*width/3);
				g.drawString("0", xCoor+width+radius/20+(2*radius)/3+radius/20+width/2-5, yCoor+(2*radius)/3+3*width+6*radius/20+2*width/3);
				
				g.drawString("8", xCoor+2*width+2*radius/20+(2*radius)/3+radius/20+width/2-5, yCoor+(2*radius)/3+3*radius/20+2*width/3);
				g.drawString("5", xCoor+2*width+2*radius/20+(2*radius)/3+radius/20+width/2-5, yCoor+(2*radius)/3+width+4*radius/20+2*width/3);
				g.drawString("2", xCoor+2*width+2*radius/20+(2*radius)/3+radius/20+width/2-5, yCoor+(2*radius)/3+2*width+5*radius/20+2*width/3);
				g.drawString("+-", xCoor+2*width+2*radius/20+(2*radius)/3+radius/20+width/2-10, yCoor+(2*radius)/3+3*width+6*radius/20+2*width/3);
				
				g.drawString("9", xCoor+3*width+3*radius/20+(2*radius)/3+radius/20+width/2-5, yCoor+(2*radius)/3+3*radius/20+2*width/3);
				g.drawString("6", xCoor+3*width+3*radius/20+(2*radius)/3+radius/20+width/2-5, yCoor+(2*radius)/3+width+4*radius/20+2*width/3);
				g.drawString("3", xCoor+3*width+3*radius/20+(2*radius)/3+radius/20+width/2-5, yCoor+(2*radius)/3+2*width+5*radius/20+2*width/3);
				g.drawString(".", xCoor+3*width+3*radius/20+(2*radius)/3+radius/20+width/2-5, yCoor+(2*radius)/3+3*width+6*radius/20+2*width/3);

				g.drawString("+", xCoor+(2*radius)/3+radius/20+width/2-5, yCoor+(2*radius)/3+4*width+7*radius/20+2*width/3);
				g.drawString("-", xCoor+width+radius/20+(2*radius)/3+radius/20+width/2-5, yCoor+(2*radius)/3+4*width+7*radius/20+2*width/3);
				g.drawString("*", xCoor+2*width+2*radius/20+(2*radius)/3+radius/20+width/2-5, yCoor+(2*radius)/3+4*width+7*radius/20+2*width/3);
				g.drawString("/", xCoor+3*width+3*radius/20+(2*radius)/3+radius/20+width/2-5, yCoor+(2*radius)/3+4*width+7*radius/20+2*width/3);				
				*/
				
				g.drawString("C", x[0]+width/2-5, y[0]+2*width/3);
				g.drawString("Sq", x[0]+width/2-10, y[1]+2*width/3);
				g.drawString("%", x[0]+width/2-5, y[2]+2*width/3);
				g.drawString("=", x[0]+width/2-5, y[3]+2*width/3);
				
				g.drawString("7", x[1]+width/2-5, y[0]+2*width/3);
				g.drawString("4", x[1]+width/2-5, y[1]+2*width/3);
				g.drawString("1", x[1]+width/2-5, y[2]+2*width/3);
				g.drawString("0", x[1]+width/2-5, y[3]+2*width/3);
				
				g.drawString("8", x[2]+width/2-5, y[0]+2*width/3);
				g.drawString("5", x[2]+width/2-5, y[1]+2*width/3);
				g.drawString("2", x[2]+width/2-5, y[2]+2*width/3);
				g.drawString("+-", x[2]+width/2-10, y[3]+2*width/3);
				
				g.drawString("9", x[3]+width/2-5, y[0]+2*width/3);
				g.drawString("6", x[3]+width/2-5, y[1]+2*width/3);
				g.drawString("3", x[3]+width/2-5, y[2]+2*width/3);
				g.drawString(".", x[3]+width/2-5, y[3]+2*width/3);

				g.drawString("+", x[0]+width/2-5, y[4]+2*width/3);
				g.drawString("-", x[1]+width/2-5, y[4]+2*width/3);
				g.drawString("*", x[2]+width/2-5, y[4]+2*width/3);
				g.drawString("/", x[3]+width/2-5, y[4]+2*width/3);

			}
		}
		else if(integer == 2) //forward
    	{
    		if(xCoor != 0 || yCoor!= 0 || radius != 300)
			{
				if(xCoor != 0)
				  xCoor--;
				if(yCoor != 0)
				  yCoor--;
				if(radius != 300)
				  radius++;
				
				draw(g, false, false, 1);
			}
			else
			{
				Gui.isTransitioning = false;
				
				Ball tempList[] = new Ball[1]; //make a new temp list for the painter
				tempList[0] = this;
				
				Gui.back.move(300-(600/9/2), 550-(600/9/2));
				Gui.back.setString("Quit");
				
				Gui.stack.push(tempList);
			}
    	}
    	else if(integer == 3) //backward
    	{
    		Ball tempList[] = (Ball[]) Gui.stack.peek();
    		
    		if(tempList.length > 1)
    		{
    			int[] xer = connect.getSmallXCoor();
				int[] yer = connect.getSmallYCoor();
				
				if(xCoor < xer[a])
  				  xCoor++;
  				else if(xCoor > xer[a])
  				  xCoor--;
  				if(yCoor < yer[a])
  				  yCoor++;
  				else if(yCoor > yer[a])
  				  yCoor--;
  				if(radius > connect.getSmallRad())
  				  radius--;
  			}
  			else
  			{
				if(xCoor < Gui.x[a])
  				  xCoor++;
  				else if(xCoor > Gui.x[a])
  				  xCoor--;
  				if(yCoor < Gui.y[a])
  				  yCoor++;
  				else if(yCoor > Gui.y[a])
  				  yCoor--;
  				if(radius > connect.getRadius())
  				  radius--;
  			}
  				
  			draw(g, false, false, 1);
    	}
	}
}