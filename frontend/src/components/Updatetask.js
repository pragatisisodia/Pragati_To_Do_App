import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


function Updatetask() {
    const [description, setDescription] = useState("");
    const [status, setStatus] = useState("");
    const { id } = useParams();
    const navigate = useNavigate();
    const [errors, setErrors] = useState({});

    const getFormData = async (e) => {
        e.preventDefault();

        // Validate form fields
        const validationErrors = {};
        let hasErrors = false;

        if (!description.trim()) {
            validationErrors.description = "Description is required";
            hasErrors = true;
        }
        if (!status) {
            validationErrors.status = "Status is required";
            hasErrors = true;
        }

        if (hasErrors) {
            setErrors(validationErrors);
            return;
        }

        try {
            const response = await axios.put(`http://localhost:8080/todo/updateTodo/${id}`, {
                description: description,
                status: status,
            });
            toast.success("Task updated Successfullly");
            // alert("Task updated successfully");
            navigate("/homepage");
        } catch (error) {
            console.error("Failed to update task:", error);
            toast.error("Something went wrong");
            // alert("Failed to update task. Please try again.");
        }
    };

    return (
        <div className="update-task-container">
            <h2>Update Task</h2>
            <form onSubmit={getFormData} className="task-form">
                <input type="text" placeholder="Name" value={description} onChange={(e) => setDescription(e.target.value)} />
                {errors.description && <span className="error">{errors.description}</span>}
                <br/><br/>
                <div>
                    <select value={status} onChange={(e) => setStatus(e.target.value)}>
                        <option value="">Select Status</option>
                        <option value="PENDING">Pending</option>
                        <option value="COMPLETED">Completed</option>
                    </select>
                    {errors.status && <span className="error">{errors.status}</span>}
                </div>
                <br/>
                <button type="submit" className="update-button">Update</button>
            </form>
        </div>
    );
}

export default Updatetask;
