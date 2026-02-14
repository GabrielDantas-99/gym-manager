import type { AuthResponse, LoginPayload, RegisterPayload } from "@/lib/types";

import { apiClient } from "./client";

export async function registerUser(
  payload: RegisterPayload,
): Promise<AuthResponse> {
  return apiClient.post<AuthResponse>("/api/auth/register", payload);
}

export async function loginUser(payload: LoginPayload): Promise<AuthResponse> {
  return apiClient.post<AuthResponse>("/api/auth/login", payload);
}
