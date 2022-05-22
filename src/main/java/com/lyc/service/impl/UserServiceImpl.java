package com.lyc.service.impl;

import com.lyc.domain.Product;
import com.lyc.domain.User;
import com.lyc.domain.VO.PidAndNumVO;
import com.lyc.domain.VO.SellerAchievementVO;
import com.lyc.domain.VO.UserCategoryCustomVO;
import com.lyc.mapper.OrderMapper;
import com.lyc.mapper.ProductMapper;
import com.lyc.mapper.UserMapper;
import com.lyc.service.UserService;
import com.lyc.service.ex.*;
import com.lyc.utils.Kmeans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentMap;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderMapper orderMapper;

    public static HashMap<Integer,Integer> pid2index = new HashMap<>();

    //主页推荐商品的数目
    public static int RecommendProductNum = 15;

    //与当前用户的相似度排名前k的用户
    public static int KthSimilarity = 3;

    @Override
    public void regist(User user) {
        User user1 = userMapper.findByUsername(user.getUsername(),user.getRole());
        if (user1==null){
            int insert = userMapper.insert(user);
            if (insert!=1){
                throw new InsertException("系统产生未知异常，请重新注册！");
            }
        }else{
            throw new UsernameDuplicatedException("用户名被占用！");
        }
    }

    @Override
    public User login(String username, String password,int role) {
        User user = userMapper.findByUsername(username,role);
        if (user==null){
            throw new UsernameNotFoundException("用户名不存在！");
        }else {
            if (!user.getPassword().equals(password)){
                throw new PasswordNotMatchException("密码不正确！");
            }else {
                return user;
            }
        }

    }

    @Override
    public List<User> queryAllSeller() {
        List<User> sellerList = userMapper.findAllSeller();
        return sellerList;
    }

    @Override
    public int deleteUser(Integer id) {
        int deletes = userMapper.deleteUserById(id);
        if (deletes!=1) {
            throw new UserDeleteException("用户删除出现未知异常！");
        }
        return deletes;
    }

    @Override
    public int resetPassword(Integer id) {
        return userMapper.resetPasswordById(id);
    }

    /**
     * 统计用户对于商品的购买数量向量（购买为1，浏览为0）
     * @param id
     * @return
     */
    @Override
    public double[] getVectorByUid(Integer id) {
        List<Integer> idList = productMapper.findAllId();
        int index = 0;
        for (Integer pid : idList) {
            pid2index.put(pid, index);
            index++;
        }
//        System.out.println(pid2index);
        List<PidAndNumVO> VOList = orderMapper.findOrderItemPidByUid(id);
        double[] vectore = new double[idList.size()];
        for (PidAndNumVO VO : VOList) {
            if (pid2index.get(VO.getPid())!=null){
                vectore[pid2index.get(VO.getPid())] = vectore[pid2index.get(VO.getPid())] + VO.getNum();
            }
        }
        List<Integer> pids = productMapper.findPidByUid(id);
        for (Integer pid : pids) {
            if (pid2index.get(pid)!=null){
                vectore[pid2index.get(pid)] = vectore[pid2index.get(pid)] + 0.5;
            }
        }
        return vectore;
    }

    /**
     * 计算两个向量的余弦相似度
     * @param a
     * @param b
     * @return
     */
    public double countSimilarity(double [] a,double [] b){
        double total=0;
        double alength=0;
        double blength=0;
        for(int i=0;i<a.length;i++){
            total=total+a[i]*b[i];
            alength=alength+a[i]*a[i];
            blength=blength+b[i]*b[i];
        }
        double down=Math.sqrt(alength)*Math.sqrt(blength);
        double result=0;
        if(down!=0){
            result =total/down;
        }
        return result;
    }

    /**
     * 计算余弦相似度最高的n个用户
     * @param uid
     * @return
     */
    @Override
    public List<Map.Entry<Integer,Double>> getMaxSimilarity(Integer uid){
        Map<Integer,Double> result =new HashMap<Integer, Double>();
        double vector[] = this.getVectorByUid(uid);
        List<Integer> allUserId = userMapper.findAllUserId();
        for (Integer id : allUserId) {
            if(!id.equals(uid)){
                double [] temp = getVectorByUid(id);
//                System.out.println(id+"->"+temp);
                double similarity =countSimilarity(temp,vector);
                result.put(id,similarity);
            }
        }
        List<Map.Entry<Integer,Double>> list = new LinkedList<Map.Entry<Integer,Double>>( result.entrySet() );
        Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        return list;
    }

    /**
     * 获得相似用户集合购买的商品，并统计相似用户购买的商品的数量，进行排序
     * @param list
     * @return
     */
    @Override
    public PriorityQueue<Integer> getProducts(List<Map.Entry<Integer,Double>> list){
        List<Integer> similarUserIdList =new ArrayList<Integer>();
        System.out.println("相似度高的"+KthSimilarity+"个用户:  ");
        for(int i=0;i<list.size()&&i<KthSimilarity;i++){
            int id =list.get(i).getKey();
            similarUserIdList.add(id);
            System.out.print(id+" ");
        }
//        System.out.println();
        Map<Integer, PidAndNumVO> map =new HashMap<Integer,PidAndNumVO>();
        for (Integer uid : similarUserIdList) {
            List<PidAndNumVO> pidAndNumVOS = orderMapper.findOrderItemPidByUid(uid);
            List<Integer> pidByUids = productMapper.findPidByUid(uid);
            for (PidAndNumVO pidAndNumVO : pidAndNumVOS) {
                PidAndNumVO tempVO = null;
                if (map.containsKey(pidAndNumVO.getPid())){
                    tempVO = map.get(pidAndNumVO.getPid());
                    tempVO.num+=pidAndNumVO.num;
                }else {
                    tempVO = new PidAndNumVO();
                    tempVO.pid = pidAndNumVO.pid;
                    tempVO.num = pidAndNumVO.num;
                }
                map.put(pidAndNumVO.getPid(),tempVO);
            }

            for (Integer pid : pidByUids) {
                PidAndNumVO tempVO = null;
                if (map.containsKey(pid)){
                    tempVO = map.get(pid);
                    tempVO.num+=0.5;
                }else {
                    tempVO = new PidAndNumVO();
                    tempVO.pid = pid;
                    tempVO.num= 0.5;
                }
                map.put(pid,tempVO);
            }
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1).getNum().compareTo(map.get(o2).getNum());
            }
        });

        for (Integer key : map.keySet()) {
            priorityQueue.add(key);
            if (priorityQueue.size()>RecommendProductNum){
                priorityQueue.remove();
            }
        }
        return priorityQueue;
    }


    /**
     * 获取用户-商品类-消费额矩阵
     */
    @Override
    public void getUserCategoryConsumption(){
        List<UserCategoryCustomVO> userCategoryCustomVOList = userMapper.findUserCategoryCustom();
        int userNum = userMapper.findUserNum();
//        System.out.println(userNum);
        List<List<Long>> matrix = new ArrayList<>(userNum);
        for (int i = 0; i <= userNum; i++) {
            List<Long> userVector = new ArrayList<>(18);
            for (int j = 0; j < 18; j++) {
                userVector.add(0L);
            }
            matrix.add(userVector);
        }
        for (UserCategoryCustomVO userCategoryCustomVO : userCategoryCustomVOList) {

            matrix.get(userCategoryCustomVO.getU_id()-1).set(userCategoryCustomVO.getCategory_id()-1,userCategoryCustomVO.getConsumption());
        }

        Kmeans.matrix = matrix;
    }

    @Override
    public SellerAchievementVO getSellerAchievement(Integer uid, Integer time) {
        return userMapper.findSellerAchievement(uid,time);
    }
}
