package projecttwo;

public class Variable {

    boolean truth;

    int value;

    public Variable(int value) {

        this.value = value;

        if (value < 0) {

            truth = false;

        }

        else
            truth = true;



    }

    public String toString() {

        return value + "";



    }




}
