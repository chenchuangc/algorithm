#!/usr/bin/python
# -*- encoding:UTF-8 -*-

'''需要注意的是python 的range(start,end) 是包含start,不包含end的 '''
def sort(src_arr):
    start=0
    end=len(src)-1
    temp=[0]*len(src)
    merge_sort(src_arr,temp,start,end)

def merge_sort(arr,temp,start,end):
    if(start>=end):
        return
    mid=(start+end)/2
    merge_sort(arr,temp,start,mid)
    merge_sort(arr,temp,mid+1,end)
    merge(arr,temp,start,mid,end)

def merge(arr,temp,start,mid,end):
    lstart=start
    lend=mid
    rstart=mid+1
    rend=end
    tstart=start
    while(lstart<=lend and rstart<=rend):
        if  (arr[lstart]<arr[rstart]):
            temp[tstart]=arr[lstart]
            lstart+=1
        else:
            temp[tstart]=arr[rstart]
            rstart+=1
        tstart+=1
    while(lstart<=lend):
        temp[tstart]=arr[lstart]
        lstart+=1
        tstart+=1
    while(rstart<=rend):
        #print "r:"+str(rstart)+ " t:"+str(tstart)
        temp[tstart]=arr[rstart]
        rstart+=1
        tstart+=1
    for i in range(start,end+1):
        arr[i]=temp[i]

src=[2,3,7,2,1]
print src
sort(src)
print src

