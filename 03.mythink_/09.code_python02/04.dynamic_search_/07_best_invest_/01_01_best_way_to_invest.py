# !/bin/python
# -*- encoding:UTF-8 -*-

# 动态规划理论最早被提出来的时候，是用来解决资源的有效分配问题。
# 这一课要介绍的投资问题，有个通用的模式，那就是总资源量有限，要分配给若干个项目，每个项目都有一个投入与收益的关系，
# 最终的问题是求如何规划在不同项目上的投资，使得收益能够最大化

# 假设有数量为 M 的资金，计划用于 N 个投资项目，每个项目投入的资金和获得的回报用一张二维表记录，
# 比如 f[i, x] 表示第 i 个项目投入 x 万元时能获取的收益。最后的问题是如何在这 N 个投资项目上分配这 M 万元的资金，使得最后的收益最大化。
# 比如有 600 万元，投资 3 个项目，每个项目的投资收益如下表所示
# 数据建模的话，如果使用三个数组标识三个项目，总共可以投三笔，
# f(i,j)标识有资金j,项目种类有i个，
# f(i,j)=max{f(i-1,j),max{f(i-1,j-u[i][k])+w[i][k]}}  0<=k<len(w[i])
# 感觉是一个二维的计算，但是用的时候又变成了三维的，好难受啊
# 感觉只要是公式分析对了，把依赖关系整好了，也就差不多了，这个问题自己没有看答案，直接出来，好棒啊


class Invest:
    def __init__(self, pay, back):
        self.pay = pay
        self.back = back


invest_items = [
    [Invest(1, 60), Invest(2, 80), Invest(3, 105)],
    [Invest(1, 65), Invest(2, 85), Invest(3, 110)],
    [Invest(1, 75), Invest(2, 100), Invest(3, 120)]
]


def get_best_invest():
    global invest_items
    # total = 6
    harvest = [[0] * 4 for i in range(7)]
    path = [[0] * 4 for i in range(7)]
    for money in range(7):
        harvest[money][0] = 0
    for i in range(4):
        harvest[0][i] = 0

    # j代表投资额度，i代表第i个项目
    for j in range(1, 7):
        for i in range(1, 4):
            cur_max = 0
            for k in range(3):
                if j >= invest_items[i - 1][k].pay:
                    print j
                    cur = harvest[j - invest_items[i - 1][k].pay][i - 1] + invest_items[i - 1][k].back
                    if cur > cur_max:
                        cur_max = cur
                        path[j][i] = k
            harvest[j][i] = max(harvest[j][i - 1], cur_max)
    for ele in harvest:
        print ele
    return harvest, path


def print_path(i, j, p):
    global invest_items
    path = [0] * i
    while i > 0:
        k = p[j][i]
        path[i - 1] = k
        j = j - invest_items[i - 1][k].pay
        i -= 1
    print path


h, p = get_best_invest()
i = 3
j = 6
print "========="
print_path(i, j, p)
