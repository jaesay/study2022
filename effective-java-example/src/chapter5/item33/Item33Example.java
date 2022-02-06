package chapter5.item33;

import java.util.*;

@Item33Annotation
public class Item33Example {

    public static void main(String[] args) {
        Favorites f = new Favorites();

        f.putFavorites(String.class, "java");
        f.putFavorites(Integer.class, 1);
        f.putFavorites(Class.class, Favorites.class);

        String favoriteString = f.getFavorite(String.class);
        int favoriteInteger = f.getFavorite(Integer.class);
        Class<?> favoriteClass = f.getFavorite(Class.class);

        System.out.printf("%s %d %s%n", favoriteString, favoriteInteger, favoriteClass.getName());

        List<Integer> integerList = List.of(1, 2, 3);
        List<Integer> integerList1 = Collections.checkedList(integerList, Integer.class);
        System.out.println("integerList1 = " + integerList1);

        Item33Annotation annotation = Item33Example.class.getAnnotation(Item33Annotation.class);
    }

    // 컬렌션 API로 대표되는 일반적인 제네릭 형태에서는 한 컨테이너가 다룰 수 있는 타입 매개변수의 수가 고정되어 있다.
    // 하지만 컨테이너 자체가 아닌 키를 타입 매캐변수로 바꾸면 이런 제약이 없는 타입 안전 이종 컨테이너를 만들 수 있다.
    // e.g. DatabaseRow
    public static class Favorites {
        private Map<Class<?>, Object> favorites = new HashMap<>();

        public <T> void putFavorites(Class<T> type, T instance) {
            favorites.put(Objects.requireNonNull(type), instance);
        }

        public <T> T getFavorite(Class<T> type) {
            return type.cast(favorites.get(type));
        }
    }
}
