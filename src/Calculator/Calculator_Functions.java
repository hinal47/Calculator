package Calculator;

import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator_Functions {
	private String str_TextCalc;
	private ArrayList<String> arr_Equations = new ArrayList<String>();
	private ArrayList<String> arr_Numbers = new ArrayList<String>();
	private ArrayList<String> arr_Operators = new ArrayList<String>();
	private int ch_Flag1 = 0;
	private int ch_Flag2 = 0;
	private int ch_Flag3 = 0;
	private String str_Result = null;
	private String str_Answer = "0";

	// ************ Getters ***************
	public String get_TextCalc() {
		return this.str_TextCalc;
	}

	public ArrayList<String> get_Equations() {
		return this.arr_Equations;
	}

	public ArrayList<String> get_Numbers() {
		return this.arr_Numbers;
	}

	public ArrayList<String> get_Operators() {
		return this.arr_Operators;
	}

	public int get_Flag1() {
		return this.ch_Flag1;
	}

	public int get_Flag2() {
		return this.ch_Flag2;
	}

	public int get_Flag3() {
		return this.ch_Flag3;
	}

	public String get_Result() {
		return this.str_Result;
	}

	// ************ Setters *****************************
	public void set_TextCalc(String str_TextCalc) {
		this.str_TextCalc = str_TextCalc;
	}

	public void set_Equations(ArrayList<String> arr_Equations) {
		this.arr_Equations = arr_Equations;
	}

	public void set_Numbers(ArrayList<String> arr_Numbers) {
		this.arr_Numbers = arr_Numbers;
	}

	public void set_Operators(ArrayList<String> arr_Operators) {
		this.arr_Operators = arr_Operators;
	}

	public void set_OneFlag(int ch_Flag1) {
		this.ch_Flag1 = ch_Flag1;
	}

	public void set_TwoFlag(int ch_Flag2) {
		this.ch_Flag2 = ch_Flag2;
	}

	public void set_ThreeFlag(int ch_Flag3) {
		this.ch_Flag3 = ch_Flag3;
	}

	public void func_addNumbers(String str_Number) {
		this.arr_Numbers.add(str_Number);
	}

	public void func_addOperators(String str_Operator) {
		this.arr_Operators.add(str_Operator);
	}

	public void func_addEquations(String str_Equation) {
		this.arr_Equations.add(str_Equation);
	}

	// *********** Functions to delete ***********

	public void func_delOperators() {
		this.arr_Operators.remove(arr_Operators.size()-1);
	}

	public void func_delNumbers() {
		this.arr_Numbers.remove(arr_Numbers.size()-1);
	}
	
	public void func_delAllOperators()
	{
		this.arr_Operators.removeAll(arr_Operators);
	}

	// Functions to check for any errors
	public int check_Error() {
		int err = 0;
		char ch_Prev = ' ';
		for (int i = 0; i < str_TextCalc.length(); i++) 
		{
			if (i == 0) {
			} else if ((str_TextCalc.charAt(i) >= 42 && str_TextCalc.charAt(i) <= 47) && (ch_Prev >= 42 && ch_Prev <= 47)) {
				err = 1;
				break;
			} else if (str_TextCalc.charAt(i) == 48 && ch_Prev == 47) {
				err = 1;
				break;
			} 
			else if ((str_TextCalc.charAt(i) == 40 && ch_Prev == 41) || (str_TextCalc.charAt(i) == 41 && ch_Prev == 40)
					|| ((str_TextCalc.charAt(i) >= 42 && str_TextCalc.charAt(i) <= 47) && ch_Prev == 40)) {
				err = 1;
				break;
			} else if ((str_TextCalc.charAt(i)>=42 && str_TextCalc.charAt(i)<=47)&&(ch_Prev == 110 || ch_Prev == 115)){
				err = 1;
				break;
			} else if (ch_Prev == '\u221A' && (str_TextCalc.charAt(i)>=42 && str_TextCalc.charAt(i)<=47)){
				err = 1;
				break;
			}

			ch_Prev = str_TextCalc.charAt(i);
		}

		if ((str_TextCalc.charAt(str_TextCalc.length() - 1) == 40)
				|| ((str_TextCalc.charAt(str_TextCalc.length() - 1) >= 42) && (str_TextCalc.charAt(str_TextCalc.length() - 1) <= 47))) {
			err = 1;
		}

		if ((str_TextCalc.contains("tan" + "\u03c0" + "/2")) || (str_TextCalc.contains("/0"))) {
			err = 1;
		}

		return err;
	}

	public void func_Replace() {
		for (int i = 0; i < arr_Numbers.size(); i++) {
			if ((arr_Numbers.get(i).contains("\u03c0"))) {
				String replacer = arr_Numbers.get(i);
				String replaceString = replacer.replaceAll("\u03c0", "3.14");
				arr_Numbers.set(i, replaceString);
			}
			if ((arr_Numbers.get(i).contains("e"))) {
				String replacer = arr_Numbers.get(i);
				String replaceString = replacer.replace("e", "2.718");
				arr_Numbers.set(i, replaceString);
			}
			if ((arr_Numbers.get(i).contains("ANS"))) {
				String replacer = arr_Numbers.get(i);
				String replaceString = replacer.replace("ANS", str_Answer);
				arr_Numbers.set(i, replaceString);
			}
		}
		
		for(int i = 0; i < arr_Numbers.size(); i++){
			if ((arr_Numbers.get(i).contains("Sqrt"))) {
				String rep_fn = arr_Numbers.get(i).substring(1);
				Double temp3 = Double.parseDouble(rep_fn);
				Double temp_res = Math.sqrt(temp3);
				arr_Numbers.set(i, temp_res.toString());
			}
		}

		for (int i = 0; i < arr_Numbers.size(); i++) {
			if ((arr_Numbers.get(i).contains("sin"))) {
				String rep_fn = arr_Numbers.get(i).substring(3);
				Double temp3 = Double.parseDouble(rep_fn);
				Double temp_res = Math.sin(temp3);
				arr_Numbers.set(i, temp_res.toString());
			}

			if ((arr_Numbers.get(i).contains("tan"))) {
				String rep_fn = arr_Numbers.get(i).substring(3);
				Double temp3 = Double.parseDouble(rep_fn);
				Double temp_res = Math.tan(temp3);
				arr_Numbers.set(i, temp_res.toString());
			}

			if ((arr_Numbers.get(i).contains("cos"))) {
				String rep_fn = arr_Numbers.get(i).substring(3);
				Double temp3 = Double.parseDouble(rep_fn);
				Double temp_res = Math.cos(temp3);
				arr_Numbers.set(i, temp_res.toString());
			}

			if ((arr_Numbers.get(i).contains("ln"))) {
				String rep_fn = arr_Numbers.get(i).substring(2);
				Double temp3 = Double.parseDouble(rep_fn);
				Double temp_res = Math.log(temp3);
				arr_Numbers.set(i, temp_res.toString());
			}
		}
	}

	public String resultCal() {
		if (check_Error() == 1) {
			str_Result = "Error or Infinity!!!";
			return str_Result;
		} else {
			String[] str_Temp = new String[30];
			String str_Temp2 = new String("");
			// All the operators which are in split can only be splitted
			int k = 0;
			for (int i = 0; i < str_TextCalc.length(); i++) {
				if (str_TextCalc.charAt(i) == '^' || str_TextCalc.charAt(i) == '/' || str_TextCalc.charAt(i) == '*'
						|| str_TextCalc.charAt(i) == '+' || str_TextCalc.charAt(i) == '-') {
					str_Temp[k] = str_Temp2;
					arr_Numbers.add(str_Temp2);
					str_Temp2 = "";
					k++;
				} 
				else {
					str_Temp2 = str_Temp2 + str_TextCalc.charAt(i);
				}

				if (str_TextCalc.length() - 1 == i) {
					str_Temp[k] = str_Temp2;
					arr_Numbers.add(str_Temp2);
				}

			}

			func_Replace();

			String str_Input = func_Input();

			double dbl_Result = func_Calculate(str_Input);
			
			arr_Numbers = new ArrayList<String>();
			arr_Operators = new ArrayList<String>();
			
			str_Answer = dbl_Result + "";

			return (dbl_Result + "");
		}
	}

	//The following function is to calculate the operations
	private double func_Calculate(String str) {

		StringTokenizer str_Token = new StringTokenizer(str);

		ArrayList<String> arr_List = new ArrayList<String>();
		arr_List.add("+");
		arr_List.add("-");
		arr_List.add("*");
		arr_List.add("/");
		arr_List.add("^");

		char ch_Sign = '+';
		double dbl_Value = 0.0;

		int len = str_Token.countTokens();
		int i = 0;

		Stack<Double> stack = new Stack<Double>();

		while (i < len) {

			String str_Copy = str_Token.nextToken();

			
			if (!arr_List.contains(str_Copy)) {
				dbl_Value = Double.parseDouble(str_Copy);
			}

			if ((arr_List.contains(str_Copy)) || i == len - 1) {
				
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

				ch_Sign = str_Copy.charAt(0);
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

	
	private String func_Input() {

		StringBuilder obj_Builder = new StringBuilder();

		int i = 0, j = 0, k = 0;
		while (i < arr_Numbers.size() + arr_Operators.size()) {
			if (i % 2 == 0) {
				if (i == arr_Numbers.size() + arr_Operators.size() - 1) {
					obj_Builder.append(arr_Numbers.get(j++));
				} else {
					obj_Builder.append(arr_Numbers.get(j++) + " ");
				}
			} else {

				obj_Builder.append(arr_Operators.get(k++) + " ");
			}
			i++;
		}

		StringTokenizer str_Token = new StringTokenizer(obj_Builder.toString());
		obj_Builder = new StringBuilder();

		String prev = "";
		boolean op_flag = false;
		int prevLength = 0;
		while (str_Token.hasMoreTokens()) {
			String s_val = str_Token.nextToken();
			if (s_val.equals("^")) {
				op_flag = true;
				continue;
			} else {
				if (op_flag) {
					double dbl_value1 = Double.parseDouble(obj_Builder.substring(obj_Builder.length() - prevLength - 1, obj_Builder.length() - 1));
					double dbl_value2 = Double.parseDouble(s_val);
					double ans = Math.pow(dbl_value1, dbl_value2);
					obj_Builder = new StringBuilder(obj_Builder.substring(0, obj_Builder.length() - prevLength - 1));
					obj_Builder.append(ans + " ");
					op_flag = false;
				} else {
					obj_Builder.append(s_val + " ");
				}
			}
			prev = s_val;
			prevLength = prev.length();
		}
		return obj_Builder.toString();
		}
}
