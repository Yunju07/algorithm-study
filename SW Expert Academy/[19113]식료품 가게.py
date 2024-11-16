# 19113. 식료품 가게

# 식료품 가게 할인 행사
# 모든 품목 25% 할인 -> 판매가격은 정상가격의 75%
# 물건의 정상가는 4의 배수, 할인된 가격도 모두 정수

# 가격 프린트
# 정상 20, 80, 100 / 할인가격 15, 60, 75
# 15, 20, 60, 75, 80, 100 오름차순으로 정렬된 뒤 출력해버림

# 오름차순으로 정렬된 가격표에서 할인가격표만 출력하자
# 우선 첫번째는 무조건 할인된 가격임

T = int(input())

for test_case in range(1, T +1):
    N = int(input())
    total_prices = list(map(int, input().split()))

    sale = []

    sale_value = total_prices.pop(0)
    sale.append(sale_value)
    total_prices.remove(int(sale_value * (4 / 3)))

    while total_prices:
        sale_value = total_prices.pop(0)
        sale.append(sale_value)
        total_prices.remove(int(sale_value * (4 / 3)))

    result = f"#{test_case} "

    for num in sale:
        result += str(num) + " "

    print(result)