import React, { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import { addCart } from "../redux/action";

import Carousel from "react-multi-carousel";
import "react-multi-carousel/lib/styles.css";

import Skeleton from "react-loading-skeleton";
import { Link } from "react-router-dom";

const Workshop = () => {
  const [workshops, setWorkshops] = useState([]);
  const [loading, setLoading] = useState(false);
  const dispatch = useDispatch();

  const addProduct = (product) => {
    dispatch(addCart(product));
  };

  useEffect(() => {
    const fetchWorkshops = async () => {
      setLoading(true);
      try {
        const response = await fetch("https://fakestoreapi.com/products");
        if (response.ok) {
          const data = await response.json();
          setWorkshops(data);
        }
      } catch (error) {
        console.error("Error fetching workshops: ", error);
      } finally {
        setLoading(false);
      }
    };

    fetchWorkshops();
  }, []);

  const Loading = () => <Skeleton height={300} count={3} />;

  return (
    <div className="container my-3 py-3">
      <style>
        {`
          .carousel-container {
            margin-bottom: 20px;
          }
        `}
      </style>

      <h4 className="text-left mb-4">Sự kiện đặc biệt</h4>
      <div className="row justify-content-center carousel-container">
        {loading ? (
          <Loading />
        ) : (
          <Carousel
            responsive={{
              desktop: {
                breakpoint: { max: 3000, min: 1024 },
                items: 3,
              },
              tablet: {
                breakpoint: { max: 1024, min: 464 },
                items: 2,
              },
              mobile: {
                breakpoint: { max: 464, min: 0 },
                items: 1,
              },
            }}
            autoPlay={true}
            autoPlaySpeed={3000}
            infinite={true}
            removeArrowOnDeviceType={["tablet", "mobile"]}
            showDots={false}
          >
            {workshops.map((workshop) => (
              <div key={workshop.id} className="text-center">
                <img
                  src={workshop.image}
                  alt={workshop.title}
                  height={200}
                  className="mb-3"
                />
                {/* <h5>{workshop.title}</h5> */}
              </div>
            ))}
          </Carousel>
        )}
      </div>

      <h4 className="text-left mb-4">Sự kiện xu hướng</h4>
      <div className="row justify-content-center carousel-container">
        {loading ? (
          <Loading />
        ) : (
          <Carousel
            responsive={{
              desktop: {
                breakpoint: { max: 3000, min: 1024 },
                items: 3,
              },
              tablet: {
                breakpoint: { max: 1024, min: 464 },
                items: 2,
              },
              mobile: {
                breakpoint: { max: 464, min: 0 },
                items: 1,
              },
            }}
            autoPlay={true}
            autoPlaySpeed={3000}
            infinite={true}
            removeArrowOnDeviceType={["tablet", "mobile"]}
            showDots={false}
          >
            {workshops.map((workshop) => (
              <div key={workshop.id} className="text-center">
                <img
                  src={workshop.image}
                  alt={workshop.title}
                  height={200}
                  className="mb-3"
                />
                {/* <h5>{workshop.title}</h5> */}
              </div>
            ))}
          </Carousel>
        )}
      </div>
    </div>
  );
};

export default Workshop;
