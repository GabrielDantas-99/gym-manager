"use client";

import type { AuthResponse, Role, User } from "@/lib/types";
import { useCallback, useEffect, useState } from "react";

import { getProfile } from "../api/users";
import useSWR from "swr";

function getStoredAuth(): {
  token: string;
  user: Omit<AuthResponse, "token">;
} | null {
  if (typeof window === "undefined") return null;
  const token = localStorage.getItem("auth_token");
  const userData = localStorage.getItem("auth_user");
  if (token && userData) {
    try {
      return { token, user: JSON.parse(userData) };
    } catch {
      return null;
    }
  }
  return null;
}

export function useAuth() {
  const [isReady, setIsReady] = useState(false);
  const [token, setToken] = useState<string | null>(null);

  useEffect(() => {
    const stored = getStoredAuth();
    if (stored) {
      setToken(stored.token);
    }
    setIsReady(true);
  }, []);

  const {
    data: user,
    error,
    mutate,
    isLoading,
  } = useSWR<User>(token ? "/api/users/me" : null, () => getProfile(), {
    revalidateOnFocus: false,
    shouldRetryOnError: false,
    onError: () => {
      logout();
    },
  });

  const login = useCallback(
    (authResponse: AuthResponse) => {
      localStorage.setItem("auth_token", authResponse.token);
      localStorage.setItem(
        "auth_user",
        JSON.stringify({
          userId: authResponse.userId,
          nome: authResponse.nome,
          email: authResponse.email,
          role: authResponse.role,
        }),
      );
      setToken(authResponse.token);
      mutate();
    },
    [mutate],
  );

  const logout = useCallback(() => {
    localStorage.removeItem("auth_token");
    localStorage.removeItem("auth_user");
    setToken(null);
    mutate(undefined, false);
    window.location.href = "/login";
  }, [mutate]);

  const hasRole = useCallback(
    (role: Role) => {
      if (!user) {
        const stored = getStoredAuth();
        return stored?.user?.role === role;
      }
      return user.role === role;
    },
    [user],
  );

  const isAdmin = hasRole("ADMIN");
  const isPersonal = hasRole("PERSONAL");
  const isAluno = hasRole("ALUNO");

  // fallback to stored user data while fetching
  const storedAuth = getStoredAuth();
  const currentUser: User | undefined =
    user ||
    (storedAuth
      ? {
          id: storedAuth.user.userId,
          nome: storedAuth.user.nome,
          email: storedAuth.user.email,
          role: storedAuth.user.role,
        }
      : undefined);

  return {
    user: currentUser,
    token,
    isReady,
    isLoading,
    isAuthenticated: !!token,
    isAdmin,
    isPersonal,
    isAluno,
    login,
    logout,
    error,
    mutate,
  };
}
