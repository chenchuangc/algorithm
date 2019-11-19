#!/bin/python
# -*- encoding:UTF-8 -*-

# 一百个钱买一百只鸡，是个典型的穷举法应用。问题描述：每只大公鸡值 5 个钱，每只母鸡值 3 个钱，每 3 只小鸡值 1 个钱，
# 现在有 100 个钱，想买 100 只鸡，问如何买？有多少种方法？

def buy_chicken_hundred():
    plan_list=[]
    # max_chicken = 100
    max_crow = 100 / 3
    max_goose = 100 / 5
    for goose_num in range(max_goose):
        for crow_num in range(max_crow):
            if goose_num * 5 + crow_num * 3 > 100:
                continue
            chicken_num = (100 - goose_num * 5 - crow_num * 3 ) * 3
            if  chicken_num+goose_num+crow_num==100:
                plan_list.append({"goose":goose_num,"crow":crow_num,"chicken":chicken_num})
    print plan_list



buy_chicken_hundred()
