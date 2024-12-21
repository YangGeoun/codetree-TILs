import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

import java.io.*;
import java.util.*;

public class Main {

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int n, m;
	static int[][] arr;
	static ArrayList<Hospital> hospitals = new ArrayList<>();
	static int emptySpaces = 0;
	static int minTime = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);

		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			String[] row = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(row[j]);
				if (arr[i][j] == 2) {
					hospitals.add(new Hospital(i, j));
				}
				if (arr[i][j] == 0) {
					emptySpaces++;
				}
			}
		}

		if (emptySpaces == 0) {
			System.out.println(0); // 빈 공간이 없으면 바로 종료
			return;
		}

		selectHospitals(0, 0, new ArrayList<>());

		if (minTime == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(minTime);
		}
	}

	// M개의 병원 선택
	public static void selectHospitals(int index, int selectedCount, List<Hospital> selectedHospitals) {
		if (selectedCount == m) {
			bfs(selectedHospitals);
			return;
		}

		for (int i = index; i < hospitals.size(); i++) {
			selectedHospitals.add(hospitals.get(i));
			selectHospitals(i + 1, selectedCount + 1, selectedHospitals);
			selectedHospitals.remove(selectedHospitals.size() - 1);
		}
	}

	// BFS로 바이러스 전파
	public static void bfs(List<Hospital> selectedHospitals) {
		PriorityQueue<Node> queue = new PriorityQueue<>((o1,o2)-> {
			return o1.time - o2.time;
		});
		boolean[][] visited = new boolean[n][n];
		int[][] time = new int[n][n];

		// 병원 좌표를 큐에 넣고 방문 처리
		for (Hospital hospital : selectedHospitals) {
			queue.add(new Node(hospital.x, hospital.y, 0,true));
			visited[hospital.x][hospital.y] = true;
		}

		int maxTime = 0;
		int spacesToFill = emptySpaces;

		// BFS 탐색 시작
		while (!queue.isEmpty()) {
			Node node = queue.poll();
		if(spacesToFill == 0) {
			break;
		}
			// 현재 칸이 빈 칸이면 빈 칸 수를 줄임
			if (arr[node.x][node.y] == 0) {
				spacesToFill--;
			}
			if (maxTime < node.time) {
				maxTime = node.time; // 가장 마지막에 도달한 시간을 갱신
				
			}
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];

				if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny] && arr[nx][ny] != 1) {
					visited[nx][ny] = true;
					if (arr[nx][ny] == 2) {
						queue.add(new Node(nx, ny, node.time + 1,true));
					}
					else {
						queue.add(new Node(nx, ny, node.time + 1,false));
					}
				}
			}
		}

		// 빈 공간을 모두 채웠는지 확인
		if (spacesToFill == 0) {
			minTime = Math.min(minTime, maxTime);
		}
		
		//System.out.println("maxTime: "+ maxTime+", inTime: "+minTime);
	}

	static class Hospital {
		int x, y;

		public Hospital(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Node {
		int x, y, time;
		boolean isHospital;

		public Node(int x, int y, int time, boolean isHospital) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.isHospital = isHospital;
		}
	}
}