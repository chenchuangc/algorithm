# -*- coding: utf-8 -*-

'''/**
这个地方的建模相对来说更加复杂一些
'''
def find_thief_complex():
    for i in range(0,4):
        man=[1,1,1,1]
        man[i]=0
        judge01=1 if (man[0] <>  0) else 0
        judge02=1 if (man[2]==0) else 0
        judge03=1 if (man[3]==0) else 0
        judge04=1 if (man[3] <>  0) else 0
        sumAll=judge01+judge02+judge03+judge04
        if sumAll==3:
            print "find the thief:"+str(i)
            break


def find_thief():
    x=0
    for i in range(0,4):
        x=i
        judge01=1 if (x <>  0) else 0
        judge02=1 if (x==2) else 0
        judge03=1 if (x==3) else 0
        judge04=1 if (x <>  3) else 0
        sumAll=judge01+judge02+judge03+judge04
        if sumAll==3:
            print "find the thief:"+str(x)
            break

print 'start...'
find_thief()
print 'find another way'
find_thief_complex()
