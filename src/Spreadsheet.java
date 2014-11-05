import java.awt.*;
import javax.swing.*;

public class Spreadsheet extends Ball
{
	private Ball connect;
	private int strX, strY;
	
	public Spreadsheet(int xx, int yy, int r, Color c, String s, Ball con, int aa)
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
		
		cell = new String[22][6];
		
		for(int row=0; row<cell.length; row++)
		{
			for(int col=0; col<cell[0].length; col++)
			{
				cell[row][col]="";
			}
		}
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
			int width = (3*(2*radius)/5)/6;
			int space = radius/20;
			int x[] = new int[6];;
			int y[] = new int[22];
			
			for(int l=0; l<6; l++)
			  x[l] = xCoor+(2*radius)/5+l*width;
			
			for(int z=0; z<22; z++)
			  y[z] = yCoor+(2*radius)/5+(z+2)*space;
			
			g.setColor(Color.gray);
			g.fillRect(xCoor, yCoor, 2*radius, 2*radius);
			g.setColor(Color.white);
			g.fillRect(xCoor+(2*radius)/5, yCoor+(2*radius)/5, 3*(2*radius)/5, 3*(2*radius)/5);
			g.setColor(Color.black);
			g.drawRect(xCoor+(2*radius)/5, yCoor+(2*radius)/5, 3*(2*radius)/5, space);
			
			g.setColor(Color.blue);
			
			for(int xin=1; xin<=xIncr; xin++)
			{
				g.fillRect(x[selectX+xin], y[selectY], width, space);
				for(int yin=1; yin<=yIncr; yin++)
				{
					g.fillRect(x[selectX+xin], y[selectY+yin], width, space);
				}
			}
			
			for(int yin=1; yin<=yIncr; yin++)
			{
				g.fillRect(x[selectX], y[selectY+yin], width, space);
				for(int xin=1; xin<=xIncr; xin++)
				{
					g.fillRect(x[selectX+xin], y[selectY+yin], width, space);
				}
			}
			
			if(radius==300)
			{
				g.setFont(Font.decode("Courier New-BOLD-12"));
				for(int row=0; row<cell.length; row++)
				{
					for(int col=0; col<cell[0].length; col++)
					{
						if((col >= selectX && col <= selectX+xIncr) && (row >= selectY && row <= selectY+yIncr))
						  g.setColor(Color.white);
						else
						  g.setColor(Color.black);
						g.drawString(cell[row][col], x[col]+3, y[row]+space-3);
					}
				}
				g.setColor(Color.black);
				g.drawString(cell[selectY][selectX], x[selectX]+3, y[selectY]+space-3);
				g.drawString(cell[selectY][selectX], xCoor+(2*radius)/5+3, yCoor+(2*radius)/5+space-3);
			}
			
			g.setColor(Color.black);
			
			for(int i=0; i<6; i++)
			{
				for(int e=0; e<22; e++)
				{
					g.drawRect(x[i], y[e], width, space);
				}
			}
			
			g.drawRect(x[selectX]+1, y[selectY]+1, width-2, space-2);
			
			g.setColor(Color.gray);
			int rad = radius/60;
			for(int u=0; u<5; u++)
			{
				g.fillRect(x[u]+width-rad, y[0]-rad, rad*2, rad*2);
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