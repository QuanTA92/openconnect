import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";

export default function Header() {
  const [user, setUser] = useState(null);

  useEffect(() => {
    // Kiểm tra cookie để xác định xem người dùng đã đăng nhập hay chưa
    const username = document.cookie.replace(
      /(?:(?:^|.*;\s*)username\s*=\s*([^;]*).*$)|^.*$/,
      "$1"
    );
    if (username) {
      setUser(username);
    }
  }, []);

  const handleLogout = async () => {
    try {
      const response = await fetch("http://localhost:8080/user/logout");
      if (response.ok) {
        // Xóa tất cả các cookie trong web
        document.cookie.split(";").forEach(function (c) {
          document.cookie = c
            .replace(/^ +/, "")
            .replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/");
        });

        // Cập nhật trạng thái người dùng và chuyển hướng về trang chính
        setUser(null);
        window.location.href = "/"; // Chuyển hướng về trang chính
      } else {
        console.error("Đăng xuất không thành công");
      }
    } catch (error) {
      console.error("Lỗi khi thực hiện đăng xuất:", error);
    }
  };

  return (
    <header className="header">
      <div className="container-fluid">
        <div className="row">
          <div className="col-xl-3 col-lg-2">
            <div className="header__logo">
              <img src="/img/open.png" alt="logo" />
            </div>
          </div>
          <div className="col-xl-6 col-lg-5">
            <nav className="header__menu">
              <ul>
                <li>
                  <Link to="/" className="nav-link">
                    Home
                  </Link>
                </li>
                <li>
                  <Link to="/product" className="nav-link">
                    Workshop
                  </Link>
                </li>
                <li>
                  <Link to="/About" className="nav-link">
                    About Us
                  </Link>
                </li>
                <li>
                  <Link to="/Contact" className="nav-link">
                    Contact
                  </Link>
                </li>
              </ul>
            </nav>
          </div>
          <div className="col-xl-3 col-lg-5">
            <div className="header__right d-flex justify-content-end align-items-center">
              <div className="search me-3" >
                <div className="input-group rounded" style={{ width: "440px" }}>
                  <input
                    type="search"
                    className="form-control rounded"
                    placeholder="Search"
                    aria-label="Search"
                    aria-describedby="search-addon"
                  />
                  <span className="input-group-text border-0" id="search-addon" style={{ height: "38px" }}>
                    <i className="fas fa-search"></i>
                  </span>
                </div>
              </div>

              {user ? (
                <ul className="header__right__widget d-flex align-items-center ">
                  <li>
                    <Link to="#" className="nav-link">
                      <span className="icon_heart_alt"></span>
                      <div className="tip">2</div>
                    </Link>
                  </li>
                  <li>
                    <Link to="/Cart" className="nav-link">
                      <span className="icon_bag_alt"></span>
                    </Link>
                  </li>
                  <li className="nav-item dropdown">
                    <a
                      className="nav-link dropdown-toggle d-flex align-items-center"
                      href="#"
                      id="navbarDropdownMenuLink"
                      role="button"
                      data-bs-toggle="dropdown"
                      aria-expanded="false"
                    >
                      <img
                        src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/img (31).webp"
                        className="rounded-circle"
                        height="22"
                        alt="Avatar"
                        loading="lazy"
                      />
                      <span className="ms-1">{user}</span>
                    </a>
                    <ul
                      className="dropdown-menu"
                      aria-labelledby="navbarDropdownMenuLink"
                    >
                      <li>
                        <Link className="dropdown-item" to="/myprofile">
                          My profile
                        </Link>
                      </li>
                      <li>
                        <Link className="dropdown-item" to="/myhistory">
                          History
                        </Link>
                      </li>
                      <li>
                        <button
                          className="dropdown-item"
                          onClick={handleLogout}
                        >
                          Logout
                        </button>
                      </li>
                    </ul>
                  </li>
                </ul>
              ) : (
                <div className="header__right__auth d-flex">
                  <Link to="/Login" className="btn btn-outline-light fs-5 fw-bold me-2">
                    Login
                  </Link>
                  <Link to="/Register" className="btn btn-outline-light fs-5 fw-bold">
                    Register
                  </Link>
                </div>
              )}
            </div>
          </div>
        </div>
        <div className="canvas__open">
          <i className="fa fa-bars"></i>
        </div>
      </div>
    </header>
  );
}
