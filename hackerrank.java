import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.rmi.CORBA.Tie;

import java.util.Iterator;

public class hackerrank{
    public static void main(String[] args){
        List<Integer> arr = Arrays.asList(1,1,3,2,1);
        int[] ans = new int[4];
        for (int i=0; i<arr.size(); i++){
            ans[arr.get(i)] ++;
        }

        System.out.println(Arrays.asList(ans).toString());
    }
    static int diagonalDifference(List<List<Integer>> arr) {
    // Write your code here
        int leftDiag = 0;
        int rightDiag = 0;
        for (int i=0; i<arr.size(); i++){
            for (int j=0; j<arr.get(i).size(); j++){
                if (i == j) leftDiag += arr.get(i).get(j);
                if (i+j == arr.size()-1) rightDiag += arr.get(i).get(j);
            }
        }
        int ans = Math.abs(leftDiag - rightDiag);
        return ans;
    }
    static String timeConvert(String s){
        String suffix = s.substring(8);
        String time = s.substring(0,8);
        String[] strs = time.split(":");
        int hour = Integer.parseInt(strs[0]);
        int min = Integer.parseInt(strs[1]);
        int sec = Integer.parseInt(strs[2]);
        if (suffix.equals("PM") && hour < 12){
            hour += 12;
        }else if (suffix.equals("AM") && hour == 12){
            hour -= 12;
        }
        String hours = Integer.toString(hour);
        String mins = Integer.toString(min);
        String secs = Integer.toString(sec);
        if (hour < 10) hours = "0" + hours;
        if (min < 10) mins = "0" + mins;
        if (sec < 10) secs = "0" + secs;
        String ans = hours+":"+mins+":"+secs;
        return ans;
    }
    static void plusMinus(List<Integer> arr) {
        // Write your code here
        int positive = 0;
        int negative = 0;
        int zeros = 0;
        for (int num : arr){
            if (num > 0) positive++;
            if (num < 0) negative++;
            if (num == 0) zeros++;
        }
        double size = arr.size();
        double p = positive/size;
        double n = negative/size;
        double z = zeros/size;
        System.out.println(p);
        System.out.println(n);
        System.out.println(z);
    }
}