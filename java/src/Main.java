// 자바 자주 사용하는 코딩 테스트 문법

public class Main {
    public static void main(String[] args) {
        ListMethod();
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

    }
}