import java.awt.*;
import javax.swing.*;

public class Ball extends Component
{
	protected int xCoor, yCoor, radius;
	protected Color color;
	private Ball ballsIn[];
	private int[] smallXCoor;
	private int[] smallYCoor;
	//private int smallRadFinal;
	private double smallAngle[];
	private int smallRad;
	private int smallSize = -1;
	private int pathXCoor;
	private int pathYCoor;
	protected String str;
	private int strX, strY;
	private Ball connect;
	private boolean isBack = false;
	protected int size = 1;
	protected int mx, my;
	protected int a;
	
	protected String num;
	protected String n1, n2;
	protected String oper;
	protected int ans;
	protected boolean answer;
	protected int butX;
	protected int butY;
	protected int click = 0;
	
	protected int selectX = 0;
	protected int selectY = 0;
	protected int xIncr = 0, yIncr = 0;
	
	protected String[][] cell;
	
	//private int width, height;
	//private Gui myGui;
	//private boolean isTransitioning;
	//private Color palette[];
	//private Color hoverColor = new Color(0, 150, 200);
	
	public Ball(int x, int y, int r, Color c, String s, Ball con, int aa)
	{
		xCoor = x;
    	yCoor = y;
    	radius = r;
    	color = c;
    	str = s;
    	connect = con;
    	strX = xCoor;
    	strY = yCoor + 2*radius + 30;
    	a = aa;
	}
	
	public Ball(int x, int y, int r, String s) //back
	{
		xCoor = x;
    	yCoor = y;
    	radius = r;
    	color = null;
    	str = s;
    	strX = xCoor+20;
    	strY = yCoor + radius + 7;
    	isBack = true;
	}
	
	public int getX()
  	{
    	return xCoor;
  	}

  	public int getY()
  	{
    	return yCoor;
  	}
  	
  	public int getRadius()
  	{
    	return radius;
  	}
  	
  	public int getSmallRad()
  	{
  		return smallRad;
  	}
  	
  	public int[] getSmallXCoor()
  	{
  		return smallXCoor;
  	}
  	
  	public int[] getSmallYCoor()
  	{
  		return smallYCoor;
  	}
  	
  	public String getString()
  	{
  		return str;
  	}
  	
  	public int getOrder()
  	{
  		return a;
  	}
  	
  	public void setString(String s)
  	{
  		str = s;
  		strX = xCoor+20;
    	strY = yCoor + radius + 7;
  	}
  	
  	public void setColor(Color c)
  	{
  		color = c;
  	}
  	
  	public Color getColor()
  	{
  		return color;
  	}
  	
  	public void setSize(int i)
  	{
  		size = i;
  	}
  	
  	public int getSizer()
  	{
  		return size;
  	}
  	
  	public void setMouse(int xx, int yy)
  	{
  		mx = xx;
  		my = yy;
  	}
  	
  	public Ball getConnect()
  	{
  		return connect;
  	}
  	
  	public int distance(int x, int y)
  	{
    	double dx = x - (xCoor+radius);
    	double dy = y - (yCoor+radius);
    	return (int)(Math.sqrt(dx*dx + dy*dy) + .5);
  	}
  	
  	public double transitionDistance(int x1, int y1, int x2, int y2)
  	{
  		double dx = x1 - x2;
    	double dy = y1 - y2;
    	return Math.abs((double)(Math.sqrt(dx*dx + dy*dy) + .5));
  	}

  	public void move(int x, int y)
  	{
    	xCoor = x;
    	yCoor = y;
  	}
  	
  	public void reset(int x, int y, int r)
  	{
  		xCoor = x;
  		yCoor = y;
  		radius = r;
  		strX = xCoor;
    	strY = yCoor + 2*radius + 30;
    	
  		int i = getSmallBallNum();
    	
    	if(i==1)
      	  smallRad = (5*radius)/6;
      	else if(i==2)
      	  smallRad = (3*radius)/4;
      	else if(i==3)
      	  smallRad = (2*radius)/3;
      	else //if(i==4)
      	  smallRad = radius/2;
      	 
      	pathXCoor = xCoor + radius - smallRad/2;
      	pathYCoor = yCoor + radius - smallRad/2;
      	smallRad = smallRad/2;
      	for(int xy=0; xy<smallAngle.length; xy++)
      	{
      		smallAngle[xy] = 360-xy*(360/smallAngle.length);
      		smallXCoor[xy] = (int) (pathXCoor + ((double) Math.cos((smallAngle[xy]*3.14)/180))*radius/2);
      		smallYCoor[xy] = (int) (pathYCoor + ((double) Math.sin((smallAngle[xy]*3.14)/180))*radius/2);
      		ballsIn[xy].reset(smallXCoor[xy], smallYCoor[xy], smallRad);
      	}
  	}
  	
  	public void resetSmall()
  	{
  		int i = getSmallBallNum();
    	
    	if(i==1)
      	  smallRad = (5*radius)/6;
      	else if(i==2)
      	  smallRad = (3*radius)/4;
      	else if(i==3)
      	  smallRad = (2*radius)/3;
      	else //if(i==4)
      	  smallRad = radius/2;
      	 
      	pathXCoor = xCoor + radius - smallRad/2;
      	pathYCoor = yCoor + radius - smallRad/2;
      	smallRad = smallRad/2;
      	for(int xy=0; xy<smallAngle.length; xy++)
      	{
      		smallAngle[xy] = 360-xy*(360/smallAngle.length);
      		smallXCoor[xy] = (int) (pathXCoor + ((double) Math.cos((smallAngle[xy]*3.14)/180))*radius/2);
      		smallYCoor[xy] = (int) (pathYCoor + ((double) Math.sin((smallAngle[xy]*3.14)/180))*radius/2);
      		ballsIn[xy].reset(smallXCoor[xy], smallYCoor[xy], smallRad);
      	}
  	}
  	
  	public void setSmallNum(int i)
  	{
  		ballsIn = new Ball[i];
  		
  		if(i==1)
      	  smallRad = (5*radius)/6;
      	else if(i==2)
      	  smallRad = (3*radius)/4;
      	else if(i==3)
      	  smallRad = (2*radius)/3;
      	else //if(i==4)
      	  smallRad = radius/2;
      	
      	smallXCoor = new int[i];
      	smallYCoor = new int[i];
      	
      	pathXCoor = xCoor + radius - smallRad/2;
      	pathYCoor = yCoor + radius - smallRad/2;
      	smallAngle = new double[i];
      	smallRad = smallRad/2;
      	//smallRadFinal = smallRad;
  	}

  	public boolean isInside(int x, int y)
  	{
    	double d = distance(x, y);
    	return d < radius;
  	}
  	
  	public void addBall(String s)
  	{
      	smallSize++;
      	smallAngle[smallSize] = 360-smallSize*(360/smallAngle.length);
      	smallXCoor[smallSize] = (int) (pathXCoor + ((double) Math.cos((smallAngle[smallSize]*3.14)/180))*radius/2);
      	smallYCoor[smallSize] = (int) (pathYCoor + ((double) Math.sin((smallAngle[smallSize]*3.14)/180))*radius/2);
      	ballsIn[smallSize] = new Ball(smallXCoor[smallSize], smallYCoor[smallSize], smallRad, new Color(0, (int) (100*Math.random())+100,(int) (100*Math.random())+150), s, this, smallSize); // palette[(int) (8*Math.random())]
  	}
  	
  	public void addPainter(String s)
  	{
  		smallSize++;
      	smallAngle[smallSize] = 360-smallSize*(360/smallAngle.length);
      	smallXCoor[smallSize] = (int) (pathXCoor + ((double) Math.cos((smallAngle[smallSize]*3.14)/180))*radius/2);
      	smallYCoor[smallSize] = (int) (pathYCoor + ((double) Math.sin((smallAngle[smallSize]*3.14)/180))*radius/2);
      	ballsIn[smallSize] = new Painter(smallXCoor[smallSize], smallYCoor[smallSize], smallRad, Color.white, s, this, smallSize); // palette[(int) (8*Math.random())]
  	}
  	
  	public void addCal(String s)
  	{
  		smallSize++;
      	smallAngle[smallSize] = 360-smallSize*(360/smallAngle.length);
      	smallXCoor[smallSize] = (int) (pathXCoor + ((double) Math.cos((smallAngle[smallSize]*3.14)/180))*radius/2);
      	smallYCoor[smallSize] = (int) (pathYCoor + ((double) Math.sin((smallAngle[smallSize]*3.14)/180))*radius/2);
      	ballsIn[smallSize] = new Calculator(smallXCoor[smallSize], smallYCoor[smallSize], smallRad, Color.white, s, this, smallSize); // palette[(int) (8*Math.random())]
  	}
  	
  	public void addSpread(String s)
  	{
  		smallSize++;
      	smallAngle[smallSize] = 360-smallSize*(360/smallAngle.length);
      	smallXCoor[smallSize] = (int) (pathXCoor + ((double) Math.cos((smallAngle[smallSize]*3.14)/180))*radius/2);
      	smallYCoor[smallSize] = (int) (pathYCoor + ((double) Math.sin((smallAngle[smallSize]*3.14)/180))*radius/2);
      	ballsIn[smallSize] = new Spreadsheet(smallXCoor[smallSize], smallYCoor[smallSize], smallRad, Color.white, s, this, smallSize); // palette[(int) (8*Math.random())]
  	}
  	
  	public int getSmallBallNum()
  	{
  		return ballsIn.length;
  	}
  	
  	public Ball getSmallBall(int num)
  	{
  		return ballsIn[num];
  	}
  	
  	public double[] getSmallAngle()
  	{
  		return smallAngle;
  	}

  	public void draw(Graphics g, boolean hover, boolean transition, int integer)
  	{
    	if(hover) //hovering over big balls
    	{
    		if(isBack)
    		{
    			g.setFont(Font.decode("Trebuchet MS-BOLD-14"));
    			g.setColor(Color.white);
    			g.drawString(str, strX, strY);
    			g.drawOval(xCoor, yCoor, 2*radius, 2*radius);
    		}
    		else if(radius>=16)
    		{
    			if(radius==66) //label only big balls
    			{
    				g.setFont(Font.decode("Trebuchet MS-BOLD-14"));
    				g.setColor(Color.white);
    				g.drawString(str, strX, strY);
    			}
    			g.setColor(Color.white); //draw balls
    			g.drawOval(xCoor, yCoor, 2*radius, 2*radius);
    			if(radius==66)  //draw only small balls (not small small, etc)
    			for(int i=0; i<smallAngle.length; i++)
    			{
    				ballsIn[i].draw(g, false, false, 1);
    			}
    		}
    	}
    	else if(integer == 1) //normal
    	{
    		if(isBack)
    		{
    			g.setFont(Font.decode("Trebuchet MS-BOLD-14"));
    			g.setColor(Color.lightGray);
    			g.drawString(str, strX, strY);
    			g.drawOval(xCoor, yCoor, 2*radius, 2*radius);
    		}
    		else if(radius>=16)
    		{
    			if(radius==66 && !transition) //label only big ball
    			{
    				g.setFont(Font.decode("Trebuchet MS-BOLD-14"));
    				g.setColor(Color.lightGray);
    				g.drawString(str, strX, strY);
    			}
    			g.setColor(color); //draw ball
    			g.fillOval(xCoor, yCoor, 2*radius, 2*radius);
    			if(radius==66 && !transition) //draw only small balls (not small small, etc)
    			for(int i=0; i<smallAngle.length; i++)
    			{
    				if(smallAngle[i]>=360)
    		  	  	  smallAngle[i]=0;
    				smallAngle[i]=smallAngle[i]+1;
    				ballsIn[i].move((int) (pathXCoor + ((double) Math.cos((smallAngle[i]*3.14)/180))*radius/2), (int) (pathYCoor + ((double) Math.sin((smallAngle[i]*3.14)/180)*radius/2)));
    				ballsIn[i].draw(g, false, false, 1);
    			}
    		}
    	}
    	else if(integer == 2) //forward
    	{    		
			if(isStillForward())
			{
			for(int cool=0; cool<ballsIn.length; cool++)
			{
				//System.out.println(ballsIn[cool].getOrder());
				if(ballsIn[cool].getX() != Gui.x[ballsIn[cool].getOrder()] || ballsIn[cool].getY() != Gui.y[ballsIn[cool].getOrder()] || ballsIn[cool].getRadius() != radius)
				{
					int incX = ballsIn[cool].getX();
  					int incY = ballsIn[cool].getY();
  					int incR = ballsIn[cool].getRadius();
  					if(incX < Gui.x[ballsIn[cool].getOrder()])
  			  	  	  incX++;
  					else if(incX > Gui.x[ballsIn[cool].getOrder()])
  			  		  incX--;
  					if(incY < Gui.y[ballsIn[cool].getOrder()])
  			  		  incY++;
  					else if(incY > Gui.y[ballsIn[cool].getOrder()])
  			  		  incY--;
  					if(incR < radius)
  			  		  incR++;
  					ballsIn[cool].reset(incX, incY, incR);
				}
			}
			for(int i=0; i<smallAngle.length; i++)
    		{
    			ballsIn[i].draw(g, false, true, 1);
    		}
    		}
    		else
    		{
				Gui.isTransitioning = false;
				
				Ball tempList[] = (Ball[]) Gui.stack.peek();
				
				Ball tempList2[] = new Ball[tempList[Gui.clickedBall].getSmallBallNum()]; //make a new temp list for those small balls
				for(int aa=0; aa<tempList[Gui.clickedBall].getSmallBallNum(); aa++)
				{
					tempList2[aa] = tempList[Gui.clickedBall].getSmallBall(aa);
					//tempList2[aa].reset(Gui.x[tempList[aa].getOrder()], Gui.y[tempList[aa].getOrder()], ((2*600)/9)/2); //reset the balls' values so they're no longer small
				}
				
				Gui.stack.push(tempList2);
			}
    		
    	}
    	else if(integer == 3) //backward
    	{
			int[] xer = connect.getSmallXCoor();
			int[] yer = connect.getSmallYCoor();
			
			if(xCoor != xer[a] || yCoor != yer[a] || radius != connect.getSmallRad())
			{
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
  			g.setColor(color); //draw ball
    		g.fillOval(xCoor, yCoor, 2*radius, 2*radius);
    	}
  	}
  	
  	public boolean isStillForward()
  	{  		
  		boolean tf = false;
  		
  		for(int i=0; i<ballsIn.length; i++)
  		{
  			if(ballsIn[i].getX() != Gui.x[ballsIn[i].getOrder()] || ballsIn[i].getY() != Gui.y[ballsIn[i].getOrder()] || ballsIn[i].getRadius() != radius)
  			{
  				tf = true;
  			}
  		}
  		return tf;
  	}
  	
  	public void doIt(String nu)
  	{
  		if(oper.equals(""))
  		{
  			if(answer)
  		  	  answer=false;
  			n1+=nu;
  		}
  		else
  		  n2+=nu;
  	}
  	
  	public void op(String st)
  	{
  		if(!n1.equals(""))
  		  oper = st;
  	}
  	
  	public void clear()
  	{
  		n1="";
  		n2="";
  		oper="";
  		answer=false;
  	}
  	
  	public void dot()
  	{
  		if(oper.equals(""))
  		  n1+=".";
  		else
  		  n2+=".";
  	}
  	
  	public void neg()
  	{
  		if(oper.equals(""))
  		  n1 = "-"+n1;
  		else
  		  n2 = "-"+n2;
  	}
  	
  	public void sq()
  	{
  		if(!n1.equals(""))
  		{
  			answer=true;
  			ans = Integer.valueOf(n1).intValue()*Integer.valueOf(n1).intValue();
  		}
  	}
  	
  	public void eq()
  	{
  		if(!oper.equals(""))
  		  answer = true;
  		if(oper.equals("+"))
  		  ans = Integer.valueOf(n1).intValue()+Integer.valueOf(n2).intValue();
  		else if(oper.equals("-"))
  		  ans = Integer.valueOf(n1).intValue()-Integer.valueOf(n2).intValue();
  		else if(oper.equals("*"))
  		  ans = Integer.valueOf(n1).intValue()*Integer.valueOf(n2).intValue();
  		else if(oper.equals("/"))
  		  ans = Integer.valueOf(n1).intValue()/Integer.valueOf(n2).intValue();
  		else if(oper.equals("%"))
  		  ans = Integer.valueOf(n1).intValue()%Integer.valueOf(n2).intValue();
  	}
  	
  	public void butInt(int xum, int yum)
  	{
  		butX = xum;
  		butY = yum;
  		click++;
  	}
  	
  	
	public void select(int xum, int yum)
	{
		selectX = xum;
		selectY = yum;
	}
	
	public void incr(int yy)
	{
		if(yy==1)
		  xIncr++;
		else if(yy==2)
		  yIncr++;
		else if(yy==3)
		{
			xIncr++;
			yIncr++;
		}
		else if(yy==4)
		  xIncr--;
		else if(yy==5)
		  yIncr--;
		else if(yy==6)
		{
			xIncr--;
			yIncr--;
		}
		else if(yy==7)
		{
			xIncr=0;
			yIncr=0;
		}
	}
	
	public int getSelectX()
	{
		return selectX;
	}
	
	public int getSelectY()
	{
		return selectY;
	}
	
	public int getXIncr()
	{
		return xIncr;
	}
	
	public int getYIncr()
	{
		return yIncr;
	}
	
	public void setCell(char c)
	{
		if(cell[selectY][selectX].length()<8)
		cell[selectY][selectX]+=c;
	}
	
	public void backspace()
	{
		if(cell[selectY][selectX].length()>0)
		  cell[selectY][selectX] = cell[selectY][selectX].substring(0, cell[selectY][selectX].length()-2);
	}
	
	public void delete()
	{
		cell[selectY][selectX] = "";
	}
}