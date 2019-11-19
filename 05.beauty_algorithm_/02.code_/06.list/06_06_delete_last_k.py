#!/bin/python
# -*- encoding:UTF-8 -*-

# 删除倒数第k个节点
# 可以依然使用快慢指针的的方式进行删除
# 假如删除倒数第k个，就让快指针先走k步，然后两个一起走就行了

class Node:
    def __init__(self, val, next):
        self.val = val
        self.next = next


def del_last_kst(head, k):
    fast = head
    slow = head
    while k >= 0:
        if head is None:
            print("total is small than k")
            return
        fast = fast.next
        k -= 1
    while fast is not None:
        fast = fast.next
        slow = slow.next
    slow.next = slow.next.next
    return head


def print_list(head_one):
    while head_one is not None:
        print(head_one.val, end=",")
        head_one = head_one.next


head1 = Node(1, None)
head = Node(2, head1)
head = Node(3, head)
head = Node(4, head)
head = Node(5, head)
head = Node(6, head)
head = Node(7, head)

print_list(head)
print()
print("----------")
print_list(del_last_kst(head, 2))
