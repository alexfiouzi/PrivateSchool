
package privateschool.Utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import privateschool.models.Assignment;
import privateschool.models.Trainer;
import privateschool.models.Student;
import privateschool.models.Course;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class DisplayOfObjects {
    private static final String MYSQL_JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
    public static final String DB_URL="jdbc:mysql://localhost/privateschool?serverTimezone=UTC";
    private static final String USERNAME="";
    private static final String PASSWORD="";
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Trainer> trainers = new ArrayList<>();
    ArrayList<Assignment> assignments = new ArrayList<>();
    ArrayList<Course> courses = new ArrayList<>();
    Scanner sc=new Scanner(System.in);
    
     public ArrayList<Course> selectCourses()  
  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM COURSE";
            preparedStatement = connection.prepareStatement(query);           
           resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int courseId = resultSet.getInt("ID");
                String title = resultSet.getString("title");
                String stream = resultSet.getString("stream");
                String type=resultSet.getString("type");
                String startdate=resultSet.getString("start_date");
                String enddate=resultSet.getString("end_date");
              Course course = new Course(courseId,title, stream, type,startdate,enddate);
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            closing(connection,  preparedStatement, resultSet);

        }
        return courses;
  }

    public  ArrayList<Trainer> selectTrainers()  
  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM TRAINER";
            preparedStatement = connection.prepareStatement(query);
                       
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int trainer_Id = resultSet.getInt("ID");
                String first_name = resultSet.getString("FIRST_NAME");
                String last_name = resultSet.getString("LAST_NAME");
                String subject=resultSet.getString("subject");
                
                Trainer trainer = new Trainer(trainer_Id,first_name, last_name, subject);
                trainers.add(trainer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            closing(connection,  preparedStatement, resultSet);

        }
        return trainers;
  }
    
    public ArrayList<Student> selectStudents()  
  {   
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM STUDENT";
            preparedStatement = connection.prepareStatement(query);                       
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                                
                int studentId = resultSet.getInt("ID");
                String first_name = resultSet.getString("FIRST_NAME");
                String last_name = resultSet.getString("LAST_NAME");
                String dateOfBirth=resultSet.getString("dateofbirth");
                float tuitionFees=resultSet.getFloat("tuitionfees");
                
                Student student = new Student(studentId,first_name, last_name, dateOfBirth,tuitionFees);              
                    students.add(student);                          
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            closing(connection,  preparedStatement, resultSet);
        }
        return students;
  }

        public ArrayList<Assignment> selectAssignments()  
  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM ASSIGNMENT";
            preparedStatement = connection.prepareStatement(query);
                       
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int assignment_Id = resultSet.getInt("ID");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                float oralmark=resultSet.getFloat("oralmark");
                float totalmark=resultSet.getFloat("totalmark");
                
                Assignment assignment = new Assignment(assignment_Id,title, description, oralmark,totalmark);
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            closing(connection,  preparedStatement, resultSet);
        }
        return assignments;  
  }
    
    public void selectStudentsPerCourse()  
  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "Select s.first_name,s.last_name,c.title,c.stream,c.type from student s inner join student_course st " +
                           "on s.id=st.student_id  inner join course c  on c.id=st.course_id order by st.course_id;";
            preparedStatement = connection.prepareStatement(query);                       
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                
                String first_name = resultSet.getString("FIRST_NAME");
                String last_name = resultSet.getString("LAST_NAME");
                String title=resultSet.getString("title");
                String stream=resultSet.getString("stream");
                String type=resultSet.getString("type");
               System.out.println( " First name: " + first_name + ", Last name: "+last_name+", Title: " + title+", Stream: "+stream+", Type: "+type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            closing(connection,  preparedStatement, resultSet);
        }
  }
    
     public void selectTrainersPerCourse()  
  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "select t.first_name,t.last_name,c.title,c.stream,c.type from trainer_course tc inner join trainer t on t.id=tc.trainer_id"
                    + " inner join course c on c.id=tc.course_id order by tc.course_id;";
            preparedStatement = connection.prepareStatement(query);                      
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                
                String first_name = resultSet.getString("FIRST_NAME");
                String last_name = resultSet.getString("LAST_NAME");
                String title=resultSet.getString("title");
                String stream=resultSet.getString("stream");
                String type=resultSet.getString("type");
               System.out.println( " First name: " + first_name + ", Last name: "+last_name+", Title: " + title+", Stream: "+stream+", Type: "+type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            closing(connection,  preparedStatement, resultSet);
        }
  }
    
     public void selectAssignmentsPerCourse()  
  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = " select c.title,c.stream,c.type,a.title from course_assignment ca inner join course c on c.id=ca.course_id "
                    + " inner join assignment a on a.id=ca.assignment_id order by c.id;";
            preparedStatement = connection.prepareStatement(query);                       
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                
                String course_title = resultSet.getString("c.Title");
                String stream = resultSet.getString("Stream");
                String type=resultSet.getString("Type");
                String assignment_title = resultSet.getString("a.Title");
               System.out.println( "Course title: " + course_title + ", Stream : "+stream+", Type: " + type+", Assignment title: "+assignment_title);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            closing(connection,  preparedStatement, resultSet);
        }
  }

    public Map<Student, Integer> selectStudentsWithMoreCourses()
  {
      Map<Student, Integer> studentsMap = new HashMap<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = " select s.first_name,s.last_name,count(sc.course_id)as Numberofcourses from student s,student_course sc"
                    + " where s.id=sc.student_id group by sc.student_id having count(sc.course_id)>1;";
            preparedStatement = connection.prepareStatement(query);
                      
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                
                String first_name = resultSet.getString("FIRST_NAME");
                String last_name = resultSet.getString("LAST_NAME");
               int count=resultSet.getInt("Numberofcourses");                 
               Student student = new Student(first_name, last_name);
                studentsMap.put(student, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            closing(connection,  preparedStatement, resultSet); 
        }
        return studentsMap;
  }

     public void selectAssignmentsPerCoursePerStudent()  
  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = " select s.first_name,s.last_name,c.title,c.stream,c.type,a.title,sa.oralmark,sa.totalmark from student s inner join student_assignment sa\n" +
            "on s.id=sa.student_id inner join assignment a on a.id=sa.assignment_id inner join course_assignment ca on a.id=ca.assignment_id inner join course c on ca.course_id=c.id order by s.id;";
            preparedStatement = connection.prepareStatement(query);
                       
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String first_name = resultSet.getString("S.FIRST_NAME");
                String last_name = resultSet.getString("S.LAST_NAME");
                String course_title = resultSet.getString("c.Title");
                String stream = resultSet.getString("Stream");
                String type=resultSet.getString("Type");
                String assignment_title = resultSet.getString("a.Title");
                float oralmark=resultSet.getFloat("sa.oralmark");
                float totalmark=resultSet.getFloat("sa.totalmark");
                
               System.out.println( "Student first name: "+first_name+", Student last name: "+last_name+ ", Course title: " + course_title + ", Stream : "+stream+", Type: " + type+", Assignment title: "+assignment_title+ ", Oral mark: " + oralmark + ", Total mark : "+totalmark);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            closing(connection,  preparedStatement, resultSet);
        }
  }
     public int insertCourseToStudent(int studentId, int courseId) {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        try {
            Class.forName(MYSQL_JDBC_DRIVER);

    connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    String ifExists="SELECT STUDENT_ID,COURSE_ID FROM STUDENT_COURSE WHERE STUDENT_ID= ? AND COURSE_ID=?";
     preparedStatement = connection.prepareStatement(ifExists);
     preparedStatement.setInt(1,studentId);
     preparedStatement.setInt(2,courseId);
     resultSet = preparedStatement.executeQuery();
    if (!resultSet.next()){     
     String query = " INSERT INTO STUDENT_COURSE(STUDENT_ID, COURSE_ID) VALUES (?,?) ";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);

            rowsAffected = preparedStatement.executeUpdate();}

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
         closing(connection,  preparedStatement, resultSet);
        }
        return rowsAffected;
    }
    
     public int insertCourseToTrainer(int trainerId, int coursesId) {
        int rowsAffected = 0;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        try {
            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String ifExists="SELECT TRAINER_ID,COURSE_ID FROM TRAINER_COURSE WHERE TRAINER_ID= ? AND COURSE_ID=?";
            preparedStatement = connection.prepareStatement(ifExists);
            preparedStatement.setInt(1,trainerId);
            preparedStatement.setInt(2,coursesId);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()){
           
            String query = " INSERT INTO TRAINER_COURSE(TRAINER_ID, COURSE_ID) VALUES (?,?) ";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, trainerId);
            preparedStatement.setInt(2, coursesId);

            rowsAffected = preparedStatement.executeUpdate();}
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closing(connection,  preparedStatement, resultSet);
        }
        return rowsAffected;
    }
    
 public int insertAssignmentToStudent(int assignmentId, int studentsId) {
     Scanner s=new Scanner(System.in);
        int rowsAffected = 0;
        float totalmark=-1;
        float oralmark=-1;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        boolean isInputInvalid=true;
        try {
            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            
     String ifExists="SELECT STUDENT_ID,ASSIGNMENT_ID FROM STUDENT_ASSIGNMENT WHERE  STUDENT_ID=? AND ASSIGNMENT_ID= ? ";
     preparedStatement = connection.prepareStatement(ifExists);
     preparedStatement.setInt(1,studentsId);
     preparedStatement.setInt(2,assignmentId);
     resultSet = preparedStatement.executeQuery();
     
    if (!resultSet.next()){     
     String query = " INSERT INTO STUDENT_ASSIGNMENT(STUDENT_ID, ASSIGNMENT_ID,ORALMARK,TOTALMARK) VALUES (?,?,?,?) ";
                        
        while(isInputInvalid){
        System.out.println("Please type the oral mark of the assignment");
        oralmark=s.nextInt();
        isInputInvalid=false;
        if (oralmark<0||oralmark>30){
            System.out.println("Please give me the oral mark again.Oral mark should be between 0 and 30");
            isInputInvalid=true;
        }       
        }
        isInputInvalid=true;
        while(isInputInvalid){
        System.out.println("Please type the total mark of the assignment");
        totalmark=s.nextInt();
        isInputInvalid=false;
        if (totalmark<30||totalmark>100){
            System.out.println("Please give me the total mark again.Total mark should be between 30 and 100");
            isInputInvalid=true;
        }
        }
        preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentsId);
            preparedStatement.setInt(2, assignmentId);
            preparedStatement.setFloat(3, oralmark);
            preparedStatement.setFloat(4, totalmark);
            rowsAffected = preparedStatement.executeUpdate();}
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closing( connection,  preparedStatement, resultSet);
        }
        return rowsAffected;
    }

 public int insertAssignmentToCourse(int assignmentId, int courId)  {
     Scanner s=new Scanner(System.in);
     boolean flag=false;
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        try {
            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
     String ifExists="SELECT COURSE_ID,ASSIGNMENT_ID FROM COURSE_ASSIGNMENT WHERE COURSE_ID= ? AND ASSIGNMENT_ID=?";
     preparedStatement = connection.prepareStatement(ifExists);
     preparedStatement.setInt(1,courId);
     preparedStatement.setInt(2,assignmentId);
     resultSet = preparedStatement.executeQuery();
     
    if (!resultSet.next()){
     
     String query = " INSERT INTO COURSE_ASSIGNMENT(COURSE_ID, ASSIGNMENT_ID,SUBDATETIME) VALUES (?,?,?) ";

           System.out.println("Please give me the submission date for this assignment for this course in format yyyy-MM-dd hh:mm:ss");
          
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date=s.nextLine();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courId);
            preparedStatement.setInt(2, assignmentId);
               preparedStatement.setObject(3,date); 
               System.out.println(date);

            rowsAffected = preparedStatement.executeUpdate();}
        
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closing( connection,  preparedStatement, resultSet);
        }
        return rowsAffected;
    }
 
   public void addStudentPerCourse(){ 
       Scanner s=new Scanner(System.in);
        showStudents();
     students=selectStudents();
     boolean studentExists = false;
     int studentId = -1;

     do {
        System.out.println("Select the id of the student  you wish to enter: ");
        studentId = s.nextInt();
        
         for (Student st : students) {
             if (st.getStudentID() == studentId) {
                 studentExists = true;
                  break;
                            }
                        }if (!studentExists) System.out.println("This student does not exist. Select another id.");
                    } while (!studentExists);
                    boolean courseExists = false;
                    int courseId = -1;
                   courses=selectCourses();
                     showCourses();
                    do {
                        System.out.println("Select the id of the course  you wish to enter: ");
                        courseId = s.nextInt();
                        for (Course course : courses) {
                            if (course.getCourseId() == courseId) {
                                courseExists = true;
                                break;
                            }
                        }if (!courseExists) System.out.println("This course does not exist. Select another id.");
                    } while (!courseExists);
                    
                    int rowsAffected = insertCourseToStudent(studentId, courseId);

                    if (rowsAffected == 0) {
                        System.out.println("The student is already registered in this course.");
                    } else {
                        System.out.println("Student with id  " + studentId + " was inserted successfully to course with id "+courseId);
                    }};
               
   public void addTrainerPerCourse(){
       Scanner s=new Scanner(System.in);
         trainers=selectTrainers();
         showTrainers();
     boolean trainerExists = false;
       int trainerId = -1;

                    do {
                        System.out.println("Select the id of the trainer  you wish to enter: ");
                        trainerId = s.nextInt();
                        for (Trainer trainer : trainers) {
                            if (trainer.getTrainerId() == trainerId) {
                                trainerExists = true;
                                break;
                            }
                        }if (!trainerExists) System.out.println("This trainer does not exist. Select another id.");
                    } while (!trainerExists);
                    boolean coursesExists = false;
                    int coursesId = -1;
                   courses=selectCourses();
                    showCourses();
                    do {
                        System.out.println("Select the id of the course  you wish to enter: ");
                        coursesId = s.nextInt();

                        for (Course course : courses) {
                            if (course.getCourseId() == coursesId) {
                                coursesExists = true;
                                break;
                            }
                        }if (!coursesExists) System.out.println("This course does not exist. Select another id.");
                    } while (!coursesExists);
                    
                    int rowsAffected = insertCourseToTrainer(trainerId, coursesId);

                    if (rowsAffected == 0) {
                        System.out.println("The trainer is already registered in this course.");
                    } else {
                        System.out.println("Trainer with id  " + trainerId + " was inserted successfully to course with id "+coursesId);
                    }          
     }
   
          public void addAssignmentPerStudentPerCourse()  {
              Scanner s=new Scanner(System.in);
              assignments=selectAssignments();
              showAssignments();
              boolean assignmentExists = false;
              int assignmentId = -1;
                    do {
                        System.out.println("Select the id of the assignment  you wish to enter: ");

                        assignmentId = s.nextInt();

                        for (Assignment assignment : assignments) {
                            if (assignment.getAssignmentId() == assignmentId) {
                                assignmentExists = true;
                                break;
                            }
                        }if (!assignmentExists) System.out.println("This assignment does not exist. Select another id.");
                    } while (!assignmentExists);
                    boolean studentsExists = false;
                    int studentsId = -1;
                   students=selectStudents();
                    showStudents();
                    do {
                        System.out.println("Select the id of the student  you wish to enter: ");

                        studentsId = s.nextInt();

                        for (Student student : students) {
                            if (student.getStudentID() == studentsId) {
                                studentsExists = true;
                                break;
                            }
                        }if (!studentsExists) System.out.println("This student does not exist. Select another id.");
                    } while (!studentsExists);
                    
                    int rowsAffected = insertAssignmentToStudent(assignmentId, studentsId);

                    if (rowsAffected == 0) {
                        System.out.println("This assignment has already been assigned to this student.");
                    } else {
                        System.out.println("Assignment with id  " + assignmentId + " was inserted successfully to student with id "+studentsId);
                    }
                    boolean CourseExists = false;
                    int courId = -1;
                   courses=selectCourses();
                    showCourses();
                    do {
                        System.out.println("Select the id of the course  you wish to enter: ");

                        courId = s.nextInt();

                        for (Course course : courses) {
                            if (course.getCourseId()== courId) {
                                CourseExists = true;
                                break;
                            }
                        }if (!CourseExists) System.out.println("This course does not exist. Select another id.");
                    } while (!CourseExists);
                    
                    rowsAffected = insertAssignmentToCourse(assignmentId, courId);

                    if (rowsAffected == 0) {
                        System.out.println("The assignment has already been assigned to this course.");
                    } else {
                        System.out.println("Assignment with id  " + assignmentId + " was inserted successfully to course with id "+courId);
                    }                 
                }
                 
    public void closing(Connection connection, PreparedStatement preparedStatement,ResultSet resultSet){
    if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }
        
     public void showStudents()  
  {        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM STUDENT";
            preparedStatement = connection.prepareStatement(query);                       
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                
                int studentId = resultSet.getInt("ID");
                String first_name = resultSet.getString("FIRST_NAME");
                String last_name = resultSet.getString("LAST_NAME");
                String dateOfBirth=resultSet.getString("dateofbirth");
                float tuitionFees=resultSet.getFloat("tuitionfees");
                System.out.println("Student Id:"+ studentId+", firstname:"+ first_name+", lastname:"+ last_name+", dateofbirth:" +dateOfBirth+", tuitionfees:"+ tuitionFees);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            closing(connection,  preparedStatement, resultSet);
        }                
  }
     
      public void showCourses()  
  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM COURSE";
            preparedStatement = connection.prepareStatement(query);           
           resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int courseId = resultSet.getInt("ID");
                String title = resultSet.getString("title");
                String stream = resultSet.getString("stream");
                String type=resultSet.getString("type");
                String startdate=resultSet.getString("start_date");
                String enddate=resultSet.getString("end_date");
                
          System.out.println("Course Id:"+ courseId+", title:"+ title+", stream:"+ stream+", type:" +type+", startdate:" +startdate+", enddate:" +enddate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            closing(connection,  preparedStatement, resultSet);
        }       
  }
      
        public  void showTrainers()  
  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM TRAINER";
            preparedStatement = connection.prepareStatement(query);
                       
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int trainer_Id = resultSet.getInt("ID");
                String first_name = resultSet.getString("FIRST_NAME");
                String last_name = resultSet.getString("LAST_NAME");
                String subject=resultSet.getString("subject");
                
            System.out.println("Trainer Id:"+ trainer_Id+", firstname:"+ first_name+", lastname:"+ last_name+", subject:" +subject);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            closing(connection,  preparedStatement, resultSet);
        }
  }
            public void showAssignments()  
  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM ASSIGNMENT";
            preparedStatement = connection.prepareStatement(query);
                       
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int assignment_Id = resultSet.getInt("ID");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                float oralmark=resultSet.getFloat("oralmark");
                float totalmark=resultSet.getFloat("totalmark");

         System.out.println("Assignment Id:"+ assignment_Id+", title:"+ title+", description:"+ description+", oralmark:" +oralmark+", totalmark:" +totalmark);
           
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            closing(connection,  preparedStatement, resultSet);
        } 
  }
}
