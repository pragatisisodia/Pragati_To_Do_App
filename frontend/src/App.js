import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import logo from './logo.svg';
import './App.css';
import './css/Homepage.css';
import './css/Createtask.css';
import './css/Header.css';
import './css/Newproject.css';
import './css/Tasks.css';
import './css/Updatetask.css';
import './css/Login.css';
import Header from './components/Header';
import Homepage from './components/Homepage';
import Newproject from './components/Newproject';
import Tasks from './components/Tasks';
import Createtask from './components/Createtask';
import Updatetask from './components/Updatetask';
import { ToastContainer } from 'react-toastify';
import Login from './components/Login';


function App() {
  return (
    <Router>
      <div>
        <ToastContainer />
        <Header />
        <Routes>
              <Route path="/" element={<Login />} />
              <Route path="/homepage" element={<Homepage />} />
              <Route path="/newproject/" element={<Newproject />} />
              <Route path="/viewproject/:project_id/" element={<Tasks />} />
              <Route path="/newtask/:project_id/" element={<Createtask />} />
              <Route path="/updatetask/:id/" element={<Updatetask />} />
        </Routes>
      </div>  
    </Router>

  );
}

export default App;
