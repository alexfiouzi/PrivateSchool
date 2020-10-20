
package privateschool.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import privateschool.Utilities.DisplayOfObjects;


public class Student {
    private int studentID;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private float tuitionFees;   
    private ArrayList<Course> courses = new ArrayList();
    private static final String MYSQL_JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
    public static final String DB_URL="jdbc:mysql://localhost/privateschool?serverTimezone=UTC";
    private static final String USERNAME="";
    private static final String PASSWORD="";
    DisplayOfObjects displayOfObjects=new DisplayOfObjects();
    
    public Student(String firstName, String lastName, String dateOfBirth, float tuitionFees) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tuitionFees = tuitionFees;
    }
    public Student(String firstName, String lastName, String dateOfBirth, float tuitionFees,ArrayList<Course> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tuitionFees = tuitionFees;
        this.courses=courses;
    }

    public Student(int studentID, String firstName, String lastName, String dateOfBirth, float tuitionFees) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tuitionFees = tuitionFees;
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Student() {
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setStcourses(Course course) {
        this.courses.add(course);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public float getTuitionFees() {
        return tuitionFees;
    }

    public void setTuitionFees(float tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    @Override
    public String toString() {
        return "Student{"+"ID="+ studentID + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", tuitionFees=" + tuitionFees + '}';
    }

    public void askStudentDetails(Scanner sc) throws ParseException  {
        Connection connection=null;  
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        boolean flag = false;
        boolean flag2=false;
        boolean flag3=false;
        boolean flag4=false;
        while (flag==false){
        System.out.print("Please type student's name: ");
        firstName = sc.next();
        flag = isAlpha(firstName);
        if (flag == false) {
            System.out.println("Please give me a name without numbers");                          }   
        }  
        while(flag2==false){
        System.out.print("Please type student's lastname: ");
        lastName = (sc.next());
        flag2=isAlpha(lastName);
        if(flag2==false){System.out.println("Please give me a lastname without numbers");}       
        }               
        while(flag4==false){
           System.out.print("Please type the date of birth of the student with format yyyy-MM-dd: ");
           String dateBirth = sc.next(); 
           flag4=Course.validateJavaDate(dateBirth);
           if(flag4==true){
           dateOfBirth=dateBirth;            
           }
        }
        while(flag3==false){
        System.out.println("please give the tuitionfees of the student");
        tuitionFees = sc.nextInt();
        flag3=true;
        if (tuitionFees<0){
            System.out.println("Please give the tuition fees again.They should be more than 0 euro");
            flag3=false;
        }        
    }
        try{
     Class.forName(MYSQL_JDBC_DRIVER);
               
     System.out.println("Connecting to database...");
     
     connection=DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
     System.out.println("Connection created successfully");
     
     String ifExists="SELECT FIRST_NAME,LAST_NAME FROM STUDENT WHERE FIRST_NAME= ? AND LAST_NAME=?";
     preparedStatement = connection.prepareStatement(ifExists);
     preparedStatement.setString(1,firstName);
     preparedStatement.setString(2,lastName);
     resultSet = preparedStatement.executeQuery();
    if (!resultSet.next()){
   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
   java.util.Date dateStr = formatter.parse(dateOfBirth);
   java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
     String query="INSERT INTO STUDENT(FIRST_NAME,LAST_NAME,DATEOFBIRTH,TUITIONFEES) VALUES(?,?,?,?)";
    preparedStatement = connection.prepareStatement(query);
    preparedStatement.setString(1,firstName);
    preparedStatement.setString(2,lastName);
    preparedStatement.setDate(3,dateDB);
    preparedStatement.setFloat(4,tuitionFees);
    int result= preparedStatement.executeUpdate();
    if (result>0){
        System.out.println("new student inserted");}
    }
    else{
        System.out.println("Student is already in the database");
    }       
    }catch(ClassNotFoundException |SQLException e){
    e.printStackTrace();    
    }finally{
            displayOfObjects.closing(connection, preparedStatement, resultSet);
        }
}    
    public boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }
}

