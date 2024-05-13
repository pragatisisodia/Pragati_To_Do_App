import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';


function Homepage() {
    const [projects, setProjects] = useState([]);
    
    const navigate = useNavigate();

    useEffect(() => {
        axios.get("http://localhost:8080/project/getAllProject")
            .then(response => {
                console.log(response.data);
                setProjects(response.data.projects);
            })
            .catch(error => {
                console.error('Error fetching projects:', error);
                
            });

    }, []);

    function handlelogout () {
        const confirmation = window.confirm("Logout ?");
            if (confirmation) {
              navigate("/");
            }
      }

    return (
        <div className="homepage-container">
            <button onClick={handlelogout} className="logout-button">Logout</button>
            <h2 className="homepage-heading">Projects</h2>
            
            <Link to={"/newproject/"} className="new-project-link">
                <button className="new-project-button">New Project</button>
            </Link>
            <div className="projects-container">
                {projects.map((currentProject) => {
                    const { id, title } = currentProject;
                    return (
                        <div key={id} className="project-card">
                            <h3>{title}</h3>
                            <Link to={`/viewproject/${id}`} className="view-project-link">
                                <button className="view-project-button">View</button>
                            </Link>
                        </div>
                    )
                })}
            </div>
        </div>
    )
}

export default Homepage;

