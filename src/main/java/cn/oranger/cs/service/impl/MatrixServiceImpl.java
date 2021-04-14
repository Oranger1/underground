package cn.oranger.cs.service.impl;

import cn.oranger.cs.broker.InitialMatrix;
import cn.oranger.cs.service.MatrixService;
import io.swagger.models.auth.In;
import jdk.nashorn.internal.ir.IdentNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Oranger
 * @date 2021/3/5
 */
@Service
public class MatrixServiceImpl implements MatrixService {
    @Autowired
    private InitialMatrix initialMatrix;

    @Override
    public Integer queryDistance(String beginStation, String endSatiton) {

        Map<String, Integer> nameMap = initialMatrix.getNameMap();
        Integer[][] matrix = initialMatrix.getMatrix();

        Integer begin = nameMap.get(beginStation);
        Integer end = nameMap.get(endSatiton);

        return BFS(matrix,begin,end);
    }


    public Integer BFS(Integer[][] matrix,Integer begin,Integer end){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(begin);
        int distance = 1;
        int flag = 1; //计数器
        int temp = 1; //层级结点
        int now = 0;
        int result = 0; //找到终点标志
        List<Integer> loaded = new ArrayList<>();
        loaded.add(-1);
        //队列的循环判断
        while(!queue.isEmpty()){
            now++;
            for (int i = 0;i<matrix.length;i++){
                if(matrix[begin][i] != null){
                    //如果是访问过的结点，直接跳过
                    if(loaded.contains(i)){
                        continue;
                    }
                    //如果等于终点
                    if (i == end){
                        result = 1;
                        break;
                    }else{
                        queue.add(i);
                        flag++;
                    }
                }
            }
            if (result == 1){
                break;
            }
            if (now == temp){
                temp=flag;
                distance++;
            }
            loaded.add(queue.remove());
            begin = queue.element();
        }
        return distance;

    }
}
