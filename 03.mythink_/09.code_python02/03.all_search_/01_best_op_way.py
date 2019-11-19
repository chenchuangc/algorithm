# !/bin/python
# -*- encoding:UTF-8 -*-

# 感觉只要把数据结构理清楚了，慢慢也就能写出来了
# 装配线的安排，主要还是对数据状态的分析,使用递归的方式进行遍历，
# 这里的step记录使用的是数组而不是stack,只是巧合让这些刚好满足，因为装配线的位置是有序列号的，
# 所以每次按照所以操作数组的话会自动进行覆盖，所以不再需要进行出栈入栈的操作了，这个算是歪打正着吧

class Op:
    def __init__(self, in_time, out_time, op_time):
        self.in_time = in_time
        self.out_time = out_time
        self.op_time = op_time


line01 = [Op(2, 3, 10),
          Op(1, 1, 2),
          Op(2, 3, 10),
          Op(4, 3, 10),
          Op(2, 1, 10),
          Op(2, 3, 1)]

line02 = [Op(2, 3, 10),
          Op(1, 1, 30),
          Op(2, 1, 1),
          Op(1, 3, 30),
          Op(2, 1, 1),
          Op(2, 2, 10)]

line_group = [line01, line02]


def find(line_group, cur_line, cur_op_num, cur_best, cur, best_step, cur_step):
    cur_time = cur
    cur_op = line_group[cur_line][cur_op_num]
    total_line = len(line_group)
    total_op_num = len(line_group[0])
    print cur_op_num
    cur_step[cur_op_num] = str(cur_line) + "_" + str(cur_op_num)+"_"+str(cur)
    if cur_op_num == total_op_num - 1:
        cur += (cur_op.out_time + cur_op.op_time)
        if cur < cur_best[0]:
            cur_best[0] = cur
            for i in range(total_op_num):
                best_step[i] = cur_step[i]
        return

    find(line_group, cur_line, cur_op_num + 1, cur_best, cur_time + cur_op.op_time, best_step, cur_step)
    next_line = (cur_line + 1) % total_line
    next_op = line_group[next_line][cur_op_num + 1]
    find(line_group, next_line, cur_op_num + 1, cur_best, cur_time + cur_op.op_time + cur_op.out_time + next_op.in_time,
         best_step, cur_step)


def find_best_way(line_group):
    cur_line = 0
    cur_op_num = 0
    cur_best = [10000]  # 0位存放当前最好的
    cur = 0
    best_step = [0] * 6
    cur_step = [0]* 6
    find(line_group, cur_line, cur_op_num, cur_best, cur, best_step, cur_step)
    best01 = cur_best[0] + line_group[cur_line][cur_op_num].in_time

    cur_line = 1
    cur_best = [10000]
    best_step02 = [0] * 6
    cur_step02 = [0] * 6
    find(line_group, cur_line, cur_op_num, cur_best, cur, best_step02, cur_step02)
    best02 = cur_best[0] + line_group[cur_line][cur_op_num].in_time
    print best01, best02
    print best_step
    print best_step02
    return min(best01, best02)


print find_best_way(line_group)
