# !/bin/python
# -*- encoding:UTF-8 -*-

# 装配线问题，有两条装配线，均有n个工位，每个工位的耗时分为三种 come_in_time, op_time, out_time.
# 问题是遇到加急件的时候在这两个线上找到一个最快的线路

# 动态规划的要义 状态，状态转移方程式，最优子结构，边界
# 状态有，line_num,op_num,use_time
# 递推公式f(line,op_num_n)=min{{f(line,op_num_n-1)+op(line,op_num_n)},{f((line+1)%2),op_num_n-1)}+out((line+1)%2),op_num_n-1))+in(line,op_num_n)+op(line,op_num_n)}

# 在记录的path的时候出现了思维混乱，很多时候出现思维混乱的原因是不能通过感性思维直接找到问题的解，导致了思维陷入混乱，
# 这个时候就要想想，能否使用递推的方式找到方案，这里也是可以使用递推的方式来进行求解的，孩子都返回自己的结果，父亲进行选择
# 父亲再将自己的结果与孩子的结果合并
# 在面对问题，无从下手的时候，不妨多问一个what,多问一个是什么，比如这里的要记录路径，每次迭代要记录的路径具体是啥，用数据表示出来是啥


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
    if op_num == 0:
        return line_group[line_num][op_num].in_time + line_group[line_num][op_num].op_time, []

    time01, step_recored01 = get_best_way(line_num, op_num - 1, line_group)
    time01 += line_group[line_num][op_num].op_time
    another_line = (line_num + 1) % 2

    time02, step_recored02 = get_best_way(another_line, op_num - 1, line_group)
    time02 += line_group[another_line][op_num - 1].out_time + line_group[line_num][op_num].in_time + \
              line_group[line_num][op_num].op_time

    if time01 < time02:
        step_record = step_recored01
        step_record.append("line:" + str(line_num) + " op:" + str(op_num - 1))
    else:
        step_record = step_recored02
        step_record.append("line:" + str(another_line) + " op:" + str(op_num - 1))

    return min(time01, time02), step_record


t1, r1 = get_best_way(0, 5, lines)
t1 += lines[0][5].out_time
r1.append("line:0 op:5")
print t1
print r1

t2, r2 = get_best_way(1, 5, lines)
t2 += lines[1][5].out_time
r2.append("line:1 op:5")
print t2
print r2
