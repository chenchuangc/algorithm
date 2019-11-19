# !/bin/python
# -*- encoding:UTF-8 -*-

# 4个数字3，3，7，7 各种运算符，求24点的运算表达式
# 这里使用的是分治的方法，
# 分治有两种，一种是缩减问题的规模，但是求解的结果不变， 一种是缩减问题的规模，同时缩减问题的规模
# 思考的时候形成一定的思考方式，尝试使用两种，第一种有时候没有那么直观，但是按照这个思考方式是可以的

import copy


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


class Stack:
    def __init__(self):
        self.container = [0] * 1000
        self.p = 0

    def get_container(self):
        return self.container

    def put(self, ele):
        self.container[self.p] = ele
        self.p += 1

    def get(self):
        if self.p == 0:
            return
        self.p -= 1
        return self.container[self.p]

    def peek(self):
        return self.container[self.p - 1]

    def empty(self):
        return self.p == 0


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


def do_iter(src, want, step, res):
    if len(src) == 1 and abs(src[0] - want) < 0.01:
        res.append(copy.deepcopy(step))
        return
    len_s = len(src)
    for i in range(len_s):
        for j in range(len_s):
            if j == i:
                continue
            for a_i in range(4):
                can, a_res, p = action[a_i](src[i], src[j])
                a_src = [a_res]
                for copy_i in range(len_s):
                    if copy_i != i and copy_i != j:
                        a_src.append(src[copy_i])
                step.put("(" + str(src[i]) + p + str(src[j]) + ")")
                do_iter(a_src, want, step, res)
                step.get()


def ele_iter():
    step = Stack()
    res = []
    do_iter(ele, 24, step, res)
    print len(res)
    len_r = len(res)
    for i in range(len_r):
        stack = res[i]
        while not stack.empty():
            s = stack.get()
            print s
        print "----------"

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
