#!/usr/bin/python
# -*- coding:UTF-8 -*-
'''这一课中注意到了 python中使用的 与操作是 and (不是&&)，
同样的，对应的有 或操作 or (不是 ||)
非操作  not  (不是!) 
但是在比较操作中有 != 操作符
还有就是 python 中没有 ++ -- 等自增操作，只能使用 +=1 -=1
 '''

def quick_sort(src_arr):
    low=0
    hi=len(src_arr)-1
    sort(src_arr,low,hi)

def sort(array,start,end):
    if(start>=end):
        return
    mark_pos=partition(array,start,end)
    print "mark_pos:"+str(mark_pos)
    sort(array,start,mark_pos-1)
    sort(array,mark_pos+1,end)

def partition(array,start,end):
    low=start
    hi=end
    mark=array[low]
    while(low<hi):
''' 这种先循环low,使用的边界是 low <=hi , 再循环右边，最后exchange(array,start,low-1)  对应的可以认为是一个bad case，
因为这样的话，代码变得难以理解，边界问题变得很棘手, 代码的可维护性相对1来说打了不少折扣'''

       while(low<=hi and array[low]<=mark):
            low +=1
       while(low<hi and array[hi]>mark):
            hi -=1
       if(low<hi):
            exchange(array,low,hi)
    exchange(array,start,low-1)    
    return hi
def exchange(array,left,right):
#    print array
#    print "low:"+str(left)+" hi:"+str(right)
    temp=array[left]
    array[left]=array[right]
    array[right]=temp
    
src=[30,12,18,4,17,8,2,5]
quick_sort(src)
print src
