package main;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class UnprofitableStore {
	
	private Map<String, Good> goods;			// 条形码可以唯一标识商品
	private Vector<Promotion> goodoffer;		// 优惠清单，记录参与优惠的商品的条形码
	
	public UnprofitableStore(){
		stock();
		setOfferInfo();
	}
	
	// 进货
	public void stock()
	{
		goods = new HashMap<String, Good>();
		goods.put("ITEM000000", new Good("ITEM000000", "可口可乐", "瓶", "食品",3.00));
		goods.put("ITEM000001", new Good("ITEM000001", "雪碧", "瓶", "食品", 3.00));
		goods.put("ITEM000002", new Good("ITEM000002", "羽毛球", "个", "运动器材", 1.00));
		goods.put("ITEM000003", new Good("ITEM000003", "苹果", "斤", "食品", 5.50));
	}
	
	// 优惠活动
	public void setOfferInfo()
	{
		goodoffer = new Vector<Promotion>();	
		Vector<String> barcodes = new Vector<String>();		//买二赠一
		barcodes.add("ITEM000000");
		barcodes.add("ITEM000002");
		goodoffer.add(new TwoForOnePromotion(barcodes,1));
		
		Vector<String> discount = new Vector<String>();		//95折
		discount.add("ITEM000000");
		discount.add("ITEM000003");
		goodoffer.add(new DiscountPromotion(discount,2));			
		
		// 对优惠活动按优先级排序
		Collections.sort(goodoffer,new Comparator<Promotion>() {
			public int compare(Promotion left, Promotion right) {
                Promotion l = (Promotion)left;
                Promotion r = (Promotion)right;
                return l.getLevel() - r.getLevel();
			}		
		});
	}
	
	public Map<String, Good> getGoods() {
		return goods;
	}

	public void setGoods(Map<String, Good> goods) {
		this.goods = goods;
	}

	public Vector<Promotion> getGoodoffer() {
		return goodoffer;
	}

	public void setGoodoffer(Vector<Promotion> goodoffer) {
		this.goodoffer = goodoffer;
	}

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		UnprofitableStore store = new UnprofitableStore();
		String data = "[ 'ITEM000000-3', 'ITEM000002-6', 'ITEM000003-2', 'ITEM000001-2' ]";
		ShoppingList shoplist = new ShoppingList(data, store.goods, store.goodoffer);
		shoplist.printShoppingList();	
	}
}
