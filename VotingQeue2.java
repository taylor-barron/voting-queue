import jsjf.CircularArrayQueue;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class VotingQeue2 {
    public static void main (String[] args) throws Exception {

        // add a car and queue data from car
        CircularArrayQueue<String> queue = new CircularArrayQueue<String>();
        // processed voters saved to new queue for reports length
        CircularArrayQueue<String> processedQueue = new CircularArrayQueue<String>();
        // store voting time
        CircularArrayQueue<String> votingTime = new CircularArrayQueue<String>();
    
        int numberInCar = 0;
        int counter = 0;
        int totalTime = 0;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Scanner scan = new Scanner(System.in);
        System.out.println("\nEnter 1 to add a new car of voters.\nEnter 2 to process voters\nEnter 3 to display statistics.\nEnter 4 to end the program and save the report:  ");
        int selection = scan.nextInt();

        while (selection == 1 || selection == 2 || selection == 3) {

            if (selection == 1) {
                System.out.println("\nEnter the number of voters in the car:  ");
                numberInCar = scan.nextInt();
                queue.enqueue(String.valueOf(numberInCar));
                
            } else if (selection == 2) {

                String numVotersInCarStr = "";
                int numVotersInCar;
                System.out.println("Date: " + formatter.format(date));
                System.out.println ("\nVOTER REPORT");
                System.out.println ("");
                System.out.printf ("%s%20s", "Voter #", "Voting Time");
                System.out.println ("");
        
                while(numVotersInCarStr != null) {
        
                    try{
                        numVotersInCar = Integer.valueOf(queue.dequeue());

                        for (int i = 0; i < numVotersInCar; i++) {
                            double randomNumberDouble = Math.random() * (840) + 60;
                            int randomNumber = (int)randomNumberDouble;
                            processedQueue.enqueue("1");
                            votingTime.enqueue(String.valueOf(randomNumber));
                            totalTime += randomNumber;
                            counter++;
                            System.out.printf ("%5s%18s\n", String.valueOf(counter), String.valueOf(randomNumber));
                        }
                    } catch (Exception e) {
                        numVotersInCarStr = null;
                    }
                }

            } else if (selection == 3) {
                String processedVoters = processedQueue.toString();
                int processedVotersLength = processedVoters.length();
                String unprocessedVoters = queue.toString(); // get length // add up using charAt
                int unprocessedVotersLength = unprocessedVoters.length();
                int averageTime = 0;
                int waitingVoters = 0;
                int totalWaitingVoters = 0;

                if (processedVotersLength == 0) {  averageTime = 0; }
                else {  averageTime = totalTime / processedVotersLength; }

                for (int i = 0; i < unprocessedVotersLength; i++) {
                    waitingVoters = Integer.valueOf(String.valueOf(unprocessedVoters.charAt(i)));
                    totalWaitingVoters += waitingVoters;               
                }

                System.out.println("\nNumber of voters processed: "+processedVotersLength+"\nTotal time to vote: "+totalTime+"\nAverage time to vote: "+averageTime+"\nNumber of voters left in queue: "+totalWaitingVoters);
            }

            System.out.println("\nEnter 1 to add a new car of voters.\nEnter 2 to process voters\nEnter 3 to display statistics.\nEnter 4 to end the program and save the report:  ");
            selection = scan.nextInt();
        }
        // outfile report on exit
        // length is processed queue.length // get individual times from votingTime queue //
        String processedVoters = processedQueue.toString();
        int processedVotersLength = processedVoters.length();
        String unprocessedVoters = queue.toString();
        int unprocessedVotersLength = unprocessedVoters.length();
        int averageTime = 0;
        int waitingVoters = 0;
        int totalWaitingVoters = 0;

        if (processedVotersLength == 0) {  averageTime = 0; }
        else {  averageTime = totalTime / processedVotersLength; }

        for (int i = 0; i < unprocessedVotersLength; i++) {
            waitingVoters = Integer.valueOf(String.valueOf(unprocessedVoters.charAt(i)));
            totalWaitingVoters += waitingVoters;               
        }
                
		PrintWriter outFile = null;
		outFile = new PrintWriter(new FileWriter("voters.txt"));
        outFile.println("Date: " + formatter.format(date));
		outFile.println ("\nVOTER REPORT");
		outFile.println ("");
		outFile.printf ("%s%20s", "Voter #", "Voting Time");
		outFile.println ("");

        // for loop for voters
        for (int i = 0; i < processedVotersLength; i++) {
            outFile.printf ("%5s%18s\n", String.valueOf(i + 1), votingTime.dequeue());
        }

		outFile.print("\nNumber of voters processed: "+processedVotersLength+"\nTotal time to vote: "+totalTime+"seconds.\nAverage time to vote: "+averageTime+" seconds.\nNumber of voters left in queue: "+totalWaitingVoters);

		System.out.println ("\n---PROCESSING COMPLETED---\n\n");
		System.out.println ("\n---Your report can be found in voters.txt\n\n");		
		if (outFile != null)
		{	outFile.close();
       	}
    }    
}
