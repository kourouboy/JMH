package com.kourou.cases;

import com.kourou.annotations.Benchmark;
import com.kourou.annotations.Measurement;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//测试用例类
@Measurement(iterations = 1000,countPerGroup = 5)
public class StringConcat {
    private void doNothing(){

    }

    @Benchmark
    public String addString(){
        doNothing();
        String a = "";
        for (int i = 0; i < 10; i++) {
            a += i;
        }
        return a;
    }

    @Benchmark
    public  String addStringBuilder(){
        doNothing();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(i);
        }
        return sb.toString();
    }
    public static void runCase(Method method,int iterations,
                               int countPerGroup){
        for (int i = 0; i < iterations; i++) {
            System.out.printf("第%d次测试：", i + 1);
            long t1 = System.nanoTime();
            for (int j = 0; j <countPerGroup ; j++) {
                try {
                    method.invoke(null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            long t2 = System.nanoTime();
            System.out.printf("耗时 ：%d%n",t2 -t1);
        }
    }
    public static void main(String[] args){
        Method[] methods = StringConcat.class.getMethods();
        for(Method method : methods){
            if (method.getName().equals("addString") || method
            .getName().equals("addStringBuilder")){
                Annotation annotation = method.getAnnotation(Measurement.class);
                int iterations = 10;
                int countPerGroup = 1000;
                if (annotation != null){
                    Measurement measurement = (Measurement)annotation;
                    iterations = measurement.iterations();
                    countPerGroup = measurement.countPerGroup();
                }
                runCase(method,iterations,countPerGroup);
            }
        }
    }
}
