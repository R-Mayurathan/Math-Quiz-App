package Quiz_Game.Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.sql.rowset.CachedRowSet;

/*
 * This is the remote interface
 * This class is same for client and server
 * This class has abstract methods
 * @author  Rasu Mayurathan
 * @version 1.0
 * @since   2021-11-22
 */
public interface Interface extends Remote{
     
     
     /***
      * This method is used to add question in database
      * @param id
      * @param name
      * @param opt1
      * @param opt2
      * @param opt3
      * @param opt4
      * @param answer
      * @return
      * @throws RemoteException 
      */
     public boolean InsertQuestion(String id, String name, String opt1, String opt2, String opt3, String opt4, String answer) throws RemoteException;
     
     /***
      * This method is used to update existing questions
      * @param id
      * @param name
      * @param opt1
      * @param opt2
      * @param opt3
      * @param opt4
      * @param answer
      * @return
      * @throws RemoteException 
      */
     public boolean UpdateQuestion(String id, String name, String opt1, String opt2, String opt3, String opt4, String answer) throws RemoteException;
     
     /***
      * This method is used to delete the question.
      * @param id
      * @return
      * @throws RemoteException 
      */
     public boolean DeleteQuestion(String id) throws RemoteException ;
     
     /***
      * This method is used to check the login username and password
      * @param username
      * @param password
      * @return
      * @throws RemoteException 
      */
     public boolean LogIn (String username, String password) throws RemoteException ;
      
     /***
      * This method is used to get the question id from the database
      * @return
      * @throws RemoteException 
      */
     public CachedRowSet getquestionId() throws RemoteException;
     
     /***
      *  This method is used to get the questions from the database's table using question id
      * @param id
      * @return
      * @throws RemoteException 
      */
     public CachedRowSet getquestions(String id) throws RemoteException; 
     
     /***
      * This method is used to get the all questions from database
      * @return
      * @throws RemoteException 
      */
     public CachedRowSet getallquestions() throws RemoteException;
     
     /***
      * This method is used to get the all student's results.
      * @return
      * @throws RemoteException 
      */
     public CachedRowSet allStudentResult() throws RemoteException;
     
     /***
      * This method is used to get the student's rank according th marks
      * @return
      * @throws RemoteException 
      */
     public CachedRowSet marksdetails() throws RemoteException;
     
     /***
      * This method is use to get the counts of good marks
      * @return
      * @throws RemoteException 
      */
     public CachedRowSet countgoodmarks() throws RemoteException;
     
     /***
      * This method is use to get the counts of average marks
      * @return
      * @throws RemoteException 
      */
     public CachedRowSet countaveragemarks() throws RemoteException;
     
     /***
      * This method is use to get the counts of low marks
      * @return
      * @throws RemoteException 
      */
     public CachedRowSet countlowmarks() throws RemoteException;
     
     /***
      * This method to collect the data of student who is going to face quiz game
      * @param rollNo
      * @param name
      * @param gender
      * @param age
      * @param marks
      * @return
      * @throws RemoteException 
      */
     public boolean studentlogin(String rollNo, String name, String gender, String age, String marks) throws RemoteException;
     
     /***
      * This method is to update marks after finished th quiz
      * @param rollNo
      * @param marks
      * @return
      * @throws RemoteException 
      */
     public boolean Updateresults(String rollNo, String marks) throws RemoteException;
     
     /***
      * This method is to get the student name
      * @param rollNo
      * @return
      * @throws RemoteException 
      */
     public CachedRowSet getStudentName(String rollNo) throws RemoteException; 
     
     /***
      * This method is to get all student's result according to the marks
      * @param marks
      * @return
      * @throws RemoteException 
      */
     public CachedRowSet allStudentResultbyMarks(String marks) throws RemoteException;
     

}
