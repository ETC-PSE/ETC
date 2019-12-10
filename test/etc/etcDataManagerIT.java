/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etc;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test Cases 
 * @author sures
 */
public class etcDataManagerIT {
    
    public etcDataManagerIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of readFromFile method, of class etcDataManager.
     */

    @Test
    public void testIsStudentLoginIDfound() {
        System.out.println("isLoginIDfound");
        Information instance = null;
        String id = "18022208";
        instance = new Information(id);
        String expResult = "X";
        String result = instance.isIDfound();
        assertEquals(expResult, result);
        System.out.println("Student User ID has been found");
    }
    
    @Test
    public void testInvalidStudentLoginID() {
        System.out.println("isLoginIDfound");
        Information instance = null;
        String id = "189990099";
        instance = new Information(id);
        String result = instance.isIDfound();
        assertTrue(result==null);
        System.out.println("Invalid Student Id");
    }
    
    @Test
    public void testIsTutorstLoginIDfound() {
        System.out.println("isLoginIDfound");
        Information instance = null;
        String id = "19001";
        instance = new Information(id);
        String expResult = "X";
        String result = instance.isIDfound();
        assertEquals(expResult, result);
        System.out.println("Tutors ID has been found");
    }
    
    @Test
    public void testGetUserName() {
        System.out.println("getUserName");
        Information instance = null;
        String id = "18022208";
        instance = new Information(id);
        String expResult = "Suresh";
        String result = instance.getUserName();
        assertEquals(expResult, result);
        System.out.println("User details has been found with name "+result);
    }
    
    @Test
    public void testAddTutorsID() {
        System.out.println("addTutorsID");
        TutorsData td = null;
        td = new TutorsData("19009","Cheng");
        ArrayList<TutorsData> result = TutorsData.addTutorsID(td);
        assertTrue(result.size()>=1);
        System.out.println("Tutors ID has been added successfully");
    }
    
    /**
     * Test of addLessonInfo method, of class etcDataManager.
     */

    @Test
    public void testAddLessonInfo() {
        System.out.println("addLessonInfo");
        etcDataManager instance = new etcDataManager();
        Information info = null;
        String id = "18022201";
        info = new Information(id);
        String name = info.getUserName();
        String subject = "Maths";
        String date = "20-12-2019";
        String hours = "20";
        String minutes = "30";
        String tutor = "John Wick";
        String stdstatus = "Booked";
        String tutstatus = "";
        String tutid = "19001";
        String book = "none";
        String rowindex = "0";
        String command = "BOOKLIST";
        boolean expResult = true;
        boolean result = instance.addLessonInfo(id, name, subject, date, hours, minutes, tutor, stdstatus, tutstatus, tutid, book, rowindex, command);
        assertEquals(expResult, result);
        System.out.println("Lesson has been booked successfully");
    }
    
    @Test
    public void testChangeLessonInfo() {
        etcDataManager instance = new etcDataManager();
        System.out.println("changeLessonInfo");
        Information info = null;
        String id = "18022208";
        info = new Information(id);
        String name = info.getUserName();
        String subject = "Maths";
        String date = "20-12-2019";
        String hours = "20";
        String minutes = "30";
        String tutor = "John Wick";
        String stdstatus = "Booked";
        String tutstatus = "";
        String tutid = "19001";
        String book = "none";
        String rowindex = "0";
        String command = "BOOKLIST";
        instance.addLessonInfo(id, name, subject, date, hours, minutes, tutor, stdstatus, tutstatus, tutid, book, rowindex, command);
        stdstatus = "Absent";
        boolean expResult = true;
        boolean result = instance.changeLessonInfo(id, name, subject, date, hours, minutes, tutor, stdstatus, tutstatus, tutid, book, rowindex);
        assertEquals(expResult, result);
        System.out.println("Lesson has been chnaged successfully");
    }



    /**
     * Test of searchData method, of class etcDataManager.
     */
    @Test
    public void testSearchData() {
        System.out.println("searchData");
        etcDataManager instance = new etcDataManager();
        System.out.println("addLessonInfo");
        Information info = null;
        String id = "18022201";
        info = new Information(id);
        String name = info.getUserName();
        String subject = "Maths";
        String date = "20-12-2019";
        String hours = "20";
        String minutes = "30";
        String tutor = "John Wick";
        String stdstatus = "Booked";
        String tutstatus = "";
        String tutid = "19001";
        String book = "none";
        String rowindex = "0";
        String command = "BOOKLIST";
        LessonInfo li = new LessonInfo(id, name, subject, date, hours, minutes, tutor, stdstatus, tutstatus, tutid, book, rowindex);
        instance.addLessonInfo(id, name, subject, date, hours, minutes, tutor, stdstatus, tutstatus, tutid, book, rowindex, command);
        LessonInfo expResult = li;
        ArrayList<LessonInfo> result = instance.searchData(id);
        assertEquals(expResult.ID, result.get(0).ID);
        System.out.println("classes have been found for student ID "+id);
    }

    /**
     * Test of storeToFile method, of class etcDataManager.
     */
    @Test
    public void testStoreToFile() {
        System.out.println("storeToFile");
        LessonInfo li = new LessonInfo("18022208", "Suresh", "Maths", "16-12-2019", "16", "00", "Penny", "Booked",null, null, null,null);
        etcDataManager instance = new etcDataManager();
        boolean result = instance.storeToFile(li);
        assertTrue(result);
        System.out.println("file has been saved successfully ");
    }
    
    @Test
    public void testReadFromDatabase() {
        System.out.println("readFromFile");
        etcDataManager instance = new etcDataManager();
        List<LessonInfo> infostorage = instance.readFromDatabase();
        assertTrue(infostorage.size()>=1);
        System.out.println("fetched data from database ");
    }
    
    
}
