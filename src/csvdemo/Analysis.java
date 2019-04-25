package csvdemo;

import java.util.ArrayList;

import csvdemo.StaticMethods;
public class Analysis {
	
	public static void main(String[] args){
		//read in our file
		ArrayList<ArrayList<Float>> values = StaticMethods.readFile("./data/powercleanmodel.csv");
		//pass the 5th column into the filter
		ArrayList<Float> dataColumn = StaticMethods.getColumn(values, 5);
		ArrayList<Float> filteredData = StaticMethods.exponentialFilter(dataColumn);
		values = StaticMethods.replaceColumn(values, filteredData, 5);
		StaticMethods.writeFile(values, "./data/output.csv");
    }
	
}


