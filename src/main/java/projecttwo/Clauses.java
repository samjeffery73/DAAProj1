package projecttwo;

import java.util.*;



public class Clauses {

    List<Integer> clauseList = new ArrayList<Integer>();


    public Clauses(int ... vars) {


        for (int var : vars) {

            clauseList.add(var);

        }





    }




}
