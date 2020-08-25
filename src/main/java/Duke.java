import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("The You-know-who:");
        System.out.println("\tHello! I'm The You-know-who\n\tWhat are your orders?");

        System.out.print("Hung:\n\t");
        line = in.nextLine();
        while (!line.equalsIgnoreCase("bye")){
            System.out.println("The You-know-who:\n" + "\t" + line);
            System.out.print("Hung:\n\t");
            line = in.nextLine();
        }

        System.out.println("The You-know-who:");
        System.out.println("\t~~Dead is like a wind, always by my side~~");
    }

}
