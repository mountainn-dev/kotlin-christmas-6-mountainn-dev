# 크리스마스 프로모션

## 기능 목록

- [x] 방문 날짜를 정한다.
  - [x] 방문 날짜를 입력한다. - InputView#readNumber()
  - [x] 값이 올바르지 않은 형태일 경우, 예외가 발생하고 재입력 받는다. - EventPlanner#setValidVisitDay()
  - [x] 방문 날짜가 평일인지, 주말인지 등을 확인할 수 있다. - VisitDay#isWeekDay(), isWeekEnd()..
  - [x] 방문 날짜가 12월 1일 로부터 며칠이 지나있는지 확인할 수 있다. - VisitDay#daySinceDecemberFirst()
- [ ] 메뉴를 주문한다.
  - [ ] 메뉴를 입력한다.
  - [ ] 값이 올바르지 않은 형태일 경우, 예외가 발생하고 재입력 받는다.
  - [ ] 주문 메뉴가 에피타이저인지, 메인 요리인지 등을 확인할 수 있다.
  - [ ] 주문 메뉴의 금액을 확인할 수 있다.
- [ ] 방문 날짜와 주문 금액을 기반으로 각종 이벤트 정보를 종합한다.
  - [ ] 할인 기준에 알맞게 할인 금액을 계산한다.
- [ ] 이벤트가 적용된 결과를 출력한다.

## 기능 요구 사항

2023년 12월 우테코 식당 방문 예약 시 각종 이벤트를 종합하여 혜택 정보를 전달해주는 프로그램입니다.

### 12월 이벤트 안내
- 할인 행사
  - 크리스마스 디데이 할인
    - 기간: 12.1 ~ 12.25
    - 내용: 할인 금액 1,000 원에서 시작하여 날마다 할인 금액 100 원씩 증가
  - 평일 할인
    - 기간: 12 월 한 달간 매주 일요일 ~ 목요일
    - 내용: 디저트 메뉴 1 개당 2,023 원 할인
  - 주말 할인
    - 기간: 12 월 한 달간 매주 금요일, 토요일
    - 내용: 메인 메뉴 1 개당 2,023 원 할인
  - 특별 할인
    - 기간: 12 월 한 달간 매 공휴일
    - 내용: 총 주문 금액에서 1,000 원 할인
  

- 증정 행사
  - 샴페인 증정
    - 기간: 12.1 ~ 12.31
    - 내용: 할인 전 총 주문 금액 120,000 원 이상 시 샴페인 1개 증정
  

- 12월 이벤트 배지
  - 기간: 12.1 ~ 12.31
  - 내용: 총 혜택 금액에 따른 배지 부여
  - 혜택 금액별 배지 등급
    - 5,000 원 이상: 별
    - 10,000 원 이상: 트리
    - 20,000 원 이상: 산타
  

- 이벤트 유의사항
  - <U>총 주문 금액 10,000 원 이상부터 이벤트 적용이 가능합니다.
  - 음료만 주문은 불가능합니다.
  - 메뉴는 한 번에 최대 20개 까지 가능합니다.</U>
  

- __총 혜택 금액 = 할인 금액 합계 + 증정 메뉴 가격__
- __할인 후 예상 결제 금액 = 할인 전 총 주문 금액 - 할인 금액__

위 이벤트 안내사항을 참고하여, 고객이 식당 방문 날짜를 입력하고 주문을 완료하면 해당 날짜와 주문 정보를  
바탕으로 이벤트 혜택 적용 결과를 출력합니다. 프로그램 사용 시 유의사항은 다음과 같습니다.

- 프로그램 유의사항
  - 방문 날짜 입력 시, 1 ~ 31 사이의 숫자만 입력 가능합니다.
  - 메뉴 입력 시, 메뉴판에 존재하는 메뉴만 주문 가능하며, 중복 입력을 허용하지 않습니다.
  - 메뉴 입력 시, 최소 1 이상의 개수를 주문해야 합니다.

## 예외 상황

기본적으로 해당 프로그램은 모든 예외 처리가 진행되어  
<u>__고객의 이벤트 혜택 결과가 출력되기 전까지 프로그램이 종료되지 않아야 합니다.__</u>

### 예외 상황 목록

- 사용자 입력
  - 공통
    - 입력값이 공백인 경우
    - 숫자 입력 상황에서 문자를 입력하는 경우
    - 여러 값을 입력할 때 구분자 쉼표(',') 가 없는 경우
  - 방문 날짜
    - 1 부터 31 사이의 수가 아닌 경우
  - 메뉴
    - 메뉴판에 없는 메뉴인 경우 (입력 형식 불일치 포함)
    - 다른 메뉴와 중복되는 경우
    - 주문 개수가 1 미만인 경우

- 클래스
  - DiscountCalculator
    - 공통
      - 고객의 총 주문 금액이 10,000 원 미만인 경우
    - dDayDiscount
      - 크리스마스 이후 방문 고객인 경우
    - weekDayDiscount
      - 평일 이외 방문 고객인 경우
      - 디저트를 주문하지 않은 고객인 경우
    - weekEndDiscount
      - 주말 이외 방문 고객인 경우
      - 메인 요리를 주문하지 않은 고객인 경우
    - specialDayDiscount
      - 공휴일 이외 방문 고객인 경우