# !/bin/python
# -*- encoding:UTF-8 -*-

# 欧拉图也就是一笔画图，也就是每个路径都只走一遍的情况下最后能够走回来
# 这里只考虑无向图
# 欧拉对图有要求，存在欧拉路径的必须是联通图，同样，所有顶点的度数均为偶，则存在欧拉回路，可以回到终点，
# 若有且仅有两个顶点的度数为奇，其余的都为偶则可以遍历完图，但是不能回到终点
# 度数是指每个顶点有几个边相连。
# 这两个条件是否满足都是很好判断的

# 假如使用深度优先搜索，遍历结束的条件如果没有边可以走了，那么就是终止条件，这个时候判断存储的边的数量和那个总的边数是否对的上
# 需要一个栈来辅助记录走过的边
# 这个时候并不是从任何一个点出发都能全部遍历的，所以还要选好出发点
# https://blog.csdn.net/guomutian911/article/details/42105127  这个博客讲的挺好的

import sys
import copy

sys.path.append("..")

from base.graph import Graph
from base.graph import Stack

g = Graph(9)
g.add_edge(0, 8)
g.add_edge(0, 4)
g.add_edge(4, 2)
g.add_edge(4, 3)
g.add_edge(4, 7)
g.add_edge(3, 1)
g.add_edge(2, 1)
# g.add_edge(3, 5)
g.add_edge(5, 7)
g.add_edge(5, 6)
g.add_edge(6, 7)
g.add_edge(8, 7)


# 3 4 7 6 5 7 8 0 4 2 1 3 5
# 4 5 8 7 6 8 9 1 5 3 2 4 6
# 4 5 1 9 8 5 3 2 4 6 8 7 6
def deep_find(g, s, stack, mark, res, already_find):
    if already_find[0]:
        return
    no_way = True
    for i in g.adj(s):
        no_way = no_way and mark[s][i]
    if no_way:
        print "stack size:" + str(stack.size())
        if stack.size() == g.edge_num:
            res.append(stack.container[:stack.p])
            already_find[0] = True
        return

    for j in g.adj(s):
        if not mark[s][j]:
            stack.put(str(s) + "--->" + str(j))
            mark[s][j] = True
            mark[j][s] = True
            deep_find(g, j, stack, mark, res, already_find)
            stack.get()
            mark[s][j] = False
            mark[j][s] = False


def find_oula_way(g):
    num = g.p_num
    res = []
    already_find = [False]
    for k in range(num):
        if not already_find[0]:
            print "k:" + str(k)
            mark = [[False] * num for i in range(num)]
            stack = Stack()
            deep_find(g, k, stack, mark, res, already_find)

    print res
    print "aa"


find_oula_way(g)
