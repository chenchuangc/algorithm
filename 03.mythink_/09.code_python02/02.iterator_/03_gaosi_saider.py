# !/bin/python
# -*- encoding:UTF-8 -*-

# 针对雅可比做了优化，直接使用本轮产生的变量

# factor 矩阵因子，x初始变量 ， b加法因子，n 方程组个数，变量个数
def gaussi_cal(factor, x, b, n):
    precise = 0.0001
    max_dif = 1
    while max_dif > precise:
        max_dif = 0.0
        for index_x in range(n):
            sum = 0.0
            for i in range(n):
                if i != index_x:
                    sum += x[i] * factor[index_x][i]
            sum = b[index_x] - sum
            sum = sum / factor[index_x][index_x]
            max_dif = max(max_dif, abs(sum - x[index_x]))
            x[index_x] = sum
    print x


x = [0.00000001, 0.00000000, 0.00000]
factor = [
        [5, 2, 1],
        [-1, 4, 2],
        [2, -3, 10]
    ]
#precise = 0.0001
b = [-12, 20, 3]

gaussi_cal(factor,x,b,3)

