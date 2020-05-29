package com.allen.origin.demo;


import java.util.Arrays;

/**
 * @author Allen Wan
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

//        Solution.ListNode n1 = new Solution.ListNode(1);
//        n1.next = new Solution.ListNode(2);
//        n1.next.next = new Solution.ListNode(3);
//        n1.next.next.next = new Solution.ListNode(4);
//        n1.next.next.next.next = new Solution.ListNode(5);
//        n1.next.next.next.next.next = new Solution.ListNode(6);
//
//        System.out.println(solution.reverseKGroup(n1, 4));

        solution.searchInsert(new int[]{1,2,4,6,7}, 3);

    }

    static class Solution {
        public int searchInsert(int[] nums, int target) {
            int N = nums.length;
            if (N == 0) {
                return 0;
            }

            if (target <= nums[0]) {
                return 0;
            }

            if (target > nums[N-1]) {
                return N;
            }

            return binarySearch(nums, target, 0, nums.length-1);
        }

        private int binarySearch(int[] nums, int target, int i, int j) {
            int mid = (i+j) / 2;

            if (nums[mid] < target) {
                if (mid + 1 == j) {
                    return j;
                }
                return binarySearch(nums, target, mid+1, j);
            } else if (target < nums[mid]) {
                if (mid - 1 == i) {
                    return nums[i] == target ? i : mid;
                }
                return binarySearch(nums, target, i, mid - 1);
            } else {
                return mid;
            }
        }
    }

//    static class Solution {
//        public ListNode reverseKGroup(ListNode head, int k) {
//
//            ListNode dummyHead = new ListNode(0);
//            dummyHead.next = head;
//            ListNode tail = dummyHead;
//
//            while (tail.next != null) {
//                ListNode prev = tail.next;
//                boolean complete = reverse(tail, prev, k);
//                if (!complete) {
//                    reverse(tail, tail.next, k);
//                    tail.next = prev;
//                    break;
//                }
//                tail = prev;
//            }
//
//            return dummyHead.next;
//        }
//
//        private boolean reverse(ListNode tail, ListNode prev, int k) {
//            ListNode last = tail.next;
//            for (int i = 1; i < k; i++) {
//                last = prev.next;
//                if (last == null) {
//                    return false;
//                }
//                prev.next = last.next;
//                last.next = tail.next;
//                tail.next = last;
//            }
//            return true;
//        }
//
//        static class ListNode {
//            int val;
//            ListNode next;
//
//            ListNode(int x) {
//                val = x;
//            }
//
//            @Override
//            public String toString() {
//                return val + "";
//            }
//        }
//    }

//    static class Solution {
//        public List<List<Integer>> fourSum(int[] nums, int target) {
//            Arrays.sort(nums);
//
//            if (nums.length < 4 || (target <= 0 && nums[nums.length-1] < target)) {
//                return Collections.emptyList();
//            }
//
//            List<List<Integer>> result = new ArrayList<>();
//
//            for (int i=0; i<nums.length-3; i++) {
//                if (target >=0 && nums[i] > target) {
//                    break;
//                }
//
//                if (i > 0 && nums[i-1] == nums[i]) {
//                    continue;
//                }
//
//                List<Integer[]> tmp = threeSum(nums, i+1, target-nums[i]);
//                for (Integer[] arr : tmp) {
//                    arr[0] = nums[i];
//                    result.add(Arrays.asList(arr));
//                }
//            }
//
//            return result;
//        }
//
//        private List<Integer[]> threeSum(int[] nums, int begin, int target) {
//            if (nums.length < 3 || (target <= 0 && nums[nums.length-1] < target)) {
//                return Collections.emptyList();
//            }
//
//            List<Integer[]> result = new ArrayList<>();
//
//            for (int i=begin; i<nums.length-2; i++) {
//                if (target >=0 && nums[i] > target) {
//                    break;
//                }
//
//                if (i > begin && nums[i-1] == nums[i]) {
//                    continue;
//                }
//
//                int j=i+1, k=nums.length-1;
//                while(j < k) {
//                    int sum = nums[i] + nums[j] + nums[k];
//                    if (sum > target) {
//                        while (j < k && nums[k] == nums[--k]);
//                    } else if (sum < target) {
//                        while (j < k && nums[j] == nums[++j]);
//                    } else {
//                        result.add(new Integer[] {null, nums[i], nums[j], nums[k]});
//                        while (j < k && nums[j] == nums[++j]);
//                        while (j < k && nums[k] == nums[--k]);
//                    }
//                }
//            }
//
//            return result;
//        }
//    }

//    static class Solution {
//        public List<List<Integer>> threeSum(int[] nums) {
//            Arrays.sort(nums);
//
//            if (nums.length < 3 || nums[nums.length-1] < 0) {
//                return Collections.emptyList();
//            }
//
//
//            List<List<Integer>> result = new ArrayList<>();
//
//            for (int i=0; i<nums.length-2; i++) {
//                if (nums[i] > 0) {
//                    break;
//                }
//
//                if (i > 0 && nums[i-1] == nums[i]) {
//                    continue;
//                }
//
//                int j=i+1, k=nums.length-1;
//                while(j < k) {
//                    int sum = nums[i] + nums[j] + nums[k];
//                    if (sum > 0) {
//                        while (nums[k] == nums[--k]) {
//                            if (j == k) break;
//                        }
//                    } else if (sum < 0) {
//                        while (nums[j] == nums[++j]) {
//                            if (j == k) break;
//                        }
//                    } else {
//                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
//                        while (nums[j] == nums[++j]) {
//                            if (j == k) break;
//                        }
//                        while (nums[k] == nums[--k]) {
//                            if (j == k) break;
//                        }
//                    }
//                }
//            }
//
//            return result;
//        }
//    }

//    static class Solution {
//        private static final int one = 1;
//        private static final int ten = 10;
//        private static final int hundred = 100;
//        private static final int thousand = 1000;
//
//        public String intToRoman(int num) {
//            int state = one;
//            StringBuilder sb = new StringBuilder();
//            while (num > 0) {
//                int pop = num % 10;
//                num /= 10;
//                switch (state) {
//                    case one:
//                        sb.insert(0, oneConvert(pop));
//                        state = ten;
//                        break;
//                    case ten:
//                        sb.insert(0, tenConvert(pop));
//                        state = hundred;
//                        break;
//                    case hundred:
//                        sb.insert(0, hundredConvert(pop));
//                        state = thousand;
//                        break;
//                    case thousand:
//                        sb.insert(0, thousandConvert(pop));
//                        state = -1;
//                        break;
//                    default:
//                        break;
//                }
//            }
//
//            return sb.toString();
//        }
//
//        private String thousandConvert(int num) {
//            StringBuilder s = new StringBuilder();
//            for (int i = 1; i <= num; i++) {
//                if (i < 4) {
//                    s.insert(0, "M");
//                } else {
//                    break;
//                }
//            }
//            return s.toString();
//        }
//
//        private String hundredConvert(int num) {
//            return convert(num, "C", "CD", "D", "CM");
//        }
//
//        private String tenConvert(int num) {
//            return convert(num, "X", "XL", "L", "XC");
//        }
//
//        private String oneConvert(int num) {
//            return convert(num, "I", "IV", "V", "IX");
//        }
//
//        private String convert(int num, String a, String b, String c, String d) {
//            StringBuilder s = new StringBuilder();
//            for (int i = 1; i <= num; i++) {
//                if (i < 4) {
//                    s.insert(0, a);
//                } else if (i == 4) {
//                    s.replace(0, s.length(), b);
//                } else if (i == 5) {
//                    s.replace(0, s.length(), c);
//                } else if (i == 9) {
//                    s.replace(0, s.length(), d);
//                } else {
//                    s.append(a);
//                }
//            }
//            return s.toString();
//        }
//    }

//    static class Solution {
//        private int[] src;
//
//        public Solution(int[] nums) {
//            src = nums;
//        }
//
//        /** Resets the array to its original configuration and return it. */
//        public int[] reset() {
//            return Arrays.copyOf(src, src.length);
//        }
//
//        /** Returns a random shuffling of the array. */
//        public int[] shuffle() {
//            int[] src = reset();
//            for (int i=0; i<src.length; i++) {
//                int rand = ThreadLocalRandom.current().nextInt(src.length - 1);
//                exch(src, i ,rand);
//            }
//            return src;
//        }
//
//        private void exch(int[] arr, int i, int j) {
//            int tmp = arr[i];
//            arr[i] = arr[j];
//            arr[j] = tmp;
//        }
//    }

//    static class Solution {
//        public double myPow(double x, int n) {
//            if (n == 0) return 1;
//
//            double base = quickPow(x, Math.abs(n));
//
//            return n < 0 ? 1 /base : base;
//        }
//
//        public double quickPow(double x, int n) {
//            int N = n / 2;
//
//            if (N == 0) return x;
//
//            double y = quickPow(x, N);
//
//            return (n & 1) == 0 ? y * y : y * y * x;
//        }
//    }
//
//    private static final byte STARTED = 0;
//    private static final byte SIGNED = 1;
//    private static final byte IN_NUM = 2;
//    private static final byte END = 3;
//
//    public static int myAtoi(String str) {
//        int sign = 1;
//        long result = 0;
//
//        byte state = STARTED;
//
//        for (int i = 0; i < str.length() && state != END; i++) {
//            char c = str.charAt(i);
//            switch (state) {
//                case STARTED:
//                    state = started(c);
//
//                    if (state == SIGNED) sign = c == '+' ? 1 : -1;
//                    break;
//                case SIGNED:
//                    state = signed(c);
//                    break;
//                case IN_NUM:
//                    state = inNum(c);
//                    break;
//                default:
//                    state = END;
//            }
//
//            if (state == IN_NUM) {
//                long tmp = result * 10 + (c - '0');
//                result = sign == 1 ?
//                        Math.min(tmp, Integer.MAX_VALUE) :
//                        Math.min(tmp, 0 - (long) Integer.MIN_VALUE);
//            }
//        }
//
//        return (int) (sign * result);
//    }
//
//    private static byte inNum(char c) {
//        if (!isNum(c))
//            return END;
//        return IN_NUM;
//    }
//
//    private static byte signed(char c) {
//        if (!isNum(c))
//            return END;
//        return IN_NUM;
//    }
//
//    private static byte started(char c) {
//        if (c == ' ') return STARTED;
//        if (isNum(c)) return IN_NUM;
//        if (isSign(c)) return SIGNED;
//        return END;
//    }
//
//    private static boolean isNum(char c) {
//        return c >= '0' && c <= '9';
//    }
//
//    private static boolean isSign(char c) {
//        return c == '-' || c == '+';
//    }

//    public static int reverse(int x) {
//        int rev = 0;
//
//        while (x != 0) {
//            int pop = x % 10;
//            x /= 10;
//            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10)) return 0;
//            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE % 10)) return 0;
//            rev = rev * 10 + pop;
//        }
//
//        return rev;
//    }

//    public static String longestPalindrome(String s) {
//        if (s == null || s.length() <= 0)
//            return "";
//
//        int left = 0;
//        int maxLen = 1;
//
//        Map<Character, Integer> map = new HashMap<>();
//
//        for (int i = 0; i < s.length(); i++) {
//            if (i == 0) continue;
//
//            int len1 = centerExpand(s, i, i);
//            int len2 = centerExpand(s, i-1, i);
//
//            int len = Math.max(len1, len2);
//
//            if (len > maxLen) {
//                left = i - len / 2;
//                maxLen = len;
//            }
//        }
//
//        return s.substring(left, left + maxLen);
//    }
//
//    private static int centerExpand(String s, int l, int r) {
//        char L = s.charAt(l), R = s.charAt(r);
//
//        while (L == R && (--l != -1 & ++r != s.length())) {
//            L = s.charAt(l);
//            R = s.charAt(r);
//        }
//        return r - l - 1;
//    }
}

//class Solution {
//    public double findMedianSortedArrays(int[] A, int[] B) {
//        int m = A.length;
//        int n = B.length;
//
//        if (m > n) {
//            return findMedianSortedArrays(B, A);
//        }
//
//        if (m == 0) {
//            int mid = n/2;
//            return (n & 1) == 1 ? B[mid] : (B[mid-1] + B[mid]) / 2.0;
//        }
//
//        int iMin = 0, iMax = m;
//        while (iMin <= iMax) {
//            int aj = (iMin + iMax) / 2;
//            int bj = (m + n + 1)/2 - aj;
//            if (bj > 0 && aj < m && A[aj] < B[bj - 1]) {
//                iMin = aj + 1;
//            } else if (aj > 0 && bj < n && A[aj - 1] > B[bj]) {
//                iMax = aj - 1;
//            } else {
//                int LeftMax = 0;
//                if (aj == 0) {
//                    LeftMax = B[bj - 1];
//                } else if (bj == 0) {
//                    LeftMax = A[aj - 1];
//                } else {
//                    LeftMax = Math.max(A[aj - 1], B[bj - 1]);
//                }
//
//                boolean isOdd = isOdd(aj + bj);
//                if (isOdd) {
//                    return LeftMax;
//                }
//
//                int rightMin = 0;
//                if (aj == m) {
//                    rightMin = B[bj];
//                } else if (bj == n) {
//                    rightMin = A[aj];
//                } else {
//                    rightMin = Math.min(A[aj], B[bj]);
//                }
//
//                return (LeftMax + rightMin) / 2.0;
//            }
//        }
//
//
//        return 0.0;
//    }
//
//    private boolean isOdd(int num) {
//        return (num & 1) == 1;
//    }
//}

//class Solution {
//    public int trap(int[] height) {
//        if (height == null || height.length == 0) {
//            return 0;
//        }
//
//        int result = 0;
//        int l = 0;
//        int r = height.length - 1;
//        while (l < r) {
//            int lh = height[l];
//            int rh = height[r];
//            if (lh <= rh) {
//                for (l = l + 1; l < r; l++) {
//                    int ih = height[l];
//                    if (lh < ih) {
//                        break;
//                    } else {
//                        result += lh - ih;
//                    }
//                }
//            } else {
//                for (r = r - 1; r > l; r--) {
//                    int ih = height[r];
//                    if (ih > rh) {
//                        break;
//                    } else {
//                        result += rh - ih;
//                    }
//                }
//            }
//        }
//
//        return result;
//    }
//}

//class Solution {
//    public boolean isMatch(String s, String p) {
//        int sn = s.length(), pn = p.length();
//
//        boolean[][] dp = new boolean[pn + 1][sn + 1];
//        dp[0][0] = true;
//
//        for (int pi = 1; pi <= pn; pi++) {
//            char pc = p.charAt(pi - 1);
//
//            if (pc == '*') {
//                dp[pi][0] = dp[pi - 2][0];
//                for (int si = 1; si <= sn; si++) {
//                    dp[pi][si] = /* _*匹配0个 */dp[pi - 2][si] || /* _*匹配多个 */ (dp[pi][si - 1] && isMatch(s.charAt(si - 1), p.charAt(pi - 2)));
//                }
//            } else {
//                // dp[pi][0] = false 默认false, 不需要显示赋值
//                for (int si = 1; si <= sn; si++) {
//                    dp[pi][si] = dp[pi - 1][si - 1] && isMatch(s.charAt(si - 1), pc);
//                }
//            }
//        }
//
//        return dp[pn][sn];
//    }
//
//    private boolean isMatch(char s, char p) {
//        return p == '.' || p == s;
//    }
//}

//class Solution {
//    public boolean isMatch(String s, String p) {
//        Objects.requireNonNull(s);
//        Objects.requireNonNull(p);
//
//        int sn = s.length(), pn = p.length();
//
//        boolean[][] dp = new boolean[sn + 1][pn + 1];
//        dp[0][0] = true;
//        for (int pi = 0; pi < pn; pi++) {
//            char pc = p.charAt(pi);
//            if (pc == '*') {
//                boolean prevMatched = false;
//                for (int si = 0; si <= sn; si++) {
//                    if (!dp[si][pi] && !prevMatched)
//                        continue;
//                    prevMatched = true;
//                    dp[si][pi + 1] = true;
//                }
//            } else {
//                for (int si = 0; si < sn; si++) {
//                    if (pc == '?' || s.charAt(si) == pc) {
//                        dp[si + 1][pi + 1] = dp[si][pi];
//                    }
//                }
//            }
//        }
//
//        return dp[sn][pn];
//    }
//}

//class Solution {
//    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
//        int[] result = new int[requirements.length];
//
//        int[][] total = new int[increase[0].length][increase.length + 1];
//        total[0][1] = increase[0][0];
//        total[1][1] = increase[0][1];
//        total[2][1] = increase[0][2];
//        for (int i = 0; i < increase.length; i++) {
//            total[0][i + 1] = total[0][i] + increase[i][0];
//            total[1][i + 1] = total[1][i] + increase[i][1];
//            total[2][i + 1] = total[2][i] + increase[i][2];
//        }
//
//        for (int i = 0; i < requirements.length; i++) {
//            result[i] = search(total, requirements[i]);
//        }
//
//        return result;
//    }
//
//    private int search(int[][] total, int[] req) {
//        int c = binarySearch(total[0], req[0], 0, total[0].length);
//        int r = binarySearch(total[1], req[1], 0, total[0].length);
//        int h = binarySearch(total[2], req[2], 0, total[0].length);
//        return (c < 0 || r < 0 || h < 0) ? -1 : Math.max(c, Math.max(r, h));
//    }
//
//    private int binarySearch(int[] arr, int key, int lo, int hi) {
//        if (lo > hi) {
//            return -1;
//        } else if (lo == hi) {
//            return key <= arr[lo] ? lo : -1;
//        }
//
//        int mid = (lo + hi) / 2;
//
//        if (lo < mid) {
//            if (arr[mid - 1] < key && key <= arr[mid]) {
//                return mid;
//            } else if (key <= arr[mid]) {
//                return binarySearch(arr, key, lo, mid - 1);
//            } else if (key > arr[mid]) {
//                return binarySearch(arr, key, mid, hi);
//            }
//        } else {
//            if (key <= arr[mid]) {
//                return mid;
//            } else if (key > arr[mid]) {
//                return binarySearch(arr, key, mid, hi);
//            }
//        }
//        return -1;
//    }
//}

//class Solution {
//    public int minPathSum(int[][] grid) {
//        int xLen = grid.length;
//        int yLen = grid[0].length;
//
//        int[][] dp = new int[xLen][yLen];
//        for (int x = 0; x < xLen; x++) {
//            for (int y = 0; y < yLen; y++) {
//                int c = grid[x][y];
//                if (x == 0 && y == 0) {
//                    dp[x][y] = c;
//                } else if (x == 0) {
//                    dp[x][y] = dp[x][y - 1] + c;
//                } else if (y == 0) {
//                    dp[x][y] = dp[x - 1][y] + c;
//                } else {
//                    dp[x][y] = Math.min(dp[x - 1][y], dp[x][y - 1]) + c;
//                }
//            }
//        }
//
//        return dp[xLen - 1][yLen - 1];
//    }
//}

//class Solution {
//    public int maxProduct(int[] nums) {
//        if (nums == null || nums.length == 0) {
//            return 0;
//        }
//
//        int len = nums.length;
//        int[][] dp = new int[len][2];
//        dp[0][0] = nums[0];
//        dp[0][1] = nums[0];
//        for (int i = 1; i < len; i++) {
//            int c = nums[i];
//
//            dp[i][0] = Math.max(Math.max(dp[i - 1][0] * c, dp[i - 1][1] * c), c);
//            dp[i][1] = Math.min(Math.min(dp[i - 1][0] * c, dp[i - 1][1] * c), c);
//        }
//
//        int max = dp[0][0];
//        for (int i = 0; i < len; i++) {
//            max = Math.max(dp[i][0], max);
//        }
//        return max;
//    }
//}


//    public static double findMedianSortedArrays(int[] A, int[] B) {
//        int m = A.length, n = B.length;
//        if (m > n) {
//            return findMedianSortedArrays(B, A);
//        }
//
//        if (m == 0) {
//            return (n & 1) == 1 ? B[n / 2] : (B[n / 2] + B[n / 2 - 1]) / 2.0;
//        }
//
//        int iMin = 0, iMax = m;
//
//        while (iMin <= iMax) {
//            int i = (iMin + iMax) / 2;
//            // 奇数情况：i + j = (m - i) + (n - j) + 1 <==> j = (m + n + 1)/2 - i
//            // 偶数请假：i + j = (m - i) + (n - j) <==> j = (m + n)/2 - i
//            // 因为 m + n 为偶数，所以 (m + n)/2 = (m + n + 1)/2
//            int j = (m + n + 1) / 2 - i;
//            // 二分查找法，找i
//            if (j != 0 && i != m && A[i] < B[j - 1]) { // 在右半边
//                iMin = i + 1;
//            } else if (i != 0 && j != n && A[i - 1] > B[j]) { // 在左半边
//                iMax = i - 1;
//            } else { // i是合适值
//                int maxLeft = 0;
//                if (i == 0) maxLeft = B[j - 1];
//                else if (j == 0) maxLeft = A[i - 1];
//                else maxLeft = Math.max(A[i - 1], B[j - 1]);
//
//                if (((m + n) & 1) == 1) return maxLeft; // 奇数，直接返回
//
//                int minRight = 0;
//                if (i == m) minRight = B[j];
//                else if (j == n) minRight = A[i];
//                else minRight = Math.min(A[i], B[j]);
//
//                return (maxLeft + minRight) / 2.0;
//            }
//        }
//
//        return 0.0;
//    }
//}

//    public static double findMedianSortedArrays(int[] arr1, int[] arr2) {
//        int len = (arr1.length + arr2.length);
//        int end = len / 2 + 1;
//        int c1 = 0, c2 = 0;
//
//        int[] sortedArr = new int[end];
//
//        for (int i = 0; i < end; i++) {
//            int val1 = getI(arr1, c1);
//            int val2 = getI(arr2, c2);
//            int min = Math.min(val1, val2);
//
//            if (val1 == min) {
//                c1++;
//            } else {
//                c2++;
//            }
//
//            sortedArr[i] = min;
//        }
//
//        return (len & 1) == 0 ? (sortedArr[end - 1] + sortedArr[end - 2]) / 2.0 : sortedArr[end - 1];
//    }
//
//    private static int getI(int[] arr1, int i) {
//        return arr1.length >= i ? arr1[i] : Integer.MAX_VALUE;
//    }
//
//}

//    public static int lengthOfLongestSubstring(String s) {
//        if (s.length() == 0) return 0;
//
//        Map<Character, Integer> map = new HashMap<>();
//        int max = 0;
//        int left = 0;
//        for (int i = 0; i < s.length(); i++) {
//            Integer pos = map.put(s.charAt(i), i);
//            if (pos != null) {
//                left = Math.max(left, pos + 1);
//            }
//            max = Math.max(max, i - left + 1);
//        }
//        return max;
//    }
//}

//    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        int carry = 0;
//
//        ListNode result = new ListNode(0);
//        ListNode curr = result;
//
//        while (l1 != null || l2 != null) {
//            int x = l1 == null ? 0 : l1.val;
//            int y = l2 == null ? 0 : l2.val;
//
//            int val = carry + x + y;
//            carry = val / 10;
//            val = val % 10;
//
//            curr.next = new ListNode(val);
//            curr = curr.next;
//
//            if (l1 != null) l1 = l1.next;
//            if (l2 != null) l2 = l2.next;
//        }
//
//        if (carry > 0) {
//            curr.next = new ListNode(carry);
//        }
//        return result.next;
//    }
//
//
//    public static class ListNode {
//        int val;
//        ListNode next;
//
//        ListNode(int x) {
//            val = x;
//        }
//    }
