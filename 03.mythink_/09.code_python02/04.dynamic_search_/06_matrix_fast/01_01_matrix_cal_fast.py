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
# 对于这个问题，第一步肯定是选择两个元素相乘，无论那两个，然后后面把这两个的结果再和其他的进行同样的操作，这个实际上等于是穷举操作
# 而不是动态规划的方式，要不先试试实现以下这个？
# 思路，每次选择两个，进行穷举，直到最后计算出来结果，保存现在最优的结果以及当前的步骤，在计算结束以后进行一次比较。最后穷举完以后完成。
# 使用深度优先的方式记录搜索路径
# 你这个是正着计算的。就是从第一次开始进行计算
# good 完成了，哈哈哈
# 这样的计算就是穷举，同时开销有点大
# 而且好像是没有办法使用备忘录的，维度有点多，关键是子问题也没有重复的哈
# 有时候使用递归完成某些迭代的类的工作反而很清晰

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


def get_best_way(mat_list, res, cur, stack):
    if len(mat_list) == 1:
        print cur
        if cur < res.best:
            res.best = cur
            res.stack = copy.deepcopy(stack)
            print stack.get_all()
        return
    for i in range(len(mat_list) - 1):
        stack.put(str(i) + "*" + str(i + 1))
        cur_cur = mat_list[i].row * mat_list[i].col * mat_list[i + 1].col + cur
        cur_mat_list = mat_list[:i] + [Matrix(mat_list[i].row, mat_list[i + 1].col)] + mat_list[i + 2:]
        get_best_way(cur_mat_list, res, cur_cur, stack)
        stack.get()


# mat_list = [Matrix(10, 100), Matrix(100, 5), Matrix(5, 50)]
mat_list = [Matrix(50, 10), Matrix(10, 100), Matrix(100, 5)]
# 50*10*100 + 50*100*5  | 10*100*5 + 50*10*5
res = Res(0, 1000000000)
stack = Stack()
get_best_way(mat_list, res, 0, stack)
print res.best

# a = [1, 2, 3, 4, 5, 6, 7]
# print a[:3]
# print a[7:]
