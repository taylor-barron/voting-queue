/* --------------------------------
    Fill in your information here
	Taylor Barron
	4/5/22
  ---------------------------------  */


import jsjf.CircularArrayQueue;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class VotingQueue
{
	/*-----------------------------------------------------------------------------
	*   This program should use the Scanner class to input the number of voters arriving at a 
	*	voting precinct. There are 2 items on each line (record) in the file:
	*		(1)  A sequential car number
	*		(2)  The number of voters arriving in the car
	*	
	*
	*   This program will be used to process the voters in a queue.             
	*---------------------------------------------------------------------------------*/
   
   public static void main (String[] args) throws Exception {
        CircularArrayQueue<String> queue = new CircularArrayQueue<String>();
	    BufferedReader reader = new BufferedReader(new FileReader("Voters.csv"));
	    String line = null;
		String votersInCar;

	    while((line = reader.readLine()) != null) {

			Scanner scan = new Scanner(line);
			scan.useDelimiter(",");
			String theNext = "";

			while (scan.hasNext()) {
				theNext = scan.next();
			}

			votersInCar = theNext;
			queue.enqueue(votersInCar);
	    }

		PrintWriter outFile = null;
		outFile = new PrintWriter(new FileWriter("voters.txt"));
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        outFile.println("Date: " + formatter.format(date));
		outFile.println ("\nVOTER REPORT");
		outFile.println ("");
		outFile.printf ("%s%20s", "Voter #", "Voting Time");
		outFile.println ("");

		String numVotersInCarStr = queue.first();
		int numVotersInCar;
		int counter = 1;
		int totalTime = 0;

		while(numVotersInCarStr != null) {
			numVotersInCar = Integer.valueOf(queue.dequeue());

			for(int i = 0; i < numVotersInCar; i++) {
				double randomNumberDouble = Math.random() * (840) + 60;
				int randomNumber = (int)randomNumberDouble;
				totalTime += randomNumber;
				outFile.printf ("%5s%18s\n", String.valueOf(counter), String.valueOf(randomNumber));
				counter++;
			}

			try{
				numVotersInCarStr = queue.first();
			} catch (Exception e) {
				numVotersInCarStr = null;
			}
		}

		outFile.print("\nNumber of voters: "+counter+"\nTotal time for all voters: "+totalTime+" seconds\nAverage time to vote: "+(totalTime/counter)+" seconds");

		System.out.println ("\n---PROCESSING COMPLETED---\n\n");
		System.out.println ("\n---Your report can be found in voters.txt\n\n");		
		if (outFile != null)
		{	outFile.close();
       	}
	}
}
