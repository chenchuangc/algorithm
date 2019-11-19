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

# 这里想对上面的问题进行逆向的改造，使用逆向的方式，这样的话占用的内存更少，计算的方式更少

# 动态规划优于穷举的原因是因为他不需要穷举，只需要穷举子问题的一部分即可求解，所以才可以是用逆向的方式进行推算



import time


def find_longest_child(str01, str02):
    table = [[""] * (len(str02) + 1) for i in range(len(str01) + 1)]
    len01 = len(str01) + 1
    len02 = len(str02) + 1

    for i01 in range(1, len01):
        for i02 in range(1, len02):
            index01 = i01 - 1
            index02 = i02 - 1
            cur_str01 = str01[index01:index01 + 1]
            cur_str02 = str01[index02:index02 + 1]
            if cur_str01 == cur_str02:
                table[i01][i02] = table[i01 - 1][i02 - 1] + cur_str01
            else:
                temp01 = table[i01 - 1][i02]
                temp02 = table[i01][i02 - 1]
                if len(temp01) > len(temp02):
                    table[i01][i02] = temp01
                else:
                    table[i01][i02] = temp02
    # print table[len01 - 1][len02 - 1]
    return table[len01 - 1][len02 - 1]


list = ["a", "b", "c"]
print list[1:2]

cur_time = time.time()
s1 = "fyutrwerwryutrrwrcfrgbdmler"
s2 = "yutrrwrcsadfasdfasfasdfafrgbdmler"
rs = find_longest_child(s1, s2)
print rs
end_time = time.time()
print 'using time ...'
print (end_time - cur_time)
