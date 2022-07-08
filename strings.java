public class strings {
    public static void main(String[] args){
        System.out.println(reverseWords(" hello word "));
    }
    static String reverseWords(String s){
        String[] sa = s.split(" ");
        String ans = "";
        for (int i=sa.length-1; i>=0; i--){
            if (sa[i].isBlank()){
                if (i!=0 || i!=sa.length-1) ans += " ";
            }else{
                ans += sa[i];
            }
        }
        return ans;
    }
    static String replaceSpace(String s){
        char[] ca = s.toCharArray();
        String ans = "";
        for (int i=0; i<ca.length; i++){
            if (Character.isWhitespace(ca[i])){
                ans += "%20";   
            }else{
                ans += ca[i];
            }
        }
        return ans;
    }
    static String reverseStrWithK(String s, int k){
        char[] cs = s.toCharArray();
        for (int i=0; i<cs.length; i+=2*k){
            int left = i;
            int right = cs.length > i+k ? i+k-1 : cs.length-1;
            while (left < right){
                char temp = cs[left];
                cs[left] = cs[right];
                cs[right] = temp;
                left++;
                right--;   
            }
        }
        return new String(cs);
    }
    static void reverseString(char[] s){
        int left = 0;
        int right = s.length-1;
        while (left < right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
        System.out.println(s.toString());
    }
}
