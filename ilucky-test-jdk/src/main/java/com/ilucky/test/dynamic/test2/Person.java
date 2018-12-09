package com.ilucky.test.dynamic.test2;

public class Person {

    private String name;
    private boolean sex;
    private int age;
    
    public String getName() {
        return name;
    }
    public boolean isSex() {
        return sex;
    }
    public int getAge() {
        return age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSex(boolean sex) {
        this.sex = sex;
    }
    public void setAge(int age) {
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "Person [name=" + name + ", sex=" + sex + ", age=" + age + "]";
    }
}
