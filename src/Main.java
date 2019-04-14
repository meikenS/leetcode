import java.util.*;
import java.util.logging.FileHandler;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        int[] b = {1, 2, 10, 12};
        System.out.println("solution" + twoSum(b, 11)[0] + twoSum(b, 11)[1]);
        System.out.println("solution" + lengthOfLongestSubstring("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}"));
        int[] ls = new int[]{1, 0, 1, 2, -1};
        System.out.printf("" + threeSum1(ls));
    }

    private static int[] twoSum(int[] nums, int target) throws IllegalAccessException {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer idx = map.get(target - nums[i]);
            if (idx != null)
                return new int[]{idx, i};
            map.put(nums[i], i);
        }
        throw new IllegalAccessException("no solution");
    }

    //给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
    public static int lengthOfLongestSubstring(String s) {
        int length = s.length();
        int subSlength1 = 0;
        int sum = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length + 1; j++) {
                if (noRepeat(s, i, j)) sum = Math.max(sum, j - i);
            }
        }
        return sum;
    }

    public static boolean noRepeat(String s, int start, int end) {
        List<Character> subStr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (subStr.contains(ch)) return false;
            subStr.add(ch);
        }
        return true;
    }

    //给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
    public int lengthOfLongestSubstring2(String s) {
        StringBuilder sb = new StringBuilder();
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            Character a = s.charAt(i);
            if (sb.lastIndexOf(a.toString()) != -1) {
                if (sb.length() > maxLength) {
                    //更新最大值
                    maxLength = sb.length();
                }
                //更新结果集，不可直接清空，只能截取
                sb = new StringBuilder(sb.substring(sb.lastIndexOf(a.toString()) + 1, sb.length()));
            }
            //添加元素
            sb.append(a);
        }

        //最后判断
        if (maxLength < sb.length()) {
            maxLength = sb.length();
        }
        return maxLength;
    }

    //给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int start = i + 1;
                int end = nums.length - 1;
                int sum = nums[start] + nums[end];
                while (start < end) {
                    if (nums[i] == -sum) {
                        list.add(Arrays.asList(nums[i], nums[start], nums[end]));
                        while (start < end && nums[start] == nums[start + 1]) start++;
                        while (start < end && nums[end] == nums[end - 1]) end--;
                        start++;end--;
                    } else if (nums[i] + sum < 0)
                        start++;
                    else
                        end--;
                }
            }
        }
        return list;
    }

    public static List<List<Integer>> threeSum1(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < num.length-2; i++) {
            if (i == 0 || num[i] != num[i-1]) {
                int lo = i+1, hi = num.length-1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        while (lo < hi && num[lo] == num[lo+1]) lo++;
                        while (lo < hi && num[hi] == num[hi-1]) hi--;
                        lo++; hi--;
                    } else if (num[lo] + num[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }
}
