# !/bin/python
# -*- encoding:UTF-8 -*-


# 有向无环图DAG的拓扑排序，一般是AOV(Activate On Vertex)网，也就是顶点标识活动的图
# 记得之前看算法这本书，讲的是比较复杂，三重循环，然后又反向逆序的。好复杂。
# 对应的应该是只记录了出度没有记录入度的情况吧。想一想怎么能够将没有入度的转化为有入度的图
# 遍历一遍应该就可以了啊
# 这里需要注意的一个问题是，如果
# 使用一个栈来存储信息，为什么要需用栈而不是队列呢，应该是一样的效果吧，
# 因为同一时刻被压入栈中的数据是没有先后的顺序的。所以使用栈或者队列都是可以的。
# 还有一个前提就是这个图不能是有环的

import Queue
import sys

sys.path.append("..")

from base.direct_graph_in_degree import DirectGraphDegree

#
# dgd = DirectGraphDegree(5)
# dgd.add_edge(0, 1)
# dgd.add_edge(0, 2)
# dgd.add_edge(1, 3)
# dgd.add_edge(0, 3)
# dgd.add_edge(0, 4)
# dgd.add_edge(3, 4)

dgd = DirectGraphDegree(5)
dgd.add_edge(0, 1)
dgd.add_edge(0, 2)
dgd.add_edge(1, 2)
dgd.add_edge(0, 3)


def find_toplogic(gdg):
    num = gdg.num
    stack = Queue.Queue()
    list = []
    for i in range(num):
        if gdg.points[i].degree_in == 0:
            stack.put(i)
    while not stack.empty():
        index = stack.get()
        list.append(index)
        for child in gdg.adj(index):
            gdg.points[child].degree_in -= 1
            if gdg.points[child].degree_in == 0:
                stack.put(child)
    print list


find_toplogic(dgd)
# for ele in dgd.points:
#     print ele
