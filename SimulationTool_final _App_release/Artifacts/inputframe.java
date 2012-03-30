import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Toolkit;

public class inputframe extends JPanel {
	private JButton button;
	private JTextField text;
	private String input;
	private Font font;
	private JPanel panel;
	private addlogo im;
	private JTextField text2;
	private JRadioButton rbutton1;
	private JRadioButton rbutton2;
	private ButtonGroup group;
	private int r1;
	private int r2;
	private Dimension dim;
	private int res;


	public inputframe(final JFrame fm){
		setLayout(new BorderLayout());

		dim = Toolkit.getDefaultToolkit().getScreenSize();
		double wt = 650*dim.width/1920.00;
		double ht = 650*dim.height/1080.00;
		double ln = 400*dim.width/1920.00;
		double wn = 40*dim.height/1080.00;

		double wt2 = 1100*dim.width/1920.00;
		double ht2 = 650*dim.height/1080.00;
		double ln2 = 100*dim.width/1920.00;
		double wn2 = 40*dim.height/1080.00;

		int f = 20*dim.height/1080;


		font = new Font("TimesRoman",Font.PLAIN,f);

		//Adding two seperate text fields for the two types of inputs

		text = new JTextField(50);
		text2 = new JTextField(30);

		//Adding the two radio buttons
		rbutton1 = new JRadioButton("Polynomial Functions",true);
		rbutton2 = new JRadioButton("Transcendental Functions",false);

		button = new JButton("Enter");
		group = new ButtonGroup();
		group.add(rbutton1);
		group.add(rbutton2);



		panel = new JPanel();
		text.setBounds((int)wt, (int)ht, (int)ln, (int)wn);
		text2.setBounds((int)wt, (int)ht, (int)ln, (int)wn);
		button.setBounds((int)wt2, (int)ht2, (int)ln2, (int)wn2);

		text.setFont(font);
		text2.setFont(font);
		button.setFont(font);
		rbutton1.setFont(font);
		rbutton2.setFont(font);

		add(text);
		add(text2);
		panel.add(rbutton1);
		panel.add(rbutton2);
		add(button);

		text.setVisible(true);
		text2.setVisible(false);
		r1=1;
		r2=0;

		//       s = "E.g: 12*x^3 - 3*x^2 + x + 4";
		//Action listener for the buttons
		rbutton1.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						text2.setVisible(false);
						text.setVisible(true);
						//                button.setBounds(1100, 650, 100, 40);
						//                s = "E.g: 12*x^3 - 3*x^2 + x + 4";
						r1=1;
						r2=0;
					}
				}
		);

		rbutton2.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						text.setVisible(false);
						text2.setVisible(true);
						//             s = "E.g: 4*sin(3*x), 3*cos(2*x), x^2*logx, 4*e^(2*x)";
						//              button.setBounds(1100, 650, 100, 40);
						r1=0;
						r2=1;
					}
				}
		);


		button.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						if(r1==1 && r2==0){
							input = text.getText();
							SanitizeStr ss = new SanitizeStr(input);
							if(ss.getvalidity() == 1){
								String var = ss.getvar();
								findval fv = new findval(ss.getpolycoeff(),fm,input,var);

							}
							else{
								JOptionPane.showMessageDialog (null, "Invalid Input", "Error..!.", JOptionPane.ERROR_MESSAGE);
							}
						}
						if(r1==0 && r2==1){
							input = text2.getText(); 
							if(input.toLowerCase().indexOf("log") == -1){ //If e^x,sinx,cosx
								sanitizetrans ss = new sanitizetrans(input);
								if(ss.getvalidity() == 1){
									String var = ss.getvar();
									int f = ss.getflag();
									if(f==1){ //if e^x
										findval fv = new findval(ss.getpolycoeff(),fm,input,var);
									}
									else{
										transfindval fv = new transfindval(ss.getpolycoeff(),fm,input,var);
									}
								}
								else{
									JOptionPane.showMessageDialog (null, "Invalid Input", "Error..!.", JOptionPane.ERROR_MESSAGE);
								}
							}
							else{ //if log
								findvallog ss = new findvallog(fm,input);
							}
						}


					}
				}
		);

		add(panel,BorderLayout.NORTH);

		im = new addlogo();
		add(im);
	}
}