package dataStructure;

/**
 * 手写单例模式 time：2024年3月15日16:50:40 ->
 */
public class Singleton {

    /**
     * 饿汉式-获取单例对象 （直接先创建）
     */

    //1.声明共享的、静态的 单例对象
    private static final Singleton singleton = new Singleton();

    //2.私有化 构造器
    private Singleton(){}

    //3.获取单例对象-懒汉式（只有在使用的时候 才会创建）
    public static Singleton getSingleton(){
        return singleton;
    }

    /**
     * 懒汉式-获取单例对象 （只有在使用的时候 才会创建）
     */
/*
    //1.声明共享的、静态的 单例对象
    private volatile static Singleton singleton;

    //2.私有化 构造器
    private Singleton(){}

    //3.获取单例对象-懒汉式（只有在使用的时候 才会创建）
    public static Singleton getSingleton(){
        //①双重验证第一次验证
        if (singleton == null){
            //只有为null的时候 代表还没创建实例对象 才进去创建
            synchronized (Singleton.class){
                //②双重验证 第二次验证
                if (singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

 */
}
