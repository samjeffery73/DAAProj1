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

    Iterator<Clause> cListIt = clauseList.listIterator();


    // creating a new list to keep track of the T/F values of the n amount of variables in the formula.
    ArrayList<Variable> vList = new ArrayList<Variable>();

    ListIterator<Variable> vListIterator = vList.listIterator();

    // Creating a HashMap that will store variable assignments so that they will not be run more than once.
    ArrayList<ArrayList<Variable>> variableList = new ArrayList<ArrayList<Variable>>();


    List<Variable> noZeroList = new ArrayList<Variable>();

    Variable var;

    Variable tempVar;

    Variable clauseVar;

    Clause clause;
    int tempCounter;
    int cursor;

    // counter for variables
    int counter = 0;

    int clauseCounter = 0;

    int mapCounter = 0;


    long startTime;

    int variables;

    int clauses;

    int assignments;

    boolean truth = false;

    boolean finishedTesting = false;

    public Formula(List<Variable> list, int variables, int clauses) {


        startTime = System.currentTimeMillis();

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


        // a loop adding all variables (FALSE) to vList
        for (int i = 1; i < variables + 1; i++) {

            Variable v = new Variable(i - (i * 2));

            vList.add(v);

        }




        while (!finishedTesting){

            nextAssignment();


            if (truth)
                break;


        }
    }


    public void solve() {




        // an iterator to keep track of all of our clauses in the formula.
        cListIt = clauseList.listIterator();
        clause = cListIt.next();

        // an iterator to keep track of the variables within each clause
        Iterator<Variable> clauseVarIt = clause.list.listIterator();
        Variable clauseVar = clauseVarIt.next();


        // an iterator for our list of assignments, in which the program will use to brute force
        Iterator<Variable> vListIt = vList.listIterator();
        Variable var = vListIt.next();


        while (clauseCounter < clauses) {

            if (truth) {

                finishedTesting = true;
                break;
            }


            // end of vList, go to the next entry
            if (!vListIt.hasNext()) {

                vListIt = vList.listIterator();

            }

            // no more comparisons for this variable, go to next variable in the clause and start comparing.
            if (counter == variables) {

                counter = -1;

                // no more comparisons in the clause, we know that the clause is FALSE
                // the entire formula is also FALSE. Program will still continue to gather data.
                if (clause.list.indexOf(clauseVar) == (clause.list.size() - 1 )) {


                    clause.truth = false;

                    truth = false;



                    clauseVarIt = clause.list.listIterator();

                    clauseVar = clauseVarIt.next();

                    vListIt = vList.iterator();

                    counter = -1;
                    clauseCounter++;

                    break;




                } else

                    clauseVar = clauseVarIt.next();


            }


            // we found one, wipe all of our entries. This clause is true.
            if (clauseVar.equals(var)) {

                clauseCounter++;

                clause.truth = true;

                if (!(clauseCounter == clauses)) {

                    clause = cListIt.next();

                    clauseVarIt = clause.list.listIterator();

                    clauseVar = clauseVarIt.next();

                    vListIt = vList.iterator();

                    counter = -1;

                }

                else {

                    truth = true;
                    break;


                }



            }


            // increment the counter, so we know when all variables in vList have been compared to one variable in the
            // clause. Go to the next variable

            counter++;
            var = vListIt.next();


        }





        // if the size of the map is 2^n, the formula is NOT satisfiable.
        if (variableList.size() == (2 << variables) ) {

            System.out.println("Formula is NOT satisfiable.");

            truth = false;

            finishedTesting = true;




        }


        // if the size is not yet 2^n, and truth is also still false,
        if ((!truth && !finishedTesting)) {

            // full false are at index 0 of variableMap
            // adding assignments to map
            variableList.add(mapCounter++, vList);

            clauseCounter = 0;


        }






        else {

            long endTime = (System.currentTimeMillis() - startTime);
            truth = true;

            finishedTesting = true;

            System.out.println("Formula is true. Time taken: " + endTime + " ms");


        }


    }

    private boolean nextAssignment() {


        addOne();

        solve();



        return false;


    }


    private void addOne() {

        // Iterate starting at the LAST value of the list.

        vListIterator = vList.listIterator(vList.size() - 1);


        tempVar = vList.get(vList.size()-1);



        //Right most 0, flip it to a 1
        // All the 1s to the right, turns to 0s

        while (vListIterator.hasPrevious() || vListIterator.hasNext()) {


            // if the previous value is = 0, and the value before that one is also not a 1,
            if (tempVar.binaryVal == 0) {

                // flip the sign, set the binary value to 1, making variable true.
                vList.set(vList.indexOf(tempVar), new Variable (tempVar.value * -1));


                break;

            }

            // if the value is a 1, set == 0, go BACK one value.
                if (tempVar.binaryVal == 1) {

                    tempCounter++;

                    vList.set(vList.indexOf(tempVar), new Variable ((tempVar.value * -1)));



                }

                while (tempCounter >= 0) {

                    tempVar = vListIterator.previous();
                    tempCounter--;


                }







        }








    }




    }



