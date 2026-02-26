import { useSearchParams, useNavigate } from 'react-router-dom';

function SignupResult() {
  // 1️⃣ 현재 URL에 붙어있는 ?result=값 을 읽기 위해 사용하는 리액트 훅
  // 예: /member/signup_result?result=success
  // 위 주소에서 result=success 부분을 꺼내기 위한 도구
  const [searchParams] = useSearchParams();

  // 2️⃣ 페이지를 다른 주소로 이동시키기 위한 도구
  // 예: 버튼 클릭 시 로그인 페이지로 이동할 때 사용
  // navigate("/member/login") 이런 식으로 사용
  const navigate = useNavigate();

  // 3️⃣ URL에 붙어있는 result 값을 꺼내는 코드
  // 예: /member/signup_result?result=success
  // → "success" 라는 문자열을 꺼내서 result 변수에 저장
  const result = searchParams.get('result');

  return (
    <div>
      {result === 'success' && <h2>회원가입 성공 🎉</h2>}
      {result === 'duplicate' && <h2>이미 존재하는 아이디입니다</h2>}
      {result === 'fail' && <h2>회원가입 실패</h2>}
      {result === 'error' && <h2>서버 오류 발생</h2>}

      <button onClick={() => navigate('/member/login')}>
        로그인 페이지로 이동
      </button>
    </div>
  );
}

export default SignupResult;
