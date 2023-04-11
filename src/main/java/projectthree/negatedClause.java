package projectthree;


import java.util.ArrayList;
import java.util.List;

public class negatedClause {

    int index;

    String clause;




    /**
     *
     * @param first
     * @param last
     */

    public negatedClause(int first, int last){



        String clause = (first + " " + last + " 0\n");

        int index = (first + last) * -1;

        this.index = index;

        this.clause = clause;



    }




}
