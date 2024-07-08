import React, { useState, useEffect } from "react";
import { Footer, Header } from "../components";

export default function History() {
  const [data, setData] = useState([]);

  useEffect(() => {
    // Lấy userId từ cookie
    const userId = parseInt(
      document.cookie
        .split("; ")
        .find((row) => row.startsWith("userId"))
        .split("=")[1]
    );

    // Gọi API để lấy dữ liệu đơn hàng của userId
    fetch(`http://localhost:8080/orders/${userId}`)
      .then((response) => response.json())
      .then((result) => {
        if (result.statusCode === 200) {
          setData(result.data); // Cập nhật dữ liệu từ API vào state
        } else {
          console.error("Lỗi khi lấy dữ liệu đơn hàng:", result.error);
        }
      })
      .catch((error) => {
        console.error("Lỗi khi gọi API:", error);
      });
  }, []);

  const getStatusBadgeClass = (status) => {
    switch (status) {
      case "Thành công":
        return "badge badge-success";
      case "Đang xử lý":
        return "badge badge-warning";
      case "Đã hủy":
        return "badge badge-danger";
      default:
        return "badge";
    }
  };

  return (
    <>
      <Header />
      <section className="ftco-section">
        <div className="container">
          <div className="row justify-content-center">
            <div className="col-md-6 text-center mb-4 mt-4">
              <h2 className="fw-bold">Vé của tôi</h2>
            </div>
          </div>
          <div className="row">
            <div className="col-md-12">
              <div className="table-responsive">
                {data.length === 0 ? (
                  <p className="text-center">Bạn chưa mua vé</p>
                ) : (
                  <table className="table table-bordered table-striped text-center">
                    <thead className="thead-light">
                      <tr>
                        <th className="fw-bold">STT</th>
                        <th className="fw-bold">Workshop</th>
                        <th className="fw-bold">Giá</th>
                        <th className="fw-bold">Số lượng</th>
                        <th className="fw-bold">Tổng tiền</th>
                        <th className="fw-bold">Trạng thái</th>
                        <th className="fw-bold">Ngày mua</th>
                      </tr>
                    </thead>
                    <tbody>
                      {data.map((order, index) => (
                        <React.Fragment key={index}>
                          {order.orderDetailsTicket.map((ticket, idx) => (
                            <tr
                              className="alert"
                              role="alert"
                              key={`${index}-${idx}`}
                            >
                              {idx === 0 && (
                                <td rowSpan={order.orderDetailsTicket.length}>
                                  {index + 1}
                                </td>
                              )}
                              <td>{ticket.nameWorkshop}</td>
                              <td>{ticket.priceTicket.toFixed(2)} VND</td>
                              <td>{ticket.quantityTicket}</td>
                              {idx === 0 && (
                                <>
                                  <td rowSpan={order.orderDetailsTicket.length}>
                                    {order.priceTotal.toFixed(2)} VND
                                  </td>
                                  <td rowSpan={order.orderDetailsTicket.length}>
                                    <span
                                      className={getStatusBadgeClass(
                                        order.nameStatus
                                      )}
                                    >
                                      {order.nameStatus}
                                    </span>
                                  </td>
                                  <td rowSpan={order.orderDetailsTicket.length}>
                                    {order.createDate}
                                  </td>
                                </>
                              )}
                            </tr>
                          ))}
                        </React.Fragment>
                      ))}
                    </tbody>
                  </table>
                )}
              </div>
            </div>
          </div>
        </div>
      </section>
      <Footer />
    </>
  );
}
