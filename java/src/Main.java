// 자바 자주 사용하는 코딩 테스트 문법

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    public static void StringMethod() {
        /** String 관련 메소드 **/
        String str = "stars";

        // 길이 반환
        str.length();

        // 빈 문자열인지 체크
        str.isEmpty();

        // 문자 찾기
        str.charAt(0);              // 's'
        str.indexOf('s');           // 0 - 첫 's'의 인덱스 반환
        str.lastIndexOf('s');   // 4 - 마지막 's'의 인덱스 반환

        // 문자 자르기
        str.substring(1, 3);        // "ta" (1 <= 인덱스 < 3)
        str.substring(3);   // "rs" -> 해당 인덱스 부터 끝까지

        // 문자 치환
        str.replace("s", "p");          // "ptarp" - 모든 "s"를 치환
        str.replaceFirst("s", "p");     // "ptars" - 첫 "s"만 치환

        // 문자 비교
        str.equals("stars");

        // 문자 포함 여부
        str.contains("sta");

        // 문자열 분리
        str.split(" ");     // 공백으로 분리
        str.split("");              // 한 문자씩 분리하여 String[] 로 반환

        // 공백 제거
        str.trim();                              // 문자열 앞뒤 공백제거
        str.replace(" ", "");   // 문자열 모든 공백 제거

        // 문자열 - 정수 변환
        Integer.parseInt("100");
        Integer.toString(100);


        /** StringBuilder 관련 메소드
         * 문자열 변경이 필요한 경우 **/
        StringBuilder sb = new StringBuilder();

        // 문자열 추가 & 삽입
        sb.append("stars");     // "stars"
        sb.insert(2, "oo");     // "stooars"

        // 문자열 삭제
        sb.delete(0, 2);            // "ooars"
        sb.deleteCharAt(2);   // "oors"

        // 특정 인덱스 문자열 지정
        sb.setCharAt(0, 's');   // "sors"

        // 문자열 뒤집기
        sb.reverse();   // "sros"

        // 문자열 절대길이 변경
        sb.setLength(2);    // "sr"
        sb.setLength(4);    // "sr  "   -> 뒤가 공백으로

    }

    public static void ListMethod() {
        /** List 관련 메소드 **/
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        
        // 요소 삽입
        list.add("one");
        list.add(0, "zero");
        list2.add("two");

        // 리스트 합치기
        list.addAll(list2);    // list = ["zero", "one", "two"]

        // 특정 요소의 인덱스 반환
        list.indexOf("zero");           // 첫번째 인덱스
        list.lastIndexOf("zero");    // 마지막 인덱스

        // 인덱스로 요소 조회
        list.get(2);    // "two"

        // 요소 삭제
        list.remove(0);      // 인덱스로 삭제 ["one", "two"]
        list.remove("one");     // 첫번째 요소 삭제 ["two"]

        // 리스트 차집합
        list.removeAll(list2);      // list에서 list2와 겹치는 값 삭제
        // 리스트 교집합
        list.retainAll(list2);      // list에서 list2와 겹치는 값만 남기고 삭제

        //리스트 비우기
        list.clear();

        // 리스트 비어있는지 체크
        list.isEmpty();

        // 리스트 길이
        list.size();

        // 특성 요소 포함 여부 체크
        list.contains("zero");

        // 리스트에 다른 리스트 요소 전부 포함 여부 체크
        list.containsAll(list2);    // list에 list2의 모든 값이 포함되어 있으면 true

        /** Array <-> List **/
        // Stirng 타입
        String[] stringArray = {"apple", "banana", "lemon"};
        List<String> toStringList = new ArrayList<>(Arrays.asList(stringArray));

        List<String> stringList = new ArrayList<>();
        String[] toStringArray = stringList.toArray(new String[stringList.size()]);

        // Integer 타입
        Integer[] integerArray = {1, 2, 3, 4};
        List<Integer> toIntegerList = new ArrayList<>(Arrays.asList(integerArray));

        List<Integer> integerList = new ArrayList<>();
        int[] toIntegerArray = integerList.stream().mapToInt(x -> x).toArray();
    }

    public static void CollectionsMethod() {
        Integer[] temp = {1, 2, 3, 4, 5};
        List<Integer> list = new ArrayList<>(Arrays.asList(temp));

        // 최대 최소
        Collections.max(list);
        Collections.min(list);

        // List 정렬
        Collections.sort(list);                                 // 오름차순
        Collections.sort(list, Collections.reverseOrder());     // 내림차순

        // List 뒤집기
        Collections.reverse(list);

        // List 내 원소의 갯수 반환
        Collections.frequency(list, 3);     // 1

        // List 내 원소 이진탐색
        // 이진 탐색으로, 값이 정렬되어 있지 않으면 사용할 수 없음
        // 오름차순 정렬 후, 사용하기
        Collections.binarySearch(list, 3);  // 2 - index 반환 / 없으면 -1 반환

    }
}