import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import java.io.FileReader;
import java.io.BufferedReader;
import java.lang.StringBuffer;
import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Toolkit;


public class frame extends JFrame {

	private Font font;
	private JMenuBar menuBar;
	private JButton next;
	private JFrame fm;
	private JMenuItem resetAction;
	private JMenuItem viewcirAction;
	private JMenuItem viewinAction;
	private JMenuItem exitAction;
	private JMenuItem aboutAction;
	private JMenuItem helpAction;
	private Container con;
	private Dimension dim;



	public frame(){

		fm = new JFrame("Simulation Tool For FPAA");  //Application Window

		//      setLayout(new FlowLayout());

		dim = Toolkit.getDefaultToolkit().getScreenSize();

		con = fm.getContentPane();
		String s;
		FileReader fr;
		BufferedReader myInput;
		String[] instructions = new String[10];
		StringBuffer b = new StringBuffer();
		//Includeing the Instructions from the Instructions file
		try{
			fr = new FileReader("Instructions.txt");
			myInput = new BufferedReader(fr);
			int i = 0;

			while ((s = myInput.readLine()) != null) {
				b.append(s);
				instructions[i] = b.toString();
				b.delete(0, b.length());
				i++;
			}
			fr.close();
		}
		catch(Exception e){
			System.out.println("Error\n");
		}

		int f = 30*dim.height/1080;

		font = new Font("Serif",Font.PLAIN,f);
		next = new JButton("NEXT");
		next.setFont(font);
		PutImageOnJFrame im = new PutImageOnJFrame(instructions,next); //Adding the background image to the frame
		im.setLayout(null);
		im.add(next);
		//Button to move to the next frame i.e the input frame
		next.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						inputframe nextframe = new inputframe(fm);
						fm.setContentPane(nextframe);
						fm.validate();
						fm.repaint();
					}
				}
		);  

		fm.add(im);

		double ssw = 1360*dim.width/1920.00;
		double ssh = 800*dim.height/1080.00;

		fm.setSize((int)ssw,(int)ssh);
		fm.setVisible(true);

		Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("tool.png"));

		fm.setIconImage(icon);




		//      Adding the menu bar
		int f2 = 20*dim.height/1080;
		font = new Font("SERIF",Font.PLAIN,f2);
		menuBar = new JMenuBar();
		fm.setJMenuBar(menuBar);
		JMenu toolMenu = new JMenu("Tools");
		JMenu aboutMenu = new JMenu("About");
		menuBar.add(toolMenu);
		menuBar.add(aboutMenu);

		resetAction = new JMenuItem("Reset");
		resetAction.setFont(font);
		viewcirAction = new JMenuItem("View Circuit");
		viewcirAction.setFont(font);
		viewinAction = new JMenuItem("View Instructions");
		viewinAction.setFont(font);
		exitAction = new JMenuItem("Exit",new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("exit3.png"))));
		exitAction.setFont(font);
		aboutAction = new JMenuItem("About Tool",new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("tool2.png"))));
		aboutAction.setFont(font);
		helpAction = new JMenuItem("Help");
		helpAction.setFont(font);

		int f3 = 25*dim.height/1080;
		font = new Font("SERIF",Font.PLAIN,f3);
		toolMenu.add(resetAction);
		toolMenu.add(viewcirAction);
		toolMenu.add(viewinAction);
		toolMenu.addSeparator();
		toolMenu.add(exitAction);
		aboutMenu.add(aboutAction);
		aboutMenu.add(helpAction);
		toolMenu.setFont(font);
		aboutMenu.setFont(font);

		HandlerClass handler = new HandlerClass();
		resetAction.addActionListener(handler);
		exitAction.addActionListener(handler);
		viewcirAction.addActionListener(handler);
		viewinAction.addActionListener(handler);
		aboutAction.addActionListener(handler);
		helpAction.addActionListener(handler);

		fm.setJMenuBar(menuBar);

	}

	// Handler class for the menu items
	public class HandlerClass implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == resetAction){
				inputframe nextframe = new inputframe(fm);
				fm.setContentPane(nextframe);
				fm.validate();
				fm.repaint();
			}
			if(event.getSource() == exitAction)
			{
				System.exit(0);
			}
			if(event.getSource() == viewcirAction){
				opvalues[] o = new opvalues[25];
				for(int i = 0;i<25;i++){
					o[i] = new opvalues();
				}
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				double ssw = 50*dim.width/1920.00;
				double ssh = 50*dim.height/1080.00;
				double ssw1 = 220*dim.width/1920.00;
				double ssh1 = 140*dim.height/1080.00;
				drawcircuit c1 = new drawcircuit((int)ssw,(int)ssh,(int)ssw1,(int)ssh1,o,0,1,0);

				c1.setLayout(null);
				c1.setBackground(Color.WHITE);
				fm.setContentPane(c1);
				fm.validate();
				fm.repaint();

			}
			if(event.getSource() == viewinAction){
				fm.setContentPane(con);
				fm.validate();
				fm.repaint();
			}
			if(event.getSource() == aboutAction){
				JOptionPane.showMessageDialog(null,"Simulation Tool For FPAA v1.0\nDeveloped by Yash Vadalia","About tool",1);
			}
			if(event.getSource() == helpAction){
				help h = new help();
				h.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				double ssw = 800*dim.width/1920.00;
				double ssh = 500*dim.height/1080.00;
				int w = (int)ssw;
				int h2 = (int)ssh;
				h.setSize(w,h2);
				//         h.setResizable(false);
				h.setVisible(true);
				h.setLocationRelativeTo(null);
			}

		}
	}
}