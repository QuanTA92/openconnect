import React from 'react'
import { Footer, Header } from "../components";
const AboutPage = () => {
  return (
    <>
      <Header />
      <div className="container my-3 py-3">
        <div className="row">
          <div className="col-md-6">
            <h1>Về chúng tôi</h1>
            <hr />
            <p className="lead ">
              Chào mừng bạn đến với hệ thống đặt chỗ workshop của chúng tôi! Chúng tôi cung cấp một nền tảng thuận tiện để các cá nhân và tổ chức có thể tìm kiếm, đặt và quản lý các buổi workshop một cách hiệu quả. Với sự đa dạng về chủ đề và địa điểm, hệ thống của chúng tôi kết nối người học với những chuyên gia hàng đầu và những khóa học bổ ích.
            </p>

            <h1>Thông tin liên hệ</h1>
            <div className="d-flex align-items-center mb-3">
            <img src="https://img.icons8.com/?size=24&id=118497&format=png&color=000000" alt="Icon" className="me-2" />
              <a href="https://www.facebook.com/openconnectfpt" className="me-3 d-flex align-items-center">
                <span>/openconnectfpt</span>
              </a>
            </div>
            <div className="d-flex align-items-center">
              <img src="https://img.icons8.com/?size=24&id=X0mEIh0RyDdL&format=png&color=000000" alt="Icon" className="me-2" />
              <a href="mailto:openconnectfpt@gmail.com" className="d-flex align-items-center">
                <span className="me-2">openconnectfpt@gmail.com</span>
              </a>
            </div>


          </div>
          <div className="col-md-6">
            <div className="embed-responsive embed-responsive-16by9">
              <iframe
                title="Google Maps"
                className="embed-responsive-item"
                src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d3835.8561678280007!2d108.2583164!3d15.9688859!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3142116949840599%3A0x365b35580f52e8d5!2sFPT%20University%20Danang!5e0!3m2!1sen!2s!4v1717466435944!5m2!1sen!2s"
                allowfullscreen
              ></iframe>
            </div>
          </div>
        </div>





        <div className="container">
          <h1 className="text-center py-4">Workshops</h1>
          <div className="row justify-content-center">
            <div className="col-md-2 col-sm-6 mb-3">
              <div className="card h-100">
                <img className="card-img-top img-fluid" src="/img/categories/1.jpg" alt="" height={240} />
                <div className="card-body">
                  <h5 className="card-title text-center">Làm nến</h5>
                </div>
              </div>
            </div>
            <div className="col-md-2 col-sm-6 mb-3">
              <div className="card h-100">
                <img className="card-img-top img-fluid" src="/img/categories/2.jpg" alt="" height={240} />
                <div className="card-body">
                  <h5 className="card-title text-center">Cắm hoa</h5>
                </div>
              </div>
            </div>
            <div className="col-md-2 col-sm-6 mb-3">
              <div className="card h-100">
                <img className="card-img-top img-fluid" src="/img/categories/3.jpg" alt="" height={240} />
                <div className="card-body">
                  <h5 className="card-title text-center">Làm bánh</h5>
                </div>
              </div>
            </div>
            <div className="col-md-2 col-sm-6 mb-3">
              <div className="card h-100">
                <img className="card-img-top img-fluid" src="/img/categories/4.jpg" alt="" height={240} />
                <div className="card-body">
                  <h5 className="card-title text-center">Vẽ tranh</h5>
                </div>
              </div>
            </div>
            <div className="col-md-2 col-sm-6 mb-3">
              <div className="card h-100">
                <img className="card-img-top img-fluid" src="/img/categories/5.jpg" alt="" height={240} />
                <div className="card-body">
                  <h5 className="card-title text-center">Làm gốm</h5>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div >
      <Footer />
    </>
  )
}

export default AboutPage