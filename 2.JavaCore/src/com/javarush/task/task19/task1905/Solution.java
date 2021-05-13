package com.javarush.task.task19.task1905;

import java.util.HashMap;
import java.util.Map;

/* 
Закрепляем адаптер
*/

public class Solution {
    public static Map<String,String> countries = new HashMap<String,String>();

    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {

    }

    public static class DataAdapter implements RowItem {
        private Customer customer;
        private Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.customer = customer;
            this.contact = contact;
        }

        public String getCountryCode() {
            String cName = customer.getCountryName();
            String cCode = "";
            for (String key : countries.keySet()) {
                if (countries.get(key).equals(cName)) cCode = key;
            }
            return cCode;
        }

        public String getCompany() {
            return customer.getCompanyName();
        }

        public String getContactFirstName() {
            String[] name = contact.getName().split(" ");
            return name[1];
        }

        public String getContactLastName() {
            String[] name = contact.getName().split(" ");
            return name[0].replace(",", "");
        }

        public String getDialString() {
            String data = contact.getPhoneNumber();
            data = data.replace("(", "");
            data = data.replace(")", "");
            data = data.replace("-", "");
            return "callto://" + data;
        }
    }

    public static interface RowItem {
        String getCountryCode();        //For example: UA
        String getCompany();            //For example: JavaRush Ltd.
        String getContactFirstName();   //For example: Ivan
        String getContactLastName();    //For example: Ivanov
        String getDialString();         //For example: callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //For example: JavaRush Ltd.
        String getCountryName();        //For example: Ukraine
    }

    public static interface Contact {
        String getName();               //For example: Ivanov, Ivan
        String getPhoneNumber();        //For example: +38(050)123-45-67 or +3(805)0123-4567 or +380(50)123-4567 or ...
    }
}