package homeworks.huffman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void writeFile(String filePath, String content){
        try (PrintWriter writer = new PrintWriter(filePath, "UTF-8")) {
            writer.println(content);
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String file2string(String filePath){
        String content = "";

        File file = new File(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            
            String line;
            while ((line = br.readLine()) != null) {
                for (int i=0; i < line.length(); i++){
                    char currentChar = line.charAt(i);
                    content += currentChar;
                }
            }
            br.close();
        } 
        catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }

        return content;
    }
    
    public static Map<Character, Integer> letter2map(String filePath){
        Map<Character, Integer> charMap = new HashMap<Character, Integer>();
        File file = new File(filePath);
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int count = 0;

            while ((line = br.readLine()) != null) {
                for (int i=0; i < line.length(); i++){
                    char currentChar = line.charAt(i);
                    int value = charMap.getOrDefault(currentChar, -1);

                    if (value == -1)
                        charMap.put(currentChar, 1);
                    else{
                        charMap.put(currentChar, charMap.get(currentChar) + 1);
                    }                    
                    count++;
                }
            }

            charMap.put('~', count);

            br.close();
        } 
        catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        
        return charMap;
    }

    public static HuffmanList map2list(Map<Character, Integer> map){
        
        HuffmanList list = new HuffmanList();
        int count = map.get('~');

        // traverse the map
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            String symbol = String.valueOf(entry.getKey());
            Float frequency = ( (float) entry.getValue() ) / count;

            HuffmanNode node = new HuffmanNode(symbol, frequency);
            
            list.sortedAdd(node);
        }

        return list;
    }

    public static void main(String[] args) {
        
        String PATH_LETTER = "homeworks/huffman/letter.txt";
        String PATH_SOURCE = "homeworks/huffman/source.txt";
        String PATH_ENCODED = "homeworks/huffman/encoded.txt";
        String PATH_DECODED = "homeworks/huffman/decoded.txt";

        //-------------------------------------

        Map<Character, Integer> letterMap = letter2map(PATH_LETTER);
        HuffmanList letterList = map2list(letterMap);

        //-------------------------------------

        HuffmanTree letterTree = new HuffmanTree(letterList);

        //-------------------------------------

        letterList = map2list(letterMap);
        while (letterList.count() > 0){
            HuffmanNode node = letterList.popHead();
            String symbol = node.symbol;

            System.out.println("~~ encoded " + symbol + " is " + letterTree.encode(symbol));
        }

        //-------------------------------------

        String source = file2string(PATH_SOURCE);
        String encodedSource = letterTree.encode(source);
        System.out.println(encodedSource);
        writeFile(PATH_ENCODED, encodedSource);

        //-------------------------------------

        String encoded = file2string(PATH_ENCODED);
        String decoded = letterTree.decode(encoded);
        System.out.println(decoded);
        writeFile(PATH_DECODED, decoded);

        //-------------------------------------
    }
}
