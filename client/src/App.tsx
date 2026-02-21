import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";

import LoginPage from "./pages/auth/LoginPage";
import RegisterPage from "./pages/auth/RegisterPage";

const App = () => (
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<Navigate to="/login" replace />} />
      {/* Auth */}
      <Route path="/login" element={<LoginPage />} />
      <Route path="/register" element={<RegisterPage />} />
    </Routes>
  </BrowserRouter>
);

export default App;
