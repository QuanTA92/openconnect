import React, { useState, useEffect } from "react";
import { Footer, Header } from "../components";
import { Link } from "react-router-dom";

const MyProfile = () => {
  const [userData, setUserData] = useState({
    userName: "",
    address: "",
    email: "",
    phone: "",
    imageUser: "",
  });

  useEffect(() => {
    // Lấy userId từ cookie
    const userId = parseInt(
      document.cookie
        .split("; ")
        .find((row) => row.startsWith("userId"))
        .split("=")[1]
    );

    // Gọi API để lấy dữ liệu người dùng của userId
    fetch(`http://localhost:8080/user/${userId}`)
      .then((response) => response.json())
      .then((result) => {
        if (result.statusCode === 200) {
          setUserData(result.data[0]); // Cập nhật dữ liệu người dùng từ API vào state
        } else {
          console.error("Lỗi khi lấy dữ liệu người dùng:", result.error);
        }
      })
      .catch((error) => {
        console.error("Lỗi khi gọi API:", error);
      });
  }, []);

  // Function to get the image URL from backend
  const getImageUrl = (filename) => {
    return `http://localhost:8080/images/${filename.split("\\").pop()}`;
  };

  return (
    <>
      <Header />
      <div
        className="container my-3 py-3"
        style={{ backgroundColor: "#f0f9ff" }}
      >
        <h2 className="text-center mb-4">My Profile</h2>
        <div className="row gutters-sm">
          <div className="col-md-4 mb-3">
            <div className="card">
              <div className="card-body">
                <div className="d-flex flex-column align-items-center text-center">
                  <img
                    src={getImageUrl(userData.imageUser)}
                    alt="User Avatar"
                    className="rounded-circle"
                    width="150"
                  />
                  <div className="mt-3">
                    <h4>{userData.userName}</h4>
                    <p className="text-muted font-size-sm">
                      {userData.address}
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="col-md-8">
            <div className="card mb-3">
              <div className="card-body">
                <div className="row">
                  <div className="col-sm-3">
                    <h6 className="mb-0">Full Name</h6>
                  </div>
                  <div className="col-sm-9 text-secondary">
                    {userData.userName}
                  </div>
                </div>
                <hr />
                <div className="row">
                  <div className="col-sm-3">
                    <h6 className="mb-0">Email</h6>
                  </div>
                  <div className="col-sm-9 text-secondary">
                    {userData.email}
                  </div>
                </div>
                <hr />
                <div className="row">
                  <div className="col-sm-3">
                    <h6 className="mb-0">Phone</h6>
                  </div>
                  <div className="col-sm-9 text-secondary">
                    {userData.phone}
                  </div>
                </div>
                <hr />
                <div className="row">
                  <div className="col-sm-3">
                    <h6 className="mb-0">Address</h6>
                  </div>
                  <div className="col-sm-9 text-secondary">
                    {userData.address}
                  </div>
                </div>
                <hr />
                <div className="row">
                  <div className="col-sm-12">
                    <Link className="btn btn-info" to="/updateprofile">
                      Edit
                    </Link>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
};

export default MyProfile;
