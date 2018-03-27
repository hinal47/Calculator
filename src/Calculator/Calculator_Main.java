package Calculator;

import java.awt.EventQueue;

public class Calculator_Main {

	public static void main(String[] args) throws Exception {
		try{
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Calculator_GUI main_Window = new Calculator_GUI();
						main_Window.frm_Calculator.setVisible(true);
					} catch (Exception Ex) {
						Ex.printStackTrace();
					}
				}
			});
		}
		catch (Exception Ex){
			Ex.printStackTrace();
		}		
	}

}
