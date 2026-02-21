import type {
  AuthResponse,
  LoginRequest,
  RegisterRequest,
  UsuarioResponse,
} from "@/types/auth";

import apiClient from "./client";

export async function login(data: LoginRequest): Promise<AuthResponse> {
  const response = await apiClient.post<AuthResponse>("/api/auth/login", data);
  localStorage.setItem("gym_token", response.data.token);
  localStorage.setItem(
    "gym_user",
    JSON.stringify({
      userId: response.data.userId,
      nome: response.data.nome,
      email: response.data.email,
      role: response.data.role,
    }),
  );
  return response.data;
}

export async function register(data: RegisterRequest): Promise<AuthResponse> {
  const response = await apiClient.post<AuthResponse>(
    "/api/auth/register",
    data,
  );
  localStorage.setItem("gym_token", response.data.token);
  localStorage.setItem(
    "gym_user",
    JSON.stringify({
      userId: response.data.userId,
      nome: response.data.nome,
      email: response.data.email,
      role: response.data.role,
    }),
  );
  return response.data;
}

export async function getProfile(): Promise<UsuarioResponse> {
  const response = await apiClient.get<UsuarioResponse>("/api/users/me");
  return response.data;
}
