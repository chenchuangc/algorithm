#!/bin/python
# -*- encoding:utf-8 -*-

# 不重复的字符串的排列组合
# 使用分治的方式进行处理，逐步缩小问题的规模和求解的规模，每个子问题的规模都变小了

# 分治的策略是，全量的字符串，等于减掉一个字符的后的字符串的
# 思考的时候想一下，分治一般是将问题的规模变小，或者求解的结果变小，在这里加入问题的规模字符串的长度减小，那么记过也一定减小了，
# 所以，这里要找到的就是问题的规模减小以后如何得到和和原来一致的解，也就是分治的策略。

# 可以假设进行举例，对于一个长度为5的字符串，他和长度为4的字符串之间的关系。
# 这个可以从排列组合中进行推断，其实就是对4个字符串今次那个插空，可以组成新的字符串

# 这里还有一个难点是结果的合并，因为排列组合的可能是多样的，所以只能使用共享存储的方式来收集结果，
# 也就是在子问题中最终得到结果，并不是返回到上一层方式


# 分治的结果收集方式有两种
# 1. 子方法返回子问题的解，父方法在子方法的解上处理得到父方法。
# 2. 遍历型，子问题承接父问题的结果，直接得到最终结果的一部分。

# 这个过程能否统一到分治的三步走（分解，解决，结果合并）
# 记得之前说过，迭代法常常是父亲的结果作为孩子的基础，但是分治法是相反的，孩子的结果常常作为父亲的基础。
# 在这个案例中是否还成立呢？在这里，解决子问题的时候确实没有再涉及到父亲的结果，而且父亲此时是没有结果的。
# 只是更加复杂的是父亲的操作也算是产生了结果的一部分，
# 对比一下归并排序，父亲需要等子处理完后，再将两个子问题进行结果合并，然后得出父结果。


# 先记录一下另一种实现方式，就是思考是这样的，
# 父亲对子的实现进行插空，这对于unique的字符串是有效的，后面看看考虑一下重复字符串。
# 这种是没有办法处理有重复的字符串的，因为子字符串是有可能有重复的组合的，这个时候插空的结果也是重复的。

# 另一种方式则是父亲固定了一个字符，子再排列组合，这样的话，子的排列组合不会出现重复


def insert_child(str_list, insert_char):
    des_list = []
    for ele in str_list:
        ele_len = len(ele)
        for i in range(0, ele_len + 1):
            if insert_char != ele[i:i + 1]:
                cur = ele[0:i] + insert_char + ele[i:]
                des_list.append(cur)
    return des_list


# 基于插空的想法实现的，实际中这样会对内存造成很大的消耗，而且需要大量的复制操作，
# 相对来说运行的效率应该不是太高，但是能够解决问题
def get_sequence_list(src_str):
    if len(src_str) == 1:
        return src_str
    first = src_str[0:1]
    str_list = get_sequence_list(src_str[1:])
    parent_str_list = insert_child(str_list, first)
    return parent_str_list


res = get_sequence_list("abcd")
print len(res)
print res

print '--------------------'
res = get_sequence_list("121")
print len(res)
print res
# print "asd"[0:2]
