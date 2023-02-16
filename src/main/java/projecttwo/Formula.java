/**
 * Formula.java
 *
 * @author SJ
 *
 * @date 13 Feb, 2023
 *
 * The Formula class takes a FULL list of Variables as a parameter (with zeros from the file included).
 * Then, it stores the variables that ARENT zero and generates clauses.
 *
 * From this list, it generates Clauses. From those clauses, it forms a
 * list of clauses.
 *
 *
 * This file will likely be renamed.
 *
 *
 *
 *
 */

package projecttwo;

import java.util.*;



public class Formula {

    ArrayList<Clause> clauseList = new ArrayList<Clause>();
    List<Variable> newList = new ArrayList<Variable>();

    int cursor;


    int variables;

    int clauses;

    boolean truth = false;

    public Formula(List<Variable> list, int variables, int clauses) {

        this.clauses = clauses;

        this.variables = variables;


        Iterator<Variable> it = list.iterator();

        while (it.hasNext()) {

            Variable val = list.get(0);

            if (!(val.value == 0)) {

                newList.add(val);
                list.remove(0);

            }


            else {

                List<Variable> temp = new ArrayList<Variable>(newList);
                Clause c = new Clause(temp);
                clauseList.add(c);
                newList.clear();
                list.remove(cursor);

            }


        }




        Clause c = new Clause(newList);

        clauseList.add(c);

        System.out.println("List of clauses: " + clauseList);





        solve();
    }


    public void solve() {


        System.out.println("Solving formula...");

        // creating a new list to keep track of the T/F values of the n amount of variables in the formula.
        List<Variable> vList = new ArrayList<Variable>();


        Iterator<Clause> it = clauseList.iterator();
        Clause c = it.next();
        System.out.println("Number of clauses: " + clauses);
        System.out.println("Number of clauses: " + variables);

// a loop adding
        for (int i = 1; i == variables; i++) {

            Variable v = new Variable(i);


            vList.add(v);
        }

        System.out.println(vList);



        for (int i = 0; i < clauses; i++) {








      }
















    }




}
