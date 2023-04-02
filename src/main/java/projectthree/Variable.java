package projectthree;

public class Variable {

    int rowLoc, colLoc, value, id;

    boolean truth = false;


    /**
     * Variable
     * @param rowLoc
     * @param colLoc
     * @param value
     *
     * A variable is in form x<i,j,k>
     *     where i is the row location
     *     j is the column location
     *     and k is the value at that position.
     *
     *     Each variable has an identifier, or a unique ID.
     *
     *     ID = rowLoc * 100 + colLoc * 10 + the integers numeric 'value'.
     *
     *     i.e.
     *
     *     x<1,1,5>
     *         has a unique identifier of 115.
     *
     */

    public Variable(int rowLoc, int colLoc, int value) {


        this.rowLoc = rowLoc;

        this.colLoc = colLoc;

        this.value = value;


        if (value > 0) {

            this.truth = true;
        }



    }


    public String toString() {

        String s = "x" + value + "<" + rowLoc + " " + colLoc + ">";
        return s;

    }



}
