
这个地方主要是记录一些算法常用的思想，以及一些理解。

算法当中所谓的线性模型，树模型等一般是指对应使用的数据结构，比如线性表一般是用数组来表示。
而堆的话一般使用二叉树来表示。

有些时候不同的问题需要选取不同的数据模型来表示，这样能够简化问题，

同时，有些时候，可以混合两种模型，这样可以使用两种模型的有点，
比如一般情况下top k使用的小顶堆，就是使用数组来模拟二叉树，这样的话，操作更加方便，而且主要是使用指针直接访问，高效，同时也避免了一些不必要的边界判断
并不是所有的时候都能结合两种模型，只有在有限制的情况下可以，比如大顶堆小顶堆的限制就是堆的大小是有限的，而不是无限扩张的。



## 常用技巧
1. 哨兵位
2. 巧用数组下标:可以默认规定某个下标对应的属性是啥，这样在属性数量比较少的情况下，可以用数组代替map，效果要更好
3. 取余的用法:可以防止越界
4. 方向遍历
5. 单链表相关:单链表有环判断，单链表中间元素，单链表倒数第k个元素，单链表逆序


## 常见问题
1. 数组链表
2. 数组二叉树
3. topN和最小堆
4. 常见hash算法

## 常用设计原则与策略
1. 程序设计的一致性原则
2. 以空间换时间的策略

















