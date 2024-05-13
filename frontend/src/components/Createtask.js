import React, { useState } from 'react';
import axios from 'axios';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


function Createtask() {
    const [description, setDescription] = useState("");
    const [status, setStatus] = useState("");
    const [statusError, setStatusError] = useState("");
    const [descriptionError, setDescriptionError] = useState("");
    const { project_id } = useParams();
    const navigate = useNavigate();

    const getFormData = async (e) => {
        e.preventDefault();

        // Clear previous error messages
        setDescriptionError("");
        setStatusError("");
        // Validate form fields
        let hasErrors = false;

        if (!description) {
            setDescriptionError("Please enter description");
            hasErrors = true;
        }

        if (!status) {
            setStatusError("Please select status");
            hasErrors = true;
        }

        if (hasErrors) {
            return;
        }

        try {
            const response = await axios.post("http://localhost:8080/todo/addTask", {
                project_id: project_id,
                description: description,
                status: status
            });
            // alert("Created Successfully")
            toast.success('Task Created Successfully');
            setDescription("");
            setStatus("");

            // Extract the token from the response data
            // const token = response.data.access;

            // navigate(`viewproject/${project_id}`);

        } catch (error) {
            console.error("failed", error);
            toast.error("Something went wrong.")
            // alert("Something went wrong");
        }
    }

    return (
        <div className="create-task-container">
            <h2>Create Task</h2>
            <form onSubmit={getFormData} className="task-form">
                <input type="text" placeholder="Description" value={description} onChange={(e) => setDescription(e.target.value)} />
                {descriptionError && <span className="error">{descriptionError}</span>}
                <br /><br />
                <div>
                    <select value={status} onChange={(e) => setStatus(e.target.value)}>
                        <option value="">Select Status</option>
                        <option value="PENDING">Pending</option>
                        <option value="COMPLETED">Completed</option>
                    </select>
                    {statusError && <span className="error">{statusError}</span>}
                </div>
                <br />
                <button type='submit' className="create-button">Create</button>
            </form>
        </div>
    );
}

export default Createtask;
