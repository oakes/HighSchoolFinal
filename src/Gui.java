import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Stack;
import java.util.ArrayList;

public class Gui extends JPanel
	implements MouseMotionListener, MouseListener, ActionListener, KeyListener
{
	private int w = 600;
	private boolean isHovering[] = new boolean[4];
	public static Stack stack = new Stack();
	private int stacksize = 1;
	public static boolean isTransitioning;
	public static boolean isGoingBack, isHoveringBack;
	public static Ball back;
	public static int clickedBall;
	private Timer t;
	private int incX, incY, incR;
	private String games[] = {"Set Stuff On Fire", "Dodge Stuff On Fire", "Extinguish Stuff On Fire", "Jump The Rope Or Get Set On Fire"};
	private String apps[] = {"Paint", "Calculator", "WordPad", "Spreadsheet"};
	private String comp[] = {"Hard Disk", "Floppy", "CD", "Network"};
	private String docs[] = {"My Pictures", "My Word Docs", "My Spreadsheets", "My Buddies"};
	private String hd[] = {"Common Files", "Documents and Settings", "My Downloads", "Program Files"};
	private String floppy[] = {"Chomp", "Balloons", "Ramblecs", "SnackBar"};
	private String cd[] = {"Beethoven", "Bach", "Mozart", "Uematsu"};
	private String network[] = {"ayn22", "jrf15", "zso11", "opo10"};
	
	public static ArrayList cx = new ArrayList();
	public static ArrayList cy = new ArrayList();
	public static ArrayList csize = new ArrayList();
	public static ArrayList ccolor = new ArrayList();
	
	private static Ball list[] = new Ball[4];
	public static int x[] = new int[list.length];
	public static int y[] = new int[list.length];
	
	//private static int backwards;
	
	public Gui()
	{
		setBackground(new Color(0,50,100));
		addMouseMotionListener(this);
		addMouseListener(this);
		addKeyListener(this);
		
		for(int eks=0; eks<list.length; eks++)
		  if(eks%2==0) //if it's 0 or 2
		    x[eks] = (w/3)+(w/18);
		  else if(eks==1)
		    x[eks] = 2*(w/3)+(w/18);
		  else //if (eks==3)
		    x[eks] = (w/18);
		
		for(int why=0; why<list.length; why++)
		  if(why%2!=0) //if it's 1 or 3
		    y[why] = (w/3)+(w/18);
		  else if(why==0)
		    y[why] = (w/18);
		  else //if (why==2)
		    y[why] = 2*(w/3)+(w/18);
		
		list[0] = new Ball(x[0], y[0], ((2*w)/9)/2, new Color(0, 100, 150), "My Games", null, 0);
		list[1] = new Ball(x[1], y[1], ((2*w)/9)/2, new Color(0, 100, 150), "My Applications", null, 1);
		list[2] = new Ball(x[2], y[2], ((2*w)/9)/2, new Color(0, 100, 150), "My Computer", null, 2);
		list[3] = new Ball(x[3], y[3], ((2*w)/9)/2, new Color(0, 100, 150), "My Documents", null, 3);
		
		back = new Ball(w/3+w/9, w/3+w/9, w/9/2, "Back");
		
		list[0].setSmallNum(4);
		list[1].setSmallNum(3);
		list[2].setSmallNum(4);
		list[3].setSmallNum(4);
		
		for(int big=0; big<4; big++) //add small balls, set small small num to 0
		{
			list[0].addBall(games[big]);
			list[0].getSmallBall(big).setSmallNum(0);
			list[2].addBall(comp[big]);
			list[2].getSmallBall(big).setSmallNum(0);
			list[3].addBall(docs[big]);
			list[3].getSmallBall(big).setSmallNum(0);
		}
		
		list[1].addPainter("Paint");
		list[1].getSmallBall(0).setSmallNum(0);
		list[1].addCal("Calculator");
		list[1].getSmallBall(1).setSmallNum(0);
		list[1].addSpread("Spreadsheet");
		list[1].getSmallBall(2).setSmallNum(0);
		
		for(int big=0; big<4; big++) //set small small num of comp to 4
		  list[2].getSmallBall(big).setSmallNum(4);
		  	   	
		for(int small2=0; small2<4; small2++) //add small small balls, set small small small num to 0
		{
			list[2].getSmallBall(0).addBall(hd[small2]);
			list[2].getSmallBall(0).getSmallBall(small2).setSmallNum(0);
		  	list[2].getSmallBall(1).addBall(floppy[small2]);
		  	list[2].getSmallBall(1).getSmallBall(small2).setSmallNum(0);
		  	list[2].getSmallBall(2).addBall(cd[small2]);
		  	list[2].getSmallBall(2).getSmallBall(small2).setSmallNum(0);
		  	list[2].getSmallBall(3).addBall(network[small2]);
		  	list[2].getSmallBall(3).getSmallBall(small2).setSmallNum(0);
		}
		    
		stack.push(list);
		
		t = new Timer(0, this);
		t.start();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Ball tempList[] = (Ball[]) stack.peek();

		if(isTransitioning)
		{
			tempList[clickedBall].draw(g, false, false, 2); //for loop print all ballsIn
		}
		else if(isGoingBack)
		{
		  	if(isStillBacking())
		  	{
		  		for(int dum=0; dum<tempList.length; dum++)
		  		{
		  			tempList[dum].draw(g, false, false, 3); //get connect, if not yet at smallXCoor/smallYCoor, change
		  		}
		  	}
		  	else
			{
				isGoingBack = false;
				stack.pop();
				//System.out.println("pop");
				back.move(w/3+w/9, w/3+w/9);
				back.setString("Back");
				Ball tempList2[] = (Ball[]) stack.peek();
				tempList2[clickedBall].resetSmall();
			}
		}
		else
		{
			for(int i=0; i<tempList.length; i++)
			  if(isHovering[i])
			    tempList[i].draw(g, isHovering[i], false, 0);
			  else
			    tempList[i].draw(g, isHovering[i], false, 1);
			if(stacksize>1)
			if(isHoveringBack)
			  back.draw(g, true, false, 0);
			else
			  back.draw(g, false, false, 1);
		}
	}
	
	public void mouseMoved(MouseEvent e)
	{
		Ball tempList[] = (Ball[]) stack.peek();
		
		if(back.isInside(e.getX(), e.getY()))
		  isHoveringBack = true;
		else
		  isHoveringBack = false;
		
		if(tempList.length>1)
		{
			for(int i=0; i<tempList.length; i++)
			{
				if(tempList[i].isInside(e.getX(), e.getY()))
				  isHovering[i]=true;
				else
				  isHovering[i]=false;
			}
		}
		else if(tempList[0].getString().equals("Paint") && !isGoingBack);
		{
			int xCoor = tempList[0].getX();
			int yCoor = tempList[0].getY();
			int radius = tempList[0].getRadius();
			
			if(e.getX() > xCoor+3*((2*radius)/3)/40+2*(radius*2)/30 && e.getX() < (xCoor+3*((2*radius)/3)/40+2*(radius*2)/30)+(2*radius-(3*((2*radius)/3)/40+2*(radius*2)/30)) && e.getY() > yCoor+radius/3 && e.getY() < (yCoor+radius/3)+(2*radius)/3)
			{
				tempList[0].setMouse(e.getX(), e.getY());
			}
			else
			{
				tempList[0].setMouse(0, 0);
			}
		}
	}
	public void mouseDragged(MouseEvent e)
	{
		Ball tempList[] = (Ball[]) stack.peek();
		
		if(tempList.length==1)
		if(tempList[0].getString().equals("Paint") && !isGoingBack)
		{
			int xCoor = tempList[0].getX();
			int yCoor = tempList[0].getY();
			int radius = tempList[0].getRadius();
			
			if(e.getX() > xCoor+3*((2*radius)/3)/40+2*(radius*2)/30 && e.getX() < xCoor+3*((2*radius)/3)/40+2*(radius*2)/30+2*radius-(3*((2*radius)/3)/40+2*(radius*2)/30) && e.getY() > yCoor+radius/3 && e.getY() < yCoor+radius/3+(2*radius)/3)
			{
				int asdf = tempList[0].getSizer();
				int asdf2;
				if(asdf==1)
				  asdf2=radius/60;
				else if(asdf==2)
				  asdf2=radius/40;
				else //if(asdf==3)
				  asdf2=radius/30;
				//System.out.println("add");
				
				cx.add(new Integer(e.getX() - asdf2/2));
				cy.add(new Integer(e.getY() - asdf2/2));
				csize.add(new Integer(asdf));
				ccolor.add(tempList[0].getColor());
				
				tempList[0].setMouse(e.getX(), e.getY());
			}
			else
			  tempList[0].setMouse(0, 0);
		}
		else if(tempList[0].getString().equals("Spreadsheet") && !isGoingBack)
		{
			int xCoor = tempList[0].getX();
			int yCoor = tempList[0].getY();
			int radius = tempList[0].getRadius();
			
			int width = (3*(2*radius)/5)/6;
			int space = radius/20;
			
			int xx[] = new int[6];;
			int yy[] = new int[22];
			
			for(int l=0; l<6; l++)
			{
				xx[l] = xCoor+(2*radius)/5+l*width;
				for(int z=0; z<22; z++)
				{
					yy[z] = yCoor+(2*radius)/5+(z+2)*space;
				}
			}
			
			if(tempList[0].getSelectX()+tempList[0].getXIncr()+1<6 && tempList[0].getSelectX()+tempList[0].getXIncr()+1<6 && tempList[0].getSelectY()+tempList[0].getYIncr()+1<21 && tempList[0].getSelectY()+tempList[0].getYIncr()+1<21)
			  if(e.getX() > xx[tempList[0].getSelectX()+tempList[0].getXIncr()+1] && e.getX() < xx[tempList[0].getSelectX()+tempList[0].getXIncr()+1]+width && e.getY() > yy[tempList[0].getSelectY()+tempList[0].getYIncr()+1] && e.getY() < yy[tempList[0].getSelectY()+tempList[0].getYIncr()+1]+space)
			    tempList[0].incr(3);
			if(tempList[0].getSelectX()+tempList[0].getXIncr()+1<6 && tempList[0].getSelectX()+tempList[0].getXIncr()+1<6)
			  if(e.getX() > xx[tempList[0].getSelectX()+tempList[0].getXIncr()+1] && e.getX() < xx[tempList[0].getSelectX()+tempList[0].getXIncr()+1]+width)
			    tempList[0].incr(1);
			if(tempList[0].getSelectY()+tempList[0].getYIncr()+1<21 && tempList[0].getSelectY()+tempList[0].getYIncr()+1<21)
			  if(e.getY() > yy[tempList[0].getSelectY()+tempList[0].getYIncr()+1] && e.getY() < yy[tempList[0].getSelectY()+tempList[0].getYIncr()+1]+space)
			    tempList[0].incr(2);
			if(tempList[0].getSelectX()+tempList[0].getXIncr()>0 && tempList[0].getSelectY()+tempList[0].getYIncr()>0)
			  if(e.getX() > xx[tempList[0].getSelectX()+tempList[0].getXIncr()-1] && e.getX() < xx[tempList[0].getSelectX()+tempList[0].getXIncr()-1]+width && e.getY() > yy[tempList[0].getSelectY()+tempList[0].getYIncr()-1] && e.getY() < yy[tempList[0].getSelectY()+tempList[0].getYIncr()-1]+space)
			    tempList[0].incr(6);
			if(tempList[0].getSelectX()+tempList[0].getXIncr()>0)
			  if(e.getX() > xx[tempList[0].getSelectX()+tempList[0].getXIncr()-1] && e.getX() < xx[tempList[0].getSelectX()+tempList[0].getXIncr()-1]+width)
			    tempList[0].incr(4);
			if(tempList[0].getSelectY()+tempList[0].getYIncr()>0)
			  if(e.getY() > yy[tempList[0].getSelectY()+tempList[0].getYIncr()-1] && e.getY() < yy[tempList[0].getSelectY()+tempList[0].getYIncr()-1]+space)
			    tempList[0].incr(5);
		}
	}
	
	
	public void mouseClicked(MouseEvent e)
	{
		Ball tempList[] = (Ball[]) stack.peek(); //peek the current level (list) of balls
		
		if(isHoveringBack)
		{
			stacksize--;
			isGoingBack = true;
		}
		
		if(tempList.length>1)
		{
			for(int i=0; i<tempList.length; i++)
			{
				if(isHovering[i]==true) //if we're hovering over any balls
				{
					if(tempList[i].getSmallBallNum()>0) //if there are small balls in the clicked ball
					{
						stacksize++;
						isTransitioning = true;
						clickedBall = i;
					}
					else //if(tempList[i].getString() == "Paint")
					{
						stacksize++;
						isTransitioning = true;
						clickedBall = i;
					}
				}
			}
		}
		else if(tempList[0].getString().equals("Paint") && !isGoingBack) //(tempList[0].getString().equals("Paint"));
		{
			int xCoor = tempList[0].getX();
			int yCoor = tempList[0].getY();
			int radius = tempList[0].getRadius();
						
			if(e.getX() > (xCoor+((2*radius)/3)/40+((radius*2)/30)/2) && e.getX() < (xCoor+((2*radius)/3)/40+((radius*2)/30)/2)+((radius*2)/30+((2*radius)/3)/40) && e.getY() > (yCoor+radius/3+7*((2*radius)/3)/40+6*(radius*2)/30) && e.getY() < (yCoor+radius/3+7*((2*radius)/3)/40+6*(radius*2)/30)+(2*(radius*2)/30+((2*radius)/3)/40)/3)
			  tempList[0].setSize(1);
			else if(e.getX() > (xCoor+((2*radius)/3)/40+((radius*2)/30)/2) && e.getX() < (xCoor+((2*radius)/3)/40+((radius*2)/30)/2)+((radius*2)/30+((2*radius)/3)/40) && e.getY() > (yCoor+radius/3+7*((2*radius)/3)/40+6*(radius*2)/30)+(2*(radius*2)/30+((2*radius)/3)/40)/3 && e.getY() < (yCoor+radius/3+7*((2*radius)/3)/40+6*(radius*2)/30)+2*(2*(radius*2)/30+2*((2*radius)/3)/40)/3)
			  tempList[0].setSize(2);
			else if(e.getX() > (xCoor+((2*radius)/3)/40+((radius*2)/30)/2) && e.getX() < (xCoor+((2*radius)/3)/40+((radius*2)/30)/2)+((radius*2)/30+((2*radius)/3)/40) && e.getY() > (yCoor+radius/3+7*((2*radius)/3)/40+6*(radius*2)/30)+2*(2*(radius*2)/30+((2*radius)/3)/40)/3 && e.getY() < (yCoor+radius/3+7*((2*radius)/3)/40+6*(radius*2)/30)+(2*(radius*2)/30+2*((2*radius)/3)/40))
			  tempList[0].setSize(3);
			else if(e.getX() > xCoor+((2*radius)/3)/40 && e.getX() < xCoor+((2*radius)/3)/40+(radius*2)/30 && e.getY() > yCoor+radius/3+((2*radius)/3)/40 && e.getY() < yCoor+radius/3+((2*radius)/3)/40 + (radius*2)/30)
			  tempList[0].setColor(Color.white);
			else if(e.getX() > xCoor+((2*radius)/3)/40 && e.getX() < xCoor+((2*radius)/3)/40 + (radius*2)/30 && e.getY() > yCoor+radius/3+2*((2*radius)/3)/40+(radius*2)/30 && e.getY() < yCoor+radius/3+2*((2*radius)/3)/40+(radius*2)/30+(radius*2)/30)
			  tempList[0].setColor(Color.black);
			else if(e.getX() > xCoor+((2*radius)/3)/40 && e.getX() < xCoor+((2*radius)/3)/40+(radius*2)/30 && e.getY() > yCoor+radius/3+3*((2*radius)/3)/40+2*(radius*2)/30 && e.getY() < yCoor+radius/3+3*((2*radius)/3)/40+2*(radius*2)/30+(radius*2)/30)
			  tempList[0].setColor(Color.lightGray);
			else if(e.getX() > xCoor+((2*radius)/3)/40 && e.getX() < xCoor+((2*radius)/3)/40+(radius*2)/30 && e.getY() > yCoor+radius/3+4*((2*radius)/3)/40+3*(radius*2)/30 && e.getY() < yCoor+radius/3+4*((2*radius)/3)/40+3*(radius*2)/30+(radius*2)/30)
			  tempList[0].setColor(Color.darkGray);
			else if(e.getX() > xCoor+((2*radius)/3)/40 && e.getX() < xCoor+((2*radius)/3)/40+(radius*2)/30 && e.getY() > yCoor+radius/3+5*((2*radius)/3)/40+4*(radius*2)/30 && e.getY() < yCoor+radius/3+5*((2*radius)/3)/40+4*(radius*2)/30+(radius*2)/30)
			  tempList[0].setColor(Color.blue);
			else if(e.getX() > xCoor+((2*radius)/3)/40 && e.getX() < xCoor+((2*radius)/3)/40+(radius*2)/30 && e.getY() > yCoor+radius/3+6*((2*radius)/3)/40+5*(radius*2)/30 && e.getY() < yCoor+radius/3+6*((2*radius)/3)/40+5*(radius*2)/30+(radius*2)/30)
			  tempList[0].setColor(Color.green);
			else if(e.getX() > xCoor+(radius*2)/30+2*((2*radius)/3)/40 && e.getX() < xCoor+(radius*2)/30+2*((2*radius)/3)/40+(radius*2)/30 && e.getY() > yCoor+radius/3+((2*radius)/3)/40 && e.getY() < yCoor+radius/3+((2*radius)/3)/40+(radius*2)/30)
			  tempList[0].setColor(Color.cyan);
			else if(e.getX() > xCoor+(radius*2)/30+2*((2*radius)/3)/40 && e.getX() < xCoor+(radius*2)/30+2*((2*radius)/3)/40+(radius*2)/30 && e.getY() > yCoor+radius/3+2*((2*radius)/3)/40+(radius*2)/30 && e.getY() < yCoor+radius/3+2*((2*radius)/3)/40+(radius*2)/30+(radius*2)/30)
			  tempList[0].setColor(Color.red);
			else if(e.getX() > xCoor+(radius*2)/30+2*((2*radius)/3)/40 && e.getX() < xCoor+(radius*2)/30+2*((2*radius)/3)/40+(radius*2)/30 && e.getY() > yCoor+radius/3+3*((2*radius)/3)/40+2*(radius*2)/30 && e.getY() < yCoor+radius/3+3*((2*radius)/3)/40+2*(radius*2)/30+(radius*2)/30)
			  tempList[0].setColor(Color.magenta);
			else if(e.getX() > xCoor+(radius*2)/30+2*((2*radius)/3)/40 && e.getX() < xCoor+(radius*2)/30+2*((2*radius)/3)/40+(radius*2)/30 && e.getY() > yCoor+radius/3+4*((2*radius)/3)/40+3*(radius*2)/30 && e.getY() < yCoor+radius/3+4*((2*radius)/3)/40+3*(radius*2)/30+(radius*2)/30)
			  tempList[0].setColor(Color.pink);
			else if(e.getX() > xCoor+(radius*2)/30+2*((2*radius)/3)/40 && e.getX() < xCoor+(radius*2)/30+2*((2*radius)/3)/40+(radius*2)/30 && e.getY() > yCoor+radius/3+5*((2*radius)/3)/40+4*(radius*2)/30 && e.getY() < yCoor+radius/3+5*((2*radius)/3)/40+4*(radius*2)/30+(radius*2)/30)
			  tempList[0].setColor(Color.orange);
			else if(e.getX() > xCoor+(radius*2)/30+2*((2*radius)/3)/40 && e.getX() < xCoor+(radius*2)/30+2*((2*radius)/3)/40+(radius*2)/30 && e.getY() > yCoor+radius/3+6*((2*radius)/3)/40+5*(radius*2)/30 && e.getY() < yCoor+radius/3+6*((2*radius)/3)/40+5*(radius*2)/30 + (radius*2)/30)
			  tempList[0].setColor(Color.yellow);
			else if(e.getX() > xCoor+3*((2*radius)/3)/40+2*(radius*2)/30 && e.getX() < xCoor+3*((2*radius)/3)/40+2*(radius*2)/30+2*radius-(3*((2*radius)/3)/40+2*(radius*2)/30) && e.getY() > yCoor+radius/3 && e.getY() < yCoor+radius/3+(2*radius)/3)
			{
				int asdf = tempList[0].getSizer();
				int asdf2;
				if(asdf==1)
				  asdf2=radius/60;
				else if(asdf==2)
				  asdf2=radius/40;
				else //if(asdf==3)
				  asdf2=radius/30;
				
				cx.add(new Integer(e.getX() - asdf2/2));
				cy.add(new Integer(e.getY() - asdf2/2));
				csize.add(new Integer(asdf));
				ccolor.add(tempList[0].getColor());
			}
		}
		else if(tempList[0].getString().equals("Calculator") && !isGoingBack)
		{
			int xCoor = tempList[0].getX();
			int yCoor = tempList[0].getY();
			int radius = tempList[0].getRadius();
			
			int width = (((2*radius)/3-2*(radius/20))-3*radius/20)/4;
			
			int xx[] = new int[4];
			int yy[] = new int[5];
			
			for(int p=0; p<4; p++)
			{
				xx[p] = xCoor+p*width+p*radius/20+(2*radius)/3+radius/20;
				for(int m=0; m<5; m++)
				{
					yy[m] = yCoor+(2*radius)/3+m*width+(m+3)*radius/20;
					if(e.getX() > xx[p] && e.getX() < xx[p]+width && e.getY() > yy[m] && e.getY() < yy[m]+width)
					{
						if(p==0 && m==0)
							tempList[0].clear();
						else if(p==1 && m==0)
							tempList[0].doIt("7");
						else if(p==2 && m==0)
							tempList[0].doIt("8");
						else if(p==3 && m==0)
							tempList[0].doIt("9");
						else if(p==0 && m==1)
							tempList[0].sq();
						else if(p==1 && m==1)
							tempList[0].doIt("4");
						else if(p==2 && m==1)
							tempList[0].doIt("5");
						else if(p==3 && m==1)
							tempList[0].doIt("6");
						else if(p==0 && m==2)
							tempList[0].op("%");
						else if(p==1 && m==2)
							tempList[0].doIt("1");
						else if(p==2 && m==2)
							tempList[0].doIt("2");
						else if(p==3 && m==2)
							tempList[0].doIt("3");
						else if(p==0 && m==3)
							tempList[0].eq();
						else if(p==1 && m==3)
							tempList[0].doIt("0");
						else if(p==2 && m==3)
							tempList[0].neg();
						else if(p==3 && m==3)
							tempList[0].dot();
						else if(p==0 && m==4)
							tempList[0].op("+");
						else if(p==1 && m==4)
							tempList[0].op("-");
						else if(p==2 && m==4)
							tempList[0].op("*");
						else if(p==3 && m==4)
							tempList[0].op("/");
						tempList[0].butInt(p, m);
					}
				}
			}
			
			/*if(e.getX() > xCoor+(2*radius)/3+radius/20 && e.getX() < xCoor+(2*radius)/3+radius/20+width && e.getY() > yCoor+(2*radius)/3+3*radius/20 && e.getY() < yCoor+(2*radius)/3+3*radius/20+width)
			  tempList[0].clear();
			else if(e.getX() > xCoor+(2*radius)/3+radius/20 && e.getX() < xCoor+(2*radius)/3+radius/20+width && e.getY() > yCoor+(2*radius)/3+width+4*radius/20 && e.getY() < yCoor+(2*radius)/3+width+4*radius/20+width)
			  tempList[0].sq();
			else if(e.getX() > xCoor+(2*radius)/3+radius/20 && e.getX() < xCoor+(2*radius)/3+radius/20+width && e.getY() > yCoor+(2*radius)/3+2*width+5*radius/20 && e.getY() < yCoor+(2*radius)/3+2*width+5*radius/20+width)
			  tempList[0].op("%");
			else if(e.getX() > xCoor+(2*radius)/3+radius/20 && e.getX() < xCoor+(2*radius)/3+radius/20+width && e.getY() > yCoor+(2*radius)/3+3*width+6*radius/20 && e.getY() < yCoor+(2*radius)/3+3*width+6*radius/20+width)
			  tempList[0].eq();
			else if(e.getX() > xCoor+width+radius/20+(2*radius)/3+radius/20 && e.getX() < xCoor+width+radius/20+(2*radius)/3+radius/20+width && e.getY() > yCoor+(2*radius)/3+3*radius/20 && e.getY() < yCoor+(2*radius)/3+3*radius/20+width)
			  tempList[0].doIt("7");
			else if(e.getX() > xCoor+width+radius/20+(2*radius)/3+radius/20 && e.getX() < xCoor+width+radius/20+(2*radius)/3+radius/20+width && e.getY() > yCoor+(2*radius)/3+width+4*radius/20 && e.getY() < yCoor+(2*radius)/3+width+4*radius/20+width)
			  tempList[0].doIt("4");
			else if(e.getX() > xCoor+width+radius/20+(2*radius)/3+radius/20 && e.getX() < xCoor+width+radius/20+(2*radius)/3+radius/20+width && e.getY() > yCoor+(2*radius)/3+2*width+5*radius/20 && e.getY() < yCoor+(2*radius)/3+2*width+5*radius/20+width)
			  tempList[0].doIt("1");
			else if(e.getX() > xCoor+width+radius/20+(2*radius)/3+radius/20 && e.getX() < xCoor+width+radius/20+(2*radius)/3+radius/20+width && e.getY() > yCoor+(2*radius)/3+3*width+6*radius/20 && e.getY() < yCoor+(2*radius)/3+3*width+6*radius/20+width)
			  tempList[0].doIt("0");
			else if(e.getX() > xCoor+2*width+2*radius/20+(2*radius)/3+radius/20 && e.getX() < xCoor+2*width+2*radius/20+(2*radius)/3+radius/20+width && e.getY() > yCoor+(2*radius)/3+3*radius/20 && e.getY() < yCoor+(2*radius)/3+3*radius/20+width)
			  tempList[0].doIt("8");
			else if(e.getX() > xCoor+2*width+2*radius/20+(2*radius)/3+radius/20 && e.getX() < xCoor+2*width+2*radius/20+(2*radius)/3+radius/20+width && e.getY() > yCoor+(2*radius)/3+width+4*radius/20 && e.getY() < yCoor+(2*radius)/3+width+4*radius/20+width)
			  tempList[0].doIt("5");
			else if(e.getX() > xCoor+2*width+2*radius/20+(2*radius)/3+radius/20 && e.getX() < xCoor+2*width+2*radius/20+(2*radius)/3+radius/20+width && e.getY() > yCoor+(2*radius)/3+2*width+5*radius/20 && e.getY() < yCoor+(2*radius)/3+2*width+5*radius/20+width)
			  tempList[0].doIt("2");
			else if(e.getX() > xCoor+2*width+2*radius/20+(2*radius)/3+radius/20 && e.getX() < xCoor+2*width+2*radius/20+(2*radius)/3+radius/20+width && e.getY() > yCoor+(2*radius)/3+3*width+6*radius/20 && e.getY() < yCoor+(2*radius)/3+3*width+6*radius/20+width)
			  tempList[0].neg();
			else if(e.getX() > xCoor+3*width+3*radius/20+(2*radius)/3+radius/20 && e.getX() < xCoor+3*width+3*radius/20+(2*radius)/3+radius/20+width && e.getY() > yCoor+(2*radius)/3+3*radius/20 && e.getY() < yCoor+(2*radius)/3+3*radius/20+width)
			  tempList[0].doIt("9");
			else if(e.getX() > xCoor+3*width+3*radius/20+(2*radius)/3+radius/20 && e.getX() < xCoor+3*width+3*radius/20+(2*radius)/3+radius/20+width && e.getY() > yCoor+(2*radius)/3+width+4*radius/20 && e.getY() < yCoor+(2*radius)/3+width+4*radius/20+width)
			  tempList[0].doIt("6");
			else if(e.getX() > xCoor+3*width+3*radius/20+(2*radius)/3+radius/20 && e.getX() < xCoor+3*width+3*radius/20+(2*radius)/3+radius/20+width && e.getY() > yCoor+(2*radius)/3+2*width+5*radius/20 && e.getY() < yCoor+(2*radius)/3+2*width+5*radius/20+width)
			  tempList[0].doIt("3");
			else if(e.getX() > xCoor+3*width+3*radius/20+(2*radius)/3+radius/20 && e.getX() < xCoor+3*width+3*radius/20+(2*radius)/3+radius/20+width && e.getY() > yCoor+(2*radius)/3+3*width+6*radius/20 && e.getY() < yCoor+(2*radius)/3+3*width+6*radius/20+width)
			  tempList[0].dot();
			else if(e.getX() > xCoor+(2*radius)/3+radius/20 && e.getX() < xCoor+(2*radius)/3+radius/20+width && e.getY() > yCoor+(2*radius)/3+4*width+7*radius/20 && e.getY() < yCoor+(2*radius)/3+4*width+7*radius/20+width)
			  tempList[0].op("+");
			else if(e.getX() > xCoor+width+radius/20+(2*radius)/3+radius/20 && e.getX() < xCoor+width+radius/20+(2*radius)/3+radius/20+width && e.getY() > yCoor+(2*radius)/3+4*width+7*radius/20 && e.getY() < yCoor+(2*radius)/3+4*width+7*radius/20+width)
			  tempList[0].op("-");
			else if(e.getX() > xCoor+2*width+2*radius/20+(2*radius)/3+radius/20 && e.getX() < xCoor+2*width+2*radius/20+(2*radius)/3+radius/20+width && e.getY() > yCoor+(2*radius)/3+4*width+7*radius/20 && e.getY() < yCoor+(2*radius)/3+4*width+7*radius/20+width)
			  tempList[0].op("*");
			else if(e.getX() > xCoor+3*width+3*radius/20+(2*radius)/3+radius/20 && e.getX() < xCoor+3*width+3*radius/20+(2*radius)/3+radius/20+width && e.getY() > yCoor+(2*radius)/3+4*width+7*radius/20 && e.getY() < yCoor+(2*radius)/3+4*width+7*radius/20+width)
			  tempList[0].op("/");*/
		}
		else if(tempList[0].getString().equals("Spreadsheet") && !isGoingBack)
		{
			int xCoor = tempList[0].getX();
			int yCoor = tempList[0].getY();
			int radius = tempList[0].getRadius();
			
			int width = (3*(2*radius)/5)/6;
			int space = radius/20;
			
			int xx[] = new int[6];;
			int yy[] = new int[22];
			
			for(int l=0; l<6; l++)
			{
				xx[l] = xCoor+(2*radius)/5+l*width;
				for(int z=0; z<22; z++)
				{
					yy[z] = yCoor+(2*radius)/5+(z+2)*space;
					
					if(e.getX() > xx[l] && e.getX() < xx[l]+width && e.getY() > yy[z] && e.getY() < yy[z]+space)
					{
						tempList[0].select(l, z);
					}
				}
			}
			
			tempList[0].incr(7);
		}
	}
	public void mouseEntered(MouseEvent e)
	{
	}
	public void mouseExited(MouseEvent e)
	{
	}
	public void mousePressed(MouseEvent e)
	{
	}
	public void mouseReleased(MouseEvent e)
	{
	}
	
	public void actionPerformed(ActionEvent e)
	{
		repaint();
		requestFocus();
	}
  	
  	public boolean isStillBacking() //connect ball
  	{
  		Ball tempList[] = (Ball[]) stack.peek();
  		Ball ball = tempList[0].getConnect();
  		
  		int[] xer = ball.getSmallXCoor();
  		int[] yer = ball.getSmallYCoor();
  		
  		boolean tf = false;
  		
  		if(tempList.length>1)
  		for(int i=0; i<ball.getSmallBallNum(); i++)
  		{
  			if(tempList[i].getX() != xer[tempList[i].getOrder()] || tempList[i].getY() != yer[tempList[i].getOrder()] || tempList[i].getRadius() != ball.getSmallRad())
  			{
  				tf = true;
  			}
  		}
  		else if(tempList.length==1)
  		if(tempList[0].getX() != x[tempList[0].getOrder()] || tempList[0].getY() != y[tempList[0].getOrder()] || tempList[0].getRadius() != ball.getRadius())
  		{
  			tf = true;
  		}
  		
  		return tf;
  	}
  	
  	
  	public void keyPressed (KeyEvent e)
  	{
  		Ball tempList[] = (Ball[]) stack.peek();
  		if(tempList[0].getString().equals("Spreadsheet") && !isGoingBack)
  		{
  			//System.out.println("pressed");
  		
  		int code = e.getKeyCode();
	
	    switch(code)
	    {
	    	case KeyEvent.VK_ENTER:
	    		if(tempList[0].getSelectY()<21)
				  tempList[0].select(tempList[0].getSelectX(), tempList[0].getSelectY()+1);
		        break;
	      	
	    	case KeyEvent.VK_UP:
	    		if(tempList[0].getSelectY()>0)
	    	  	  tempList[0].select(tempList[0].getSelectX(), tempList[0].getSelectY()-1);
	    	  	break;
	      	
	    	case KeyEvent.VK_DOWN:
	    		if(tempList[0].getSelectY()<21)
	    	  	  tempList[0].select(tempList[0].getSelectX(), tempList[0].getSelectY()+1);
	    	  	break;
	        
	    	case KeyEvent.VK_LEFT:
	    		if(tempList[0].getSelectX()>0)
	    	  	  tempList[0].select(tempList[0].getSelectX()-1, tempList[0].getSelectY());
	    	  	break;
	      	
	    	case KeyEvent.VK_RIGHT:
	    		if(tempList[0].getSelectX()<6)
	    	  	  tempList[0].select(tempList[0].getSelectX()+1, tempList[0].getSelectY());
	    	  	break;
	    	
	    	case KeyEvent.VK_BACK_SPACE:
	    		tempList[0].backspace();
	    		break;
	    		
	    	case KeyEvent.VK_DELETE:
	    		tempList[0].delete();
	    		break;
	    }
	 	}
	}

	public void keyReleased (KeyEvent e)
	{
	}

  	public void keyTyped (KeyEvent e)
  	{
  		Ball tempList[] = (Ball[]) stack.peek();
  		if(tempList[0].getString().equals("Spreadsheet") && !isGoingBack)
  		{
  			char ch = e.getKeyChar();
  			//String st = KeyEvent.getKeyText(code);
  			
  			tempList[0].setCell(ch);
  			//System.out.println("typed");
	 	}
  	}
}