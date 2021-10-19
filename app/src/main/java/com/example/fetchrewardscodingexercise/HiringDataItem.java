package com.example.fetchrewardscodingexercise;

import java.lang.reflect.Array;
import java.util.Comparator;

public class HiringDataItem {
    /*
    * -Model for data
    * -Gets and returns "listID" and "name" in functions
    * -Sorts data in ascending/descending order using comparator
    * */
    private int listId;
    private String itemName;

    public HiringDataItem(){}
    public HiringDataItem(int listId, String itemName){
        this.listId=listId;
        this.itemName=itemName;
    }

    public static Comparator<HiringDataItem> listIdAscComparator = new Comparator<HiringDataItem>() {
        @Override
        public int compare(HiringDataItem o1, HiringDataItem o2) {
            return o1.getListID() - o2.getListID(); //sort in ascending order by "listId"
        }
    };

    public static Comparator<HiringDataItem> listIdDescComparator = new Comparator<HiringDataItem>() {
        @Override
        public int compare(HiringDataItem o1, HiringDataItem o2) {
            return o2.getListID() - o1.getListID(); //sort in descending order by "listId"
        }
    };

    public static Comparator<HiringDataItem> itemNameAZComparator = new Comparator<HiringDataItem>() {
        @Override
        public int compare(HiringDataItem o1, HiringDataItem o2) {
            return o1.getItemName().compareTo(o2.getItemName()); //sort in ascending order by "name"
        }
    };

    public static Comparator<HiringDataItem> itemNameZAComparator = new Comparator<HiringDataItem>() {
        @Override
        public int compare(HiringDataItem o1, HiringDataItem o2) {
            return o2.getItemName().compareTo(o1.getItemName()); //sort in descending order by "name"
        }
    };

    public int getListID() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
