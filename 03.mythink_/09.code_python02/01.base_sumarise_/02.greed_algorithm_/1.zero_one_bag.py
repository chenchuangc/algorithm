#!/bin/python
# -*- encoding:utf-8 -*-

# 有 N 件物品和一个承重为 C 的背包（也可定义为体积），每件物品的重量是 wi，价值是 pi，
# 求解将哪几件物品装入背包可使这些物品在重量总和不超过 C 的情况下价值总和最大
# 从下面的样例中也可以看出，贪心原则很难找到最优解，其解的有效性取决于贪心策略，当然，很多情况下贪心原则要比随机的选取要优秀

bag = {"total": 0, "left": 0, "worth": 0, "goods_list": []}
goods = {"weight": 0, "worth": 0}


def greed_stragy():
    return


# 贪婪算法，按照价值最大的进行选择
def get_max_worth_by_worth(bag_cap, goods_list):
    goods_list_sorted = sorted(goods_list, cmp=lambda x, y: cmp(x.get("worth"), y.get("worth")), reverse=True)
    big_worth_index = 0
    cur_bag = {"total": bag_cap, "left": bag_cap, "worth": 0, "goods_list": []}
    while big_worth_index < len(goods_list) and cur_bag.get("left") > 0:
        cur_big_worth_goods = goods_list_sorted[big_worth_index]
        if cur_big_worth_goods.get("weight") <= cur_bag.get("left"):
            cur_bag["worth"] += cur_big_worth_goods.get("worth")
            cur_bag["left"] -= cur_big_worth_goods.get("weight")
            cur_bag["goods_list"].append(cur_big_worth_goods)
        big_worth_index += 1
    print cur_bag


# 贪婪算法，按照重量来进行
def get_max_worth_by_weight(bag_cap, goods_list):
    goods_list_sorted = sorted(goods_list, cmp=lambda x, y: cmp(x.get("weight"), y.get("weight")))
    min_weight_index = 0
    cur_bag = {"total": bag_cap, "left": bag_cap, "worth": 0, "goods_list": []}
    while min_weight_index < len(goods_list) and cur_bag["left"] > 0:
        cur_goods = goods_list_sorted[min_weight_index]
        if cur_goods["weight"] <= cur_bag["left"]:
            cur_bag["worth"] += cur_goods["worth"]
            cur_bag["left"] -= cur_goods["weight"]
            cur_bag["goods_list"].append(cur_goods)
        min_weight_index += 1
    print cur_bag


bag_cap = 10
goods_list = [{"weight": 4, "worth": 3}, {"weight": 3, "worth": 5}, {"weight": 2, "worth": 8},
              {"weight": 6, "worth": 30}]
get_max_worth_by_worth(bag_cap, goods_list)
print '------------------------'
get_max_worth_by_weight(bag_cap, goods_list)
