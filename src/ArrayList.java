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
    public boolean add(E value) { // 매개변수로 전달된 값을 리스트의 마지막 위치에 추가
        addLast(value); // add 메소드는 내부적으로 addLast 메소드를 호출합니다.
        return true; // 항상 true를 반환
    }

    // addLast 메소드는 매개변수로 전달된 값을 리스트의 마지막 위치에 추가
    public void addLast(E value) {
        // 리스트의 현재 크기와 배열의 크기를 비교하여 배열이 꽉 차면
        if (size == array.length) {
            resize(); // 내부적으로 배열의 크기를 늘리고, 기존의 원소들을 새로운 배열에 복사
        }
        array[size] = value;    // 배열의 다음 위치에 매개변수로 전달된 값을 저장
        size++;    // 리스트의 크기를 증가
    }

    @Override
    public void add(int index, E value) {

        if (index > size || index < 0) {    // 인덱스가 범위를 벗어나면 IndexOutOfBoundsException 예외
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {    // 인덱스가 리스트의 마지막 인덱스와 같으면 addLast 메소드를 호출
            addLast(value); // addLast 메소드는 리스트의 맨 뒤에 값을 추가
        } else {
            if (size == array.length) {    // 배열의 크기를 확인하고 필요한 경우
                resize(); // resize 메소드를 호출하여 배열의 크기를 늘립니다.
            }
            // 추가할 위치 이후의 요소들을 오른쪽으로 이동시키기 위해 반복문을 사용
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = value;    // 지정된 인덱스에 값을 할당
            size++; // size를 1 증가
        }
    }

    // 첫 번째 인수로 0을 전달하여 요소를 리스트의 첫 번째 위치에 추가합니다.
    // 두 번째 인수로는 새로운 요소의 값을 전달
    public void addFirst(E value) {
        add(0, value);
    }

    @SuppressWarnings("unchecked") // 컴파일러가 형 안정성(type safety) 검사를 수행하지 않도록 하는 역할
    @Override
    public E get(int index) {
        if (index >= size || index < 0) {    //  index가 리스트의 범위를 벗어나면 예외처리
            throw new IndexOutOfBoundsException();
        }
        // array 배열에서 해당 index에 위치한 요소를 반환합니다.
        // 이때 array 배열은 ArrayList 클래스의 인스턴스 변수로 선언된 배열이며, Object 타입의 요소를 갖습니다.
        // 따라서 반환된 요소는 Object 타입으로 반환되며, (E)를 사용하여 형변환을 수행하여 반환
        return (E) array[index];
    }

    // index: 수정할 요소의 인덱스를 나타내는 정수 값, value: 지정된 인덱스의 현재 요소를 대체할 새로운 값
    @Override
    public void set(int index, E value) {
        if (index >= size || index < 0) {    // 주어진 인덱스가 유효한지 확인, index가 리스트의 범위를 벗어나면 예외처리
            throw new IndexOutOfBoundsException();
        } else {
            // array[index]에 주어진 값을 할당하여 지정된 인덱스의 요소를 새로운 값으로 설정
            array[index] = value;
        }
    }

    @Override
    public int indexOf(Object value) { // value: 검색할 객체
        int i = 0;

        // value와 같은 객체(요소 값)일 경우 i(위치) 반환
        for (i = 0; i < size; i++) { // 인덱스 i가 리스트의 크기보다 작은 동안 배열을 반복
            if (array[i].equals(value)) { // array[i]와 value를 equals 메소드를 사용하여 비교
                return i; // 현재 인덱스를 반환
            }
        }
        // value와 같은 값을 찾을 수 없다면, -1을 반환 (리스트에 해당 객체가 없음)
        return -1;
    }

    // 배열의 끝에서부터 역순으로 탐색하면서, 주어진 객체(value)가 마지막으로 나타나는 인덱스를 반환
    public int lastIndexOf(Object value) {
        // 배열의 끝부터(마지막 인덱스) 시작하여, 인덱스 i를 size - 1로 초기화합니다.
        // size는 배열의 길이를 나타내는 변수
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(value)) { // 배열의 i번째 요소(array[i])가 주어진 객체(value)와 동일한지 확인
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object value) { // 배열에 주어진 객체(value)가 포함되어 있는지 여부를 반환

        // 0 이상이면 요소가 존재한다는 뜻
        if (indexOf(value) >= 0) { // indexOf(value) 메서드를 호출하여, 배열에서 주어진 객체(value)가 처음으로 나타나는 인덱스를 찾습니다.
           /*
           만약 주어진 객체(value)가 배열에 있다면,
           indexOf(value)는 해당 객체가 처음으로 나타나는 인덱스를 반환할 것입니다.
           이 경우, indexOf(value)의 반환값이 0보다 크거나 같은 경우
           (즉, 주어진 객체(value)가 배열에 있다는 것을 나타내는 경우), true를 반환
            */
            return true;
        } else {
            /*
            만약 주어진 객체(value)가 배열에 없다면, indexOf(value)는 -1을 반환할 것입니다.
            이 경우, indexOf(value)의 반환값이 0보다 작은 경우
            (즉, 주어진 객체(value)가 배열에 없다는 것을 나타내는 경우), false를 반환
             */
            return false;
        }
    }
    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) { // 주어진 인덱스에 해당하는 요소를 제거
        // size보다 크거나 0보다 작은 인덱스가 주어지면 예외처리
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        E element = (E) array[index];	// 제거할 요소를 저장하기 위해 array[index] 값을 element 변수에 할당
        array[index] = null; // 해당 위치의 요소를 null로 설정하여 제거

        // 제거한 위치 이후의 모든 요소를 하나씩 왼쪽으로 이동
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1]; // 배열의 i번째 요소를 i+1번째 요소로 덮어쓰기
            array[i + 1] = null; // i+1번째 요소를 null로 설정
        }
        size--; // 제거 후 배열의 크기를 하나 감소
        resize(); // 필요한 경우 배열의 크기를 조절하는 resize 메소드를 호출
        return element; // 마지막으로 제거한 요소를 반환
        /*
        제거한 요소를 반환하는 이유는, remove 메소드가 호출되면 해당 인덱스에 위치한 요소를 제거하고,
        제거된 요소를 반환함으로써 사용자에게 해당 요소를 반환할 수 있도록 하기 위함입니다.
        예를 들어, List 인터페이스를 구현한 클래스에서 remove 메소드를 호출하여 리스트에서 요소를 제거할 때,
        제거된 요소를 반환하여 그 값을 다른 변수에 저장하거나 다른 용도로 사용할 수 있습니다.
        제거된 요소를 반환하지 않고, 단순히 제거만 한다면 제거된 요소의 값을 알 수 없으므로,
        제거한 요소의 값을 사용하는 경우에는 문제가 발생할 수 있습니다.
        따라서 제거된 요소를 반환하는 것은 사용자의 편의성을 높이기 위한 것입니다.
         */
    }
    @Override
    public boolean remove(Object value) { // 주어진 객체를 리스트에서 제거

        // 주어진 객체가 리스트에 존재하는지 확인
        int index = indexOf(value);

        // 리스트에 해당 객체가 없다면 indexOf 메소드는 -1을 반환
        if (index == -1) {
            return false; // false를 반환
        }

        // 찾은 인덱스를 사용하여 remove 메소드를 호출하여 해당 인덱스에 위치한 요소를 리스트에서 제거
        remove(index);
        return true; // 성공적으로 실행되었으면 true를 반환
    }
    /*
    이 코드는 객체의 동등성(equality)을 비교하기 위해 equals 메소드가 재정의되어 있어야 합니다.
    객체의 equals 메소드가 재정의되어 있지 않으면, 해당 객체와 동일한 객체를 찾을 수 없을 수 있습니다.
     */
}