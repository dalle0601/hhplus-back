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
[![](https://mermaid.ink/img/pako:eNrlV-9r20YY_leOg0JDvBDHTmKLEWjmsIX1R6jTfdg8hmZfHFFb8mS5NAuBNGglTQTJoF4cYxd3y5oUVvAar_Og_Yd8p_9hr3SyLNmS4wz2ZfMH-3z3_nje5x69p9vGWSVHsIDL5LsKkbMkJYl5VSxm5IyM4FMSVU3KSiVR1tCD9Mr90Vlq7Pa6bXbSGV26tbY6OglBvlj9ZGV0IbXcT2ol-mhpCdwF9NXavfT612j93ucrdxFtN3rdZ4jVn7O3b7gtGIGpE1RAUrlcIQ_KRF1XHhInnLMIZqllAfG51LLXa9jOzuymNH965s1loRPQTZWUp3yw-uhv3ODRp4fCw_wgw7Sdwl3gkad56CEaXIKBjE9XgAvW7LD66157lx78iuhei503EHvZNusG9ysoSmmwLaj3dpe9aiJ2eGYaF-ywwY3sj5jVpEeiRjzWg1V3zo_BfNoyn7QRa7TM-oUTfOCUI6ExiZzjA6WkBcDTf-9d_sGaNZj4wOrtYBw2a-H1-5Th5huSSJ5otx6JUkH8tkBSAHRSkQSlvb2aXoftr5mntUAN3ZbK2se-bEthUgoNz-VwDUmwnw2mN_-ZJP4DivCVf21FpImoBSviSk04ia-nCSvfZJrwhh_TIni_7JNQ22fVD_8PDXgrnmjbNVIsWfTfJ3BeADhJkdHNCoy_kXIRJHqfWs9fy2OKxwvSiVnt0MO_XDStY6CE291VoHxVym9qSNlAlu08faf32jpiL3TYAPqyMcbb-z3B4eWpaVXeUMIEZiGgR6esuu8kY2c_gujo4T4Q3mathlmt0eb73mULsapBX733whALmh0AHBA736V7NT9wRJ800ZgdczRDzw36i-GLH7BZ5HFJUu16PCf7KP2cyj4ABw6wCqUMXIJ3wuGB1w0-NagJUB2wphGUzXOAX8mtExOGFhQdRn9esKcGPb5gTd1XOSmUyRCpQBrUo7OTYxfbcx3EwvQuOzizVMPOdfbihwEW3_uTfT6YR7-xZtc86Aa1xeCOaL1ErSkSvJj1n4mpMNI90VtVetnxdb8wwQZ1RkXOI55zHLHD6UIK543QY_zuNdB4VeXZTVHNk9HiI6hk_Q_lgEenx41_gY1xZITnHUtLX5HjySiJW0Uia8NMwMCemqhJjlLVz-1lB36O3JbTcPvIqW5vmacPXoNATw_8DM5cRd0aR6SDiu512MkbGw4_SgCD3Z44Iu9euucKjuAiUYuilIOL1Lb9toa1TVIkGQzwcE5UH2ZwRt4BO7GiKektOYsFTa2QCK6UcsCcc-nCwoYIz38Ew30IC9v4MRbmozPJ5OxCcjYaXYwnF-NzEbyFhWg8MbMwH48lFpKJuVhyNr4Twd8rCgSYm4nFkonFWCwK07HEbGIhgklOguLv8Huefd2zM3xpO1gwdv4GmDLIGg?type=png)](https://mermaid.live/edit#pako:eNrlV-9r20YY_leOg0JDvBDHTmKLEWjmsIX1R6jTfdg8hmZfHFFb8mS5NAuBNGglTQTJoF4cYxd3y5oUVvAar_Og_Yd8p_9hr3SyLNmS4wz2ZfMH-3z3_nje5x69p9vGWSVHsIDL5LsKkbMkJYl5VSxm5IyM4FMSVU3KSiVR1tCD9Mr90Vlq7Pa6bXbSGV26tbY6OglBvlj9ZGV0IbXcT2ol-mhpCdwF9NXavfT612j93ucrdxFtN3rdZ4jVn7O3b7gtGIGpE1RAUrlcIQ_KRF1XHhInnLMIZqllAfG51LLXa9jOzuymNH965s1loRPQTZWUp3yw-uhv3ODRp4fCw_wgw7Sdwl3gkad56CEaXIKBjE9XgAvW7LD66157lx78iuhei503EHvZNusG9ysoSmmwLaj3dpe9aiJ2eGYaF-ywwY3sj5jVpEeiRjzWg1V3zo_BfNoyn7QRa7TM-oUTfOCUI6ExiZzjA6WkBcDTf-9d_sGaNZj4wOrtYBw2a-H1-5Th5huSSJ5otx6JUkH8tkBSAHRSkQSlvb2aXoftr5mntUAN3ZbK2se-bEthUgoNz-VwDUmwnw2mN_-ZJP4DivCVf21FpImoBSviSk04ia-nCSvfZJrwhh_TIni_7JNQ22fVD_8PDXgrnmjbNVIsWfTfJ3BeADhJkdHNCoy_kXIRJHqfWs9fy2OKxwvSiVnt0MO_XDStY6CE291VoHxVym9qSNlAlu08faf32jpiL3TYAPqyMcbb-z3B4eWpaVXeUMIEZiGgR6esuu8kY2c_gujo4T4Q3mathlmt0eb73mULsapBX733whALmh0AHBA736V7NT9wRJ800ZgdczRDzw36i-GLH7BZ5HFJUu16PCf7KP2cyj4ABw6wCqUMXIJ3wuGB1w0-NagJUB2wphGUzXOAX8mtExOGFhQdRn9esKcGPb5gTd1XOSmUyRCpQBrUo7OTYxfbcx3EwvQuOzizVMPOdfbihwEW3_uTfT6YR7-xZtc86Aa1xeCOaL1ErSkSvJj1n4mpMNI90VtVetnxdb8wwQZ1RkXOI55zHLHD6UIK543QY_zuNdB4VeXZTVHNk9HiI6hk_Q_lgEenx41_gY1xZITnHUtLX5HjySiJW0Uia8NMwMCemqhJjlLVz-1lB36O3JbTcPvIqW5vmacPXoNATw_8DM5cRd0aR6SDiu512MkbGw4_SgCD3Z44Iu9euucKjuAiUYuilIOL1Lb9toa1TVIkGQzwcE5UH2ZwRt4BO7GiKektOYsFTa2QCK6UcsCcc-nCwoYIz38Ew30IC9v4MRbmozPJ5OxCcjYaXYwnF-NzEbyFhWg8MbMwH48lFpKJuVhyNr4Twd8rCgSYm4nFkonFWCwK07HEbGIhgklOguLv8Huefd2zM3xpO1gwdv4GmDLIGg)
