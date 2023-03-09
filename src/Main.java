public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {

        ArrayList<Integer> original = new ArrayList<>();
        original.add(10);    // 원래 ArrayList 객체(original)를 생성하고 값으로 10을 추가
        // original 객체를 참조하는 두 개의 ArrayList 객체(copy, clone)를 만듭니다.
        // copy 객체는 original 객체와 같은 참조(reference)를 가지지만,
        // clone 객체는 original 객체의 복사본을 참조
        ArrayList<Integer> copy = original;
        ArrayList<Integer> clone = (ArrayList<Integer>) original.clone();

        copy.add(20);    // copy 객체에 20을 추가
        clone.add(30);    // clone 객체에 30을 추가

        System.out.println("original list");
        for (int i = 0; i < original.size(); i++) {
            System.out.println("index " + i + " data = " + original.get(i));
        }

        System.out.println("\ncopy list");
        for (int i = 0; i < copy.size(); i++) {
            System.out.println("index " + i + " data = " + copy.get(i));
        }

        System.out.println("\nclone list");
        for (int i = 0; i < clone.size(); i++) {
            System.out.println("index " + i + " data = " + clone.get(i));
        }

        System.out.println("\noriginal list reference : " + original);
        System.out.println("copy list reference : " + copy);
        System.out.println("clone list reference : " + clone);
        /*
        original, copy, clone 객체의 참조(reference)를 출력합니다.
        이 결과에서는 original과 copy가 같은 참조를 가지고 있지만,
        clone 객체는 original 객체의 복사본을 참조하므로 다른 참조를 가지고 있습니다.
         */
        ArrayList<Integer> list = new ArrayList<>();

// get list to array (using toArray())
        // toArray() 메서드를 사용하여 Object 타입의 배열 array1에 변환하는 것
        // 이 방법은 제네릭 타입 정보를 잃게 되므로, 실제 타입이 무엇인지 알 수 없습니다.
        Object[] array1 = list.toArray();

// get list to array (using toArray(T[] a)
        // toArray(T[] a) 메서드를 사용하여 Integer 타입의 배열 array2에 변환하는 것
        // 이 방법은 변환할 배열의 타입을 명시할 수 있으므로, 변환된 배열의 타입이 Integer로 지정
        // 또한, 변환될 배열의 크기를 미리 지정할 수 있습니다.
        Integer[] array2 = new Integer[10];
        array2 = list.toArray(array2);
        /*
        만약 변환될 배열의 크기가 충분하지 않으면, toArray() 메서드는 새로운 배열을 생성하여 list의 모든 요소를 저장하게 됩니다.
        반면, 변환될 배열의 크기가 충분하면, list의 요소를 해당 배열에 복사하고, 남은 공간은 null로 채워집니다.
         */
    }
}