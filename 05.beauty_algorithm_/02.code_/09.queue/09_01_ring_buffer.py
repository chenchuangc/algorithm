#!/bin/python
# -*- encoding:UTF-8 -*-

# 这里要做的是使用数组实现一个环的缓冲区，听说这种在并发情况下使用可以cas进行判断，相对来说更加高效

# 一个数组，有自己的容量cap,head=0,tail=0，num
# enqueue 的时候在arr[(tail)]=k; tail=(tail+1)%n;
# dequeue 的时候 c=arr[head] head+=1

# 边界，num=cap,num=0


class RingBuffer:

    def __init__(self, cap):
        self.container = [0]*cap
        self.num = 0
        self.cap = cap
        self.head = 0
        self.tail = 0

    def enqueue(self, ele):
        if self.num == self.cap:
            return False
        self.container[self.tail] = ele
        print("container["+str(self.tail)+"]="+str(ele))
        self.tail = (self.tail + 1) % self.cap
        self.num += 1
        return True

    def de_queue(self):
        if self.num == 0:
            return None
        temp = self.container[self.head]
        self.head = (self.head + 1) % self.cap
        self.num -= 1
        return temp


buffer = RingBuffer(4)
print(buffer.enqueue(1))
print(buffer.enqueue(2))
print(buffer.enqueue(3))
print(buffer.enqueue(4))
print(buffer.enqueue(5))

print(buffer.de_queue())
print(buffer.de_queue())
print(buffer.enqueue(5))
print(buffer.de_queue())
print(buffer.de_queue())
print(buffer.de_queue())
print(buffer.enqueue(5))
print(buffer.enqueue(8))
print(buffer.enqueue(95))
