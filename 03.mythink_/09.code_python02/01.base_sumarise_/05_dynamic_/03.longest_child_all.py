#!/bin/python
# -*- encoding:UTF-8 -*-

# 这个最长子序列是可以间断的，并不是完全连续的
# 阶段，状态，最优子结构，状态转移方程
# 阶段就是两个子字符串处于不同的长度，求两个子的公共部分
# 状态有，两个子字符串，已经匹配的
# 迭代的终止条件，有一个长度为0

def get_all_longest(str01, str02):
    if len(str01) == 0 or len(str02) == 0:
        return ""
    # len01 = len(str01)
    # len02 = len(str02)
    char01 = str01[0:1]
    char02 = str02[0:1]
    if char01 == char02:
        return char01 + get_all_longest(str01[1:], str02[1:])
    else:
        s1 = get_all_longest(str01, str02[1:])
        s2 = get_all_longest(str01[1:], str02)
        if len(s1) > len(s2):
            return s1
        else:
            return s2


s1 = "dabcdefa"
s2 = "dagdef"
res = get_all_longest(s1, s2)
print res
