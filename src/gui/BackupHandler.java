package gui;

import sqldata.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.util.Calendar;


import java.io.*;


/**
 * Created by Eirik on 16.11.2014.
 */
public class BackupHandler {


    private static String split = "-:-";
    public static void writeItemBackup(ObservableList<Item> itemData){

        Writer writer = null;
        try{
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("backup/ItemBackup.txt"), "utf-8"));
            writer.write("This backup was made on " + Calendar.getInstance().getTime().toString()+"\n\n");


            for(Item i : itemData){
                writer.write(i.getCabinName()+split+i.getItemName()+split+i.getAmount()+split+i.getId()+"\n");
            }


            writer.close();
        }
        catch(IOException e){
            System.out.println("Item backup failed!" + e);
        }
        finally{
            try{writer.close();}catch(Exception ex){}
        }
    }
    public static void writeCabinBackup(ObservableList<Cabin> cabinData){

        Writer writer = null;
        try{
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("backup/CabinBackup.txt"), "utf-8"));
            writer.write("This backup was made on " + Calendar.getInstance().getTime().toString()+"\n\n");


            for(Cabin c : cabinData){
                writer.write(c.getId()+split+c.getName()+split+c.getBednumber()+split+c.getTablenumber()+split
                +c.getYear()+split+c.getTerrain()+split+c.getBike()+split+c.getTrip()+split+c.getGuitar()+split
                +c.getWaffleiron()+split+c.getHunting()+split+c.getFishing()+split+c.getSpecialities()+split+c.getWood()+"\n");
            }


            writer.close();
        }
        catch(IOException e){
            System.out.println("sqldata backup failed!" + e);
        }
        finally{
            try{writer.close();}catch(Exception ex){}
        }
    }

    public static void writeMailInterfaceBackup(ObservableList<MailInterface> mailData){

        Writer writer = null;
        try{
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("backup/MailInterfaceBackup.txt"), "utf-8"));
            writer.write("This backup was made on " + Calendar.getInstance().getTime().toString()+"\n\n");


            for(MailInterface m : mailData){
                writer.write(m.getName()+split+m.getEmail()+split+m.getSubject().get()+"\n"+m.getDescription()+"\n"+"-----"+"\n");

            }


            writer.close();
        }
        catch(IOException e){
            System.out.println("MainInterface backup failed!" + e);
        }
        finally{
            try{writer.close();}catch(Exception ex){}
        }
    }
    public static void writeReservationBackup(ObservableList<Reservation> reservationData){

        Writer writer = null;
        try{
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("backup/ReservationBackup.txt"), "utf-8"));
            writer.write("This backup was made on " + Calendar.getInstance().getTime().toString()+"\n\n");


            for(Reservation r : reservationData){
                writer.write(r.getName()+split+r.getEmail()+split+r.getStartDate()+split+r.getEndDate()
                +split+r.getfirstname()+split+r.getlastname()+"\n");
            }


            writer.close();
        }
        catch(IOException e){
            System.out.println("Reservation backup failed!" + e);
        }
        finally{
            try{writer.close();}catch(Exception ex){}
        }
    }
    public static void writeOutboxBackup(ObservableList<Sent> outbox){

        Writer writer = null;
        try{
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("backup/OutboxBackup.txt"), "utf-8"));
            writer.write("This backup was made on " + Calendar.getInstance().getTime().toString()+"\n\n");


            for(Sent i : outbox){
                writer.write(i.getTo()+split+i.getSubject()+"\n"+i.getBody()+"\n"+"-----"+"\n");
            }


            writer.close();
        }
        catch(IOException e){
            System.out.println("Outbox backup failed!" + e);
        }
        finally{
            try{writer.close();}catch(Exception ex){}
        }
    }

    //read backup starts here

    public static ObservableList<Cabin> readCabinBackup(){

        ObservableList<Cabin> cabinData = FXCollections.observableArrayList();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("backup/CabinBackup.txt"));
            System.out.println(reader.readLine());
            reader.readLine();
            while(reader.ready()){
                String[] data = reader.readLine().split(split);
                cabinData.add(new Cabin(data[0],data[1],data[2],data[3],Integer.parseInt(data[4]),data[5],data[6],data[7]
                        ,data[8],data[9],data[10],data[11],data[12],data[13]));
            }

            reader.close();

        }catch(IOException e){
            System.out.println("Reading cabin backup failed!" + e);
        }
        return cabinData;
    }

    public static ObservableList<Item> readItemBackup(){

        ObservableList<Item> itemData = FXCollections.observableArrayList();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("backup/ItemBackup.txt"));
            System.out.println(reader.readLine());
            reader.readLine();
            while(reader.ready()){
                String[] data = reader.readLine().split(split);
                itemData.add(new Item(data[0],data[1],data[2],data[3]));
            }


            reader.close();

        }catch(IOException e){
            System.out.println("Reading cabin backup failed!" + e);
        }
        return itemData;
    }


    public static ObservableList<Reservation> readReservationBackup(){

        ObservableList<Reservation> reservationData = FXCollections.observableArrayList();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("backup/ReservationBackup.txt"));
            System.out.println(reader.readLine());
            reader.readLine();
            while(reader.ready()){
                String[] data = reader.readLine().split(split);
                reservationData.add(new Reservation(null, null,data[0],data[1],data[2],data[3],data[4],data[5]));
            }


            reader.close();

        }catch(IOException e){
            System.out.println("Reading cabin backup failed!" + e);
        }
        return reservationData;
    }

    public static ObservableList<Sent> readOutboxBackup(){

        ObservableList<Sent> outboxData = FXCollections.observableArrayList();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("backup/OutboxBackup.txt"));
            System.out.println(reader.readLine());
            reader.readLine();
            while(reader.ready()){
                String[] data = reader.readLine().split(split);
                String str = "";
                int count = 0;
                while(true){
                    String temp = reader.readLine();
                    if(temp.equals("-----")){
                        break;
                    }
                    str += temp+"\n";
                }
                outboxData.add(new Sent(null, data[0],data[1],str,"outbox"));
            }
            reader.close();

        }catch(IOException e){
            System.out.println("Reading cabin backup failed!" + e);
        }
        return outboxData;
    }

    public static ObservableList<MailInterface> readMailInterfaceBackup() {

        ObservableList<MailInterface> mainInterfaceData = FXCollections.observableArrayList();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("backup/MailInterfaceBackup.txt"));
            System.out.println(reader.readLine());
            reader.readLine();
            while (reader.ready()) {
                String[] data = reader.readLine().split(split);
                String str = "";
                int count = 0;
                while (true) {
                    String temp = reader.readLine();
                    if (temp.equals("-----")) {
                        break;
                    }
                    str += temp + "\n";
                }
            if(data[1].equals("Glemt")){

            mainInterfaceData.add(new Forgotten(null, data[0], str, data[1]));
            }else{
                mainInterfaceData.add(new Destroyed(null,data[0],str,data[1]));
            }
            }
            reader.close();


        } catch (IOException e) {
            System.out.println("Reading cabin backup failed!" + e);
        }
        return mainInterfaceData;

    }
}
