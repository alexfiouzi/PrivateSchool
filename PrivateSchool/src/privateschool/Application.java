
package privateschool;

import java.text.ParseException;
import privateschool.models.School;


public class Application {

    public static void main(String[] args) throws ParseException, InterruptedException {
       School school=new School();
        school.menu();
    }
    
}
