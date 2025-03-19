import java.io.*;
import java.util.*;

public class YearlyReader {
    public static YearlyData readYear(int year, int arraySize) {
        Baby[] boys = new Baby[arraySize];
        Baby[] girls = new Baby[arraySize];
        int boyIndex = 0;
        int girlIndex = 0;
        long totalBoys = 0;
        long totalGirls = 0;
        
        String filename = "names" + year + ".csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
 
            if (line != null && line.toLowerCase().contains("rank")) {
                line = br.readLine();
            }
            while (line != null) {
                String[] tokens = line.split(",");
                if (tokens.length >= 5) {
                    try {
                        int rank = Integer.parseInt(tokens[0].trim());
                        String boyName = tokens[1].trim();
                        int boyCount = Integer.parseInt(tokens[2].trim());
                        String girlName = tokens[3].trim();
                        int girlCount = Integer.parseInt(tokens[4].trim());
                        
                        if (boyIndex < arraySize) {
                            boys[boyIndex++] = new Baby(boyName, rank, boyCount);
                        }
                        if (girlIndex < arraySize) {
                            girls[girlIndex++] = new Baby(girlName, rank, girlCount);
                        }
                        
                        totalBoys += boyCount;
                        totalGirls += girlCount;
                    } catch (NumberFormatException e) {
                        System.err.println("Error line: " + line);
                    }
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File " + filename + " not found. Skipping year " + year);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Sort the arrays alphabetically
        Arrays.sort(boys, 0, boyIndex);
        Arrays.sort(girls, 0, girlIndex);
        
        // Write each array to its own file
        writeArrayToFile(boys, boyIndex, "Boys" + year + ".txt");
        writeArrayToFile(girls, girlIndex, "Girls" + year + ".txt");
        
        // Display totals for each year
        System.out.println(year + " Totals:");
        System.out.println("Total boys born: " + totalBoys);
        System.out.println("Total girls born: " + totalGirls);
        System.out.println("-----------------------------------------------------");
        
        return new YearlyData(year, Arrays.copyOf(boys, boyIndex),
                                     Arrays.copyOf(girls, girlIndex));
    }
    
    private static void writeArrayToFile(Baby[] arr, int length, String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (int i = 0; i < length; i++) {
                pw.println(arr[i]);
            }
            System.out.println("Wrote " + filename);
        } catch (IOException e) {
            System.err.println("Error writing " + filename + ": " + e.getMessage());
        }
    }
}


