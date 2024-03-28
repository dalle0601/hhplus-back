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
