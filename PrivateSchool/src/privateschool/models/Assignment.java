
package privateschool.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import privateschool.Utilities.DisplayOfObjects;

public class Assignment {
    private int assignmentId;
    private String title;
    private String description;
    private String subDateTime;
    private float oralMark;
    private float totalMark;
    private static final String MYSQL_JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
    public static final String DB_URL="jdbc:mysql://localhost/privateschool?serverTimezone=UTC";
    private static final String USERNAME="";
    private static final String PASSWORD="";
    DisplayOfObjects displayOfObjects=new DisplayOfObjects();
    
    public void  assignmentDetails(Scanner sc) throws ParseException{
        Connection connection=null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;  
        boolean isInputInvalid=true;
        System.out.print("Please type the title of the assignment : ");
        title=sc.next();
        System.out.print("Please type the description of the assignment : ");
        description=(sc.next());
        while(isInputInvalid){
        System.out.println("Please type the oral mark of the assignment");
        oralMark=sc.nextInt();
        isInputInvalid=false;
        if (oralMark<0||oralMark>30){
            System.out.println("Please give me the oral mark again.Oral mark should be between 0 and 30");
            isInputInvalid=true;
        }       
        }
        isInputInvalid=true;
        while(isInputInvalid){
        System.out.println("Please type the total mark of the assignment");
        totalMark=sc.nextInt();
        isInputInvalid=false;
        if (totalMark<0||totalMark>100){
            System.out.println("Please give me the total mark again.Total mark should be between 0 and 100");
            isInputInvalid=true;
        }
        }
           try{
     Class.forName(MYSQL_JDBC_DRIVER);
               
     System.out.println("Connecting to database...");
     
     connection=DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
     System.out.println("Connection created successfully"); 
     
     String ifExists="SELECT title FROM ASSIGNMENT WHERE TITLE= ?";
     preparedStatement = connection.prepareStatement(ifExists);
     preparedStatement.setString(1,title);    
     resultSet = preparedStatement.executeQuery();
    if (!resultSet.next()){     
     String query="INSERT INTO ASSIGNMENT(TITLE,DESCRIPTION,ORALMARK,TOTALMARK) VALUES(?,?,?,?)";
    preparedStatement = connection.prepareStatement(query);
    preparedStatement.setString(1,title);
    preparedStatement.setString(2,description);
    preparedStatement.setFloat(3,oralMark);
    preparedStatement.setFloat(4,totalMark);
    int result= preparedStatement.executeUpdate();
    if (result>0){
        System.out.println("new assignment inserted");}
    }
    else{
        System.out.println("Assignment is already in the database");
    }     
    }catch(ClassNotFoundException |SQLException e){
    e.printStackTrace();    
    }finally{
               displayOfObjects.closing(connection, preparedStatement, resultSet);

  }
}
    public Assignment() {
    }

    public Assignment(int assignmentId, String title, String description, float oralMark, float totalMark) {
        this.assignmentId = assignmentId;
        this.title = title;
        this.description = description;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubDateTime() {
        return subDateTime;
    }

    public void setSubDateTime(String subDateTime) {
        this.subDateTime = subDateTime;
    }

    public float getOralMark() {
        return oralMark;
    }

    public void setOralMark(int oralMark) {
        this.oralMark = oralMark;
    }

    public float getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }
    
    @Override
    public String toString() {
        return "Assignment{"+"ID= "+assignmentId + ", title=" + title + ", description=" + description + ", subDateTime=" + subDateTime + ", oralMark=" + oralMark + ", totalMark=" + totalMark + '}';
    }   
}
