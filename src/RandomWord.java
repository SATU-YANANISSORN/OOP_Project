
import java.io.*;
import java.util.*;

public class RandomWord{
    private List<String> words = new ArrayList<>();
    
    public RandomWord(){
        try (BufferedReader br = new BufferedReader(new FileReader("word.txt"))){
            String line;
            while ((line = br.readLine()) != null){
                words.add(line.trim());
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        if(words.isEmpty()){
            System.out.println("empty file");
        }
    }

    public String randomWord(){
        Random random = new Random();
        String randomWord = words.get(random.nextInt(words.size()));
        return randomWord;
    }
}