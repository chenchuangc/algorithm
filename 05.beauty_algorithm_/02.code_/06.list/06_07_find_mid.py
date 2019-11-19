#!/bin/python
# -*- encoding:UTF-8 -*-

# 求链表的中间，节点，还是使用快慢指针，一个走一步，一个走两步，
# 边界，只有一个，两个节点的


class Node:
    def __init__(self, val, next):
        self.val = val
        self.next = next


def find_mid(head):
    if (head is None) or (head.next is None):
        return head
    slow = head
    fast = head
    while fast is not None:
        if fast.next is not None:
            fast = fast.next.next
            slow = slow.next
        else:
            fast = fast.next
    return slow.val


head1 = Node(1, None)
head = Node(2, head1)
head = Node(3, head)
head = Node(4, head)
head = Node(5, head)
head = Node(6, head)
# head = Node(7, head)

print(find_mid(head))
