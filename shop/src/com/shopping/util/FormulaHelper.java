package com.shopping.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FormulaHelper {
	private static Random r = new Random();
	private static String[] allSymbol = {"＋","－","×","÷"};
	
	public static String[] getAddSubMulFormula(int numSize, int StartNum, int endNum){
		ArrayList<String> formula = new ArrayList<String>();
		//获得符号的总量以及算式的容量
		int formulaSize = numSize*2+1;
		String str = null;
		
		for (int i = 0; i < formulaSize; i++) {
			//生成数字以及符号的下标
			if(i%2==0){
				int numberIndex = r.nextInt(endNum-StartNum+1)+StartNum;
				str = numberIndex+"";
			}else{
				int symbolIndex = r.nextInt(3);
				str = allSymbol[symbolIndex];
			}
			//加入生成的数组
			formula.add(str);
		}

		String formulas = formula.toString();
		formulas = formulas.substring(1, formulas.length()-1).replace(",", "").replace(" ", "");
		//计算结果
		int result = countMultip(formula);
		String[] returnResult = {formulas,String.valueOf(result)};
		
		return returnResult;
	}
	
	//计算结果
	public static int countMultip(List<String> formula){
		int rideIndex = formula.indexOf("×");
		int result = 0;
		
		//先把乘法全算了，再算加减法
		if(rideIndex!=-1){
			//计算找到的第一个乘式
			int beforeNum = Integer.parseInt(formula.get(rideIndex-1));
			int afterNum = Integer.parseInt(formula.get(rideIndex+1));
			int midResult = beforeNum * afterNum;
			//将结果替换进去
			formula.remove(rideIndex+1);
			formula.remove(rideIndex);
			formula.set(rideIndex-1, midResult+"");
			//迭代计算所有乘式
			result = countMultip(formula);
		}else{
			//先把第一位数加上
			result += Integer.parseInt(formula.get(0));
			//循环加上后面的数字
			for (int i = 0; i < formula.size()/2; i++) {
				//获得要加的数字
				int afterNum = Integer.parseInt(formula.get((i+1)*2));
				//判断符号
				if(formula.get(i*2+1).equals("－")){
					result = result - afterNum;
				}else{
					result = result + afterNum;
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		FormulaHelper.getAddSubMulFormula(4,0,9);
		
	}
}
