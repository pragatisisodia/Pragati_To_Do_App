import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [emailError, setEmailError] = useState("");
    const [passwordError, setPasswordError] = useState("");
    const navigate = useNavigate();

    const getFormData = async (e) => {
        e.preventDefault();

        // Clear previous error messages
        setEmailError("");
        setPasswordError("");

        // Validate form fields
        let hasErrors = false;

        if (!email) {
            setEmailError("Please enter your email");
            hasErrors = true;
        }

        if (!password) {
            setPasswordError("Please enter your password");
            hasErrors = true;
        }

        if (hasErrors) {
            return;
        }

        if (email === "admin@gmail.com" && password === "admin") {
            navigate("/homepage");
        } else {
            toast.error("Invalid Credentials!");
        }
    }

    return (
        <div className="login-container">
            <h1>Login</h1>
            <form onSubmit={getFormData} className="login-form">
                <input type="text" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
                {emailError && <span className="error">{emailError}</span>}
                <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
                {passwordError && <span className="error">{passwordError}</span>}
                <button type="submit">Login</button>
            </form>
        </div>
    );
}

export default Login;



























// import React, { useState } from 'react';
// import axios from 'axios';
// import { Link, useNavigate } from 'react-router-dom';
// import { toast } from 'react-toastify';
// import 'react-toastify/dist/ReactToastify.css';


// function Login() {
//     const [email, setEmail] = useState("");
//     const [password, setPassword] = useState("");
//     const [emailError, setEmailError] = useState("");
//     const [passwordError, setPasswordError] = useState("");
//     const navigate = useNavigate();

//     const getFormData = async (e) => {
//         e.preventDefault();

//         // Clear previous error messages
//         setEmailError("");
//         setPasswordError("");

//         // Validate form fields
//         let hasErrors = false;

//         if (!email) {
//             setEmailError("Please enter your email");
//             hasErrors = true;
//         }

//         if (!password) {
//             setPasswordError("Please enter your password");
//             hasErrors = true;
//         }

//         if (hasErrors) {
//             return;
//         }


//         if(email=="admin@gmail.com" && password=="admin") {
//             navigate("/homepage");
//         }
//         else {
//             console.log("credentials are not correct");
//             toast.error("Invalid Credentials!");
//         }
//         // try {
//         //     const response = await axios.post("http://localhost:8000/event_management/login/", {
//         //         email: email,
//         //         password: password
//         //     });
//         //     // Extract the token from the response data
//         //     const token = response.data.access;

//         //     // Store the token securely (e.g., in local storage)
//         //     localStorage.setItem('token', token);
//         //     localStorage.setItem('email', response.data.email);

//         //     setEmail("");
//         //     setPassword("");

//         //     navigate("/");
//         //     window.location.reload(); // Refresh the page after successful login
//         // } catch (error) {
//         //     console.error("Login failed", error);
//         //     alert("Invalid credentials");
//         // }
//     }

//     return (
//         <div className="App">
//             <h1>Login</h1>
//             <form onSubmit={getFormData}>
//                 <input type="text" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
//                 {emailError && <span className="error">{emailError}</span>}
//                 <br /><br />
//                 <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
//                 {passwordError && <span className="error">{passwordError}</span>}
//                 <br /><br />
//                 <button type="submit">Login</button>
//             </form>
//         </div>
//     );
// }

// export default Login;