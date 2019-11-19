#!/bin/python
# -*- encoding:UTF-8 -*-

# 插入排序
# 在已经排序的子序列中插入当前元素，不断重复这个过程


def insert_sort(src, n):
    if n < 1:
        return
    for i in range(1, n):
        j = i
        temp = src[i]
        while j >= 1 and temp < src[j - 1]:
            src[j] = src[j - 1]
            j -= 1
        src[j] = temp
    return


arr = [5, 4, 3, 20, 90, 30, 33, 23, 62]
insert_sort(arr, 9)
print(arr)
