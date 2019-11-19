# !/bin/python
# -*- encoding:UTF-8 -*-

# 凸多边形分割，这个问题是对一个凸多边形进行分割，形成多个三角形，
# 形成新的三角形以后，要计算这些三角形的边的权重之和，看看哪种方式的权重和最小
# 建模，假设顶点为0-n ,每两个顶点之间构成的边的权重就算是这两个点之间的直角距离吧

# 沿着这个思路来，假设这个三角形是{V0,Vi,Vn}三个点，那么还有两个点集就是{V0-Vi},{Vi-Vn}
# 这样的话就可以进行最优子结构的分解了，当然因为这个i是 0<i<n 所以是有n-1种可能，需要取这n-1种可能的最小值
# 状态:点集start-end,
# 递推公式f(s,e)=min{f(s,i)+f(i,e)+w(s,e,i)} s<i<e
# 边界当s+1=e||s==e的时候，权重之和为0，因为无法凑成三角形了

# 这一篇接上一篇，主要是为了使用备忘录的方式来加快运算
# 数据模型的构建，根据递推公司可以看出来，可以用二维数组来作为数据表(备忘录)
# 那么计算的过程又是什么样的呢，可以看出来的是问题的大规模依赖于更小的规模，问题的规模是指什么呢，就是指顶点的数量，
# 可以在不同顶点规模的情况下分别对问题进行计算，这样就可以逐步推出问题的解了
# 所以可以使问题的规模从2个点开始，逐步求解
# 在一个固定规模m的问题中，需要进行m-1次遍历，1<i<m
# 关键是如何递推公式中的i估计需要倒着来了
# 这种方案好像是求不出来解的,因为这样的话出现了后面的依赖于前面了 f(i,e)没有办法求出了，不是在前面求出的，所以没有办法进行递推了
#


from math import sqrt


class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y


class Range:
    def __init__(self, s, e):
        self.s = s
        self.e = e


def distance_p(p1, p2):
    return sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y))


# print distance_p(Point(1,1),Point(2,2))
point_list = [
    Point(0, 0),
    Point(4, 0),
    Point(4, 4),
    Point(3, 5),
    Point(1, 5),
    Point(0, 4)
]


def weight_tangle(i1, i2, i3):
    global point_list
    p1 = point_list[i1]
    p2 = point_list[i2]
    p3 = point_list[i3]
    return distance_p(p1, p2) + distance_p(p2, p3) + distance_p(p1, p3)


def get_best():
    global point_list
    point_num = len(point_list)
    w = [[0] * point_num for i in range(point_num)]
    k = [[0] * point_num for i in range(point_num)]
    for i in range(point_num):
        w[i][i] = 0
        if i < point_num - 1:
            w[i][i + 1] = 0

    for p_num in range(2, len(point_list)):
        for i in reversed(range(1, point_num)):
            min = 1000
            for j in range(i, p_num):
                cur = w[0][j] + w[j][p_num] + weight_tangle(0, j, p_num)

    print w


ch = []
print get_best()
print ch
