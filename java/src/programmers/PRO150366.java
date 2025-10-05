import java.util.*;
import java.io.*;

// 표 병합
class PRO150366 {
    static int N = 50;
    static String empty = "EMPTY";
    static String[][] maps = new String[N+1][N+1];
    static int[][][] parent = new int[N+1][N+1][2];
    public String[] solution(String[] commands) {
        StringTokenizer st;
        
        // 부모들은 자기 자신으로 초기화
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                parent[i][j] = new int[] {i, j};
                maps[i][j] = empty;
            } 
        }
         
        List<String> results = new ArrayList<>();
        for(String cmd: commands) {
            //printMaps();
            st = new StringTokenizer(cmd);
            String a = st.nextToken();
            
            if(a.equals("UPDATE")) {
                if(st.countTokens() == 3) {
                    int r = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    String val = st.nextToken();
                    
                    update1(r, c, val);
                    
                } else {
                    String val1 = st.nextToken();
                    String val2 = st.nextToken();
                    
                    update2(val1, val2);
                }
                
            } else if(a.equals("MERGE")) {
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                
                
                merge(r1, c1, r2, c2);
                
            } else if(a.equals("UNMERGE")) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                    
                unmerge(r, c);
                
            } else if(a.equals("PRINT")) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                
                int[] p = find(r, c);
                results.add(maps[p[0]][p[1]]);
            }
        }
        
        String[] answer = new String[results.size()];
        for(int i=0; i<results.size(); i++) {
            answer[i] = results.get(i);
        }
        return answer;
    }
    
    // "UPDATE r c value"
    public void update1(int r, int c, String val) {
        int[] p = find(r, c);
        maps[p[0]][p[1]] = val;
    }
    
    // "UPDATE value1 value2"
    public void update2(String val1, String val2) {
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if (parent[i][j][0] == i && parent[i][j][1] == j) {
                    if(maps[i][j].equals(val1)){
                        maps[i][j] = val2;
                    }
                }
            }
        }
    }

    
    // parent 배열에서 (r,c)인 친구들의 부모를 모두 자기자신으로 초기화 & 값도 초기화 됨
    public void unmerge(int a, int b) {
        int[] root = find(a, b);
        int r = root[0];
        int c = root[1];
        String val = String.valueOf(maps[r][c]);
        
        List<int[]> group = new ArrayList<>();
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                int[] p = find(i, j);
                if(p[0] == r && p[1] == c) {
                    group.add(new int[] {i, j});
                }
            }
        }
        
        for(int[] s: group) {
            parent[s[0]][s[1]][0] = s[0];
            parent[s[0]][s[1]][1] = s[1];
            maps[s[0]][s[1]] = empty;
        }
        if (!val.equals(empty)) {
             maps[a][b] = val;
        }
    }
    
    public void merge(int r1, int c1, int r2, int c2) {
        int[] p1 = find(r1, c1);
        int[] p2 = find(r2, c2);
        
        if(Arrays.equals(p1, p2)) return;
        
        // 값 결정 (요구사항: p1의 값 우선)
        String val = maps[p1[0]][p1[1]].equals(empty) ? maps[p2[0]][p2[1]] : maps[p1[0]][p1[1]];

        // **Union 연산**: p2의 루트를 p1의 루트에 연결
        parent[p2[0]][p2[1]][0] = p1[0];
        parent[p2[0]][p2[1]][1] = p1[1];

        // 값 업데이트: 새 대표(p1)에 값 설정, 구 대표(p2)의 값 초기화
        maps[p1[0]][p1[1]] = val;
        maps[p2[0]][p2[1]] = empty; 
    }
    
//     public int[] find(int r, int c) {
//         if(parent[r][c][0] == r && parent[r][c][1] == c) {
//             return new int[]{r, c};
//         } 
        
//         return parent[r][c] = find(parent[r][c][0], parent[r][c][1]);
//     }
    // find with path compression
    public int[] find(int r, int c) {
        if(parent[r][c][0] == r && parent[r][c][1] == c) {
            return new int[]{r, c};
        } 
        
        // 경로 압축: 부모를 루트로 변경
        int pr = parent[r][c][0];
        int pc = parent[r][c][1];
        
        int[] root = find(pr, pc);
        parent[r][c][0] = root[0];
        parent[r][c][1] = root[1];
        
        return root;
    }
    
    public void printMaps() {
        for(int i=1; i<=10; i++) {
            for(int j=1; j<=10; j++) {
                System.out.print(maps[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}

// MERGE -> union-find로 그룹화?
// Unmerged - parent 배열에서 (r,c)인 친구들의 부모를 모두 자기자신으로 초기화 & 값도 초기화 됨
// PRINT, UPDATE r c -> rc가 병합된 경우 -> 부모의 값을 출력하면 되고, 부모의 값을 업데이트 하면됌
