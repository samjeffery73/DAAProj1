/***
 * Variable.java
 *
 * @auth SJ
 *
 * @date 13 Feb, 2023
 *
 * A variable is defined as an integer with a boolean attached to it. If the value of a variable is less than 0 (has a - sign)
 * The variable is considered to be FALSE. For the boolean to be true, the value must be > 0.
 *
 *
 *
 *
 */

package projecttwo;

public class Variable {

    boolean truth;

    int value;

    public Variable(int value) {

        this.value = value;

        if (value < 0)
            truth = false;

        else
            truth = true;



    }

    public String toString() {

        return value + "";



    }


    public boolean equals(Variable var) {

        if (var.value == this.value) {

            return true;

        }

        if (var == null) {

            return false;
        }

        return false;



    }




}
