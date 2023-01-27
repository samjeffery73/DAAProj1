/**
 * @author SJ
 * 26 January 2023
 *
 * Driver file for DAA Project 1 EC
 *
 * This program is called the 'Sapphire Access System."
 * It uses a hashMap to store Employees who work for a 'secure' company.
 * To clock in, the employee must type a 'secure' four digit code.
 * Employees names are given from a text file called names.txt
 *
 * If you type an incorrect code three times, the system acts
 * as if it is being infiltrated, and shuts down.
 *
 * After clocking in, your name is stored to a LinkedList, and is written to a text file status.txt
 * which shows if you have clocked in or not.
 *
 * If you have not, you are marked in the status file as LATE.
 *
 * (For testing purposes, i have hardcoded a 'LATE' employee.)
 *
 */

package projectone;
import java.io.*;
import java.util.*;

public class Driver {

    public static void main(String args[]) throws IOException {

        // The file with the names of the employees.
        File readFile = new File("C:/Users/njhdt/OneDrive/Desktop/Rowan Files/DAA/SapphireSystem/names");
        Scanner fileScan = new Scanner(readFile);

        // The status file that writes if employees are on time.
        BufferedWriter writer = new BufferedWriter(new FileWriter
                ("C:/Users/njhdt/OneDrive/Desktop/Rowan Files/DAA/SapphireSystem/status"));

        // for the sake of simplicity each line has a first and last name
        // creating employee objects

        Employee jane = new Employee(fileScan.nextLine(), "1234567", false);
        Employee marge = new Employee(fileScan.nextLine(), "2255889", false);
        Employee steve = new Employee(fileScan.nextLine(), "1182345", false);

        // I added an extra employee to demonstrate what may happen if an employee doesn't clock in on time.
        Employee late = new Employee(fileScan.nextLine(), "2855832", false);

        // 'clocked in' employees will be added to the LinkedList
        List<Employee> list = new LinkedList<Employee>();

        // all employees and employee info is stored in here.
        HashMap<Integer, Employee> map = new HashMap<Integer, Employee>();

        // putting each employee to the hashmap with their dedicated hashcodes or 'access codes.'
        map.put(steve.hashCode(), steve);
        map.put(marge.hashCode(), marge);
        map.put(jane.hashCode(), jane);
        map.put(late.hashCode(), late);

        /** For Debugging purposes
                System.out.println("steve " + steve.hashCode()); // 1199
                System.out.println("marge " + marge.hashCode()); // 2272
                System.out.println("jane " + jane.hashCode()); // 1251
                System.out.println("Late Guy " + late.hashCode()); // 2872
        **/

        // blank employee to get the employee value from the map
        Employee e = new Employee(null, null, false);

        // again, map.size - 1 because we want 1 employee to ALWAYS be late.
        int employeesLeft = map.size() - 1;
        int failedAttempts = 0;

        while (employeesLeft > 0) {

            System.out.println("Welcome, please enter your Sapphire Access Code to clock in.");

            Scanner scan = new Scanner(System.in);

            int key = scan.nextInt();

            if (map.containsKey(key)) {

                for (Map.Entry<Integer, Employee> entry : map.entrySet()) {

                    int mapKey = entry.getKey();


                    if (mapKey == key) {
                        map.get(key).clockedIn = true;
                        e = map.get(key);
                        list.add(e);

                        System.out.println("Hello " + e.name + "! Access has been granted and you have clocked in!");
                        employeesLeft--;
                    }

                }


            }
            else {
                failedAttempts++;
                System.err.println("No Employee with such access code exists. Try again. Tries remaining: " + (3 - failedAttempts ));

            }

            if (failedAttempts == 3) {
                System.err.println("You have failed 3 times and the system has been locked. Goodbye.");
                break;
            }

        }

        writer.write("CLOCKED IN \n" + list.toString());

        // a for loop that checks each entry, if any of them are not clocked in, it adds "LATE" mark to the
        // 'status' text file.
        for (Map.Entry<Integer, Employee> entry : map.entrySet()) {

            e = entry.getValue();
            if (!e.clockedIn) {

                writer.write("\n LATE \n" + e.name);
            }
        }

        writer.close();

        System.out.println("\nAll Employees clocked in. Please review status file for more info.");
    }
}






