import { useState, useEffect } from 'react';
import reactLogo from './assets/react.svg';
import viteLogo from '/vite.svg';
import './App.css';
import axio from 'axios';
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './pages/Home';
import Signup from './pages/Signup';
import Login from './pages/Login';
import SignupResult from './pages/SignupResult';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import AuthProvider from './contexts/AuthContext';
import MyInfo from './pages/MyInfo';
import Modify from './pages/Modify';
import Product from './pages/Product';

function App() {
  return (
    <AuthProvider>
      {/* 2. 전체를 감싸서 전역 저장소 활성화 */}
      <BrowserRouter>
        <div className="App">
          <Header />
          <nav id="nav_wrap">
            <a href="/example">EXAMPLE</a>
          </nav>

          <main>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/member/signup" element={<Signup />} />
              <Route path="/member/login" element={<Login />} />
              <Route path="/member/signup_result" element={<SignupResult />} />
              <Route path="member/myinfo" element={<MyInfo />} />
              <Route path="member/modify" element={<Modify />} />
              <Route path="/cars/insert" element={<Product />} />
            </Routes>
          </main>

          <Footer />
        </div>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
