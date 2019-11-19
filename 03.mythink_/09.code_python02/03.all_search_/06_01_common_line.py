# !/bin/python
# -*- encoding:UTF-8 -*-

# 求解共线最多的点
# 这里问题的求解可以使用穷举法，对每一个点和其他的点进行穷举，k相同的话就可以认为是在一条直线上面
# 这里问题的关键是如何保存对应的k对应的点的个数，考虑一下需要保存的东西，需要保存的有，原点，对应的第二个点，对应的k是多少，
# 每次上来不要操之过急，很多时候可能问题就没有更好的求解方法，就是比较复杂
# 每次以一个点位原点，求出过这个点的最大的共线点，需要记录这些点以及k
# 像这里的每个点的斜率，使用map记录的话实际上是比较浪费的，因为没有用上hash表的特性，但是又会在添加的时候做hash计算，
# 有一种改进的方式是使用数组存储每个点的位置和斜率对象，然后按照斜率进行排序，这样的话遍历数组就可以得到共线最多的点

class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y


point_arr = [Point(1, 5), Point(1, 1), Point(2, 2), Point(3, 3), Point(4, 4), Point(1, 6), Point(1, 9), Point(1, 4)]


def getk(p1, p2):
    if p1.x==p2.x:
        return 10000
    return (p1.y*1.00 - p2.y) / (p1.x*1.00 - p2.x)


def set_cur(cur_k_point, k, param):
    for key in cur_k_point.keys():
        if abs(key - k) < 0.01:
            cur_k_point[key].append(param)
            return
    list = [param]
    cur_k_point[k] = list


def get_cur_max(cur_k_point):
    best_k = 0
    best_list = []
    for k, list in cur_k_point.items():
        if len(list) > len(best_list):
            # print k
            best_list = list
            best_k = k
    return best_k, best_list


def find_max(point_list):
    num_p = len(point_list)
    pre_max = []
    pre_k = 0
    for i in range(num_p):
        cur_k_point = {}
        for j in range(num_p):
            if j == i:
                continue
            k = getk(point_list[i], point_list[j])
            # print k
            set_cur(cur_k_point, k, str(i) + "-" + str(j))
        cur_k, cur_max = get_cur_max(cur_k_point)
        if len(cur_max) > len(pre_max):
            pre_max = cur_max
            pre_k = cur_k
            # print cur_k
    print pre_k
    print pre_max


find_max(point_arr)
# map_={}
# print map_["aaa"]
