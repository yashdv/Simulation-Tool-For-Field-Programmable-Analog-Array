import javax.swing.*;
import java.awt.*;

public class OPAM extends JPanel{
	
	private int x1,y1,x2,y2,x3,y3,x4,y4,ln,wd;
	private double ten, twentyx, twentyy, twfive, seventy;
	opvalues temp;
	private Dimension dim;
	
	OPAM(int x1,int y1,int length,int width,opvalues a)
	{
		this.x1=x1;
		this.y1=y1;
		ln=length;
		wd=width;
		x2=x1+length;
		x3=x2;
		x4=x1;
		y2=y1;
		y3=y1+width;
		y4=y3;
		temp=a;
		this.setBackground(Color.WHITE);
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		ten = (10*dim.height)/1080;
		twentyy = (20*dim.height)/1080;
		twentyx = (20*dim.width)/1920;
		twfive = (25*dim.width)/1920;
		seventy = (70*dim.height)/1080;
	};
	
	// lines around
	private void formopam(Graphics j,int x1,int y1,int length,int width,int flag)
	{ // flag is not scaled like other variables based on resolution
		j.setColor(Color.BLUE);
	j.drawLine(x1,y1,x1+flag*length,y1+(width/2));
	j.drawLine(x1+flag*length,y1+(width/2),x1,y1+width);
	j.drawLine(x1,y1,x1,y1+width);
	j.drawLine(x1+flag*length,y1+width/6,x1+flag*length,y1+5*(width/6));
	}
	
	// opam
	private void Bigopam(Graphics j,int x1,int y1,int length,int width,int flag)
	{
		j.setColor(Color.BLUE);
		j.drawLine(x1,y1,x1+flag*length,y1+(width/2));
		j.drawLine(x1+flag*length,y1+(width/2),x1,y1+width);
		j.drawLine(x1,y1,x1,y1+width);
		j.drawLine(x1+flag*length,y1,x1+flag*length,y1+width/2);
	}

	// diode
	private void smallopam(Graphics j,int x1,int y1,int length,int width,int flag)
	{
		j.drawLine(x1,y1,x1+(length/3),y1);
		j.drawLine(x1+2*(length/3),y1,x1+length,y1);
		if(flag==1)
			x1=x1+(length/3);
		else
			x1=x1+2*(length/3);
		y1=y1-width/2;
		formopam(j,x1,y1,length/3,width,flag);
	}
	
	// all the resistors
	public void resistor_sideward(Graphics j,int xcord,int ycord,int length,int var)
	{
		j.drawLine(xcord,ycord,xcord+(length/4),ycord);
		int i,k,x1=xcord+length/4,y1=ycord,a;
		for(i=0;i<10;i++)
		{
			if(i%2!=0)
				k=1;
			else
				k=-1;
			a=y1+k*wd/20;
			j.drawLine(x1,y1,x1+(length/20),a);
			y1=a;
			x1=x1+(length/20);
		}
		//Font font = new Font("Serif", Font.PLAIN,length/10);

		int f = 20*dim.height/1080;
		Font font = new Font("Serif", Font.PLAIN,f);
		j.setFont(font);
		j.setColor(Color.black);
		if(var==1)
		{
			j.drawString(temp.r1,xcord+length/4+(int)twfive,y1-(wd/20)-2);
			j.drawString(temp.v1,xcord,y1-2);
		}
		else if(var==2)
		{
			j.drawString(temp.r2,xcord+length/4+(int)twfive,y1-(wd/20)-2);
			j.drawString(temp.v2,xcord,y1-2);
		}

		else if(var==3)
		{
			j.drawString(temp.r3,xcord+length/4+(int)twfive,y1-(wd/20)-2);
			j.drawString(temp.v3,xcord,y1-2);
			j.drawString(temp.vdiode,xcord,y1-2+wd/6);
			j.drawString(temp.vdown,xcord,y1-2+wd/3+(int)seventy);
		}
		else
		{
			j.drawString(temp.rup,xcord+length/4,y1-(wd/20)-2);
		}

		j.setColor(Color.BLUE);
		j.drawLine(x1,ycord,x1+(length/4),ycord);

	}
	// vertical resistor
	public void resistor_upward(Graphics j,int xcord,int ycord,int length)
	{
		j.drawLine(xcord,ycord,xcord,ycord+length/4);
		int i,k,x1=xcord,y1=ycord+length/4,a;
		for(i=0;i<10;i++)
		{
			if(i%2!=0)
				k=1;
			else
				k=-1;
			a=x1+k*wd/20;
			j.drawLine(x1,y1,a,y1+(length/20));
			x1=a;
			y1=y1+(length/20);
		}
		//Font font = new Font("Serif", Font.PLAIN,length/10);
		int f = 20*dim.height/1080;
		Font font = new Font("Serif", Font.PLAIN,f);
		j.setFont(font);
		j.setColor(Color.black);
		j.drawString(temp.rdown,xcord,ycord+length/2);
		j.setColor(Color.BLUE);
		j.drawLine(x1,ycord+3*(length/4),x1,ycord+length);
	}
	
	// draw ground
	public void drawground(Graphics j,int x1,int y1)
	{
		int d=(int)twentyx,c=0;
		for(int i=0;i<4;i++)
		{
			j.drawLine(x1-d,y1+c,x1+d,y1+c);
			d=d-5;
			c+=5;

		}
	}
	
	// draw open or close switch
	void switches(Graphics j,int x1,int y1,int ln,int l)//drawing switches according to input
	{
		j.setColor(Color.magenta);
		if(temp.flag!=null)
		{
			if(Integer.parseInt(temp.flag)==1)
				j.drawLine(x1+2*(ln/3)-ln/6,y1+3*(l/2),x1+ln/3+ln/8,y1+3*(l/2)-l/8);

			else if(Integer.parseInt(temp.flag)==2)
				j.drawLine(x1+2*(ln/3)-ln/6,y1+3*(l/2),x1+ln/3+ln/8,y1+3*(l/2));

			else if(Integer.parseInt(temp.flag)==3)
				j.drawLine(x1+2*(ln/3)-ln/6,y1+3*(l/2),x1+ln/3+ln/8,y1+3*l-3*(l/2)+l/8);
		}
		j.setColor(Color.BLUE);
	}
	/*public void draw_mosfet_sideward(Graphics j,int xlen,int ylen,int length1)
	{
		j.drawLine(xlen, ylen, xlen+length1/3, ylen);
		j.drawLine(xlen+length1/3,ylen,xlen+length1/3,ylen-length1/8);
		j.drawLine(xlen+2*(length1/3),ylen,xlen+2*(length1/3),ylen-length1/8);
		j.drawLine(xlen+2*(length1/3), ylen, xlen+length1, ylen);
		j.drawLine(xlen+length1/3,ylen-length1/8,xlen+2*(length1/3),ylen-length1/8);
		j.drawLine(xlen+length1/3,ylen-length1/8-length1/24,xlen+2*(length1/3),ylen-length1/8-length1/24);
		j.drawLine(xlen+length1/2,ylen-length1/8-length1/24,xlen+length1/2,ylen-length1/8-length1/24-length1/16);
		j.drawLine(xlen+length1/2,ylen-length1/8-length1/24-length1/16,xlen+length1/2+length1/4,ylen-length1/8-length1/24-length1/16);
		j.drawLine(xlen+length1/2+length1/4,ylen-length1/8-length1/24-length1/16,xlen+length1/2+length1/4,ylen);
	}
	public void draw_mosfet_upward(Graphics j,int xlen,int ylen, int length1)
	{
		j.drawLine(xlen, ylen, xlen, ylen+length1/3);
		j.drawLine(xlen,ylen+length1/3,xlen-length1/8,ylen+length1/3);
		j.drawLine(xlen,ylen+2*(length1/3),xlen-length1/8,ylen+2*(length1/3));
		j.drawLine(xlen, ylen+2*(length1/3), xlen, ylen+length1);
		j.drawLine(xlen-length1/8,ylen+length1/3,xlen-length1/8,ylen+2*(length1/3));
		j.drawLine(xlen-length1/8-length1/24,ylen+length1/3,xlen-length1/8-length1/24,ylen+2*(length1/3));
		j.drawLine(xlen-length1/8-length1/24,ylen+length1/2,xlen-length1/8-length1/24-length1/16,ylen+length1/2);
		j.drawLine(xlen-length1/8-length1/24-length1/16,ylen+length1/2,xlen-length1/8-length1/24-length1/16,ylen+length1/2-length1/4);
		j.drawLine(xlen-length1/8-length1/24-length1/16,ylen+length1/2-length1/4,xlen,ylen+length1/2-length1/4);
	}*/
	
	// the functions which calls the above modules to draw stuff in itself
	public void justpaint(Graphics j,int x1,int y1)
	{
		int l=wd/6;
		//adding big opam
		Bigopam(j,x1+2*(ln/3),y1+l+l/4,ln/3,4*l,1);

		//adding common registor;
		resistor_sideward(j,x1,y1,ln/3,1);
		resistor_sideward(j,x1,y1+l,ln/3,2);
		resistor_sideward(j,x1,y1+2*l,ln/3,3);
		///adding sideward small opam
		smallopam(j,x1,y1+3*l,ln/3,wd/5,-1);
		j.drawLine(x1+ln/3,y1,x1+ln/3,y1+2*l);
		//drawing vdown line to Big opam
		j.drawLine(x1,y1+4*l+(int)seventy,x1+2*(ln/3),y1+4*l+(int)seventy);
		resistor_upward(j,x1+ln/3,y1+4*l+(int)seventy,2*l);
		////adding ground below at the downword resisitor
		drawground(j,x1+ln/3,y1+6*l+(int)seventy);

		//upward opam and resisitor
		smallopam(j,x1+2*(ln/3)-ln/12,y1-(int)twentyy,ln/3+ln/12,wd/5,1);
		//line from upward opam to right resistor
		j.drawLine(x1+2*(ln/3)-ln/12,y1-(int)twentyy,x1+2*(ln/3)-ln/12,y1+l/2);
		//switch for upward registor and diode

		if(temp.upswitch=="0")//if switch between uper diode and resistor is off
		{
			j.setColor(Color.MAGENTA);

			//j.drawLine(x1+2*(ln/3)-ln/12,y1-(int)twentyy+2*l/2,x1+2*(ln/3)-ln/12+(int)twentyx,y1+(l/2)-(int)ten);
			j.drawLine(x1+2*(ln/3)-ln/12,y1+2*l/2,x1+2*(ln/3),y1+2*l/2);

			j.setColor(Color.blue);
		}
		else if(temp.upswitch=="1")//if switch between uper diode and resistor is on
		{
			j.setColor(Color.MAGENTA);

			//j.drawLine(x1+2*(ln/3)-ln/12,y1-(int)twentyy+2*l/2,x1+2*(ln/3)-ln/12,y1+(l/2)-(int)ten);
			j.drawLine(x1+2*(ln/3)-ln/12,y1+2*l/2,x1+2*(ln/3)-ln/12,y1+l/2);
			j.setColor(Color.blue);

		}
		j.drawLine(x1+2*(ln/3)-ln/12,y1+2*l/2,x1+2*(ln/3)-ln/12,y1+3*(l/2));
		resistor_sideward(j,x1+2*(ln/3),y1+l,ln/3,4);
		//line after opam
		j.drawLine(x1+ln,y1-(int)twentyy,x1+ln,y1+l+l/4);
		//line from big Opam to output
		j.drawLine(x1+ln,y1+3*l+l/4,x1+ln+ln/6,y1+3*l+l/4);
		//adding string to output
		int f = 25;
		Font font = new Font("Serif", Font.PLAIN,f);
		j.setFont(font);
		j.setColor(Color.BLACK);
		j.drawString(temp.out,x1+ln+ln/6,y1+3*l+l/4-3);
		j.setColor(Color.BLUE);

		////drawing horizantal line from 3 resistor to common point
		j.drawLine(x1+ln/3,y1+3*(l/2),x1+ln/3+ln/8,y1+3*(l/2));
		//line from Big opam to commom point
		j.drawLine(x1+2*(ln/3)-ln/6,y1+3*(l/2),x1+2*(ln/3),y1+3*(l/2));
		//line from  diode to below common point
		j.drawLine(x1+ln/3,y1+3*l,x1+ln/3+ln/8,y1+3*l);
		//line from left opam to common point
		j.drawLine(x1+ln/3+ln/8,y1+3*l,x1+ln/3+ln/8,y1+3*l-3*(l/2)+l/8);
		//adding current
		j.drawLine(x1+ln/3+ln/8,y1-ln/12-60,x1+ln/3+ln/8,y1+3*(l/2)-l/8);
		//adding left line for ground
		j.drawLine(x1+ln/3+ln/8, y1-ln/12-60,x1+ln/3+ln/8-50,y1-ln/12-60);
		//adding vertical line for grounding
		j.drawLine(x1+ln/3+ln/8-50, y1-ln/12-60,x1+ln/3+ln/8-50,y1-ln/12-(int)ten);
		//adding ground
		drawground(j,x1+ln/3+ln/8-50,y1-ln/12-(int)ten);
		//drawing current circle
		int radius=ln/30;
		j.drawOval(x1+ln/3+ln/8 - radius, y1+(l/2)-radius, radius*2, radius*2);
		//adding string in current
		//j.setColor(Color.BLACK);
		//j.drawString(temp.current,x1+ln/3+ln/14,y1);
		//j.setColor(Color.BLUE);
		if(temp.currentval!="")
		{
			j.drawString("If",x1+ln/3+ln/8+ln/24,y1+ln/10);//adding string to the current
		}

		///switches for input
		switches(j,x1,y1,ln,l);

		//plus minus
		//              int f2 = 50*dim.height/1080;
		font = new Font("Serif", Font.PLAIN,50);
		j.setFont(font);
		j.drawString("-",x1+2*(ln/3)+1,y1+3*(l/2)+11);//minus	
		j.drawString("+",x1+2*(ln/3)+1,y1+4*l+(int)seventy+12);//plus


	}
	// the place where the control goes first in this class
	public void  paintComponent(Graphics j)
	{
		super.paintComponent(j);

		justpaint(j,x1,y1);

	}

}