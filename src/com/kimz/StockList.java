package com.kimz;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list;

    public StockList(){
        this.list = new HashMap<>();
    }

    //add stock method
    public int addStock(StockItem item){
        if (item!=null){
            //check if already have quantities of this item
            StockItem inStock = list.getOrDefault(item.getName(), item);
            //if there are already stocks on this item, adjust quantity
            if (inStock!=item){
                item.adjustStock(inStock.quantityInStock());
            }
            list.put(item.getName(),item);
            return item.quantityInStock();
        }
        return 0;
    }

    public int sellStock(String item, int quantity){
        //assumption is that item is on list - null
        StockItem inStock = list.getOrDefault(item, null);
        if((inStock!=null) && (inStock.quantityInStock()>=quantity) &&(quantity>0)){
            inStock.adjustStock(-quantity);
            return quantity;
        }
        return 0; //nothing in stock, not enough or quantity passed is equal or less than zero
    }

    public StockItem get(String key){
        return list.get(key); //return object
    }

    public Map<String, StockItem> Items(){
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
       String s = "\nStock List\n";
       double totalCost = 0.0;
       for (Map.Entry<String, StockItem> item : list.entrySet()){
           StockItem stockItem = item.getValue();
           double itemValue = stockItem.getPrice();

           s = s + stockItem + ". There are " + stockItem.quantityInStock() + " in stock. Value of items: ";
           s = s + itemValue + "\n";
           totalCost+= itemValue;
       }
       return s + "Total stock value " + totalCost;
    }
}
