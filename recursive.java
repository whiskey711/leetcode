import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.spi.RegisterableService;

public class recursive {
    public static void main(String[] args){
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<Integer> subls = new ArrayList<>();
        int[] nums = new int[]{1,2,2};
        for (int i=0; i<=nums.length; i++){
            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            subset(list, subls, nums, i, 0);
            list.addAll(ans);
        }
        char[][] board = new char[][]{
            new char[]{'O','O','O','O','X','X'},
            new char[]{'O','O','O','O','O','O'},
            new char[]{'O','X','O','X','O','O'},
            new char[]{'O','X','O','O','X','O'},
            new char[]{'O','X','O','X','O','O'},
            new char[]{'O','X','O','O','O','O'}
        };
        char[][] test = new char[][]{
            new char[]{'X','O','X','O'},
            new char[]{'X','O','O','X'},
            new char[]{'X','O','X','O'}
        };
        int[][] grid = new int[][]{
            new int[]{1,0,0},
            new int[]{1,0,1},
            new int[]{0,1,1}
        };
        System.out.println(islandsArea(grid));
    }
    static void flip(char[][] board){
        boolean[][] visit = new boolean[board.length][board[0].length];
        for (int x=1; x<board.length-1; x++){
            System.out.println();
            for (int y=1; y<board[x].length-1; y++){
                /*
                if (x==3 && y==3){
                    regionSrh(board, x, y, visit, alive);
                }
                */
                if (board[x][y] != 'X' && !regionSrh(board, x, y, visit)){
                    board[x][y] = 'X';
                }
                System.out.print(board[x][y]);
            }
        }
        
    }
    static boolean regionSrh(char[][] board, int x, int y, boolean[][] visit){
        if (visit[x][y] || board[x][y] == 'X'){
            return false;
        }
        if (x <= 0 || y <= 0 || x >= board.length-1 || y >= board[0].length-1){
            return true;
        }
        visit[x][y] = true;
        if (regionSrh(board, x-1, y, visit)){

        }else if (regionSrh(board, x+1, y, visit)){

        }else if (regionSrh(board, x, y-1, visit)){

        }else if (regionSrh(board, x, y+1, visit)){

        }
        return false;
    }
    static int islandsNum(int[][] grid){
        int num = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int x=0; x<grid.length; x++){
            for (int y=0; y<grid[x].length; y++){
                if (visited[x][y]){
                    continue;
                }
                if (grid[x][y] == 0){
                    continue;
                }
                num++;
                dfs(grid, x, y, visited);
            }
        }
        return num;
    }
    static void dfs(int[][] grid, int x, int y, boolean[][] visited){
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length){
            return;
        }
        if (visited[x][y] || grid[x][y] == 0){
            return;
        }
        visited[x][y] = true;
        dfs(grid, x-1, y, visited);
        dfs(grid, x+1, y, visited);
        dfs(grid, x, y-1, visited);
        dfs(grid, x, y+1, visited);
    }
    static int islandsArea(int[][] grid){
        boolean[][] visit = new boolean[grid.length][grid[0].length];
        int max = 0;
        for (int x=0; x<grid.length; x++){
            for (int y=0; y<grid[x].length; y++){
                if (visit[x][y] || grid[x][y] == 0){
                    continue;
                }
                int area = bfsOrdfs(grid, x, y, visit);
                max = Math.max(max, area);
            } 
        }
        return max;
    }
    static int bfsOrdfs(int[][] grid, int x, int y, boolean[][] visit){
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length){
            return 0;
        }
        if (visit[x][y] || grid[x][y] == 0){
            return 0;
        }
        visit[x][y] = true;
        int up = bfsOrdfs(grid, x-1, y, visit);
        int down = bfsOrdfs(grid, x+1, y, visit);
        int left = bfsOrdfs(grid, x, y-1, visit);
        int right = bfsOrdfs(grid, x, y+1, visit);
        return up + down + left + right + 1;
    }
    static void subset(List<List<Integer>> ans, List<Integer> sub, int[] nums, int k, int start){
        if (sub.size() == k){
            ans.add(new ArrayList<Integer>(sub));
            return;
        }
        for (int i=start; i<nums.length; i++){
            if (i > start && nums[i] == nums[i-1]) continue;
            sub.add(nums[i]);
            subset(ans, sub, nums, k, i+1);
            sub.remove(sub.size()-1);
        }
    }
    static void permuteUniq(int n, HashMap<Integer, Integer> dict, List<Integer> sub, 
    List<List<Integer>> ans){
        if (sub.size() == n){
            ans.add(new ArrayList<Integer>(sub));
            return;
        }
        for (Map.Entry<Integer, Integer> element : dict.entrySet()){
            int key = element.getKey();
            int value = element.getValue();
            if (value <= 0){
                continue;
            }
            sub.add(key);
            dict.put(key, value-1);
            permuteUniq(n, dict, sub, ans);
            dict.put(key, dict.get(key)+1);
            sub.remove(sub.size()-1);
        }
    }
    static void permute(int[] nums, List<Integer> sub, List<List<Integer>> ans, boolean[] used){
        if (nums.length == sub.size()){
            ans.add(new ArrayList<Integer>(sub));
            return;
        }
        for (int i=0; i<nums.length; i++){
            if (used[i]){
                continue;
            }
            sub.add(nums[i]);
            used[i] = true;
            permute(nums, sub, ans, used);
            used[i] = false;
            sub.remove(sub.size()-1); 
        }
    }
    static void combSum2(List<List<Integer>> ls, List<Integer> sub, int target, int sum, int start, int[] nums){
        if (sum > target){
            return;
        }
        if (sum == target){
            ls.add(new ArrayList<Integer>(sub));
            return;
        }
        for (int i=start; i<nums.length; i++){
            if (start < i && nums[i-1] == nums[i]){
                continue;
            }
            sub.add(nums[i]);
            combSum2(ls, sub, target, sum+nums[i], i+1, nums);
            sub.remove(sub.size()-1);
        }
    }
}
