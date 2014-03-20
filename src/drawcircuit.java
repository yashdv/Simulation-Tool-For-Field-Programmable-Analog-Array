import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class drawcircuit extends JPanel implements MouseMotionListener,MouseListener{

	private int x1,y1,x2,y2,x3,y3,x4,y4,ln,wd,cnst,flg,last;
	opvalues temp1[] = new opvalues[25];
	private int xpos,ypos,prev_xpos=-1,prev_ypos=-1,trans_graph_x=0,trans_graph_y=0;
	private JButton[][] but;
	static int x = 110;
	static int y = 205;
	private int click_x=0,click_y=0;
	private Color colour;
	Stroke drawingStroke = new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0,new float[]{9},0);

	drawcircuit(int x1,int y1,int length,int width,opvalues a[],int cons,int flag,int last_used)
	{
		cnst = cons;
		flg = flag;
		last=last_used;
		temp1 = a;
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

		addMouseMotionListener(this);
		addMouseListener(this);
	};

	// get co-ordinates of the mouse position on screen
	public void mouseMoved(MouseEvent me)
	{
		xpos = me.getX();
		ypos = me.getY();
	}
	public void mouseReleased (MouseEvent me) {

		prev_xpos = -1;
		prev_ypos = -1;
		repaint();
	}
	
	// empty
	public void mouseEntered (MouseEvent me) {
	}
	
	// empty
	public void mouseExited (MouseEvent me) {
	}

	// get the direction and magnitude of the drag, so that the background can be movd accordingly
	public void mouseDragged (MouseEvent me) {

		xpos = me.getX();
		ypos = me.getY();
		if(prev_xpos != -1 && prev_ypos != -1)
		{
			trans_graph_x =  xpos - prev_xpos + trans_graph_x; // shift in x
			trans_graph_y =  ypos - prev_ypos + trans_graph_y; // shift in y
		}
		prev_xpos = xpos;
		prev_ypos = ypos;
		repaint();
	}
	
	// repainting the panel
	public void mousePressed (MouseEvent me) {
		repaint();
	}
	
	// this opens a detailed view of op-amps if the click is made "on" the op-amp
	public void mouseClicked(MouseEvent me){
		click_x=me.getX();
		click_y=me.getY();
		int stx=x1+trans_graph_x;
		int sty=y1-ln/12+trans_graph_y;
		int col=-1,row=-1;
		int i,j;
		int colf=0,rowf=0;
		for(i=1;;i++){
			if(stx>click_x){
				col = i-1;
				break;
			}
			stx+=(4*ln)/3;
		}
		for(i=1;;i++){
			if(sty>click_y){
				row = i-1;
				break;
			}
			sty+=(5*wd)/3;
		}
		if(col<1 || col>5 || row<1 || row>5){}
		else{
			col--;
			row--;
			if(click_x<=x1+trans_graph_x+ln+(4*ln*col)/3){
				colf=1;
			}
			if(click_y<=wd+y1+trans_graph_y+(5*wd*row)/3){
				rowf=1;
			}
			if(rowf==1 && colf==1){
				//      JOptionPane.showMessageDialog (null, "row "+row+" col "+col, "hello", JOptionPane.ERROR_MESSAGE);
				new OPAMCalling(200,300,600,500,temp1[col*5+row]);
			}

		}
		repaint();
	}




	// draw op-amp
	private void Bigopam(Graphics j,int x1,int y1,int length,int width,int flag)
	{
		j.setColor(colour);
		j.drawLine(x1,y1,x1+flag*length,y1+(width/2));
		j.drawLine(x1+flag*length,y1+(width/2),x1,y1+width);
		j.drawLine(x1,y1,x1,y1+width);
		j.drawLine(x1+flag*length,y1,x1+flag*length,y1+width/2);
		int f = 24;
		Font font = new Font("Serif", Font.PLAIN,f);
		j.setFont(font);
		j.drawString("_",x1,y1+(width/10));//minus	
		j.drawString("+",x1,y1+width-(width/5));//plus

	}
	
	// draw diode
	private void formopam(Graphics j,int x1,int y1,int length,int width,int flag)
	{
		j.setColor(colour);
		j.drawLine(x1,y1,x1+flag*length,y1+(width/2));
		j.drawLine(x1+flag*length,y1+(width/2),x1,y1+width);
		j.drawLine(x1,y1,x1,y1+width);
		j.drawLine(x1+flag*length,y1,x1+flag*length,y1+width);
	}

	// draw lines around op-amp and diode
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
	
	// three resistor on left and right
	public void resistor_sideward(Graphics j,int xcord,int ycord,int length,int var,opvalues temp)
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
		int f = 12;
		Font font = new Font("Serif", Font.PLAIN,f);
		j.setFont(font);
		j.setColor(colour);
		if(var==1)
		{
			j.drawString(temp.r1,xcord+length/4+25,y1-(wd/20)-2);
			j.drawString(temp.v1,xcord,y1-2);
		}
		else if(var==2)
		{
			j.drawString(temp.r2,xcord+length/4+25,y1-(wd/20)-2);
			j.drawString(temp.v2,xcord,y1-2);
		}

		else if(var==3)
		{
			j.drawString(temp.r3,xcord+length/4+25,y1-(wd/20)-2);
			j.drawString(temp.v3,xcord,y1-2);
			j.drawString(temp.vdiode,xcord,y1-2+wd/6);
			j.drawString(temp.vdown,xcord,y1-2+wd/3);
		}
		else
		{
			j.drawString(temp.rup,xcord+length/4,y1-(wd/20)-2);
		}

		//j.drawString(s,xcord+length/4,y1-(wd/20)-2);
		j.setColor(colour);
		j.drawLine(x1,ycord,x1+(length/4),ycord);
	}
	
	// resistor boxes on left and right
	public void resistor_box(Graphics j,int xcord,int ycord,int length,int var,opvalues temp)
	{
		final double dcr = 10;
		int i,k,x1=xcord+length/4,y1=ycord,a;
		for(i=0;i<10;i++)
		{
			if(i%2!=0)
				k=1;
			else
				k=-1;
			a=y1+k*wd/20;
			//j.drawLine(x1,y1,x1+(length/20),a);
			y1=a;
			x1=x1+(length/20);
		}

		j.drawLine(xcord,ycord,xcord+(length/4),ycord);
		j.drawRect(xcord+length/4,ycord-(int)dcr/2,length/2,(int)dcr);
		int f = 12;
		Font font = new Font("Serif", Font.PLAIN,f);
		j.setFont(font);
		j.setColor(Color.black);
		if(var==1)
		{
			j.drawString(temp.r1,xcord+length/4+25,y1-(wd/20)-2);
			j.drawString(temp.v1,xcord,y1-2);
		}
		else if(var==2)
		{
			j.drawString(temp.r2,xcord+length/4+25,y1-(wd/20)-2);
			j.drawString(temp.v2,xcord,y1-2);
		}

		else if(var==3)
		{
			j.drawString(temp.r3,xcord+length/4+25,y1-(wd/20)-2);
			j.drawString(temp.v3,xcord,y1-2);
			j.drawString(temp.vdiode,xcord,y1-2+wd/6);
			j.drawString(temp.vdown,xcord,y1-2+wd/3);
		}
		else
		{
			j.drawString(temp.rup,xcord+length/4,y1-(wd/20)-2);
		}

		//j.drawString(s,xcord+length/4,y1-(wd/20)-2);
		j.setColor(colour);
		j.drawLine(xcord+3*(length/4),ycord,xcord+length,ycord);
	}
	
	// draw the grounding
	public void drawground(Graphics j,int x1,int y1)
	{
		int d=10,c=0;
		for(int i=0;i<4;i++)
		{
			j.drawLine(x1-d,y1+c,x1+d,y1+c);
			d=d-5/2;
			c+=2;

		}
	}
	
	// draw resistor box at the bottom
	public void resistor_box_upward(Graphics j,int xcord,int ycord,int length,opvalues temp)
	{
		final double dcr = 10;
		int i,k,x1=xcord,y1=ycord+length/4,a;
		for(i=0;i<10;i++)
		{
			if(i%2!=0)
				k=1;
			else
				k=-1;
			a=x1+k*wd/20;
			x1=a;
			y1=y1+(length/20);
		}
		j.drawLine(xcord,ycord,xcord,ycord+length/4);
		j.drawRect(xcord-length/8,ycord+length/4,(int)dcr,length/2);
		int f = 12;
		Font font = new Font("Serif", Font.PLAIN,f);
		j.setFont(font);
		j.setColor(colour);
		j.setColor(colour);
		j.drawLine(x1,ycord+3*(length/4),x1,ycord+length);
		j.drawString(temp.rdown,x1+(length/6),ycord+(length/2));//printing string at down resistor
	}

	// resistor at the bottom
	public void resistor_upward(Graphics j,int xcord,int ycord,int length,String s)
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
		int f = 12;
		Font font = new Font("Serif", Font.PLAIN,f);
		j.setFont(font);
		j.setColor(colour);
		j.setColor(colour);
		j.drawLine(x1,ycord+3*(length/4),x1,ycord+length);
	}
	
	// the function which calls the above modules to draw stuff in itself
	public void justpaint(Graphics j,int x1,int y1,opvalues temp)
	{
		int l=wd/6;
		//adding big opam
		Bigopam(j,x1+2*(ln/3),y1+l+l/4,ln/3,4*l,1);

		//adding common registor;
		//		resistor_sideward(j,x1,y1,ln/3,1,temp);
		//		resistor_sideward(j,x1,y1+l,ln/3,2,temp);
		//		resistor_sideward(j,x1,y1+2*l,ln/3,3,temp);
		
		// add all the resistor
		resistor_box(j,x1,y1,ln/3,1,temp);
		resistor_box(j,x1,y1+l,ln/3,2,temp);
		resistor_box(j,x1,y1+2*l,ln/3,3,temp);
		// adding side ward small opam
		smallopam(j,x1,y1+3*l,ln/3,wd/5,-1);
		j.drawLine(x1+ln/3,y1,x1+ln/3,y1+2*l);
		//drawing this line
		j.drawLine(x1,y1+4*l,x1+2*(ln/3),y1+4*l);
		resistor_box_upward(j,x1+ln/3,y1+4*l,2*l,temp);

		//upward opam and resisitor
		smallopam(j,x1+2*(ln/3)-ln/12,y1,ln/3+ln/12,wd/5,1);
		//line from upward opam to three resistor
		j.drawLine(x1+2*(ln/3)-ln/12,y1+1,x1+2*(ln/3)-ln/12,y1+l/2);
		j.drawLine(x1+2*(ln/3)-ln/12,y1+l,x1+2*(ln/3)-ln/24,y1+l/2);
		j.drawLine(x1+2*(ln/3)-ln/12,y1+l,x1+2*(ln/3)-ln/12,y1+3*l/2);
		resistor_box(j,x1+2*(ln/3),y1+l,ln/3,4,temp);

		drawground(j,x1+ln/3,y1+6*l);
		//line after opam
		j.drawLine(x1+ln,y1,x1+ln,y1+l+l/4);
		//line from big Opam to output
		j.drawLine(x1+ln,y1+3*l+l/4,x1+ln+ln/6,y1+3*l+l/4);
		j.drawString(temp.out,x1+ln+ln/6,y1+3*l+l/4-3);//adding string to output line
		//drawing horizantal line from small upward opam to  big opam
		j.drawLine(x1+ln/3,y1+3*(l/2),x1+ln/3+ln/8,y1+3*(l/2));
		//line from registor to commom point
		j.drawLine(x1+2*(ln/3)-ln/8,y1+3*(l/2),x1+2*(ln/3),y1+3*(l/2));
		//line from  Big opam to common point
		j.drawLine(x1+ln/3,y1+3*l,x1+ln/3+ln/8,y1+3*l);
		//line from left opam to common point
		j.drawLine(x1+ln/3+ln/8,y1+3*l,x1+ln/3+ln/8,y1+9*(l/4));
		//adding current
		j.drawLine(x1+ln/3+ln/8,y1-ln/12-20,x1+ln/3+ln/8,y1+l);
		//adding left line for ground
		j.drawLine(x1+ln/3+ln/8, y1-ln/12-20,x1+ln/3+ln/8-25,y1-ln/12-20);
		//adding vertical line for grounding
		j.drawLine(x1+ln/3+ln/8-25, y1-ln/12-20,x1+ln/3+ln/8-25,y1-ln/12-10);
		//adding ground
		drawground(j,x1+ln/3+ln/8-25,y1-ln/12-10);

		//drawing current circle
		int radius=ln/30;
		j.drawOval(x1+ln/3+ln/8 - radius, y1+(l/4)-radius, radius*2, radius*2);
		if (temp.currentval != "") {
			j.drawString("If", x1 + ln / 3 + ln / 8 + ln / 24, y1 + ln / 10);//adding string to the current
		}
	}
	
	// starting points for the DC shift line
	static int xdc = 1575; 
	static int ydc = 10;
	public void  paintComponent(Graphics j)
	{
		super.paintComponent(j);

		// repainting and storing current panel state
		Graphics2D g2d = (Graphics2D)j;
		AffineTransform tx = new AffineTransform();
		AffineTransform oldtx = new AffineTransform();
		oldtx = g2d.getTransform();
		tx.translate(trans_graph_x,trans_graph_y);
		g2d.setTransform(tx);

		//       dim = Toolkit.getDefaultToolkit().getScreenSize();
		int i=0,k=0,local_y=y1;

		for(i=0;i<5;i++)
		{
			int local_x=x1;
			for(k=0;k<5;k++)
			{
				if(temp1[k*5+i].opnum!=-1)
				{
					colour=Color.blue;
					j.setColor(Color.blue);
				}
				else
				{    colour=Color.gray;
				j.setColor(Color.gray);
				}
				justpaint(j,local_x,local_y,temp1[k*5+i]);
				//j.drawLine(local_x,local_y, local_x, local_y+27);
				local_x=local_x+(4*ln)/3;
			}
			local_y=local_y+(5*wd)/3;
		}

		ydc=y1; 
		int j1,a;
		j.setColor(Color.BLACK);
		j.drawString("DC shift",xdc,y1-y1/5);
		//super.paintComponent(j);
		for(i=12;i> 0;i--) // upper half of line
		{
			j.drawLine(xdc,ydc,xdc,ydc+9);
			int c=(2*ydc+10)/2;
			j.drawLine(xdc,c,xdc+5,c);
			if(i == cnst)
			{
				j.setColor(Color.BLUE);
				j.drawString("final_output",xdc+6,c);
				j.setColor(Color.black);
			}
			String s = Integer.toString(i);
			if(i==12)
				j.drawString(s+'v',xdc+6,c);
			//j.drawLine(xdc,ydc,a,ydc+5);
			ydc=ydc+9;
			for(j1=0;j1<6;j1++) // zig-zag resistors
			{
				if(j1%2!=0)
					k=1;
				else
					k=-1;
				if(j1==3)
				{
					j.setColor(Color.RED);
					j.drawString("1 ohm",xdc-45,ydc);
					j.setColor(Color.BLACK);

				}
				a=xdc+k*5;
				j.drawLine(xdc,ydc,a,ydc+5);
				xdc=a;
				ydc=ydc+5;
			}

		}
		for(i=0;i<=12;i++) // lower half of line
		{
			int c=(2*ydc+10)/2;
			j.setColor(Color.BLUE);
			if(i == 0  && flg == 2){
				j.drawString("input",xdc-40,c);
			}
			else if(i == 0 && flg == 3)
				j.drawString("out"+Integer.toString(last),xdc-35,c);
			j.setColor(Color.black);
			j.drawLine(xdc,ydc,xdc,ydc+9);
			j.drawLine(xdc,c,xdc+5,c);
			if(i == (-1)*cnst)
			{
				j.setColor(Color.BLUE);
				j.drawString("final_output",xdc+6,c);
				j.setColor(Color.black);
			}
			String s1 = Integer.toString(i);
			if(i == 12)
				j.drawString('-'+s1+'v',xdc+6,c);


			//j.drawLine(xdc,ydc,a,ydc+5);
			ydc=ydc+9;
			if(i!=12)
			{
				for(j1=0;j1<6;j1++) // zig-zag resistor
				{
					if(j1%2!=0)
						k=1;
					else
						k=-1;
					if(j1==3)
					{
						j.setColor(Color.decode("#008000"));
						j.drawString("1 ohm",xdc-45,ydc);
						j.setColor(Color.black);
					}
					a=xdc+k*5;
					j.drawLine(xdc,ydc,a,ydc+5);
					xdc=a;
					ydc=ydc+5;
				}
			}
		}

		i=0;
		k=0;
		local_y=y1;
		for(i=0;i<5;i++)  // three way switch in op-amp
		{
			int local_x=x1;
			for(k=0;k<5;k++)
			{
				//justpaint(j,local_x,local_y,temp1[k*5+i]);
				g2d.setStroke(drawingStroke);
				j.drawLine(local_x+ln/3+ln/12,local_y+3*(wd/12),local_x+2*(ln/3)-ln/8,local_y+3*(wd/12));
				j.drawLine(local_x+ln/3+ln/8,local_y+wd/6,local_x+2*(ln/3)-ln/8,local_y+3*(wd/12));
				j.drawLine(local_x+ln/3+ln/8,local_y+9*(wd/24),local_x+2*(ln/3)-ln/8,local_y+3*(wd/12));
				//g2d.resetStroke(drawingStroke);
				//j.drawLine(x1+2*(ln/3)-ln/12,y1+3*(l/2),x1+2*(ln/3),y1+3*(l/2));
				local_x=local_x+(4*ln)/3;
			}
			local_y=local_y+(5*wd)/3;
		}

		repaint();
		g2d.setTransform(oldtx);
	}

}
