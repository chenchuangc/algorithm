#!/bin/python

# -*- encoding:UTF-8 -*-

# 单链表反转
# 1. 使用分治法，每次先把子链表完成，然后让next指向当前节点
# 2. 迭代法，这个就是需要的变量稍微多一些，其实空间复杂段也是O(1)


class Node:
    def __init__(self, val, next):
        self.val = val
        self.next = next


def reverse_list_recursivel(head):
    if head is None:
        return None
    if head.next is None:
        return head
    next_one = head.next
    head.next = None       # 最开始没有这一步的判断，导致头部的两个节点形成了一个环，遍历的时候就陷入了死循环。。。
    new_head = reverse_list_recursivel(next_one)
    next_one.next = head
    # print(new_head)
    return new_head


def reverse_list_iterator(head_one):
    if head_one is None:
        return head_one
    if head_one.next is None:
        return head_one
    pre = None
    while head_one.next is not None:
        next_one = head_one.next
        head_one.next = pre
        pre = head_one
        head_one = next_one
    head_one.next = pre
    return head_one


def print_list(head):
    while head is not None:
        print(head.val, end=",")
        head = head.next


head = Node(1, None)
head = Node(2, head)
head = Node(3, head)
head = Node(4, head)
head = Node(5, head)
head = Node(6, head)
head = Node(7, head)
print_list(head)
print("------------")
# print_list(reverse_list_iterator(head))
print("---------")
print_list(reverse_list_recursivel(head))
