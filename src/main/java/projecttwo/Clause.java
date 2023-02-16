/**
 * @author SJ
 *
 *
 * @date 16 Feb, 2023
 *
 *
 * A clause is defined as a list of variables. Within a clause, all of the variables are logically OR'd together. All
 * clauses start off at a base of 'false' until test variables are assigned.
 *
 *
 *
 *
 *
 */


package projecttwo;
import java.util.*;

public class Clause {

    List<Variable> clause = new ArrayList<Variable>();

    boolean truth = false;

    public Clause(List<Variable> list) {

        this.clause = new ArrayList<Variable>(list);


        }





    public void test(){



    }

    public String toString() {

        return clause.toString();

    }






}
