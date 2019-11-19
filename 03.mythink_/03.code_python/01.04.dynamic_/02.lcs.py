#!/usr/bin/python
# -*- encoding:UTF-8 -*-

'''
最长公共子序列（LCS，Longest Common Subsequence）的定义是：一个序列 S，如果分别是两个或多个已知序列的子序列，且是符合此条件的子序列中最长的，则称 S 为已知序列的最长公共子序列
关于子序列的定义通常有两种方式，
一种是对子序列没有连续的要求，其子序列的定义就是原序列中删除若干元素后得到的序列；
另一种是对子序列有连续的要求，其子序列的定义是原序列中连续出现的若干个元素组成的序列。
求解子序列是非连续的最长公共子序列问题，也是一个十分实用的问题，它可以描述两段文字之间的“相似度”，即它们的雷同程度，从而能够用来辨别抄袭
'''


'''
我们是不是应该先思考是先选取模型再思考，还是先思考问题的解决模式在建模
我觉得是先思考解决的方式吧，其实这里的问题建模基本上在问题描述的时候就已经给出了，并没有披上其他的外套

然后，接着使用四步走策略

假设有两个字符串 s1[1-n1],s2[1-n2]

1.状态: 随便来一下，假如s1,s2的第一个字符是相同的，那么问题就变成了s1[2-n1],s2[2-n2],这里的变量可以看到就是字符串的长短发生了变化，所以状态就可以规定为两个字符串的起始位置,同时我们也可以加上我们的求解就是已经有多少相同的字符串了

2.阶段/最优子结构: 阶段也是由状态推导而来的，阶段实际上可以认为状态变化的一个最小单位，这里可以看出，是一个字符串的长度。那么，s1[1-n1]和 s2[1-n2]之间最大公共长度问题的最优子结构就是:s1长度减小1，或者s2长度减小1，或者二者一起减小1，从这个角度出发，可以看出 f(s1[i-n1],s2[j-n2])的最优子结构是:
2.1  f(s1[i+1,n1],s2[j+1,n2]) + 1  这个时候s1[i]=s2[j] 
2.2  max{ f(s1[i,n1],s2[j+1,n2]), f(s1[i+1,n1],s2[j,n2]) } 这个是s1[i]!=s2[j] 的情况
这样的话就比较清楚的知道了对应的最优子结构是什么样子的

3.状态转移方程，从上面2中基本上可以认为是已经得到了状态转移方程

4.边界: 应该就是数组越界的情况吧

'''
def getLcsIter(s1,s2,s1_start,s2_start):
    if s1_start>=len(s1) or s2_start>= len(s2):
        return ''
    if s1[s1_start]==s2[s2_start]:
        return s1[s1_start]+getLcsIter(s1,s2,s1_start+1,s2_start+1)
    else:
        s1_primary=getLcsIter(s1,s2,s1_start,s2_start+1)
        s2_primary=getLcsIter(s1,s2,s1_start+1,s2_start)
        if len(s1_primary)>len(s2_primary):
            return s1_primary
        else:
            return s2_primary

def getLcs(s1,s2):
    s1_start=0
    s2_start=0
    return getLcsIter(s1,s2,s1_start,s2_start)

s1='deabkabcdef'
s2='abkef'
print getLcs(s1,s2)



'''
备忘录算法就不再重复了，就是以i+j作为key进行记录就可以了
下面看看能不能自下而上使用逆推的方式得到结果。
刚开始以为只是用两个变量进行记录就行了，后来仔细分析，最优子结构是这样的，f(s1[1-n1],s2[2-n2]) f(s1[2-n1],s2[2-n2]) f(s1[2-n1],s2[1-n2]),在求解最优子结构的时候实际上是一个广域搜索，这就必须使用二维数组的方式来进行保存才行。同时应该是逆序保存的二维数组，实际上和正序的应该没有区别吧，想想一下，假如是倒过来推的话，应该是一样的吧
使用二维数组，对应的是顺序的，也就是实际的解是和现在逆过来的，（反正是求解最长公共子序列，所以正反应该都不影响才对）,在最后输出结果的时候需要进行一次翻转

'''
def reverse(string):
    l= list(string)
    l.reverse()
    return ''.join(l)

def getPreInArr(arr,row,col):
    if row<0 or col<0:
        return ''
    else:
        return arr[row][col]

def getLcsByArr(s1,s2):
    s1_len=len(s1)
    s2_len=len(s2)
    arr=[['']*s2_len for i in range(s1_len)]    
    for row in range(s1_len):
        for col in range(s2_len):
            if s1[row]==s2[col]:
                arr[row][col]=s1[row]+getPreInArr(arr,row-1,col-1)
            else:
                pre_s1=getPreInArr(arr,row,col-1)
                pre_s2=getPreInArr(arr,row-1,col)
                if len(pre_s1)>len(pre_s2):
                    arr[row][col]=pre_s1
                else:
                    arr[row][col]=pre_s2
    res=arr[s1_len-1][s2_len-1]
    return reverse(res)
print '2..start....'
sa1='deabkabcdef'
sa2='abkef'
print getLcsByArr(sa1,sa2)






                    















