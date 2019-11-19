#!/bin/python
# -*- encoding:UTF-8 -*-

# 这里主要是试验一下哨兵在实际中的使用，哨兵在很多情况下能够使编程的过程更加简单，减少了一些校验

# 比如插入排序，假如0位不做使用，使用0位作为标兵位置，就可以减少很多的边界判断

# 这样看起来比较清爽


def insert_sort(src_arr):
    for i in range(2, len(src_arr)):
        if src_arr[i] < src_arr[i - 1]:
            src_arr[0] = src_arr[i]
            j = i
            while src_arr[j - 1] > src_arr[0]:
                src_arr[j] = src_arr[j - 1]
                j -= 1
            src_arr[j] = src_arr[0]
    print src_arr


src = [0, 3, 2, 5, 8]
insert_sort(src)


























