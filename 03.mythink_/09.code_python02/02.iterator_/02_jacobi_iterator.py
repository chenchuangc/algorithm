# !/bin/python
# -*- encoding:UTF-8 -*-

# 雅可比迭代法，真的很有创意，很难想到竟然可以直接那样求解。。。
# 这里主要是进行一个实现吧
# 5x+2y+z=-12  x=(-2y-z-12)/5
# -x+4y+2z=20  y=x-2z+20
# 2x-3y+10z=3  z=(-2x+3y+3)/10
# [-4,3,2]
# 这里的求解需要的计算变量有1.系数矩阵  2.初始变量x  3.常量因子b  4.除法因子
# 求解的时候有一个技巧就是都用数组表示，


def max_gap_cal(x, xt):
    max_r = 0.0
    for i in range(len(x)):
        dif = abs(x[i] - xt[i])
        if max_r < dif:
            max_r = dif
    return max_r


def get_cal():
    x = [0.00000001, 0.00000000, 0.00000]
    factor = [
        [5, 2, 1],
        [-1, 4, 2],
        [2, -3, 10]
    ]
    precise = 0.0001
    b = [-12, 20, 3]
    max_gap = 1.0
    while max_gap > precise:
        xt = [0] * 3
        for index_x in range(3):
            sum_x = 0.000
            for i in range(3):
                if i != index_x:
                    sum_x += x[i] * factor[index_x][i]
            sum_x = b[index_x] - sum_x
            sum_x = sum_x / factor[index_x][index_x]
            xt[index_x] = sum_x
        print '------'
        max_gap = max_gap_cal(x, xt)
        for j in range(len(x)):
            x[j] = xt[j]
        print x
    print x


get_cal()
