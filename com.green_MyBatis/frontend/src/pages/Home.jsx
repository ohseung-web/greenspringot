import { useEffect, useState } from 'react';
import axios from 'axios';
import './Home.css';

export default function Home() {
  const [carList, setCarList] = useState([]);

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
                  {/* 스프링의 static/img/car 폴더에 있는 이미지 연결 */}
                  <img src={`/img/car/${car.img}`} alt={car.carName} />
                  <div className="carName">{car.carName}</div>
                  <div className="carPrice">
                    {/* 천단위 콤마 포맷팅 */}
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
