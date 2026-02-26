import { Link } from 'react-router-dom';
import { useContext } from 'react'; // 1. useContext 임포트
import { AuthContext } from '../contexts/AuthContext'; // 2. Context 임포트
import './Header.css';

export default function Header() {
  // 3. 전역 저장소에서 user와 logout 함수를 직접 가져옴
  const { user, logout } = useContext(AuthContext);

  return (
    <header>
      <div id="top">
        <h3>MEMBER JOIN</h3>
      </div>
      <div id="header_wrap">
        <Link to="/">HOME</Link>

        {!user ? (
          <>
            <Link to="/member/signup">회원가입</Link>
            <Link to="/member/login">로그인</Link>
          </>
        ) : (
          <>
            <span style={{ fontWeight: 'bold', color: '#2c3e50' }}>
              {user.id === 'admin1234' ? (
                <span style={{ color: 'blue' }}>관리자</span>
              ) : (
                <>{user.id}님 환영합니다!</>
              )}
            </span>
            {/* 4. 가져온 logout 함수 연결 */}
            <Link to="/" onClick={logout}>
              로그아웃
            </Link>

            {user.id === 'admin1234' && (
              <>
                <Link
                  to="/member/list"
                  style={{ color: 'blue', fontWeight: 'bold' }}
                >
                  [회원목록]
                </Link>
                <Link to="/cars/insert">[상품등록]</Link>
              </>
            )}
            <Link to={`/member/myinfo?id=${user.id}`}>내정보</Link>
          </>
        )}
        <Link to="/board/list">게시판</Link>
      </div>
    </header>
  );
}
