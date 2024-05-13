import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

function Header() {

  

  
  
  return (
    <div className="header-container">
      <h1 className="header-title">TO-DO-APP</h1>
      <nav className="header-nav">
        <ul>
          {/* <button onClick={handlelogout}>Logout</button> */}
          {/* <li><Link to="/">Home</Link></li>
          <li><Link to="/about">About</Link></li>
          Add more navigation links as needed */}
        </ul>
      </nav>
    </div>
  );
}

export default Header;






























// import React, { useEffect, useState } from 'react';
// import { Link } from 'react-router-dom';
// import axios from 'axios';
// // import '../Css/Header.css'; // Import CSS file for styling

// function Header() {
  
//   return (
//     <div>
//         <h1>TO - DO - APP</h1>
//     </div>
//       );
// }

// export default Header;
