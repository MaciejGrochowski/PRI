package com.example.PRI.services;

import java.util.Random;


public class RandomService {

    private Random r;


    public RandomService(){
        this(System.currentTimeMillis());
    }

    public RandomService(long seed){
        this.r = new Random(seed);
    }

    public Double nextDouble(){
        return r.doubles().findFirst().getAsDouble();
    }

    public Integer nextInt(){
        return this.nextInt(0, Integer.MAX_VALUE);
    }

    public Integer nextInt(int maxValue){
        return this.nextInt(0, maxValue);
    }

    public Integer nextInt(int minValue, int maxValue){
        return r.ints(minValue, maxValue).findFirst().getAsInt();
    }

    public Double nextGaussian(){return r.nextGaussian();}

}
