import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./Components/Home/Home";
import Navbar from "./Components/Navbar/Navbar";
import User from "./Components/User/User";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
          <Navbar></Navbar>
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/users/:userId" element={<User />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}
export default App;
