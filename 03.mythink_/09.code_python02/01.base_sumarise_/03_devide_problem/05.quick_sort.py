#!/bin/python
# -*- encoding:UTF-8 -*-

# 快速排序的过程是，任意找一个，作为标兵，然后，比他小的放在他左边，比他大的放在他右边，然后标兵再和最后一个比他小的交换，
# 这样就可以形成部分有序
# 因为标兵要和最后一个比他小的交换，所以一定是从右边先进行遍历，这样可以保证指针所在位置比标兵小
# 在对具体实现进行认真分析的话可能就能看到一些需要注意的东西，
# 比如这里还有需要注意的地方是 左边最开始的时候是和标兵的位置一致的，所以就导致了标兵，如果不进行特殊处理的话可能在标兵的位置走不了


def exchange(int_arr, pos_left, pos_right):
    temp = int_arr[pos_right]
    int_arr[pos_right] = int_arr[pos_left]
    int_arr[pos_left] = temp


def sort(int_arr, start, end):
    if start < end:
        mark = int_arr[start]
        pos_left = start
        pos_right = end
        # pos_left+=1
        while pos_left < pos_right:
            while pos_right > pos_left and int_arr[pos_right] > mark:
                pos_right -= 1
            while pos_left < pos_right and int_arr[pos_left] <= mark:
                pos_left += 1
            if pos_right != pos_left:
                exchange(int_arr, pos_left, pos_right)
        exchange(int_arr, start, pos_right)
        sort(int_arr, start, pos_right - 1)
        sort(int_arr, pos_right + 1, end)


def quick_sort(int_arr):
    end = len(int_arr) - 1
    start = 0
    sort(int_arr, start, end)
    return int_arr


src = [12, 10, 23, 1, 8, 43, 92, 11, 12, 86, 13, 12]
des = quick_sort(src)
print des
