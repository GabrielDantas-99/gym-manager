import { DashboardResponse } from "@/types/dashboard";
import apiClient from "./client";
import { useQuery } from "@tanstack/react-query";

export async function getDashboard(): Promise<DashboardResponse> {
  const response = await apiClient.get("/api/dashboard");
  return response.data;
}

export function useDashboard() {
  return useQuery({
    queryKey: ["dashboard"],
    queryFn: getDashboard,
    staleTime: 2 * 60 * 1000, // 2 minutos
    refetchInterval: 5 * 60 * 1000, // refetch a cada 5 minutos
  });
}
