
package privateschool.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import privateschool.Utilities.DisplayOfObjects;


public class Course {
    private int courseId;
    private String title;
    private final ArrayList<String> listOfStreams = new ArrayList<>();
    private int stream;
    private String streamName;
    private String typeName;
    private final ArrayList<String> listOfTypes = new ArrayList<>();
    private int type;
    private String startDate;
    private String endDate;
    private ArrayList<Assignment> assigments = new ArrayList<>();
    private static final String MYSQL_JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
    public static final String DB_URL="jdbc:mysql://localhost/privateschool?serverTimezone=UTC";
    private static final String USERNAME="";
    private static final String PASSWORD="";
    DisplayOfObjects displayOfObjects=new DisplayOfObjects();

    public Course() {

        listOfStreams.add("JAVA");
        listOfStreams.add("C#");
        listOfStreams.add("FRONT END");

        listOfTypes.add("Fulltime");
        listOfTypes.add("parttime");
    }
    
    public Course(String title, int stream, int type, String startDate, String endDate) {
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        listOfStreams.add("JAVA");
        listOfStreams.add("C#");
        listOfStreams.add("FRONT END");
        listOfTypes.add("Full time");
        listOfTypes.add("part time");
    }

    public Course(int courseId, String title, String streamName, String typeName, String startDate, String endDate) {
        this.courseId = courseId;
        this.title = title;
        this.streamName = streamName;
        this.typeName = typeName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void askCourseDetails(Scanner sc) throws ParseException {
        Connection connection=null; 
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        boolean flag = true;
        boolean flag1 = true;
        boolean flag3 = false;
        System.out.print("Please type the name of the course: ");
        title = sc.next();
        while (flag1 == true) {
            System.out.print("Please type the stream of the course : ");
            System.out.println("");
            showStreams();
            int streamChoice = sc.nextInt();
            if (streamChoice == 1 || streamChoice == 2 || streamChoice == 3) {
                setStream(streamChoice);
                flag1 = false;
            } else {
                System.out.println("Your choise is outofbounds");
            }
        }
        while (flag == true) {
            System.out.print("Please type the type of the course : ");
            System.out.println("");
            showTypes();
            int typechoise = sc.nextInt();
            if (typechoise == 1 || typechoise == 2) {
                setType(typechoise);
                flag = false;
            } else {
                System.out.println("Your choise is outofbounds");
            }
        }
        while (flag3 == false) {
            System.out.print("Please type the start date of the course with format yyyy-MM-dd : ");
            String stDate = sc.next();
            flag3 = validateJavaDate(stDate);
            if (flag3 == true) {
             startDate = stDate;
            }
        }
        flag3 = false;
        while (flag3 == false) {
            System.out.println("please type the end date of the course with format yyyy-MM-dd ");
            String enDate = sc.next();
            flag3 = validateJavaDate(enDate);
            if (flag3 == true) {
                endDate = enDate;
            }
        }
          try{
     Class.forName(MYSQL_JDBC_DRIVER);
               
     System.out.println("Connecting to database...");
     
     connection=DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
     System.out.println("Connection created successfully");
         
     String ifExists="SELECT TITLE,STREAM,TYPE FROM COURSE WHERE TITLE= ? AND STREAM= ? AND TYPE= ?";
     preparedStatement = connection.prepareStatement(ifExists);
     preparedStatement.setString(1,title);
     preparedStatement.setString(2,getStream());
     preparedStatement.setString(3,getType());
     
     resultSet = preparedStatement.executeQuery();
    if (!resultSet.next()){
    
   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
   java.util.Date dateStr = formatter.parse(startDate);
   java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
   
   java.util.Date dateSt = formatter.parse(endDate);
   java.sql.Date dateD = new java.sql.Date(dateSt.getTime());
    String query="INSERT INTO COURSE(TITLE,STREAM,TYPE,START_DATE,END_DATE) VALUES(?,?,?,?,?)";
    preparedStatement = connection.prepareStatement(query);
    preparedStatement.setString(1,title);
    preparedStatement.setString(2,getStream());
    preparedStatement.setString(3,getType());
    preparedStatement.setDate(4,dateDB);
    preparedStatement.setDate(5,dateD);
    
    int result= preparedStatement.executeUpdate();
    if (result>0){
        System.out.println("new course inserted");}
    }
    else{
        System.out.println("Course is already in the database");
    }
    }catch(ClassNotFoundException |SQLException e){
    e.printStackTrace();    
    }finally{
              displayOfObjects.closing(connection, preparedStatement, resultSet);

          }
}

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    
    public ArrayList<Assignment> getAssigments() {
        return assigments;
    }

    public void setCourseassigments(Assignment assignment) {
        this.assigments.add(assignment);
    }

    public void showStreams() {
        for (int i = 0; i < listOfStreams.size(); i++) {
            System.out.println((i + 1) + ". " + listOfStreams.get(i));
        }
    }

    public void showTypes() {
        for (int i = 0; i < listOfTypes.size(); i++) {
            System.out.println((i + 1) + ". " + listOfTypes.get(i));
        }
    }

    public ArrayList<String> getListOfStreams() {
        return listOfStreams;
    }

    public ArrayList<String> getListOfTypes() {
        return listOfTypes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStream() {
        return listOfStreams.get(stream);
    }

    public void setStream(int stream) {
        this.stream = stream - 1;
    }

    public String getType() {
        return listOfTypes.get(type);
    }

    public void setType(int type) {
        this.type = type - 1;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    @Override
    public String toString() {
        return "Course{"+ "Id="+ courseId + ", title=" + title + ", stream=" + streamName + ", type=" + typeName + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }

    public static boolean validateJavaDate(String strDate) {
        if (strDate.trim().equals("")) {
            return true;
        } else {
            SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
            sdfrmt.setLenient(false);
            try {
                Date javaDate = sdfrmt.parse(strDate);
                System.out.println(strDate + " is valid date format");
            } catch (ParseException e) {
                System.out.println(strDate + " is Invalid Date format.The correct format is yyyy-MM-dd ");
                return false;
            }
            return true;
        }
    }
}
