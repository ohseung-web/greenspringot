import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

export default function Modify() {
  const [member, setMember] = useState({
    id: '',
    mail: '',
    phone: '',
    pw: '',
  });

  const navigate = useNavigate();

  // ✅ 기존 정보 불러오기
  useEffect(() => {
    axios
      .get('/api/member/myinfo', {
        withCredentials: true,
      })
      .then((res) => {
        if (!res.data) {
          alert('로그인이 필요합니다.');
          navigate('/member/login');
        } else {
          setMember({
            id: res.data.id,
            mail: res.data.mail,
            phone: res.data.phone,
            pw: '',
          });
        }
      })
      .catch((err) => console.error(err));
  }, []);

  // ✅ 입력값 변경 처리
  const handleChange = (e) => {
    const name = e.target.name; // 어떤 input인지 (mail? phone? pw?)
    const value = e.target.value; // 사용자가 입력한 값

    // 기존 member 객체 복사
    const newMember = { ...member };

    // 해당 속성 값 변경
    newMember[name] = value;

    // 상태 업데이트
    setMember(newMember);
  };

  // ✅ 회원정보 수정
  const handleSubmit = () => {
    if (!member.pw) {
      alert('비밀번호를 입력하세요');
      return;
    }

    axios
      .put('/api/member/modify', member, {
        withCredentials: true,
      })
      .then((res) => {
        if (res.data === 1) {
          alert('회원정보 수정 성공!');
          navigate('/member/myinfo');
        } else {
          alert('비밀번호가 일치하지 않습니다.');
        }
      })
      .catch((err) => console.error(err));
  };

  return (
    <section>
      <div id="section_wrap">
        <div className="word">
          <h2>개인 회원 정보 수정</h2>
        </div>

        <div className="content">
          <table className="detail-table">
            <tbody>
              <tr>
                <th>아이디</th>
                <td>
                  <input type="text" name="id" value={member.id} readOnly />
                </td>
              </tr>

              <tr>
                <th>이메일</th>
                <td>
                  <input
                    type="text"
                    name="mail"
                    value={member.mail}
                    onChange={handleChange}
                  />
                </td>
              </tr>

              <tr>
                <th>전화</th>
                <td>
                  <input
                    type="text"
                    name="phone"
                    value={member.phone}
                    onChange={handleChange}
                  />
                </td>
              </tr>

              <tr>
                <th>패스워드 확인</th>
                <td>
                  <input
                    type="password"
                    name="pw"
                    value={member.pw}
                    onChange={handleChange}
                    required
                  />
                </td>
              </tr>
            </tbody>
          </table>

          <div className="btn-area" style={{ marginTop: '20px' }}>
            <button className="btn" onClick={handleSubmit}>
              회원 수정하기
            </button>

            <button className="btn" onClick={() => navigate('/member/myinfo')}>
              취소
            </button>
          </div>
        </div>
      </div>
    </section>
  );
}
