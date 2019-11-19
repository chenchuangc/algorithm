# !/bin/python
# -*- encoding:UTF-8 -*-

# 最开始的时候想迷糊了，对这个程序中满堆以后换掉最大然后下沉的处理方式能不能实现堆特性的保持产生了怀疑，
# 后来发现是自己的代码有bug,
# 仔细想想的话，每次换掉最大的，然后进行下沉，只要上一次堆是满足的，则当前操作完成后堆还是满足的，
# 这样的话就像是递推证明一样，可以证明这种方式是可以满足堆的特性一直成立的。

def exchange_arr(arr, i, father):
    temp = arr[i]
    arr[i] = arr[father]
    arr[father] = temp


class BigHeap:
    def __init__(self, cap):
        self.cap = cap
        self.cur = 0
        self.arr = [-1] * cap

    def add_one(self, val):
        if self.cur < self.cap:
            self.arr[self.cur] = val
            self.cur += 1
            self.float_up()
            return
        if self.cur == self.cap:
            if val < self.arr[0]:
                self.arr[0] = val
                self.sink()

    def float_up(self):
        i = self.cur - 1
        while i > 0:
            parent = (i - 1) / 2
            if self.arr[i] > self.arr[parent]:
                exchange_arr(self.arr, parent, i)
                i = parent
                # print i
            else:
                break

    def sink(self):
        i = 0
        while (2 * i + 1) < self.cap:
            child01 = 2 * i + 1
            child02 = 2 * i + 2
            target = child01
            if child02 < self.cap and self.arr[child02] > self.arr[child01]:
                target = child02
            if self.arr[i] < self.arr[target]:
                exchange_arr(self.arr, i, target)
            i = target

    def print_self(self):
        print self.arr


big_heap = BigHeap(5)
big_heap.add_one(91)
big_heap.add_one(122)
big_heap.add_one(23)
big_heap.add_one(324)
big_heap.add_one(725)
big_heap.add_one(26)
big_heap.add_one(28)
big_heap.add_one(28)
big_heap.add_one(29)
big_heap.add_one(32)
big_heap.add_one(952)
big_heap.print_self()
