# !/bin/python
# -*- encoding:UTF-8 -*-

# 这里采用另一种，就是嵌套for循环的方式来进行实现，这样代码繁琐一些，但是可以根据条件进行优化，
# 注意这种深度的遍历都是使用迭代更好实现，要不然就是在方法中嵌套太多会很难受

# 使用二维数组，每一行代表一个房子，每一列代表一个属性（颜色，宠物等）


class Smoke:
    BlueMaster = 0
    Pall_Mall = 1
    Blends = 2
    Dunhill = 3
    Prince = 4


class Country:
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
    if function01(house) and function02(house) and function03(house) and function04(house) and function05(
            house) and function06(house) and function07(house) and function08(house):
        print 'ok'
        if function09(house) and function10(house) and function11(house) and function12(house) and function13(
                house) and function14(house) and function15(house):
            print house
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
    for first in range(Drink.Water + 1):
        for second in range(Drink.Water + 1):
            if second == first:
                continue
            for third in range(Drink.Water + 1):
                if third == second or third == first:
                    continue
                for fourth in range(Drink.Water + 1):
                    if fourth == first or fourth == second or fourth == third:
                        continue
                    for five in range(Drink.Water + 1):
                        if five == first or five == second or five == third or five == fourth:
                            continue
                        house[0][3] = first
                        house[1][3] = second
                        house[2][3] = third
                        house[3][3] = fourth
                        house[4][3] = five
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
                        iter_drink(house)


def iter_country(house):
    for first in range(Country.German + 1):
        for second in range(Country.German + 1):
            if second == first:
                continue
            for third in range(Country.German + 1):
                if third == second or third == first:
                    continue
                for fourth in range(Country.German + 1):
                    if fourth == first or fourth == second or fourth == third:
                        continue
                    for five in range(Country.German + 1):
                        if five == first or five == second or five == third or five == fourth:
                            continue
                        house[0][1] = first
                        house[1][1] = second
                        house[2][1] = third
                        house[3][1] = fourth
                        house[4][1] = five
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
                        iter_country(house)


house = [[0] * 5 for i in range(5)]
iter_color(house)


