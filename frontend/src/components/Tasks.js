import React, { useEffect, useState } from 'react';
import { Link, useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
// import { toast } from 'react-toastify';
// import 'react-toastify/dist/ReactToastify.css';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


function Tasks() {
    const [tasks, setTasks] = useState([]);
    const { project_id } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        axios.get(`http://localhost:8080/todo/getAllTodoOfProject/${project_id}`)
            .then(response => {
                console.log(response.data);
                setTasks(response.data.todos);
            })
            .catch(error => {
                console.error('Error fetching tasks:', error);
            });

    }, [project_id]);



    const handleDelete = (id) => {
        const confirmation = window.confirm("Are you sure you want to delete this task?");
        if (confirmation) {
            axios.delete(`http://localhost:8080/todo/deleteTodo/${id}`)
                .then(response => {
                    console.log('Task deleted successfully');
                    toast.success("Task Deleted Successfully.");
                    window.location.reload(false);
                    // navigate(`/viewproject/${project_id}`);
                    // alert("Task deleted successfully");
                })
                .catch(error => {
                    console.error('Error deleting task:', error);
                    toast.error("Something went wrong");
                    // alert("Something went wrong", error);
                });
        }
    };

    return (
        <div className="tasks-container">
            <h2>Tasks</h2>
            <Link to={`/newtask/${project_id}`} className="add-task-link"><button className="add-task-button">ADD</button></Link>
            <div className="task-list">
                {tasks.map((task) => (
                    <div key={task.id} className="task-card">
                        <h3>{task.createdDate}</h3>
                        <h3>{task.description}</h3>
                        <h3>{task.status}</h3>
                        <h3>{task.updatedDate}</h3>
                        <Link to={`/updatetask/${task.id}`} className="update-task-link"><button className="update-task-button">UPDATE</button></Link>
                        <button onClick={() => handleDelete(task.id)} className="delete-task-button">DELETE</button>
                    </div>
                ))}
            </div>
        </div>
    )
}

export default Tasks;

