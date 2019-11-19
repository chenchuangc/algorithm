# !/bin/python
# -*- encoding:UTF-8 -*-

# 这里采用另一种，就是嵌套for循环的方式来进行实现，这样代码繁琐一些，但是可以根据条件进行优化，
# 注意这种深度的遍历都是使用迭代更好实现，要不然就是在方法中嵌套太多会很难受

# 使用二维数组，每一行代表一个房子，每一列代表一个属性（颜色，宠物等）
# 这里使用了一些剪枝策略，要不然运行的时间太久，导致无法求解，看来java还是比python快很多

# 这个是答案
# Yellow,Nowei,Cat ,Water ,Dunhill,
# Blue,Danmai,House,Tea,Blends,
# Red,England,Bird,Milk,Pall_Mall,
# Green,German,Fish,Coffe,Prince,
# White,Ruidian,Dog,Beer,BlueMaster,

import time


class Smoke:
    BlueMaster = 0
    Pall_Mall = 1
    Blends = 2
    Dunhill = 3
    Prince = 4
    reverse = ["BlueMaster", "Pall_Mall", "Blends", "Dunhill", "Prince"]


class Country:
    Nowei = 0
    Danmai = 1
    England = 2
    Ruidian = 3
    German = 4
    reverse = ["Nowei", "Danmai", "England", "Ruidian", "German"]


class Color:
    Blue = 0
    Green = 1
    Red = 2
    Yellow = 3
    White = 4
    reverse = ["Blue", "Green", "Red", "Yellow", "White"]


class Pet:
    Fish = 0
    House = 1
    Dog = 2
    Bird = 3
    Cat = 4
    reverse = ["Fish", "House", "Dog", "Bird", "Cat "]


class Drink:
    Milk = 0
    Tea = 1
    Coffe = 2
    Beer = 3
    Water = 4
    reverse = ["Milk", "Tea", "Coffe", "Beer", "Water "]


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
        if house[i][1] == Country.German:
            return house[i][4] == Smoke.Prince


def function14(house):
    for i in range(5):
        if house[i][1] == Country.Nowei:
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
            return house[i][1] == Country.England


def function02(house):
    for i in range(5):
        if house[i][1] == Country.Ruidian:
            return house[i][2] == Pet.Dog


def function03(house):
    for i in range(5):
        if house[i][1] == Country.Danmai:
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
    return house[0][1] == Country.Nowei


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
        # print 'ok'
        if function09(house) and function10(house) and function11(house) and function12(house) and function13(
                house) and function14(house) and function15(house):
            # print house
            for i in house:
                print i
            dic = [Color.reverse, Country.reverse, Pet.reverse, Drink.reverse, Smoke.reverse]
            print dic
            for row in range(5):
                s = ""
                for col in range(5):
                    val = house[row][col]
                    # print val
                    s = s + dic[col][val] + ","
                print s
            print 'ok'


# 0 color , 1 country , 2 pet , 3 drink , 4 smoke


def iter_smoke(house):
    for first in range(Smoke.Prince + 1):
        for second in range(Smoke.Prince + 1):
            if second == first:
                continue
            for third in range(Smoke.Prince + 1):
                if third == second or third == first:
                    continue
                for fourth in range(Smoke.Prince + 1):
                    if fourth == first or fourth == second or fourth == third:
                        continue
                    for five in range(Smoke.Prince + 1):
                        if five == first or five == second or five == third or five == fourth:
                            continue
                        house[0][4] = first
                        house[1][4] = second
                        house[2][4] = third
                        house[3][4] = fourth
                        house[4][4] = five
                        filter_way(house)


def iter_drink(house):
    house[2][3] = Drink.Milk
    for first in range(1, Drink.Water + 1):
        for second in range(1, Drink.Water + 1):
            if second == first:
                continue
            for third in range(1, Drink.Water + 1):
                if third == second or third == first:
                    continue
                for fourth in range(1, Drink.Water + 1):
                    if fourth == first or fourth == second or fourth == third:
                        continue
                    house[0][3] = first
                    house[1][3] = second
                    # house[2][3] =
                    house[3][3] = third
                    house[4][3] = fourth
                    # print "drink..."
                    iter_smoke(house)


def iter_pet(house):
    for first in range(Pet.Cat + 1):
        for second in range(Pet.Cat + 1):
            if second == first:
                continue
            for third in range(Pet.Cat + 1):
                if third == second or third == first:
                    continue
                for fourth in range(Pet.Cat + 1):
                    if fourth == first or fourth == second or fourth == third:
                        continue
                    for five in range(Pet.Cat + 1):
                        if five == first or five == second or five == third or five == fourth:
                            continue
                        house[0][2] = first
                        house[1][2] = second
                        house[2][2] = third
                        house[3][2] = fourth
                        house[4][2] = five
                        for i in range(5):
                            if house[i][1] == Country.Ruidian and house[i][2] == Pet.Dog:
                                # print "pet..."
                                iter_drink(house)


def iter_country(house):
    # 第9条应用，挪威在第一个房子中
    house[0][1] = Country.Nowei
    for first in range(Country.Danmai, Country.German + 1):
        for second in range(Country.Danmai, Country.German + 1):
            if second == first:
                continue
            for third in range(Country.Danmai, Country.German + 1):
                if third == second or third == first:
                    continue
                for fourth in range(Country.Danmai, Country.German + 1):
                    if fourth == first or fourth == second or fourth == third:
                        continue
                    house[1][1] = first
                    house[2][1] = second
                    house[3][1] = third
                    house[4][1] = fourth
                    # print "country"
                    for i in range(5):
                        if house[i][1] == Country.England and house[i][0] == Color.Red:
                            iter_pet(house)


def iter_color(house):
    for first in range(Color.White + 1):
        for second in range(Color.White + 1):
            if second == first:
                continue
            for third in range(Color.White + 1):
                if third == second or third == first:
                    continue
                for fourth in range(Color.White + 1):
                    if fourth == first or fourth == second or fourth == third:
                        continue
                    for five in range(Color.White + 1):
                        if five == first or five == second or five == third or five == fourth:
                            continue
                        house[0][0] = first
                        house[1][0] = second
                        house[2][0] = third
                        house[3][0] = fourth
                        house[4][0] = five
                        for i in range(4):
                            if house[i][0] == Color.Green and house[i + 1][0] == Color.White:
                                # print "color"
                                iter_country(house)


print "start..."
start = time.time()
house = [[0] * 5 for i in range(5)]
iter_color(house)
end = time.time()
print "using time:" + str(end - start)

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
