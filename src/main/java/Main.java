import com.kourou.annotations.Benchmark;
import com.kourou.annotations.Measurement;
import com.kourou.cases.StringConcat;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Measurement(iterations = 10,countPerGroup = 5)
public class Main {
    public static void main(String[] args) {

        /*Class<?> cls = Main.class;
        Annotation annotation = cls.getAnnotation(Measurement.class);
        if (annotation == null){
            System.out.println("没有使用注解");
            return;
        }
        Measurement measurement  = (Measurement)annotation;
        System out.println(measurement.iterations());
        System.out.println(measurement.countPerGroup());*/

        //第一级默认配置
        int iterations = 10;
        int countPerGroup = 5;

        //第二级配置（类级别）
        StringConcat object = new StringConcat();
        Class<?> cls = object.getClass();
        Annotation annotationM = cls.getAnnotation(Measurement.class);
        if (annotationM != null){
            Measurement measurement = (Measurement)annotationM;
            iterations = measurement.iterations();
            countPerGroup = measurement.countPerGroup();
        }

        // 如果方法中没有BenchMark注解，不属于要测试的方法，所以跳过
        Method[] methods = cls.getMethods();
        for (Method method : methods){
            Annotation annotationB = method.getAnnotation(Benchmark.class);
            if(annotationB == null){
                continue;
            }

            //针对方法的配置
            int methodIter = iterations;
            int methodGroup = countPerGroup;

            System.out.println(method.getName());
            Annotation methodA = method.getAnnotation(Measurement.class);
            if (methodA != null){
                Measurement measurement1 = (Measurement)methodA;
                methodIter = measurement1.iterations();
                methodGroup = measurement1.countPerGroup();
            }


            //真正的测试，调用对象的测试实例方法
            for (int i = 0; i < methodGroup; i++) {
                for (int j = 0; j < methodIter; j++) {
                    try {
                        method.invoke(object);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
