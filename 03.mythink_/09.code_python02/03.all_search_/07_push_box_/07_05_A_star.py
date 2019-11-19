# !/bin/python
# -*- encoding:UTF-8 -*-

# A* 算法，是为了常见的静态寻路算法的，是经过dijkstra算法演进而来。
# 主要是增加了启发函数，相对于dijkstra算法来说能再更高的概率上快速接近目标地
# 启发函数是f=g+h  g为从初始点走到这一步的距离，h为当前点到目标点的距离
# 这样的话就会尽可能的向着目标方向前进
# 之前脑袋中关于这个都是图的结构，想想假如是地图的话如何建模转化为图么，转化为图再运算么，那这样的话应该也是很痛苦了吧。
# 一般情况就直接是一个二维数组就行了，因为实际上转化为图的效率也并不高，很浪费时间
#
# 目前对于这个算法的疑惑在于,小顶堆的排序方式使用的是(g+h),但是每次出来的g还是最小的g么，就是g的求解还能够满足最优解么
# 简单的推理了一下
# 假如有这样几条边 a-b,a-c,c-b,b-t,c-t出发点位a目标为t, 假如g(b)>g(c+w(c-b)) ,则肯定满足g(b)>g(c)
# 如果用dijkstra从open中出来的则一定是先出a-c,所以可以满足close表中的a到b的最佳路径就是a-c-b
# 如果使用A* 则存在g(b)>g(c+w(c-b)) , 但是g(b)+h(bt)<g(c)+h(ct) 是有可能出现的，这样个的话从open表中先出来的可能就是b了，
# 一旦进入了close表就不会再修改，所以可能导致中间的结果是不正确的，
# 但是因为A*算法的逻辑是在当前的状态下找出到达目标的最优解，而不用满足局部的解也是最优解
# 算法打印出来可以看到，相对的少计算了一些点，这个就是A*算法
# 对于一个算法，不要急，多思考，多琢磨为什么要这样做，可能收获的更多

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
    def __init__(self, row, col, g, h):
        self.row = row
        self.col = col
        self.g = g
        self.h = h

    def __cmp__(self, other):
        return cmp(self.g + self.h, other.g + other.h)


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


class AStar:
    def __init__(self, graph, s_row, s_col, t_row, t_col):
        self.find = False
        self.graph = graph
        self.srow = s_row
        self.scol = s_col
        self.trow = t_row
        self.tcol = t_col
        self.weight = [[100000] * len(graph[0]) for i in range(len(graph))]
        self.weight[s_row][s_col] = 0
        self.close = [[False] * len(graph[0]) for i in range(len(graph))]
        self.close[s_row][s_col] = True
        self.path = [[None] * len(graph[0]) for i in range(len(graph))]
        self.init_weight()

    def init_weight(self):
        start_point = Point(self.srow, self.scol, 0, self.distance_h(self.srow, self.scol))
        queue = Queue.PriorityQueue()
        queue.put(start_point)
        while not queue.empty():
            min = queue.get()
            row = min.row
            col = min.col
            self.close[row][col] = True
            if row == self.trow and col == self.tcol:
                self.find = True
                break
            around_list = self.around(self.graph, min)
            for point in around_list:
                if self.close[point.row][point.col]:
                    continue
                if point.g < self.weight[point.row][point.col]:
                    self.weight[point.row][point.col] = point.g
                    self.path[point.row][point.col] = str(row) + "," + str(col)
                    queue.put(point)
        for line in self.weight:
            print line
        print "close...."
        for line in self.close:
            print line

    def distance_h(self, crow, ccol):
        return abs(self.trow - crow) + abs(self.tcol - ccol)

    def around(self,graph, min):
        point_list = []
        row = min.row
        col = min.col
        row1 = row - 1
        col1 = col - 1
        row2 = row + 1
        col2 = col + 1
        if row1 >= 0:
            if graph[row1][col] != "x":
                point_list.append(Point(row1, col, min.g + 1, self.distance_h(row1,col)))
        if row2 < len(graph):
            if graph[row2][col] != "x":
                point_list.append(Point(row2, col, min.g + 1, self.distance_h(row2,col)))
        if col1 >= 0:
            if graph[row][col1] != "x":
                point_list.append(Point(row, col1, min.g + 1, self.distance_h(row,col1)))
        if col2 < len(graph[0]):
            if graph[row][col2] != "x":
                point_list.append(Point(row, col2, min.g + 1, self.distance_h(row,col2)))

        return point_list



graph = [
    ["", "", "x", "", ""],
    ["", "", "x", "", ""],
    ["", "x", "", "", ""],
    ["", "", "", "x", ""]
]

star = AStar(graph, 0, 0, 0, 3)
