# !/bin/python
# -*- encoding:UTF-8 -*-

class Node:
    def __init__(self, val, next):
        self.val = val
        self.next_one = next


# 判断链表是否有环
def ever_circle(head):
    if head is None:
        return False
    slow = head
    fast = slow
    while fast is not None:
        slow = slow.next_one
        fast = fast.next_one
        if fast is None:
            return False
        fast = fast.next_one
        if slow == fast:
            return True
    return False


# 找到中间节点
def find_middle(head):
    if head is None:
        return head
    slow = head
    fast = head
    while fast is not None:
        fast = fast.next_one
        if fast is None:
            return slow
        fast = fast.next_one
        slow = slow.next_one
        if fast is None:
            return slow


# 找到倒数第k个节点
def find_the_last_k(head, k):
    fast = head
    slow = head
    i = 0
    while i < k and fast is not None:
        fast = fast.next_one
        i += 1
    if fast is None and i != k:
        return None
    while fast is not None:
        fast = fast.next_one
        slow = slow.next_one
    return slow


node01 = Node(1, None)
node02 = Node(2, node01)
node03 = Node(3, node02)
node04 = Node(4, node03)
node05 = Node(5, node04)
node06 = Node(6, node05)
node07 = Node(7, node06)
# node01.next_one = node06

print ever_circle(node07)
print find_middle(node03).val
print find_the_last_k(node06, 6).val
