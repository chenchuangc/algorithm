#!/bin/python
# -*- coding:utf-8 -*-

'''

再来看一个完整的例子：警察抓了 A、B、C、D 四名罪犯，其中一名是小偷，审讯的时候：
A说：“我不是小偷。”   
B说：“C 是小偷。”     
C说：“小偷肯定是 D。” 
D说：“C 是在冤枉人。”

'''


def who_is_thief():
    for i in range(1, 5):
        x = i
        exp1 = 1 if x != 1 else 0
        exp2 = 1 if x == 3 else 0
        exp3 = 1 if x == 4 else 0
        exp4 = 1 if x != 4 else 0
        #exp4 = 1 - exp3
        sum_ = exp1 + exp2 + exp3 + exp4
        if sum_ == 3:
            print "thief is :" + str(i)
            break


who_is_thief()
