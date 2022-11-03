import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import structures.node.Node;
import structures.linkedlist.LinkedList;

public class hw {

    // ~~design of readFile
    public static LinkedList<Integer> readIntegersFile(String fileName){
        // initialize reader and output linkedlist
        File file = new File(fileName);
        LinkedList<Integer> output = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            // add to output linkedlist
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (String str : values) {
                    output.add(Integer.parseInt(str));
                }
            }

            // close reader
            br.close();
        } 
        catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        
        return output;
    }

    public static void main(String[] args) {
        // initialization
        String sourcePath = "homeworks/2/source.txt";
        String searchPath = "homeworks/2/search.txt";

        LinkedList<Integer> uniqueList = new LinkedList<>();
        Node<Integer> iterator = null;
        long startTime = 0;
        long endTime = 0;
        long timeCost = 0;

        // ~~design of a
        // read comma seperated text file into source list
        // iterate through source list
        // check if that value in unique list
        // add value to the unique list if not added already
        LinkedList<Integer> sourceList = readIntegersFile(sourcePath);
        iterator = sourceList.head;
        
        while(iterator != null){
            
            if(uniqueList.search(iterator.value) == false){
                uniqueList.add(iterator.value);
            }

            iterator = iterator.next;
        }

        // ~~design of b
        // init total var
        // read comma seperated text file into search list
        // iterate through search list
        // analyze search search list elements on unique list in each iteration
        // add memory access count to total var
        // avg = total / count
        int totalMemoryAccess = 0;

        LinkedList<Integer> searchList = readIntegersFile(searchPath);
        iterator = searchList.head;

        startTime = System.nanoTime();
        while(iterator != null){
            totalMemoryAccess += uniqueList.searchAnalyze(iterator.value);
            iterator = iterator.next;
        }
        endTime = System.nanoTime();

        int avgMemoryAccess = totalMemoryAccess / uniqueList.count();
        timeCost = (endTime - startTime) / 1000000;
        System.out.println("~~ total memory access: " + totalMemoryAccess);
        System.out.println("~~ average memory access: " + avgMemoryAccess);
        System.out.println("~~ calculated time cost: " + timeCost + "ms");

        // ~~design of c
        // init total var
        // read comma seperated text file into search list
        // iterate through search list
        // analyze search and push search list elements on unique list in each iteration
        // add memory access count to total var
        // avg = total / count
        // note: since search list not manipulated, we can use it
        LinkedList<Integer> uniqueListCopy = uniqueList.copy();
        totalMemoryAccess = 0;
        iterator = searchList.head;

        startTime = System.nanoTime();
        while(iterator != null){
            totalMemoryAccess += uniqueListCopy.searchPushAnalyze(iterator.value);
            iterator = iterator.next;
        }
        endTime = System.nanoTime();

        avgMemoryAccess = totalMemoryAccess / uniqueListCopy.count();
        timeCost = (endTime - startTime) / 1000000;
        System.out.println("~~ total memory access: " + totalMemoryAccess);
        System.out.println("~~ average memory access: " + avgMemoryAccess);
        System.out.println("~~ calculated time cost: " + timeCost + "ms");

        // ~~ design of d
        // calcuate & display time cost by delta nanotime for every search algorithm
    
        // ~~ design of e
        // init total var
        // read comma seperated text file into search list
        // sort unique list
        // iterate through search list
        // analyze search search list elements on sorted unique list in each iteration
        // add memory access count to total var
        // avg = total / count
        // note: since search list not manipulated, we can use it

        LinkedList<Integer> sortedUniqueList = uniqueList.selectionSort();
        totalMemoryAccess = 0;
        iterator = searchList.head;

        startTime = System.nanoTime();
        while(iterator != null){
            totalMemoryAccess += sortedUniqueList.searchAnalyze(iterator.value);
            iterator = iterator.next;
        }
        endTime = System.nanoTime();

        avgMemoryAccess = totalMemoryAccess / sortedUniqueList.count();
        timeCost = (endTime - startTime) / 1000000;
        System.out.println("~~ total memory access: " + totalMemoryAccess);
        System.out.println("~~ average memory access: " + avgMemoryAccess);
        System.out.println("~~ calculated time cost: " + timeCost + "ms");
    }
}
