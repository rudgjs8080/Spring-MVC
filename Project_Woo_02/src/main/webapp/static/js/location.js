
// 요청 승인시
function success(position) {
    
    let lat = position.coords.latitude; // x축 좌표 ( 경도 )
    let lng = position.coords.longitude; // y축 좌표 ( 위도 )

    // DATA 확인용
    console.log("latitude(경도) : " + lat);
    console.log("longitude(위도) : " + lng);

    // fetch 함수로 변경예정
    // fetch(`${rootPath}/set?lat=${lat}&lng=${lng}`) // ,{data:posJson})
    // .then(response=>response.json())
    // .then(json=>console.log(json));
    location.href=`${rootPath}/api?lat=${lat}&lng=${lng}`
    
};

// 요청 거절, 에러시
function error(err) {
     
    let lat = 37.68404154086126;
    let lng = 126.98555553293436

    console.log("ERROR : " + err.code + "/" + err.message);
    location.href=`${rootPath}/api?lat=${lat}&lng=${lng}`

}

navigator.geolocation.getCurrentPosition(success, error);