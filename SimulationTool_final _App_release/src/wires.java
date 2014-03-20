import java.awt.*;

// to draw wires in view 2
public class wires {
	
	final int OPAM_COUNT=25;
	private int points[][][]=new int[OPAM_COUNT][7][2]; // stores points/marks around each opam
	private opvalues inout[];
	int ver,hor;
	public wires(Graphics j,int initial_xcord,int initial_ycord,int width,int height,opvalues a[]){
		//j.drawRect(500, 500, 500, 500); to check

		int x_cord=(int)initial_xcord;
		int y_cord=(int)initial_ycord;
		inout=a;
		ver = (((11 * width)/6)/(OPAM_COUNT+1));
		hor = (((11 * height)/6)/(OPAM_COUNT+1));
		//ver = (((11 * width)/6));
		//hor = (((11 * height)/6));
		for(int i=0;i<Math.sqrt(OPAM_COUNT);i++) // stores the points
		{
			y_cord=(int)initial_ycord;
			x_cord=(int)initial_xcord+(i*(int)((3)*(int)width));
			for(int k=0;k<Math.sqrt(OPAM_COUNT);k++)
			{
				y_cord=(int)initial_ycord+(k*(int)(3*(int)height));
				points[i*5+k][5][0]=x_cord+((int)width)/3+((int)width)/8; // top point's x 
				points[i*5+k][5][1]=y_cord-((int)height)/12; // top point's y

				points[i*5+k][6][0]=x_cord+((int)width)+((int)width)/6; // opamp output's point's x 
				points[i*5+k][6][1]=y_cord+3*((int)width)/6+((int)width)/24; // opamp output's point's x 

				for(int j1=0;j1<5;j1++)
				{
					// x and y of all the input voltages for all opamp
					points[i*5+k][j1][0]=x_cord;
					points[i*5+k][j1][1]=y_cord;
					y_cord+=(((int)height)/6);

				}
			}

		}
		handle_wires(j);
	}
	
	// draw the wires
	public void handle_wires(Graphics tool)
	{
		for(int i=0;i<OPAM_COUNT;i++) // draw wires to each opamp
		{
			//int index=inout[i].v1.find('t'); index++;
			// conditions tell us where the input voltages are coming from
			if(inout[i].v1.length()>3 &&  inout[i].v1.charAt(3)<='9' && inout[i].v1.charAt(3)>='0')
			{
				int src=Integer.parseInt(inout[i].v1.substring(3));
				int dest=i;
				wire_draw(tool,src,dest,0);
			}
			if(inout[i].v2.length()>3 &&inout[i].v2.charAt(3)<='9' && inout[i].v2.charAt(3)>='0')
			{
				int src=Integer.parseInt(inout[i].v2.substring(3));
				int dest=i;
				wire_draw(tool,src,dest,1);
			}
			if(inout[i].v3.length()>3 &&inout[i].v3.charAt(3)<='9' && inout[i].v3.charAt(3)>='0')
			{
				int src=Integer.parseInt(inout[i].v3.substring(3));
				int dest=i;
				wire_draw(tool,src,dest,2);
			}
			if(inout[i].vdiode.length()>3 &&inout[i].vdiode.charAt(3)<='9' && inout[i].vdiode.charAt(3)>='0')
			{
				int src=Integer.parseInt(inout[i].vdiode.substring(3));
				int dest=i;
				wire_draw(tool,src,dest,3);
			}
			if(inout[i].vdown.length()>3 &&inout[i].vdown.charAt(3)<='9' && inout[i].vdown.charAt(3)>='0')
			{
				int src=Integer.parseInt(inout[i].vdown.substring(3));
				int dest=i;
				wire_draw(tool,src,dest,4);
			}

		}

	}
	
	// actually draw the wires  
	// src = from where the input comes
	// dest = where the input goes to
	// in_num = denotes the input of the destination opamp
	public void wire_draw(Graphics tool,int src,int dest,int in_num)
	{
		//		tool.setColor(Color.green);
		tool.setColor(new Color((1991*(src+1*src+1*src+1))%(255),(881*(dest+1*dest+1*dest+1))%(255),((dest+1*dest+1)*(src+1*src+1)*1777)%225));
		if((src%5)<=(dest%5))
		{
			tool.drawLine(points[src][6][0],points[src][6][1],points[src][6][0]+(ver*(src+1)),points[src][6][1]);//line1
			tool.drawLine(points[src][6][0]+(ver*(src+1)),points[src][6][1],points[src][6][0]+(ver*(src+1)),points[dest][5][1]-((OPAM_COUNT-src)*hor));//line2
			tool.drawLine(points[src][6][0]+(ver*(src+1)),points[dest][5][1]-((OPAM_COUNT-src)*hor),points[dest][in_num][0]-((OPAM_COUNT-src)*ver),points[dest][5][1]-((OPAM_COUNT-src)*hor));//line3
			tool.drawLine(points[dest][in_num][0]-((OPAM_COUNT-src)*ver),points[dest][5][1]-((OPAM_COUNT-src)*hor),points[dest][in_num][0]-((OPAM_COUNT-src)*ver),points[dest][in_num][1]);//line4
			tool.drawLine(points[dest][in_num][0]-((OPAM_COUNT-src)*ver),points[dest][in_num][1],points[dest][in_num][0],points[dest][in_num][1]);//line5
		}
		else
		{
			tool.drawLine(points[src][6][0],points[src][6][1],points[src][6][0]+(ver*(src+1)),points[src][6][1]);
			tool.drawLine(points[src][6][0]+(ver*(src+1)),points[src][6][1],points[src][6][0]+(ver*(src+1)),points[dest+1][5][1]-((OPAM_COUNT-src)*hor));
			tool.drawLine(points[src][6][0]+(ver*(src+1)),points[dest+1][5][1]-((OPAM_COUNT-src)*hor),points[dest+1][in_num][0]-((OPAM_COUNT-src)*ver),points[dest+1][5][1]-((OPAM_COUNT-src)*hor));//line3
			tool.drawLine(points[dest][in_num][0]-((OPAM_COUNT-src)*ver),points[dest+1][5][1]-((OPAM_COUNT-src)*hor),points[dest][in_num][0]-((OPAM_COUNT-src)*ver),points[dest][in_num][1]);//line4
			tool.drawLine(points[dest][in_num][0]-((OPAM_COUNT-src)*ver),points[dest][in_num][1],points[dest][in_num][0],points[dest][in_num][1]);//line5
		}

	}

}