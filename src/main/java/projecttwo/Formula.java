/**
 * @author SJ
 *
 * @date 13 Feb, 2023
 *
 * The Clause class takes a list of Variables as a parameter.
 *
 * From this list, it creates a list of lists that store the variables from the input and throws away zeroes.
 *
 * The finished output of creating a clause is a series of varying size lists of variables.
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
    int val;

    boolean truth = false;

    public Formula(List<Variable> list) {

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

      Iterator<Clause> it = clauseList.iterator();














    }




}
