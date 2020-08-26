import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String line;
        Task[] tasks = new Task[100];
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
                    System.out.printf("\t%d. [%s] %s\n", i, tasks[i - 1].getStatusIcon(), tasks[i - 1].description);
                }
                System.out.print("Hung:\n\t");
                line = in.nextLine();
            } else if (line.toLowerCase().startsWith("done")) {
                int taskNumber = Integer.parseInt(line.substring(5)) - 1;
                if (taskNumber >= 0 && taskNumber < taskCount) {
                    tasks[taskNumber].setDone();
                    System.out.println("The You-know-who:");
                    System.out.println("\tNice! I've mark this task as done:");
                    System.out.printf("\t [%s] %s\n", tasks[taskNumber].getStatusIcon(), tasks[taskNumber].description);
                    System.out.print("Hung:\n\t");
                    line = in.nextLine();
                } else {
                    System.out.println("The You-know-who:");
                    System.out.println("\tTask number is not valid! Please enter another command!");
                    System.out.print("Hung:\n\t");
                    line = in.nextLine();
                }
            } else{
                Task t = new Task(line);
                tasks[taskCount] = t;
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
