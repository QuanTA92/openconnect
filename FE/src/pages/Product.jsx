import React, { useEffect, useState } from "react";
import Skeleton from "react-loading-skeleton";
import { Link, useParams } from "react-router-dom";
import { useDispatch } from "react-redux";
import { addCart } from "../redux/action";
import { Footer, Header } from "../components";
import Swal from "sweetalert2";

const Product = () => {
  const { id } = useParams();
  const [product, setProduct] = useState({});
  const [tickets, setTickets] = useState([]);
  const [loading, setLoading] = useState(false);
  const dispatch = useDispatch();

  const addProduct = async (ticket) => {
    try {
      // Lấy thông tin người dùng từ cookie
      const cookies = document.cookie.split(";");
      let userId = null;

      cookies.forEach((cookie) => {
        const [name, value] = cookie.trim().split("=");
        if (name === "userId") {
          userId = value;
        }
      });

      if (!userId) {
        console.error("User ID is missing.");
        alert("User ID is missing. Please log in again.");
        return;
      }

      // Xây dựng request body
      const requestBody = {
        quantityProduct: 1,
        quantityTicket: 1,
        idUser: userId,
        idProduct: null, // Không có sản phẩm liên kết
        idTicket: ticket.idTicket, // ID của vé
      };

      // Gửi request POST đến đường dẫn http://localhost:8080/cart
      const response = await fetch("http://localhost:8080/cart", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(requestBody),
      });

      if (response.ok) {
        // Nếu thành công, thêm vé vào giỏ hàng Redux
        dispatch(addCart(ticket));
        // alert("Ticket added to cart successfully!");

        Swal.fire({
          icon: "success",
          title: "Thêm vé thành công",
          text: "Vui lòng thanh toán!",
          confirmButtonText: "OK",
        });
      } else {
        // Xử lý khi có lỗi
        alert("Failed to add ticket to cart.");
      }
    } catch (error) {
      console.error("Error:", error);
      alert("An error occurred while adding ticket to cart.");
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        // Fetch workshop data
        const workshopResponse = await fetch(
          `http://localhost:8080/workshop/${id}`
        );
        const workshopData = await workshopResponse.json();
        setProduct(workshopData.data[0]);

        // Fetch tickets for the workshop
        const ticketsResponse = await fetch(
          `http://localhost:8080/ticket/getAllTicketsByIdWorkshop/${id}`
        );
        const ticketsData = await ticketsResponse.json();
        setTickets(ticketsData.data);

        setLoading(false);
      } catch (error) {
        console.error("Error:", error);
        alert("An error occurred while fetching workshop data.");
        setLoading(false);
      }
    };

    fetchData();
  }, [id]);

  const Loading = () => (
    <div className="container my-5 py-2">
      <div className="row">
        <div className="col-md-6 py-3">
          <Skeleton height={400} width={400} />
        </div>
        <div className="col-md-6 py-5">
          <Skeleton height={30} width={250} />
          <Skeleton height={90} />
          <Skeleton height={40} width={70} />
          <Skeleton height={50} width={110} />
          <Skeleton height={120} />
          <Skeleton height={40} width={110} inline={true} />
          <Skeleton className="mx-3" height={40} width={110} />
        </div>
      </div>
    </div>
  );

  const ShowProduct = () => (
    <div className="container my-5 py-2">
      <div className="row">
        <div className="col-md-6 col-sm-12 py-3">
          <img
            className="img-fluid"
            src={
              product.imageWorkshop &&
              `http://localhost:8080/images/${product.imageWorkshop
                .split("\\")
                .pop()}`
            }
            alt={product.nameWorkshop}
            width="400px"
            height="400px"
          />
        </div>
        <div className="col-md-6 col-md-6 py-5 d-flex flex-column align-items-start">
          <h4 className="text-uppercase text-muted">
            {product.nameCategoryWorkshop}
          </h4>
          <h1 className="display-5">{product.nameWorkshop}</h1>
          <p className="lead">{product.addressWorkshop}</p>
          <h3 className="display-6 my-4">{product.timeWorkshop}</h3>
          <p className="lead">{product.descriptionWorkshop}</p>
          {/* Nút "Add to Cart" cho từng vé */}
          <div className="d-flex align-items-center">
            {tickets.map((ticket) => (
              <button
                key={ticket.idTicket}
                className="btn btn-outline-dark me-2 mb-2"
                onClick={() => addProduct(ticket)}
              >
                {ticket.nameTicket}
              </button>
            ))}
            <Link to="/cart" className="btn btn-dark mx-3">
              Thanh toán
            </Link>
          </div>
        </div>

        <div>
          <br />
          <h4>Ban tổ chức</h4>

          <div className="d-flex align-items-center">
            {product.imageCompanyWorkshop && (
              <img
                className="img-fluid"
                src={`http://localhost:8080/images/${product.imageCompanyWorkshop
                  .split("\\")
                  .pop()}`}
                alt={product.nameCompanyWorkshop}
                width="200px"
                height="200px"
              />
            )}
            <div className="ms-3">
              <p>
                <strong>{product.nameCompanyWorkshop}</strong>
              </p>
              <p>{product.descriptionCompanyWorkshop}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );

  return (
    <>
      <Header />
      <div className="container">
        {loading ? <Loading /> : <ShowProduct />}
        <Footer />
      </div>
    </>
  );
};

export default Product;
