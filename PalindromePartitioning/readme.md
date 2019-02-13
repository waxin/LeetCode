# 131. Palindrome Partitioning

## 题目说明

Given a string *s*, partition *s* such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of *s*.

**Example:**

```
Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
```



## 解题思路

- 这道题本来是很简单的一道题，但是我还是从中收获了一些东西，特意进行记录
- 题面含义还是很好理解的，针对一个字符串，找到所有子字符串都是回文的划分。
- 我一开始思考这个问题，最先想到的是找到所有以最左边字符为首的回文串，然后对剩下的字符串递归操作。然后考虑如何找到所有回文串，这个问题还没有考虑清楚又觉得上面递归部分会产生大量重复，比如字符串"aaaaaa",对于右边的"aaa"会进行多次递归操作，于是觉得可以考虑用动态规划，重复的问题解决了，但是又没有想到比较高效的判断回文的算法。就这样觉得这个问题好复杂！
- 在纠结了一段时间后，我看了一下讨论区，结果高赞回答和我一开始的想法一致。而且判断回文就是用简单的循环而已。难道不会超时吗？
- ![思路](img/思路.png)

- 上图来自[讨论区](https://leetcode.com/problems/palindrome-partitioning/discuss/41963/Java%3A-Backtracking-solution.)
- 按照原来的思路写出代码，很顺利地通过测试

## 总结

下次遇到问题，能够想到解决方案就写出代码，不要想着一开始就写出最优代码。当然，相信随着不断练习，每次第一版本会越接近最优解。