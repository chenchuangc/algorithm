#!/bin/python
# -*- encoding:UTF-8 -*-


class Node:
    def __init__(self, val, next):
        self.val = val
        self.next_one = next


def reverse_list(head):
    pre = head
    cur = pre.next_one
    pre.next_one = None
    if cur is None:
        return pre
    while cur is not None:
        next_ele = cur.next_one
        cur.next_one = pre
        pre = cur
        cur = next_ele
    return pre


node01 = Node(1, None)
node02 = Node(2, node01)
node03 = Node(3, node02)
node04 = Node(4, node03)
node05 = Node(5, node04)
node06 = Node(6, node05)
node07 = Node(7, node06)

reversed_node = reverse_list(node05)
while reversed_node is not None:
    print reversed_node.val
    reversed_node = reversed_node.next_one
