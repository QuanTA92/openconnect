import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Footer, Header } from "../components";
import Swal from "sweetalert2";

const Register = () => {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const formData = new FormData();
      formData.append("username", username);
      formData.append("email", email);
      formData.append("phone", phone);
      formData.append("password", password);

      const response = await fetch("http://localhost:8080/user/signup", {
        method: "POST",
        body: formData,
      });
      const data = await response.json();
      if (response.ok) {
        // Hiển thị thông báo alert khi đăng ký thành công
        Swal.fire({
          icon: "success",
          title: "Đăng ký thành công",
          text: "Vui lòng đăng nhập!",
          confirmButtonText: "OK",
        });
        // Chuyển hướng sang trang đăng nhập sau khi đăng ký thành công
        navigate("/login");
      } else {
        console.error("Đăng ký không thành công:", data.error);
      }
    } catch (error) {
      console.error("Lỗi khi thực hiện đăng ký:", error);
    }
  };

  return (
    <>
      <Header />
      <div
        className="container my-3 py-3"
        style={{ backgroundColor: "#f0f9ff" }}
      >
        <h1 className="text-center">Đăng ký</h1>
        <hr />
        <div className="row my-4 h-100">
          <div className="col-md-4 col-lg-4 col-sm-8 mx-auto">
            <form onSubmit={handleSubmit}>
              <div className="form my-3">
                <label htmlFor="username">Username</label>
                <input
                  type="text"
                  className="form-control"
                  id="username"
                  placeholder="Enter Username"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                />
              </div>
              <div className="form my-3">
                <label htmlFor="email">Email</label>
                <input
                  type="email"
                  className="form-control"
                  id="email"
                  placeholder="name@example.com"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                />
              </div>
              <div className="form my-3">
                <label htmlFor="phone">Số Điện thoại</label>
                <input
                  type="text"
                  className="form-control"
                  id="phone"
                  placeholder="Phone number"
                  value={phone}
                  onChange={(e) => setPhone(e.target.value)}
                />
              </div>
              <div className="form my-3">
                <label htmlFor="password">Mật khẩu</label>
                <input
                  type="password"
                  className="form-control"
                  id="password"
                  placeholder="Password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                />
              </div>
              <div className="my-3">
                <p>
                  Bạn đã có tài khoản ?{" "}
                  <Link
                    to="/login"
                    className="text-decoration-underline text-info"
                  >
                    Login
                  </Link>{" "}
                </p>
              </div>
              <div className="text-center">
                <button className="my-2 mx-auto btn btn-primary" type="submit">
                  Đăng ký
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

export default Register;
