import React, { useState } from 'react';
import { Footer, Header } from '../components';

export default function Contact() {
    // Khởi tạo state để lưu trữ dữ liệu form
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        message: ''
    });

    // Hàm xử lý khi thay đổi giá trị trong các trường input
    const handleChange = (e) => {
        const { id, value } = e.target;
        setFormData({ ...formData, [id]: value });
    };

    // Hàm xử lý khi submit form
    const handleSubmit = (e) => {
        e.preventDefault();
        // Kiểm tra tính hợp lệ của form trước khi gửi
        if (formData.name && formData.email && formData.message) {
            alert('THông tin đã được gửi đến admin');
            // Thực hiện các hành động gửi form tại đây
        } else {
            alert('Please fill in all required fields.');
        }
    };

    return (
      <>
      <Header/>
        <div className="container my-3 py-3" style={{ backgroundColor: '#f0f9ff' }}>
            <h1 className="text-center">Liên hệ</h1>
            <hr />
            <div className="row my-4 h-100">
                <div className="col-md-6 col-lg-4 col-sm-8 mx-auto">
                    <form>
                        <div className="form-group my-3">
                            <label htmlFor="name">Name</label>
                            <input
                                type="text"
                                className="form-control"
                                id="name"
                                placeholder="Enter your name"
                                value={formData.name}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className="form-group my-3">
                            <label htmlFor="email">Email</label>
                            <input
                                type="email"
                                className="form-control"
                                id="email"
                                placeholder="name@example.com"
                                value={formData.email}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className="form-group my-3">
                            <label htmlFor="message">Message</label>
                            <textarea
                                rows={5}
                                className="form-control"
                                id="message"
                                placeholder="Enter your message"
                                value={formData.message}
                                onChange={handleChange}
                                required
                            ></textarea>
                        </div>
                        <div className="text-center">
                            <button
                                className="my-2 px-4 btn btn-primary"
                                type="submit"
                                onClick={handleSubmit}
                            >
                                Nộp
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <Footer/>
        </>
    );
}
