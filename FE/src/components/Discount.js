import React from "react"
import { Link } from "react-router-dom"
export default function Discount() {
    return (
        <section className="discount">
            <div className="container">
                <div className="row">
                    <div className="col-lg-6 p-0">
                        <div className="discount__pic ">
                            <img src="/img/listworkshop.jpg" alt="" />
                        </div>
                    </div>
                    <div className="col-lg-6 p-0">
                        <div className="discount__text">
                            <div className="discount__text__title">
                                <span>WORKSHOP</span>
                                <h2>HOT 2024</h2>
                                <h5>Tháng 6</h5>

                            </div>
                            <div className="discount__countdown" id="countdown-time">
                                <div className="countdown__item">
                                    <span>8</span>
                                    <p>Workshops</p>
                                </div>
                                <div className="countdown__item">
                                    <span>9</span>
                                    <p>Giờ</p>
                                </div>
                                <div className="countdown__item">
                                    <span>15</span>
                                    <p>Giờ</p>
                                </div>
                                <div className="countdown__item">
                                    <span>2</span>
                                    <p>Tiếng</p>
                                </div>
                            </div>
                            <Link to="#" className="nav-link">Book now</Link>

                        </div>
                    </div>
                </div>
            </div>
        </section>
    )
}