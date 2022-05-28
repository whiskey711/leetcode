import java.util.Arrays;

public class greedy {
    public static void main(String[] args){
        int[][] people = new int[][]{
            new int[]{7,0}, new int[]{4,4}, new int[]{7,1}, 
            new int[]{5,0}, new int[]{6,1}, new int[]{5,2}
        };
        System.out.println(Arrays.deepToString(reconstructQueue(people)));
    }
    static int[][] reconstructQueue(int[][] people){
        int[][] ans = new int[people.length][2];
        for (int i=1; i<people.length; i++){
            int count = people[i][1];
            for (int j=0; j<i; j++){
                if (people[i][0] <= people[j][0] && count > 0){
                    count--;
                }else if (count == 0){
                    
                }
            }
        }
        return ans;
    }
    static int maxProfit(int[] prices){
        int profit = 0;
        for (int i=1; i<prices.length; i++){
            int diff = prices[i] - prices[i-1];
            if (diff > 0){
                profit += diff;
            }
        }
        return profit;
    }
    static boolean jumpGame(int[] nums){
        int destination = nums.length-1;
        if (destination < 1){
            return true;
        }
        for (int i=destination-1; i>=0; i--){
            if (nums[i] >= destination - i){
                destination = i;
            }
        }
        if (destination == 0){
            return true;
        }else{
            return false;
        }
    }
}
