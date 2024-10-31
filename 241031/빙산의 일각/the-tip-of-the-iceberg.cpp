#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>
#include <set>

using namespace std;

int main() {
    // 여기에 코드를 작성해주세요.
    int n;
    int answer = 0;
    set<int> user_set;  //빙산의 높이가 높은 순서대로 정렬하되, 중복되는 높이는 제거하기 위해서 set을 사용
    unordered_map<int,int> user_map;    //배열에서 빙산이 몇 번째로 높은지를 확인하기 위해서 map을 사용

    cin >> n;

    //0번 인덱스와 N + 1번 인덱스는 비워 두었음 (양 끝 인덱스를 참조할 수 있지만 아무것도 존재하지 않음)
    vector<int> arr(n + 2); 
    vector<vector<int>> index_arr(n + 2);
    vector<bool> visited(n + 2, false);


    for(int i=1; i <= n; i++){
        cin >> arr[i];
        user_set.insert(arr[i]);
    }

    int idx = 0;
    for(auto elem : user_set){
        user_map.insert({elem, idx++});
    }

    
    for(int i=1; i <= n; i++){
        index_arr[user_map[arr[i]]].push_back(i);
    }

    int result = 0;
    int user_set_size = user_set.size();


    for(int i= user_set_size - 1; i >= 0; i--){ //레벨(빙산의 높이)이 높은 순으로 loop

        for(int j= 0; j < index_arr[i].size(); j++){
            
            int cur_idx = index_arr[i][j];
            if(!visited[cur_idx -1] && !visited[cur_idx + 1]){  //양 옆 빙산이 모두 체크되지 않았을 경우
                result++;
            }
            else if(!visited[cur_idx - 1]){ //왼쪽 빙산만 체크된 경우
            }
            else if(!visited[cur_idx  + 1]){    //오른쪽 빙산만 체크된 경우
            }
            else{   //양 옆 빙산이 모두 체크된 경우
                result--;
            }
            visited[cur_idx] = true;    //현재 빙산을 체크
        }
        
       
        answer= max(answer, result);
    }

    cout << answer << '\n';
    return 0;
}