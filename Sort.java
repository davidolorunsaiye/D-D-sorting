import java.util.*;
import java.util.Collections;
import java.io.*;

class SortDnD implements Comparable<SortDnD>
{
    //initializes the variables to the keys and values of the list being sorted
    String name;
    int skill;

    public SortDnD(String name, int skill)
    {
        this.name = name;
        this.skill = skill;
    }

    @Override
    //compares each skill value to effectively sort
    public int compareTo(SortDnD o) 
    {
        
        return skill - o.skill;
    }
    @Override
    // methodd to output the sorted list as string
    public String toString()
    {
        return name + " - " + "(" + skill + ")";
    }
}
public class Sort
{
    public static void main(String [] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        boolean again = true;
        String input;
        String line;
        // reads the text file in
        Scanner in = new Scanner(new File("characters.txt"));
        HashMap<String, Integer> skills = new LinkedHashMap <String, Integer>();
        HashMap<String, HashMap<String, Integer>> all = new LinkedHashMap<String, HashMap<String, Integer>>();
        
        // reads the text file and stores it in the hashmap
        while(in.hasNext())
        {
            line = in.nextLine();

            if(line.isEmpty())
            {
                skills = new HashMap<String, Integer>();
            }
            // checks if the line contains a colon, breaks it into 2 (key and value) and stores it in the hashmap
            if(line.contains (":"))
            {
                String [] parts = line.split(":");
                //converts the keys to lower case to avoid case clashes
                skills.put(parts[0].toLowerCase(), Integer.parseInt(parts[1]));
            }
            // If the line coints "-" it is saved as a key and the entire 2nd hashmap is the value
            if(line.contains ("-"))
            {
                all.put(line, skills);
                
            }
        }
        System.out.println(all);  

        // runs the loop until "recurssion!" is entered and again becomes false
        while(again)
        {
            System.out.println("What key do you want values for?");
            // converts the input to lower case to avoid case clashes
            input = sc.nextLine().toLowerCase();
            ArrayList<SortDnD> sorted = new ArrayList<SortDnD>();
            for(String key: all.keySet())
            {
                // saves all keys and values for the user input to the arraylist if the hashmap contains that input
                if(all.get(key).containsKey(input))
                { 
                    sorted.add(new SortDnD(key, all.get(key).get(input)));
                }
                // outputs the println if the user input isn't in the hashmap
                if(!all.get(key).containsKey(input))
                {
                    System.out.println("Try a different key.");
                    break;
                }
                // ends the loop and outputs the println if the user types in "recurssion!"
                if(input.equals("recursion!"))
                {
                    System.out.println("Bye. May the D&D be with you!");
                    break;
                }  
            }
            // sorts the arraylist in ascending order
            Collections.sort(sorted);
            for(SortDnD i : sorted)
            {
                System.out.println(i);
            }
        }
    }
}