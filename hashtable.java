import java.util.HashMap;
import java.util.HashSet;

public class hashtable {
    public static void main(String[] args){
        System.out.println(isAnagram("atr", "rat"));
    }
    static boolean isAnagram(String s, String t){
        HashMap<Character, Integer> letters = new HashMap<>();
        char[] sChar = s.toCharArray();
        for (char c : sChar){
            if (letters.containsKey(c)){
                letters.put(c, letters.get(c)+1);
            }else{
                letters.put(c, 1);
            }
        }
        char[] tChar = t.toCharArray();
        for (char c : tChar){
            if (letters.containsKey(c)){
                letters.put(c, letters.get(c)-1);
            }else{
                return false;
            }
        }
        for (int val : letters.values()) {
            if (val < 0 || val > 0) return false;
        }
        return true;
    }
}
