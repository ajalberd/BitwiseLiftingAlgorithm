package csvdemo;

import java.util.ArrayList;

import csvdemo.StaticMethods;
public class Analysis {
	static float lowTolerance = 6; //if max above this value...
	static double zeroTolerance = .1;

	public static void main(String[] args){
		int numReps = 0;
		//read in our file
		ArrayList<ArrayList<Float>> values = StaticMethods.readFile("./data/15mspc.csv");
		//pass the 5th column into the filter
		
		//normalize gravity to column 5
		
		
		ArrayList<Float> dataColumn = StaticMethods.getColumn(values, 5);
		ArrayList<Float> filteredData = StaticMethods.exponentialFilter(dataColumn);
		values = StaticMethods.replaceColumn(values, filteredData, 5);
		StaticMethods.writeFile(values, "./data/output.csv");
		for(Float z : StaticMethods.findZeros(values, 5, 0,zeroTolerance)) {
			System.out.println(z);
		}
		ArrayList<Float> temp = StaticMethods.findZeros(values, 5, 0,zeroTolerance);
		for(int i = 0; i<temp.size()-1; i++ ) {
			//find time between zeroes
			int locTime1 = StaticMethods.getColumn(values, 0).indexOf(temp.get(i)); //for the first zero get time 1
			int locTime2 = StaticMethods.getColumn(values, 0).indexOf(temp.get(i+1)); //for the second zero get time 2
			int timebetween = locTime2 - locTime1; 									//subtract them
			Float max = StaticMethods.findMinAndMax(StaticMethods.getColumn(values, 5), locTime1, locTime2)[1]; //find the max between the two time points
			if(max>lowTolerance) {													//is the max between them greater than out threshold?
				System.out.println(timebetween + " | " + locTime1 + " | " + locTime2); //that's a rep
				numReps++;
			}
		}
		System.out.println(numReps);
    }
}



