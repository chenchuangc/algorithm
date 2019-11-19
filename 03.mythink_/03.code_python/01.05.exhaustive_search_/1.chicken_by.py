#!/usr/bin/python
# -*- encoding:UTF-8 -*-

'''
问题描述：每只大公鸡值 5 个钱，每只母鸡值 3 个钱，每 3 只小鸡值 1 个钱，现在有 100 个钱，想买 100 只鸡，问如何买？有多少种方法？
'''

'''
这里的问题的解的变量有三个，大公鸡，母鸡，小鸡的个数，搜索的方式也很简单，直接针对解中的三个维度进行穷举即可,不需要做太多状态上的规划

'''
def buy():
    roosters_max=100/5
    hens_max=100/3
    res=[]
    for rooster_num in range(roosters_max):
        for hen_num in range(hens_max):
            chicks=100-rooster_num-hen_num
            if(chicks%3==0):
                if rooster_num*5+hen_num*3+chicks/3==100:
                    print 'r:'+str(rooster_num)+" h:"+str(hen_num)+ " c:"+str(chicks)

print 'start...'
buy()
