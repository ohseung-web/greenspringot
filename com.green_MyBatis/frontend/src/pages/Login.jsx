import { useState, useContext } from 'react';
import { AuthContext } from '../contexts/AuthContext';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './Member.css';

export default function Login() {
  // 1️⃣ 입력값을 각각 따로 관리 (이게 훨씬 이해 쉬움)
  const [id, setId] = useState('');
  const [pw, setPw] = useState('');

  const navigate = useNavigate();
  const { login } = useContext(AuthContext);

  // 2️⃣ 로그인 버튼 클릭 시 실행
  const handleLogin = () => {
    // 간단한 유효성 검사
    if (id === '') {
      alert('아이디를 입력하세요');
      return;
    }
    if (pw === '') {
      alert('비밀번호를 입력하세요');
      return;
    }
    // 3️⃣ 서버로 로그인 요청
    axios
      .post('/api/member/login', {
        id: id,
        pw: pw,
      })
      .then((res) => {
        if (res.data) {
          alert(`${res.data.id}님 환영합니다!`);

          // 4. Context의 login 함수 호출 (세션 저장 + 상태 업데이트가 한 번에 됨)
          login(res.data);
          // 로그인 성공 → 홈페이지로 이동
          navigate('/');
        } else {
          // 로그인 실패
          alert('아이디 또는 비밀번호를 확인하세요.');
        }
      })
      .catch((err) => console.error(err));
  };

  return (
    <div id="section_wrap">
      <div className="word">로그인</div>

      <table width="500" border="1">
        <tbody>
          <tr>
            <td>아이디</td>
            <td>
              <input type="text" onChange={(e) => setId(e.target.value)} />
            </td>
          </tr>

          <tr>
            <td>비밀번호</td>
            <td>
              <input type="password" onChange={(e) => setPw(e.target.value)} />
            </td>
          </tr>

          <tr>
            <td colSpan="2" align="center">
              <button onClick={handleLogin}>로그인</button>
              <button type="reset">취소</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
}
