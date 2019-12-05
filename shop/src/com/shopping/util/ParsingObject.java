package com.shopping.util;

import com.shopping.dto.SimpleGoodsDto;

public class ParsingObject {
	public static SimpleGoodsDto getGoodsObject(String goods){
		//{"id":"9","goodsId":"7","num":"1"}
		String firstSym = String.valueOf(goods.charAt(0));
		String lastSym = String.valueOf(goods.charAt(goods.length()-1));
		SimpleGoodsDto sgd = new SimpleGoodsDto();
		if(firstSym.equals("{")&&lastSym.equals("}")){
			goods = goods.substring(1, goods.length()-1);
			String[] objectStr = goods.split(",");
			for (int i = 0; i < objectStr.length; i++) {
				String[] eleStr = objectStr[i].split(":");
				int ele = Integer.parseInt(eleStr[1].substring(1, eleStr[1].length()-1));
				if(i==0){
					sgd.setId(ele);
				}else if(i==1){
					sgd.setGoodsId(ele);
				}else{
					sgd.setNumber(ele);
				}
			}
		}
		return sgd;
	}
}
