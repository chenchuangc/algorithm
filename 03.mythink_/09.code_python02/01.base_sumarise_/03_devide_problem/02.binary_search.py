#!/bin/python
# -*- encoding:utf-8 -*-


def find(src_arr, start, end, dest):
    if start > end:
        return -1
    expect_pos = (start + end) / 2
    if src_arr[expect_pos] == dest:
        return expect_pos
    elif src_arr[expect_pos] > dest:
        return find(src_arr, start, expect_pos - 1, dest)
    else:
        return find(src_arr, expect_pos + 1, end, dest)


#
def find_target(src_arr, dest):
    start = 0
    end = len(src_arr) - 1
    return find(src_arr, start, end, dest)


arr = [2, 3, 5, 7, 8, 9, 12, 14]
print find_target(arr, 5)
print '---------'
print find_target(arr, 13)
