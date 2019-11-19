# !/bin/python
# -*- encoding:UTF-8 -*-

# Jimmy 最近迷上了一款叫做“方块消除”的游戏。游戏的规则是：n 个带颜色的方块排成一列，
# 颜色相同的方块连成一个区域（如果两个相邻的方块颜色相同，则这两个方块属于同一个区域）。
# 游戏时，可以选择一个区域消除，如果消除区域的方块数为 x，则可以得到 x*x 的分数。
# 方块消除后右边的方块自动向左移动。游戏虽然很简单，但是要得高分也不容易，那么问题来了，最高可以得多少分


# 这个题其实是很有一些难度的，主要是数据建模这块儿，看了作者写的数据模型描述d{i,j,k} 但是感觉自己很难想出来这个东西，那么按照自己的思路走一走呢
# 假设问题是d{i,j} 标识第1组到第j组方块消除能够得到的最大值。那么这次选择有两个，一是消除第j个区域，
# 这样的话 d{i,j}=d{i,j-1}+len{j}^2
# 二是不消除第j个区域，
# 而是先消除m-(j-1)的区域，使j和m-1连接到一起，这样的话就可以，当然需要保证的j和m-1对应的区域是相同的颜色，要不然的话就没有必要做这一步了
# 为什么这样说呢，因为主要是针对j组做的操作，如果不能连到相同的颜色区域的话等于是和第一种有一部分重复了。
# 所以必须连到第一种区域的话才算是一个独立的子问题问题
# 怎么描述这个模型呢？
# 最开始想的，是直接用一个数组加枚举来描述问题，这样的话，假如想要使用这个模型呢，感觉还是比较困难的，所以可以进行适当的重建。




# 从这里引出的问题是，如何推演动态规划的递推公式，能够保证子问题之间的独立性，比如就像这里的消除方块的问题，动态规划的子问题划分，
# 想想作者的说的，产生子问题的动作必须是面对全局的，这样才能够满足无后向性，所以他并不是针对某一步的操作，而是相对于全局来说的。
# 比如背包问题中，划分的两个子问题，a,选择物品1，b,不选物品1， 这两个子问题是互斥的，而且，他们是相对整个问题的解来说的，而不是只是针对某一步的操作
# 如果从这个案例进行对比的话就更容易体会到消除星星的子问题的划分，最优子结构应该是1. 某个区域和其他区域连的上，2.某个区域和其他区域连不上
# 如果从这个思路进行思考，可能更加容易理解问题的划分

# 在这个问题上我经常陷入的一个烦恼当中的是，比如会想着最后一步是否是消灭第i组，但是很可能这个时候问题已经变了，不适用了，
# 也就是不存在是否消灭第i组这个问题了，这个时候这种递推也就是由问题的。
# 然而当我在思考背包问题的时候，我常常想的是这样划分子问题，比如最后一步有两种选择，装入i或者不装入i,这里是不是存在了一些思维误区呢？
# 总是带了一种时序问题，而且是最后一个操作步骤
# 看这里，作者强调了，阶段的划分不能针对某一个操作步骤，这样容易导致问题不满足无后向性，而是针对问题的规模进行推演。
# 之前也看了很多，比如有的人说，不知道状态的变化的就采用投石问路的方式，比如先装一个进入袋子里面再说的思路。
# 这个是针对状态的设计，哪些状态，但是找最优子结构的时候不能用这种方式，而是要用整个问题的全局方式去设计。

# 作者说的：
# 状态定义不针对某个阶段，而是针对这个问题之前的最优决策定义状态，然后结合后面的部分做当前问题的最优选择，
# 这样做的好处是在很多情况下分解的子问题天然具有无后向性。

# 阶段，直接是问题的规模比较合适，而不是把某个东西装进去。这样的抽象更加具备通用性。