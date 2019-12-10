/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etc;

import java.io.Serializable;

/**
 *
 * @author comqsjb
 */
public class LessonInfo implements Serializable
{
	String subject;
	String tutor;
        String hours;
        String minutes;
        String ID;
        String date;
        String name;
        String studentstatus;
        String tutorstatus;
        String tutorid;
        String books;
        String index = "0";

	public LessonInfo( String id, String name, String sub, String date, 
                           String hr, String min, String tutor, String stdstatus, String tutstatus, String tutid,
                           String books, String index)
	{
		this.ID = id;
                this.name = name;
                this.subject = sub;
                this.date = date;
                this.hours = hr;
                this.minutes = min;
		this.tutor = tutor;
                this.studentstatus = stdstatus;
                this.tutorstatus = tutstatus;
                this.tutorid = tutid;
                this.books = books;
                this.index = index;
        
                
	}
	
        
	public boolean equals(Object obj)
	{
		LessonInfo cmp = (LessonInfo)obj;
		if(ID.compareTo(cmp.ID) == 0)
			return true;
		else
			return false;
	}
}
