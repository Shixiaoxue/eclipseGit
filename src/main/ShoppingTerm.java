package main;

public class ShoppingTerm {
	private Good good;
	private int num;
	private Promotion promotion;
	private double save;
	
	public ShoppingTerm(Good good, int num) {
		super();
		this.good = good;
		this.num = num;
	}

	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
		if(promotion instanceof TwoForOnePromotion){		
			int freeNum = getNum() / 3;
			save = freeNum * good.getPrice();
		}else if(promotion instanceof DiscountPromotion){		
			DiscountPromotion p = (DiscountPromotion)promotion;
			save = good.getPrice()*num * (1-p.getDiscount());
		}
	}

	public double getSave() {
		return save;
	}
}
