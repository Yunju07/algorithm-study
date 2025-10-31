import java.util.*;

// 지게차와 크레인 - 시뮬레이션
class Solution {
    static int count, n, m;
    static boolean[][] visited;
    static String[][] map, temp; 
    static int[][] dir;
    public int solution(String[] storage, String[] requests) {
        
        n = storage.length; 
        m = storage[0].length();
        count = n*m;
        
        map = new String[n][m];
        for(int i=0; i<n; i++) {
            map[i] = storage[i].split("");
        }
        
        dir = new int[4][2];
        dir[0] = new int[]{0, -1};
        dir[1] = new int[]{0, 1};
        dir[2] = new int[]{-1, 0};
        dir[3] = new int[]{1, 0};
        
        for(String r: requests) {
            cmd(r);
            //System.out.print(r+"\n");   
        }
    
        return count;
    }
    
    // request를 map에 적용시킨다
    // 'Emp' -> 해당 칸이 비었음을 의미
    public void cmd(String request) {
            
        // type 1: 지게차 활용 - 접근 가능한
        if(request.length() == 1) {
            copytemp();
            // r: 0/(n-1) c: 0/(m-1)
            // 제일 바깥 테두리가 탐색의 시작점
            visited = new boolean[n][m];
            for(int c=0; c<m; c++) {
                dfs(0, c, request);
                dfs(n-1, c, request);
            }
            for(int r=0; r<n; r++) {  
                dfs(r, 0, request);
                dfs(r, m-1, request);
            }
            map = temp;
            
        } else {
            // 크레인 활용 -> 그냥 다 지우기
            String target = request.split("")[0];
            for(int i=0; i<n; i++) {
                for(int j=0; j<m; j++) {
                    if(map[i][j].equals(target)){
                        map[i][j] = "Emp";
                        count--;
                    }
                }
            }
        }
    }
    
    // map - 현재 창고 상태
    // temp - 에 반영
    public void dfs(int r, int c, String val) {
        if(visited[r][c]) {
            return;
        }
        visited[r][c] = true;
        
        // 칸 삭제
        if(map[r][c].equals(val)) {
            temp[r][c] = "Emp";
            count--;
        }
        
        // 빈칸 -> 다른 칸으로 탐색
        if(map[r][c].equals("Emp")) {
            for(int[] d: dir) {
                int nr = r+d[0];
                int nc = c+d[1];
                if(0<=nr && nr<n && 0<=nc && nc<m) {
                    dfs(nr, nc, val);
                }
            }
        }
    }
    
    // 현재 temp에 현재 map를 복제
    public void copytemp(){
        temp = new String[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                temp[i][j] = map[i][j];
            }
        }
    }
}
