# !/bin/python
# -*- encoding:UTF-8 -*-

# 这个是对02——01的一个改进，想了好久没有想到深度优先搜索应该怎么处理能够达到全量搜索的效果，
# 可以使用一个栈存储过程中的每个点，在处理完以后再出栈。这样就能够形成一个良性循环，这样的话判断是否有环的话只是在一次深度的结果中进行搜索，就简单了很多
# bucket对象，动作遍历，当前遍历路径的存储


import copy


# import Queue

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


class Status:
    def __init__(self, bucket_list, step):
        self.bucket_list = bucket_list
        self.step = step


def try_process(from_b, to_b, i, buckets):
    bucket_s = buckets[from_b[i]]
    buckets_t = buckets[to_b[i]]
    if bucket_s.already == 0:
        return False
    if buckets_t.cap == buckets_t.already:
        return False
    target_free = buckets_t.cap - buckets_t.already
    if target_free > bucket_s.already:
        buckets_t.already += bucket_s.already
        bucket_s.already = 0
    else:
        use = buckets_t.cap - buckets_t.already
        buckets_t.already = buckets_t.cap
        bucket_s.already = bucket_s.already - use
    return True


def is_not_precessed(stack, buckets):
    b1_use = buckets[0].already
    b2_use = buckets[1].already
    b3_use = buckets[2].already

    already_stack = copy.deepcopy(stack)
    while not already_stack.empty():
        one_b = already_stack.get().bucket_list
        if one_b[0].already == b1_use and one_b[1].already == b2_use and one_b[2].already == b3_use:
            return False
    return True


def check_answer(buckets):
    if buckets[1].already == 4 and buckets[2].already == 4:
        return True
    else:
        return False


def find(from_b, to_b, stack, answer):
    cur = stack.get()
    # print cur
    stack.put(cur)
    if check_answer(cur.bucket_list):
        answer.append(copy.deepcopy(stack))
        return
    for i in range(6):
        state = copy.deepcopy(cur)
        if try_process(from_b, to_b, i, state.bucket_list):
            state.step = "" + str(from_b[i]) + "-->" + str(to_b[i]) + "  " + str(state.bucket_list[0].already) + str(
                state.bucket_list[1].already) + str(state.bucket_list[2].already)
            if is_not_precessed(stack, state.bucket_list):
                stack.put(state)
                find(from_b, to_b, stack, answer)
                stack.get()


def find_all_res(bucket_list):
    answer = []
    from_b = [0, 0, 1, 1, 2, 2]
    to_b = [1, 2, 0, 2, 0, 1]
    stack = Stack()
    status = Status(bucket_list, None)
    stack.put(status)
    find(from_b, to_b, stack, answer)
    return answer


bucket_list = [Bucket(3, 0), Bucket(5, 0), Bucket(8, 8)]
res = find_all_res(bucket_list)
print len(res)

for i in range(len(res)):
    arr = res[i]
    print arr
    print arr.p
    while not arr.empty():
        ele = arr.get()
        print ele.step
