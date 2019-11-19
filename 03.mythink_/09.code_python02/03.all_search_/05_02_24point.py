# !/bin/python
# -*- encoding:UTF-8 -*-

# 4个数字3，3，7，7 各种运算符，求24点的运算表达式
# 因为元素中有重复的，这里尝试使用了去重的排列组合，发现效果不是很好，原来只能去掉一部分的重，所以还是可能导致结果重复，但是相对会少一些

def fun_mul(x, y):
    return True, x * y, "*"


def fun_add(x, y):
    return True, x + y, "+"


def fun_sub(x, y):
    return True, x - y, "-"


def fun_div(x, y):
    if y == 0:
        return False, 0, "/"
    return True, x / y, "/"


def exchange(strArr, start, i):
    temp = strArr[start]
    strArr[start] = strArr[i]
    strArr[i] = temp


action = [fun_add, fun_sub, fun_mul, fun_div]

ele = [3.0, 3.0, 7.0, 7.0]


def filter_res(bak, act):
    res = bak[0]
    s = ""
    for i in range(1, 4):
        can, res, p = act[i - 1](res, bak[i])
        if not can:
            return
        s += " " + p
    if abs(res - 24) < 0.1:
        print bak
        print s
        # print act

    # print bak


def action_iter(bak):
    act = [0] * 3
    for first in range(4):
        for sec in range(4):
            for third in range(4):
                act[0] = action[first]
                act[1] = action[sec]
                act[2] = action[third]
                filter_res(bak, act)


def do_iter(index):
    if index == 4:
        action_iter(ele)
    s = index
    for i in range(s, 4):
        if i > s and ele[i] == ele[s]:
            continue
        exchange(ele, i, s)
        do_iter(s + 1)
        exchange(ele, i, s)


def ele_iter():
    do_iter(0)

    # bak = [0] * 4
    # for first in range(4):
    #     for sec in range(4):
    #         if sec == first:
    #             continue
    #         for third in range(4):
    #             if third == first or third == sec:
    #                 continue
    #             for fourth in range(4):
    #                 if fourth == first or fourth == sec or fourth == third:
    #                     continue
    #                 bak[0] = ele[first]
    #                 bak[1] = ele[sec]
    #                 bak[2] = ele[third]
    #                 bak[3] = ele[fourth]
    #                 action_iter(bak)


ele_iter()
# print 3.0==3.0
