import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static long factorial(int number)
    {
        if (number <= 1)
            return 1;
        else
            return number * factorial(number - 1);
    }

    private static void nextArrange(Integer[] nums)
    {
        for (int i = nums.length - 1; i > 0; i--)
        {
            if (nums[i] > nums[i - 1])
            {
                Integer temp = nums[i - 1];
                Integer[] sorted_nums = new Integer[nums.length - i];
                boolean is_sorted = false;
                for (int j = i; j < nums.length; j++)
                {
                    if ((j + 1 == nums.length || nums[j + 1] <= temp) && !is_sorted)
                    {
                        nums[i - 1] = nums[j];
                        nums[j]  = temp;
                        is_sorted = true;
                    }
                    sorted_nums[nums.length - j - 1] = nums[j];
                }
                System.arraycopy(sorted_nums, 0, nums, i, sorted_nums.length);
                break;
            }
        }
    }

    private static void previousArrange(Integer[] nums)
    {
        for (int i = nums.length - 1; i > 0; i--)
        {
            if (nums[i] < nums[i - 1])
            {
                Integer temp = nums[i - 1];
                Integer[] sorted_nums = new Integer[nums.length - i];
                boolean is_sorted = false;
                for (int j = i; j < nums.length; j++)
                {
                    if ((j + 1 == nums.length || nums[j + 1] >= temp) && !is_sorted)
                    {
                        nums[i - 1] = nums[j];
                        nums[j]  = temp;
                        is_sorted = true;
                    }
                    sorted_nums[nums.length - j - 1] = nums[j];
                }
                System.arraycopy(sorted_nums, 0, nums, i, sorted_nums.length);
                break;
            }
        }
    }

    private static void fullPermutation(Set<Integer> set)
    {
        Integer[] nums = set.toArray(new Integer[0]);
        Arrays.sort(nums);
        long fact = factorial(nums.length);
        for (long i = 0; i < fact; i++)
        {
            for (Integer num : nums) {
                System.out.print(num);
            }
            System.out.println();
            nextArrange(nums);
        }
    }

    private static void fullPermutationWithRepeatedElem(Integer[] nums, int numbers_of_arrangements)
    {
        Arrays.sort(nums);
        for (long i = 0; i < numbers_of_arrangements; i++)
        {
            for (Integer num : nums) {
                System.out.print(num);
            }
            System.out.println();
            nextArrange(nums);
        }
    }

    public static void testForQuestion1()
    {
        Integer[] nums;

        System.out.println("--------------------Test for Question 1--------------------");
        System.out.println("----------Next Arrangement of {1, 2, 3, 4}----------");
        nums = new Integer[]{1, 2, 3, 4};
        nextArrange(nums);
        Arrays.stream(nums).forEach(System.out::println);

        System.out.println("----------Next Arrangement of {2, 1, 4, 3}----------");
        nums = new Integer[]{2, 1, 4, 3};
        nextArrange(nums);
        Arrays.stream(nums).forEach(System.out::println);

        System.out.println("----------Previous Arrangement of {4, 3, 2, 1}----------");
        nums = new Integer[]{4, 3, 2, 1};
        previousArrange(nums);
        Arrays.stream(nums).forEach(System.out::println);

        System.out.println("----------Previous Arrangement of {2, 1, 3, 4}----------");
        nums = new Integer[]{2, 1, 3, 4};
        previousArrange(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }

    public static void testForQuestion2()
    {
        System.out.println("--------------------Test for Question 2--------------------");
        System.out.println("----------Full Permutation of {1, 2, 3, 4}----------");
        fullPermutation(new HashSet<Integer>(List.of(new Integer[]{1, 2, 3, 4})));

        System.out.println("----------Full Permutation of {5, 7, 9}----------");
        fullPermutation(new HashSet<Integer>(List.of(new Integer[]{5, 7, 9})));

        System.out.println("----------Full Permutation of {7, 9, 5, 1}----------");
        fullPermutation(new HashSet<Integer>(List.of(new Integer[]{7, 9, 5, 1})));
    }

    public static void testForQuestion3()
    {
        Integer[] nums;
        System.out.println("--------------------Test for Question 3--------------------");
        System.out.println("----------Next Arrangement of {1, 4, 3, 1}----------");
        nums = new Integer[]{1, 4, 3, 1};
        nextArrange(nums);
        Arrays.stream(nums).forEach(System.out::println);

        System.out.println("----------Previous Arrangement of {1, 4, 3, 1}----------");
        nums = new Integer[]{1, 4, 3, 1};
        previousArrange(nums);
        Arrays.stream(nums).forEach(System.out::println);

        System.out.println("----------Full Permutation of {1, 1, 3, 4}----------");
        fullPermutationWithRepeatedElem(new Integer[]{1, 1, 3, 4}, 12);

        System.out.println("----------Full Permutation of {1, 1, 2, 3, 4, 4, 4, 5}----------");
        fullPermutationWithRepeatedElem(new Integer[]{1, 1, 2, 3, 4, 4, 4, 5}, 3360);
    }

    public static void main(String[] args) {
        testForQuestion1();
        testForQuestion2();
        testForQuestion3();
    }
}
