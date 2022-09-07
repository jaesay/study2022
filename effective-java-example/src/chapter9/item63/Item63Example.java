package chapter9.item63;

public class Item63Example {
    public static void main(String[] args) {
//        statement();
        statement2();
    }

    private static void statement() {
        // https://velog.io/@maigumi/Java%EC%97%90%EC%84%9C-%EB%AC%B8%EC%9E%90%EC%97%B4-%EB%8D%A7%EB%B6%99%EC%9D%B4%EB%8A%94-3%EA%B0%80%EC%A7%80-%EB%B0%A9%EB%B2%95
        // StringBuffer, StringBuilder, or java.lang.invoke.StringConcatFactory => 바이트코드 확인(java.lang.invoke.StringConcatFactory)
        String result = "";
        for (int i = 0; i < 100; i++) {
            result += lineForItem();
        }
        System.out.println("result = " + result);
    }

    private static void statement2() {
        StringBuilder sb = new StringBuilder(100 * lineForItem().length()); // 전체 결과를 담기에 충분한 크기로 초기화
        for (int i = 0; i < 100; i++) {
            sb.append(lineForItem());
        }
        System.out.println("sb = " + sb);
    }

    private static String lineForItem() {
        return "asdasdsaasdasdasda";
    }
}
