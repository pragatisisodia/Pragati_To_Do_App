import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


function Newproject() {
    const [title, setTitle] = useState("");
    const [titleError, setTitleError] = useState("");
    const navigate = useNavigate();

    const getFormData = async (e) => {
        e.preventDefault();

        // Clear previous error messages
        setTitleError("");
        // Validate form fields
        let hasErrors = false;

        if (!title) {
            setTitleError("Please enter project name");
            hasErrors = true;
        }

        if (hasErrors) {
            return;
        }

        try {
            const response = await axios.post("http://localhost:8080/project/addProject", {
                title: title
            });

            toast.success("Project Created Successfully");
            
            navigate("/homepage");

        } catch (error) {
            console.error("failed", error);
            toast.error("Something went wrong");
            // alert("Something went wrong");
        }
    }

    return (
        <div className="new-project-container">
            <h2>Create Project</h2>
            <form onSubmit={getFormData} className="project-form">
                <input type="text" placeholder="Project Name" value={title} onChange={(e) => setTitle(e.target.value)} />
                {titleError && <span className="error">{titleError}</span>}
                <br /><br />
                <button type='submit' className="create-button">Create</button>
            </form>
        </div>
    );
}

export default Newproject;

