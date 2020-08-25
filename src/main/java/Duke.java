import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String line;
        String[] tasks = new String[100];
        int taskCount = 0;
        Scanner in = new Scanner(System.in);

        System.out.println("The You-know-who:");
        System.out.println("\tHello! I'm The You-know-who\n\tWhat are your orders?");

        System.out.print("Hung:\n\t");
        line = in.nextLine();
        while (!line.equalsIgnoreCase("bye")){
            if (line.equalsIgnoreCase("list")) {
                System.out.println("The You-know-who:");
                for(int i = 1; i < taskCount + 1; i = i + 1){
                    System.out.println("\t" + i + ". " + tasks[i - 1]);
                }
                System.out.print("Hung:\n\t");
                line = in.nextLine();
            } else {
                tasks[taskCount] = line;
                taskCount++;
                System.out.println("The You-know-who:\n\tadded: " + line);
                System.out.print("Hung:\n\t");
                line = in.nextLine();
            }
        }

        System.out.println("The You-know-who:");
        System.out.println("\t~~Dead is like a wind, always by my side~~");
    }

}
