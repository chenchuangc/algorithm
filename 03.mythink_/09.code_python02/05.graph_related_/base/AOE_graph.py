# !/bin/python
# -*- encoding:UTF-8 -*-


class Edge:
    def __init__(self, s, t, w, use):
        self.s = s
        self.t = t
        self.w = w
        self.use = use


class AOE_Graph:
    def __init__(self, num):
        self.num = num
        self.edges = [[] for i in range(num)]

    def add_edge(self, edge):
        s_index = edge.s
        self.edges[s_index].append(edge)

    def adj(self, i):
        return self.edges[i]
