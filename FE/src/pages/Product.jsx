import React, { useEffect, useState } from "react";
import Skeleton from "react-loading-skeleton";
import { Link, useParams } from "react-router-dom";
import { useDispatch } from "react-redux";
import { addCart } from "../redux/action";
import { Footer, Header } from "../components";

const Product = () => {
  const { id } = useParams();
  const [product, setProduct] = useState({});
  const [tickets, setTickets] = useState([]);
  const [loading, setLoading] = useState(false);

  const dispatch = useDispatch();

  const addProduct = (product) => {
    dispatch(addCart(product));
  };

  useEffect(() => {
    const getProduct = async () => {
      setLoading(true);
      const response = await fetch(`http://localhost:8080/workshop/${id}`);
      const data = await response.json();
      setProduct(data.data[0]);
      setLoading(false);

      // Fetch tickets for the workshop
      const ticketsResponse = await fetch(
        `http://localhost:8080/ticket/getAllTicketsByIdWorkshop/${id}`
      );
      const ticketsData = await ticketsResponse.json();
      setTickets(ticketsData.data);
    };
    getProduct();
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
        <div className="col-md-6 col-md-6 py-5">
          <h4 className="text-uppercase text-muted">
            {product.nameCategoryWorkshop}
          </h4>
          <h1 className="display-5">{product.nameWorkshop}</h1>
          <p className="lead">{product.addressWorkshop}</p>
          <h3 className="display-6 my-4">{product.timeWorkshop}</h3>
          <p className="lead">{product.descriptionWorkshop}</p>
        </div>

        <div className="col-md-12">
          <div className="portlet light bordered">
            <div className="portlet-title tabbable-line">
              <div className="caption caption-md">
                <i className="icon-globe theme-font hide"></i>
                <h4 className="caption-subject font-blue-madison bold uppercase">
                  {product.timeWorkshop}, {product.addressWorkshop}
                </h4>
              </div>
            </div>
            <div className="portlet-body">
              <table className="table">
                <thead>
                  <tr className="active">
                    <th scope="col">Vé</th>
                    <th scope="col">Mô tả</th>
                    <th scope="col">Giá</th>
                    <th className="d-flex justify-content-end">
                      <Link to="/cart" className="btn btn-outline-dark">
                        Thanh toán
                      </Link>
                    </th>
                  </tr>
                </thead>
                <tbody>
                  {tickets.map((ticket, index) => (
                    <tr key={index}>
                      <td>{ticket.nameTicket}</td>
                      <td>{ticket.descriptionTicket}</td>
                      <td>{ticket.priceTicket} đ</td>
                      <td className="d-flex justify-content-end">
                        <button
                          className="btn btn-outline-dark"
                          onClick={() => addProduct(ticket)}
                        >
                          Mua vé
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>

        <div>
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
