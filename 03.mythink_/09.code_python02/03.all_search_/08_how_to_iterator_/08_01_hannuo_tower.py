# !/bin/python
# -*- encoding:UTF-8 -*-

# 有三根杆子A，B，C。A杆上有 N 个 (N>1) 穿孔圆盘，盘的尺寸由下到上依次变小。要求按下列规则将所有圆盘移至 C 杆：
# 每次只能移动一个圆盘；
# 大盘不能叠在小盘上面。
# 提示：可将圆盘临时置于 B 杆，也可将从 A 杆移出的圆盘重新移回 A 杆，但都必须遵循上述两条规则。
# 问：如何移？最少要移动多少次？

# 这个是经典的汉诺塔问题，我们可以使用递推的方式进行 假如能够完成f(n)到b上，那就一定能够完成f(n+1)到c上面，
# 因为，完成了f(n)，只需要将第n+1个移动到c,后面的问题就等于f(n)了

# 递归的的主体是父亲在还在的基础上移动一个盘子，然后再转化问题
# 问题的描述变量 当前盘子数，当前盘子在哪个杆上，移动的目标是到哪个盘上面，
# that is ok


def han_nuo_tower(n, s_index, target_index, step_list):
    if n == 1:
        step_list.append(str(s_index) + "->" + str(target_index))
        return
    mid_target_index = (1 + 2 + 3) - s_index - target_index
    han_nuo_tower(n - 1, s_index, mid_target_index, step_list)
    step_list.append(str(s_index) + "->" + str(target_index))
    han_nuo_tower(n - 1, mid_target_index, target_index, step_list)


step = []
han_nuo_tower(4, 1, 3, step)
print step
