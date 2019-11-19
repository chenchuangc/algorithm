# !/bin/python
# -*- encoding:UTF-8 -*-

# 再来实现以下dijkstra算法试试
# dijkstra算是AOE 网(Activate On Edge) 专用（相对于AOV网）
# 这个主要是带权重的边，所以树的结构也是多种多样的
# 他的思路就是把顶点分为两部分，一部分是s已经能够到达的部分也就是找到最短路径的点，这个是集合T，另一部是s尚未到达的地方，标识为集合M
# 开始的时候将只是将s顶点加入T，然后就是遍历s能够到达的点，都加入M，从中选择路径最短的点vi就是当前可以到达的最近的点，
# 同时，还要有一个集合d来记录s点到达各个点的最短距离（可以先将其他的点都标识为极大值），用来在将vi周围的点是否应该加入M中做判断，
# 当s-->v(i+k) 的巨鹿小于d中记录的值的时候，这个路线是相对更优的就可以加入M，重复进行图的广度优先搜索，就能找到s到各个点的最近距离了
# 如果想要记录路径的话，还要有一个集合path用来记录路径

import sys
import Queue

sys.path.append("..")

from base.AOE_graph import AOE_Graph
from base.AOE_graph import Edge


class Point:
    def __init__(self, p, path_total):
        self.p = p
        self.distance = path_total

    def __cmp__(self, other):
        return cmp(self.distance, other.distance)


g = AOE_Graph(4)
g.add_edge(Edge(0, 1, 3, 5))
g.add_edge(Edge(0, 2, 3, 5))
g.add_edge(Edge(0, 3, 8, 5))
g.add_edge(Edge(2, 3, 3, 5))


def find_best_way(g, s, to):
    num = g.num
    already_get_t = [False] * num
    path = [0] * num
    Max_Weight = 100000
    weight = [Max_Weight] * num
    weight[s] = 0
    try_point = Queue.PriorityQueue()
    try_point.put(Point(0, 0))
    while not try_point.empty():
        ele = try_point.get()
        index = ele.p
        already_get_t[index] = True
        if index == to:
            break
        for edge in g.adj(index):
            t = edge.t
            if already_get_t[t]:
                continue
            new_weight = weight[index] + edge.w
            if new_weight < weight[t]:
                # print "new:"+str(new_weight)
                weight[t] = new_weight
                try_point.put(Point(t, new_weight))
                path[t] = index

    print path
    print weight
    cur_path = []
    cur_i = to
    while cur_i != 0:
        cur_path.insert(0, str(path[cur_i]) + "-->" + str(cur_i))
        cur_i = path[cur_i]
    print cur_path


find_best_way(g, 0, 3)
