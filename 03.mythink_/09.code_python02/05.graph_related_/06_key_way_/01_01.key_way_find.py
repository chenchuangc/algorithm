#!/bin/python
# -*- encoding:UTF-8 -*-

# 关键路径，是为了找出一个任务网中耗时最长的任务链，这是整个任务网完成的最短耗时
# 这里使用的是AOE网，想一想能不能使用AOV网来完成呢？
# AOV网能够完成任务网的拓扑排序，但是他没有记录任务链条的的能力

# 关键路径算法使用的是AOE网，也就是边是任务耗时等相关，节点代表的是任务的开始事件，任务的开始事件就是用顶点进行记录的
# 数据建模，先要考虑的是这个问题有哪些变量(状态)，每个任务有他的耗时，他的入度依赖，那么有自身的最早开始时间限制么，
# 假如有，假如没有的话应该如何处理，
# 这里假设没有吧，就像盖房子的装修一样，多个装修小队装修不同的项目，项目之间有些有依赖，有些没有依赖
# 每个边代表任务，每个点代表每个任务的开始事件

# 算法的顺序，
# 1. 按照要求进行建模
# 2. 进行拓扑排序
# 3. 按照拓扑排序求最早开始时间 ，取顶点的最晚的开始时间，有多个前驱的话取最晚的时间节点
# 4. 按照拓扑排序求最晚开始时间，有多个的话取相对较早的时间
# 5. 比较最早开始和最晚开始相同的节点，找出多个关键任务，
# 6. 找出耗时最长的关键路径(可能有多个关键路径，要找出耗时最长的)如何找出,
import Queue


class Edge:
    def __init__(self, end, weight, name):
        # self.s = start
        self.e = end
        self.w = weight
        self.name = name


class Point:
    def __init__(self, front_degree, s_time, e_time):
        self.f_degree = front_degree
        self.s_time = s_time  # 最早开始时间
        self.e_time = e_time  # 最晚开始时间
        self.edges = []

    def is_empty(self):
        return len(self.edges) == 0


class Task:
    def __init__(self, num_task):
        self.points = [None] * num_task

    def add_edge(self, start, end, weight, name):
        p_index = start
        e_index = end
        if self.points[p_index] is None:
            self.points[p_index] = Point(0, 0, 1000)
        if self.points[e_index] is None:
            self.points[e_index] = Point(0, 0, 1000)
        self.points[p_index].edges.append(Edge(end, weight, name))
        self.points[e_index].f_degree += 1


def find_toplogic(task, toplogic_list):
    stack = Queue.LifoQueue()
    point_list = task.points
    total = len(task.points)
    for i in range(total):
        if not point_list[i].is_empty() and point_list[i].f_degree == 0:
            point_list[i].s_time = 0
            stack.put(i)
    while not stack.empty():
        ele_index = stack.get()
        # print ele_index
        ele = point_list[ele_index]
        toplogic_list.append(ele_index)
        for a_edge in ele.edges:
            next_index = a_edge.e
            point_list[next_index].f_degree -= 1
            next_start = ele.s_time + a_edge.w
            if next_start > point_list[next_index].s_time:
                point_list[next_index].s_time = next_start
            if point_list[next_index].f_degree == 0:
                stack.put(next_index)


def find_lasted(task, toplogic_list):
    size = len(toplogic_list)
    point_list = task.points
    point_list[toplogic_list[size - 1]].e_time = point_list[toplogic_list[size - 1]].s_time
    for index_of_point in reversed(range(size - 1)):
        # print index_of_point
        temp_p = point_list[index_of_point]
        for a_edge in temp_p.edges:
            if point_list[index_of_point].e_time > point_list[a_edge.e].e_time - a_edge.w:
                point_list[index_of_point].e_time = point_list[a_edge.e].e_time - a_edge.w


def fin_best_key_way(task, toplogic_list):
    list_way = []
    point_list = task.points
    a_point = point_list[toplogic_list[0]]
    while not (a_point is None):
        list_way.append(a_point)
        if len(a_point.edges) >0:
            for edge in a_point.edges:
                v = edge.e
                v_point = point_list[v]
                if v_point.s_time == v_point.e_time:
                    a_point = v_point
                    break
        else:
            a_point = None
    return list_way


def find_key_way(task):
    toplogic_list = []
    find_toplogic(task, toplogic_list)
    print toplogic_list
    for p in task.points:
        print p.s_time
    # find_earlist(task, toplogic_list)
    find_lasted(task, toplogic_list)
    print "-----------"
    for p in task.points:
        print p.e_time
    way = fin_best_key_way(task, toplogic_list)
    # print way
    for step in way:
        print step.s_time


task = Task(6)
task.add_edge(0, 1, 3, "0to1")
task.add_edge(1, 2, 4, "2to3")
task.add_edge(2, 5, 5, "2to5")
task.add_edge(0, 3, 1, "0to3")
task.add_edge(3, 4, 2, "3to4")
task.add_edge(4, 5, 3, "4to5")

find_key_way(task)
