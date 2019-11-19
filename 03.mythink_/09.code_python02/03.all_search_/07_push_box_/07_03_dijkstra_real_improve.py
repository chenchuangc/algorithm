# !/bin/python
# -*- encoding:utf-8 -*-


# 接之前的，增加了一个close表来优化算法,优先队列等于是open表
import Queue


class Edge:
    def __init__(self, s, t, w):
        self.s = s
        self.t = t
        self.w = w

    def another(self, one):
        if one == self.s:
            return self.t
        return self.s


class Point:
    def __init__(self, pos, dis):
        self.pos = pos
        self.dis = dis

    def __cmp__(self, other):
        return cmp(self.dis, other.dis)


class Graph:
    def __init__(self, num):
        self.num = num
        self.container = []
        for i in range(num):
            self.container.append([])

    def add_edge(self, edge):
        s_index = edge.s
        t_index = edge.t
        self.container[s_index].append(edge)
        self.container[t_index].append(edge)

    def adj(self, i):
        return self.container[i]


class Dijkstra:
    def __init__(self, graph):
        self.graph = graph
        self.weight = [10000] * graph.num
        self.weight[0] = 0
        self.close = [False] * graph.num
        self.close[0] = True
        self.path = [None] * graph.num
        self.init_weight()

    def init_weight(self):
        start_point = Point(0, 0)
        queue = Queue.PriorityQueue()
        queue.put(start_point)
        while not queue.empty():
            min = queue.get()
            pos = min.pos
            print "pos:"+str(pos)
            self.close[pos] = True
            print pos
            for edge in self.graph.adj(pos):
                another_pos = edge.another(pos)
                if self.close[another_pos]:
                    continue
                if self.weight[pos] + edge.w < self.weight[another_pos]:
                    self.weight[another_pos] = self.weight[pos] + edge.w
                    queue.put(Point(another_pos, self.weight[another_pos]))
                    self.path[another_pos] = pos
        print self.close
        print self.path
        print self.weight

    def get_path(self, t):
        index = t
        paths = [t]
        while index != 0:
            paths.append(self.path[index])
            index = self.path[index]
        # paths.append(0)
        return paths


graph = Graph(5)
graph.add_edge(Edge(0, 1, 1))
graph.add_edge(Edge(0, 2, 5))
graph.add_edge(Edge(1, 2, 2))
graph.add_edge(Edge(0, 3, 2))
graph.add_edge(Edge(1, 3, 2))
graph.add_edge(Edge(1, 4, 5))
graph.add_edge(Edge(2, 4, 1))
graph.add_edge(Edge(3, 4, 1))
graph.add_edge(Edge(3, 4, 1))
graph.add_edge(Edge(0, 4, 6))

dijkstra = Dijkstra(graph)
p = dijkstra.get_path(4)
print p
