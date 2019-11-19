# !/bin/python
# -*- encoding:UTF-8 -*-

# dijkstra算法意在找出从一个点出发到其他所有点的最短路径，他和最小生成树还是不一样的
# 他将就的是求出了当前顶点到任何一个顶点的距离，最小生成树则在意的是整体的最短距离
# grap => list[list[edge]]
# s中放入起点，w[]中存储s到达的顶点的距离w,不能直接到达的就是无穷大，然后遍历s的周边，更新w[]数组来修改s到达对应顶点的距离
# 这个过程中还要记录需要走过的边，怎么记录更加方便呢，使用一个数组来保留顶点之间的关系应该就好了
# 不要急着写，还是要先理清了，写着也就快了


# 这个dijkstra 算法 感觉想不到的是把边加入到了优先队列当中，我最开始想的也是这样，可是却不知道应该如何加入第一条边
# 这里的第一条边总是想用现有的边的，刚刚才反应过来，
# 可以是一个虚拟的边，也就是不存在的边，可以造一个边，起始和结束都是0，然后权重为0，这样的话就可以开启了

# 最开始你是从队列中取出边之后进行总路径的权重判断，判断是否能够取代原来的边，因为本来就是取出的最小边，想着肯定是用来用的。
# 然后再把这个最小边挨着的边都加进去，但是这样的话就引入了一个新的问题，如果这样加入边的话，因为可能有环，这样的话边就永远也加不完了，

# 一直没有想到其实可以将能够降低路径权重的边加入到优先队列当中，这样的思维似乎有点奇怪，但是也是在加入之前就更新在数组中保存的路径权重了
# 对于这个问题还是需要好好理解一下
# 只要是加入到优先队列中的都是过滤过的，这样就不需要重复加入了
# 可不可以加入只是为了为下一次寻找更外面的点做准备呢
# 这个做法是错误了，误解了dijkstra算法的原理


from Queue import PriorityQueue


class Stack:
    def __init__(self):
        self.container = [0] * 100000
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


class Edge:
    def __init__(self, s, t, w):
        self.s = s
        self.t = t
        self.w = w

    def __cmp__(self, other):
        return cmp(self.w, other.w)

    def get_another(self, one):
        if one == self.s:
            return self.t
        return self.s


class Point:
    def __init__(self, i, pre, w):
        self.index = i
        self.pre_point = pre
        self.weight = w

    def __cmp__(self, other):
        return cmp(self.weight, other.weight)


class Graph:
    def __init__(self, edges, num):
        self.p_num = num
        self.edge_list = [[] for i in range(num)]
        self.init_grap(edges)

    def init_grap(self, edges):
        for edge in edges:
            self.edge_list[edge.s].append(edge)
            self.edge_list[edge.t].append(edge)


class Dijkstra:
    def __init__(self, graph):
        self.graph = graph
        self.p_list = []
        self.init_graph()

    def init_graph(self):
        for i in range(self.graph.p_num):
            self.p_list.append(Point(i, 0, 10000))
        # stack=Stack()
        self.p_list[0] = Point(0, -1, 0)
        # self.p_list[0].pre_point = -1
        queue = PriorityQueue()
        queue.put(Point(0, -1, 0))
        while not queue.empty():
            ele = queue.get()
            index = ele.index
            print index
            for item in self.graph.edge_list[index]:
                another_p = item.get_another(index)
                cur_weight = self.p_list[index].weight + item.w
                print cur_weight
                if cur_weight < self.p_list[another_p].weight:
                    self.p_list[another_p].weight = cur_weight
                    self.p_list[another_p].pre_point = index
                    queue.put(Point(another_p, index, item.w))

    def get_best_way(self, target):
        stack = Stack()
        ele = self.p_list[target]
        print "****"
        print ele.weight
        while ele.pre_point != -1:
            stack.put(ele)
            # print ele
            ele = self.p_list[ele.pre_point]
        stack.put(self.p_list[0])
        return stack


edges = [Edge(0, 1, 1), Edge(0, 2, 5), Edge(1, 2, 2), Edge(0, 3, 2), Edge(1, 3, 2)]

g = Graph(edges, 4)
d = Dijkstra(g)
res = d.get_best_way(2)
print "-------"
while not res.empty():
    e = res.get()
    print e.index
