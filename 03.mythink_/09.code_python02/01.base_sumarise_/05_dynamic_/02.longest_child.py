#!/bin/python
# -*- encoding:UTF-8 -*-

# 连续最长公共子序列问题，分治法是没有办法实现的，这种觉得就像只能使用穷举的方式一样
# 动态规划的解决思想是:都从头开始，
# 1. 如果第一个字符重合，
#   1.1 将该字符加入结果集，下面两个字符长度各减一，求结果，得到r1
#   1.2 错位找重合的得到r2,r3
#   1.3 r1,r2,r3 求最长的。
#
# 2. 如果第一个字符不重合，
#   2.1 将已经得到的结果集和现有的最长子序列比较看是否需要取代，
#   2.2 分别比较(a1-1 和 a2)(a1 和 a2-1)的公共子序列，如果有重合的，开始1
# 3. 边界条件，有一个子序列为0
# 4. 优化，如果某个子序列的长度小于当前已知的最大的序列的长度，那么可以提前结束
# 5. 结果集当中只保留最长的结果集即可

# 动态规划四要素
# 1. 阶段，就是两个字符串不断变化的过程，问题的规模也因此不断变化
# 2. 状态，子字符串，现有的最长，正在添加的
# 3. 最优子结构，如上
# 4. 状态转移方程式，感觉这个判断的条件更多一些，也更复杂一些
# 5. 边界条件:有一个子长度为0

# 存在的问题，耗时太久，子问题重复了求解

import time
from copy import deepcopy

def long_find(str01, str02, cur_child, cur_long):
    if len(str01) == 0 or len(str02) == 0:
        if len(cur_child) > len(cur_long):
            cur_long = "".join(cur_child)
        return cur_long
    f01 = str01[0:1]
    f02 = str02[0:1]
    if f01 == f02:
        cur_child02=[]
        cur_child03=[]

        cur_child.append(f01)
        r1 = long_find(str01[1:], str02[1:], cur_child, cur_long)
        r2 = long_find(str01, str02[1:], cur_child02, cur_long)
        r3 = long_find(str01[1:], str02, cur_child03, cur_long)
        if len(r1) >= len(r2) and len(r1) >= len(r3):
            return r1
        if len(r2) >= len(r1) and len(r2) >= len(r3):
            return r2
        if len(r3) >= len(r2) and len(r3) >= len(r1):
            return r3

    else:
        if len(cur_child) > len(cur_long):
            cur_long = "".join(cur_child)
        cur_child = []
        long01 = long_find(str01[1:], str02, cur_child, cur_long)
        cur_child = []
        long02 = long_find(str01, str02[1:], cur_child, cur_long)
        # print long02
        if len(long01) > len(long02):
            return long01
        else:
            return long02


def find_longest_child(str01, str02):
    cur_long = ''
    cur_child = []
    return long_find(str01, str02, cur_child, cur_long)


# list=["a","b"]
# print list[2:]

cur_time = time.time()
s1 = "fyutryutrrwrcfr"
s2 = "yutrrwrcf"
print 'start...'
rs = find_longest_child(s1, s2)
print rs
end_time = time.time()
print 'using time ...'
print (end_time - cur_time)
list=[]
print "".join(list)
