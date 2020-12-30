import java.util.LinkedList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.*;

/**
 *           Translation_Dictionary_SHELL 
 * 
 * 
 * Translation_Dictionary_SHELL: 
 * A dictionary can be represented as a map in which each word is associated 
 * with a set of all its translations into another language.  For example, 
 * in an English-Spanish dictionary, holiday might be associated with
 * {fiesta, vacaciones}; in a Spanish-English dictionary, fiesta might 
 * be associated with {holiday, party, celebration, feast}.  In a dictionary 
 * map, a word is a key and a set of its translations is a “value” associated
 * with that key.  Suppose the dictionary is implemented as a TreeMap and a
 * set of translations for each word implemented as a TreeSet.
 * 
 * OutPut:
 *  -------------------------------------------------------------------- 
English -> Spanish: 
    beautiful: bello, bonito, hermoso, 
    celeberation: celebracion, fiesta, 
    feast: fiesta, 
    holiday: fiesta, vaccaciones, 
    nice: agradable, ameno, bonito, simpatico, 
    party: fiesta, partido, velada, 
    pretty: bonito, lindo, 

 -------------------------------------------------------------------- 
Spanish -> English: 
    agradable: nice, 
    ameno: nice, 
    bello: beautiful, 
    bonito: beautiful, nice, pretty, 
    celebracion: celeberation, 
    fiesta: celeberation, feast, holiday, party, 
    hermoso: beautiful, 
    lindo: pretty, 
    partido: party, 
    simpatico: nice, 
    vaccaciones: holiday, 
    velada: party, 

 * 
*/

// Or you can cover all bases by doing:  import java.util.*;
public class Translation_Dictionary
{

    public static void main()
    {
        Map<String,Set> EnglishToSpanishMapDictionary = new TreeMap<String,Set>();
        Map reverseMap_SpanishToEnglishMapDictionary;
        String wordsAndTranslationsArray[][] = {   // English to Spanish where the first element of each row
                        {"holiday","fiesta", "vacaciones"}, // is the 'key' and the 'value' is a set containing 
                        {"celebration", "celebracion", "fiesta"}, // the rest of the words associated with it.
                        {"party", "fiesta", "partido", "velada"},
                        {"feast","fiesta"},
                        {"beautiful", "bello", "bonito", "hermoso"},
                        {"nice", "ameno", "agradable", "bonito", "simpatico"},
                        {"pretty", "bonito", "lindo"}
                    };    
                     
        // Put the words into the Map as key-English value-Spanish
        for (int i = 0; i < wordsAndTranslationsArray.length; i++) 
        {
            for (int j = 1; j < wordsAndTranslationsArray[i].length; j++)
            {
                add(EnglishToSpanishMapDictionary, wordsAndTranslationsArray[i][0],wordsAndTranslationsArray[i][j]);
            }
        }
        
        // OutPut the English->Spanish dictionary.
        printDictionary(EnglishToSpanishMapDictionary,"English","Spanish");
               
        // create the reverseMapDictionary
        reverseMap_SpanishToEnglishMapDictionary= reverse(EnglishToSpanishMapDictionary);
           
        // OutPut the Spanish->English dictionary.
        printDictionary(reverseMap_SpanishToEnglishMapDictionary,"Spanish","English");                                           
    } // main
          
    // =============================================================================
    /* precondition:  dictionary != null
                      dictionary is a Map, which associates a word (a String) with a 
                      set (a TreeSet) of its translations (Strings).
       postCondition: If a English word is among the keys, the Spanish translation should
                      be added to its set of translations. Otherwise, a new entry is 
                      created for word and it is associated with a single-element set 
                      that contains the given translation.
    */
    public static void add(Map<String, Set> dictionary, String word, String wordTranslation)
    {
        
        // =======================  YOUR CODE HERE =======================  
        if(dictionary.containsKey(word))
        {
            Set<String> temp = new TreeSet<String>();
            temp = dictionary.get(word);
            temp.add(wordTranslation);
            
            dictionary.put(word, temp);
        }
        else
        {
            Set<String> temp = new TreeSet<String>();
            temp.add(wordTranslation);
            
            dictionary.put(word, temp);
        }
    } // add
    // =============================================================================    
    /* Write a method that takes a dictionary and generates a reverse dictioanry.  A reverse
     * dictionary uses the same structure as the original dictionary, and for each pair 
     * (word, translations) in the original dictionary there is a reversed pair (translation, word) 
     * in the reverse dictionary. For example, if you can find holiday->fiesta in an English->Spanish 
     * dictionary, you should be able to find fiesta->holiday in its reverse Spanish->English dictionary. 
     * Write reverse as started below.  You can assume that the add method from Part (a) is in the same 
     * class and works as specified.
     * 
     * precondition:  dictionary != null
     *                 dictionary associates a TreeSet of translations with each word.
     * postcondition: returns a reverse dictionary with a similar structure and the following property:
     *                word2 is in the set of translations associated with word1 if and only if word1 is 
     *                in the set of translations associated with word2 in the original dictionary.
     * 
     * */
    
    public static Map reverse(Map d)
    {
      // =======================  YOUR CODE HERE =======================    
      Set<String> keys = new TreeSet<String>();
      keys = d.keySet();
      
      Map<String, Set> input = new TreeMap<String, Set>(d);
      Map<String, Set> output = new TreeMap<String, Set>();
      
      Iterator<String> itr = keys.iterator();
      while(itr.hasNext())
      {
          String current = itr.next();
          
          Set<String> words = new TreeSet<String>();
          words = input.get(current);
          
          Iterator<String> secondItr = words.iterator();
          while(secondItr.hasNext())
          {
              add(output, secondItr.next(), current);
          }
      }
      
      return output;
    } // reverse
    
    // ========================================================================================
    // OutPut the dictionary.
    public static void printDictionary(Map d,String language1,String language2)
    {    
    Iterator<String> it1 = d.keySet().iterator();   // set it1 to iterate over the keyset gathered from d.
            
        System.out.println("\n -------------------------------------------------------------------- ");
        System.out.println(language1 + " -> " + language2 + ": ");
        while (it1.hasNext()) {
            String word =  it1.next();                     // get the key word
            System.out.print("\t" + word + ": ");          // output it
            Iterator it2 = ((Set)d.get(word)).iterator();  // set it2 to iterate over the value that matches
                                                           //   the key 'word', iow the Set of translations
            while (it2.hasNext())                          //   returned.
                System.out.print(it2.next() + ", ");            

            System.out.println();
         }  // while
        
    }  // printDictionary
                       
} // Translation_Dictionary
