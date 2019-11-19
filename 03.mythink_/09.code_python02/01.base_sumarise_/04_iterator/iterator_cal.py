#!/bin/python
# -*- encoding:UTF-8 -*-
# 平方根的迭代公式是  (x+a/x)/2
# 需要注意的是用浮点计算


def square_cal(target):
    x1 = 1
    x2 = x1
    precise = 0.001
    step_total = 5
    step = 0
    while step < step_total:
        x2 = (x1 + target / x1) / 2.0
        if abs(x2 - x1) < precise:
            break
        x1 = x2
        step += 1
        print "step:" + str(step)
    return x2


print square_cal(7)
