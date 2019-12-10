/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etc;

import java.util.ArrayList;
import java.util.List;

/**
 * Tutors related data
 * @author suresh
 */
public class TutorsData {
    
    String tutorname;
    String tutorID;
    static List<TutorsData> tutordata = new ArrayList<TutorsData>();	

    
    public TutorsData(String id, String name){
        this.tutorID = id;
        this.tutorname = name;
    }
    
    TutorsData(){
        TutorsData td1 = new TutorsData("19001","John Wick");addTutorsID(td1);
        TutorsData td2 = new TutorsData("19002","Victor");  addTutorsID(td2);
        TutorsData td3 = new TutorsData("19003","Mike");    addTutorsID(td3);
        TutorsData td4 = new TutorsData("19004","Penny");   addTutorsID(td4);
        TutorsData td5 = new TutorsData("19005","Chan");    addTutorsID(td5);
    }
    
    public static ArrayList<TutorsData> addTutorsID( TutorsData td){
        tutordata.add(td);
        return (ArrayList<TutorsData>) tutordata;
               
    }

    
}