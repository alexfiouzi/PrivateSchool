
package privateschool.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import privateschool.Utilities.DisplayOfObjects;


public class Trainer {
    private int trainerId;
    private String firstName;
    private String lastName;
    private final ArrayList<String> listOfSubjects=new ArrayList<>();
    private int subject;
    private String subjectName;
    private ArrayList<Course> courses = new ArrayList<>();
    private static final String MYSQL_JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
    public static final String DB_URL="jdbc:mysql://localhost/privateschool?serverTimezone=UTC";
    private static final String USERNAME="";
    private static final String PASSWORD="";
    DisplayOfObjects displayOfObjects=new DisplayOfObjects();

    public Trainer(String firstName, String lastName, String subjectName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subjectName = subjectName;
    }

    public Trainer(int trainerId, String firstName, String lastName, String subjectName) {
        this.trainerId = trainerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.subjectName = subjectName;
    }
    
    public Trainer() {        
        listOfSubjects.add("JAVA");
        listOfSubjects.add("C#");
        listOfSubjects.add("FRONT END");
    }
    
    public Trainer(String firstName, String lastName, int subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
        listOfSubjects.add("JAVA");
        listOfSubjects.add("C#");
        listOfSubjects.add("FRONT END");
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = getSubject();
    }

    public String getSubject() {
        return listOfSubjects.get(subject);
    }

    public void setSubject(int subject) {
        this.subject = subject - 1;
    }

    public ArrayList<String> getListOfSubjects() {
        return listOfSubjects;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setTrainerscourses(Course course) {
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

    public void showSubjects() {
        for (int i = 0; i < listOfSubjects.size(); i++) {
            System.out.println((i + 1) + ". " + listOfSubjects.get(i));
        }
    }

    public void askTrainersDetails(Scanner sc) {
        Connection connection=null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;         
        boolean flag = false;
        boolean flag2 = false;
        boolean flag3=true;
        while (flag == false) {
            System.out.print("Please type trainer's name: ");
            firstName = sc.next();
            flag = isAlpha(firstName);
            if (flag == false) {
                System.out.println("please give me a name without numbers");
            }
        }
            while(flag2==false){
            System.out.print("Please type trainer's lastname: ");
            lastName = sc.next();
            flag2=isAlpha(lastName);
            if (flag2 == false) {
                System.out.println("please give me a surname without numbers");
            }
            }
            while (flag3==true){
            System.out.print("Please type trainer's subject of teaching : ");
            System.out.println("");
            showSubjects();
            int subjectChoice = sc.nextInt();
            if(subjectChoice==1||subjectChoice==2||subjectChoice==3){
            setSubject(subjectChoice);
            flag3=false;
            }
            else{System.out.println("Your choise is outofbounds");            
    }
            }    
    try{
     Class.forName(MYSQL_JDBC_DRIVER);               
     System.out.println("Connecting to database...");    
     connection=DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
     System.out.println("Connection created successfully");
     
     String ifExists="SELECT FIRST_NAME,LAST_NAME FROM TRAINER WHERE FIRST_NAME= ? AND LAST_NAME=?";
     preparedStatement = connection.prepareStatement(ifExists);
     preparedStatement.setString(1,firstName);
     preparedStatement.setString(2,lastName);
     resultSet = preparedStatement.executeQuery();
    if (!resultSet.next()){    
     String query="INSERT INTO TRAINER(FIRST_NAME,LAST_NAME,SUBJECT) VALUES(?,?,?)";
    preparedStatement = connection.prepareStatement(query);
    preparedStatement = connection.prepareStatement(query);
    preparedStatement.setString(1,firstName);
    preparedStatement.setString(2,lastName);
    preparedStatement.setString(3,getSubject());
    int result= preparedStatement.executeUpdate();
    if (result>0){
        System.out.println("new trainer inserted");}
    }
    else{
        System.out.println("Trainer is already in the database");
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

    @Override
    public String toString() {
        return "Trainer{"+"Id="+ trainerId + ", firstName=" + firstName + ", lastName=" + lastName + ", subject=" + subjectName + '}';
    }
   
}
