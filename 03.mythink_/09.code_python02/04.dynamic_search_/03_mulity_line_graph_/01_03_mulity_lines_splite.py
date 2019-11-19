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
# 这里是参照作者的答案来的，我自己想的那个方式实现起来好困难。
# 感觉还是对这个递推公式没有真正理解的了，展现的就是fn对f(n-1)的依赖，就是n个点的规模对n-1个点的规模的依赖
# 你使用的思想是什么样的呢，感觉还是像之前的那个样子，就是背包问题的思考方式，先入为主，对点进行了排序，
# 但是可以看出来，这个是明显不行的，这个问题在求解f(s,e)的时候也依赖于e点了，所以不能从点的顺序角度出发了


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
    t = [[0] * point_num for i in range(point_num)]
    s = [[0] * point_num for i in range(point_num)]
    N = point_num
    for i in range(1, N):
        t[i][i] = 0

    for r in range(2, N):
        for i in range(1, N - r + 1):
            j = i + r - 1
            min = 9999
            for k in range(i, j):
                t[i][j] = t[i][k] + t[k + 1][j] + weight_tangle(i - 1, k, j)
                if t[i][j] < min:  # //判断是否是最小值
                    min = t[i][j]
                    s[i][j] = k
            t[i][j] = min
    for ele in t:
        print ele
    print "------------"
    for ele in s:
        print ele

    return s, t


def get_res(s, i, j):
    if i == j:
        return
    get_res(s, i, s[i][j])
    get_res(s, s[i][j] + 1, j)
    print "[" + str(i - 1) + "," + str(s[i][j]) + "," + str(j) + "]"


ch = []
s, t = get_best()
print "*****"
get_res(s, 1, 5)
