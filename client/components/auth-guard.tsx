"use client";

import { Loader2 } from "lucide-react";
import type { Role } from "@/lib/types";
import { useAuth } from "@/lib/hooks/use-auth";
import { useEffect } from "react";

interface AuthGuardProps {
  children: React.ReactNode;
  allowedRoles?: Role[];
}

export function AuthGuard({ children, allowedRoles }: AuthGuardProps) {
  const { isAuthenticated, isReady, user } = useAuth();

  useEffect(() => {
    if (!isReady) return;
    if (!isAuthenticated) {
      window.location.href = "/login";
      return;
    }
    if (allowedRoles && user && !allowedRoles.includes(user.role)) {
      const redirectMap: Record<Role, string> = {
        ADMIN: "/admin",
        PERSONAL: "/personal",
        ALUNO: "/aluno",
      };
      window.location.href = redirectMap[user.role] || "/login";
    }
  }, [isReady, isAuthenticated, user, allowedRoles]);

  if (!isReady) {
    return (
      <div className="flex min-h-screen items-center justify-center bg-background">
        <Loader2 className="h-8 w-8 animate-spin text-primary" />
      </div>
    );
  }

  if (!isAuthenticated) return null;
  if (allowedRoles && user && !allowedRoles.includes(user.role)) return null;

  return <>{children}</>;
}
