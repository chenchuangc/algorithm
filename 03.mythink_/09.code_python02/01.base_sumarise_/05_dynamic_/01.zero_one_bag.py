#!/bin/python
# -*- encoding:UTF-8 -*-

# 0,1背包问题，状态应该有，背包已经装进去的东西，背包剩余容量，背包已有价值，剩余可选物品。这个应该是状态
# 阶段，就是背包已经做了某些选择的结果
# 选用广域搜索的方式，倒推
# f(xn,cap1)=max{f(xm,cap1),f(xm,cap2)} xn=xm+1 cap1=cap2+k
# 边界问题，也就是推演的最终结束的地方

# 通过下面的背包问题，可以发现确实是比穷举搜索要优秀一些，
# 因为他等于对问题的做了限制，只需要对全部子问题的一部分子问题进行求解就可以得到了问题的解
# 下面的把goods放到一个数组当中，并且进行了排序，并没有对goods进行全量的排列组合，但是也能够得到最优解的，从这一点来书他是大大优于穷举的
# 也正是因为不需要进行穷举，所以，可以使用一个数组来逆过来进行计算，可以大大减小需要的存储空间
# 也就是直接从简单的问题累计推到大问题上面
# 因为你使用备忘录的时候实际上就是不断在填写数组的过程，逆过来想，只要用一种方式把数组填满就行了

# 这个地方保存了上一步的选择，可以进行回溯，进而知道到底选择了哪些goods,想进行回溯的话只能将中间的结果拓展为一个map，来记录上一步的选择

# bag = {"total": 0, "left": 0, "worth": 0, "goods_list": []}
# goods = {"weight": 0, "worth": 0}
from copy import deepcopy

temp = ''


def get_temp(row, col):
    global temp
    if row < 1 or col < 0:
        return {"worth": 0, "cur_goods": -1}
    else:
        return deepcopy(temp[row - 1][col])


def set_temp(row, col, val):
    global temp
    if row >= 1 and col >= 0:
        # print "row:" + str(row - 1) + " col:" + str(col) + " val:" + str(val)
        temp[row - 1][col] = val


def get_max(bag_weight, goods_list, end_index):
    global temp
    # print temp
    if end_index < 0:
        return {"worth": 0, "cur_goods": -1}

    if bag_weight < goods_list[0]["weight"]:
        print "bw:  " + str(bag_weight)
        return {"worth": 0, "cur_goods": -1}

    while bag_weight < goods_list[end_index]["weight"]:
        end_index -= 1

    a_goods = goods_list[end_index]
    child1 = get_temp(bag_weight, end_index - 1)
    if child1["worth"] == 0:
        child1 = get_max(bag_weight, goods_list, end_index - 1)
        set_temp(bag_weight, end_index - 1, child1)
    child2 = get_temp(bag_weight - a_goods["weight"], end_index - 1)
    print str(child2)
    if child2["worth"] == 0:
        child2 = get_max(bag_weight - a_goods["weight"], goods_list, end_index - 1)
    print str(child2)
    child2["worth"] = child2["worth"] + a_goods["worth"]
    child2["cur_goods"] = end_index
    set_temp(bag_weight, end_index, child2)
    # set_temp(bag_weight - a_goods["weight"], end_index - 1, child2)

    print "bw-:" + str(bag_weight - a_goods["weight"]) + " c2:" + str(child2["worth"]) + " goods:" + str(a_goods)

    print "bag_w:" + str(bag_weight) + " end_i:" + str(end_index) + " child1:" + str(
        child1["worth"]) + " child2:" + str(child2["worth"]) + "  choose:" + str(child1["worth"] < child2["worth"])

    if child1["worth"] < child2["worth"]:
        res = {"worth": child2["worth"], "pre": child2, "cur_goods": end_index}
    else:
        res = {"worth": child1["worth"], "pre": child1, "cur_goods": -1}
    return res


def max_worth(bag_weight, goods_list):
    global temp
    temp = [[{"worth": 0, "cur_goods": -1}] * len(goods_list) for i in range(bag_weight)]  # 这个可以用来做备忘录算法使用
    goods_list = sorted(goods_list, cmp=lambda x, y: cmp(x["weight"], y["weight"]))
    return get_max(bag_weight, goods_list, len(goods_list) - 1)


bag_cap = 11
goods_list = [{"weight": 2, "worth": 4},
              {"weight": 1, "worth": 4},
              {"weight": 3, "worth": 4},
              {"weight": 5, "worth": 4},
              {"weight": 3, "worth": 5},
              {"weight": 4, "worth": 7},
              {"weight": 6, "worth": 30}]

print max_worth(bag_cap, goods_list)
