package com.kourou;

import com.kourou.cases.StringConcat;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    private static List<BenchmarkCase> initcases(){
        List<BenchmarkCase> cases = new ArrayList<BenchmarkCase>();
        cases.add((BenchmarkCase) new StringConcat());
        return cases;
    }
    private static void runMethodCase(BenchmarkCase bcase, Method method,
                                      int iterations,int countPerGroup){
        for (int i = 0; i < iterations; i++) {
            System.out.println();
        }
    }

}

