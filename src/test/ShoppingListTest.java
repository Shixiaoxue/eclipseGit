package test;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import main.ShoppingList;
import main.UnprofitableStore;
import main.util;

public class ShoppingListTest {

	@Test
	public void NoPromotion() throws IOException {
		//fail("Not yet implemented");
		UnprofitableStore store = new UnprofitableStore();
		String data = "[ 'ITEM000001-2' ]";
		ShoppingList shoplist = new ShoppingList(data, store.getGoods(), store.getGoodoffer());
		String result = shoplist.printShoppingList();	
		String Truth_res = util.txt2String(new File("NoPromotion_test.txt"));
		Assert.assertEquals(Truth_res, result);
	}
	
	@Test
	public void OnlyTwoForOne() throws IOException {
		//fail("Not yet implemented");
		UnprofitableStore store = new UnprofitableStore();
		String data = "[ 'ITEM000002-6' ]";
		ShoppingList shoplist = new ShoppingList(data, store.getGoods(), store.getGoodoffer());
		String result = shoplist.printShoppingList();	
		String Truth_res = util.txt2String(new File("OnlyTwoForOne_test.txt"));
		Assert.assertEquals(Truth_res, result);
	}
	
	@Test
	public void OnlyDiscount() throws IOException {
		//fail("Not yet implemented");
		UnprofitableStore store = new UnprofitableStore();
		String data = "[  'ITEM000003-2' ]";
		ShoppingList shoplist = new ShoppingList(data, store.getGoods(), store.getGoodoffer());
		String result = shoplist.printShoppingList();	
		String Truth_res = util.txt2String(new File("OnlyDiscount_test.txt"));
		Assert.assertEquals(Truth_res, result);
	}
	
	@Test
	public void GoodWithDiffPromotion() throws IOException {
		//fail("Not yet implemented");
		UnprofitableStore store = new UnprofitableStore();
		String data = "[ 'ITEM000000-5' ]";
		ShoppingList shoplist = new ShoppingList(data, store.getGoods(), store.getGoodoffer());
		String result = shoplist.printShoppingList();	
		String Truth_res = util.txt2String(new File("GoodWithDiffPromotion_test.txt"));
		Assert.assertEquals(Truth_res, result);
	}
	
	@Test
	public void AllKindsOf() throws IOException {
		//fail("Not yet implemented");
		UnprofitableStore store = new UnprofitableStore();
		String data = "[ 'ITEM000000-3', 'ITEM000002-6', 'ITEM000003-2', 'ITEM000001-2' ]";
		ShoppingList shoplist = new ShoppingList(data, store.getGoods(), store.getGoodoffer());
		String result = shoplist.printShoppingList();	
		String Truth_res = util.txt2String(new File("AllKindsOf_test.txt"));
		Assert.assertEquals(Truth_res, result);
	}
}
