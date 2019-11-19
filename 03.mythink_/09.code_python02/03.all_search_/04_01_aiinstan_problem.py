# !/bin/python
# -*- encoding:UTF-8 -*-


# 建模的话，有5个房子，进行嵌套的分配
# 假设只有颜色来分派，是5个嵌套的for循环
# 这其实是一个排列组合，可以排列组合的方式进行
# 加入直接使用嵌套for循环的话则嵌套的层数可能很深导致问题的求解出问题，
# 可以想象有没有更好的方式，还记得排列组合的算法么，就是字符串的排列组合，这个算法可以应用在这里
# 定义一个二维表，可以更加方便的用来遍历，每一行代表了一个房子的所有属性，每一列代表了一个属性在这5个房子中的所有的排列组合的可能
# 感觉这样使用起来很麻烦，每一列代表一个房子的所有属性好了，每一行代表一个属性在所有房子的分布

# 在做这个的时候，脑子没有转过来，就是需要进行排列组合的时候发现需要嵌套，
# 但是有些犯晕，本来就是通过迭代嵌套才能完成的，只不过这个多层嵌套，需要更高的技巧
# 当时脑子里面一直想的是通过循环直接完成
# 双重迭代的遍历
# 这里要掌握的就是二维表的遍历，也就是嵌套遍历时可以使用迭代法来进行嵌套的，避免代码的可读性太差，
# 这里的迭代有两个维度，一个是多个房子的一个属性的排列组合，一个是多个属性之间的排列组合


# 不同问题的穷举法算法实现最大的差异点就是搜索算法的不同，前几课介绍了线性空间的搜索、树状空间的搜索，
# 这一课我们再介绍一种求解问题的搜索算法，其搜索的空间是一个二维表，对二维表中的每个元素进行枚举遍历，依次确定每个表格元素的值，
# 当二维表中所有表格元素的值都确定后，检查其结果是否符合问题解的要求，如果满足要求，则输出一个结果，
# 如果不满足要求，则按照一定的顺序继续遍历各个表格元素的值。
# 这也是一种典型的解空间搜索方式，通过对这个问题的理解，未来遇到类似的问题，或三维表空间的问题，都可以用类似的方法设计搜索算法。

# 题目是这样的，据说有五个不同颜色的房间排成一排，
# 每个房间里分别住着一个不同国籍的人，每个人都喝一种特定品牌的饮料，抽一种特定品牌的烟，养一种宠物，
# 没有任意两个人抽相同品牌的香烟，或喝相同品牌的饮料，或养相同的宠物，问题是谁在养鱼作为宠物？为了寻找答案，爱因斯坦给出了 15 条线索：

# （1）英国人住在红色的房子里
# （2）瑞典人养狗作为宠物
# （3）丹麦人喝茶
# （4）绿房子紧挨着白房子，在白房子的左边
# （5）绿房子的主人喝咖啡
# （6）抽 Pall Mall 牌香烟的人养鸟
# （7）黄色房子里的人抽 Dunhill 牌香烟
# （8）住在中间那个房子里的人喝牛奶
# （9）挪威人住在第一个房子里面
# （10）抽 Blends 牌香烟的人和养猫的人相邻
# （11）养马的人和抽 Dunhill 牌香烟的人相邻
# （12）抽 BlueMaster 牌香烟的人喝啤酒
# （13）德国人抽 Prince 牌香烟
# （14）挪威人和住在蓝房子的人相邻
# （15）抽 Blends 牌香烟的人和喝矿泉水的人相邻


class Smoke:
    BlueMaster = 0
    Pall_Mall = 1
    Blends = 2
    Dunhill = 3
    Prince = 4


class Person:
    Danmai = 0
    England = 1
    Nowei = 2
    Ruidian = 3
    German = 4


class Color:
    Blue = 0
    Green = 1
    Red = 2
    Yellow = 3
    White = 4


class Pet:
    Fish = 0
    House = 1
    Dog = 2
    Bird = 3
    Cat = 4


class Drink:
    Tea = 0
    Coffe = 1
    Milk = 2
    Beer = 3
    Water = 4


# 0 color , 1 country , 2 pet,3 drink,4 somke


def function11(house):
    for i in range(5):
        if house[i][2] == Pet.House:
            if 4 > i > 0:
                return house[i - 1][4] == Smoke.Dunhill or house[i + 1][4] == Smoke.Dunhill
            if i == 0:
                return house[i + 1][4] == Smoke.Dunhill
            if i == 4:
                return house[i - 1][4] == Smoke.Dunhill


def function12(house):
    for i in range(5):
        if house[i][3] == Drink.Beer:
            return house[i][4] == Smoke.BlueMaster


def function13(house):
    for i in range(5):
        if house[i][1] == Person.German:
            return house[i][4] == Smoke.Prince


def function14(house):
    for i in range(5):
        if house[i][1] == Person.Nowei:
            if 4 > i > 0:
                return house[i - 1][0] == Color.Blue or house[i + 1][0] == Color.Blue
            if i == 0:
                return house[i + 1][0] == Color.Blue
            if i == 4:
                return house[i - 1][0] == Color.Blue


def function15(house):
    for i in range(5):
        if house[i][4] == Smoke.Blends:
            if 4 > i > 0:
                return house[i - 1][3] == Drink.Water or house[i + 1][3] == Drink.Water
            if i == 0:
                return house[i + 1][3] == Drink.Water
            if i == 4:
                return house[i - 1][3] == Drink.Water


def exchange(house, col_val, row, row_house):
    temp = house[row_house][col_val]
    house[row_house][col_val] = house[row][col_val]
    house[row][col_val] = temp


def function01(house):
    for i in range(5):
        if house[i][0] == Color.Red:
            return house[i][1] == Person.England


def function02(house):
    for i in range(5):
        if house[i][1] == Person.Ruidian:
            return house[i][2] == Pet.Dog


def function03(house):
    for i in range(5):
        if house[i][1] == Person.Danmai:
            return house[i][3] == Drink.Tea


def function04(house):
    for i in range(5):
        if house[i][0] == Color.Green:
            return i < 4 and house[i + 1][0] == Color.White


def function05(house):
    for i in range(5):
        if house[i][3] == Drink.Coffe:
            return house[i][0] == Color.Green


def function06(house):
    for i in range(5):
        if house[i][2] == Pet.Bird:
            return house[i][4] == Smoke.Pall_Mall


def function07(house):
    for i in range(5):
        if house[i][4] == Smoke.Dunhill:
            return house[i][0] == Color.Yellow


def function08(house):
    return house[2][3] == Drink.Milk


def function09(house):
    return house[0][1] == Person.Nowei


def function10(house):
    for i in range(5):
        if house[i][2] == Pet.Cat:
            if 4 > i > 0:
                return house[i - 1][4] == Smoke.Blends or house[i + 1][4] == Smoke.Blends
            if i == 0:
                return house[i + 1][4] == Smoke.Blends
            if i == 4:
                return house[i - 1][4] == Smoke.Blends


def filter_way(house):
    # print "1"
    if function01(house) and function02(house) and function03(house) and function04(house) and function05(
            house) and function06(house) and function07(house) and function08(house):
        print 'ok'
        if function09(house) and function10(house) and function11(house) and function12(house) and function13(
                house) and function14(house) and function15(house):
            print house
            print 'ok'


def find(house, col_val, row_house):
    if col_val == 5:
        filter_way(house)
        return
    if row_house == 4:
        find(house, col_val + 1, 0)

    for row in range(row_house, 5):
        exchange(house, col_val, row, row_house)
        find(house, col_val, row_house + 1)
        exchange(house, col_val, row, row_house)


def find_res():
    house = [[0] * 5 for i in range(5)]
    # init
    for col in range(5):
        for row in range(5):
            house[row][col] = row

    find(house, 0, 0)

    # iterator
    # for i in range(5):
    #     house[0][0]=i
    #     for j in range(5):
    #         if j!=i:
    #             house[0][1]=j
    #         for k in range(5)


find_res()
