package com.shopping.dto;

public class SimpleGoodsDto {
	private int id;
	private int goodsId;
	private int number;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String toString() {
		return "SimpleGoodsDto [id=" + id + ", goodsId=" + goodsId
				+ ", number=" + number + "]";
	}
}
