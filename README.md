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

z3B4eWpaVXeUMIEZiGgR6esuu8kY2c_gujo4T4Q3mathlmt0eb73mULsapBX733whALmh0AHBA736V7NT9wRJ800ZgdczRDzw36i-GLH7BZ5HFJUu16PCf7KP2cyj4ABw6wCqUMXIJ3wuGB1w0-NagJUB2wphGUzXOAX8mtExOGFhQdRn9esKcGPb5gTd1XOSmUyRCpQBrUo7OTYxfbcx3EwvQuOzizVMPOdfbihwEW3_uTfT6YR7-xZtc86Aa1xeCOaL1ErSkSvJj1n4mpMNI90VtVetnxdb8wwQZ1RkXOI55zHLHD6UIK543QY_zuNdB4VeXZTVHNk9HiI6hk_Q_lgEenx41_gY1xZITnHUtLX5HjySiJW0Uia8NMwMCemqhJjlLVz-1lB36O3JbTcPvIqW5vmacPXoNATw_8DM5cRd0aR6SDiu512MkbGw4_SgCD3Z44Iu9euucKjuAiUYuilIOL1Lb9toa1TVIkGQzwcE5UH2ZwRt4BO7GiKektOYsFTa2QCK6UcsCcc-nCwoYIz38Ew30IC9v4MRbmozPJ5OxCcjYaXYwnF-NzEbyFhWg8MbMwH48lFpKJuVhyNr4Twd8rCgSYm4nFkonFWCwK07HEbGIhgklOguLv8Huefd2zM3xpO1gwdv4GmDLIGg)
