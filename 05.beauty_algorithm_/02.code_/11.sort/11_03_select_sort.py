#!/bin/python
# -*- encoding:UTF-8 -*-

# 选择排序的算法思想是每次


def select_sort(src, n):
    if n <= 1:
        return
    for i in range(n):
        min_index = i
        for j in range(i + 1, n):
            if src[j] < src[min_index]:
                min_index = j
        temp = src[i]
        src[i] = src[min_index]
        src[min_index] = temp


arr = [5, 4, 3, 20, 90, 30, 33, 23, 62]
select_sort(arr, 9)
print(arr)
