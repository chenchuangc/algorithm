#!/bin/python
# -*- encoding:UTF-8 -*-

# LRU算法是一种淘汰算法，redis中使用的淘汰算法也是这个，
# 其思想就是在已经加入缓存的数据中找出那些加入时间久，而且最近一段时间一直没有用到的额数据淘汰掉
# least recently used

# 这里使用单链表来做缓存的存储
# 查找一个数据，
# 1. 如果这个数据在缓存中
#    直接将该数据换到缓存列表的头部
# 2. 该数据不再缓存中，需要加入缓存的操作
#  2.1 缓存未满
#      直接对链表进行头插入
#  2.2 缓存已满
#      将链表最后一个删除，然后进行头插入

#  先把逻辑想清楚，再写代码就不容易出问题


class Node:
    def __init__(self, ele, next):
        self.ele = ele
        self.next = next


class LRU_Cache:
    def __init__(self, cap):
        self.cap = cap
        self.head = None
        self.usedNum = 0

    def add_ele(self, target):
        find = False
        iterator = self.head
        pre = iterator
        while not (iterator is None):
            if target == iterator.ele:
                find = True
                break
            pre = iterator
            iterator = iterator.next
        if find:
            pre.next = iterator.next
            iterator.next = self.head
            self.head = iterator
        else:
            if self.usedNum == self.cap:
                self.delete_tail()
            self.head = Node(target, self.head)
            self.usedNum += 1
        self.print_self()

    def delete_tail(self):
        fast = self.head
        slow = self.head
        if fast is None:
            self.head = None
            return
        fast = fast.next
        while fast.next is not None:
            fast = fast.next
            slow = slow.next
        slow.next = None

    def print_self(self):
        cursor = self.head
        while cursor is not None:
            print(cursor.ele, end=",")
            cursor = cursor.next
        print("---")


cache = LRU_Cache(5)
cache.add_ele(3)
cache.add_ele(8)
cache.add_ele(9)
cache.add_ele(1)
cache.add_ele(8)
cache.add_ele(6)
cache.add_ele(3)
cache.add_ele(5)
