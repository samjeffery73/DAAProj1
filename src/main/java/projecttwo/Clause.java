package projecttwo;

import java.util.*;



public class Clause {

    ArrayList<List<Variable>> doubleList = new ArrayList<List<Variable>>();
    List<Variable> newList = new ArrayList<Variable>();

    int cursor = 0;
    int val;

    public Clause(List<Variable> list) {

        Iterator<Variable> it = list.iterator();

        while (it.hasNext()) {

            Variable val = list.get(cursor);

            if (!(val.value == 0)) {

                newList.add(val);
                list.remove(cursor);

            }


            else {

                List<Variable> temp = new ArrayList<Variable>(newList);
                doubleList.add(temp);
                newList.clear();
                list.remove(cursor);

            }


        }


        doubleList.add(newList);


        System.out.println("List of clauses: " + doubleList);

        solve();
    }


    public void solve() {


        System.out.println("Solving formula...");


        for (int i = 0; i < doubleList.size(); i++) {







        }




    }




}
