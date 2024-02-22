import './App.css';
import {BrowserRouter,Route, Routes} from "react-router-dom";
import Home from "./Components/Home/Home";
import Navbar from "./Components/Navbar/Navbar";
import User from "./Components/User/User";
import Auth from "./Components/Auth/Auth";
import Welcome from "./Components/Welcome/Welcome";
import Login from "./Components/Auth/Login";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
          <Navbar></Navbar>
        <Routes>
            <Route path="/" element={<Welcome/>}/>
            <Route path="/users/:userId" element={<User />} />
            <Route path="/home" element={<Home/>} />
            <Route path="/login" element={<Login />} />
            <Route path={"/auth"} element={<Auth/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}
export default App;
