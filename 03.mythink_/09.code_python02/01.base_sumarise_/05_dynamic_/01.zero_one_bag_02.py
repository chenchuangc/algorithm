#!/bin/python
# -*- encoding:UTF-8 -*-

# 0,1背包问题，状态应该有，背包已经装进去的东西，背包剩余容量，背包已有价值，剩余可选物品。这个应该是状态
# 阶段，就是背包已经做了某些选择的结果
# 选用广域搜索的方式，倒推
# f(xn,cap1)=max{f(xm,cap1),f(xm,cap2)} xn=xm+1 cap1=cap2+k
# 边界问题，也就是推演的最终结束的地方
#
# 这里使用从下到上面的递推方式
# 这种使用自下到上的方式进行推算的原因也是因为动态规划并不等于穷举的状态，只要求出了其中的一种状态即可。
# 其实这离的goods_list本来是无序的，但是在这里强行被我们定义成了有序的，就是为了求解问题方便
# 为了找出阶段，我们可以随便放进去一个商品，看看有哪些地方发生了变化
# 这里如果想对操作的步骤进行记忆的话，也需要使用一个二维数组进行记忆才能够得到，每个方格记录截止到当前方格的选择


def get_child(temp, bag_weight, goods_index):
    if bag_weight < 1 or goods_index < 0:
        return 0
    else:
        return temp[bag_weight][goods_index]


def max_worth(bag_weight, goods_list):
    temp = [[0] * len(goods_list) for i in range(bag_weight + 1)]  # 这个可以用来做备忘录算法使用
    goods_list = sorted(goods_list, cmp=lambda x, y: cmp(x["weight"], y["weight"]))
    for wei in range(1, bag_weight + 1):
        # print str(wei)
        for goods_index in range(len(goods_list)):
            a_goods = goods_list[goods_index]
            child1 = get_child(temp, wei, goods_index - 1)
            if wei >= a_goods["weight"]:
                child2 = get_child(temp, wei - a_goods["weight"], goods_index - 1) + a_goods["worth"]
                best = max(child2, child1)
            else:
                best = child1
            temp[wei][goods_index] = best
    # print temp
    for i in range(len(temp)):
        print temp[i]


bag_cap = 11
goods_list = [{"weight": 2, "worth": 4},
              {"weight": 1, "worth": 4},
              {"weight": 3, "worth": 4},
              {"weight": 5, "worth": 10},
              {"weight": 3, "worth": 5},
              {"weight": 4, "worth": 3},
              {"weight": 6, "worth": 30}]

print max_worth(bag_cap, goods_list)

# a = [[1] * 3 for i in range(2)]
# print  a[1][1]
print range(1, 3)
