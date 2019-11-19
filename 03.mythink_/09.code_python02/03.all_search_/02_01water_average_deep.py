# !/bin/python
# -*- encoding:UTF-8 -*-

#  有三个分别是 3 升、5 升和 8 升容积的水桶，其中容积为 8 升的水桶中装满了水，
#  容积为 3 升和容积为 5 升的水桶是空的，三个水桶都没有体积刻度。
#  现在需要把大水桶中的 8 升水等分成两份，每份都是 4 升水，
#  附加条件是只能使用这 8 升水和另外两个空水桶，不能借助其他容器或更多的水

# 现在开始，尽量用c的语法去实现，少用面向对象的方式，不对，也可以使用结构体来做，哈哈哈

# 这个问题处理的误区反思
# 1. 判断是否为answer在判断是否处理过之后，这样的话，就只有第一个answer能够添加进来了，后面的answer都被判断为已处理了，走不到加入answer的那一步
# 2. 判断是否走过该状态的时候有问题，只是把三个桶的已经装的水算作状态了，可能走到这个状态的路是不一样的，这样扼杀了结果的多样性，
# 后来加入了一个状态，就是走到当前状态使用的步数，这个也是有问题的，这样有环的话就发现不了了，果然被爆栈。
# 后面改用步数大于走过该状态的步数就认为是重复。这样虽然也是在一定程度上扼杀了一部分结果，但是可以展示出更多结果，而且可以避免自环


import copy


class Bucket:
    def __init__(self, cap, already):
        self.cap = cap
        self.already = already


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


def is_not_precessed(already, buckets, step_num):
    b1_use = buckets[0].already
    b2_use = buckets[1].already
    b3_use = buckets[2].already
    for i in range(len(already)):
        one_b = already[i]
        if one_b[0] == b1_use and one_b[1] == b2_use and one_b[2] == b3_use and step_num >= one_b[3]:
            return False
    return True


def check_answer(buckets):
    if buckets[1].already == 4 and buckets[2].already == 4:
        return True
    else:
        return False


def find(buckets, from_b, to_b, answer, already, cur):
    for i in range(6):
        copy_b = copy.deepcopy(buckets)
        copy_cur = copy.deepcopy(cur)
        do_process = try_process(from_b, to_b, i, copy_b)
        # print do_process
        if do_process:
            step =  str(from_b[i]) + "---->" + str(to_b[i])+":"+str(copy_b[0].already) + str(copy_b[1].already) + str(copy_b[2].already)
            print step
            copy_cur.append(step)
            one_answer = check_answer(copy_b)
            if one_answer:
                s = "   ".join(copy_cur)
                answer.append(s)
                # cur[:] = []
            else:
                pass
            not_processed = is_not_precessed(already, copy_b, len(copy_cur))
            if not_processed:
                already.append([copy_b[0].already, copy_b[1].already, copy_b[2].already, len(copy_cur)])
                find(copy_b, from_b, to_b, answer, already, copy_cur)
                print one_answer


def find_answer(buckets):
    answer = []
    already = [[0, 0, 8, 0]]
    from_b = [0, 0, 1, 1, 2, 2]
    to_b = [1, 2, 0, 2, 0, 1]
    cur = []
    find(buckets, from_b, to_b, answer, already, cur)
    for i in range(len(answer)):
        print answer[i]
        # print i


bucket_list = [Bucket(3, 0), Bucket(5, 0), Bucket(8, 8)]
find_answer(bucket_list)
