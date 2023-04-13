package projectthree;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class negatedClause {

    int index;

    String clause;

    int first, last;

    Map<Integer, String> clauseMap = new HashMap<Integer,String>();



    /**
     *
     * @param first
     * @param last
     */

    public negatedClause(int first, int last){



        String clause = (first + " " + last + " 0\n");



        this.clause = clause;


        clauseMap.put(this.hashCode(), clause);



    }

    public int hashCode() {

        int index = (first + last) * -1;

        this.index = index;

        return index;


    }


    public boolean exists() {


        if (clauseMap.containsValue(this.clause)) {

            return true;

        }


        return false;

    }




}
