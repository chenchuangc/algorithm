# !/bin/python
# -*- encoding:UTF-8 -*-

# 农夫需要把狼、羊、菜和自己运到河对岸去（不知道为啥要运狼，别问我），只有农夫能够划船，而且船比较小，除农夫之外每次只能运一种东西，
# 还有一个棘手的问题，就是如果没有农夫看着，羊会偷吃菜，狼会吃羊。
# 请考虑一种方法，让农夫能够安全地安排这些东西和他自己过河。

# 对于这个问题，使用穷举搜索的时候也是让状态自动转换，关键是怎么描述状态，状态应该怎么选择，
# 你自己选的时候总是把农夫在河上以及方向都作为一个状态了，这样问题麻烦了很多。
# 换个思路，看看初始是什么，结果要的是什么，其实就可以把状态定义为农夫，羊，狼和白菜的位置了

# 然后要考虑的是驱动的动作，这个是思维的关键，在倒水游戏中好像比较容易理解，
# 但是在这个有些中有时候没有割裂开来看，要能够从复杂的问题中抽象出来这些东西
# 不要把动作和状态混为一谈，这样就无形中加大了问题分析的难度
# 有时候对于复杂的对象不要总是奢求使用数组表达，这样可能会把自己搞晕
# 这里的动作可以是一个都不带，可以是带其中的一个，但是动作必须合法，合法就是不让东西被吃掉
# 动作有，农夫带羊，农夫带狼，农夫带菜，农夫啥都不带，然后动作又有方向
# 考虑动作的时候只需要考虑穷举，先不要考虑动作的限制，就像下面的，分为8个动作，简单明了，如果不使用穷举的思维，直接想的话是很难想出来的
# 你刚才就是考虑是么情况下带什么，这种方式反而容易出现漏洞，很容易漏掉某种可能性
# 在穷举完动作后由每个动作来判断是否具备可行性，这样的话就比较舒服了，代码的结构也更加明了
# 这个实际上有点工程设计的意味了，也是复杂算法实现的一种能力吧

import copy



class Stack:
    def __init__(self):
        self.container = [0] * 1000
        self.p = 0

    def get_container(self):
        return self.container

    def put(self, ele):
        self.container[self.p] = ele
        self.p += 1

    def get(self):
        if self.p == 0:
            return
        self.p -= 1
        return self.container[self.p]

    def peek(self):
        return self.container[self.p - 1]

    def empty(self):
        return self.p == 0


class Location:
    Left = 0
    Right = 1


class Status:
    def __init__(self, cabage_loc, wolf_loc, rabbit_loc, famer_loc):
        self.cabage_loc = cabage_loc
        self.wolf_loc = wolf_loc
        self.rabbit_loc = rabbit_loc
        self.famer_loc = famer_loc


def action_fun_left_rabbit(stats):
    if stats.famer_loc == Location.Right:
        return False, ""
    if stats.rabbit_loc == Location.Right:
        return False, ""
    stats.rabbit_loc = Location.Right
    stats.famer_loc = Location.Right
    return True, "rabbit-->right"


def action_fun_left_wolf(stats):
    if stats.famer_loc == Location.Right:
        return False, ""
    if stats.wolf_loc == Location.Right:
        return False, ""
    if stats.cabage_loc == stats.rabbit_loc:
        return False, ""
    stats.wolf_loc = Location.Right
    stats.famer_loc = Location.Right
    return True, "wolf--->right"


def action_fun_left_cabage(stats):
    if stats.famer_loc == Location.Right:
        return False, ""
    if stats.cabage_loc == Location.Right:
        return False, ""
    if stats.wolf_loc == stats.rabbit_loc:
        return False, ""
    stats.cabage_loc = Location.Right
    stats.famer_loc = Location.Right
    return True, "cabage--->right"


def action_fun_left_none(stats):
    if stats.famer_loc == Location.Right:
        return False, ""
    if stats.cabage_loc == stats.rabbit_loc:
        return False, ""
    if stats.wolf_loc == stats.rabbit_loc:
        return False, ""
    stats.famer_loc = Location.Right
    return True, "famer--->right"


def action_fun_right_wolf(stats):
    if stats.famer_loc == Location.Left:
        return False, ""
    if stats.wolf_loc == Location.Left:
        return False, ""
    if stats.cabage_loc == stats.rabbit_loc:
        return False, ""
    stats.wolf_loc = Location.Left
    stats.famer_loc = Location.Left
    return True, "wolf--->left"


def action_fun_right_rabbit(stats):
    if stats.famer_loc == Location.Left:
        return False, ""
    if stats.rabbit_loc == Location.Left:
        return False, ""
    # if stats.cabage_loc == stats.rabbit_loc:
    #     return False
    stats.rabbit_loc = Location.Left
    stats.famer_loc = Location.Left
    return True, "rabbit--->left"


def action_fun_right_cabage(stats):
    if stats.famer_loc == Location.Left:
        return False, ""
    if stats.cabage_loc == Location.Left:
        return False, ""
    if stats.wolf_loc == stats.rabbit_loc:
        return False, ""
    stats.cabage_loc = Location.Left
    stats.famer_loc = Location.Left
    return True, "cabage--->left"


def action_fun_right_none(stats):
    if stats.famer_loc == Location.Left:
        return False, ""
    if stats.cabage_loc == stats.rabbit_loc:
        return False, ""
    if stats.wolf_loc == stats.rabbit_loc:
        return False, ""
    stats.famer_loc = Location.Left
    return True, "famer--->left"


actions_list = [action_fun_left_cabage, action_fun_left_rabbit, action_fun_left_wolf, action_fun_left_none,
                action_fun_right_cabage, action_fun_right_rabbit, action_fun_right_wolf, action_fun_right_none]


def check_res(stat):
    return stat.famer_loc == stat.rabbit_loc == stat.cabage_loc == stat.wolf_loc == Location.Right


def not_processed(s_copy, s):
    stack = copy.deepcopy(s)
    while not stack.empty():
        ele = stack.get()
        if s_copy.famer_loc == ele.famer_loc and s_copy.cabage_loc == ele.cabage_loc \
                and s_copy.rabbit_loc == ele.rabbit_loc and s_copy.wolf_loc == ele.wolf_loc:
            return False
    return True


def find(stack, res):
    cur = stack.peek()
    if check_res(cur):
        res.append(copy.deepcopy(stack))
        return
    for i in range(8):
        s_copy = copy.deepcopy(cur)
        action = actions_list[i]
        processed_, record = action(s_copy)
        if processed_:
            step = record + " :" + "c" + str(s_copy.cabage_loc) + "|" + "r" + str(s_copy.rabbit_loc) + "|" \
                   + "w" + str(s_copy.wolf_loc) + "|" + "f:" + str(s_copy.famer_loc)
            s_copy.step = step
            if not_processed(s_copy, stack):
                stack.put(s_copy)
                find(stack, res)
                stack.get()


def get_way():
    stats = Status(Location.Left, Location.Left, Location.Left, Location.Left)
    stack = Stack()
    stats.step = "aa"
    stack.put(stats)
    res = []
    find(stack, res)
    return res


res = get_way()
print len(res)
for i in range(len(res)):
    stack = res[i]
    p = stack.p
    arr = stack.container
    print p
    for j in range(p):
        print arr[j].step
