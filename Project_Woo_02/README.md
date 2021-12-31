# Project WOO
공공 API를 통해 현재 위치의 날씨를 조회하고
날씨에 맞는 간단한 옷차림을 이미지로 추천해주는 웹서비스

개발환경
Eclipse, Spring, MyBatis

position function을 통해 유저의 현재 위치의 경도와 위도값을 받은 후
네이버지도 API로 상세주소를 추출하여
그에 맞는 값을 기상청 API에 요청하여 날씨데이터를 받았습니다