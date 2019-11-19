#!/bin/python
# -*- encoding:utf-8 -*-

# 这里提供了一种更好的解决方案，就是字符串排列组合的实现的方式，有时候觉得，这种东西，并不是快速思考能够思考出来的，
# 可能这个算法在发明的时候消耗了发明人的很多时间，你在这里如果没有接触到，想快速的创造出来也是很难的。

# 这里使用的方式是固定父亲，然后进入子的迭代环节，同时将已经固定的父亲字符串也传给子，这样在迭代结束的时候子就能够将结果放到结果集当中了。


def exchange(strArr, start, i):
    temp = strArr[start]
    strArr[start] = strArr[i]
    strArr[i] = temp


def get_dis_order_basic(strArr, start, end, res):
    if start == end:
        print "-------"
        res.append("".join(strArr))
        return
    i = start
    while i <= end:
        if i == start or strArr[i] != strArr[start]:
            print "-s:" + str(start) + " i:" + str(i) + str(strArr)
            exchange(strArr, i, start)
            print "+s:" + str(start) + " i:" + str(i) + str(strArr)
            get_dis_order_basic(strArr, start + 1, end, res)
            exchange(strArr, start, i)
        i += 1


def get_dis_order(str):
    start = 0
    end = len(str) - 1
    res = []
    get_dis_order_basic(list(str), start, end, res)
    return res


#
# b = "abc"
# a = list(b)
# temp = a[0]
# a[0] = a[2]
# a[2] = temp
# print a
res = get_dis_order("3377")
print  res
