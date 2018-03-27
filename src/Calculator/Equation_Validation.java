package Calculator;

import java.util.ArrayList;

public class Equation_Validation 
{
	public String str_TextString;
	public String str_TrigFunc = new String();
	public int tFlag;
	public int errorTrig = 0;
	public int errorMain = 0;
	public double dbl_xLow;
	public double dbl_xHigh;
	public double dbl_yHigh;
	public double dbl_yLow;
	public char PrevChar = ' ';
	public String txtTrig = new String();
	public ArrayList<String> arr_TrigNumbers = new ArrayList<String>();
	public ArrayList<String> arr_TrigOperators = new ArrayList<String>();
	public String txtFinal = new String();
	public ArrayList<String> arr_finalNumbers = new ArrayList<String>();
	public ArrayList<String> arr_finalOperators = new ArrayList<String>();
	public Calculator_Equation obj_CalculateEquation;
	public ArrayList<Double> xPoints = new ArrayList<Double>();
	public ArrayList<Double> yPoints = new ArrayList<Double>();
	
	public void func_validatePoints() 
	{
		// TODO Auto-generated method stub
		String str_Text = str_TextString;
		func_TrigEquation(str_Text);
		if (tFlag == 1){
			errorTrig = func_chkError(txtTrig);
		}
		errorMain = func_chkMain(txtFinal);
		obj_CalculateEquation = new Calculator_Equation();
		func_Set();
		obj_CalculateEquation.func_CalculateGraph();
		xPoints = obj_CalculateEquation.arr_xPoints;
		yPoints = obj_CalculateEquation.arr_yPoints;
	}
	
	public void func_Set()
	{
		obj_CalculateEquation.func_setErrMain(errorMain);
		obj_CalculateEquation.func_setTFlag(errorTrig);
		obj_CalculateEquation.func_setFinalNumbers(arr_finalNumbers);
		obj_CalculateEquation.func_setFinalOperators(arr_finalOperators);
		obj_CalculateEquation.func_setTFlag(tFlag);
		obj_CalculateEquation.func_setTrigNumbers(arr_TrigNumbers);
		obj_CalculateEquation.func_setTrigOperators(arr_TrigOperators);
		obj_CalculateEquation.func_setTrigFunc(str_TrigFunc);
		obj_CalculateEquation.func_setXHigh(dbl_xHigh);
		obj_CalculateEquation.func_setXLow(dbl_xLow);
		obj_CalculateEquation.func_setYHigh(dbl_yHigh);
		obj_CalculateEquation.func_setYLow(dbl_yLow);
	}

//************************ Setters*************************
	
	public void func_setString(String str_TextString){
		this.str_TextString = str_TextString;
	} public void func_setXHigh(double xHigh){
		this.dbl_xHigh = xHigh;
	} public void func_setXLow(double xLow){
		this.dbl_xLow = xLow;
	} public void func_setYHigh(double yHigh){
		this.dbl_yHigh = yHigh;
	} public void func_setYLow(double yLow){
		this.dbl_yLow = yLow;
	}
	
//******************************* Getters***********************************
	
	public int func_getErrTrig(){
		return errorTrig;
	} public int func_getErrMain(){
		return errorMain;
	} public ArrayList<Double> func_getXPoints(){
		return xPoints;
	} public ArrayList<Double> func_getYPoints(){
		return yPoints;
	}
	
	public void func_TrigEquation(String str_Text)
	{
		String str_FinalText = new String();
		String str_TrigText = new String();
		if((str_Text.contains("tan")) || (str_Text.contains("sin")) || (str_Text.contains("ln")) || (str_Text.contains("cos")))
		{
			if(str_Text.contains("tan")){
				str_TrigFunc = "tan";
			} else if(str_Text.contains("sin")) {
				str_TrigFunc = "sin";
			} else if(str_Text.contains("ln")) {
				str_TrigFunc = "ln";
			} else if(str_Text.contains("cos")) {
				str_TrigFunc = "cos";
			}
			tFlag = 1;
		}
		
		if(tFlag == 1)
		{
			int flg = 0;
			for (int i = 0; i < str_Text.length(); i++) 
			{
				if(flg == 1 && str_Text.charAt(i)!=')') {
					str_TrigText = str_TrigText + str_Text.charAt(i);
				} if(flg == 0 && str_Text.charAt(i)=='(') {
					flg = 1;
				} if(flg == 1 && str_Text.charAt(i)==')') {
					flg = 0;
				} 
			}
		}
		
		if(tFlag == 0)
		{
			str_FinalText = str_Text;
			txtTrig = null;
			arr_TrigNumbers = null;
			txtFinal = str_FinalText;
			String[] str_Temp = new String[30];
			String str_Temp2 = new String("");
			// All the operators which are in split can only be splitted
			int k = 0;
			for (int i = 0; i < txtFinal.length(); i++) 
			{
				if (txtFinal.charAt(i) == '^' || txtFinal.charAt(i) == '/' || txtFinal.charAt(i) == '*'
						|| txtFinal.charAt(i) == '+' || txtFinal.charAt(i) == '-') 
				{
					str_Temp[k] = str_Temp2;
					arr_finalNumbers.add(str_Temp2);
					arr_finalOperators.add(txtFinal.charAt(i)+"");
					str_Temp2 = "";
					k++;
				} 
				else 
				{
					str_Temp2 = str_Temp2 + txtFinal.charAt(i);
				}

				if (txtFinal.length() - 1 == i) {
					str_Temp[k] = str_Temp2;
					arr_finalNumbers.add(str_Temp2);
				}
			}
		}
		else
		{
			String temp3 = str_TrigFunc + "(" + str_TrigText + ")";
			str_FinalText = str_Text.replace(temp3, str_TrigFunc);
			txtFinal = str_FinalText;
			String[] str_Temp = new String[30];
			String str_Temp2 = new String("");
			// All the operators which are in split can only be splitted
			int k = 0;
			for (int i = 0; i < txtFinal.length(); i++) 
			{
				if (txtFinal.charAt(i) == '^' || txtFinal.charAt(i) == '/' || txtFinal.charAt(i) == '*'
						|| txtFinal.charAt(i) == '+' || txtFinal.charAt(i) == '-') 
				{
					str_Temp[k] = str_Temp2;
					arr_finalNumbers.add(str_Temp2);
					arr_finalOperators.add(txtFinal.charAt(i)+"");
					str_Temp2 = "";
					k++;
				} 
				else 
				{
					str_Temp2 = str_Temp2 + txtFinal.charAt(i);
				}

				if (txtFinal.length() - 1 == i) {
					str_Temp[k] = str_Temp2;
					arr_finalNumbers.add(str_Temp2);
				}
			}
			
			str_Temp = new String[30];
			str_Temp2 = "";
			// All the operators which are in split can only be splitted
			k = 0;
			txtTrig = str_TrigText;
			for (int i = 0; i < txtTrig.length(); i++) 
			{
				if (txtTrig.charAt(i) == '^' || txtTrig.charAt(i) == '/' || txtTrig.charAt(i) == '*') 
				{
					str_Temp[k] = str_Temp2;
					arr_TrigNumbers.add(str_Temp2);
					arr_TrigOperators.add(txtTrig.charAt(i)+"");
					str_Temp2 = "";
					k++;
				} 
				else 
				{
					str_Temp2 = str_Temp2 + txtTrig.charAt(i);
				}

				if (txtTrig.length() - 1 == i) {
					str_Temp[k] = str_Temp2;
					arr_TrigNumbers.add(str_Temp2);
				}
			}
		}		
	}
	
	public int func_chkError(String str_Text)
	{
		for (int i = 0; i < str_Text.length(); i++) 
		{
			if (i == 0) { } 
			else if (((str_Text.charAt(i) == 43) || str_Text.charAt(i) == 45)
					|| (((str_Text.charAt(i) >= 42 && str_Text.charAt(i) <= 47) || str_Text.charAt(i) == 94) && ((PrevChar >= 42 && PrevChar <= 47) || PrevChar == 94))
					|| (str_Text.charAt(i) == 48 && PrevChar == 47)
					|| ((str_Text.charAt(i) == 40 && PrevChar == 41) || (str_Text.charAt(i) == 41 && PrevChar == 40)
							|| (((str_Text.charAt(i) >= 42 && str_Text.charAt(i) <= 47) || str_Text.charAt(i) == 94) && PrevChar == 40))
					|| ((str_Text.charAt(i)>=42 && str_Text.charAt(i)<=47)&&(PrevChar == 110 || PrevChar == 115))
					|| (PrevChar == '\u221A' && (str_Text.charAt(i)>=42 && str_Text.charAt(i)<=47))
					|| (((PrevChar >= 42 && PrevChar <= 47) || PrevChar == 94) && ((str_Text.charAt(i) >= 97 && str_Text.charAt(i) <= 119) || (str_Text.charAt(i) >= 121 && str_Text.charAt(i) <= 122)))
					|| (((str_Text.charAt(i) >= 42 && str_Text.charAt(i) <= 47) || str_Text.charAt(i) == 94) && ((PrevChar >= 97 && PrevChar <= 119) || (PrevChar >= 121 && PrevChar <= 122)))
					|| ((str_Text.charAt(i) == 120 && (PrevChar >= 48 && PrevChar <= 57)) || (PrevChar == 120 && (str_Text.charAt(i) >= 48 && str_Text.charAt(i) <= 57)))){
				return 1;
			} 
			PrevChar = str_Text.charAt(i);
		}
		
		if (((str_Text.charAt(str_Text.length() - 1) == 40)
				|| ((str_Text.charAt(str_Text.length() - 1) >= 42) && (str_Text.charAt(str_Text.length() - 1) <= 47)))
				|| ((str_Text.contains("tan" + "\u03c0" + "/2")) || (str_Text.contains("/0")))) {
			return 1;
		}
		return 0;
	}
	
	public int func_chkMain(String txt_main)
	{
		String str_Text = new String();
		if (tFlag == 1){
			str_Text = txt_main.replace(str_TrigFunc, "x");
		} else{
			str_Text = txt_main;
		}
		for (int i = 0; i < str_Text.length(); i++) 
		{
			if (i == 0) { } 
			else if ((((str_Text.charAt(i) >= 42 && str_Text.charAt(i) <= 47) || str_Text.charAt(i) == 94) && ((PrevChar >= 42 && PrevChar <= 47) || PrevChar == 94))
					||(str_Text.charAt(i) == 48 && PrevChar == 47)
					|| ((str_Text.charAt(i) == 40 && PrevChar == 41) || (str_Text.charAt(i) == 41 && PrevChar == 40)
							|| (((str_Text.charAt(i) >= 42 && str_Text.charAt(i) <= 47) || str_Text.charAt(i) == 94) && PrevChar == 40))
					||((str_Text.charAt(i)>=42 && str_Text.charAt(i)<=47)&&(PrevChar == 110 || PrevChar == 115))
					|| (PrevChar == '\u221A' && (str_Text.charAt(i)>=42 && str_Text.charAt(i)<=47))
					|| (((PrevChar >= 42 && PrevChar <= 47) || PrevChar == 94) && ((str_Text.charAt(i) >= 97 && str_Text.charAt(i) <= 119) || (str_Text.charAt(i) >= 121 && str_Text.charAt(i) <= 122)))
					|| (((str_Text.charAt(i) >= 42 && str_Text.charAt(i) <= 47) || str_Text.charAt(i) == 94) && ((PrevChar >= 97 && PrevChar <= 119) || (PrevChar >= 121 && PrevChar <= 122)))
					|| ((str_Text.charAt(i) == 120 && (PrevChar >= 48 && PrevChar <= 57)) || (PrevChar == 120 && (str_Text.charAt(i) >= 48 && str_Text.charAt(i) <= 57)))){
				return 1; 
			}
			PrevChar = str_Text.charAt(i);
		}
		
		if (((str_Text.charAt(str_Text.length() - 1) == 40)
				|| ((str_Text.charAt(str_Text.length() - 1) >= 42) && (str_Text.charAt(str_Text.length() - 1) <= 47)))
				|| ((str_Text.contains("tan" + "\u03c0" + "/2")) || (str_Text.contains("/0")) || str_TextString.contains("()"))) {
			return 1;
		}

		return 0;
	}

}
