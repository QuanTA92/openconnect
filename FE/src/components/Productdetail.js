import React from 'react';

export default function Productdetail() {
    return (
        <>
            <section className="product-details spad">
                <div className="container">
                    <div className="row">
                        <div className="col-lg-6">
                            <div className="product__details__pic">
                                <div className="product__details__pic__left product__thumb nice-scroll">
                                    <a className="pt active" href="#product-1">
                                        <img src="img/product/details/thumb-1.jpg" alt="Thumbnail 1" />
                                    </a>
                                    <a className="pt" href="#product-2">
                                        <img src="img/product/details/thumb-2.jpg" alt="Thumbnail 2" />
                                    </a>
                                    <a className="pt" href="#product-3">
                                        <img src="img/product/details/thumb-3.jpg" alt="Thumbnail 3" />
                                    </a>
                                    <a className="pt" href="#product-4">
                                        <img src="img/product/details/thumb-4.jpg" alt="Thumbnail 4" />
                                    </a>
                                </div>
                                <div className="product__details__slider__content">
                                    <div className="product__details__pic__slider owl-carousel">
                                        <img data-hash="product-1" className="product__big__img" src="img/product/details/product-1.jpg" alt="Product 1" />
                                        <img data-hash="product-2" className="product__big__img" src="img/product/details/product-3.jpg" alt="Product 2" />
                                        <img data-hash="product-3" className="product__big__img" src="img/product/details/product-2.jpg" alt="Product 3" />
                                        <img data-hash="product-4" className="product__big__img" src="img/product/details/product-4.jpg" alt="Product 4" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-6">
                            <div className="product__details__text">
                                <h3>Essential structured blazer <span>Brand: SKMEIMore Men Watches from SKMEI</span></h3>
                                <div className="rating">
                                    <i className="fa fa-star"></i>
                                    <i className="fa fa-star"></i>
                                    <i className="fa fa-star"></i>
                                    <i className="fa fa-star"></i>
                                    <i className="fa fa-star"></i>
                                    <span>( 138 reviews )</span>
                                </div>
                                <div className="product__details__price">$ 75.0 <span>$ 83.0</span></div>
                                <p>Nemo enim ipsam voluptatem quia aspernatur aut odit aut loret fugit, sed quia consequuntur
                                    magni lores eos qui ratione voluptatem sequi nesciunt.</p>
                                <div className="product__details__button">
                                    <div className="quantity">
                                        <span>Quantity:</span>
                                        <div className="pro-qty">
                                            <input type="text" defaultValue="1" />
                                        </div>
                                    </div>
                                    <a href="#" className="cart-btn"><span className="icon_bag_alt"></span> Add to cart</a>
                                    <ul>
                                        <li><a href="#"><span className="icon_heart_alt"></span></a></li>
                                        <li><a href="#"><span className="icon_adjust-horiz"></span></a></li>
                                    </ul>
                                </div>
                                <div className="product__details__widget">
                                    <ul>
                                        <li>
                                            <span>Availability:</span>
                                            <div className="stock__checkbox">
                                                <label htmlFor="stockin">
                                                    In Stock
                                                    <input type="checkbox" id="stockin" />
                                                    <span className="checkmark"></span>
                                                </label>
                                            </div>
                                        </li>
                                        <li>
                                            <span>Available color:</span>
                                            <div className="color__checkbox">
                                                <label htmlFor="red">
                                                    <input type="radio" name="color__radio" id="red" defaultChecked />
                                                    <span className="checkmark"></span>
                                                </label>
                                                <label htmlFor="black">
                                                    <input type="radio" name="color__radio" id="black" />
                                                    <span className="checkmark black-bg"></span>
                                                </label>
                                                <label htmlFor="grey">
                                                    <input type="radio" name="color__radio" id="grey" />
                                                    <span className="checkmark grey-bg"></span>
                                                </label>
                                            </div>
                                        </li>
                                        <li>
                                            <span>Available size:</span>
                                            <div className="size__btn">
                                                <label htmlFor="xs-btn" className="active">
                                                    <input type="radio" id="xs-btn" />
                                                    xs
                                                </label>
                                                <label htmlFor="s-btn">
                                                    <input type="radio" id="s-btn" />
                                                    s
                                                </label>
                                                <label htmlFor="m-btn">
                                                    <input type="radio" id="m-btn" />
                                                    m
                                                </label>
                                                <label htmlFor="l-btn">
                                                    <input type="radio" id="l-btn" />
                                                    l
                                                </label>
                                            </div>
                                        </li>
                                        <li>
                                            <span>Promotions:</span>
                                            <p>Free shipping</p>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        </div>
                        </div>
                        </section>
                        </>
    )}

                   
