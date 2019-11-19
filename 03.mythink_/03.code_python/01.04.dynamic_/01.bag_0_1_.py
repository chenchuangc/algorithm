#!/usr/bin/python
# -*- encoding:UTF-8 -*-

'''
问题描述:
有一个窃贼在偷窃一家商店时发现有n件物品，第i件物品价值为vi元，重量为wi，假设vi和wi都为整数。他希望带走的东西越值钱越好，但他的背包中之多只能装下W磅的东西，W为一整数。他应该带走哪几样东西

'''

''' 
动态规划的四个基本点:
1.阶段/最优子结构
2.状态，阶段的描述性变量
3.状态转移方程，问题的推导方式
4.边界，为题处理的边界

两个原则
1.阶段必须满足最优化原理
2.状态必须满足在阶段之间的无后向性

对于当前问题的对应解析
先找状态，然后找最优子结构可能更加简单一些
1.状态：
    当你不知道状态的时候，直接最简化问题，尝试走两步，看看问题有哪些变化，也就找出了状态变量
对于当前问题，就是:随便拿一件放到包里，看看问题变成了什么模样。当前的假如装了第1个物品（价值为v1,重量为w1）,那么问题就转化为了从2-n个物品尽量装入背包最大为W-w1已得到最大价值。从这里可以看到问题的描述中发生变化的地方有1.物品有变化(1-n-->2-n) 2.背包容量有变化（W-->w1）,可以将这两个定义为状态同时呢，我们还会发现一个隐含的变量，就是当前已经获得的价值数，当然这个并不一定都能发现。就认为在这里没有发现好了。 

2.阶段/最优子结构 
    有了状态，我们来看看最优子结构，最优子结构，就是看看当前问题能不能由一些规模较小的子问题推导出来，所谓的规模变小，基本上就是围绕问题的变量的规模的变小。在这里就是物品变小，背包变小（不一定是两者同时缩减）
    这里来分析这个问题的最优子结构，可以从两个变量的维度来，1，物品变小，背包不变（此时物品变小的最小粒度是一个，假设为第i个,也就是说不会装入第i个物品）此时的最优解是f(n-1,W)   2.背包变小，这个时候肯定装进去了一个物品,所以背包不可能不变，一定会变小，假设为i, 则这个时候的最优解是vi+ f(n-1,W-wi),从这里可以看出来这个问题一共有两种最优子结构,哪一个更优就是当前问题的解，这里说明最优子结构实际上是当前问题的解集中的一个，他们为实现最优解提供支撑，而不是简单的子问题，可以认为是问题的转化。
    那么这种最优子结构满足最优化原理么，也就是所有的阶段都满足么，这里看是满足的，因为所有的阶段都适应这两个维度的问题规模的缩减
    那么阶段划分的步子应该是多大呢，从这个地方看就是在装入某一个物品的时候是否选择装进去。

3.状态转移方程
    (首先说一下这个背包问题，最常见的讲解方式和推理方式，就是说第i个物品是否放进去有两种选择，一种是放进去，一种是不放，然后推出了状态转移方程式，我总是觉得这里怪怪的，我也说不上来，就是觉得可能正常人可能一下子想不到这样去思考问题。如果能依据2来退出来状态转移方程的话，可能，普适性更加强一些，那么，如何才能根据二来推导出来状态转移方程呢？这里也只是一些想法，并不一定是正确的)
    我们已经找到了最优子结构，那么就要从最优子结构中提炼出来状态转移方程，这个时候需要的是对问题进行抽象。在背包问题中我觉得，还有一点比较重要的就是最优子结构的i的选择有没有顺序性，有没有说在某个阶段一定要选择第k个物品，实际上是满足无序性的。从2的子状态中就可以看出，i等于任何一个物品都是可以正常求解的。
   所以我们可以推出状态转移方程为 f(n,W)=max{ f(n-1,W),f(n-1,W-wi)+vi}

4.边界在哪里
    边界在这个地方有，1，背包容量小于当前要装的wi,则第i个肯定就装不了了   2.物品n个都已经装完了
    

'''

''' 
1.建模 
1.1 物品直接使用数组表示好了
1.2 状态，使用一个对象表示，属性有 可用物品，背包容量，当前已经有的价值（我认为这个是自然的哈，因为这个是要求解的终极结果）

'''

'''
直接按照递推公式参照边界求解法，以上问题，最直观的就是按照递推公司来实现代码，其实代码在这个过程中实现了一个广域搜索
'''

def getMaxValIte(goodsArr,bSize,index):
    if(index<0):
        return 0
    if(goodsArr[index]["weight"]>bSize) :
        return getMaxValIte(goodsArr,bSize,index-1)
    return max(getMaxValIte(goodsArr,bSize,index-1),getMaxValIte(goodsArr,bSize-goodsArr[index]['weight'],index-1)+goodsArr[index]['val'])


def getMaxVal(goodsArr,bSize):
    #state={"",}
   return getMaxValIte(goodsArr,bSize,len(goodsArr)-1) 



print 'start 1...'
goods=[{"weight":2,"val":3},{"weight":4,"val":2},{"weight":5,"val":8},{"weight":8,"val":3}]
print getMaxVal(goods,7)



'''
上面的问题会有很多子问题被重复搜索，在这里可以增加一个备忘录来进行求解
备忘录的key 应该为 两个变量组成的联合索引 bsize+index
'''

def getMaxValIteReminder(goodsArr,bSize,index,reminder):
    key=str(bSize)+"_"+str(index)
    alreadyDo=reminder.get(key)
    if not alreadyDo is None:
        return alreadyDo
    if(index<0):
        reminder[key]=0
        return 0
    if(goodsArr[index]["weight"]>bSize) :
        want=getMaxValIte(goodsArr,bSize,index-1)
        reminder[key]=want
        return want
    
    want=max(getMaxValIteReminder(goodsArr,bSize,index-1,reminder),getMaxValIteReminder(goodsArr,bSize-goodsArr[index]['weight'],index-1,reminder)+goodsArr[index]['val'])
    reminder[key]=want
    return want
   

def getMaxValReminder(goodsArr,bSize):
    reminder={}
    return getMaxValIteReminder(goodsArr,bSize,len(goodsArr)-1,reminder)


print 'start 2...'
goodsremin=[{"weight":1,"val":3},{"weight":2,"val":12},{"weight":4,"val":2},{"weight":5,"val":8},{"weight":8,"val":3}]
print getMaxValReminder(goodsremin,8)


'''
上面两种方法，没有完整的记录整个的过程，所以无法通过回溯得知具体把哪些物品装进了背包当中
同时，根绝递推方程，可以使用二维数组的方法进行推理和记忆，这样的话就可以进行回溯了。

'''
def getVal(calArr,col,row):
    r_len=len(calArr)
    #print calArr
    #print calArr[0]
    c_len=len(calArr[0])
    if col<0 or col >= c_len or row<0 or row>=r_len :
        return 0
    return calArr[row][col]

def getMaxByDir(goodsArr,bSize):
    num_g=len(goodsArr)
    calArr=[[0]*len(goodsArr) for i in range (bSize)]
    #print "look"
    #print calArr
    for row in range(bSize):
        for col in range(num_g):
            one_best=getVal(calArr,col-1,row)
            another_best=getVal(calArr,col-1,row-goodsArr[col]['weight'])+goodsArr[col]['val']
            calArr[row][col]=max(one_best,another_best)

    #step="["+str(num_g-1)"]"+""+str(bSize-1)    
    step =[]
    back_r=bSize-1
    back_c=num_g-1
    
    while back_r>=0 and back_c>=0 :
        cur_step=str(back_c)
        #cur_step=","+str(back_r)+":"+str(back_c)
        #step+=cur_step
        pre_one_col=back_c-1
        pre_one_row=back_r
        pre_another_col=back_c-1
        pre_another_row=back_r-goodsArr[back_c]['weight']
        if getVal(calArr,pre_one_col,pre_one_row)>(getVal(calArr,pre_another_col,pre_another_row)+goodsArr[back_c]['val']):
            back_r=pre_one_row
            back_c=pre_one_col
            cur_step+=":false"
        else:
            back_r=pre_another_row
            back_c=pre_another_col
            cur_step+=":true"
        step.append(cur_step)
    step.reverse()       
    print step

    print calArr
    return calArr[bSize-1][num_g-1]


print 'start 3...'
goodsDir=[{"weight":1,"val":3},{"weight":2,"val":12},{"weight":4,"val":2},{"weight":5,"val":8},{"weight":8,"val":3}]
print "goods",goodsDir
print getMaxByDir(goodsDir,8)
















