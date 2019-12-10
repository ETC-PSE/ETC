/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etc;

import javax.swing.JOptionPane;

/**
 *
 * @author sures
 */
public class Information {
    private String stID;
    private String[] IDdata = {  "18022208","18022209",
                                    "18022201","18022202",
                                    "18022204","18022205", 
                                    "18022206","18022207",
                                    "18022203","18022211",  
                                    "19001","19002",
                                    "19003","19004",
                                    "19005","19006", 
                                    "19007","19008",
                                    "19009","19010" };
  
     
    private String LoginName;
    private String flag;

    
    public Information(String id){
        this.stID = id;
        for (String IDdata1 : IDdata) {
            if (stID.equals(IDdata1)) {
                flag = "X";
            }
        }
    }
    
    public String isIDfound(){
        return flag;
    }


    public String getUserName(){
        if("X".equals(flag))
            LoginName = getName();
        return LoginName;
    }       
            


    public String getName( ){
            String name = null;
            switch (stID){
                case "18022208":
                    name = "Suresh";
                    break;
                case "18022201":
                    name = "Siva";
                    break;
                case "18022202":
                    name = "Michael";
                    break;
                case "18022203":
                    name = "Filip";
                    break;
                case "18022204":
                    name = "Theo";
                    break;
                case "18022205":
                    name = "Jane";
                    break;
                case "18022206":
                    name = "Catherine";
                    break;
                case "18022207":
                    name = "Katty";
                    break;
                case "18022209": 
                    name = "Julia";
                    break;
                case "180222011":
                    name = "Robert"; 
                    break;
                case "19001":
                    name = "John Wick"; 
                    break;
                case "19002":
                    name= "Victor";
                    break;
                case "19003":
                    name = "Mike";
                    break;
                case "19004":
                    name = "Penny";
                    break;
                case "19005":
                    name = "Chan";
                    break;
                default:
            }
        return name;
    }          
            
        
}
