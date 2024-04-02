# hhplus-back
항해플러스 백엔드 4기 과제 모음

## Week2

<img width="529" alt="스크린샷 2024-03-28 오후 6 05 17" src="https://github.com/dalle0601/hhplus-back/assets/33375877/209ab64d-a14a-4ea4-88ca-5db46de7a097">

### Api
1) 강의 수강 신청 (POST /apply-lecture)
- 수강신청 가능 시간 체크
- 수강가능 최대인원 체크
- 기존 수강신청 여부 체크
  
2) 수강 신청 여부 확인 (GET /apply-lecture/{userId})
- userId 로 특강 신청 완료 여부를 조회
- 신청 성공, 실패 여부 반환

## Week3
||Method|Description|Request|Response|
|----------|--------------|----------------|-------------------|-----------------|
|/user/token|POST|유저 토큰 발급|{"user_id": "test"}|{"result": "UUID+대기열정보"}|
|/reservation/date/concert_id}|GET|예약가능한 날짜 조회||{"result": []}|
|/reservation/seat/{concert_id}/{concert_date}|GET|예약가능한 좌석 조회||{"result": []}|
|/reservation|POST|예약하기|{"user_id":"test", "availableDate": "", "availableSeat": ""}|{"result": {"reservation_id", "user_id", "concert_id", "date", "seat", "reservationTime", "cost"}}|
|/reservation/{reservation_id}|GET|예약번호로 예약 정보 가져오기||{"result": {"user_id", "concert_id", "date", "seat", "reservationTime", "cost"}|
|/point/charge|POST|포인트 충전|{"user_id":"test", "point": 1000}|{"result": {"user_id: "test", "point": 3000}}|
|/point/{user_id}|GET|포인트 조회||{"result": "result": {"user_id: "test", "point": 3000}|
|/point|POST|결제|{"reservation_id", "user_id", "concert_id", "date", "seat", "reservationTime", "cost"}|{"result": {"user_id", "concert_id", "date", "seat", "reservationTime"}}|

### 시퀀스 다이어그램
[![](https://mermaid.ink/img/pako:eNrlV_9r20YU_1eOg0JD3BDLdtyIEWiXsIV1bajT_bB5DM2-OKK25MlyaRYCadBKmgiSQb04xiruljUprOA1XudB-w_5Tv_Dnr5asiUnGeyXzT_Y57t3733e5330TreJC3KRYB7XyHd1IhXIoiiUFKGSl_ISgk9VUFSxIFYFSUUPckv3x2epvj3od9lRb3zp1sry-CQ4-WL546XxhcXbXlAr0I2FBdjOo69W7uVWv0ar9z5buototz3oP0Os9Zy9fePYghGYuk55JNZqdfKgRpRV-SFx3bmLnkfflfnTs6APKyqPriukNhUK56G6dg0ggt20H8yfH0aYtkP4C47nacf1SHo-cZDkJ0uQIzN6rPV60N2me78iutNhp23EXnbNlu7sK8tydUg3GrzdZq8MxPZPTP2M7bcdI_sjFFTxkaCSgPVw1Z8LYzCfdswnXcTaHbN15jofbiqSWJ9EKjoDuapGwNN-H5z_wYwmTHxgrW40Dpu1-PxDFffjjZS-RNRbjwSxLHxbJosAdLT4i7e9wthl9PdFhb2znFuF8jfN42akhu6INfWjULSFOCnFunfkcAVJsJ91phn_TBL_AUWE0r-yInJEUKMVcaEm3MBX04QV73KaCLqf0CKcPuiR0NxljQ__Dw0EM75U2VVSqVr03ydwDgA4UZbQ9TqMvxGLCSQEn9rAX2vHlOMvSidmo0f3__LRdA6BEsfurgzpK2JpXUXyGrJsM_SdNuhqiL3QoAD0ZXvC7uB3WIORIgvktCytyXECsxDQg2PW2HWDsZMfQXR0fxcI77JO22w0qfF-cN5BrKHTV--DMISyajuADYidbtOdZhg4ok8MNKFirmboqU5_0UP-I4pFHldFxc4ncGKP0-9Q6QFw4QCrkMpwS3QlXB6cvGFPE3ICVHvM0KOiBQ7wC7l1fcLQgqLB6M8z9lSnh2fM0EKZk3KNjJAKpEE-Gjs69LE910AsTOuzvRNLNexUYy9-GGIJvRfZ54N58Bsz-uZeP6otRndE6-VoRRbhhct7JqbiSA947zToeS_U_eIEG9UZZamEnJiTiB0NF5O40wgDxu9eA40XZV5YF5QSGU8-garW_1gOHO_0sP0vsDGJjPi4E2nxFDmZjKqwUSGSOsoEDOypSzXJcaq82EF24OfAbzltv48ca3bJAn3wCgQGeuCncObKysYkIl1UdKfHjt7YcJyjBDDY7clBFKylf67gBK4QpSKIRbggbdpva1hdJxWSxwAPFwXlYR7npS2wE-qqnNuQCphXlTpJ4Hq1CMy5lynMrwnw_Ccw3HMwv4kfYz6Z4WbmuFQ2O5tNptOZJJdO4A3M30imMzOp-fTsfJLLzM9y83PprQT-XpbBR3KGu5mZS6VSXCaTncve5LgEJkUR8v_cucLZNzk7yJf2BgvJ1t9FN77K?type=png)](https://mermaid.live/edit#pako:eNrlV_9r20YU_1eOg0JD3BDLdtyIEWiXsIV1bajT_bB5DM2-OKK25MlyaRYCadBKmgiSQb04xiruljUprOA1XudB-w_5Tv_Dnr5asiUnGeyXzT_Y57t3733e5330TreJC3KRYB7XyHd1IhXIoiiUFKGSl_ISgk9VUFSxIFYFSUUPckv3x2epvj3od9lRb3zp1sry-CQ4-WL546XxhcXbXlAr0I2FBdjOo69W7uVWv0ar9z5buototz3oP0Os9Zy9fePYghGYuk55JNZqdfKgRpRV-SFx3bmLnkfflfnTs6APKyqPriukNhUK56G6dg0ggt20H8yfH0aYtkP4C47nacf1SHo-cZDkJ0uQIzN6rPV60N2me78iutNhp23EXnbNlu7sK8tydUg3GrzdZq8MxPZPTP2M7bcdI_sjFFTxkaCSgPVw1Z8LYzCfdswnXcTaHbN15jofbiqSWJ9EKjoDuapGwNN-H5z_wYwmTHxgrW40Dpu1-PxDFffjjZS-RNRbjwSxLHxbJosAdLT4i7e9wthl9PdFhb2znFuF8jfN42akhu6INfWjULSFOCnFunfkcAVJsJ91phn_TBL_AUWE0r-yInJEUKMVcaEm3MBX04QV73KaCLqf0CKcPuiR0NxljQ__Dw0EM75U2VVSqVr03ydwDgA4UZbQ9TqMvxGLCSQEn9rAX2vHlOMvSidmo0f3__LRdA6BEsfurgzpK2JpXUXyGrJsM_SdNuhqiL3QoAD0ZXvC7uB3WIORIgvktCytyXECsxDQg2PW2HWDsZMfQXR0fxcI77JO22w0qfF-cN5BrKHTV--DMISyajuADYidbtOdZhg4ok8MNKFirmboqU5_0UP-I4pFHldFxc4ncGKP0-9Q6QFw4QCrkMpwS3QlXB6cvGFPE3ICVHvM0KOiBQ7wC7l1fcLQgqLB6M8z9lSnh2fM0EKZk3KNjJAKpEE-Gjs69LE910AsTOuzvRNLNexUYy9-GGIJvRfZ54N58Bsz-uZeP6otRndE6-VoRRbhhct7JqbiSA947zToeS_U_eIEG9UZZamEnJiTiB0NF5O40wgDxu9eA40XZV5YF5QSGU8-garW_1gOHO_0sP0vsDGJjPi4E2nxFDmZjKqwUSGSOsoEDOypSzXJcaq82EF24OfAbzltv48ca3bJAn3wCgQGeuCncObKysYkIl1UdKfHjt7YcJyjBDDY7clBFKylf67gBK4QpSKIRbggbdpva1hdJxWSxwAPFwXlYR7npS2wE-qqnNuQCphXlTpJ4Hq1CMy5lynMrwnw_Ccw3HMwv4kfYz6Z4WbmuFQ2O5tNptOZJJdO4A3M30imMzOp-fTsfJLLzM9y83PprQT-XpbBR3KGu5mZS6VSXCaTncve5LgEJkUR8v_cucLZNzk7yJf2BgvJ1t9FN77K)
