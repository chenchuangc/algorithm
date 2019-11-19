# !/bin/python
# -*- encoding:UTF-8 -*-

# 矩阵 A 和 B 能够相乘，前提是矩阵 A 和矩阵 B 必须相容，所谓的相容，就是矩阵 A 的列数等于矩阵 B 的行数。
# 假设矩阵 A 是 p×q 的矩阵，矩阵 B 是 q×r 的矩阵，则矩阵 A 和矩阵 B 的乘积是一个 p×r 的矩阵。
# 计算这个乘积需要 p×q×r 次标量乘法计算和若干次加法，其计算量的主要代价就是这 p×q×r 次乘法计算

# 对于一组满足相容性条件（顺序）排列的矩阵做链乘，无论选择中间哪两个矩阵先计算，其结果都能与剩下的矩阵继续保持相容性条件，
# 这是一个很重要的前提，因为调整矩阵的位置会破坏相容性。但是，在矩阵位置不变的情况下，选择计算的顺序对标量乘法的计算量有巨大的影响。
# 例如，有三个矩阵 A B C 相乘，其中 A 是 10×100 的矩阵，B 是 100×5 的矩阵，C 是 5×50 的矩阵。
# 如果我们按照（（A B）C）的顺序计算，则需要计算的乘法次数是 10 × 100 × 5 + 10 × 5 × 50 = 7500 次。
# 如果我们按照（A（B C））的顺序计算，则需要计算的乘法次数是 100 × 5 × 50 + 10 × 100 × 50 = 75000 次。
# 可见，第二种计算方法需要的计算量是第一种方法的 10 倍。
# 那么，最后的问题来了，对于 n 个矩阵序列 A_{1}, A_{2}, A_{3},...,A_{n}
# 我们要计算它们的乘积，请用括号化的方式给出一种计算次序，使得最终需要的标量乘法次数最少


# 对于这个问题的分析，因为刚开始给出了这个问题是一个区间动态规划的问题的暗示，所以也是从区间动态规划上面去想的，最开始的思路想的是
# 使用区间动态规划的思想，问题到达最后一步的时候，肯定是两个子问题的结果进行矩阵相乘，加入存在最优解，且这一步就是最优解，
# 那么，就可以进行问题的分解了
# f(i,j)是指问题的区间是从i->j f(i,j)=min{f(i,k)+f(k+1,j)+w(k)} (i<k<j)
# 只要能够形成这种子问题区间，问题就迎刃而解了，可以形成了一个套路
# 从两个点开始进行迭代，每次的w(k) 的计算等于i.row_num*k.col_num*j.col_num 这样就可以简单的进行迭代了
# 这个问题和上次做的凸多边形的问题好像还有点不太一样，这里需要注意的是，分解的子问题后面的是k+1-->j 之前的是k


import copy


class Matrix:
    def __init__(self, row, col):
        self.row = row
        self.col = col


class Res:
    def __init__(self, cur, best):
        self.cur = cur
        self.best = best


class Stack:
    def __init__(self):
        self.container = [0] * 1000
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

    def get_all(self):
        return self.container[:self.p]


def weight_matrix(i, k, j, mat_list):
    return mat_list[i].row * mat_list[k].col * mat_list[j].col


def get_best_way(mat_list):
    total_num = len(mat_list)
    res = [[1000000] * total_num for i in range(total_num)]
    for i in range(total_num):
        res[i][i] = 0

    for c_num in range(2, total_num + 1):

        for i in range(0, total_num - c_num + 1):
            j = i + c_num - 1
            min_cur = 1000000
            for k in range(i, j):
                cur = res[i][k] + res[k + 1][j] + weight_matrix(i, k, j, mat_list)
                print str(i) + "," + str(k) + "," + str(j) + ":" + str(cur)
                if cur < min_cur:
                    min_cur = cur
            res[i][j] = min_cur
    for line in res:
        print line
    print res[0][2]

    # mat_list = [Matrix(10, 100), Matrix(100, 5), Matrix(5, 50)]


mat_list = [Matrix(50, 10), Matrix(10, 100), Matrix(100, 5)]
# 50*10*100 + 50*100*5  | 10*100*5 + 50*10*5
# res = Res(0, 1000000000)
# stack = Stack()
get_best_way(mat_list)
# print res.best

# a = [1, 2, 3, 4, 5, 6, 7]
# print a[:3]
# print a[7:]
