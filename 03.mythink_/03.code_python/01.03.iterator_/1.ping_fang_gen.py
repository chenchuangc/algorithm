#!/usr/bin/python
# -*- encoding:UTF-8 -*-

precies=0.001
def find_res(a):
    return find_iterator(a,a)


def find_iterator(a,cur_res):
    res=(cur_res+(float(a)/cur_res))/2.0
    if abs(res-cur_res)<precies:
        return res
    return find_iterator(a,res)

res=find_res(5)
print str(res)
    



''' 这个是直接使用循环的方式，更加贴近迭代的本质 '''

def find_res_iterator(a):
    precies=0.001
    pre_res=a
    cur_res=(pre_res+(float(a)/pre_res))/2.0
    while(abs(cur_res-pre_res)>precies):
        pre_res=cur_res
        cur_res=(pre_res+(float(a)/pre_res))/2.0
    return cur_res
    
a=6
res_a=find_res_iterator(a)
print "a:"+str(a)+" res:"+str(res_a)
