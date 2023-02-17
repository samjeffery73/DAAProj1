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


    // creating a new list to keep track of the T/F values of the n amount of variables in the formula.
    ArrayList<Variable> vList = new ArrayList<Variable>();


    List<Variable> noZeroList = new ArrayList<Variable>();

    Variable var;

    Variable clauseVar;

    Clause clause;

    int cursor;

    // counter for variables
    int counter = 0;

    int clauseCounter = 0;


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

                noZeroList.add(val);
                list.remove(0);

            } else {

                List<Variable> temp = new ArrayList<Variable>(noZeroList);
                Clause c = new Clause(temp);
                clauseList.add(c);
                noZeroList.clear();
                list.remove(cursor);

            }


        }


        Clause c = new Clause(noZeroList);

        List<Variable> emptyList = new ArrayList<Variable>();


        // Empty clause, still not sure if i'll be using this
        clauseList.add(c);

        System.out.println("List of clauses: " + clauseList);
        System.out.println("Number of clauses: " + clauses);
        System.out.println("Number of variables: " + variables);


        // a loop adding all variables (TRUE) to vList
        for (int i = 1; i < variables + 1; i++) {

            Variable v = new Variable(i);

            vList.add(v);

        }

        // End of vList
        Variable x = new Variable(0);

        System.out.println(vList.toString());

        vList.add(x);


        solve();
    }


    public void solve() {


        System.out.println("Solving formula...");

        // an iterator to keep track of all of our clauses in the formula.
        Iterator<Clause> cListIt = clauseList.iterator();
        Clause clause = cListIt.next();

        // an iterator to keep track of the variables within each clause
        Iterator<Variable> clauseVarIt = clause.list.iterator();
        Variable clauseVar = clauseVarIt.next();


        // an iterator for our total list of variables, in which the program will use to brute force
        Iterator<Variable> vListIt = vList.iterator();
        Variable var = vListIt.next();


        while (clauseCounter < clauses) {

            // end of vList, go to the next entry
            if (var.value == 0) {

                vListIt = vList.listIterator();

            }

            // no more comparisons for this variable, go to next variable in the clause and start comparing.
            if (counter == variables) {

                counter = -1;

                // no more comparisons in the clause, we know that the clause is FALSE
                // the entire formula is also FALSE. Program will still continue to gather data.
                if (clause.list.indexOf(clauseVar) + 1 == clause.list.size()) {


                    clause.truth = false;

                    truth = false;

                    System.out.println(clause.toString() + " " + clause.truth);

                    clause = cListIt.next();

                    clauseVarIt = clause.list.listIterator();

                    clauseVar = clauseVarIt.next();

                    vListIt = vList.iterator();

                    counter = -1;
                    clauseCounter++;


                } else
                    clauseVar = clauseVarIt.next();


            }





            // we found one, wipe all of our entries. This clause is true.
            if (clauseVar.equals(var)) {

                clauseCounter++;

                clause.truth = true;

                System.out.println(clause.toString() + " " + clause.truth);

                if (!(clauseCounter == clauses)) {

                    clause = cListIt.next();

                    clauseVarIt = clause.list.listIterator();

                    clauseVar = clauseVarIt.next();

                    vListIt = vList.iterator();

                    counter = -1;

                }


            }


            // increment the counter, so we know when all variables in vList have been compared to one variable in the
            // clause. Go to the next variable

            counter++;
            var = vListIt.next();


        }

    }















    private void reSolve() {


        // new instance of vList, we will be editing it.
        Iterator<Variable> vListIt = vList.iterator();
        var = vListIt.next();

        // wiping the clauseList again
        Iterator<Clause> cListIt = clauseList.listIterator();
        clause = cListIt.next();

        // wiping the stored clause variable list again
        Iterator<Variable> clauseVarIt = clause.list.listIterator();
        clauseVar = clauseVarIt.next();

        // iterate through the list of clauses, find one that is false
        for (int i = 0; i < clauseList.size(); i++) {

            if (clause.truth == false) {

                // find the value that is false

                while (clauseVarIt.hasNext()) {

                    if (clauseVar.value == var.value - (2 * var.value)) {


                        vList.set(vList.indexOf(var), clauseVar);


                        break;

                    }

                    if (var.value == 0) {

                        vListIt = vList.listIterator();
                        clauseVar = clauseVarIt.next();


                    }

                    var = vListIt.next();


                    }

                break;


                }


            }

            clause = cListIt.next();



        }




    }



