# !/bin/python
# -*- encoding:utf-8 -*-

# 终于算是对迪杰斯特拉算法有一点的了解了，感觉之前好像一直是没有看明白的样子，可能是记住了套路，但是没有想明白为什么要这样做
# 这两天反复找了一些问题来思考，终于算是有一些心得了
# 他的方式是使用一个最小堆，先把起点放进去，把起点到其他点的距离都初始化为无穷大（在一个额外的dis数组中），然后每次从小顶堆里面取出来最小的，这这个最小的必然是一个解
# 因为假设开头为s,取出来的为t1, 那么s->t1的最短路径肯定是t1中记录的长度，
# 因为没有负权的存在，所以如果存在其他最短到达的方式，那么他记录的长度肯定小于t1中记录的长度
# 所以就可以用这种方式找到所有的最短路径，同时使用path数组来记录数组的路径

# 在这里其实少了一个优化，可以加入一个close表来记录已经走过的点，这样可以过滤点不必要的点
# 所有从min出来的点都可以加入close表当中


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
        self.path = [None] * graph.num
        self.init_weight()

    def init_weight(self):
        start_point = Point(0, 0)
        queue = Queue.PriorityQueue()
        queue.put(start_point)
        while not queue.empty():
            min = queue.get()
            pos = min.pos
            print pos
            for edge in self.graph.adj(pos):
                another_pos = edge.another(pos)
                if self.weight[pos] + edge.w < self.weight[another_pos]:
                    self.weight[another_pos] = self.weight[pos] + edge.w
                    queue.put(Point(another_pos, self.weight[another_pos]))
                    self.path[another_pos] = pos
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

dijkstra = Dijkstra(graph)
p = dijkstra.get_path(4)
print p
