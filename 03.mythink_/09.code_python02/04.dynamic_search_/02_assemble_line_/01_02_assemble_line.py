# !/bin/python
# -*- encoding:UTF-8 -*-

# 装配线问题，有两条装配线，均有n个工位，每个工位的耗时分为三种 come_in_time, op_time, out_time.
# 问题是遇到加急件的时候在这两个线上找到一个最快的线路

# 承接01，这里主要是进行改造，添加备忘录或者是逆过来的存储的方式，
# 其实很多时候通过递推关系式，直接就可以选择从最小子问题直接一步一步递推出要求解的问题
# 在面对这个问题的时候，使用递推的时候不知道怎么建模了，感觉二维数组满足不了需求了，
# 脑袋里面的模式只有背包问题的那种模式，对于这种更简单一些的竟然失去了建模的能力
# 明显的，可以使用二维数组，简单就ko了，就是求解的方式因为递推的方式不太一样，所以有一点不一样，只要是真实的表达了模型就好了

# 这里实际上只是需要使用二维数组就行了，

class Result:
    def __init__(self, line_num, op_num):
        self.line_num = line_num
        self.op_num = op_num
        self.res_time = [[0] * self.op_num for i in range(line_num)]
        self.path = [[0] * self.op_num for i in range(line_num)]
        self.total_time = 0
        self.final_line = 0


class Op:
    def __init__(self, in_time, out_time, op_time):
        self.in_time = in_time
        self.out_time = out_time
        self.op_time = op_time


class Step:
    def __init__(self, line, op_num, pre):
        self.line = line
        self.op_num = op_num
        self.pre = pre


line01 = [Op(2, 3, 10),
          Op(1, 1, 2),
          Op(2, 3, 10),
          Op(4, 3, 10),
          Op(2, 1, 10),
          Op(2, 3, 1)]

line02 = [Op(2, 3, 10),
          Op(1, 1, 30),
          Op(2, 1, 10),
          Op(1, 3, 30),
          Op(2, 1, 1),
          Op(2, 2, 10)]

lines = [line01, line02]


def get_best_way(line_num, op_num, line_group):
    res = Result(line_num, op_num)
    res.res_time[0][0] = line_group[0][0].in_time + line_group[0][0].op_time
    res.res_time[1][0] = line_group[1][0].in_time + line_group[1][0].op_time
    for i in range(1, 6):
        if res.res_time[0][i - 1] < res.res_time[1][i - 1] + line_group[1][i - 1].out_time + line_group[0][i].in_time:
            res.res_time[0][i] = res.res_time[0][i - 1] + line_group[0][i].op_time
            res.path[0][i] = 0
        else:
            res.res_time[0][i] = res.res_time[1][i - 1] + line_group[1][i - 1].out_time + line_group[0][i].in_time + \
                                 line_group[0][i].op_time
            res.path[0][i] = 1

        if res.res_time[1][i - 1] < res.res_time[0][i - 1] + line_group[0][i - 1].out_time + line_group[1][i].in_time:
            res.res_time[1][i] = res.res_time[1][i - 1] + line_group[1][i].op_time
            res.path[1][i] = 1
        else:
            res.res_time[1][i] = res.res_time[0][i - 1] + line_group[0][i - 1].out_time + line_group[1][i].in_time + \
                                 line_group[1][i].op_time
            res.path[1][i] = 0
    if res.res_time[0][5] + line_group[0][5].out_time < res.res_time[1][5] + line_group[1][5].out_time:
        res.total_time = res.res_time[0][5] + line_group[0][5].out_time
        res.final_line = 0
    else:
        res.total_time = res.res_time[1][5] + line_group[1][5].out_time
        res.final_line = 1

    print res.total_time
    print res.final_line
    print res.res_time
    print res.path


get_best_way(2, 6, lines)
