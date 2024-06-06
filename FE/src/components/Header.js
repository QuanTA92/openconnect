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

        // Cập nhật trạng thái người dùng và hiển thị thông báo
        setUser(null);
        // alert("Logged out successfully.");
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
          <div className="col-xl-6 col-lg-7">
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
                  <Link to="/shop" className="nav-link">
                    History
                  </Link>
                </li>
                <li className="dropdown">
                  <Link
                    to="#"
                    className="nav-link dropdown-toggle"
                    id="pagesDropdown"
                    role="button"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    Pages
                  </Link>
                  <ul className="dropdown-menu" aria-labelledby="pagesDropdown">
                    <li>
                      <Link to="/product-details" className="dropdown-item">
                        Workshop Details
                      </Link>
                    </li>
                    <li>
                      <Link to="/Cart" className="dropdown-item">
                        Booking Tickets
                      </Link>
                    </li>
                    <li>
                      <Link to="/Checkout" className="dropdown-item">
                        Checkout
                      </Link>
                    </li>
                  </ul>
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
          <div className="col-lg-3">
            <div className="header__right">
              {user ? (
                <ul className="header__right__widget">
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
                        {/* Gọi hàm handleLogout khi người dùng nhấp vào "Logout" */}
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
                <div className="header__right__auth">
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
