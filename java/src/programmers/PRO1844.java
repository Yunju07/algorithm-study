import java.util.*;

// 최단거리 -> bfs
class Solution {
    public int solution(int[][] maps) {
        int answer = -1;
        
        int[][] dir = new int[4][2];
        dir[0] = new int[]{0, 1};
        dir[1] = new int[]{0, -1};
        dir[2] = new int[]{1, 0};
        dir[3] = new int[]{-1, 0};
        
        int Rr = maps.length - 1;
        int Rc = maps[0].length - 1;
        System.out.println(Rr+" "+Rc);
        
        // bfs
        // [depth, r, c]
        boolean[][] visited = new boolean[Rr+1][Rc+1];
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{1, 0, 0});
        
        while(!que.isEmpty()){
            int[] n = que.poll();
            int depth = n[0];
            int r = n[1];
            int c = n[2];
            
            if(visited[r][c]) {
                continue;
            }
            visited[r][c] = true;
            
            if(r==Rr && c==Rc) {
                answer = depth;
                break;
            }
            
            for(int[] d: dir) {
                int nr = r+d[0];
                int nc = c+d[1];
                
                if(0<= nr && nr <= Rr && 0 <= nc && nc <=Rc) {
                    if(maps[nr][nc] == 1) {
                        que.add(new int[]{depth+1, nr, nc});
                    }
                }
            }
        }
        
        return answer;
    }
}
