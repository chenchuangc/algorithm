# !/bin/python
# -*- encoding:UTF-8 -*-

# 承接01，这个问题，重要的地方不仅仅是递推，还有数据的建模，如果一开始不能设计出比较有效的模型，假如使用你最开始的想法，直接是数组+枚举
# 则后面基本上没有办法设计出有效的算法

# 好了，小面我们借鉴作者的建模思路，后面尝试自己对问题的递推作出递推设计
# 数据模型转为多个连续的group，每个group有颜色，数量的属性
# 问题的最优子结构，按照问题的规模进行划分，减掉空间上的最后一个group,变化是从f(j)变成了f(j-1),关系呢，
# 第一种是 先消灭掉第j组，那么问题就直接从f(j)变成了f(j-1)  f(j)=f(j-1)+len(j)^2
# 第二种是 不是先不消灭第j组，因为简单这样的分离的话是和第一种有重复的，包含了第一种，那么就没有意义了。
# 所以，第二种情况应该是第j组和前面的某一个进行了连接，这样的话才会构成不同于一的问题，因为这样的话就不是第j组单独被消除了，
# 否则，只要第j组单独被消除了，就会和第一种子结构重复。这样的话，问题的推演就更加复杂了。可以想得到，这样的可能需要消灭掉p+1->j-1 的group(p必须是和j一样的颜色)
# 这样的子结构就需要再引入一个一个变量组的开始处i,其实对于第一种最优子结构也是有的，只是默认组的开始处是0罢了，这个时候递推编程了
# f(i,j)=max{f(i,p)+f(p+1,j-1)}  这里的p因为和j进行了合并len(p)会变大。
# 猛一看这个以为可以使用递推解决，但是仔细思考发现这里的f(i,p)中的len(p)会变大，所以并不能直接使用子问题的结果，
# 如果想用的话需要增加一个维度，p后面合进来了多少数据，但是这个合进来多少就不好定了吧，可以尝试一下，p的大小小于等于所有同色的
# 递推的时候变成三维的
# f(i,j)=max{f(i,p,len(p))+f(p+1,j-1)}

# 边界呢：f(i,i)=len(i)^2
# 下面尝试使用作者的备忘录算法的模式进行

class Group:
    def __init__(self, color, num):
        self.color = color
        self.num = num


group_list = [Group(1, 1), Group(2, 4), Group(3, 3), Group(1, 1), Group(2, 5)]


def find_best(start, end, num, dic):
    print "" + str(start) + "," + str(end) + "," + str(num) + "-----"
    global group_list
    if start > end:
        print "???"
        return 0
    if start == end:
        dic[start][end][num] = num * num
        return dic[start][end][num]
    if dic[start][end][num] > 0:
        return dic[start][end][num]
    # 这个地方需要注意，加的是num*num
    dic[start][end][num] = find_best(start, end - 1, group_list[end - 1].num, dic) + num * num
    for k in range(start, end):
        if group_list[k].color == group_list[end].color:
            # 这里也是，第end的num现在已经不是数组中标识的了，而是num
            cur = find_best(start, k, group_list[k].num + num, dic)
            print "" + str(start) + "," + str(k) + "," + str(end) + ":" + str(cur) + ":" + str(
                group_list[k].num + group_list[end].num)
            cur += find_best(k + 1, end - 1, group_list[end - 1].num, dic)
            print "" + str(start) + "," + str(k) + "," + str(end) + ":" + str(cur)
            if cur > dic[start][end][num]:
                dic[start][end][num] = cur
    return dic[start][end][num]


def get_best_score():
    global group_list
    group_len = len(group_list)
    total = 0
    for i in range(group_len):
        g = group_list[i]
        total += g.num
    dic = [[[0] * (total + 1) for i in range(group_len)] for j in range(group_len)]

    aa = find_best(0, 4, 5, dic)
    for i in dic:
        print i
    return aa


print get_best_score()
