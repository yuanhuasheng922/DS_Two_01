package com.example.yuan.fanshe;

public class Student {
    //默认构造方法
    Student(String str)
    {
        System.out.print("默认的构造方法" + str);
    }
    //无参构造方法

    public Student() {
        System.out.print("无参构造方法");
    }
    public Student(char name) {
        System.out.print("姓名" + name);
    }
    public Student(char name,int age) {
        System.out.print("姓名" + name +" 年龄"+age);
    }
    protected Student(boolean n)
    {
        System.out.print("受保护的构造方法" + n);
    }
    //私有构造方法
    private Student(int age)
    {
        System.out.print("私有的构造方法  年龄" + age);
    }
}
