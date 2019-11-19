# !/bin/python
# -*- encoding:UTF-8 -*-

# 二分图的最大匹配，完美匹配
# 搞了很久，发现自己把题意理解错了，
# 搜仙二分图的概念不再讲了。
# 在求解最大匹配的时候实际上使用的模型并不是你常见的图模型，而是已经划分过的两个点集，然后再这两个点集中进行处理，之前理解的有问题，
# 二分图显然是能够分成两个点集的。

# 对这个算法的实现依然有一些迷惑，为何用这种方式来模拟增广路径，能够成功模拟么，增广路径的取反是如何操作的
# 感觉对这个算法的理解需要再深入一点
# 在第一次找到非匹配路径的时候就认为是增广路径，就直接选择加入匹配，对应的就是增广路径的取反，然后本次就返回了，
# 后面的话再对下一个节点操作，试图寻找新的增广路径，这个增广路径有可能会包含之前处理过的匹配，如果包含了之前的匹配，而且发现了增广路径，
# 那么就会对增广路径进行一系列的取反操作，对应的就是 match[y]=x， 一旦在最底层的调用中返回了true,也就是发现了增广路径，那么上层的就依次取反了。
# 这个玩意儿有点牛啊
#

import sys

sys.path.append("..")

from base.graph import Graph
from two_color_try import TwoColorGraph

g = Graph(6)
g.add_edge(0, 1)
g.add_edge(0, 2)
g.add_edge(0, 3)
g.add_edge(4, 3)
g.add_edge(5, 2)
# g.add_edge(1,3)

# print g.p_num
two_color_g = TwoColorGraph(g)
print two_color_g.is_two_color

red_arr = two_color_g.red
green_arr = two_color_g.green


def find_growing_path(i, on_path, match):
    global red_arr
    global green_arr
    for j in red_arr[i]:
        if not on_path[j]:
            on_path[j] = True
            if (match[j] == -1) or find_growing_path(match[j], on_path, match):
                match[j] = i
                return True
    return False


def find_best_match():
    global red_arr
    global green_arr
    match_num = 0
    match = [-1] * len(green_arr)
    for i in range(len(red_arr)):
        # print "i:"+str(i)
        on_path = [False] * len(green_arr)
        if find_growing_path(i, on_path, match):
            match_num += 1
    print "match count:"
    print match_num
    print "match detail .."
    print match


find_best_match()
