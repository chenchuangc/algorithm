# !/bin/python
# -*- encoding:UTF-8 -*-

from direct_graph import DirectGraph


class DegreePoint:
    def __init__(self, mark, st, us_d):
        self.degree_in = 0
        self.mark = mark
        self.st = st  # 可以开始的最早的时间
        self.us_d = us_d  # 会消耗的时间

    def __str__(self):
        return "mark:" + str(self.mark) + "  degree:" + str(self.degree_in)+" st:"+str(self.st)

    def __cmp__(self, other):
        return cmp(self.st, other.st)


class DirectGraphDegree:
    def __init__(self, num):
        self.num = num
        self.points = [DegreePoint(i, 0, 0) for i in range(num)]
        self.container = [[] for i in range(num)]

    def add_edge(self, from_p, to_p):
        self.container[from_p].append(to_p)
        self.points[to_p].degree_in += 1

    def adj(self, from_p):
        return self.container[from_p]


def convert_from_no_degree(dg):
    num = dg.num
    dgd = DirectGraphDegree(num)
    for i in range(num):
        for j in dg.adj(i):
            dgd.add_edge(i, j)
    return dgd

# dg = DirectGraph(4)
# dg.add_edge(0, 1)
# dg.add_edge(0, 2)
# dg.add_edge(0, 3)
# dg.add_edge(1, 2)
# dg.add_edge(1, 3)
#
# print dg.adj(1)
# print "--------"
# dgd = convert_from_no_degree(dg)
# for ele in dgd.points:
#     print ele
