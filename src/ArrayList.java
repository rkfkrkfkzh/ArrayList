import Interface_form.List;

import java.util.Arrays;

//해당 클래스는 제네릭 타입 E를 사용하여 모든 데이터 유형을 수용할 수 있습니다.
// E는 클래스나 인터페이스가 아니라 타입 매개변수(parameter)입니다.
public class ArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;    // ArrayList의 기본 용량을 나타내는 상수입니다. 이 값은 10으로 설정
    private static final Object[] EMPTY_ARRAY = {};    // 빈 배열을 나타내는 상수입니다. 초기화에 사용

    private int size;    //  ArrayList의 현재 요소 수를 나타내는 변수

    Object[] array;    // ArrayList의 내부 배열을 나타내는 변수

    // 초기 크기가 0인 빈 ArrayList를 생성, 내부 배열은 EMPTY_ARRAY로 초기화
    public ArrayList() {
        this.array = EMPTY_ARRAY;
        this.size = 0;

    }

    // 지정된 초기 용량으로 ArrayList를 생성, 내부 배열은 지정된 용량으로 초기화
    public ArrayList(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
    }

    // 현재 배열의 크기 및 요소 수를 확인하고 배열의 크기가 필요한 경우 적절하게 조정
    private void resize() {
        int array_capacity = array.length;

        // 용량이 0이면 기본 용량으로 배열을 다시 만듭니다.
        if (Arrays.equals(array, EMPTY_ARRAY)) {
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        // 용량이 꽉 찼으면 현재 용량의 두 배로 배열의 크기를 늘립니다.
        if (size == array_capacity) {
            int new_capacity = array_capacity * 2;

            // copy
            array = Arrays.copyOf(array, new_capacity);
            return;
        }
        // 용적이 절반 미만으로 요소가 차지하고 있으면 현재 용량의 절반으로 배열의 크기를 줄입니다.
        // 그러나 기본 용량보다 작아지지 않도록합니다.
        if (size < (array_capacity / 2)) {
            int new_capacity = array_capacity / 2;

            // copy
            array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
            return;
        }
        /*
        private로 선언되어 있으므로 클래스 내에서만 사용됩니다.
        이 코드 자체로는 실행되지 않으며 다른 메서드 내에서 호출되어 동적 배열의 크기를 조절합니다.
         */
    }
    @Override
    public boolean add(E value) { // 내부적으로 addLast 메소드를 호출
        addLast(value); // 리스트의 마지막 인덱스 다음에 새로운 값을 추가
        return true; //
    }

    public void addLast(E value) {

        // 꽉차있는 상태라면 용적 재할당
        if (size == array.length) {
            resize();
        }
        array[size] = value;	// 마지막 위치에 요소 추가
        size++;	// 사이즈 1 증가
    }
}