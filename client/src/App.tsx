import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import { ProtectedRoute, PublicOnlyRoute } from "./components/ProtectedRoute";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { Toaster as Sonner, Toaster } from "@/components/ui/sonner";

import AdminDashboard from "./pages/admin/DashboardPage";
import AlunoDashboard from "./pages/aluno/DashboardPage";
import AppLayout from "@/components/layouts/AppLayout";
import LoginPage from "./pages/auth/LoginPage";
import NotFound from "./pages/NotFound";
import PersonalDashboard from "./pages/personal/DashboardPage";
import RegisterPage from "./pages/auth/RegisterPage";
import { Role } from "@/types/common";
import { TooltipProvider } from "./components/ui/tooltip";
import UnauthorizedPage from "./pages/UnauthorizedPage";

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      staleTime: 5 * 60 * 1000,
      retry: 1,
      refetchOnWindowFocus: false,
    },
  },
});

const App = () => (
  <QueryClientProvider client={queryClient}>
    <TooltipProvider>
      <Toaster />
      <Sonner />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Navigate to="/login" replace />} />

          {/* Auth */}
          <Route
            path="/login"
            element={
              <PublicOnlyRoute>
                <LoginPage />
              </PublicOnlyRoute>
            }
          />
          <Route
            path="/register"
            element={
              <PublicOnlyRoute>
                <RegisterPage />
              </PublicOnlyRoute>
            }
          />
          <Route path="/unauthorized" element={<UnauthorizedPage />} />

          {/* Admin */}
          <Route
            path="/admin/dashboard"
            element={
              <ProtectedRoute allowedRoles={[Role.ADMIN]}>
                <AppLayout>
                  <AdminDashboard />
                </AppLayout>
              </ProtectedRoute>
            }
          />
          {/* Personal */}
          <Route
            path="/personal/dashboard"
            element={
              <ProtectedRoute allowedRoles={[Role.PERSONAL]}>
                <AppLayout>
                  <PersonalDashboard />
                </AppLayout>
              </ProtectedRoute>
            }
          />
          {/* Aluno */}
          <Route
            path="/aluno/dashboard"
            element={
              <ProtectedRoute allowedRoles={[Role.ALUNO]}>
                <AppLayout>
                  <AlunoDashboard />
                </AppLayout>
              </ProtectedRoute>
            }
          />

          <Route path="*" element={<NotFound />} />
        </Routes>
      </BrowserRouter>
    </TooltipProvider>
  </QueryClientProvider>
);

export default App;
