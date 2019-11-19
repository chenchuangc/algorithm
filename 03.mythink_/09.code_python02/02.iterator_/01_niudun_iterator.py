# !/bin/python
# -*- encoding:UTF-8 -*-

# 牛顿迭代法是使用倒数来加速求解的方式,因为直接按照公式来，比较简单
# 1. 迭代变量  2. 迭代递推公式 3.终止条件（ 1. 迭代精度  2. 迭代步数 ）


def function_cal(x):
    return x * x + 2 * x - 15


def derivative(fun, x0):
    step = 0.0001
    x1 = x0 + step
    return (fun(x1) - fun(x0)) / step


def cal_res(fun):
    x = 1
    precise = 0.001
    total_step = 2000
    step_count = 0
    y = fun(x)
    while abs(y) > precise and step_count < total_step:
        x = x - (y / derivative(fun, x))
        y = fun(x)
        step_count += 1

    return x


print cal_res(function_cal)
