/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etc;

//import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

/**
 *  Maintains student and tutors information and modifications
 * @author Suresh Katamsetty
 */
public class etcDataManager
{
	private File dataFile = new File(System.getProperty("user.dir")+"\\src\\etcDataset.dat");        
	List<LessonInfo> infoStorage = new ArrayList<LessonInfo>();
        static List<LessonInfo> infoStoragetable = new ArrayList<LessonInfo>();
	static etcDataManager inst = null;
        static boolean success;
        private String status;
	public static etcDataManager createManagerInst()
	{
                
                if(inst == null || success == true){
                    inst = new etcDataManager();
                }
                    
		return inst;
	}
	
	etcDataManager()
	{
            System.out.println(System.getProperty("user.dir"));
            readFromFile();
	}
        
        public boolean changeLessonInfo(String id,  String name, String subject, 
                                        String date, String hours, String minutes, 
                                        String tutor, String stdstatus, String tutstatus, 
                                        String tutid, String book, String rowindex)
        {
            if (subject.equals("")||tutor.equals("")) return false;
                try
                {  
                LessonInfo li = new LessonInfo(id,name,subject,date,hours,
                                              minutes,tutor,stdstatus,tutstatus,
                                              tutid,book,rowindex);
                int index = Integer.parseInt(rowindex);	
                infoStorage.remove(index);
                infoStorage.add(index, li);
                infoStoragetable = infoStorage;//global array list for only jar file run
                storeToFile(li);
                showConfirmationMessage(id,subject,date,hours,minutes,stdstatus,tutstatus);
                return true;
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Unable to change your booking");
                    return false;
                }    
        }

        public boolean addLessonInfo(String id,     String name,    String subject, 
                                     String date,   String hours,   String minutes, 
                                     String tutor, String stdstatus, String tutstatus, 
                                     String tutid, String book, String index, String command)
                
        {
            int size = 0;
            if (id.equals("")||subject.equals("")||tutor.equals("")||date.equals("")||
                hours.equals("")||minutes.equals("")) return false;
            try
            {  
                LessonInfo li = new LessonInfo(id,name,subject,date,hours,minutes,tutor,stdstatus,tutstatus,tutid,book,index);
                size = infoStorage.size();
                li.index = String.valueOf(size);
                if(infoStorage.isEmpty()){
                    li.index = "0";
                }
                for(int i=0;i<infoStorage.size();i++){
                    LessonInfo lf = infoStorage.get(i);
                    if((lf.date.equals(li.date)) && (li.hours.equals(lf.hours)) &&
                       (lf.minutes.equals(li.minutes)) && (lf.subject.equals(li.subject))&&
                       (lf.tutor.equals(li.tutor))){
                        JOptionPane.showMessageDialog(null, "Booking is already available");
                        return false;
                    }
                }
                infoStorage.add(li);
                infoStoragetable = infoStorage; //global array list for only jar file 
                storeToFile(li);
                if("ORDERBOOK".equals(command)){
                    JOptionPane.showMessageDialog(null, "Your book has been ordered");
                }else{
                    JOptionPane.showMessageDialog(null, subject+" Class has been booked for "+date+" at "+hours+":"+minutes);
                }
                
                return true;
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, "Unable to book your class");
                return false;
            }
            
            
        }
             
	public ArrayList<LessonInfo> searchData(String ID)
	{       
                 
 		List<LessonInfo> searchresult  = search(ID);
		if(searchresult == null)
			return null;
		else
			return  (ArrayList<LessonInfo>) searchresult;
                        
	}
        private ArrayList<LessonInfo> search(String IdNumber)
	{       
                List<LessonInfo> searchresult = new ArrayList<LessonInfo>();
		Iterator<LessonInfo> itr = infoStorage.iterator();
		while(itr.hasNext())
		{
			LessonInfo curInfo=itr.next();
			if(IdNumber.compareTo(curInfo.ID) == 0){
                          searchresult.add(curInfo);  
                        } else if(IdNumber.compareTo(curInfo.tutorid) == 0){
                            searchresult.add(curInfo);                            
                        }
                        

                        
		}
	return  (ArrayList<LessonInfo>) searchresult;
	}
        
	public boolean storeToFile(LessonInfo li)
	{            
                
                try
		{

			OutputStream file = new FileOutputStream(dataFile) {};		
                    try (ObjectOutputStream out = new ObjectOutputStream(file)) 
                    {
                        Iterator<LessonInfo> itr=infoStorage.iterator();
                        while(itr.hasNext())
                            out.writeObject(itr.next());
                        success = true;
                        etcDataManager inst = null;
                        out.close();
                        
                    }
                
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
                
                return success;
	}
        

        public String toString()
        {
            String retval = "";
            Iterator<LessonInfo> itr=infoStorage.iterator();
            while(itr.hasNext())
                    {
                            LessonInfo curInfo=itr.next();
                            retval+=curInfo.toString() + "\r\n";
                    }
            return retval;
        }
	
	public void readFromFile()
	{
                infoStorage = infoStoragetable; //only for jar file as parsisteant objects 
//                if(dataFile.exists() == false)  // cannot be modified at run time in jar file
//                        return;
//
//                try
//		{
////                            InputStream file = etcDataManager.class.getClassLoader().getResourceAsStream("etcDataset.dat");
//                            FileInputStream file = new FileInputStream(dataFile) {};
//                            ObjectInputStream in = new ObjectInputStream(file); 
//                            {
//                                while(true)
//                                {
//                                    LessonInfo info = (LessonInfo)in.readObject();
//                                    if(info == null)
//                                        break;
//                                    infoStorage.add(info);
//
//                                }
//                    }
//		}
//		catch(IOException e)
//		{
//			return;
//		}
//		catch(ClassNotFoundException e)
//		{
//			return;
//		}
                
                
        }
        
    public List<LessonInfo> readFromDatabase(){
        return infoStorage;
    }
    

    private void showConfirmationMessage(String id, String subject, String date,
                                         String hours, String minutes, String stdstatus,
                                         String tutstatus) {
                String status;
                String idrecg = id.substring(0,2);


                if("18".equals(idrecg)){
                    status = stdstatus;
                }else{
                    status = tutstatus;
                }
                
                switch(status){
                    case "Booked":
                        JOptionPane.showMessageDialog(null, subject+" class has been changed to "+date+" at "+hours+":"+minutes);
                        break;
                    case "Attend":
                        JOptionPane.showMessageDialog(null, "You are attending the class "+subject+" on "+date);
                        break;
                    case "Cancel":
                        JOptionPane.showMessageDialog(null, "class has been cancelled "+subject+" on "+date);
                        break;
                }
    }

        
}


