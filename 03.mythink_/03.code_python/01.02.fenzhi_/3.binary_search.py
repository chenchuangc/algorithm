#!/usr/bin/python
# -*- encoding:UTF-8 -*-

''' python中的esle if 是连着写的 写作elif 同时，最后一个else 之后如果有返回，不要忘了带冒号 :   '''

def search(src,target):
    start=0
    end=len(src)-1
    return binary_search(src,target,start,end)

def binary_search(arr,target,start,end):
    if (start>end):
        return -1
    mid=(start+end)/2
    if  (target>arr[mid]):
        return binary_search(arr,target,mid+1,end)
    elif(target < arr[mid]):
        return binary_search(arr,target,start,mid-1)
    else: 
        return mid


src=[1,2,3,4,5,6]
target=4
index=search(src,target)

print "src:"
print src
print "target:"+str(target)
print "index:"+str(index)
print "find:"+str(src[index])
