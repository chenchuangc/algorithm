#!/bin/python
# -*- encoding:UTF-8 -*-

# 最长公共子序列问题，分治法是没有办法实现的，这种觉得就像只能使用穷举的方式一样
# 动态规划的解决思想是:都从头开始，
# 1. 如果有重合的，就记录一个字符，下面接着找重合的，
#   1.1 如果下面是重合的，接着加入接着找
#   1.2 如果下面是不重合的，就将上面的结果记入结果集，清空临时结果，同时按照2来走
# 2. 如果没有重合的，分别比较(a1-1 和 a2)(a1 和 a2-1)的公共子序列，如果有重合的，开始1
# 3. 边界条件，有一个子序列为0
# 4. 优化，如果某个子序列的长度小于当前已知的最大的序列的长度，那么可以提前结束
# 5. 结果集当中只保留最长的结果集即可

# 动态规划四要素
# 1. 阶段，就是两个字符串不断变化的过程，问题的规模也因此不断变化
# 2. 状态，子字符串，现有的最长，正在添加的
# 3. 最优子结构，如上
# 4. 状态转移方程式，感觉这个判断的条件更多一些，也更复杂一些
# 5. 边界条件:有一个子长度为0

# 这里想对上面的问题进行备忘录改造，发现很难进行改造，因为子问题的返回的是整个的处理结果，
# 而不是单单的子问题的处理结果，这个样的话子问题就不是独立的，这样的话很难进行子问题的备忘
# 所以要尝试将子问题独立出来
# abcde  bbcde
# 后面发现没有办法独立出来，因为求解的时候父亲的结果可能对于结果的合并完全没有作用，所以还是子来处理更合理，
# 备忘录增加一个维度，就是已经匹配的字符串的长度，这样就能做唯一性限制了
# 加了一个三维数组作为备忘录，果然快了很多，就是对内存消耗有点大，可以组成一个map来进行存储理论上也可以，就是key需要做特殊的处理
# 这里的一个理解点就是，备忘录对子问题的界定问题，有些时候子问题的划分是很明显的，基本上一下就能看出来，
# 但是有些时候看子问题是和父亲耦合在一起的，这个时候可能需要增加一些维度，把父亲相关的一些要素也纳入考核，使那能够满足对问题的一致性求解
#


# 同时，这个问题的求解基本上也是子最后才能确定解是哪个，类似于求字符串的排列组合一样，
# 当然，这个相对来说更加复杂一些，因为这个到达的子的情况有多种，需要在父亲中对多个子再做判断，这个实际上是挺有难度的
# 所以，在思考算法实现的时候也需要注意

import time

import numpy as np


def long_find(str01, str02, cur_child, cur_long):
    global table
    len01 = len(str01)
    len02 = len(str02)
    lenc = len(cur_child)
    if table[len01][len02][lenc] != '':
        return table[len01][len02][lenc]
    if len(str01) == 0 or len(str02) == 0:
        if len(cur_child) > len(cur_long):
            cur_long = "".join(cur_child)
            table[len01][len02][lenc] = cur_long
        return cur_long
    f01 = str01[0:1]
    f02 = str02[0:1]
    if f01 == f02:
        cur_child02 = []
        cur_child03 = []

        cur_child.append(f01)
        r1 = long_find(str01[1:], str02[1:], cur_child, cur_long)
        r2 = long_find(str01, str02[1:], cur_child02, cur_long)
        r3 = long_find(str01[1:], str02, cur_child03, cur_long)
        if len(r1) >= len(r2) and len(r1) >= len(r3):
            table[len01][len02][lenc] = r1
            return r1
        if len(r2) >= len(r1) and len(r2) >= len(r3):
            table[len01][len02][lenc] = r2
            return r2
        if len(r3) >= len(r2) and len(r3) >= len(r1):
            table[len01][len02][lenc] = r3
            return r3

    #        cur_child.append(f01)
    #        r1 = long_find(str01[1:], str02[1:], cur_child, cur_long)
    #        table[len01][len02][lenc] = r1
    #        return r1
    else:
        if len(cur_child) > len(cur_long):
            cur_long = "".join(cur_child)
        cur_child = []
        long01 = long_find(str01[1:], str02, cur_child, cur_long)
        cur_child = []
        long02 = long_find(str01, str02[1:], cur_child, cur_long)
        # print long01
        if len(long01) > len(long02):
            table[len01][len02][lenc] = long01
            return long01
        else:
            table[len01][len02][lenc] = long02
            return long02


def find_longest_child(str01, str02):
    global table
    table = np.zeros((len(str01) + 1, len(str02) + 1, len(str02) + 1), dtype=np.str)
    # print table
    # print "a"+table[0][0][0]+"k"

    # table = [["a"] * (len(str02) + 1) for i in range(len(str01) + 1)]
    cur_long = ''
    cur_child = []
    return long_find(str01, str02, cur_child, cur_long)


cur_time = time.time()
s1 = "fyutryutrrwrcfr"
s2 = "yutrrwrcf"
print 'start...'
rs = find_longest_child(s1, s2)
print rs
end_time = time.time()
print 'using time ...'
print (end_time - cur_time)

# list=["a","b"]
# print list[2:]
#
# cur_time = time.time()
# s1 = "yufayutrrwrcfrgb"
# s2 = "yutrrwrcfrgbdmlerfasfdasdfafsdfasdyufayutrrwrcfrgb"
# rs = find_longest_child(s1, s2)
# print rs
# end_time = time.time()
# print 'using time ...'
# print (end_time - cur_time)

# list=['y','','']
# print "".join(list)
