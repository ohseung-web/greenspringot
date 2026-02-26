import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './Member.css';

export default function Procudt() {
  // 모든 값을 하나의 객체로 관리
  const [car, setCar] = useState({
    carName: '',
    price: '',
    company: '',
    info: '',
    img: null,
  });

  const navigate = useNavigate();

  const getBoardList = () => {
    axios.get('/api/board/list', {
      params: {
        searchType: searchType,
        searchKeyword: searchKeyword,
        page: page,
        pageSize: 5,
      },
    });
  };

  // 수정했어

  // 상품등록 전송 메소드
  const handleSubmit = () => {
    const formData = new FormData();

    // 자바의 확장 for문과 비슷
    // 자바스크립스 for ~ in 구문
    // 객체(Object)의 key를 하나씩 꺼내는 구조
    for (let key in car) {
      if (key === 'img') {
        // 서버의 DTO 내부 'img' 필드와 충돌하지 않도록 이름을 바꿉니다.
        // 서버 컨트롤러의 @RequestParam 이름과 맞춰준다.
        formData.append('uploadFile', car[key]);
      } else if (key === 'price') {
        formData.append(key, Number(car[key]));
      } else {
        formData.append(key, car[key]);
      }
    }

    axios
      .post('/api/cars/insert', formData)
      .then((res) => {
        if (res.data === 1) {
          alert('상품등록 성공');
          navigate('/');
        }
      })
      .catch((error) => {
        console.log(error);
        alert('등록 실패');
      });
  };

  // 객체를 반복문으로 자동 추가
  // car이 배열이 아니라 Object이기때문에
  // Object.keys(car) 배열에 저장한다.
  // ["carName", "price", "company", "info", "img"]
  // const keys = Object.keys(car);

  // for (let i = 0; i < keys.length; i++) {
  //   const key = keys[i];
  //   const value = car[key];

  //   formData.append(key, value);
  // }

  // 공통 입력 처리 함수
  const handleChange = (e) => {
    // input 태그의 name 값을 가져온다
    const inputName = e.target.name;

    // 파일 input인지 확인한다
    if (e.target.type === 'file') {
      // 파일이면 첫 번째 파일을 저장
      setCar({ ...car, [inputName]: e.target.files[0] });
    } else {
      // 일반 텍스트, 숫자 input이면 value 저장
      setCar({ ...car, [inputName]: e.target.value });
    }
  };

  return (
    <div id="section_wrap">
      <div className="word">상품등록</div>
      <table width="500" border="1">
        <tbody>
          <tr>
            <td>자동차이름</td>
            <td>
              <input type="text" name="carName" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>자동차가격</td>
            <td>
              <input type="number" name="price" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>제조사</td>
            <td>
              <input type="text" name="company" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>자동차 이미지</td>
            <td>
              <input type="file" name="img" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>자동차 정보</td>
            <td>
              <input type="text" name="info" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td colSpan="2" align="center">
              <button type="button" onClick={handleSubmit}>
                상품등록
              </button>
              <button type="reset">취소</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
}
