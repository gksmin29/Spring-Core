package hello.core.singleton;

public class SingletonService {

    //자신의 인스턴스를 만들어서 instance에 넣어둔다.
    //이러면 static 영역에 하나만 만들어져서 올라감.
    private static final SingletonService instance = new SingletonService();

    //인스턴스를 외부에서 가져다 쓰는 메서드
    public static SingletonService getInstance() {
        return instance;
    }

    //생성자를 private 으로 잠궈둔다.
    //외부에서 새로운 인스턴스(new SingletonServe)를 만들지 못하게 함.
    private SingletonService() {}

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
