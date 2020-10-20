
package privateschool.models;
import privateschool.Utilities.CreationObjects;
import privateschool.Utilities.DisplayOfObjects;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class School {
    Scanner sc=new Scanner(System.in);
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Trainer> trainers = new ArrayList<>();
    ArrayList<Assignment> assignments = new ArrayList<>();
    ArrayList<Course> courses = new ArrayList<>();
    CreationObjects creationObjects=new CreationObjects();
    DisplayOfObjects displayOfObjects=new DisplayOfObjects();
    
    public void menu() throws ParseException, InterruptedException {         
            while (true) {
                System.out.println("1.Insert a course \n2.Insert a Trainer\n3.Insert a Student\n4.Insert an Assignment\n5.Add Student to a course\n6.Add Trainer to a course\n7.Add Assignment to a Student to a Course\nAny key to go to Search menu");
                String num = sc.next();
                switch (num) {
                    case "1":                        
                        creationObjects.createCourse(sc,courses);
                        break;
                    case "2":
                      creationObjects.createTrainer(sc,trainers,courses);
                        break;
                    case "3":                        
                         creationObjects.createStudent(sc,students,courses,assignments);
                        break;
                    case "4":
                         creationObjects.createAssignment(sc,assignments,courses);
                        break;
                    case "5":
                        displayOfObjects.addStudentPerCourse();  
                        break;
                    case "6":
                        displayOfObjects.addTrainerPerCourse();                    
                        break;
                    case "7":  
                       displayOfObjects.addAssignmentPerStudentPerCourse();    
                        break;
                    default:
                        Search();                        
                        break;                       
                }
            }        
    }

    public void Search() throws InterruptedException, ParseException {
        boolean flag=true;
        while (flag) {
            System.out.println("If you want to print \n all  courses press 1 \n all trainers press 2\n all students press 3 "
                    + "\n all assignments press 4 \n all students per course press 5 \n all trainers per course press 6 "
                    + "\n all assignments per course press 7 \n students with more one course press 8  "
                    + " \n all assignments per course per student press 9 \n to go back to search menu press any key");
            String numOfLists = sc.next();
            switch (numOfLists) {
                case "1":
                    displayOfObjects.showCourses();
                    break;
                case "2":
                    displayOfObjects.showTrainers();
                    break;
                case "3":
                    displayOfObjects.showStudents();
                    break;
                case "4":
                    displayOfObjects.showAssignments();
                    break;
                case "5":
                    displayOfObjects.selectStudentsPerCourse();
                    break;
                case "6":
                    displayOfObjects.selectTrainersPerCourse();
                    break;
                case "7":
                    displayOfObjects.selectAssignmentsPerCourse();
                    break;
                case "8":
                    displayOfObjects.selectStudentsWithMoreCourses();
                    Map<Student, Integer> studentsMap =displayOfObjects.selectStudentsWithMoreCourses();
                    studentsMap.forEach((student, count) ->
                        System.out.println(
                                 " first name : " + student.getFirstName()
                                + ", last name: "+ student.getLastName() + " is enrolled in " + count + " course(s).")
                    );
                    break;                    
                case "9":
                    displayOfObjects.selectAssignmentsPerCoursePerStudent();
                    break;
                 default:                                      
                    return;
           }
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
