	package Calculator;
	
	import java.awt.Color;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.RenderingHints;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.geom.Line2D;
	import java.util.ArrayList;
	
	import javax.swing.DefaultComboBoxModel;
	import javax.swing.DefaultListModel;
	import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JList;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTextField;
	import javax.swing.ListSelectionModel;
	import javax.swing.SwingConstants;
	
	public class Calculator_GUI {
	
		public JFrame frm_Calculator = new JFrame();;
		public JTextField txt_Display;
		public JTextField txt_Equation;
		public DefaultListModel<String> lst_Equation;
		public String str_Answer;
		public String cbox_Color;
		private JTextField txt_XRange;
		private JTextField txt_YRange;
		private ArrayList<Double> arr_xPoints = new ArrayList<Double>();
		private ArrayList<Double> arr_yPoints = new ArrayList<Double>();
		private ArrayList<Double> arr_plotX = new ArrayList<Double>();
		private ArrayList<Double> arr_plotY = new ArrayList<Double>();
		public int errorTrig = 0;
		public int errorMain = 0;
		public double dbl_xLow;
		public double dbl_xHigh;
		public double dbl_yLow;
		public double dbl_yHigh;
		Calculator_Functions calcFunctions = new Calculator_Functions();
		Equation_Validation obj_ValidateEquation;
	
		/**
		 * Launch the application.
		 */
		/**
		 * Create the application.
		 */
		public Calculator_GUI() {
			initialize();
		}
	
		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize() {
			frm_Calculator.setBounds(100, 100, 1250, 800);
			frm_Calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frm_Calculator.getContentPane().setLayout(null);
			
			JPanel pnl_Equation = new JPanel();
			pnl_Equation.setBounds(6, 6, 450, 310);
			frm_Calculator.getContentPane().add(pnl_Equation);
			pnl_Equation.setLayout(null);
			
			JPanel pnl_Calculator = new JPanel();
			pnl_Calculator.setBounds(6, 331, 450, 381);
			frm_Calculator.getContentPane().add(pnl_Calculator);
			pnl_Calculator.setLayout(null);
			
			JPanel pnl_Graph = new JPanel();
			pnl_Graph.setBounds(465, 6, 750, 706);
			frm_Calculator.getContentPane().add(pnl_Graph);
			pnl_Graph.setLayout(null);
			
			JLabel lbl_Yaxis = new JLabel("Y-axis");
			lbl_Yaxis.setBounds(360, 6, 48, 16);
			pnl_Graph.add(lbl_Yaxis);
			
			JLabel lbl_Xaxis = new JLabel("X-axis");
			lbl_Xaxis.setBounds(665, 298, 48, 16);
			pnl_Graph.add(lbl_Xaxis);
			
			JLabel lbl_Center = new JLabel("(0,0)");
			lbl_Center.setBounds(360, 298, 34, 16);
			pnl_Graph.add(lbl_Center);
			
			txt_Display = new JTextField();
			txt_Display.setHorizontalAlignment(SwingConstants.RIGHT);
			txt_Display.setBounds(6, 6, 429, 40);
			pnl_Calculator.add(txt_Display);
			txt_Display.setColumns(10);
	
			//Back Button
			JButton btn_back = new JButton("\uF0E7");
			btn_back.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					String str_backSpace = null;
					if(txt_Display.getText().length() > 0)
					{
						StringBuilder str_newText = new StringBuilder(txt_Display.getText());
						if(str_newText.charAt(txt_Display.getText().length()-1)>= 42 && str_newText.charAt(txt_Display.getText().length()-1)<= 47)
						{
							//reverts the action to delete the last entered operator
							calcFunctions.func_delOperators();
						}
						str_newText.deleteCharAt(txt_Display.getText().length()-1);
						str_backSpace = str_newText.toString();
						txt_Display.setText(str_backSpace);
					}
				}
			});
			btn_back.setBounds(16, 60, 60, 50);
			pnl_Calculator.add(btn_back);
			
			//Addition Button
			JButton btn_Addition = new JButton("+");
			btn_Addition.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					//Append the value to the text field for calculation
					String str_Display =  txt_Display.getText() + btn_Addition.getText();
					txt_Display.setText(str_Display);
					calcFunctions.func_addOperators("+");
				}
			});
			btn_Addition.setBounds(80, 60, 60, 50);
			pnl_Calculator.add(btn_Addition);
			
			//Subtraction Button
			JButton btn_Sub = new JButton("-");
			btn_Sub.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					//Append the value to the text field for calculation
					String str_Display =  txt_Display.getText() + btn_Sub.getText();
					txt_Display.setText(str_Display);
					calcFunctions.func_addOperators("-");
				}
			});
			btn_Sub.setBounds(145, 60, 60, 50);
			pnl_Calculator.add(btn_Sub);
			
			//Division Button
			JButton btn_Div = new JButton("\u00F7");
			btn_Div.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					//Append the value to the text field for calculation
					String str_Display =  txt_Display.getText() + btn_Div.getText();
					txt_Display.setText(str_Display);
					calcFunctions.func_addOperators("/");
				}
			});
			btn_Div.setBounds(210, 60, 60, 50);
			pnl_Calculator.add(btn_Div);
			
			//sin Function Button
			JButton btn_Sin = new JButton("sin");
			btn_Sin.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					//Append the value to the text field for calculation
					String str_Display =  txt_Display.getText() + btn_Sin.getText();
					txt_Display.setText(str_Display);
				}
			});
			btn_Sin.setBounds(275, 120, 60, 50);
			pnl_Calculator.add(btn_Sin);
	
			// Button for Number 7
			JButton btn_7 = new JButton("7");
			btn_7.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					//Append the value to the text field for calculation 
					String str_Display =  txt_Display.getText() + btn_7.getText();
					txt_Display.setText(str_Display);
				}
			});
			btn_7.setBounds(16, 120, 60, 50);
			pnl_Calculator.add(btn_7);
			
			//Button for Number 8
			JButton btn_8 = new JButton("8");
			btn_8.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					//Append the value to the text field for calculation
					String str_Display =  txt_Display.getText() + btn_8.getText();
					txt_Display.setText(str_Display);
				}
			});
			btn_8.setBounds(80, 120, 60, 50);
			pnl_Calculator.add(btn_8);
			
			//Button for Number 9
			JButton btn_9 = new JButton("9");
			btn_9.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					//Append the value to the text field for calculation
					String str_Display =  txt_Display.getText() + btn_9.getText();
					txt_Display.setText(str_Display);
				}
			});
			btn_9.setBounds(145, 120, 60, 50);
			pnl_Calculator.add(btn_9);
			
			//Multiplication Button
			JButton btn_Mul = new JButton("*");
			btn_Mul.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					//Append the value to the text field for calculation
					String str_Display =  txt_Display.getText() + btn_Mul.getText();
					txt_Display.setText(str_Display);
					calcFunctions.func_addOperators("*");
				}
			});
			btn_Mul.setBounds(210, 120, 60, 50);
			pnl_Calculator.add(btn_Mul);
			
			//cos Function Button
			JButton btn_Cos = new JButton("cos");
			btn_Cos.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					//Append the value to the text field for calculation
					String str_Display =  txt_Display.getText() + btn_Cos.getText();
					txt_Display.setText(str_Display);
				}
			});
			btn_Cos.setBounds(275, 180, 60, 50);
			pnl_Calculator.add(btn_Cos);
	
			//Button for Number 4
			JButton btn_4 = new JButton("4");
			btn_4.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					//Append the value to the text field for calculation
					String str_Display =  txt_Display.getText() + btn_4.getText();
					txt_Display.setText(str_Display);
				}
			});
			btn_4.setBounds(16, 180, 60, 50);
			pnl_Calculator.add(btn_4);
			
			//Button for Number 5
			JButton btn_5 = new JButton("5");
			btn_5.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					//Append the value to the text field for calculation
					String str_Display =  txt_Display.getText() + btn_5.getText();
					txt_Display.setText(str_Display);
				}
			});
			btn_5.setBounds(80, 180, 60, 50);
			pnl_Calculator.add(btn_5);
			
			//Button for Number 6
			JButton btn_6 = new JButton("6");
			btn_6.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					//Append the value to the text field for calculation
					String str_Display =  txt_Display.getText() + btn_6.getText();
					txt_Display.setText(str_Display);
				}
			});
			btn_6.setBounds(145, 180, 60, 50);
			pnl_Calculator.add(btn_6);
			
			//Power Function Button
			JButton btn_Pow = new JButton("^");
			btn_Pow.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					//Append the value to the text field for calculation
					String str_Display =  txt_Display.getText() + btn_Pow.getText();
					txt_Display.setText(str_Display);
					calcFunctions.func_addOperators("^");
				}
			});
			btn_Pow.setBounds(210, 180, 60, 50);
			pnl_Calculator.add(btn_Pow);
			
			//Tan Function Button
			JButton btn_Tan = new JButton("tan");
			btn_Tan.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					//Append the value to the text field for calculation
					String str_Display =  txt_Display.getText() + btn_Tan.getText();
					txt_Display.setText(str_Display);
				}
			});
			btn_Tan.setBounds(275, 240, 60, 50);
			pnl_Calculator.add(btn_Tan);
	
			//Button for Number 1
			JButton btn_1 = new JButton("1");
			btn_1.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					//Append the value to the text field for calculation
					String str_Display =  txt_Display.getText() + btn_1.getText();
					txt_Display.setText(str_Display);
				}
			});
			btn_1.setBounds(16, 240, 60, 50);
			pnl_Calculator.add(btn_1);
			
			//Button for Number 2
			JButton btn_2 = new JButton("2");
			btn_2.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					//Append the value to the text field for calculation
					String str_Display =  txt_Display.getText() + btn_2.getText();
					txt_Display.setText(str_Display);
				}
			});
			btn_2.setBounds(80, 240, 60, 50);
			pnl_Calculator.add(btn_2);
			
			//Button for Number 3
			JButton btn_3 = new JButton("3");
			btn_3.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					//Append the value to the text field for calculation
					String str_Display =  txt_Display.getText() + btn_3.getText();
					txt_Display.setText(str_Display);
				}
			});
			btn_3.setBounds(145, 240, 60, 50);
			pnl_Calculator.add(btn_3);
			
			//Square Root Function Button
			JButton btn_Sqrt = new JButton("\u221A");
			btn_Sqrt.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					//Append the value to the text field for calculation
					String str_Sqrt = "Sqrt";
					String str_Display =  txt_Display.getText() + str_Sqrt;
					txt_Display.setText(str_Display);
				}
			});
			btn_Sqrt.setBounds(210, 240, 60, 50);
			pnl_Calculator.add(btn_Sqrt);
			
			//Natural Log Function Button
			JButton btn_Ln = new JButton("ln");
			btn_Ln.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					String str_Display =  txt_Display.getText() + btn_Ln.getText();
					txt_Display.setText(str_Display);
				}
			});
			btn_Ln.setBounds(275, 300, 60, 50);
			pnl_Calculator.add(btn_Ln);
	
			//Decimal Function Button
			JButton btn_Dec = new JButton(".");
			btn_Dec.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					char ch_Dec = 46;
					String str_Display =  txt_Display.getText() + ch_Dec;
					txt_Display.setText(str_Display);
				}
			});
			btn_Dec.setBounds(16, 300, 60, 50);
			pnl_Calculator.add(btn_Dec);
			
			//Button for Number 0
			JButton btn_0 = new JButton("0");
			btn_0.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					String str_Display =  txt_Display.getText() + btn_0.getText();
					txt_Display.setText(str_Display);
				}
			});
			btn_0.setBounds(80, 300, 60, 50);
			pnl_Calculator.add(btn_0);
			
			//Exponential Function Button
			JButton btn_Exp = new JButton("e");
			btn_Exp.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					String str_Display =  txt_Display.getText() + btn_Exp.getText();
					txt_Display.setText(str_Display);
				}
			});
			btn_Exp.setBounds(145, 300, 60, 50);
			pnl_Calculator.add(btn_Exp);
			
			//Pi Button
			JButton btn_Pi = new JButton("\u03C0");
			btn_Pi.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					String str_Pi = "\u03c0";
					String str_Display =  txt_Display.getText() + str_Pi;
					txt_Display.setText(str_Display);
				}
			});
			btn_Pi.setBounds(210, 300, 60, 50);
			pnl_Calculator.add(btn_Pi);
			
			//Answer Button
			JButton btn_Ans = new JButton("ANS");
			btn_Ans.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					txt_Display.setText("ANS");
				}
			});
			btn_Ans.setBounds(345, 240, 70, 110);
			pnl_Calculator.add(btn_Ans);
	
			//Clear Button
			JButton btn_Clear = new JButton("CLR");
			btn_Clear.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					txt_Display.setText("");
				}
			});
			btn_Clear.setBounds(275, 60, 140, 50);
			pnl_Calculator.add(btn_Clear);
			
			//Equals Button
			JButton btn_Equals = new JButton("=");
			btn_Equals.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					try
					{
						calcFunctions.set_TextCalc(txt_Display.getText());
						str_Answer = calcFunctions.resultCal();
						txt_Display.setText(str_Answer);
					}
					catch(Exception Ex)
					{
						System.out.println(Ex.getMessage());
						txt_Display.setText("Something went wrong!!!");
						calcFunctions.func_delAllOperators();
					}
					
				}
			});
			btn_Equals.setBounds(345, 121, 70, 110);
			pnl_Calculator.add(btn_Equals);
			
	//		*************** Panel for Equation ***************
			
			JLabel lbl_Equation = new JLabel("Equation Y = ");
			lbl_Equation.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_Equation.setBounds(15, 15, 100, 25);
			pnl_Equation.add(lbl_Equation);
			
			txt_Equation = new JTextField();
			txt_Equation.setText("");
			txt_Equation.setBounds(120, 15, 300, 25);
			pnl_Equation.add(txt_Equation);
			txt_Equation.setColumns(10);
			
			//combobox for list of colors
			JComboBox<String> cmb_Color = new JComboBox<String>();
			cmb_Color.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					switch(cmb_Color.getSelectedIndex()) {
						
						case(0):
							cbox_Color = "BLACK";
							txt_Equation.setForeground(Color.BLACK);
							break;
						case(1):
							cbox_Color = "RED";
							txt_Equation.setForeground(Color.RED);
							break;
						case(2):
							cbox_Color = "GREEN";
							txt_Equation.setForeground(Color.GREEN);
							break;
						case(3):
							cbox_Color = "BLUE";
							txt_Equation.setForeground(Color.BLUE);
							break;
						case(4):
							cbox_Color = "YELLOW";
							txt_Equation.setForeground(Color.YELLOW);
							break;
					}
				}
			});
			cmb_Color.setModel(new DefaultComboBoxModel<String>(new String[] {"Black", "Red", "Green", "Blue", "Yellow"}));
			cmb_Color.setSelectedIndex(0);
			cmb_Color.setBounds(118, 100, 100, 25);
			pnl_Equation.add(cmb_Color);
			
			JLabel lbl_Color = new JLabel("Select Color:");
			lbl_Color.setBounds(15, 100, 100, 25);
			pnl_Equation.add(lbl_Color);
			
			JLabel lbl_History = new JLabel("History:");
			lbl_History.setBounds(15, 146, 100, 25);
			pnl_Equation.add(lbl_History);
			
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(79, 146, 340, 101);
			pnl_Equation.add(scrollPane);
			
			lst_Equation = new DefaultListModel<String>(); 
			
			JList<String> list = new JList<String>(lst_Equation);
			scrollPane.setViewportView(list);
			list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
			
			//Load Button
			JButton btn_Load = new JButton("Load");
			btn_Load.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					int i = list.getSelectedIndex();
					final String LoadText = (String) lst_Equation.get(i);
					txt_Equation.setText(LoadText); 
				}
			});
			btn_Load.setBounds(118, 265, 100, 25);
			pnl_Equation.add(btn_Load);
			
			//Erase Button
			JButton btn_Erase = new JButton("Erase");
			btn_Erase.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					Graphics grp = pnl_Graph.getGraphics();
					Graphics2D grp_2D;
					grp_2D = (Graphics2D) grp;
					grp_2D.clearRect(0, 0, 718, 630);
					int height = pnl_Graph.getHeight();
					int width = pnl_Graph.getWidth();
					grp_2D.setColor(Color.BLACK);
					grp_2D.drawLine((width/2), 0, (width/2), 630);
					grp_2D.setColor(Color.BLACK);
					grp_2D.drawLine(0, (height/2), 718, (height/2));
					grp_2D.drawString("X-axis", 665, 310);
					grp_2D.drawString("Y-axis", 360, 16);
					grp_2D.drawString("(0,0)", 360, 310);
				}
			});
			btn_Erase.setBounds(333, 99, 85, 30);
			pnl_Equation.add(btn_Erase);
			
			JButton btn_Plot = new JButton("Plot");
			btn_Plot.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
						double prevX = 0, prevY = 0;
						arr_plotX = new ArrayList<Double>();
						arr_plotY = new ArrayList<Double>();
						arr_xPoints = new ArrayList<Double>();
						arr_yPoints = new ArrayList<Double>();
						String str_xRange, str_yRange;
						int height = pnl_Graph.getHeight();
						int width = pnl_Graph.getWidth();
						final String str_Input = txt_Equation.getText();
				
						obj_ValidateEquation = new Equation_Validation();
						obj_ValidateEquation.func_setString(txt_Equation.getText());
						str_xRange = txt_XRange.getText();
						str_yRange = txt_YRange.getText();
						func_CheckRange(str_xRange, str_yRange);
						obj_ValidateEquation.func_setXHigh(dbl_xHigh);
						obj_ValidateEquation.func_setXLow(dbl_xLow);
						obj_ValidateEquation.func_setYHigh(dbl_yHigh);
						obj_ValidateEquation.func_setYLow(dbl_yLow);
						obj_ValidateEquation.func_validatePoints();
						errorMain = obj_ValidateEquation.func_getErrMain();
						errorTrig = obj_ValidateEquation.func_getErrTrig();
						arr_xPoints = obj_ValidateEquation.func_getXPoints(); 
						arr_yPoints = obj_ValidateEquation.func_getYPoints();
						if(errorMain==0 && errorTrig ==0)
						{
							lst_Equation.addElement(str_Input);
							func_ConvertPlot();
							//Graphics 
							Graphics grp = pnl_Graph.getGraphics(); 
							Graphics2D grp_2D;
							grp_2D = (Graphics2D) grp;
							
							grp_2D.clearRect(0, 0, 718, 630);
							grp_2D.setColor(Color.BLACK);
							grp_2D.drawString("X-axis", 665, 310);
							grp_2D.drawString("Y-axis", 360, 16);
							grp_2D.drawString("(0,0)", 360, 310);
							grp_2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		                            RenderingHints.VALUE_ANTIALIAS_ON);
							grp_2D.setColor(Color.BLACK);
							grp_2D.drawLine((width/2), 0, (width/2), 630);
							grp_2D.setColor(Color.BLACK);
							grp_2D.drawLine(0, (height/2), 718, (height/2));
							
							switch(cbox_Color) {
							
								case ("BLACK"):
									grp_2D.setColor(Color.BLACK);
									break;
								case ("GREEN"):
									grp_2D.setColor(Color.GREEN);
									break;
								case ("BLUE"):
									grp_2D.setColor(Color.BLUE);
									break;
								case ("RED"):
									grp_2D.setColor(Color.RED);
									break;
								case ("YELLOW"):
									grp_2D.setColor(Color.YELLOW);
									break;
							}
							
							for (int i=0; i<arr_plotX.size(); i++)
							{
								if(i==0){}
								else
								{
									grp_2D.draw(new Line2D.Double(prevX, height-prevY, arr_plotX.get(i), height-arr_plotY.get(i)));
								}
								prevX = arr_plotX.get(i);
								prevY = arr_plotY.get(i);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(frm_Calculator, "Incorrect Equation!!!");
						}
				}
			});
			btn_Plot.setBounds(233, 99, 85, 30);
			pnl_Equation.add(btn_Plot);
			
			JButton btn_Delete = new JButton("Delete");
			btn_Delete.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					int i = list.getSelectedIndex();
					if(i !=- 1)
					{
						lst_Equation.remove(i);
					}
				}
			});
			btn_Delete.setBounds(265, 265, 100, 25);
			pnl_Equation.add(btn_Delete);
			
			JLabel lbl_XRange = new JLabel("X-Range:");
			lbl_XRange.setBounds(15, 60, 100, 25);
			pnl_Equation.add(lbl_XRange);
			
			JLabel lbl_YRange = new JLabel("Y-Range:");
			lbl_YRange.setBounds(233, 58, 100, 25);
			pnl_Equation.add(lbl_YRange);
			
			txt_XRange = new JTextField();
			txt_XRange.setBounds(120, 60, 100, 25);
			pnl_Equation.add(txt_XRange);
			txt_XRange.setColumns(10);
			
			txt_YRange = new JTextField();
			txt_YRange.setBounds(320, 60, 100, 25);
			pnl_Equation.add(txt_YRange);
			txt_YRange.setColumns(10);
			
		}
		
		// TODO Auto-generated method stub
		private void func_ConvertPlot() 
		{
			for (int i=0; i<arr_yPoints.size(); i++)
			{
				if(arr_yPoints.get(i)>dbl_yHigh){
					arr_yPoints.add(i,dbl_yHigh);
					arr_yPoints.remove(i+1);
				} else if (arr_yPoints.get(i)<dbl_yLow){
					arr_yPoints.add(i,dbl_yLow);
					arr_yPoints.remove(i+1);
				}
				
				double newx = (arr_xPoints.get(i)/dbl_xHigh)*359 + 359;
				double newy = (arr_yPoints.get(i)/dbl_yHigh)*315 + 315;
				arr_plotX.add(newx);
				arr_plotY.add(newy);
			}
		}
		
		//Implement a Check Range Function 
		private void func_CheckRange(String str_xRange, String str_yRange) 
		{
			int err_Range = 0;
			
			if(!(str_xRange.contains(",") && str_xRange.contains("[") && str_xRange.contains("]") && str_yRange.contains(",") && str_yRange.contains("[") && str_yRange.contains("]"))){
				err_Range = 1;
			} 
			
			for(int i=1; i<str_xRange.length()-1; i++){
				if((str_xRange.charAt(i)>=58 && str_xRange.charAt(i)<=127) || (str_xRange.charAt(i)>=32 && str_xRange.charAt(i)<=43) || str_xRange.charAt(i)=='/'){
					err_Range = 1;
				}
			}
			
			if(err_Range == 0)
			{
				String str_x = str_xRange.substring(1, str_xRange.length()-1);
				String sb_x[] = str_x.split(",");
				String str_y = str_yRange.substring(1, str_yRange.length()-1);
				String sb_y[] = str_y.split(",");
				dbl_xLow = Double.parseDouble(sb_x[0]);
				dbl_xHigh = Double.parseDouble(sb_x[1]);
				dbl_yLow = Double.parseDouble(sb_y[0]);
				dbl_yHigh = Double.parseDouble(sb_y[1]);
			}
			else
			{
				JOptionPane.showMessageDialog(frm_Calculator, "Incorrect Format for the Range!!!");
			}
		}
	
	}
