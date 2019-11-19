# !/bin/python
# -*- encoding:UTF-8 -*-
# 铺瓷砖、铺地板、在电路板上嵌入芯片等问题，都属于一类问题，基本上可以描述为在一个 N × M 的平面空间中摆放一些形状固定的物品，
# 要求覆盖整个平面空间，问有多少种摆放方法。在某些情况下还会增加一点难度，比如在平面上标记一些位置为“坏”点，摆放物品时要避开这些位置等。
# 这类问题传统上是使用状态压缩的动态规划方法解题，因状态递推关系复杂，常用深度优先搜索（DSF）辅助状态的遍历。
# 近些年也流行使用轮廓线动态规划方法求解，
# 但其本质上还是状态压缩。这一课我们就用传统的状态压缩动态规划方法解决铺瓷砖问题

# 这个问题，上来直接干蒙了。。完全不知道如何下手了，动态规划的方案感觉也很难，尤其是在问题逐渐变得复杂的时候
# 所以就直接看这个题目的操作来做了，算是看懂了，而且很具备通用性，很厉害，自己尝试实现以下
# 这里面有几个点是需要注意的，bit_mask的应用很重要，可以简化整个过程
# 使用迭代的方式进行遍历，这样逻辑相对来说更加简洁了很多，而且判断等要方便很多，如果用循环的话可能大大加大难度，
#


# 总结一下这个方案吧，使用的是状态压缩的方式，


# a = 16 - 1
# print (~7 & a)


def deep_search(cur_col, n, i, d, pre, cur_stat):
    bit_mask = [0x00000001, 0x00000002, 0x00000004, 0x00000008, 0x00000010, 0x00000020, 0x00000040, 0x00000080]
    if cur_col == n:
        d[i][cur_stat] += d[i - 1][pre]
        return
    if bit_mask[cur_col] & cur_stat > 0:
        deep_search(cur_col + 1, n, i, d, pre, cur_stat)
    if bit_mask[cur_col] & cur_stat == 0:
        deep_search(cur_col + 1, n, i, d, pre, cur_stat)
    if cur_col < n - 1 and bit_mask[cur_col] & cur_stat == 0 and bit_mask[cur_col + 1] & cur_stat == 0:
        deep_search(cur_col + 2, n, i, d, pre, cur_stat | bit_mask[cur_col] | bit_mask[cur_col + 1])


def get_way_num(n, m):
    SS = (1 << n) - 1
    MAX_N = SS + 1
    d = [[0] * MAX_N for i in range(m + 1)]
    d[0][SS] = 1
    for i in range(1, m + 1):

        for state in range(0, MAX_N):
            if d[i - 1][state] > 0:
                deep_search(0, n, i, d, state, ~state & SS)
    print d[m][SS]

get_way_num(3,8)
