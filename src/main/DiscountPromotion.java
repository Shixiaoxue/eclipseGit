package main;

import java.util.Vector;

public class DiscountPromotion extends Promotion {

	private double discount = 0.95;
	
	public DiscountPromotion( Vector<String> barcodes, int level) {
		super(barcodes, level);
		// TODO Auto-generated constructor stub
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	
}
