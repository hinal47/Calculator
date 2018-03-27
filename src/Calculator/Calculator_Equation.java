package Calculator;

import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator_Equation
{
	public int tFlag = 0;
	public int errFlag = 0;
	public int errMain = 0;
	public double dbl_xLow = 0;
	public double dbl_xHigh = 0;
	public double dbl_yLow = 0;
	public double dbl_yHigh = 0;
	public String str_TrigFunc = new String();
	public ArrayList<String> arr_TrigNumbers = new ArrayList<String>();
	public ArrayList<String> arr_TrigOperators = new ArrayList<String>();
	public ArrayList<String> arr_finalNumbers = new ArrayList<String>();
	public ArrayList<String> arr_finalOperators = new ArrayList<String>();
	public ArrayList<Double> arr_xPoints = new ArrayList<Double>();
	public ArrayList<Double> arr_yPoints = new ArrayList<Double>();
	

	// ************ Setters *****************
	
	public void func_setTFlag(int tFlag){
		this.tFlag = tFlag;
	} public void func_setErrFlag(int errFlag){
		this.errFlag = errFlag;
	} public void func_setErrMain(int errMain){
		this.errMain = errMain;
	} public void func_setXLow(double dbl_xLow){
		this.dbl_xLow = dbl_xLow;
	} public void func_setYLow(double dbl_yLow){
		this.dbl_yLow = dbl_yLow;
	} public void func_setXHigh(double dbl_xHigh){
		this.dbl_xHigh = dbl_xHigh;
	} public void func_setYHigh(double dbl_yHigh){
		this.dbl_yHigh = dbl_yHigh;
	} public void func_setTrigFunc(String str_TrigFunc){
		this.str_TrigFunc = str_TrigFunc;
	} public void func_setTrigNumbers(ArrayList<String> arr_TrigNumbers){
		this.arr_TrigNumbers = arr_TrigNumbers;
	} public void func_setTrigOperators(ArrayList<String> arr_TrigOperators){
		this.arr_TrigOperators = arr_TrigOperators;
	} public void func_setFinalNumbers(ArrayList<String> arr_finalNumbers){
		this.arr_finalNumbers = arr_finalNumbers;
	} public void func_setFinalOperators(ArrayList<String> arr_finalOperators){
		this.arr_finalOperators = arr_finalOperators;
	}
	

	
	public void func_CalculateGraph()
	{
		if(errFlag == 0 && errMain == 0)
		{
			if(tFlag==0)
			{
				ArrayList<Integer> arr_X = new ArrayList<Integer>();
				for(int i=0; i<arr_finalNumbers.size(); i++)
				{
					String str_Temp = arr_finalNumbers.get(i);
					if(str_Temp.equals("x"))
					{
						arr_X.add(i);
					}
				}
				for (double i=dbl_xLow; i<=dbl_xHigh; i=i+0.05)
				{
					arr_xPoints.add(i);
					ArrayList<String> arr_Temp = arr_finalNumbers;
					for (int j=0; j<arr_X.size(); j++)
					{
						arr_Temp.add(arr_X.get(j), Double.toString(i));
						arr_Temp.remove(arr_X.get(j)+1);
					}
					String str_Input = func_Input(arr_Temp, arr_finalOperators);
					double dbl_Result = func_Calculate(str_Input);
					arr_yPoints.add(dbl_Result);
				}
			}
			else
			{
				ArrayList<Integer> arr_X = new ArrayList<Integer>();
				ArrayList<Integer> arr_Trig = new ArrayList<Integer>();
				ArrayList<Integer> Arr_finalX = new ArrayList<Integer>();
				
				for(int i=0; i<arr_TrigNumbers.size(); i++)
				{
					String str_Temp = arr_TrigNumbers.get(i);
					if(str_Temp.equals("x"))
					{
						arr_X.add(i);
					}
				}
				
				for(int i=0; i<arr_finalNumbers.size(); i++)
				{
					String str_Temp = arr_finalNumbers.get(i);
					if(str_Temp.equals("x"))
					{
						Arr_finalX.add(i);
					}
					if(str_Temp.equals(str_TrigFunc))
					{
						arr_Trig.add(i);
					}
				}
				
				for (double i=dbl_xLow; i<=dbl_xHigh; i=i+0.05)
				{
					arr_xPoints.add(i);
					ArrayList<String> arr_Temp = arr_TrigNumbers;
					ArrayList<String> arr_Temp2 = arr_finalNumbers;
					
					for (int j=0; j<arr_X.size(); j++)
					{
						arr_Temp.add(arr_X.get(j), Double.toString(i));
						arr_Temp.remove(arr_X.get(j)+1);
					}
					
					String str_Input = func_Input(arr_Temp, arr_TrigOperators);
					double dbl_Result = func_Calculate(str_Input);
					
					for (int j=0; j<Arr_finalX.size(); j++)
					{
						arr_Temp2.add(Arr_finalX.get(j), Double.toString(i));
						arr_Temp2.remove(Arr_finalX.get(j)+1);
					}
					
					double dbl_Trig = 0;
					for (int j=0; j<arr_Trig.size(); j++)
					{
						if(str_TrigFunc.equals("sin")){
							dbl_Trig = Math.sin(dbl_Result);
						} else if (str_TrigFunc.equals("cos")){
							dbl_Trig = Math.cos(dbl_Result);
						} else if (str_TrigFunc.equals("tan")){
							dbl_Trig = Math.tan(dbl_Result);
						} else if (str_TrigFunc.equals("ln")){
							dbl_Trig = Math.log(dbl_Result);
						}
						arr_Temp2.add(arr_Trig.get(j), Double.toString(dbl_Trig));
						arr_Temp2.remove(arr_Trig.get(j)+1);
					}
					
					String inputStringFinal = func_Input(arr_Temp2, arr_finalOperators);
					double resultFinal = func_Calculate(inputStringFinal);
					arr_yPoints.add(resultFinal);
				}
			}
		}
		else
		{
			System.out.println("Incorrect Equation!!!");
		}
	}
	
	
	// TODO Auto-generated method stub
	private String func_Input(ArrayList<String> arr_Numbers, ArrayList<String> arr_Operators) {

		StringBuilder objBuilder = new StringBuilder();

		int i = 0, j = 0, k = 0;
		while (i < arr_Numbers.size() + arr_Operators.size()) {
			if (i % 2 == 0) {
				if (i == arr_Numbers.size() + arr_Operators.size() - 1) {
					objBuilder.append(arr_Numbers.get(j++));
				} else {
					objBuilder.append(arr_Numbers.get(j++) + " ");
				}
			} else {

				objBuilder.append(arr_Operators.get(k++) + " ");
			}
			i++;
		}

		StringTokenizer obj_Token = new StringTokenizer(objBuilder.toString());
		objBuilder = new StringBuilder();

		String prev = "";
		boolean op_flag = false;
		int prevLength = 0;
		while (obj_Token.hasMoreTokens()) {
			String str_Value = obj_Token.nextToken();
			if (str_Value.equals("^")) {
				op_flag = true;
				continue;
			} else {
				if (op_flag) {
					double val1 = Double.parseDouble(objBuilder.substring(objBuilder.length() - prevLength - 1, objBuilder.length() - 1));
					double val2 = Double.parseDouble(str_Value);
					double ans = Math.pow(val1, val2);
					objBuilder = new StringBuilder(objBuilder.substring(0, objBuilder.length() - prevLength - 1));
					objBuilder.append(ans + " ");
					op_flag = false;
				} else {
					objBuilder.append(str_Value + " ");
				}
			}
			prev = str_Value;
			prevLength = prev.length();
		}
		return objBuilder.toString();
	}
	
	private double func_Calculate(String str) {

		StringTokenizer obj_Token = new StringTokenizer(str);

		ArrayList<String> arr_List = new ArrayList<String>();
		arr_List.add("+");
		arr_List.add("-");
		arr_List.add("*");
		arr_List.add("/");
		arr_List.add("^");

		char ch_Sign = '+';
		double dbl_Value = 0.0;

		int len = obj_Token.countTokens();
		int i = 0;

		Stack<Double> stack = new Stack<Double>();

		while (i < len) {

			String s_copy = obj_Token.nextToken();

			
			if (!arr_List.contains(s_copy)) {
				dbl_Value = Double.parseDouble(s_copy);
			}

			if ((arr_List.contains(s_copy)) || i == len - 1) {
				
				if (ch_Sign == '-') {
					stack.push(-dbl_Value);
				}
				if (ch_Sign == '+') {
					stack.push(dbl_Value);
				}
				if (ch_Sign == '*') {
					stack.push(stack.pop() * dbl_Value);
				}
				if (ch_Sign == '/') {
					stack.push(stack.pop() / dbl_Value);
				}

				ch_Sign = s_copy.charAt(0);
				dbl_Value = 0.0;

			}
			i++;
		}

		double dbl_Result = 0;
		for (double k : stack) {
			dbl_Result += k;
		}
		return dbl_Result;
	}
	
}

