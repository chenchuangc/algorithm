#!/usr/bin/python
# -*- encoding:UTF-8 -*-
import requests

'''
#数据样例
health status index                                                             uuid                   pri rep docs.count docs.deleted store.size pri.store.size
green  open   kk-log_cbb_comic_pay_ironcock-service-2019.01.05                  BxWzMG_lQK6sGkJWsUXYGA   5   1   20218955            0     11.7gb          5.8gb
green  open   kk-log_cbb_base_app_app-service-2019.01.07                        BtWAtn9ZR6iXjOyftCSB1w   5   1   56063363            0     28.1gb           14gb
green  open   kk-log_cbb_comic_background_comic-admin-2019.01.04                J3kXTuTGTcuyex4FWXe6og   5   1     179220            0     56.7mb         28.3mb
'''


def convertSize(str_size):
    size = float(str_size[:-2])
    if str_size.endswith('gb'):
        return size
    if str_size.endswith('mb'):
        return size / 1024
    if str_size.endswith('kb'):
        return size / 1024 / 1024
    if str_size.endswith('tb'):
        return size * 1024


def getIndexInfo(url):
    index_info_array = requests.get(index_info_url).content.split('\n')
    info_list = []
    for info in index_info_array:
        if not info:
            continue
        ele_list = info.split()
        index_name = ele_list[2]
        #    print 'index-name:'+index_name
        if not index_name.startswith('kk-log'):
            continue
        #    print index_name
        index_health = ele_list[0]
        index_status = ele_list[1]
        uuid = ele_list[3]
        pri_shard_num = ele_list[4]
        replica_num = ele_list[5]
        doc_num = ele_list[6]
        doc_del_num = ele_list[7]
        store_size = ele_list[8]
        pri_size = ele_list[9]

        one_info_map = {}
        one_info_map['index_name'] = index_name
        one_info_map['index_health'] = index_health
        one_info_map['index_status'] = index_status
        one_info_map['uuid'] = uuid
        one_info_map['pri_shard_num'] = pri_shard_num
        one_info_map['replica_num'] = replica_num
        one_info_map['doc_num'] = doc_num
        one_info_map['doc_del_num'] = doc_del_num
        one_info_map['store_size'] = store_size
        one_info_map['pri_size'] = pri_size

        info_list.append(one_info_map)
    print 'info list size:' + str(len(info_list))
    return info_list


# 获取所有的索引信息，存储为list<map>格式
def groupByIndexAndDay(list_map_index_info, filter_list):
    filted_res = list_map_index_info
    for a_filter in filter_list:
        filted_res = filter(a_filter, filted_res)
    filted_res.sort(key=lambda ele: ele['index_name'])
    #    return filted_res
    print "index_name                               size(GB)          doc"
    for ele in filted_res:
        print format(ele['index_name'], '<60'), format(convertSize(ele['store_size']), '>7,.1f'), format(
            float(ele['doc_num']), '>19,.1f')


def groupByDay(l_map):
    data_dic = {}
    doc_num = {}
    for a_index_info in l_map:
        print a_index_info['index_name']
        index_time = a_index_info['index_name'][-10:]
        size = convertSize(a_index_info['store_size'])
        doc_count = float(a_index_info['doc_num'])
        print size
        if index_time not in data_dic.keys():
            data_dic[index_time] = size
        else:
            data_dic[index_time] += size
        if index_time not in doc_num.keys():
            doc_num[index_time] = doc_count
        else:
            doc_num[index_time] += doc_count

    data_dic_items = data_dic.items()
    data_dic_items.sort()
    sorted(data_dic.items(), lambda x, y: cmp(x[1], y[1]))

    # for k,v in data_dic.items():
    print "date          size(GB)        doc"
    for k, v in data_dic_items:
        doc = doc_num[k]
        print k + "   ", format(round(v, 2), '>10,.2f'), format(doc, '>19,.1f')


b_url = "http://log-search.quickcan.com/es/"
index_info_url = b_url + "_cat/indices"
l_map = getIndexInfo(index_info_url)

# size_filter=lambda a_index_info: convertSize(a_index_info['store_size'])>20
# groupByIndexAndDay(l_map,[size_filter])
groupByDay(l_map)
