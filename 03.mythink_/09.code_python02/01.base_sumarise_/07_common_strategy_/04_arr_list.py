#!/bin/python
# -*- encoding:UTF-8 -*-

# 使用数组链表的优点，避免了链表频繁申请内存的弊端，一次性申请块内存，在使用的过程中性能更佳
# 在一些系统上，内存申请和释放的开销比较大，使用链表存在性能问题。如果存储的数据元素的个数是固定或总数是受限的，
# 可以考虑用数组链表这种存储方式


class Node:
    def __init__(self, val, next):
        self.val = val
        self.next_one = next


class ArrList:
    def __init__(self, arr_len):
        self.len = arr_len
        self.arr = []
        self.free = 0
        self.head = -1
        for i in range(arr_len):
            if i < arr_len - 1:
                self.arr.append(Node(0, i + 1))
            else:
                self.arr.append(Node(0, -1))

    def add_ele(self, val):
        if self.free != -1:
            index = self.free
            self.free = self.arr[index].next_one
            self.arr[index].val = val
            self.arr[index].next_one = self.head
            self.head = index
        else:
            print 'Error, already full'

    def del_ele(self):
        del_index = self.head
        self.arr[del_index].next_one = self.free
        self.free = del_index
        self.head = self.arr[del_index].next_one

    def print_self(self):
        while self.head != -1:
            print str(self.head) + ":" + str(self.arr[self.head].val)
            self.head = self.arr[self.head].next_one


list_t = ArrList(5)
list_t.add_ele(50)
list_t.add_ele(40)
list_t.add_ele(30)
list_t.add_ele(20)
# list_t.add_ele(10)
# list_t.add_ele(1)
list_t.print_self()
