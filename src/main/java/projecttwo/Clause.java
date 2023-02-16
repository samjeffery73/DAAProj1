package projecttwo;
import java.util.*;

public class Clause {

    List<Variable> clause = new ArrayList<Variable>();

    boolean truth = false;

    public Clause(List<Variable> list) {

        this.clause = new ArrayList<Variable>(list);

        System.out.println(clause);



        }





    public void test(){



    }

    public String toString() {

        return clause.toString();

    }






}
