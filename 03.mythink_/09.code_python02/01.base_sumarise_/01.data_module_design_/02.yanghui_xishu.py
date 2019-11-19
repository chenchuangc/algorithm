#!/bin/python
# -*- encoding:utf-8 -*-

# 当指数为n求杨辉三角的展开项系数

# 先思考使用哪种算法设计模式，分治，穷举，动态规划，
# 一旦想到了使用分治，就会觉得问题迎刃而解

def yanghui(arr, n):
    if n == 0:
        arr[0] = {"c": 1, "a": 0, "b": 0}
        return
    yanghui(arr, n - 1)
    arr[n] = {"c": 1, "a": 0, "b": n}
    total = n
    n -= 1
    while n > 0:
        c = arr[n - 1].get("c") + arr[n].get("c")
        arr[n] = {"c": c, "a": total - n, "b": n}
        n -= 1

#这个是下标为0的进行了特殊处理
    arr[0] = {"c": 1, "a": total, "b": 0}
    return


n = 3
arr = [{"c": 0, "a": 0, "b": 0}] * (n + 1)
yanghui(arr, n)
print arr
