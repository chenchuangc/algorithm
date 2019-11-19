# !/usr/bin/python
# -*- coding:UTF-8 -*-
'''
杨辉三角，使用递推的方式
建模的时候，最先想到的是使用二维数组，这样的话确实很简单，
这个时候可以根据算法的逻辑看看能否在存储层面进行优化
这个就要根绝递推表达式结合实际情况了
不要试图一下子全部都写出来，可能有些就是要修修补补才行 '''

def calculateN(num):
    n=num
    container=[{'n':1,'a':1,'b':1}]*(n+1)
    for i in range(0,len(container)):
        container[i]={'n':1,'a':0,'b':i}
        nc=i-1;
        for j in range(nc,0,-1):
            ncount=container[j]['n']+container[j-1]['n']
            container[j]={'n':ncount,'a':i-j,'b':j}
        container[0]={'n':1,'a':i,'b':0}#这里应该就算是一个修补动作，最开始总是想把这一块儿也去掉，但是代码复杂了很多，而且很难实现
        print container
#print container

print 'start ....'
calculateN(6)
