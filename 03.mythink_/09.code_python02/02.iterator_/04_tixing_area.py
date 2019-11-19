# !/bin/python
# -*- encoding:UTF-8 -*-


def fun_a(x):
    return x * x + 2 * x


def cal_area(fun, a, b):
    n = 10000
    step = (b - a)*1.0 / n
    sum_r = (fun(a) + fun(b)) / 2
    for i in range(1, n):
        sum_r += fun(a+i*step)
    sum_r *= step
    return sum_r


def cal_area_better(fun, a, b):
    precise = 0.00001
    r1 = (fun(a) + fun(b)) / 2 * (b - a)
    mid = (a + b) / 2.0
    r2 = ((fun(a) + fun(mid)) / 2 * (mid - a)) + ((fun(b) + fun(mid)) / 2 * (b - mid))
    if abs(r1 - r2) > precise:
        return cal_area_better(fun, a, mid) + cal_area_better(fun, mid, b)
    else:
        return r1


print cal_area(fun_a, 2, 4)
print cal_area_better(fun_a, 2, 4)
