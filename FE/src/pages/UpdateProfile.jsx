import React, { useState } from 'react';
import { Footer, Header } from "../components";
import { useNavigate } from 'react-router-dom';

const UpdateProfile = () => {
    const [profile, setProfile] = useState({
        fullName: "John Doe",
        email: "john@example.com",
        phone: "(239) 816-9029",
        mobile: "(320) 380-4539",
        address: "Bay Area, San Francisco, CA"
    });

    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setProfile({ ...profile, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Perform the save operation here (e.g., API call to save the updated profile)
        console.log("Profile updated:", profile);
        navigate("/myprofile"); // Redirect to the profile page after saving
    };

    return (
        <>
            <Header />
            <div className="container my-3 py-3" style={{ backgroundColor: '#f0f9ff' }}>
                <h2 className="text-center mb-4">My Profile</h2>
                <div className="row gutters-sm">
                    <div className="col-md-4 mb-3">
                        <div className="card">
                            <div className="card-body">
                                <div className="d-flex flex-column align-items-center text-center">
                                    <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin" className="rounded-circle" width="150" />
                                    <div className="mt-3">
                                        <h4>{profile.fullName}</h4>
                                        <p className="text-secondary mb-1">Full Stack Developer</p>
                                        <p className="text-muted font-size-sm">{profile.address}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="col-lg-8">
                        <div className="card">
                            <div className="card-body">
                                <form onSubmit={handleSubmit}>
                                    <div className="row mb-3">
                                        <div className="col-sm-3">
                                            <h6 className="mb-0">Full Name</h6>
                                        </div>
                                        <div className="col-sm-9 text-secondary">
                                            <input
                                                type="text"
                                                className="form-control"
                                                name="fullName"
                                                value={profile.fullName}
                                                onChange={handleChange}
                                            />
                                        </div>
                                    </div>
                                    <div className="row mb-3">
                                        <div className="col-sm-3">
                                            <h6 className="mb-0">Email</h6>
                                        </div>
                                        <div className="col-sm-9 text-secondary">
                                            <input
                                                type="email"
                                                className="form-control"
                                                name="email"
                                                value={profile.email}
                                                onChange={handleChange}
                                            />
                                        </div>
                                    </div>
                                    <div className="row mb-3">
                                        <div className="col-sm-3">
                                            <h6 className="mb-0">Phone</h6>
                                        </div>
                                        <div className="col-sm-9 text-secondary">
                                            <input
                                                type="text"
                                                className="form-control"
                                                name="phone"
                                                value={profile.phone}
                                                onChange={handleChange}
                                            />
                                        </div>
                                    </div>
                                    <div className="row mb-3">
                                        <div className="col-sm-3">
                                            <h6 className="mb-0">Mobile</h6>
                                        </div>
                                        <div className="col-sm-9 text-secondary">
                                            <input
                                                type="text"
                                                className="form-control"
                                                name="mobile"
                                                value={profile.mobile}
                                                onChange={handleChange}
                                            />
                                        </div>
                                    </div>
                                    <div className="row mb-3">
                                        <div className="col-sm-3">
                                            <h6 className="mb-0">Address</h6>
                                        </div>
                                        <div className="col-sm-9 text-secondary">
                                            <input
                                                type="text"
                                                className="form-control"
                                                name="address"
                                                value={profile.address}
                                                onChange={handleChange}
                                            />
                                        </div>
                                    </div>
                                    <div className="row">
                                        <div className="col-sm-12">
                                            <button type="submit" className="btn btn-info">Save Changes</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <Footer />
        </>
    );
}

export default UpdateProfile;
