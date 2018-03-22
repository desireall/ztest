package base.vist.init;

public class Child extends Parent{
    public static int i = print("child static:i");
    public int ii = print("child:ii");
 
    static{
        print("子类静态初始化");
    }
 
    {
        print("子类实例初始化");
    }
 
    public Child(String str) {
        super(str);
        System.out.println("Child constructor:" + str);
    }
 
    public static int print(String str){
        System.out.println("initial:" + str);
        return i;
    }
 
    public static void main(String args[]){
        Child child = new Child("cindy");
    }
}
