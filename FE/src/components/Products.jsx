import React, { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import { addCart } from "../redux/action";

import Skeleton from "react-loading-skeleton";
import "react-loading-skeleton/dist/skeleton.css";

import { Link } from "react-router-dom";

const Products = () => {
  const [data, setData] = useState([]);
  const [filter, setFilter] = useState(data);
  const [loading, setLoading] = useState(false);
  let componentMounted = true;

  const dispatch = useDispatch();

  const addProduct = (product) => {
    dispatch(addCart(product));
  };

  useEffect(() => {
    const getProducts = async () => {
      setLoading(true);
      const response = await fetch("http://localhost:8080/workshop");
      if (componentMounted) {
        const responseData = await response.clone().json();
        setData(responseData.data);
        setFilter(responseData.data);
        setLoading(false);
      }

      return () => {
        componentMounted = false;
      };
    };

    getProducts();
  }, []);

  const Loading = () => {
    return (
      <>
        <div className="col-12 py-5 text-center">
          <Skeleton height={40} width={560} />
        </div>
        <div className="col-md-4 col-sm-6 col-xs-8 col-12 mb-4">
          <Skeleton height={592} />
        </div>
        <div className="col-md-4 col-sm-6 col-xs-8 col-12 mb-4">
          <Skeleton height={592} />
        </div>
        <div className="col-md-4 col-sm-6 col-xs-8 col-12 mb-4">
          <Skeleton height={592} />
        </div>
        <div className="col-md-4 col-sm-6 col-xs-8 col-12 mb-4">
          <Skeleton height={592} />
        </div>
        <div className="col-md-4 col-sm-6 col-xs-8 col-12 mb-4">
          <Skeleton height={592} />
        </div>
      </>
    );
  };

  const ShowProducts = () => {
    return (
      <>
        <div className="buttons text-center py-5">
          <button
            className="btn btn-outline-dark btn-sm m-2"
            // onClick={() => setFilterProduct(data)}
          >
            All
          </button>
          <button
            className="btn btn-outline-dark btn-sm m-2"
            // onClick={() => filterProductHandler("Làm nến")}
          >
            Làm nến
          </button>
          <button
            className="btn btn-outline-dark btn-sm m-2"
            // onClick={() => filterProductHandler("Cắm hoa")}
          >
            Cắm hoa
          </button>
          <button
            className="btn btn-outline-dark btn-sm m-2"
            // onClick={() => filterProductHandler("Làm bánh")}
          >
            Làm bánh
          </button>
          <button
            className="btn btn-outline-dark btn-sm m-2"
            // onClick={() => filterProductHandler("Vẽ tranh")}
          >
            Vẽ tranh
          </button>
          <button
            className="btn btn-outline-dark btn-sm m-2"
            // onClick={() => filterProductHandler("Làm gốm")}
          >
            Làm gốm
          </button>
        </div>
        {filter.map((workshop) => {
          return (
            <div
              id={workshop.idWorkshop}
              key={workshop.idWorkshop}
              className="col-md-4 col-sm-6 col-xs-8 col-12 mb-4"
            >
              <div className="card text-center h-100" key={workshop.idWorkshop}>
                <img
                  className="card-img-top p-3"
                  src={`http://localhost:8080/images/${workshop.imageWorkshop
                    .split("\\")
                    .pop()}`}
                  alt="Card"
                  height="300"
                />
                <div className="card-body">
                  <h5 className="card-title">
                    {workshop.nameWorkshop.substring(0, 12)}...
                  </h5>
                  <p className="card-text">
                    {workshop.descriptionWorkshop.substring(0, 90)}...
                  </p>
                </div>
                <ul className="list-group list-group-flush">
                  <li className="list-group-item lead">
                    {workshop.timeWorkshop}
                  </li>
                </ul>
                <div className="card-body">
                  <Link
                    to={"/product/" + workshop.idWorkshop}
                    className="btn btn-dark m-1"
                  >
                    View Workshop
                  </Link>
                  {/* <button className="btn btn-dark m-1" onClick={() => addProduct(workshop)}>
                    Add to Cart
                  </button> */}
                </div>
              </div>
            </div>
          );
        })}
      </>
    );
  };

  return (
    <>
      <div className="container my-3 py-3">
        <div className="row">
          <div className="col-12">
            <h2 className="display-5 text-center">Workshops</h2>
            <hr />
          </div>
        </div>
        <div className="row justify-content-center">
          {loading ? <Loading /> : <ShowProducts />}
        </div>
      </div>
    </>
  );
};

export default Products;
