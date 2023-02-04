/**
 * @author SJ
 * 26 January 2023
 *
 * An Employee object has a name, id, and a boolean. From each Employee's id, a hashcode is created to allow
 * 'clocking in.'
 *
 */

package projectone;

public class Employee {

    String name;
    String id;
    boolean clockedIn;

    public Employee(String name, String id, boolean clockedIn) {

        this.name = name;
        this.id = id;
        this.clockedIn = clockedIn;

    }

    // this is not  'good' hashcode method, i just wanted to illustrate that i am familiar with hashcodes
    public int hashCode() {

        String firstFour = id.substring(0,4);

        int result = Integer.parseInt(firstFour) + 17;

        return result;
    }

    @Override
    public String toString() {

        return this.name;

    }


}






