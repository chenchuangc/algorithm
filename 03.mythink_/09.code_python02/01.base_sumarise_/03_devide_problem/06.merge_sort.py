# /bin/python
# -*- encoding:UTF-8 -*-
# 归并排序的思想是，把两个已经有序的数组进行排序
# 可以把父亲分为两个子数组进行排序，把子数组排完的结果再合并
# 子可以一层一层递归，直到分解为最小的俩的时候无法再进行分解的时候就可以了，直接返回，然后上层进行归并


def merge(int_arr, start, partion, end, temp):
    lstart = start
    lend = partion
    rstart = partion + 1
    rend = end
    temp_start = lstart
    while lstart <= lend and rstart <= rend:
        if int_arr[lstart] <= int_arr[rstart]:
            temp[temp_start] = int_arr[lstart]
            lstart += 1
        else:
            temp[temp_start] = int_arr[rstart]
            rstart += 1
        temp_start += 1

    while lstart <= lend:
        temp[temp_start] = int_arr[lstart]
        temp_start += 1
        lstart += 1
    while rstart <= rend:
        temp[temp_start] = int_arr[rstart]
        temp_start += 1
        rstart += 1
    while start <= end:
        int_arr[start] = temp[start]
        start += 1


def sort(int_arr, start, end, temp):
    if start == end:
        return
    partion = (start + end) / 2
    sort(int_arr, start, partion, temp)
    sort(int_arr, partion + 1, end, temp)
    merge(int_arr, start, partion, end, temp)


def merge_sort(int_arr):
    start = 0
    end = len(int_arr) - 1
    temp = [0] * len(int_arr)
    sort(int_arr, start, end, temp)
    return int_arr


src = [12, 13, 14, 78, 16, 15, 12, 15, 89, 23, 42, 15]
des = merge_sort(src)
print des
