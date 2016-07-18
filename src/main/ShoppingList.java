package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
		
		Vector<String> goodinfo = parseData(inputStr);
		
		for(String str : goodinfo){	
			int inter = str.indexOf('-');
			String barcode = str.substring(0,inter);
			int num = Integer.parseInt(str.substring(inter+1));
			
			Good curGood = goods.get(barcode);
			ShoppingTerm term = new ShoppingTerm(curGood, num);
			shoplist.add(term);
		}
		//printShoppingList();
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
			
//			DecimalFormat df = new DecimalFormat("######0.00");
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
		
		String receipt = txt2String(new File("receipt.txt"));
		return receipt;
	}
	
	public String txt2String(File file){
        String result = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result = result + "\n" +s;
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
	
	/**
	 * 解析输入条形码数组
	 * @param inputStr
	 * @return
	 */
	private Vector<String> parseData(String inputStr){		
		Vector<String> res = new Vector<String>();
		int curs = inputStr.indexOf('\'');
		while(curs != -1){
			int curt = inputStr.indexOf('\'',curs+1);			
			String curStr = inputStr.substring(curs+1,curt);					
			res.add(curStr);			
			curs = inputStr.indexOf('\'',curt+1);
		}
		return res;
	}
}
