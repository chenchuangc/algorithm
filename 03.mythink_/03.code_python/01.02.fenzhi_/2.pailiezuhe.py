#!/ust/bin/python
# -*- coding:UTF-8 -*-

''' 
    排列组合，分治法，就是每次将其中的一个作为头，然后对剩下的进行同构迭代,处理有重复的情况就是增加一个记录器
最开始以为操作不用复位，后面才发现，这样会出大问题，也就是会出现重复的问题,因为子迭代操作会对下一轮的父亲的残生影响
'''
def composite(array):
    start=0
    end=len(array)-1
    composite_iterator(array,start,end)

def composite_iterator(arr,low,hi):
    if(low>=hi):
        print arr
        return
    start=low
    end=hi
#记录一下哪些已经重复过了
    already = set()
    for index in range(start,end+1):
        if arr[index] in already:
#            print arr[index]            
            continue;
        already.add(arr[index])
        exchange(arr,start,index)
        composite_iterator(arr,start+1,end)
        #这一步的复位十分必要
        exchange(arr,start,index)

def exchange(array,left,right):
#    print array
#    print "low:"+str(left)+" hi:"+str(right)
    temp=array[left]
    array[left]=array[right]
    array[right]=temp
 

#src=['s','c','b','s']
src='sabs'
composite(list(src))
