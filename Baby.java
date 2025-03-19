import java.util.*;

public class Baby implements Comparable<Baby> {
    private String name;
    private int rank;
    private int count;
    
    public Baby(String name, int rank, int count) {
        this.name = name;
        this.rank = rank;
        this.count = count;
    }
    
    public String getName() {
        return name;
    }
    
    public int getRank() {
        return rank;
    }
    
    public int getCount() {
        return count;
    }
    
    public void addCount(int additionalCount) {
        this.count += additionalCount;
    }
    
    @Override
    public int compareTo(Baby other) {
        return this.name.compareToIgnoreCase(other.name);
    }
    
    @Override
    public String toString() {
        return name + ": " + rank + ": " + count;
    }
    
    
    public static void main(String[] args) {
        int startYear = 1990;
        int endYear = 2017;
        int arraySize = 1000;
        List<YearlyData> yearlyDataList = new ArrayList<>();
        
        // Read data for each year using YearlyReader
        for (int year = startYear; year <= endYear; year++) {
            YearlyData data = YearlyReader.readYear(year, arraySize);
            if (data != null) {
                yearlyDataList.add(data);
            }
        }
        
        // Print out the arrays for each year
        for (YearlyData yd : yearlyDataList) {
            System.out.println(yd.getYear() + " Boys:");
            for (Baby b : yd.getBoys()) {
                System.out.println(b);
            }
            System.out.println(yd.getYear() + " Girls:");
            for (Baby b : yd.getGirls()) {
                System.out.println(b);
            }
        }
        
        // Sum data for boys and girls over all year
        List<Baby> sumBoys = Sum.sumBabies(yearlyDataList, true);
        List<Baby> sumGirls = Sum.sumBabies(yearlyDataList, false);
        
        // Write the summed results to files
        Sum.writeSumToFile(sumBoys, "AllBoys.txt");
        Sum.writeSumToFile(sumGirls, "AllGirls.txt");
        
        // Print the summed arrays
        System.out.println("\nTotal Boys:");
        for (Baby b : sumBoys) {
            System.out.println(b);
        }
        System.out.println("\nTotal Girls:");
        for (Baby b : sumGirls) {
            System.out.println(b);
        }
        
        // Determine which baby name first crosses 10 million births
        String crossingName = Sum.find10MillionThreshold(sumBoys, sumGirls);
        if (crossingName != null) {
            System.out.println("\nThe baby name that first crossed 10 million births is: " + crossingName);
        } else {
            // literally impossible but just in case :D
            System.out.println("\nNo baby name crossed the 10 million threshold.");
        }
    }
}



