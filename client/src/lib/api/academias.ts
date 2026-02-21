import { AcademiaResponse, CreateAcademiaRequest } from "@/types/academia";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";

import apiClient from "./client";

export async function createAcademia(
  data: CreateAcademiaRequest,
): Promise<AcademiaResponse> {
  const response = await apiClient.post("/api/academias", data);
  return response.data;
}

export async function listAcademias(): Promise<AcademiaResponse[]> {
  const response = await apiClient.get("/api/academias");
  return response.data;
}

export async function getAcademia(id: number): Promise<AcademiaResponse> {
  const response = await apiClient.get(`/api/academias/${id}`);
  return response.data;
}

// Hooks
export function useAcademias() {
  return useQuery({
    queryKey: ["academias"],
    queryFn: listAcademias,
    staleTime: 10 * 60 * 1000,
  });
}

export function useAcademia(id: number) {
  return useQuery({
    queryKey: ["academias", id],
    queryFn: () => getAcademia(id),
    enabled: !!id,
  });
}

export function useCreateAcademia() {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: createAcademia,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["academias"] });
    },
  });
}
