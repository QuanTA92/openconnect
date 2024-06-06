import React, { useEffect, useRef } from "react";
import { Link } from "react-router-dom";

export default function Categories() {
  const containerRef = useRef(null);

  useEffect(() => {
    if (containerRef.current) {
      const elements = containerRef.current.querySelectorAll(".set-bg");
      elements.forEach((element) => {
        const bg = element.getAttribute("data-setbg");
        if (bg) {
          element.style.backgroundImage = `url(${bg})`;
        }
      });
    }
  }, []);

  return (
    <section className="categories">
      <div className="container-fluid" ref={containerRef}>
        <div className="row">
          <div className="col-lg-6 p-0">
            <div
              className="categories__item categories__large__item set-bg"
              data-setbg="/img/categories/1.jpg"
            >
              <div className="categories__text">
                <h1>Làm Nến Thơm</h1>
                <p>
                  Một workshop làm nến thơm cung cấp cơ hội học hỏi và thực hành
                  tự làm nến thơm, khuyến khích sáng tạo và cá nhân hóa, giúp
                  thư giãn và kết nối xã hội, tiết kiệm chi phí và bảo vệ môi
                  trường, cùng với tiềm năng kinh doanh nhỏ..
                </p>
                <Link to="#" className="nav-link">Book now</Link>
              </div>
            </div>
          </div>
          <div className="col-lg-6">
            <div className="row">
              <div className="col-lg-6 col-md-6 col-sm-6 p-0">
                <div
                  className="categories__item set-bg"
                  data-setbg="/img/categories/2.jpg"
                >
                  <div className="categories__text">
                    <h4>Cắm Hoa</h4>
                    <p>358 items</p>
                    <Link to="#" className="nav-link">Book now</Link>

                  </div>
                </div>
              </div>
              <div className="col-lg-6 col-md-6 col-sm-6 p-0">
                <div
                  className="categories__item set-bg"
                  data-setbg="/img/categories/3.jpg"
                >
                  <div className="categories__text">
                    <h4>Làm Bánh</h4>
                    <p>273 items</p>
                    <Link to="#" className="nav-link">Book now</Link>
                  </div>
                </div>
              </div>
              <div className="col-lg-6 col-md-6 col-sm-6 p-0">
                <div
                  className="categories__item set-bg"
                  data-setbg="/img/categories/4.jpg"
                >
                  <div className="categories__text">
                    <h4>Vẽ Tranh</h4>
                    <p>159 items</p>
                    <Link to="#" className="nav-link">Book now</Link>
                  </div>
                </div>
              </div>
              <div className="col-lg-6 col-md-6 col-sm-6 p-0">
                <div
                  className="categories__item set-bg"
                  data-setbg="/img/categories/5.jpg"
                >
                  <div className="categories__text">
                    <h4>Làm Gốm</h4>
                    <p>792 items</p>
                    <Link to="#" className="nav-link">Book now</Link>

                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
