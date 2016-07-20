package main;

public class Good {
	private String barcode;
	private String name;
	private String unit;
	private String category;
	private double price;
	
	public Good(String _barcode, String _name, String _unit, 
		String _category, double _price  ){
		barcode = _barcode;
		name = _name;
		unit = _unit;
		category = _category;
		price = _price;
	}
	public Good() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getPrice() {
		return price;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
