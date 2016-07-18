package main;

import java.util.Vector;

public class Promotion {
	
	private Vector<String> barcodes;
	private int level;
	
	public Promotion(Vector<String> barcodes, int level) {
		super();
		this.barcodes = barcodes;
		this.level = level;
	}

	public Vector<String> getBarcodes() {
		return barcodes;
	}

	public void setBarcodes(Vector<String> barcodes) {
		this.barcodes = barcodes;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	// 判断这种商品是不是正在进行这种促销
	public boolean isPromoting(String barcode){
		
		for(String str : barcodes){
			if(str.equals(barcode)){
				return true;
			}
		}
		return false;
		
	}
}