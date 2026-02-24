import { CreateVinculoRequest, VinculoResponse } from "@/types/vinculo";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";

import apiClient from "./client";

export async function createVinculo(
  data: CreateVinculoRequest,
): Promise<VinculoResponse> {
  const response = await apiClient.post("/api/vinculos", data);
  return response.data;
}

export async function listVinculosByAcademia(
  academiaId: number,
): Promise<VinculoResponse[]> {
  const response = await apiClient.get(`/api/vinculos/academia/${academiaId}`);
  return response.data;
}

export async function listAlunosDoPersonal(): Promise<any[]> {
  const response = await apiClient.get("/api/vinculos/personal/alunos");
  return response.data;
}

// Hooks
export function useVinculosByAcademia(academiaId: number | null) {
  return useQuery({
    queryKey: ["vinculos", "academia", academiaId],
    queryFn: () => listVinculosByAcademia(academiaId!),
    enabled: !!academiaId,
    staleTime: 5 * 60 * 1000,
  });
}

export function useAlunosDoPersonal() {
  return useQuery({
    queryKey: ["vinculos", "personal", "alunos"],
    queryFn: listAlunosDoPersonal,
    staleTime: 5 * 60 * 1000,
  });
}

export function useCreateVinculo() {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: createVinculo,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["vinculos"] });
    },
  });
}
