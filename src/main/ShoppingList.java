package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Vector;

public class ShoppingList {
	private Vector<ShoppingTerm> shoplist;
	private Vector<Promotion> goodoffer;
	
	public ShoppingList(String inputStr, Map<String, Good> goods, Vector<Promotion> goodoffer) {
		// TODO Auto-generated constructor stub
		
		shoplist = new Vector<ShoppingTerm>();
		this.goodoffer = goodoffer;
		
		Vector<String> goodinfo = util.parseData(inputStr);
		
		for(String str : goodinfo){	
			int inter = str.indexOf('-');
			String barcode = str.substring(0,inter);
			int num = Integer.parseInt(str.substring(inter+1));
			
			Good curGood = goods.get(barcode);
			ShoppingTerm term = new ShoppingTerm(curGood, num);
			shoplist.add(term);
		}
	}
	
	/**
	 * 打印购物清单
	 * @throws FileNotFoundException 
	 */
	public String printShoppingList() throws FileNotFoundException 
	{
		PrintStream printStream = new PrintStream(new FileOutputStream("receipt.txt"));
        System.setOut(printStream);
		
		DecimalFormat df = new DecimalFormat("######0.00");
		double total = 0.0;
		double save = 0.0;
		
//		String twoForOneInfo = "买二赠一商品：\n";
		
		boolean twoforOneFlag = false;
		
		System.out.println("*<没钱赚商店>购物清单*");
		for(ShoppingTerm term : shoplist){
			
			String printStr = "";
			printStr += "名称："+term.getGood().getName()+"， ";
			printStr += "数量："+term.getNum()+term.getGood().getUnit()+"，";
			
			double price = term.getGood().getPrice();
			double subtotal = price * term.getNum();
			printStr += "单价："+df.format(price)+"（元），";
			
			// 优惠信息判断
			for(Promotion promotion : goodoffer){				
				if(promotion.isPromoting(term.getGood().getBarcode())){	
					
					if(!twoforOneFlag){
						twoforOneFlag = promotion instanceof TwoForOnePromotion;
					}
					term.setPromotion(promotion);			
					break;
				}
			}
			
			double subsave = term.getSave();
			save += subsave;
			
			printStr += "小计："+df.format(subtotal-subsave)+"（元）";	
			
			if(term.getPromotion() instanceof DiscountPromotion){
				printStr += ",节省"+df.format(subsave)+"（元）";
			}
			
			System.out.println(printStr);	
			total += subtotal;	
		}
		
		System.out.println("---------------------------------------------");
		
		if(twoforOneFlag){	
			System.out.println("买二赠一商品："); 
			for(ShoppingTerm term : shoplist){
				if(term.getPromotion() instanceof TwoForOnePromotion){
					System.out.println("名称："+term.getGood().getName()+","+"数量："+ term.getNum() / 3);
				}		
			}
		}
		
		System.out.println("---------------------------------------------");
		String printStr = "";
		printStr += "总计："+df.format(total-save)+"（元）\n";
		if(save > 1e-8){
			printStr += "节省："+df.format(save)+"（元）\n";
		}
		System.out.println(printStr);
		System.out.println("*********************************************");
		
		String receipt = util.txt2String(new File("receipt.txt"));
		return receipt;
	}	
}
