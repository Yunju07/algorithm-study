
import java.io.*;
import java.util.*;

// 고스택
//프로그램 에러
// 1. 숫자가 부족해서 연산 못함 -> stack의 갯수
// 2. 0으로 나눔(DIV, MOD)
// 3. 연산 결과의 절대값이 10^9 넘어갈때

public class BOJ3425 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 모든 기계
        boolean isEnd = false;
        while(!isEnd) {
            // 각 기계에서의 입력
            // 프로그램
            List<String> cmds = new ArrayList();
            while(true) {
                String input = br.readLine();

                if (input.equals("QUIT")) {
                    isEnd = true;
                    break;
                }

                if (input.equals("END")) {
                    break;
                }
                cmds.add(input);
            }

            if (isEnd) {
                break;
            }

            // 입력영역
            int n = Integer.parseInt(br.readLine());
            for(int i = 0; i < n; i++) {
                Long num = Long.parseLong(br.readLine());

                Deque<Long> stack = new ArrayDeque<>();
                stack.addFirst(num);

                boolean isError = false;
                for (String cmd: cmds) {
                    boolean error = false;

                    switch (cmd) {
                        case "POP":
                            if (stack.size() <= 0) {
                                error = true;
                            } else {
                                stack.removeFirst();
                            }

                            break;

                        case "INV":
                            if (stack.size() <= 0) {
                                error = true;
                            } else {
                                stack.addFirst(stack.removeFirst() * -1);
                            }
                            break;

                        case "DUP":
                            if (stack.size() <= 0) {
                                error = true;
                            } else {
                                stack.addFirst(stack.peekFirst());
                            }
                            break;

                        case "SWP":
                            if (stack.size() <= 1) {
                                error = true;
                            } else {
                                long a = stack.removeFirst();
                                long b = stack.removeFirst();
                                stack.addFirst(a);
                                stack.addFirst(b);
                            }
                            break;

                        case "ADD":
                            if (stack.size() <= 1) {
                                error = true;
                            } else {
                                long a = stack.removeFirst();
                                long b = stack.removeFirst();

                                // 연산결과 절대값 계산
                                if (Math.abs(a+b) > 1000000000) {
                                    error = true;
                                } else {
                                    stack.addFirst(a+b);
                                }
                            }
                            break;

                        case "SUB":
                            if (stack.size() <= 1) {
                                error = true;
                            } else {
                                long a = stack.removeFirst();
                                long b = stack.removeFirst();

                                // 연산결과 절대값 계산
                                if (Math.abs(b-a) > 1000000000) {
                                    error = true;
                                } else {
                                    stack.addFirst(b-a);
                                }
                            }
                            break;

                        case "MUL":
                            if (stack.size() <= 1) {
                                error = true;
                            } else {
                                long a = stack.removeFirst();
                                long b = stack.removeFirst();

                                // 연산결과 절대값 계산
                                if (Math.abs(a*b) > 1000000000) {
                                    error = true;
                                } else {
                                    stack.addFirst(a*b);
                                }
                            }
                            break;

                        case "DIV":
                            if (stack.size() <= 1) {
                                error = true;
                            } else {
                                long a = stack.removeFirst();
                                long b = stack.removeFirst();

                                if (a==0){
                                    error = true;
                                } else {
                                    long result = Math.abs(b) / Math.abs(a);

                                    if (a*b < 0) {
                                        result *= -1;
                                    }

                                    // 연산결과 절대값 계산
                                    if (Math.abs(result) > 1000000000) {
                                        error = true;
                                    } else {
                                        stack.addFirst(result);
                                    }
                                }
                            }
                            break;

                        case "MOD":
                            if (stack.size() <= 1) {
                                error = true;
                            }else {
                                long a = stack.removeFirst();
                                long b = stack.removeFirst();

                                if (a == 0) {
                                    error = true;
                                } else {
                                    long result = Math.abs(b) % Math.abs(a);

                                    if (b < 0) {
                                        result *= -1;
                                    }

                                    // 연산결과 절대값 계산
                                    if (Math.abs(result) > 1000000000) {
                                        error = true;
                                    } else {
                                        stack.addFirst(result);
                                    }
                                }
                            }
                            break;

                        default:	// NUM X
                            st = new StringTokenizer(cmd);
                            cmd = st.nextToken();
                            Long newNum = Long.parseLong(st.nextToken());

                            // 연산결과 절대값 계산
                            if (Math.abs(newNum) > 1000000000) {
                                error = true;
                            } else {
                                stack.addFirst(newNum);
                            }
                            break;
                    }

                    if (error) {
                        isError = true;
                        break;
                    }
                }
                // 프로그램 실행 끝
                if (isError || stack.size() != 1) {
                    bw.write("ERROR\n");
                } else {
                    long a = stack.peekFirst();
                    bw.write(a+"\n");
                }

            }
            bw.write("\n");
            br.readLine();
        }
        bw.flush();
    }
}
