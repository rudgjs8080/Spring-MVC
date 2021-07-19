
// 요청 승인시
function success(position) {
    
    let lat = position.coords.latitude; // x축 좌표 ( 경도 )
    let lng = position.coords.longitude; // y축 좌표 ( 위도 )

    // DATA 확인용
    console.log("latitude(경도) : " + lat);
    console.log("longitude(위도) : " + lng);

    // JSON 타입으로 전환
    //let posJson = {
    //    lat, lng
    //}

    console.log(`${rootPath}`)
    // fetch(`${rootPath}/set?lat=${lat}&lng=${lng}`) // ,{data:posJson})
    // .then(response=>response.json())
    // .then(json=>console.log(json));
    location.href=`${rootPath}/set?lat=${lat}&lng=${lng}`
    
};

// 에러시
function error(err) {
    console.log("ERROR : " + err.code + "/" + err.message);
}

navigator.geolocation.getCurrentPosition(success, error);