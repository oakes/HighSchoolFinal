import java.awt.*;
import javax.swing.*;

public class Painter extends Ball
{
	private Ball connect;
	private int strX, strY;
	private Color small;
	private Color med;
	private Color large;
	
	public Painter(int xx, int yy, int r, Color c, String s, Ball con, int aa)
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
			g.setColor(Color.gray);
			g.fillRect(xCoor, yCoor, 2*radius, 2*radius);
			g.setColor(Color.white);
			g.fillRect(xCoor+3*((2*radius)/3)/40+2*(radius*2)/30, yCoor+radius/3, 2*radius-(3*((2*radius)/3)/40+2*(radius*2)/30), (2*radius)/3);
			
			g.setColor(Color.white);
			g.fillRect(xCoor+((2*radius)/3)/40, yCoor+radius/3+((2*radius)/3)/40, (radius*2)/30, (radius*2)/30);
			g.setColor(Color.black);
			g.fillRect(xCoor+((2*radius)/3)/40, yCoor+radius/3+2*((2*radius)/3)/40+(radius*2)/30, (radius*2)/30, (radius*2)/30);
			g.setColor(Color.lightGray);
			g.fillRect(xCoor+((2*radius)/3)/40, yCoor+radius/3+3*((2*radius)/3)/40+2*(radius*2)/30, (radius*2)/30, (radius*2)/30);
			g.setColor(Color.darkGray);
			g.fillRect(xCoor+((2*radius)/3)/40, yCoor+radius/3+4*((2*radius)/3)/40+3*(radius*2)/30, (radius*2)/30, (radius*2)/30);
			g.setColor(Color.blue);
			g.fillRect(xCoor+((2*radius)/3)/40, yCoor+radius/3+5*((2*radius)/3)/40+4*(radius*2)/30, (radius*2)/30, (radius*2)/30);
			g.setColor(Color.green);
			g.fillRect(xCoor+((2*radius)/3)/40, yCoor+radius/3+6*((2*radius)/3)/40+5*(radius*2)/30, (radius*2)/30, (radius*2)/30);
			g.setColor(Color.cyan);
			g.fillRect(xCoor+(radius*2)/30+2*((2*radius)/3)/40, yCoor+radius/3+((2*radius)/3)/40, (radius*2)/30, (radius*2)/30);
			g.setColor(Color.red);
			g.fillRect(xCoor+(radius*2)/30+2*((2*radius)/3)/40, yCoor+radius/3+2*((2*radius)/3)/40+(radius*2)/30, (radius*2)/30, (radius*2)/30);
			g.setColor(Color.magenta);
			g.fillRect(xCoor+(radius*2)/30+2*((2*radius)/3)/40, yCoor+radius/3+3*((2*radius)/3)/40+2*(radius*2)/30, (radius*2)/30, (radius*2)/30);
			g.setColor(Color.pink);
			g.fillRect(xCoor+(radius*2)/30+2*((2*radius)/3)/40, yCoor+radius/3+4*((2*radius)/3)/40+3*(radius*2)/30, (radius*2)/30, (radius*2)/30);
			g.setColor(Color.orange);
			g.fillRect(xCoor+(radius*2)/30+2*((2*radius)/3)/40, yCoor+radius/3+5*((2*radius)/3)/40+4*(radius*2)/30, (radius*2)/30, (radius*2)/30);
			g.setColor(Color.yellow);
			g.fillRect(xCoor+(radius*2)/30+2*((2*radius)/3)/40, yCoor+radius/3+6*((2*radius)/3)/40+5*(radius*2)/30, (radius*2)/30, (radius*2)/30);
			g.setColor(Color.white);
			g.fillRoundRect(xCoor+((2*radius)/3)/40+((radius*2)/30)/2, yCoor+radius/3+7*((2*radius)/3)/40+6*(radius*2)/30, (radius*2)/30+((2*radius)/3)/40, 2*(radius*2)/30+((2*radius)/3)/40, radius/30, radius/30);
			
			g.setColor(Color.black);
			g.drawRect(xCoor+((2*radius)/3)/40, yCoor+radius/3+((2*radius)/3)/40, (radius*2)/30, (radius*2)/30);
			g.drawRect(xCoor+((2*radius)/3)/40, yCoor+radius/3+2*((2*radius)/3)/40+(radius*2)/30, (radius*2)/30, (radius*2)/30);
			g.drawRect(xCoor+((2*radius)/3)/40, yCoor+radius/3+3*((2*radius)/3)/40+2*(radius*2)/30, (radius*2)/30, (radius*2)/30);
			g.drawRect(xCoor+((2*radius)/3)/40, yCoor+radius/3+4*((2*radius)/3)/40+3*(radius*2)/30, (radius*2)/30, (radius*2)/30);
			g.drawRect(xCoor+((2*radius)/3)/40, yCoor+radius/3+5*((2*radius)/3)/40+4*(radius*2)/30, (radius*2)/30, (radius*2)/30);
			g.drawRect(xCoor+((2*radius)/3)/40, yCoor+radius/3+6*((2*radius)/3)/40+5*(radius*2)/30, (radius*2)/30, (radius*2)/30);
			g.drawRect(xCoor+(radius*2)/30+2*((2*radius)/3)/40, yCoor+radius/3+((2*radius)/3)/40, (radius*2)/30, (radius*2)/30);
			g.drawRect(xCoor+(radius*2)/30+2*((2*radius)/3)/40, yCoor+radius/3+2*((2*radius)/3)/40+(radius*2)/30, (radius*2)/30, (radius*2)/30);
			g.drawRect(xCoor+(radius*2)/30+2*((2*radius)/3)/40, yCoor+radius/3+3*((2*radius)/3)/40+2*(radius*2)/30, (radius*2)/30, (radius*2)/30);
			g.drawRect(xCoor+(radius*2)/30+2*((2*radius)/3)/40, yCoor+radius/3+4*((2*radius)/3)/40+3*(radius*2)/30, (radius*2)/30, (radius*2)/30);
			g.drawRect(xCoor+(radius*2)/30+2*((2*radius)/3)/40, yCoor+radius/3+5*((2*radius)/3)/40+4*(radius*2)/30, (radius*2)/30, (radius*2)/30);
			g.drawRect(xCoor+(radius*2)/30+2*((2*radius)/3)/40, yCoor+radius/3+6*((2*radius)/3)/40+5*(radius*2)/30, (radius*2)/30, (radius*2)/30);
			g.drawRoundRect(xCoor+((2*radius)/3)/40+((radius*2)/30)/2, yCoor+radius/3+7*((2*radius)/3)/40+6*(radius*2)/30, (radius*2)/30+((2*radius)/3)/40, 2*(radius*2)/30+((2*radius)/3)/40, radius/30, radius/30);
			
			for(int i=0; i<Gui.cx.size(); i++)
			{
				int asdf = ((Integer) Gui.csize.get(i)).intValue();
				int asdf2;
				if(asdf==1)
				  asdf2 = radius/60;
				else if(asdf==2)
				  asdf2 = radius/40;
				else //if(asdf==3)
				  asdf2 = radius/30;
				
				g.setColor((Color) Gui.ccolor.get(i));
				g.fillOval(xCoor+radius*((Integer) Gui.cx.get(i)).intValue()/300, yCoor+radius*((Integer) Gui.cy.get(i)).intValue()/300, asdf2, asdf2);
			}
			
			if(size==1)
			{
				small=Color.blue;
				med=Color.black;
				large=Color.black;
				if(mx != 0 && my != 0)
				{
					g.setColor(color);
					g.fillOval(mx-(radius/60)/2, my-(radius/60)/2, radius/60, radius/60);
				}
			}
			else if(size==2)
			{
				small=Color.black;
				med=Color.blue;
				large=Color.black;
				if(mx != 0 && my != 0)
				{
					g.setColor(color);
					g.fillOval(mx-(radius/40)/2, my-(radius/40)/2, radius/40, radius/40);
				}
			}
			else if(size==3)
			{
				small=Color.black;
				med=Color.black;
				large=Color.blue;
				if(mx != 0 && my != 0)
				{
					g.setColor(color);
					g.fillOval(mx-(radius/30)/2, my-(radius/30)/2, radius/30, radius/30);
				}
			}
			
			g.setColor(small);
			g.fillOval(xCoor+((2*radius)/3)/40+((2*radius)/3)/80+(radius*2)/30-(radius/60)/2, (yCoor+radius/3+7*((2*radius)/3)/40+6*(radius*2)/30)+(2*(radius*2)/30+((2*radius)/3)/40)/6-(radius/60)/2, radius/60, radius/60);
			g.setColor(med);
			g.fillOval(xCoor+((2*radius)/3)/40+((2*radius)/3)/80+(radius*2)/30-(radius/40)/2, (yCoor+radius/3+7*((2*radius)/3)/40+6*(radius*2)/30)+3*(2*(radius*2)/30+((2*radius)/3)/40)/6-(radius/30)/2, radius/40, radius/40);
			g.setColor(large);
			g.fillOval(xCoor+((2*radius)/3)/40+((2*radius)/3)/80+(radius*2)/30-(radius/30)/2, (yCoor+radius/3+7*((2*radius)/3)/40+6*(radius*2)/30)+5*(2*(radius*2)/30+((2*radius)/3)/40)/6-(radius/20)/2, radius/30, radius/30);
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