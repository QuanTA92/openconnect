import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Footer, Header } from "../components";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    // Thực hiện gọi API đăng nhập
    try {
      const formData = new FormData();
      formData.append("usernameOrEmailOrPhone", email);
      formData.append("password", password);

      const response = await fetch("http://localhost:8080/user/login", {
        method: "POST",
        body: formData,
      });
      const data = await response.json();
      if (response.ok) {
        // Đăng nhập thành công, chuyển hướng sang trang home
        navigate("/");
        // Lưu thông tin người dùng vào cookie
        document.cookie = `authenticated=true; Max-Age=${24 * 60 * 60}; Path=/`;
        document.cookie = `username=${email}; Max-Age=${24 * 60 * 60}; Path=/`;
        // Lưu ID của người dùng vào cookie
        document.cookie = `userId=${data.data.userId}; Max-Age=${
          24 * 60 * 60
        }; Path=/`;
      } else {
        // Đăng nhập không thành công, xử lý thông báo lỗi tại đây
        console.error("Đăng nhập không thành công:", data.error);
      }
    } catch (error) {
      console.error("Lỗi khi thực hiện đăng nhập:", error);
    }
  };

  return (
    <>
      <Header />
      <div
        className="container my-3 py-3"
        style={{ backgroundColor: "#f0f9ff" }}
      >
        <h1 className="text-center">Đăng nhập</h1>
        <hr />
        <div className="row my-4 h-100">
          <div className="col-md-4 col-lg-4 col-sm-8 mx-auto">
            <form onSubmit={handleLogin}>
              <div className="my-3">
                <label htmlFor="email">Username, Email or Phone</label>
                <input
                  type="text"
                  className="form-control"
                  id="email"
                  placeholder="Nhập email hoặc số điện thoại"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$|^\d{10}$"
                  title="Vui lòng nhập đúng định dạng email hoặc số điện thoại"
                  required
                />
              </div>
              <div className="my-3">
                <label htmlFor="password">Mật khẩu</label>
                <input
                  type="password"
                  className="form-control"
                  id="password"
                  placeholder="Password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
              </div>
              <div className="my-3">
                <p>
                  Bạn chưa có tài khoản ?{" "}
                  <Link
                    to="/register"
                    className="text-decoration-underline text-info"
                  >
                    Đăng ký
                  </Link>{" "}
                </p>
              </div>
              <div className="text-center">
                <button className="my-2 mx-auto btn btn-primary" type="submit">
                  Đăng nhập
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
};

export default Login;
