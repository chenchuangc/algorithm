# !/bin/python
# -*- encoding:UTF-8 -*-

# 凸多边形分割，这个问题是对一个凸多边形进行分割，形成多个三角形，
# 形成新的三角形以后，要计算这些三角形的边的权重之和，看看哪种方式的权重和最小
# 建模，假设顶点为0-n ,每两个顶点之间构成的边的权重就算是这两个点之间的直角距离吧

# 这个问题建模的一个trick是吧结合处的顶点忽略掉，比如顶点0,因为他即是起点又是终点，加入加上会怎么样呢？

# 还是先聊一下解题思路吧 对于这样的一个问题，假设最优解已经完成了对这个凸多边形的分解，那么一定会有一个以V0-Vn为边构成的三角形
# 沿着这个思路来，假设这个三角形是{V0,Vi,Vn}三个点，那么还有两个点集就是{V0-Vi},{Vi-Vn}
# 这样的话就可以进行最优子结构的分解了，当然因为这个i是 0<i<n 所以是有n-1种可能，需要取这n-1种可能的最小值
# 状态:点集start-end,
# 递推公式f(s,e)=min{f(s,i)+f(i,e)+w(s,e,i)} s<i<e
# 边界当s+1=e||s==e的时候，权重之和为0，因为无法凑成三角形了
# 看来也不是必须要避开0，有0的时候也是可以求解的，而且记录的时候也并不是很复杂


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


def get_best(range_point, choose):
    global point_list
    if range_point.e - range_point.s <= 1:
        return 0
    min_ = 1000
    for i in range(range_point.s + 1, range_point.e):
        child_choose01 = []
        child_choose02 = []
        cur = get_best(Range(range_point.s, i), child_choose01) + get_best(Range(i, range_point.e),
                                                                           child_choose02) + weight_tangle(
            range_point.s, i,
            range_point.e)
        if cur < min_:
            print range_point.s, i, range_point.e
            min_ = cur
            cur_point = [range_point.s, i, range_point.e]
            cur_choose = child_choose01 + child_choose02
    cur_choose.append(cur_point)
    choose.extend(cur_choose)
    return min_


ch = []
print get_best(Range(0, 5), ch)
for i in ch:
    print i
