import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function Signup() {
  // 1️⃣ 입력값 저장 공간
  const [id, setId] = useState('');
  const [pw, setPw] = useState('');
  const [mail, setMail] = useState('');
  const [phone, setPhone] = useState('');

  const navigate = useNavigate();

  // 2️⃣ 회원가입 버튼 눌렀을 때 실행
  const signup = () => {
    // 간단한 유효성 검사
    if (id === '') {
      alert('아이디 입력하세요');
      return;
    }
    if (pw === '') {
      alert('비밀번호 입력하세요');
      return;
    }

    // 3️⃣ 서버로 데이터 전송
    // { "id": "green","pw": "1234","mail": "green@test.com","phone": "010-1111-2222"}
    // 위처럼 JSON형태로 스프링부트 'http://localhost:8090/api/member/signup'로 보낸다.
    axios
      .post('/api/member/signup', {
        id: id,
        pw: pw,
        mail: mail,
        phone: phone,
      })
      .then((res) => {
        // 🔥 결과에 따라 signup_result 페이지로 이동
        if (res.data === 1) {
          alert('회원가입 성공!');
          navigate('/member/signup_result?result=success');
        } else if (res.data === 0) {
          alert('아이디가 이미 존재합니다');
          navigate('/member/signup_result?result=duplicate');
        } else {
          alert('회원가입 실패');
          navigate('/member/signup_result?result=fail');
        }
      })
      .catch((error) => {
        console.log(error);
        navigate('/member/signup_result?result=error');
      });
  };

  return (
    <div id="section_wrap">
      <div className="word">회원가입</div>
      <table width="500" border="1">
        <tbody>
          <tr>
            <td>아이디</td>
            <td>
              <input
                type="text"
                name="id"
                onChange={(e) => setId(e.target.value)}
              />
            </td>
          </tr>
          <tr>
            <td>비밀번호</td>
            <td>
              <input
                type="password"
                name="pw"
                onChange={(e) => setPw(e.target.value)}
              />
            </td>
          </tr>
          <tr>
            <td>이메일</td>
            <td>
              <input
                type="email"
                name="mail"
                onChange={(e) => setMail(e.target.value)}
              />
            </td>
          </tr>
          <tr>
            <td>전화번호</td>
            <td>
              <input
                type="tel"
                name="phone"
                onChange={(e) => setPhone(e.target.value)}
              />
            </td>
          </tr>
          <tr>
            <td colSpan="2" align="center">
              <button onClick={signup}>회원가입</button>
              <button type="reset">취소</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    // <div>
    //   <h2>회원가입</h2>
    //   아이디 <input onChange={(e) => setId(e.target.value)} />
    //   <br />
    //   비밀번호 <input type="password" onChange={(e) => setPw(e.target.value)} />
    //   <br />
    //   이메일 <input onChange={(e) => setMail(e.target.value)} />
    //   <br />
    //   전화번호 <input onChange={(e) => setPhone(e.target.value)} />
    //   <br />
    //   <button onClick={signup}>회원가입</button>
    // </div>
  );
}

// import { useState } from 'react';
// import axios from 'axios';
// import { useNavigate } from 'react-router-dom';
// import './Member.css'; // 작성한 CSS 연결

// export default function Signup() {
//   const [formData, setFormData] = useState({
//     id: '',
//     pw: '',
//     mail: '',
//     phone: '',
//   });
//   const navigate = useNavigate();

//   const handleChange = (e) => {
//     setFormData({ ...formData, [e.target.name]: e.target.value });
//   };

//   const handleSignup = () => {
//     // 기존 member.js의 유효성 검사 로직
//     if (!formData.id) return alert('아이디를 입력하세요');
//     if (!formData.pw) return alert('비밀번호를 입력하세요');

//     axios
//       .post('/api/member/signup', formData)
//       .then((res) => {
//         // res.data가 1이면 성공, 0이면 중복, 나머지는 실패로 가정
//         if (res.data === 1 || res.data === 0) {
//           // 주소만 옮기는 게 아니라 state에 결과 데이터를 담아서 보냅니다.
//           navigate('/member/signup_result', { state: { result: res.data } });
//         } else {
//           alert('회원가입 실패');
//         }
//       })
//       .catch((err) => console.error(err));
//   };

//   return (
//     <div id="section_wrap">
//       <div className="word">회원가입</div>
//       <table width="500" border="1">
//         <tbody>
//           <tr>
//             <td>아이디</td>
//             <td>
//               <input type="text" name="id" onChange={handleChange} />
//             </td>
//           </tr>
//           <tr>
//             <td>비밀번호</td>
//             <td>
//               <input type="password" name="pw" onChange={handleChange} />
//             </td>
//           </tr>
//           <tr>
//             <td>이메일</td>
//             <td>
//               <input type="email" name="mail" onChange={handleChange} />
//             </td>
//           </tr>
//           <tr>
//             <td>전화번호</td>
//             <td>
//               <input type="tel" name="phone" onChange={handleChange} />
//             </td>
//           </tr>
//           <tr>
//             <td colSpan="2" align="center">
//               <button onClick={handleSignup}>회원가입</button>
//               <button type="reset">취소</button>
//             </td>
//           </tr>
//         </tbody>
//       </table>
//     </div>
//   );
// }
