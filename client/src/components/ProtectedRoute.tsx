import {
  getDashboardByRole,
  getSession,
  isAuthenticated,
} from "@/lib/auth/session";

import { Navigate } from "react-router-dom";
import { ReactNode } from "react";
import { Role } from "@/types/common";

interface ProtectedRouteProps {
  children: ReactNode;
  allowedRoles?: Role[];
}

export const ProtectedRoute = ({
  children,
  allowedRoles,
}: ProtectedRouteProps) => {
  if (!isAuthenticated()) {
    return <Navigate to="/login" replace />;
  }

  if (allowedRoles) {
    const session = getSession();
    if (!session || !allowedRoles.includes(session.role)) {
      return <Navigate to="/unauthorized" replace />;
    }
  }

  return <>{children}</>;
};

interface PublicOnlyRouteProps {
  children: ReactNode;
}

export const PublicOnlyRoute = ({ children }: PublicOnlyRouteProps) => {
  if (isAuthenticated()) {
    const session = getSession();
    if (session) {
      return <Navigate to={getDashboardByRole(session.role)} replace />;
    }
  }
  return <>{children}</>;
};
