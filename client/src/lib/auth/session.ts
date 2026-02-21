import { Role } from "@/types/common";
import { UserSession } from "@/types/auth";

export function getSession(): UserSession | null {
  try {
    const user = localStorage.getItem("gym_user");
    return user ? JSON.parse(user) : null;
  } catch {
    return null;
  }
}

export function getToken(): string | null {
  return localStorage.getItem("gym_token");
}

export function isAuthenticated(): boolean {
  return !!getToken();
}

export function hasRole(role: Role): boolean {
  const session = getSession();
  return session?.role === role;
}

export function logout(): void {
  localStorage.removeItem("gym_token");
  localStorage.removeItem("gym_user");
  globalThis.location.href = "/login";
}

export function getDashboardByRole(role: Role): string {
  const routes: Record<Role, string> = {
    [Role.ADMIN]: "/admin/dashboard",
    [Role.PERSONAL]: "/personal/dashboard",
    [Role.ALUNO]: "/aluno/dashboard",
  };
  return routes[role] || "/login";
}
