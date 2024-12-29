# cap - 트럭에 실을 수 있는 재활용 택배 상자의 최대 개수를 나타내는 정수

def isZero(arr1, arr2):
    for i in range(len(arr1)):
        if arr1[i] != 0 or arr2[i] != 0:
            return False
    return True

def maxIdx(arr1, arr2):
    idx = -1
    for i in range(0, len(arr1)):
        if arr1[i] != 0 or arr2[i] != 0:
            idx = i
    return idx

def solution(cap, n, deliveries, pickups):
    answer = 0
    
    # 집 번호가 높은 쪽부터 배달을 간다.
    # 집 번호가 높은 쪽부터 수거를 한다. 
    while True:
        # 이번 운행에서 가야할 거리
        distance = maxIdx(deliveries, pickups)
        answer += 2 * (distance + 1)
        
        # 배달할 택배 - 집 번호가 높은 쪽부터 cap 개 감소
        # 수거할 택배 - 집 번호가 높은 쪽부터 cap 개 감소
        d_cap = cap
        p_cap = cap
        
        while True:
            if d_cap >= deliveries[distance]:
                d_cap -= deliveries[distance]
                deliveries[distance] = 0
                
            else:
                deliveries[distance] -= d_cap
                d_cap = 0
            
            if p_cap >= pickups[distance]:
                p_cap -= pickups[distance]
                pickups[distance] = 0
                
            else:
                pickups[distance] -= p_cap
                p_cap = 0
            
            distance -= 1
            
            if distance < 0 or (d_cap == 0 and p_cap == 0):
                break
        
        if isZero(deliveries, pickups):
            break
            
    return answer