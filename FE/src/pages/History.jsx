import * as React from 'react';
import { Footer, Header } from "../components";

export default function History() {
  const data = [
    { workshop: 'Sneakers Shoes 2020 For Men', price: 44.99, quantity: 2, total: 89.98, status: 'Thành công' },
    { workshop: 'Sneakers Shoes 2020 For Men', price: 30.99, quantity: 1, total: 30.99, status: 'Đang xử lý' },
    { workshop: 'Sneakers Shoes 2020 For Men', price: 35.50, quantity: 1, total: 35.50, status: 'Đã hủy' },
    { workshop: 'Sneakers Shoes 2020 For Men', price: 76.99, quantity: 1, total: 76.99, status: 'Thành công' },
    { workshop: 'Sneakers Shoes 2020 For Men', price: 40.00, quantity: 1, total: 40.00, status: 'Đang xử lý' },
  ];

  const getStatusBadgeClass = (status) => {
    switch (status) {
      case 'Thành công':
        return 'badge badge-success';
      case 'Đang xử lý':
        return 'badge badge-warning';
      case 'Đã hủy':
        return 'badge badge-danger';
      default:
        return '';
    }
  };

  return (
    <>
      <Header />
      <section class="ftco-section">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-md-6 text-center mb-4 mt-4">
              <h2 class="heading-section">Vé của tôi</h2>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <div class="table-wrap">
                <table class="table">
                  <thead class="thead-light ">
                    <tr>
                      <th>STT</th>
                      <th>Workshop</th>
                      <th>Giá</th>
                      <th>Số lượng</th>
                      <th>Tổng tiền</th>
                      <th>Trạng thái</th>
                    </tr>
                  </thead>
                  <tbody>
                    {data.map((item, index) => (
                      <tr class="alert" role="alert" key={index}>
                        <td>{index + 1}</td>
                        <td>
                          <div class="workshop">
                            <span>{item.workshop}</span>
                            
                          </div>
                        </td>
                        <td>${item.price.toFixed(2)}</td>
                        <td class="quantity">{item.quantity}</td>
                        <td>${item.total.toFixed(2)}</td>
                        <td>
                          <span class={getStatusBadgeClass(item.status)}>{item.status}</span>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </section>
      <Footer />
    </>
  );
}
