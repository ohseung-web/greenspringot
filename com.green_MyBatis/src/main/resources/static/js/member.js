/*회원가임 유효성 검사*/
function signupForm() {
    console.log('회원가입폼');

    let form = document.signup_form;
    if (form.id.value === '') {
        alert('새로운 id 입력');
        form.id.focus();
    } else if (form.pw.value === '') {
        alert('새로운 pw 입력!!');
        form.pw.focus();
    } else if (form.mail.value === '') {
        alert('새로운 email 입력!!');
        form.mail.focus();
    } else if (form.phone.value === '') {
        alert('새로운 전화번호 입력!!');
        form.phone.focus();
    } else {
        form.submit();
    }
}

	// 현재 로그인상태이면 게시글의 글쓰기 작성
	let write = document.getElementById("writeBtn");
	   
	 write.addEventListener("click", function () {
	
	    const isLogin = this.dataset.login; // "true" or "false"
	
	    if (isLogin === "true") {
	      location.href = "/board/write";
	    } else {
	      alert("로그인 후 이용 가능합니다.");
	      location.href = "/member/login";
	    }
	
	  });


