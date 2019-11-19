# !/bin/python
# -*- encoding:utf-8 -*-


# 之前的数据模型都是建好的一个树，但是实际中寻找最短路径的常常是一个平面，建模的时候常常是一个二维的数组
# 如何在这种数据模型下进行求解呢

# 通过这个求解也可以明显的看出来这个原来就是广度优先搜索了，从近处逐渐遍历到远处
# 这里实现了一个使用map作为模型的最优路径搜索
# 运行一下即可看到相应的结果，可以看出来这里主要应用的是广度优先搜索的方式进行了地毯式的搜索
# 这样的方式是可以将每一个点对应起始点的最短距离都可以求出来，
# 但是对于求解从起始点到某一个目标点的时候则是有点浪费了，因为多计算了很多没有用的点，
# 这个问题出现的原因，主要是open表，也就是优先队列中优先排序的因子是当前点距离起始点的距离，
# 如果想要加速找到目标表的过程，可以将当前表和目标表的距离也作为一个因子添加进来，这样的话搜索的路径就倾向于向距离目标更近的点前进了

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
    def __init__(self, row, col, dis):
        self.row = row
        self.col = col
        self.dis = dis

    def __cmp__(self, other):
        return cmp(self.dis, other.dis)


def around(graph, min):
    point_list = []
    row = min.row
    col = min.col
    row1 = row - 1
    col1 = col - 1
    row2 = row + 1
    col2 = col + 1
    if row1 >= 0:
        if graph[row1][col] != "x":
            point_list.append(Point(row1, col, min.dis + 1))
    if row2 < len(graph):
        if graph[row2][col] != "x":
            point_list.append(Point(row2, col, min.dis + 1))
    if col1 >= 0:
        if graph[row][col1] != "x":
            point_list.append(Point(row, col1, min.dis + 1))
    if col2 < len(graph[0]):
        if graph[row][col2] != "x":
            point_list.append(Point(row, col2, min.dis + 1))

    return point_list


class Dijkstra:
    def __init__(self, graph, s_row, s_col):
        self.graph = graph
        self.srow = s_row
        self.scol = s_col
        self.weight = [[100000] * len(graph[0]) for i in range(len(graph))]
        self.weight[s_row][s_col] = 0
        self.close = [[False] * len(graph[0]) for i in range(len(graph))]
        self.close[s_row][s_col] = True
        self.path = [[None] * len(graph[0]) for i in range(len(graph))]
        self.init_weight()

    def init_weight(self):
        start_point = Point(self.srow, self.scol, 0)
        queue = Queue.PriorityQueue()
        queue.put(start_point)
        while not queue.empty():
            min = queue.get()
            row = min.row
            col = min.col
            self.close[row][col] = True
            around_list = around(self.graph, min)
            for point in around_list:
                if self.close[point.row][point.col]:
                    continue
                if point.dis < self.weight[point.row][point.col]:
                    self.weight[point.row][point.col] = point.dis
                    self.path[point.row][point.col] = str(row) + "," + str(col)
                    queue.put(point)

        for line in self.weight:
            print line
        print "close...."
        for line in self.close:
            print line

    def get_path(self, t):
        index = t
        paths = [t]
        while index != 0:
            paths.append(self.path[index])
            index = self.path[index]
        return paths


graph = [
    ["", "", "x", "", ""],
    ["", "", "x", "", ""],
    ["", "x", "", "", ""],
    ["", "", "", "x", ""]
]

dijkstra = Dijkstra(graph, 0, 0)
