package hello.core.singleton;

// 매우 중요 !
// singleton 에서 주의 해야 할 점.
// 특정 클라이언트에 의존적인 필드 혹은 수정할 수 있으면 안된다.

public class StatefulService {

    //private int price;

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
       // this.price = price; // 잘못된 부분. 수정하면 싱글톤이어서 동일 객체를 사용하기 때문에, 문제 발생한다.
        return price;
    }

 /*   public int getPrice() {
        return price;
    }*/
}
