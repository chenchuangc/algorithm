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
# 使用递推方式，问题的规模i从1->j 对应的第三维度的p因i的不同而不同
# 这种使用递推的方式，需要填满所有的数组元素，看来还是很复杂的，尤其在面对这种层次比较多的情况
# 不行了，不行了，我写不出来了，太难了。。。
# god you do it ,you do it
# 现在想想，其实就是一个三维数组，初始化的时候第三维的长度就应该是方块的数量，
# 这里刚才做了一下测试，发现直接这样进行初始化的时候在处理过程中可能出现数组下标越界，
# 因为f(i,j)=max{f(i,p,len(p))+f(p+1,j-1)} 中的len(p) 可能是数组下标的最大值了，再和之前相同颜色的相加，肯定导致越界，
# 所以实际当中的初始化，是在i值越大，对应的数组长度应该越小的，但是计算起来比较麻烦，还不如直接这样
# 在实际的处理过程中增加一个剪枝处理就可以了，加油
#
# 所以对应的是n^4的时间复杂度
# 还要进一步改进才行啊


class Group:
    def __init__(self, color, num):
        self.color = color
        self.num = num


group_list = [Group(1, 1), Group(2, 4), Group(3, 3), Group(1, 1),Group(2,4)]


def get_best_score():
    global group_list
    group_len = len(group_list)
    total = 0
    for i in range(group_len):
        g = group_list[i]
        total += g.num
    dic = [[[0] * (total + 1) for i in range(group_len)] for j in range(group_len)]

    for i in range(group_len):
        for j in range(total + 1):
            dic[i][i][j] = j * j

    num = group_len
    for n in range(2, num + 1):
        for i in range(num - n + 1):
            j = i + n - 1
            pre_g = group_list[j - 1]
            cur2 = 0
            for ele in range(total + 1):
                cur1 = dic[i][j - 1][pre_g.num] + dic[j][j][ele]
                for k in range(i, j):
                    k_g = group_list[k]
                    # 这里需要做一个剪枝处理，因为这里的每个ele的最大值都是一样的，实际上是不太合理的
                    if group_list[k].color == group_list[j].color and (k_g.num + ele) <= total:
                        cur = dic[i][k][k_g.num + ele] + dic[k + 1][j - 1][group_list[j - 1].num]
                        if cur > cur2:
                            print str(cur) + "," + str(i) + "," + str(k) + "," + str(j)
                            cur2 = cur
                dic[i][j][ele] = max(cur1, cur2)
                if i==0 and (j==group_len-1) and ele==group_list[group_len-1].num:
                    break
    for ele in dic:
        print ele
    print "--------"
    print dic[0][4][4]

get_best_score()
