package chapter5.item31;

// https://web.archive.org/web/20201109035143/http://www.ibm.com/developerworks/java/library/j-jtp04298/index.html
public class CaptureTest {

    public static void main(String[] args) {
        Box<String> box = new Box<>();
        box.put("김이름");
        String s = box.get();
        System.out.println("s = " + s);
        new CaptureTest().unbox(box);
    }

    // 와일드 카드
    // raw, Object랑 차이 (이미 알음)
    public void unbox(Box<?> box) {
        // wildcard box가 할 수 있는 것 들은?
        // 1. get() 메소드 사용할 수 있다.
        // 2. Object 상속 메소드를 사용할 수 있다.
        // => 꽤 많은 것들을 할 수 있다.
        // operation의 안정성을 증명할 수가 없기 때문에 put()은 사용할 수 없다.
        // Box<?>는 T라는 type parameter가 있다는 것은 알지만 정확히 어떤 타입인지는 모른다.
        // => 타입 제약 위반할지 알수가 없다. operation의 안정성을 증명할 수가 없기 때문에 put()은 사용할 수 없다.
        // special case: null literal is a valid value for any reference type. => null은 가능
        // 그럼 wildcard는 box.get()의 리턴 타입을 알 수 있나?
        // an unbounded wildcard is Object라고 판단하는 것이 최선이다. => 인텔리제이에서 box.get()을 변수 추출해보면 Object로 만들어짐
        System.out.println(box.get());
//        box.get(); // return type of get() is a capture binding
    }

    // Wildcard capture
//    public void rebox(Box<?> box) {
//        // 컴파일러가 box 안에 wildcard를 보면
//        // ?는 T여야 하지만 정확히는 뭔지 모름
//        // 그래서 placeholder를 만든다. 이 placeholder를 capture of that particular wildcard(Wildcard capture) 라고 한다.
//        // 인텔리제이에서 확인해보면 capture of ? 타입으로 컴파일러가 할당한 것을 알 수 있다. => 상황에 따라 다른 캡쳐를 얻기 떄문에 캡쳐이름은 매번 다르다. e.g. foo(Pair<?,?> x, Pair<?,?> y)
//        box.put(box.get()); // formal type parameter (T)와 호환될 지 증명 할 수 없어서 에러 발생
//    }

    public void rebox(Box<?> box) {
        // Capture helper
        // the unknown wildcard type 이름을 컴파일러에게 알려줄 수 있다.
        reboxHelper(box);
    }

    // generic method: usually used to formulate type constraints between the parameters and/or return value of the method
    // 일반 적으로는 ^ 지만 여기서는 box의 type parameter의 이름을 주기 위해 사용 (capture conversion?) : Capture conversion is what allows the compiler to manufacture a placeholder type name for the captured wildcard, so that type inference can infer it to be that type.
    // 자바 컴파일러는 제네릭 메소드의 타입 추론을 할 수 있다 (당연)
    // 덕분에 이제 T나 V의 어떤 값이라는 것을 알 수 있음
    private <V> void reboxHelper(Box<V> box) {
        box.put(box.get());
    }
}
