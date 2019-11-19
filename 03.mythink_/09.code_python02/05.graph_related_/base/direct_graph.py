# !/bin/python
# -*- encoding:UTF-8 -*-


class DirectGraph:
    def __init__(self, num):
        self.num = num
        self.container = [[] for i in range(num)]

    def add_edge(self, from_p, to_p):
        self.container[from_p].append(to_p)

    def adj(self, from_p):
        return self.container[from_p]


# dg = DirectGraph(4)
# dg.add_edge(0, 1)
# dg.add_edge(0, 2)
# dg.add_edge(0, 3)
# dg.add_edge(1, 2)
# dg.add_edge(1, 3)
#
# print dg.adj(1)
