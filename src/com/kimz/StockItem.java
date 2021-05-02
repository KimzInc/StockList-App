	package com.kimz;

public class StockItem implements Comparable<StockItem>{

    private final String name;
    private double price;
    private int quantityInStock = 0;
    private int reserved = 0;



    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityInStock = 0;
    }

    public StockItem(String name, double price, int quantityInStock){
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    //determines the quantity available
    public int availableQuantity(){
        return quantityInStock - reserved;
    }


    public int quantityInStock() {
        return quantityInStock;
    }

    public void setPrice(double price) {
        if (price>0.0){
            this.price = price;
        }
    }

    public void adjustStock(int quantity) {
        int newQuantity = this.quantityInStock + quantity;
        if (newQuantity>=0){
            this.quantityInStock = newQuantity;
        }
    }

    //method to reserve stock
    public int reserveStock(int quantity){
        if (quantity<= availableQuantity() ){
            reserved+=quantity;
            return quantity;
        }
        return 0;
    }

    //method to un-reserve stock
    public int unreserveStock(int quantity){
        if(quantity<=reserved){
            reserved-=quantity;
            return quantity;
        }
        return 0;
    }

    public int finaliseStock(int quantity){
        if (quantity<=reserved){
            quantityInStock-=quantity;
            reserved-=quantity;
            return quantity;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Entering stockItem.equals");
        //check for self comparison
        if(obj==this){
            return true;
        }
        if ((obj==null) || (obj.getClass()!=this.getClass())){
            return false;
        }
        String objName = ((StockItem)obj).getName();

        return this.name.equals(objName);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 31;
    }

    @Override
    public int compareTo(StockItem o) {
        System.out.println("Entering StockItem.compareTo");
        //compare object passed with the object in
        //memory
        if (this==o){
            //return zero if they are equal
            return 0;
        }
        if (o!=null){
            return this.name.compareTo(o.getName()); //returns -1, 0, 1
        }
    throw new NullPointerException(); //we are not comparing something that is null
    }

    @Override
    public String toString() {
        return this.name + ": price " + this.price + ". Reserved: " +this.reserved;
    }
}
