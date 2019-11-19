package com.chen.learn.algorithm.fee_learn.design_pattern.package_3.m3_1_assemble_line;

import com.chen.learn.algorithm.util.Helper;

/**
 * Created by chencc on 2018/10/9.
 * 有两条装配线，有进出时间，有加急件，求最优的生产方式
 *
 * 数据建模的过程是非常重要的。能够想着如何将问题转化为数学模型
 * 同时，有些地方数据是否会差生重复累加，也是十分重要的技术手段
 *
 * 可以用作数据建模的一个很好的样例
 *
 * 如何进行树的遍历，而且保存最优结果，这个是一个比较好的样例
 */
public class AssLine {

    static class LineParam {
        int[][] assembleTime;
        int[][] transferTime;
        int[] enterTime;
        int[] exitTime;

        public LineParam(int[][] assembleTime, int[][] transferTime, int[] enterTime, int[] exitTime) {
            this.assembleTime = assembleTime;
            this.transferTime = transferTime;
            this.enterTime = enterTime;
            this.exitTime = exitTime;
        }
    }


    static class ResultRecord {
        int[] stepInLine;
        int curCost;
        int betterTotalCost;
        int[] betterTotalCostWay;

        public ResultRecord(int[] stepInLine, int curCost, int betterTotalCost, int[] betterTotalCostWay) {
            this.stepInLine = stepInLine;
            this.curCost = curCost;
            this.betterTotalCost = betterTotalCost;
            this.betterTotalCostWay = betterTotalCostWay;
        }
    }

    public static ResultRecord assemble(LineParam lineParam) {
        int step = 0;
        int line=0;
        int[] stepInline = new int[lineParam.assembleTime[1].length];
        int[] betterTotalWay = new int[stepInline.length];
        ResultRecord res = new ResultRecord(stepInline, 0, Integer.MAX_VALUE, betterTotalWay);
        res.curCost = lineParam.enterTime[line];
        searchWay(lineParam, res, step, line);
        line++;
        res.curCost = lineParam.enterTime[line];
        searchWay(lineParam, res, step, line);
        return res;
    }

    private static void searchWay(LineParam lineParam, ResultRecord res, int step, int line) {
        int totalStep = res.stepInLine.length;
        if (step == totalStep - 1) {
            res.curCost += lineParam.assembleTime[line][step]+lineParam.exitTime[line];
            res.stepInLine[step]=line;
            if (res.curCost < res.betterTotalCost) {
                res.betterTotalCost=res.curCost;
                System.arraycopy(res.stepInLine, 0, res.betterTotalCostWay, 0, totalStep);
            }
            return;
        }

        int curCost = res.curCost + lineParam.assembleTime[line][step];
        if (curCost > res.betterTotalCost) {
            return;
        }
        res.stepInLine[step]=line;

        res.curCost=curCost;
        searchWay(lineParam, res, step + 1, line);

        line = (line + 1) % (lineParam.assembleTime.length);
        /**
         * 这个地方非常厉害
         */
        //这一点的应用十分的巧妙，防止了数据的重复累加，只会计算有效果的数据
        res.curCost = curCost+ lineParam.transferTime[line][step + 1];
        searchWay(lineParam, res, step + 1, line);

    }

    public static LineParam initParam() {
        int[][] assembleTime = new int[][]{
                {3,2,10,3},
                {2,6,1,9}
        };
        int[][] transferTime = new int[][]{
                {0, 2, 1,1},
                {0, 1, 1,1}
        };
        int[] enterTime = new int[]{5, 2};
        int[] exitTime = new int[]{10, 7};

        return new LineParam(assembleTime, transferTime, enterTime, exitTime);
    }

    public static void main(String[] args) {
        LineParam param = initParam();
        ResultRecord res = assemble(param);

        Helper.print(res.betterTotalCostWay);
        System.out.println(res.betterTotalCost);
    }

}
