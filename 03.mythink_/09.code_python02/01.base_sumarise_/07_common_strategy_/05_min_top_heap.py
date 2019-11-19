# !/bin/python
# -*- encoding:UTF-8 -*-

# 小顶堆是使用数组来存储二叉树的一种应用，当然小顶堆也是二叉树的一种特殊形态。
# 使用数组来标示二叉树，不需要动态进行内存分配，而且使用数组的下标直接访问，导致访问也更加高效，

# 分为两个阶段，在堆还没有满的时候可以加在末尾，然后采用上浮即可，
# 在堆满了之后判断是否可以加入堆，可以的话直接替换堆顶，然后使用下沉即可
# 一般小顶堆是用来找到前k大个元素


def exchange_arr(arr, i, father):
    temp = arr[i]
    arr[i] = arr[father]
    arr[father] = temp


class MinHeap:
    def __init__(self, cap):
        self.arr = [-1] * cap
        self.cap = cap
        self.cur = 0

    def add_one(self, val):
        if self.cur < self.cap:
            self.arr[self.cur] = val
            self.cur += 1
            self.float_up()
            return

        if val > self.arr[0]:
            self.arr[0] = val
            self.sink()

    def sink(self):
        i = 0
        while (i * 2 + 1) < self.cap:
            child01 = i * 2 + 1
            child02 = i * 2 + 2
            target = child01
            if child02 >= self.cap:
                target = child01
            elif self.arr[child01] > self.arr[child02]:
                target = child02
            if self.arr[i] > self.arr[target]:
                exchange_arr(self.arr, i, target)
            i = target

    def float_up(self):
        i = self.cur - 1
        while i > 0:
            father = (i - 1) / 2
            if self.arr[i] < self.arr[father]:
                exchange_arr(self.arr, i, father)
                i = father
            else:
                break

    def print_self(self):
        print self.arr

#
# min_heap = MinHeap(5)
# min_heap.add_one(28)
# min_heap.add_one(26)
# # min_heap.add_one(28)
# min_heap.add_one(23)
# min_heap.add_one(27)
# min_heap.add_one(29)
# min_heap.add_one(91)
# min_heap.add_one(62)
# min_heap.add_one(324)
# min_heap.add_one(65)
# min_heap.add_one(69)
# # min_heap.add_one(952)
# min_heap.print_self()

big_heap=MinHeap(5)
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
