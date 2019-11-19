# !/bin/python
# -*- encoding:UTF-8 -*-

# 检测链表中的环，这个其实是不太好整的，只能使用快慢指针有一些效果，
# 如果快指针能够追上慢指针那么就是有环的，否则，在遍历到终点了还没有追上，就是无环的
# 1. 边界:
#   1.1 链表只要一个节点，ture
#   1.2 fast.next.next==null 的话，可以认为是无环的么


class Node:
    def __init__(self, val, next):
        self.val = val
        self.next = next


def circle_check(head):
    slow = head
    fast = head
    if (fast is None) or (fast.next is None):
        return False
    fast = fast.next.next
    slow = slow.next
    while fast is not None:
        if slow == fast:
            print(slow.val)
            print(fast.val)
            return True
        else:
            if (fast.next is None) or (fast.next.next is None):
                return False
            fast = fast.next.next
            slow = slow.next
    return False


head1 = Node(1, None)
head = Node(2, head1)
head = Node(3, head)
head = Node(4, head)
head = Node(5, head)
head = Node(6, head)
head = Node(7, head)
head1.next = head

print(circle_check(head))
