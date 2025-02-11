import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class fdmalg {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, -1, 5};
        System.out.println(maxSumWithJumping(nums, 6));
    }

    static int maxSumWithJumping(int[] nums, int j) {
        int maxsum = Integer.MIN_VALUE;
        for (int i=0; i<nums.length; i++) {
            int temp = 0;
            for (int k=i; k<nums.length; k+=j) {
                temp += nums[k];
            }
            if (maxsum < temp) maxsum = temp;
        }
        return maxsum;
    }

    static void fizzbuzz() {
        String fizz = "Fizz";
        String buzz = "Buzz";
        for (int i = 0; i < 100; i++) {
            int f = i % 3;
            int b = i % 5;
            if (f == 0 && b == 0)
                System.out.println(fizz + buzz);
            else if (f == 0)
                System.out.println(fizz);
            else if (b == 0)
                System.out.println(buzz);
            else
                System.out.println(i);
        }
    }

    static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        for (int i = 2; i * i < n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    static int findLargest(int[] array) {
        int largest = Integer.MIN_VALUE;
        for (int i : array) {
            if (i > largest)
                largest = i;
        }
        return largest;
    }

    static int[] reverseArray(int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
        return array;
    }

    static int reorderDuplicates(int[] array) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < array.length; i++) {
            if (!set.contains(array[i])) {
                set.add(array[i]);
            }
        }
        return set.size();

        // Map<Integer, List<Integer>> map = new HashMap<>();
        // for (int i=0; i<array.length; i++) {
        // if (map.containsKey(array[i])) {
        // List<Integer> ls = map.get(array[i]);
        // ls.add(i);
        // map.replace(array[i], ls);
        // } else {
        // map.put(array[i], new ArrayList<>(i));
        // }
        // }
    }

    static void sortZerosOnes(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] == 1) {
                if (nums[right] == 0) {
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                } else {
                    right--;
                }
            } else {
                left++;
            }
        }
        for (int n : nums)
            System.out.println(n);
    }

}

class Employee {
    private int id;
    private String name;
    private double salary;
    private Department department;
    public Employee(int id, String name, double salary, Department department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public double getSalary() {
        return salary;
    }


    public void setSalary(double salary) {
        this.salary = salary;
    }


    public Department getDepartment() {
        return department;
    }


    public void setDepartment(Department department) {
        this.department = department;
    }


    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", department=" + department + "]";
    }
    
}

class Department {
    private int id;
    private String name;
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Department [name=" + name + "]";
    }
    
}