package hello.core.singleton;

public class SingletonService {
    private static final SingletonService singletonService = new SingletonService();
    private SingletonService(){} // private으로 외부에서 생성자 만드는 것 막아두기. (new akr)
    
    public static SingletonService getSingletonService(){
        return singletonService;
    }

    
    public void logic(){
        System.out.println("singletonService = " + singletonService);
    }

}
