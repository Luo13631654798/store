package com.lyc.utils;

import com.lyc.domain.VO.UserCategoryCustomVO;
import com.lyc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class Kmeans {
    //用户-商品类-消费额矩阵,通过userService提供的接口初始化
    public static List<List<Long>> matrix;

    //储存分类一的结果
    static List<List<Long>> alist = new ArrayList<>();
    //储存分类二的结果
    static List<List<Long>> blist = new ArrayList<>();
    //储存分类三的结果
    static List<List<Long>> clist = new ArrayList<>();
    //记录初始随机产生的3个聚类中心
    static List<List<Long>> randomlist = new ArrayList<>();

    static int count;


    public static List<List<Long>> randomList() {
        int[] list = new int[3];
        //产生3个互不相同的随机数
        do {
            list[0] = (int)(Math.random()*matrix.size());
            list[1] = (int)(Math.random()*matrix.size());
            list[2] = (int)(Math.random()*matrix.size());
        }while(list[0] == list[1] && list[0] == list[2] && list[1] == list[2]);
//		System.out.println("索引："+list[0]+" "+list[1]+" "+list[2]);
//为了测试方便，我这里去数据集中前3个作为初始聚类中心
        for(int i = 0; i < 3 ; i++) {
            //randomList.add(list[i]);
            randomlist.add(matrix.get(list[i]));
        }
        return randomlist;
    }
    /**
     * 计算各个数据到三个中心点的欧氏距离，然后分成三类
     */
    public static void eudistance(List<List<Long>> list){
        double minNumber = 0;
        double distancea = 0,distanceb = 0,distancec = 0;
//		System.out.println("randomList:"+randomList);
        for(int i = 0; i < matrix.size() ; i++) {
            for (int j = 0; j < matrix.get(0).size(); j++) {
                distancea+= Math.pow(matrix.get(i).get(j)-list.get(0).get(j),2);
                distanceb+= Math.pow(matrix.get(i).get(j)-list.get(1).get(j),2);
                distancec+= Math.pow(matrix.get(i).get(j)-list.get(2).get(j),2);
            }
            minNumber = Math.min(Math.min(distancea,distanceb),distancec);
            if(minNumber == distancea) {
                alist.add(matrix.get(i));
            }else if(minNumber == distanceb) {
                blist.add(matrix.get(i));
            }else {
                clist.add(matrix.get(i));
            }
        }
        System.out.println("第"+count+"次迭代:");
        System.out.println(alist);
        System.out.println(blist);
        System.out.println(clist);
        System.out.println("\n");
        count++;
    }

    //计算每个类中二十个数据的平均值，重新生成聚类中心
    public static List<Long> newList(List<List<Long>> list){

        if (list.size()==0){
            return new ArrayList<Long>();
        }
        List<Long> newCenter = new ArrayList<>();



        for (int i = 0; i < list.get(0).size(); i++) {
            newCenter.add(0L);
        }
        for(int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(0).size(); j++) {
                newCenter.set(j,newCenter.get(j)+list.get(i).get(j));
            }
        }
        System.out.println("newCenter:"+newCenter);
        for (int i = 0; i < newCenter.size(); i++) {
            newCenter.set(i,newCenter.get(i)/list.size());
        }
        return newCenter;
    }

    //判断两个集合的元素是否完全相同，若相同，则返回1；否则，返回0
    public static int same(List<List<Long>> list1, List<List<Long>> list2) {
        int countn = 0;
        if(list1.size()==list2.size()) {
            for(int i = 0; i < list1.size() ; i++) {
                for(int j = 0; j < list2.size() ; j++) {
                    if(list1.get(i).containsAll(list2.get(j)) && list2.get(j).containsAll(list1.get(i))) {
                        countn++;
                        break;
                    }
                }
            }
        }
        if(countn == list1.size()) {
            return 1;
        }else {
            return 0;
        }
    }

//    迭代求出最后的分类结果
    public static void kmeans() {
        int a,b,c,k=0;
        List<List<Long>> klist = null;
        List<List<Long>> arlist = null;
        List<List<Long>> brlist = null;
        List<List<Long>> crlist = null;
        do {
            klist = new ArrayList<>();
            arlist = new ArrayList<>();
            brlist = new ArrayList<>();
            crlist = new ArrayList<>();
            arlist.addAll(alist);
            brlist.addAll(blist);
            crlist.addAll(clist);
            klist.clear();
            klist.add(newList(alist));
            klist.add(newList(blist));
            klist.add(newList(clist));
            eudistance(klist);
            a = same(alist,arlist);
            b = same(blist,brlist);
            c = same(clist,crlist);
            if(a == 1 && b == 1 && c == 1) {
                Kmeans0.count = 1;
                break;
            }
        }while(true);
    }
}
