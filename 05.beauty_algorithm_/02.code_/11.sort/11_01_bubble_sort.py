#!/bin/python
# -*- encoding:UTF-8 -*-

# 冒泡排序，每次将当前最大值归位，
# 假设数组大小为n,则需要进行n次大循环
# 在每次循环的时候执行的是对比次数都比上一轮小1
# 边界，数组不能越界，实际上只需要n-1轮比较久可以了，n-1个都归位了，剩下的一个自然就归位了



def bubble_sort(src, n):
    if n <= 1:
        return
    for i in reversed(range(1, n)):
        is_change = False
        for j in range(i):
            if src[j] > src[j + 1]:
                temp = src[j]
                src[j] = src[j + 1]
                src[j + 1] = temp
                is_change = True
        if not is_change:
            return


arr = [5, 4, 3, 1]
bubble_sort(arr, 4)
print(arr)
