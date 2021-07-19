document.addEventListener("DOMContentLoaded", () => {
  const nav = document.querySelector("nav#main_nav");

  nav.addEventListener("click", (e) => {
    let tagName = e.target.tagName;
    if (tagName === "LI") {
      let menuText = e.target.textContent;

      // `` : backTit : 역 작은 따옴표
      // js에서 변수를 포함하는 문자열을 생성할 때 사용한다

      //urlPath라는 변수를 생성(rootPath) backtit이용
      // jsp에서는 ` 사용불가?
      // 각각의 메뉴를 클릭했을 때 공통으로 필요한 rootPath 문자열을
      // 변수에 세팅
      let urlPath = `${rootPath}`;

      if (menuText === "HOME") {
        // urlPath += rootPath + "/"
        urlPath += "/";
      } else if (menuText === "도서정보") {
        // location.href = "${rootPath}/books"
        urlPath += "/books";
      } else if (menuText === "출판사정보") {
        urlPath += "/comp";
      } else if (menuText === "저자정보") {
        urlPath += "/author";
      } else if (menuText === "로그인") {
        urlPath += "/member/login";
      } else if (menuText === "회원가입") {
        urlPath += "/member/join";
      } else if(menuText === "로그아웃"){
        urlPath += "/member/logout";
      } else if(e.target.id === "mypage"){
        urlPath += "/member/mypage";
      }


      // `내가 가야할곳 ${urlPath}`); 이 나온다
      location.href = urlPath;
    }
  });
});
