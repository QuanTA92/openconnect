import React, { useState, useEffect } from "react";
import { Footer, Header } from "../components";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { addCart, delCart } from "../redux/action";

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);
  const dispatch = useDispatch();
  const [cartIsEmpty, setCartIsEmpty] = useState(false);

  const fetchCart = async () => {
    try {
      const idUser = getUserId(); // Lấy idUser từ cookie hoặc trạng thái đăng nhập
      if (!idUser) {
        console.error("User ID not found.");
        return;
      }

      const response = await fetch(`http://localhost:8080/cart/${idUser}`);
      if (response.ok) {
        const data = await response.json();
        setCartItems(data.data);
      } else {
        console.error("Failed to fetch cart items");
      }
    } catch (error) {
      console.error("Error fetching cart items:", error);
    }
  };

  useEffect(() => {
    fetchCart();
  }, []);

  const addItem = (item) => {
    const requestData = {
      quantityProduct: 1, // Số lượng sản phẩm không thay đổi
      quantityTicket: 1, // Tăng quantityTicket lên 1 khi thêm
      idUser: item.idUser, // Sử dụng idUser từ mục item
      idProduct: item.idProduct, // Sử dụng idProduct từ mục item
      idTicket: item.idTicket, // Sử dụng idTicket từ mục item
    };

    // Gửi yêu cầu HTTP POST đến đường dẫn http://localhost:8080/cart
    fetch("http://localhost:8080/cart", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(requestData),
    })
      .then((response) => {
        // Xử lý phản hồi từ server
        if (response.ok) {
          // Nếu thành công, thực hiện cập nhật số lượng
          const updatedItems = cartItems.map((cartItem) => {
            if (cartItem.idCart === item.idCart) {
              return {
                ...cartItem,
                quantityTicket: cartItem.quantityTicket + 1, // Tăng số lượng vé
              };
            }
            return cartItem;
          });
          setCartItems(updatedItems); // Cập nhật lại danh sách giỏ hàng
          dispatch(addCart(item));
        } else {
          // Nếu không thành công, xử lý lỗi ở đây
          console.error("Failed to add item to cart");
        }
      })
      .catch((error) => {
        // Xử lý lỗi nếu có
        console.error("Error adding item to cart:", error);
      });
  };

  const removeItem = (item) => {
    const requestData = {
      quantityProduct: 1, // Số lượng sản phẩm không thay đổi
      quantityTicket: -1, // Giảm quantityTicket đi 1 khi xóa
      idUser: item.idUser, // Sử dụng idUser từ mục item
      idProduct: item.idProduct, // Sử dụng idProduct từ mục item
      idTicket: item.idTicket, // Sử dụng idTicket từ mục item
    };

    // Gửi yêu cầu HTTP POST đến đường dẫn http://localhost:8080/cart
    fetch("http://localhost:8080/cart", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(requestData),
    })
      .then((response) => {
        // Xử lý phản hồi từ server
        if (response.ok) {
          // Nếu thành công, thực hiện cập nhật số lượng
          const updatedItems = cartItems
            .map((cartItem) => {
              if (cartItem.idCart === item.idCart) {
                const newQuantityTicket = cartItem.quantityTicket - 1;
                if (newQuantityTicket <= 0) {
                  // Nếu số lượng vé giảm xuống 0 hoặc dưới 0, xóa mục khỏi giỏ hàng
                  deleteItem(cartItem);
                  return null; // Trả về null để loại bỏ mục này khỏi danh sách
                } else {
                  return {
                    ...cartItem,
                    quantityTicket: newQuantityTicket, // Giảm số lượng vé
                  };
                }
              }
              return cartItem;
            })
            .filter(Boolean); // Loại bỏ các mục null
          setCartItems(updatedItems); // Cập nhật lại danh sách giỏ hàng
          dispatch(delCart(item));
        } else {
          // Nếu không thành công, xử lý lỗi ở đây
          console.error("Failed to remove item from cart");
        }
      })
      .catch((error) => {
        // Xử lý lỗi nếu có
        console.error("Error removing item from cart:", error);
      });
  };

  const deleteItem = async (item) => {
    try {
      const response = await fetch(
        `http://localhost:8080/cart/${item.idCart}`,
        {
          method: "DELETE",
        }
      );
      if (response.ok) {
        // Xóa thành công, cập nhật lại danh sách giỏ hàng
        const updatedItems = cartItems.filter(
          (cartItem) => cartItem.idCart !== item.idCart
        );
        setCartItems(updatedItems);

        // Nếu không còn mục nào trong giỏ hàng, hiển thị thông báo "Your Cart is Empty"
        if (updatedItems.length === 0) {
          setCartIsEmpty(true);
        }

        // Hiển thị thông báo thành công
        // alert("Item removed successfully!");
      } else {
        // Xóa thất bại, hiển thị thông báo lỗi
        const data = await response.json();
        alert("Failed to remove item: " + data.message);
      }
    } catch (error) {
      console.error("Error deleting item:", error);
      alert("Failed to remove item. Please try again later.");
    }
  };

  // Lấy idUser từ cookie hoặc trạng thái đăng nhập
  const getUserId = () => {
    const cookies = document.cookie.split(";").map((cookie) => cookie.trim());
    const userIdCookie = cookies.find((cookie) => cookie.startsWith("userId="));
    if (userIdCookie) {
      return userIdCookie.split("=")[1];
    } else {
      return null;
    }
  };

  // Đường dẫn checkout
  const checkoutUrl = `http://localhost:8080/checkout/${getUserId()}?returnUrl=http://localhost:8080`;

  return (
    <>
      <Header />
      <div className="container my-3 py-3">
        <h1 className="text-center">Cart</h1>
        <hr />
        {cartItems.length > 0 ? (
          <section className="h-100 gradient-custom">
            <div className="container py-5">
              <div className="row d-flex justify-content-center my-4">
                <div className="col-md-8">
                  <div className="card mb-4">
                    <div className="card-header py-3">
                      <h5 className="mb-0">Item List</h5>
                    </div>
                    <div className="card-body">
                      {cartItems.map((item, index) => (
                        <div key={item.id}>
                          <div className="row d-flex align-items-center">
                            <div className="col-lg-3 col-md-12">
                              <div className="bg-image rounded">
                                <img
                                  src={`http://localhost:8080/images/${item.imageWorkshop
                                    .split("\\")
                                    .pop()}`}
                                  alt={item.nameWorkshop}
                                  width={100}
                                  height={75}
                                />
                              </div>
                              <br />
                              <button
                                className="btn btn-danger"
                                onClick={() => deleteItem(item)}
                                style={{
                                  backgroundColor: "white",
                                  color: "black",
                                  border: "1px solid black",
                                }}
                              >
                                <FontAwesomeIcon icon={faTrash} /> Remove
                              </button>
                            </div>

                            <div className="col-lg-5 col-md-6">
                              <p>
                                <strong>{item.nameWorkshop}</strong>
                              </p>
                            </div>
                            <div className="col-lg-4 col-md-6">
                              <div
                                className="d-flex mb-4 align-items-center"
                                style={{ maxWidth: "300px" }}
                              >
                                <button
                                  className="btn px-3"
                                  onClick={() => removeItem(item)}
                                >
                                  <i className="fas fa-minus"></i>
                                </button>

                                <p className="mx-3">{item.quantityTicket}</p>
                                <button
                                  className="btn px-3"
                                  onClick={() => addItem(item)}
                                >
                                  <i className="fas fa-plus"></i>
                                </button>
                              </div>

                              <p className="text-start text-md-center">
                                <strong>
                                  <span className="text-muted">
                                    {item.quantityTicket}
                                  </span>{" "}
                                  x {item.priceTicket} VND
                                </strong>
                              </p>
                            </div>
                          </div>
                          <hr className="my-4" />
                        </div>
                      ))}
                    </div>
                  </div>
                </div>
                <div className="col-md-4">
                  <div className="card mb-4">
                    <div className="card-header py-3 bg-light">
                      <h5 className="mb-0">Order Summary</h5>
                    </div>
                    <div className="card-body">
                      <ul className="list-group list-group-flush">
                        <li className="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
                          Products ({cartItems.length}) <br />
                        </li>

                        <li className="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
                          <div>
                            <strong>Total amount</strong>
                          </div>
                          <span>
                            <strong>
                              {" "}
                              {Math.round(
                                cartItems.reduce((acc, cur) => {
                                  if (!isNaN(cur.totalPrice)) {
                                    return acc + cur.totalPrice;
                                  } else {
                                    return acc;
                                  }
                                }, 0)
                              )}{" "}
                              VND
                            </strong>
                          </span>
                        </li>
                      </ul>
                      <Link
                        to={checkoutUrl}
                        className="btn btn-dark btn-lg btn-block"
                      >
                        Go to checkout
                      </Link>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </section>
        ) : (
          <div className="container">
            <div className="row">
              <div className="col-md-12 py-5 bg-light text-center">
                <h4 className="p-3 display-5">Your Cart is Empty</h4>
                <Link to="/" className="btn btn-outline-dark mx-4">
                  <i className="fa fa-arrow-left"></i> Continue Shopping
                </Link>
              </div>
            </div>
          </div>
        )}
      </div>
      <Footer />
    </>
  );
};

export default Cart;
