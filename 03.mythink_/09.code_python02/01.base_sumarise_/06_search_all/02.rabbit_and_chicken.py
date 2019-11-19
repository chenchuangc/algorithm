#!/bin/python
# -*- encoding:UTF-8 -*-


# 鸡兔同笼问题。有鸡和兔在一个笼子中，数头共 50 个头，数脚共 120 只脚，问：鸡和兔分别有多少只


def get_rabbit_chicken():
    for r_num in range(50):
        c_num = 50 - r_num
        if r_num * 4 + c_num * 2 == 120:
            print "r:" + str(r_num) + " c:" + str(c_num)


get_rabbit_chicken()
