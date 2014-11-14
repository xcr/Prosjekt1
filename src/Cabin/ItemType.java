package Cabin;

import Cabin.Item;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Eirik on 14.11.2014.
 */
public class ItemType {

    private StringProperty name, amount;
    private ObservableList<Item> items = FXCollections.observableArrayList();


    public ItemType(String name){
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleStringProperty("0");
    }
    public ItemType(String name, String amount, Item item){
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleStringProperty(amount);
        this.items.add(item);
    }

    public void amountIncrease(int am){

        int amt = Integer.parseInt(amount.get()) + am;
        amount.set(""+amt);
    }
    public void amountDecrease(int am){
        amount.set(""+(Integer.parseInt(amount.get()) - am));
    }


    public String getItemName(){return name.get();}
    public String getAmount(){return amount.get();}
    public ObservableList<Item> getItemList(){ return this.items;}
    public StringProperty getItemNameProperty(){return this.name;}
    public StringProperty getAmountProperty(){return this.amount;}


    public void addItem(Item i){
        int ammm = Integer.parseInt(i.getAmount());
        amountIncrease(ammm);
        items.add(i);

    }

    public void removeItem(Item i){
        amountDecrease(Integer.parseInt(i.getAmount()));
        for(Item j : items){
            if(j.getCabinName().equals(i.getCabinName())){
                items.remove(j);
            }
        }
    }

    public void updateAmount(){
        int am = 0;
        for(Item i : items){
            am += Integer.parseInt(i.getAmount());
        }
        this.amount.set(""+am);
    }


}
