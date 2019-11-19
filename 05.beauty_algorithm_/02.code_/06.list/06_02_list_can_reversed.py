#!/bin/python
# -*- encoding:UTF-8 -*-

# 如果一个字符串是使用单向链表进行存放的，如何判断这个链表是否是回文串

# 方案:
# 1. 使用快慢游标的方式，每次遍历能够对比一个相同位置的字符，这样的时间复杂度是O（n2）

# 2. O(n)的方案
# 2.1 使用快慢指针，一个每次前进一步，一个每次前进两步，前进一步的每次进行翻转处理
# 2.2 慢指针的链表翻转处理需要是实时的了，比对整个链表进行翻转出来要麻烦一点
# 2.3 链表翻转后，分别往两边进行对比就行了，这个时候还要考虑链表是奇数个还是偶数个，对于基础和偶数的处理不一样
# 2.4 对于奇数和偶数的处理，如何尽可能保持一致性，奇数的话正好在中位数的左边，


class Node:
    def __init__(self, val, next):
        self.val = val
        self.next = next


def check_hui(head):
    fast = head
    slow = head
    pre = None
    is_even = False
    while fast.next is not None:
        if fast.next.next is not None:
            fast = fast.next.next
            a_next = slow.next
            slow.next = pre
            pre = slow
            slow = a_next
        else:
            fast = fast.next
            is_even = True
            a_next = slow.next
            slow.next = pre
            pre = slow
            slow = a_next
    left = pre
    if is_even:
        right = slow
    else:
        right = slow.next
    is_hui = True
    while (left is not None) and (right is not None):
        if left.val != right.val:
            is_hui = False
            break
        left = left.next
        right = right.next
    print(is_hui)
    return is_hui


head = Node(1, None)
# head = Node(1, head)
# head = Node(3, head)
# head = Node(4, head)
# head = Node(6, head)
# head = Node(4, head)
# head = Node(3, head)
# head = Node(2, head)
# head = Node(1, head)

print(check_hui(head))
