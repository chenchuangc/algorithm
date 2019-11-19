# !/bin/python
# -*- encoding:UTF-8 -*-


class Graph:
    def __init__(self, num):
        self.p_num = num
        self.container = [[] for i in range(self.p_num)]
        self.edge_num = 0

    def add_edge(self, one, another):
        self.container[one].append(another)
        self.container[another].append(one)
        self.edge_num += 1

    def adj(self, s):
        return self.container[s]


# g=Graph(4)


class Stack:
    def __init__(self):
        self.container = [0] * 1000
        self.p = 0

    def get_container(self):
        return self.container

    def put(self, ele):
        self.container[self.p] = ele
        self.p += 1

    def get(self):
        if self.p == 0:
            return
        self.p -= 1
        return self.container[self.p]

    def peek(self):
        return self.container[self.p - 1]

    def empty(self):
        return self.p == 0

    def size(self):
        return self.p
