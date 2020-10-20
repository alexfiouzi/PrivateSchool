
package privateschool.Utilities;
import privateschool.models.Assignment;
import privateschool.models.Trainer;
import privateschool.models.Student;
import privateschool.models.Course;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class CreationObjects {

    public  void createCourse(Scanner sc,ArrayList<Course> courses) throws ParseException {
        boolean flag = true;
        while (flag) {
            Course course = new Course();
            System.out.println("Please insert the data of the course ");
            course.askCourseDetails(sc);
            courses.add(course);            
            flag = askForMore(sc,"more courses");
        }
    }
    
    public void createTrainer(Scanner sc,ArrayList<Trainer> trainers,ArrayList<Course> courses) {
        boolean flag = true;
            while (flag) {
                Trainer trainer = new Trainer();
                System.out.println("Please insert the data of the trainer ");
                trainer.askTrainersDetails(sc);
                trainers.add(trainer);
                flag = askForMore(sc,"more trainers");
            }
        
    }

    public void createStudent(Scanner sc,ArrayList<Student> students,ArrayList<Course> courses,ArrayList<Assignment> assignments) throws ParseException {

            boolean flag = true;
            while (flag) {
                Student student = new Student();
                System.out.println("Please insert the data of the student ");
                student.askStudentDetails(sc);
                students.add(student);
                flag = askForMore(sc,"more students");
            }
        
    }
    
    public void createAssignment(Scanner sc, ArrayList<Assignment> assignments,ArrayList<Course> courses) throws ParseException {
            boolean flag = true;
            while (flag) {
                Assignment assignment = new Assignment();
                System.out.println("Please insert the data of the assignment");
                assignment.assignmentDetails(sc);
                assignments.add(assignment);
                flag = askForMore(sc,"more assignments");
            }
        
    }

    public  boolean askForMore(Scanner sc,String name) {
        System.out.println("Do you want to add "+name+" (Y/ANY KEY )?");
        String answer = sc.next();
        boolean flag = false;
        if (answer.equalsIgnoreCase("y")) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }
}
