#!/bin/python
# -*- encoding:utf-8 -*-

# 归并排序的思想也是分治，结合递归进行求解

# 原来python中有地板除的专门用法，长见识了。。。

# 步骤
# 1. 把当前数组分为两部分
# 2. 对这两部分分别进行排序
# 3. 将这两部分排序的结果进行合并

# 递推的公式
# f(n)=merge(f(i,q),f(q+1,n))

# 也就是子问题先求解，父问题才能得到求解

# f()边界
# 1. 数组大小为1，直接返回，不需要再处理

# merge()逻辑
# 借一个temp数组来实现临时存储
# 1. temp[i++]=max(left[l_index],right[r_index])
# 2. 剩余的left,right直接进temp


def merge(src, start, mid, end, temp):
    r_start = mid + 1
    l_start = start
    temp_i = start

    while l_start <= mid and r_start <= end:
        if src[l_start] <= src[r_start]:
            temp[temp_i] = src[l_start]
            temp_i += 1
            l_start += 1
        else:
            temp[temp_i] = src[r_start]
            temp_i += 1
            r_start += 1
    while l_start <= mid:
        temp[temp_i] = src[l_start]
        temp_i += 1
        l_start += 1
    while r_start <= end:
        temp[temp_i] = src[r_start]
        temp_i += 1
        r_start += 1
    while start <= end:
        src[start] = temp[start]
        start += 1


def merge_sort_real(src, start, end, temp):
    if start == end:
        return
    mid = (start + end) // 2
    merge_sort_real(src, start, mid, temp)
    merge_sort_real(src, mid + 1, end, temp)
    merge(src, start, mid, end, temp)


def merge_sort(src):
    length = len(src)
    if length <= 1:
        return
    temp = [0] * length
    merge_sort_real(src, 0, length - 1, temp)


arr = [5, 4, 3, 20, 90, 30, 33, 23, 62, 33, 63]
merge_sort(arr)
print(arr)
