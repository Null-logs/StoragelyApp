class Item {
    constructor(key, description, price, profit_percent, final_price, quantity, unit_description) {
        this.key = key;
        this.description = description;
        this.price = price;
        this.profit_percent = profit_percent;
        this.final_price = final_price;
        this.quantity = quantity;
        this.unit_description = unit_description;
    }
    
    static roundPriceToFourDecimals(){
		 return numero.toFixed(this.price);
	}
    
    
}