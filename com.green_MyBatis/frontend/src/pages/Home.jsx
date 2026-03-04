import { useEffect, useState } from 'react';
import axios from 'axios';
import './Home.css';

export default function Home() {
  const [carList, setCarList] = useState([]);

  // 주석입니다.
  // 주석입니다.
  useEffect(() => {
    // 백엔드의 @RestController API 호출
    // 8090 포트의 스프링부트 API 호출
    axios
      .get('/api/cars')
      .then((res) => {
        console.log('받아온 데이터:', res.data);
        setCarList(res.data); // 상태 업데이트
      })
      .catch((err) => {
        console.error('데이터 로딩 에러:', err);
      });
  }, []);

  return (
    <section>
      <div id="section_wrap">
        <div className="word">HOME</div>
        <div className="content">
          <div className="carList">
            {carList.length > 0 ? (
              carList.map((car) => (
                <div className="carItem" key={car.no}>
                  
                  {/* 핵심 수정 부분: 삼항 연산자를 이용해 경로 분기 처리 */}
                  <img 
                    src={
                      car.img && car.img.startsWith('data:') 
                        ? car.img                             // 1. 신규 데이터(Base64)는 그대로 사용
                        : `/img/car/${car.img}`               // 2. 기존 데이터(파일명)는 경로 추가
                    } 
                    alt={car.carName} 
                  />
                  
                  {/* 아래에 있던 <img src={car.img} ... /> 태그는 삭제했습니다. */}

                  <div className="carName">{car.carName}</div>
                  <div className="carPrice">
                    {Number(car.price).toLocaleString()}원
                  </div>
                </div>
              ))
            ) : (
              <p>등록된 차량이 없습니다.</p>
            )}
          </div>
        </div>
      </div>
    </section>
  );
}
