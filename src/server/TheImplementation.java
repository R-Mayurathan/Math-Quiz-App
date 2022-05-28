package server;


import Database.ConnectionProvider;
import Quiz_Game.Interface.Interface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

/*
 * This programme is to implement the all methods of interface 
 * This is server side implementation
 * I got the way to fetch complete data from the database's table through RMI from stackoverflow
 * https://stackoverflow.com/questions/70091315/how-to-fetch-complete-data-from-the-databases-table-through-rmi-using-the-array
 * @author  Rasu Mayurathan
 * @version 1.0
 * @since   2021-11-22
 */
public class TheImplementation extends UnicastRemoteObject implements Interface{
    
    public TheImplementation()throws RemoteException{
        super();
    }
    
    private static final long serialVersionUID = -3763231206310559L;
    
 
    
    boolean r, r1, r2, r3, r4;

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
    @Override
    public boolean InsertQuestion(String id, String name, String opt1, String opt2, String opt3, String opt4, String answer) throws RemoteException {
    try{
            Connection con=ConnectionProvider.getConnection();
            PreparedStatement ps=con.prepareStatement("insert into question values(?,?,?,?,?,?,?)");
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, opt1);
            ps.setString(4, opt2);
            ps.setString(5, opt3);
            ps.setString(6, opt4);
            ps.setString(7, answer);
            ps.executeUpdate();
            
            r=true;
        }
        catch(SQLException e)
                {
                   r = false;
                }
        
        
        return r;
    }

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
    @Override
    public boolean UpdateQuestion(String id, String name, String opt1, String opt2, String opt3, String opt4, String answer) throws RemoteException {
    try {
            Connection connection=ConnectionProvider.getConnection();
            PreparedStatement ps=connection.prepareStatement("update question set name=?,opt1=?,opt2=?,opt3=?,opt4=?,answer=? where id=?");

            ps.setString(1,name);
            ps.setString(2,opt1);
            ps.setString(3,opt2);
            ps.setString(4,opt3);
            ps.setString(5,opt4);
            ps.setString(6,answer);
            ps.setString(7,id);
            ps.executeUpdate();
            r1=true;
             

        } catch (SQLException e) {
            r1=false;
        }
        return r1;
    }

    /***
     * This method is used to delete the question.
     * @param id
     * @return
     * @throws RemoteException 
     */
    @Override
    public boolean DeleteQuestion(String id) throws RemoteException {
      
    try {
            Connection connection=ConnectionProvider.getConnection();
            PreparedStatement ps=connection.prepareStatement("delete from question where id=?");
            ps.setString(1, id);
            ps.executeUpdate();
            r2=true;
            
        }   catch (SQLException e) {
             r2=false;
        }
        return r2;
    }

    /***
     * This method is used to check the login username and password
     * @param username
     * @param password
     * @return
     * @throws RemoteException 
     */
    @Override
    public boolean LogIn(String username, String password) throws RemoteException {
         PreparedStatement pst;
    try {
            Connection con=ConnectionProvider.getConnection();
            pst = con.prepareStatement("SELECT * from admin where username = ? and password = ?");
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet result = pst.executeQuery();
            if(result.next()){
              r3=true;
              
            }
            else{
               r3=false;
            }
            
        } 
       
         catch (SQLException ex) {
            
            r3=false;
        }
         
            return r3;
    }
    
    /***
     * This method is used to get the question id from the database
     * @return
     * @throws RemoteException 
     */
     @Override
     public CachedRowSet getquestionId() throws RemoteException {
        try (Connection con = ConnectionProvider.getConnection();
             Statement st = con.createStatement(); 
             ResultSet rs = st.executeQuery("select count(id) from question"))  {
             RowSetFactory factory = RowSetProvider.newFactory();
             CachedRowSet questionidDetails = factory.createCachedRowSet();
             questionidDetails.populate(rs);
             return questionidDetails;
        }
        catch (SQLException e) {
            throw new RemoteException("Method 'getquestionId()' failed.", e);
        }
    }
    
    /***
     * This method is used to get the questions from the database's table using question id
     * @param id
     * @return
     * @throws RemoteException 
     */
    @Override
    public CachedRowSet getquestions(String id) throws RemoteException {
        try (Connection con = ConnectionProvider.getConnection();
             Statement st = con.createStatement(); 
             ResultSet rs = st.executeQuery("select *from question where id='"+id+"'"))  {
             RowSetFactory factory = RowSetProvider.newFactory();
             CachedRowSet questiondetails = factory.createCachedRowSet();
             questiondetails.populate(rs);
             return questiondetails;
        }
        catch (SQLException e) {
            throw new RemoteException("Method 'getquestions()' failed.", e);
        }
    }
    
    /***
     * This method is used to get the all questions from database
     * @return
     * @throws RemoteException 
     */
    @Override
    public CachedRowSet getallquestions() throws RemoteException {
        try (Connection con = ConnectionProvider.getConnection();
             Statement st = con.createStatement(); 
             ResultSet rs = st.executeQuery("select *from question"))  {
             RowSetFactory factory = RowSetProvider.newFactory();
             CachedRowSet allquestions = factory.createCachedRowSet();
             allquestions.populate(rs);
             return allquestions;
        }
        catch (SQLException e) {
            throw new RemoteException("Method 'getallquestions()' failed.", e);
        }
    }
    
    /***
     * This method is used to get the all student's results.
     * @return
     * @throws RemoteException 
     */
    @Override
    public CachedRowSet allStudentResult() throws RemoteException {
        try (Connection con = ConnectionProvider.getConnection();
             Statement st = con.createStatement(); 
             ResultSet rs = st.executeQuery("select *from student"))  {
             RowSetFactory factory = RowSetProvider.newFactory();
             CachedRowSet allStudentResults = factory.createCachedRowSet();
             allStudentResults.populate(rs);
             return allStudentResults;
        }
        catch (SQLException e) {
            throw new RemoteException("Method 'allStudentResult()' failed.", e);
        }
    }
    
    /***
     * This method is used to get the student's rank according th marks
     * @return
     * @throws RemoteException 
     */
    @Override
     public CachedRowSet marksdetails() throws RemoteException {
        try (Connection con = ConnectionProvider.getConnection();
             Statement st = con.createStatement(); 
             ResultSet rs = st.executeQuery("select rollNo, name, marks, RANK() OVER (ORDER BY marks DESC) AS rank from student"))  {
             RowSetFactory factory = RowSetProvider.newFactory();
             CachedRowSet marksdetails = factory.createCachedRowSet();
             marksdetails.populate(rs);
             return marksdetails;
        }
        catch (SQLException e) {
            throw new RemoteException("Method 'marksdetails()' failed.", e);
        }
    }
     
     /***
      * This method is use to get the counts of good marks
      * @return
      * @throws RemoteException 
      */
     @Override
      public CachedRowSet countgoodmarks() throws RemoteException {
        try (Connection con = ConnectionProvider.getConnection();
             Statement st = con.createStatement(); 
             ResultSet rs = st.executeQuery("select COUNT(rollNo) from student  WHERE marks > 7"))  {
             RowSetFactory factory = RowSetProvider.newFactory();
             CachedRowSet countgoodmarks = factory.createCachedRowSet();
             countgoodmarks.populate(rs);
             return countgoodmarks;
        }
        catch (SQLException e) {
            throw new RemoteException("Method 'countgoodmarks()' failed.", e);
        }
    }
      
      /****
       * This method is use to get the counts of average marks
       * @return
       * @throws RemoteException 
       */
      @Override
      public CachedRowSet countaveragemarks() throws RemoteException {
        try (Connection con = ConnectionProvider.getConnection();
             Statement st = con.createStatement(); 
             ResultSet rs = st.executeQuery("select COUNT(rollNo) from student WHERE marks <= 7 AND marks > 3"))  {
             RowSetFactory factory = RowSetProvider.newFactory();
             CachedRowSet countaveragemarks = factory.createCachedRowSet();
             countaveragemarks.populate(rs);
             return countaveragemarks;
        }
        catch (SQLException e) {
            throw new RemoteException("Method 'countaveragemarks()' failed.", e);
        }
    }
      
      /***
       * This method is use to get the counts of low marks
       * @return
       * @throws RemoteException 
       */
       @Override
       public CachedRowSet countlowmarks() throws RemoteException {
        try (Connection con = ConnectionProvider.getConnection();
             Statement st = con.createStatement(); 
             ResultSet rs = st.executeQuery("select COUNT(rollNo) from student WHERE marks <= 3"))  {
             RowSetFactory factory = RowSetProvider.newFactory();
             CachedRowSet countlowmarks = factory.createCachedRowSet();
             countlowmarks.populate(rs);
             return countlowmarks;
        }
        catch (SQLException e) {
            throw new RemoteException("Method 'countlowmarks()' failed.", e);
        }
    }
     
       
     /***
      * This method to collect the data of student who is going to face quiz game
      * @param rollNo
      * @param name
      * @param gender
      * @param age
      * @param marks
      * @return 
      */
     @Override
     public boolean studentlogin(String rollNo, String name, String gender, String age, String marks)  {
         PreparedStatement pst;
    try {
            Connection con=ConnectionProvider.getConnection();
            pst = con.prepareStatement("insert into student values(?,?,?,?,?)");
            pst.setString(1, rollNo);
            pst.setString(2, name);
            pst.setString(3, gender);
            pst.setString(4, age);
            pst.setString(5, marks);
            pst.executeUpdate();
            r3=true;
            
        } 
       
         catch (SQLException ex) {
            
            r3=false;
        }
         
            return r3;
    }
     
     
     /***
      * This method is to update marks after finished th quiz
      * @param rollNo
      * @param marks
      * @return 
      */
      @Override
      public boolean Updateresults(String rollNo, String marks){
         PreparedStatement pst;
    try {
            Connection con=ConnectionProvider.getConnection();
            pst = con.prepareStatement("update student set marks='"+marks+"' where rollNo='"+rollNo+"'");
            pst.executeUpdate();
            r3=true;
        } 
       
         catch (SQLException ex) {
            r3=false;
        }
         
            return r3;
    }
      
      /***
       * This method is to get the student name
       * @param rollNo
       * @return
       * @throws RemoteException 
       */
      @Override
      public CachedRowSet getStudentName(String rollNo) throws RemoteException {
        try (Connection con = ConnectionProvider.getConnection();
             Statement st = con.createStatement(); 
             ResultSet rs = st.executeQuery("select *from student where rollNo='"+rollNo+"'"))  {
             RowSetFactory factory = RowSetProvider.newFactory();
             CachedRowSet StudentName = factory.createCachedRowSet();
             StudentName.populate(rs);
             return StudentName;
        }
        catch (SQLException e) {
            throw new RemoteException("Method 'getStudentName()' failed.", e);
        }
    }
   
    
   
     
     /***
      * This method is to get all student's result according to the marks
      * @param marks
      * @return
      * @throws RemoteException 
      */
     @Override
     public CachedRowSet allStudentResultbyMarks(String marks) throws RemoteException {
        try (Connection con = ConnectionProvider.getConnection();
             Statement st = con.createStatement(); 
             ResultSet rs = st.executeQuery("select *from student where marks >="+marks+""))  {
             RowSetFactory factory = RowSetProvider.newFactory();
             CachedRowSet allStudentResultbyMarks = factory.createCachedRowSet();
             allStudentResultbyMarks.populate(rs);
             return allStudentResultbyMarks;
        }
        catch (SQLException e) {
            throw new RemoteException("Method 'allStudentResultbyMarks(String marks)' failed.", e);
        }
    }

   
    
 }

