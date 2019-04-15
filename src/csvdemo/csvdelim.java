package csvdemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class csvdelim {

	public static void main(String[] args){
        //exResults.setNumExcersizes(3); //simulates 3 good reps from the trainer file.
        //We should be looking for 3 good reps in the test file.
		compare(); //compare compares the data from the config to the test data
    }
	public static void compare() {
		float tolerance = -12; //this will be calculated in the real one
		float maxtolerance = -8;
		float mintime = 300;
		float maxtime = 1500;
		int numReps = 0;
		//This will be a getter in the real one
        String fileName1= "/home/andrew/eclipse-workspace-java/csvdemo/data/150msdead.csv";
        
        File file= new File(fileName1);

        // this gives you a 2-dimensional array of strings
        List<List<Float>> lines = new ArrayList<>();
        Scanner inputStream;

        try{
            inputStream = new Scanner(file);

            while(inputStream.hasNext()){
                String line= inputStream.next();
                String[] values = line.split(",");
                Float[] floatrow = new Float[values.length];
          
                int i = 0;
                for(String s : values){
                    float res = Float.parseFloat(s);
                    floatrow[i] = res;
                    i++;
                }
                // this adds the currently parsed line to the 2-dimensional string array
                lines.add(Arrays.asList(floatrow));
            }
            inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // the following code lets you iterate through the 2-dimensional array
        int lineNo = 1;
        int[] mins= new int[300]; //placeholder
        int[]maxs = new int[300];
        int i = 0;
        int j = 0;
        for(List<Float> line: lines) {
            int columnNo = 1;
            for (float value: line) {
            	if(columnNo == 6 && value < tolerance) { //potential 
            		//System.out.println("found a match");
            		mins[i] = lineNo; //saves the line the match is in.
            		i++;
            	}
            	if(columnNo == 6 && value > maxtolerance) {
            		maxs[j] = lineNo;
            		j++;
            	}
                //System.out.println("Line " + lineNo + " Column " + columnNo + ": " + value);
                columnNo++;
            }
            lineNo++;
        }
        
        //DELETE FOR LOOP UNDER THIS LINE - NOT WORKING
        for(int x : mins) {
        	for(int y : maxs) {
        		if(x != 0 && y!= 0){
        			if((mintime < (lines.get(y).get(0) - lines.get(x).get(0)) && (lines.get(y).get(0) - lines.get(x).get(0)) < maxtime)) {
        			numReps++;
        			}        			
        		}
        	}
        }
        System.out.println(numReps);
    }
	public class exresults{
		private int numReps;
		
		public exresults() {
			numReps = 0;
		}
		public exresults(int num) {
			numReps = num;
		}
		public int getNumExercises() {
			return numReps;
		}
		public void setNumExercises(int num) {
			numReps = num;
		}
	}
}