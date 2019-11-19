# !/bin/python
# -*- encoding:UTF-8 -*-


# 有向无环图DAG的拓扑排序，一般是AOV(Activate On Vertex)网，也就是顶点标识活动的图
# 记得之前看算法这本书，讲的是比较复杂，三重循环，然后又反向逆序的。好复杂。
# 对应的应该是只记录了出度没有记录入度的情况吧。想一想怎么能够将没有入度的转化为有入度的图
# 遍历一遍应该就可以了啊
# 这里对问题增加了一些难度，更加贴近实际问题
# 比如是装修，有多个环节，每个环节可能依赖一些前置环节的完成，同时呢，他们自己也有自己的最早可以开始的时间，两者应该选取比较晚的。
# 同时每个环节都有自己的耗时时间
# 如何处理和建模比较合适呢


import Queue
import sys

sys.path.append("..")

from base.direct_graph_in_degree import DirectGraphDegree

dgd = DirectGraphDegree(5)
dgd.add_edge(0, 1)
dgd.add_edge(0, 2)
dgd.add_edge(1, 2)
dgd.add_edge(0, 3)
# dgd.add_edge(1, 4)
dgd.add_edge(3, 4)
# dgd.add_edge(3, 4)

dgd.points[0].st = 0
dgd.points[0].us_d = 2

dgd.points[1].st = 5
dgd.points[1].us_d = 5

dgd.points[2].st = 3
dgd.points[2].us_d = 2

dgd.points[3].st = 1
dgd.points[3].us_d = 2


dgd.points[4].st = 3
dgd.points[4].us_d = 2


def find_toplogic(gdg):
    num = gdg.num
    stack = Queue.PriorityQueue()

    list = []
    for i in range(num):
        if gdg.points[i].degree_in == 0:
            stack.put(gdg.points[i])
    while not stack.empty():
        index = stack.get().mark
        list.append(index)
        parent_end = gdg.points[index].st + gdg.points[index].us_d
        for child in gdg.adj(index):
            gdg.points[child].degree_in -= 1
            if parent_end > gdg.points[child].st:
                gdg.points[child].st = parent_end
            if gdg.points[child].degree_in == 0:
                stack.put(gdg.points[child])
    print list
    for ele in gdg.points:
        print ele


find_toplogic(dgd)
# for ele in dgd.points:
#     print ele
