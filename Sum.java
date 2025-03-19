import java.io.*;
import java.util.*;

public class Sum {
    // Sum baby data from a list of YearlyData objects
    public static List<Baby> sumBabies(List<YearlyData> yearlyDataList, boolean isBoy) {
        Map<String, Baby> totalMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (YearlyData yd : yearlyDataList) {
            Baby[] arr = isBoy ? yd.getBoys() : yd.getGirls();
            if (arr == null) continue;
            for (Baby b : arr) {
                if (b == null) continue;
                String name = b.getName();
                if (totalMap.containsKey(name)) {
                    totalMap.get(name).addCount(b.getCount());
                } else {
                    totalMap.put(name, new Baby(name, b.getRank(), b.getCount()));
                }
            }
        }
        List<Baby> summed = new ArrayList<>(totalMap.values());
        Collections.sort(summed);
        return summed;
    }
    
    public static void writeSumToFile(List<Baby> summed, String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Baby b : summed) {
                pw.println(b);
            }
            System.out.println("Sum data written to " + filename);
        } catch (IOException e) {
            System.err.println("Could not write " + filename + ": " + e.getMessage());
        }
    }
    
    // Combine both summed lists, sort by count, find which baby name makes the cumulative total cross 10 million
    public static String find10MillionThreshold(List<Baby> sumBoys, List<Baby> sumGirls) {
        List<Baby> combined = new ArrayList<>();
        combined.addAll(sumBoys);
        combined.addAll(sumGirls);
        combined.sort((b1, b2) -> Integer.compare(b2.getCount(), b1.getCount()));
        
        long cumulative = 0;
        for (Baby b : combined) {
            cumulative += b.getCount();
            if (cumulative >= 10000000) {
                return b.getName();
            }
        }
        return null;
    }
}


