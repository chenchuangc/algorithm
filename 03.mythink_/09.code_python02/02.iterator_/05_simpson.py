# !/bin/python
# -*- encoding:UTF-8 -*-


def fun_a(x):
    return x * x + 2.0 * x


def simpson(fun, a, b):
    n = 10000
    step = (b - a) * 1.0 / n
    sum = fun(a) + fun(b)
    s1 = 0
    s2 = 0
    for i in range(1, n):
        s1 += fun(a + step * i)
    for j in range(1, n + 1):
        s2 += fun(a + step * j - (step * 0.5))
    sum += 2 * s1 + 4 * s2
    sum = sum * step / 6
    return sum


def simpson_diff_step(fun, a, b):
    precise = 0.0001
    s1 = (4 * fun((a + b) / 2) + fun(a) + fun(b)) * (b - a) / 6
    mid = (a + b) / 2.0
    s2 = (4 * fun((a + mid) / 2) + fun(a) + fun(mid)) * (mid - a) / 6
    s2 += (4 * fun((mid + b) / 2) + fun(mid) + fun(b)) * (b - mid) / 6
    if abs(s2 - s1) > precise:
        return simpson_diff_step(fun, a, mid) + simpson_diff_step(fun, mid, b)
    else:
        return s1


print simpson(fun_a, 2, 4)
print simpson_diff_step(fun_a, 2, 4)
