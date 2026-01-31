// 충돌위험 찾기

// n개의 포인트: 1~n
// m개의 포인트로 구성된 운송 경로 - 로봇마다
// 로봇은 x대
// r좌표에 대해서 먼저 이동하고, c좌표에 대해서 이동
// 로봇이 같은 좌표에 있으면 충돌위험 -> 현재 설정은 총 위험이 몇번인가?

import java.util.*;

class Solution {
    public static Deque<int[]> robots;
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int n = points.length;
        int x = routes.length;  // 로봇 갯수
        int m = routes[0].length;
        
        // 로봇의 이동을 저장하는 큐
        // [로봇번호, 로봇r, 로봇c]
        robots = new ArrayDeque<int[]>();
        
        for(int r=1; r<=x; r++) {
            int rp = routes[r-1][0];
            robots.add(new int[]{r, points[rp-1][0], points[rp-1][1], 1});
        }
        
        while(!robots.isEmpty()) {
            // 충돌위험 계산
            x = robots.size();
            answer += checkCrash(x);
            
            // 모든 로봇 이동
            int[] robot;    // [로봇 번호, 로봇r, 로봇c, 이동하려는경로인덱스]
            for(int i=0; i<x; i++) {
                robot = robots.removeFirst();
                int rnum = robot[0];
                int rr = robot[1];
                int rc = robot[2];
                
                // 마지막 도착지에 도착한 경우
                if(robot[3] == m) {
                    continue;
                }
                
                int npoint = routes[rnum-1][robot[3]];
                int nr = points[npoint-1][0];
                int nc = points[npoint-1][1];
                
                // 이동할 위치 구하기 r->c 순
                if(rr != nr) {
                    if(rr < nr) {
                        rr++;
                    } else {
                        rr--;
                    }   
                } else if(rc != nc) {
                    if(rc < nc) {
                        rc++;
                    } else {
                        rc--;
                    }    
                }
                
                // 도착했다면 -> 이동하려는 경로인덱스 갱신
                if(rr == nr && rc == nc) {
                    robots.add(new int[]{rnum, rr, rc, robot[3]+1});
                } else {
                    robots.add(new int[]{rnum, rr, rc, robot[3]});
                } 
            }
        }
        
        return answer;
    }
    
    // 충돌 계산 - map을 활용한 중복 검증?
    public int checkCrash(int x){
        HashMap<String, Integer> map = new HashMap<>();
        for(int[] r: robots){
            String key = r[1]+","+r[2];
            map.put(key, map.getOrDefault(key, 0)+1);
        }
        
        int count=0;
        for(String key: map.keySet()){
            if(map.get(key) > 1) {
                count++;
            }
        }
        return count;
    }
}
