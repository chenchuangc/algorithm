# !/bin/python
# -*- encoding:UTF-8 -*-


# 使用广度优先搜索一般都是为了找出最优的路线，所以这里的求解就只定义为最快的方式进行倒水
# 因为使用的是广度，所以重复了直接干掉即可
# 广度优先搜索

import Queue
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


def is_not_precessed(already, buckets):
    b1_use = buckets[0].already
    b2_use = buckets[1].already
    b3_use = buckets[2].already
    for i in range(len(already)):
        one_b = already[i]
        if one_b[0] == b1_use and one_b[1] == b2_use and one_b[2] == b3_use:
            return False
    return True


def check_answer(buckets):
    if buckets[1].already == 4 and buckets[2].already == 4:
        return True
    else:
        return False


def find_res(bucket):
    from_b = [0, 0, 1, 1, 2, 2]
    to_b = [1, 2, 0, 2, 0, 1]
    already = [[0, 0, 8]]
    q = Queue.Queue()
    q.put(bucket)
    while not q.empty():
        b = q.get()
        for i in range(6):
            b_copy = copy.deepcopy(b)
            do_process = try_process(from_b, to_b, i, b_copy)
            if do_process:
                b_copy[3] = b
                b_copy[4] = "" + str(from_b[i]) + "-->" + str(to_b[i])+"  "+str(b_copy[0].already) + str(b_copy[1].already) + str(b_copy[2].already)
                if check_answer(b_copy):
                    print b_copy[1].already
                    return b_copy
                if not is_not_precessed(already, b_copy):
                    continue
                already.append([b_copy[0].already, b_copy[1].already, b_copy[2].already])
                q.put(b_copy)


bucket_list = [Bucket(3, 0), Bucket(5, 0), Bucket(8, 8), 0, 0]  # 第4个元素用来记录
res = find_res(bucket_list)
while res !=0:
    print res[4]
    res = res[3]
    # print 'ok'
