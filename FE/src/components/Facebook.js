import React, { useEffect, useRef } from 'react';
import {Link } from 'react-router-dom';

export default function Facebook() {
    const containerRef = useRef(null);

    useEffect(() => {
        const setBgImages = () => {
            if (containerRef.current) {
                const elements = containerRef.current.querySelectorAll('.set-bg');
                elements.forEach(element => {
                    const bg = element.getAttribute('data-setbg');
                    if (bg) {
                        element.style.backgroundImage = `url(${bg})`;
                    }
                });
            }
        };
        setBgImages();
    }, []);

    return (
        <div className="instagram" ref={containerRef}>
            <div className="container-fluid">
                <div className="row">
                    <div className="col-lg-2 col-md-4 col-sm-4 p-0">
                        <div className="instagram__item set-bg" data-setbg="img/instagram/insta-1.jpg">
                            <div className="instagram__text">
                                <i className="fa fa-instagram"></i>
                                <Link to="#" className="nav-link">Book now</Link>

                            </div>
                        </div>
                    </div>
                    <div className="col-lg-2 col-md-4 col-sm-4 p-0">
                        <div className="instagram__item set-bg" data-setbg="img/instagram/insta-2.jpg">
                            <div className="instagram__text">
                                <i className="fa fa-instagram"></i>
                                <Link to="#" className="nav-link">Book now</Link>

                            </div>
                        </div>
                    </div>
                    <div className="col-lg-2 col-md-4 col-sm-4 p-0">
                        <div className="instagram__item set-bg" data-setbg="img/instagram/insta-3.jpg">
                            <div className="instagram__text">
                                <i className="fa fa-instagram"></i>
                                <Link to="#" className="nav-link">Book now</Link>

                            </div>
                        </div>
                    </div>
                    <div className="col-lg-2 col-md-4 col-sm-4 p-0">
                        <div className="instagram__item set-bg" data-setbg="img/instagram/insta-4.jpg">
                            <div className="instagram__text">
                                <i className="fa fa-instagram"></i>
                                <Link to="#" className="nav-link">Book now</Link>

                            </div>
                        </div>
                    </div>
                    <div className="col-lg-2 col-md-4 col-sm-4 p-0">
                        <div className="instagram__item set-bg" data-setbg="img/instagram/insta-5.jpg">
                            <div className="instagram__text">
                                <i className="fa fa-instagram"></i>
                                <Link to="#" className="nav-link">Book now</Link>

                            </div>
                        </div>
                    </div>
                    <div className="col-lg-2 col-md-4 col-sm-4 p-0">
                        <div className="instagram__item set-bg" data-setbg="img/instagram/insta-6.jpg">
                            <div className="instagram__text">
                                <i className="fa fa-instagram"></i>
                                <Link to="#" className="nav-link">Book now</Link>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
