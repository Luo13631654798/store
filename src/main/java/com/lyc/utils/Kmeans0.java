package com.lyc.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Kmeans0 {
    //记录迭代的次数
    static int count = 1;
    //文件所在路径
    static String filePath = System.getProperty("user.dir")+"\\src\\d\\Iris.txt";
    //储存从文件中读取的数据
    static ArrayList<ArrayList<Float>> table = new ArrayList<ArrayList<Float>>();
    //储存分类一的结果
    static ArrayList<ArrayList<Float>> alist = new ArrayList<ArrayList<Float>>();
    //储存分类二的结果
    static ArrayList<ArrayList<Float>> blist = new ArrayList<ArrayList<Float>>();
    //储存分类三的结果
    static ArrayList<ArrayList<Float>> clist = new ArrayList<ArrayList<Float>>();
    //记录初始随机产生的3个聚类中心
    static ArrayList<ArrayList<Float>> randomList = new ArrayList<ArrayList<Float>>();

    //读取文件中的数据，储存到集合中
    public static ArrayList<ArrayList<Float>> readTable(String filePath){
        ArrayList<Float> d = null;
        File file = new File(filePath);
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
            BufferedReader bf = new BufferedReader(isr);
            String str = null;
            while((str = bf.readLine()) != null) {
                d = new ArrayList<Float>();
                String[] str1 = str.split(",");
                for(int i = 0; i < str1.length ; i++) {
                    d.add(Float.parseFloat(str1[i]));
                }
                table.add(d);
            }
//			System.out.println(table);
            bf.close();
            isr.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("文件不存在！");
        }
        return table;
    }

    //随机产生3个初始聚类中心
    public static ArrayList<ArrayList<Float>> randomList() {
        int[] list = new int[3];
        //产生3个互不相同的随机数
        do {
            list[0] = (int)(Math.random()*30);
            list[1] = (int)(Math.random()*30);
            list[2] = (int)(Math.random()*30);
        }while(list[0] == list[1] && list[0] == list[2] && list[1] == list[2]);
//		System.out.println("索引："+list[0]+" "+list[1]+" "+list[2]);
//为了测试方便，我这里去数据集中前3个作为初始聚类中心
        for(int i = 0; i < 3 ; i++) {
            //randomList.add(list[i]);
            randomList.add(table.get(i));
        }
        return randomList;
    }

    //比较两个数的大小，并返回其中较小的数
    public static double minNumber(double x, double y) {
        if(x < y) {
            return x;
        }
        return y;
    }

    //计算各个数据到三个中心点的距离，然后分成三类
    public static void eudistance(ArrayList<ArrayList<Float>> list){
        alist.clear();
        blist.clear();
        clist.clear();
        double minNumber;
        double distancea,distanceb,distancec;
//		System.out.println("randomList:"+randomList);
        for(int i = 0; i < table.size() ; i++) {
            distancea = Math.pow(table.get(i).get(1)-list.get(0).get(1), 2) +
                    Math.pow(table.get(i).get(2)-list.get(0).get(2), 2) +
                    Math.pow(table.get(i).get(3)-list.get(0).get(3), 2) +
                    Math.pow(table.get(i).get(4)-list.get(0).get(4), 2);
            distanceb = Math.pow(table.get(i).get(1)-list.get(1).get(1), 2) +
                    Math.pow(table.get(i).get(2)-list.get(1).get(2), 2) +
                    Math.pow(table.get(i).get(3)-list.get(1).get(3), 2) +
                    Math.pow(table.get(i).get(4)-list.get(1).get(4), 2);
            distancec = Math.pow(table.get(i).get(1)-list.get(2).get(1), 2) +
                    Math.pow(table.get(i).get(2)-list.get(2).get(2), 2) +
                    Math.pow(table.get(i).get(3)-list.get(2).get(3), 2) +
                    Math.pow(table.get(i).get(4)-list.get(2).get(4), 2);
            minNumber = minNumber(minNumber(distancea,distanceb),distancec);
            if(minNumber == distancea) {
                alist.add(table.get(i));
            }else if(minNumber == distanceb) {
                blist.add(table.get(i));
            }else {
                clist.add(table.get(i));
            }
        }
        System.out.println("第"+count+"次迭代:");
        System.out.println(alist);
        System.out.println(blist);
        System.out.println(clist);
        System.out.println("\n");
        count++;
    }

    //计算每个类中四个数据的平均值，重新生成聚类中心
    public static ArrayList<Float> newList(ArrayList<ArrayList<Float>> list){
        float avnum1,avnum2,avnum3,avnum4,c=0f;
        float sum1 = 0,sum2 = 0,sum3 = 0,sum4 = 0;
        ArrayList<Float> k = new ArrayList<Float>();
        for(int i = 0; i < list.size(); i++) {
            sum1 += list.get(i).get(1);
            sum2 += list.get(i).get(2);
            sum3 += list.get(i).get(3);
            sum4 += list.get(i).get(4);
        }
        avnum1 = (float)(sum1*1.0 / list.size());
        avnum2 = (float)(sum2*1.0 / list.size());
        avnum3 = (float)(sum3*1.0 / list.size());
        avnum4 = (float)(sum4*1.0 / list.size());
        k.add(c);
        k.add(avnum1);
        k.add(avnum2);
        k.add(avnum3);
        k.add(avnum4);
        return k;
    }

    //判断两个集合的元素是否完全相同，若相同，则返回1；否则，返回0
    public static int same(ArrayList<ArrayList<Float>> list1, ArrayList<ArrayList<Float>> list2) {
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

    //迭代求出最后的分类结果
    public static void kmeans() {
        int a,b,c,k=0;
        ArrayList<ArrayList<Float>> klist = null;
        ArrayList<ArrayList<Float>> arlist = null;
        ArrayList<ArrayList<Float>> brlist = null;
        ArrayList<ArrayList<Float>> crlist = null;
        do {
            klist = new ArrayList<ArrayList<Float>>();
            arlist = new ArrayList<ArrayList<Float>>();
            brlist = new ArrayList<ArrayList<Float>>();
            crlist = new ArrayList<ArrayList<Float>>();
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

    public static void main(String[] args) {
        ArrayList<ArrayList<Float>> rlist = new ArrayList<ArrayList<Float>>();
        readTable(filePath);
        rlist = randomList();
        eudistance(rlist);
        kmeans();
    }
}
