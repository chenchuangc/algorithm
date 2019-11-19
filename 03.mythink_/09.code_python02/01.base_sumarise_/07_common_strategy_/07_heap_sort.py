# !/bin/python
# -*- encoding:UTF-8 -*-


# 堆排序，k大堆的构建是不一样的，大顶堆的构建相对简单一些，是因为元素是逐个加进去的，
# 但是对于堆排序，一般情况下为了节约空间，都是直接在现有的数组上进行堆构建,所以更加麻烦一些，

# 假如是升序，那么，可以使用大顶堆，每次把堆的长度变小，小顶堆的话下标使用起来不是很好管理
# 先进行堆构建，就是构建成大顶堆，可以使用从倒数第一个开始进行上浮，实际上可以从n/2处进行上浮


def exchange_arr(arr, i, father):
    temp = arr[i]
    arr[i] = arr[father]
    arr[father] = temp


def big_heap(arr, end):
    i = (end - 1) / 2
    for j in reversed(range(i + 1)):
        sink(arr, end, j)


def sink(arr, end, k):
    while (k * 2 + 1) < end:
        child01 = 2 * k + 1
        child02 = 2 * k + 2
        target = child01
        if child02 < end and arr[child02] > arr[child01]:
            target = child02
        if arr[k] < arr[target]:
            exchange_arr(arr, k, target)
            k = target
        else:
            break


def heap_sort(arr):
    len_a = len(arr)
    big_heap(arr, len_a)
    for i in reversed(range(len_a)):
        exchange_arr(arr, 0, i)
        sink(arr, i, 0)
    print arr


arr = [5, 2, 33, 12, 81, 12, 16, 7, 9, 1, 61, 99, 122, 13, 13]
heap_sort(arr)
