package projecttwo;

import java.util.*;



public class Clause {






    public Clause(List<Integer> list) {
        int cursor = 0;
        int val;


        ArrayList<List<Integer>> doubleList = new ArrayList<List<Integer>>();
        List<Integer> newList = new ArrayList<Integer>();

        Iterator<Integer> it = list.iterator();





        while (it.hasNext()) {

            val = list.get(cursor);

            if (!(val == 0)) {

                newList.add(val);
                list.remove(cursor);

            }


            else {

                List<Integer> temp = new ArrayList<Integer>(newList);

                doubleList.add(temp);
                newList.clear();
                list.remove(cursor);

            }




        }


        doubleList.add(newList);




        System.out.println(doubleList);
    }

    public String toString() {

       return "";



    }




}
