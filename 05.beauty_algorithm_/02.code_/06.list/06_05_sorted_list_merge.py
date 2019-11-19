#!/bin/python
# -*- encoding:UTF-8 -*-

# 两个有序链表的合并

# 这个问题听起来感觉不难哈，看看能不能一次写出来

# 两个标识head01,head01,采用类似双指针的方式，
# 尝试写了一段伪代码，并没有那么容易，捂脸
# 需要记录真正的头，r_head; 当前合并后的链表的节点cur;然后是head01,head02


class Node:
    def __init__(self, val, next):
        self.val = val
        self.next = next


def merge_list(head01, head02):
    if head01 is None:
        return head02
    if head02 is None:
        return head01
    if head01.val < head02.val:
        r_head = head01
        cur = head01
        head01 = head01.next
    else:
        r_head = head02
        cur = head02
        head02 = head02.next

    while (head01 is not None) and (head02 is not None):
        if head01.val < head02.val:
            cur.next = head01
            cur = cur.next        # 蠢得要命，这个动作忘了加了。。。
            head01 = head01.next
        else:
            cur.next = head02
            cur = cur.next
            head02 = head02.next
    if head01 is not None:
        cur.next = head01
    if head02 is not None:
        cur.next = head02

    return r_head


def print_list(head_one):
    while head_one is not None:
        print(head_one.val, end=",")
        head_one = head_one.next


head1 = Node(7, None)
head = Node(5, head1)
head = Node(4, head)
head = Node(1, head)

head03 = Node(3, None)
head03 = Node(2, head03)
head03 = Node(1, head03)

print("head01")
print_list(head)

print("----")
print("head02")
print_list(head03)
print("-------------")
print("------merge-------")
print_list(merge_list(head03, head))
