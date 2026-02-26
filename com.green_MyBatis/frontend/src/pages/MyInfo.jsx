import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { AuthContext } from '../contexts/AuthContext';
import { useContext } from 'react';

export default function MyInfo() {
  const [member, setMember] = useState(null);
  const navigate = useNavigate();
  const { logout } = useContext(AuthContext);

  // ✅ 컴포넌트 실행 시 내 정보 조회
  useEffect(() => {
    axios
      .get('/api/member/myinfo', {
        //"브라우저야, 요청 보낼 때 쿠키도 같이 보내줘"
        // 세션 기반 로그인일 때 중요함
        // JSESSIONID (쿠키)를 이용해서 로그인 상태를 판단합니다.
        // 쿠키가 안 가면? 👉 서버는 로그인 안 한 사용자로 인식합니다.
        withCredentials: true, // ⭐ 세션 쿠키 전달 (중요)
      })
      .then((res) => {
        if (!res.data) {
          alert('로그인이 필요합니다.');
          navigate('/member/login');
        } else {
          setMember(res.data);
        }
      })
      .catch((err) => {
        console.error(err);
      });
  }, []);

  // ✅ 회원 삭제
  const handleDelete = () => {
    if (!window.confirm('정말 삭제하시겠습니까? 삭제된 데이터는 복구 불가능')) {
      return;
    }

    axios
      .delete('/api/member/delete', {
        withCredentials: true,
      })
      .then((res) => {
        if (res.data === 1) {
          alert('회원이 삭제되었습니다.');

          logout(); // ✅ 프론트 로그인 상태 제거
          navigate('/member/login'); // ✅ 로그인 화면 이동
        } else {
          alert('삭제 실패');
        }
      })
      .catch((err) => console.error(err));
  };

  // 아래 코드가 없으면 현재 member=null인 상태이므로
  // 화면에 아무것도 출력이 안됨

  if (!member) {
    return <div>로딩중...</div>;
  }

  return (
    <section>
      <div id="section_wrap">
        <div className="word">
          <h2>개인 회원 상세 정보</h2>
        </div>

        <div className="content">
          <table border="1">
            <tbody>
              <tr>
                <th>아이디</th>
                <td>{member.id}</td>
              </tr>
              <tr>
                <th>이메일</th>
                <td>{member.mail}</td>
              </tr>
              <tr>
                <th>전화</th>
                <td>{member.phone}</td>
              </tr>
              <tr>
                <th>등록일</th>
                <td>{member.reg_date}</td>
              </tr>
            </tbody>
          </table>

          {/* 버튼 영역 */}
          <div className="btn-area" style={{ marginTop: '20px' }}>
            <button className="btn" onClick={() => navigate('/member/modify')}>
              회원수정
            </button>

            <button className="btn btn-danger" onClick={handleDelete}>
              회원삭제
            </button>

            <button className="btn" onClick={() => navigate('/')}>
              홈으로
            </button>

            <button className="btn" onClick={() => navigate('/member/signup')}>
              회원가입
            </button>
          </div>
        </div>
      </div>
    </section>
  );
}
