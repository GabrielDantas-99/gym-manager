import type { UsuarioResponse } from "@/types/auth";
import apiClient from "./client";
import { useQuery } from "@tanstack/react-query";

export async function getProfile(): Promise<UsuarioResponse> {
  const response = await apiClient.get("/api/users/me");
  return response.data;
}

export async function listUsers(): Promise<UsuarioResponse[]> {
  const response = await apiClient.get("/api/users");
  return response.data;
}

// Hooks
export function useProfile() {
  return useQuery({
    queryKey: ["profile"],
    queryFn: getProfile,
    staleTime: 5 * 60 * 1000,
  });
}

export function useUsers() {
  return useQuery({
    queryKey: ["users"],
    queryFn: listUsers,
    staleTime: 10 * 60 * 1000,
  });
}
