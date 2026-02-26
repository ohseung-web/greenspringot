import { createContext, useState, useEffect } from 'react';

// 1️ Context 생성
// - createContext()는 React의 전역 저장소(공유 공간)를 만드는 함수입니다.
// - 컴포넌트 트리 전체에 데이터를 전달할 수 있게 해줍니다.
export const AuthContext = createContext();

// 2️ Provider 정의
// - AuthProvider 컴포넌트는 Context의 '공급자(Provider)' 역할을 합니다.
// - App 전체를 감싸서 user, login, logout 데이터를 전역으로 전달합니다.
export default function AuthProvider({ children }) {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const savedUser = sessionStorage.getItem('user');
    if (savedUser) setUser(JSON.parse(savedUser));
  }, []);

  const login = (userData) => {
    sessionStorage.setItem('user', JSON.stringify(userData));
    setUser(userData);
  };

  const logout = () => {
    sessionStorage.removeItem('user');
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}
