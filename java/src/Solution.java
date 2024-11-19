import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.sort;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 행렬 초기화
        String[][] matrix = new String[N][N];

        for(int i = 0; i < N ; i++) {
            String[] str = br.readLine().split(" ");
            matrix[i] = str;
        }

        // 영역을 저장
        List<List<String>> areaList = new ArrayList();

        for(int i = 0; i < N ; i++) {
            for(int j = 0; j < N; j++) {
                if(matrix[i][j].equals("1")) {
                    areaList = findArea(areaList, i, j, matrix);
                }
            }
        }

        // 출력
        if (areaList.size() == 0) {
            System.out.println("0");
        }
        else {
            System.out.println(areaList.size());

            List<Integer> result = new ArrayList<>();
            for (List<String> area : areaList){
                result.add(area.size());
            }
            sort(result);

            String output = "";
            for (int i : result){
                output += String.format("%d ",i);
            }

            System.out.println(output);
        }
    }

    public static List<List<String>> findArea(List<List<String>> areaList, int i, int j, String[][] matrix) {
        boolean checkUp = false;
        boolean checkLeft = false;
        String up_position = "";
        String left_position = "";
        // 상하좌우 인접한 칸들에 대한 체크 -> 상이랑 좌만
        // 상
        if (i-1 >= 0) {
            if(matrix[i-1][j].equals("1")) {
                checkUp = true;
                up_position = String.format("(%d, %d)",i-1, j);
            }
        }

        // 좌
        if (j-1 >= 0) {
            if(matrix[i][j-1].equals("1")) {
                checkLeft = true;
                left_position = String.format("(%d, %d)",i, j-1);
            }
        }
        if (checkUp || checkLeft) {
            List<List<String>> tempAreaList = new ArrayList();
            for(List<String> area : areaList) {
                for(String position : area) {
                    if(position.equals(up_position)) {
                        tempAreaList.add(area);
                        break;
                    }
                    if(position.equals(left_position)) {
                        tempAreaList.add(area);
                        break;
                    }
                }
            }
            // tempArea가 크기가 2이면 하나로 합치기
            if(tempAreaList.size() == 2) {
                areaList.remove(tempAreaList.get(0));
                areaList.remove(tempAreaList.get(1));

                List<String> newArea = new ArrayList();
                // 상 area
                for(String position : tempAreaList.get(0)) {
                    newArea.add(position);
                }
                // 좌 area
                for(String position : tempAreaList.get(1)) {
                    newArea.add(position);
                }
                newArea.add(String.format("(%d, %d)",i, j));
                areaList.add(newArea);
            }
            else {
                areaList.remove(tempAreaList.get(0));
                List<String> newArea = (tempAreaList.get(0));
                newArea.add(String.format("(%d, %d)",i, j));
                areaList.add(newArea);
            }

        }
        else {	// 둘다 없음 -> 새로운 영역 추가
            List<String> newArea = new ArrayList();
            newArea.add(String.format("(%d, %d)",i, j));
            areaList.add(newArea);
        }
        return areaList;
    }
}










