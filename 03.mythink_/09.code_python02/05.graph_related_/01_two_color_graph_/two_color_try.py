# !/bin/python
# -*- encoding:UTF-8 -*-

# 对图进行二分，这里想的是使用深度优先遍历的方式，因为图具有联通性，所以可以从任何一个点开始对所有的点进行遍历
# 像这种使用递归的方式是比较容易对图进行深度优先搜索的，如果直接使用迭代，则有一定的难度
# 因为是无向图，所以需要标识点是否遍历过，同时需要记录标识过的点的颜色，来判断是否能够进行二分

# 把二分图的点集重新整理也是一个技术活儿，因为是数字表示，所以直接使用数组来充当map,也是很有意思的，想了半天没有想到，下午就突然想出来了。晕，就是普通套路啊。


import sys

sys.path.append("..")

from base.graph import Graph


def get_color(c):
    if c == "red":
        return "green"
    else:
        return "red"


class TwoColorGraph:
    def __init__(self, g):
        self.g = g
        self.is_processed = [False] * self.g.p_num
        self.color = [""] * g.p_num
        self.visited = [False] * g.p_num
        self.is_two_color = True
        self.color_count = {"red": 0, "green": 0}
        s = 0
        self.deep_find(s, "red")
        self.red = [[] for i in range(self.color_count["red"])]
        self.green = [[] for i in range(self.color_count["green"])]
        self.tow_part = {"red": self.red, "green": self.green}
        self.split_two_part()
        print self.color_count

    def deep_find(self, s, c):

        self.visited[s] = True
        self.color[s] = c
        self.color_count[c] += 1
        for i in self.g.adj(s):
            if self.visited[i]:
                if self.color[i] != c:
                    continue
                else:
                    self.is_two_color = False
                    return
            self.deep_find(i, get_color(c))

    def split_two_part(self):
        map_red = [0] * self.g.p_num
        map_green = [0] * self.g.p_num
        cur_red = -1
        cur_green = -1
        for i in range(self.g.p_num):
            if self.color[i] == "red":
                cur_red += 1
                map_red[i] = cur_red
            else:
                cur_green += 1
                map_green[i] = cur_green
        for j in range(self.g.p_num):
            if self.color[j] == "red":
                index = map_red[j]
                for another in self.g.container[j]:
                    self.red[index].append(map_green[another])
            else:
                index = map_green[j]
                for another in self.g.container[j]:
                    self.green[index].append(map_red[another])
        print "red..."
        for ele in self.red:
            print ele
        print "green..."
        for ele in self.green:
            print ele

# g = Graph(4)
# g.add_edge(0, 1)
# g.add_edge(0, 2)
# g.add_edge(0, 3)
# g.add_edge(1,3)
#
# print g.p_num
# two_color_g = TwoColorGraph(g)
# print two_color_g.is_two_color

# print "greep"!="reep"
