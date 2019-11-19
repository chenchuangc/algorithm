# !/bin/python
# -*- encoding:UTF-8 -*-


# 没有刻度的 3 加仑水桶和 5 加仑水桶各一个，水无限使用，可以随时倒掉，也可以随时装满，请你替约翰找找到底有几种倒腾出 4 升水的方法
# 这个问题，如何找出所有的方法呢，使用深度优先搜索，但是如何界定这个路线是否走过呢，还是确定走的步数好了
import copy


class Stack:
    def __init__(self):
        self.container = [0] * 1000
        self.p = 0

    def get_container(self):
        return self.container

    def put(self, ele):
        self.container[self.p] = ele
        self.p += 1

    def get(self):
        if self.p == 0:
            return
        self.p -= 1
        return self.container[self.p]

    def empty(self):
        return self.p == 0


class Bucket:
    def __init__(self, cap, already):
        self.cap = cap
        self.already = already

    def is_full(self):
        return self.cap == self.already


class Status:
    def __init__(self, bucket_list, step):
        self.bucket_list = bucket_list
        self.step = step


def check_res(s):
    if s.bucket_list[1].already == 4:
        return True
    else:
        return False


def try_process(from_b, to_b, s, i):
    bucket_list = s.bucket_list
    b_s = bucket_list[from_b[i]]
    b_t = bucket_list[to_b[i]]
    bucket_list[2].already = 1000
    bucket_list[2].cap = 2000
    if b_s.already == 0 or b_t.is_full():
        return False
    else:
        t_free = b_t.cap - b_t.already
        if t_free > b_s.already:
            b_t.already += b_s.already
            b_s.already = 0
        else:
            b_t.already = b_t.cap
            b_s.already -= t_free
        return True


def is_not_precessed(stack, buckets):
    b1_use = buckets[0].already
    b2_use = buckets[1].already

    already_stack = copy.deepcopy(stack)
    while not already_stack.empty():
        one_b = already_stack.get().bucket_list
        if one_b[0].already == b1_use and one_b[1].already == b2_use:
            return False
    return True


def find(s, from_b, to_b, res, stack):
    if check_res(s):
        res.append(copy.deepcopy(stack))
        return
    for i in range(6):
        s_copy = copy.deepcopy(s)
        process_ = try_process(from_b, to_b, s_copy, i)
        # print process_
        if process_:
            step = "" + str(from_b[i]) + "-->" + str(to_b[i]) + "  " + str(s_copy.bucket_list[0].already) + str(
                s_copy.bucket_list[1].already) + str(s_copy.bucket_list[2].already)
            s_copy.step=step
            if is_not_precessed(stack, s_copy.bucket_list):
                # print step
                stack.put(s_copy)
                find(s_copy, from_b, to_b, res, stack)
                stack.get()


def get_four_water(bucket_list):
    s = Status(bucket_list, None)
    stack = Stack()
    stack.put(s)
    res = []
    from_b = [0, 0, 1, 1, 2, 2]
    to_b = [1, 2, 0, 2, 0, 1]
    find(s, from_b, to_b, res, stack)
    return res


bucket_list = [Bucket(3, 0), Bucket(5, 0), Bucket(2000, 1000)]
res = get_four_water(bucket_list)
print len(res)

for i in range(len(res)):
    arr = res[i]
    print arr
    print arr.p
    while not arr.empty():
        ele = arr.get()
        print ele.step
