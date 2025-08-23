import java.util.*;

// ㅅㅓ버 증설
class PRO389479{
    public static List<Integer> server;
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        server = new ArrayList<>();

        for(int i=0; i < players.length; i++) {
            endServer(i, k);

            // 증설 판단
            int p = players[i];
            int n = server.size();

            if(p < m) {
                continue;
            }

            if((n+1)*m <= p) {
                // 현재 증설해야하는 서버 수
                int newServer = p / m - n;
                increaseServer(i, newServer);
                answer += newServer;
            }
        }

        return answer;
    }

    // count 개수만큼 서버 증설하기
    public void increaseServer(int time, int count) {
        for(int i=0; i<count; i++) {
            server.add(time);
        }
    }

    // 시간이 지난 서버 종료하기
    public void endServer(int time, int k) {
        server.removeIf(s -> s+k <= time);
    }
}