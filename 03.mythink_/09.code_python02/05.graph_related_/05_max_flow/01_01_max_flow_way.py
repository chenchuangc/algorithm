# !/bin/python
# -*- encoding:UTF-8 -*-

# 最大流问题的典型例子有物流配送、输水管路等。比如这个题目：某地区的自来水供应是由地下的水管网路组成，
# 两个地点之间由粗细不一的水管连接，粗水管的输水能力大，细水管的输水能力小，请规划一个合理的水量和管路组合，
# 使得从水源地到目的地能够获得最大的水量。
# https://gitbook.cn/gitchat/column/5b6d05446b66e3442a2bfa7b/topic/5c16f8261e59245d4d2a42a2

# 这个问题的解决的最厉害的地方在于引入了增流网络的概念，这样的就可以避免导致走了别的节点的路导致别的节点没有流量了
# 数据模型，这里的数据模型使用 邻接矩阵更加方便，但是同样的会造成对内存的使用偏大，但是和方便的记录了残余网络的容量和增流网络的容量
# 同时，每次找出一条路径以后还需要记录这条路径的流量，以这条路中容量最小的作为标准
# 这样的话，应该是能求出来最大流量，但是没有办法求出最大流对应的路径

# 这个问题最开始将没有数据的边初始化为-1了，导致了最后的结果一直是22而不是23，哎，导致了一个反向路径的失败

# 这个算法在开始写的时候很不舒服，主要是使用方法级别的深度迭代很不适应，之前都是在对象中有全局变量，后面要适应这种节奏


# 0-1 16 ; 0-2 13 ; 1-2 10; 2-1 4;
# 1-3 12 ; 3-2 9; 2-4 14;
# 4-3 7; 4-5 4; 3-5 20 ;
p_num = 6
net_way = [[0] * p_num for i in range(p_num)]

net_way[0][1] = 16
net_way[0][2] = 13
net_way[1][2] = 10
net_way[2][1] = 4
net_way[1][3] = 12
net_way[3][2] = 9
net_way[2][4] = 14
net_way[4][3] = 7
net_way[4][5] = 4
net_way[3][5] = 20


# print len(net_way)


def try_deep_find(net_way, cur, start, end, have_gone, path):
    if cur == end:
        return True
    have_gone[cur] = True
    for i in range(len(net_way)):
        if not have_gone[i] and net_way[cur][i] > 0:
            path[i] = cur
            have_path = try_deep_find(net_way, i, start, end, have_gone, path)
            # print have_path
            if have_path:
                return True
            # have_gone[i] = False   深度优先遍历不需要去除标记，感觉广度也不需要
    return False


def deep_find_way(net_way, start, end):
    have_gone = [False] * 6
    path = [0] * 6
    have_way = try_deep_find(net_way, start, start, end, have_gone, path)
    if have_way:
        return have_way, path
    return False, path


def find_cur_flow(net_way, path):
    target_piont = len(path) - 1
    min_flow = 1000
    while target_piont != 0:
        cur_f = net_way[path[target_piont]][target_piont]
        min_flow = min(min_flow, cur_f)
        target_piont = path[target_piont]
    return min_flow


def modify_net(net_way, path, cur_flow):
    target_point = len(path) - 1
    while target_point != 0:
        s = path[target_point]
        net_way[s][target_point] -= cur_flow
        net_way[target_point][s] += cur_flow
        target_point = s


def get_way(path):
    target_point = len(path) - 1
    w = ""
    while target_point != 0:
        s = path[target_point]
        w = str(s) + "-->" + str(target_point) + " " + w
        target_point = s
    return w


def max_flow(net_way):
    start = 0
    end = 5
    flow_total = 0
    haveway = True
    while haveway:
        haveway, path = deep_find_way(net_way, start, end)
        print str(haveway) + "main"
        if haveway:
            cur_flow = find_cur_flow(net_way, path)
            modify_net(net_way, path, cur_flow)
            flow_total += cur_flow
            way = get_way(path)

            print way + " :" + str(flow_total)
            for ele in net_way:
                print ele
            print "-------------"
            print "-------------"

    return flow_total


max_flow_num = max_flow(net_way)
print "------"
print max_flow_num
