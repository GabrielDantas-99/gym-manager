import type { User } from "@/lib/types";
import { apiClient } from "./client";

export async function getProfile(): Promise<User> {
  return apiClient.get<User>("/api/users/me");
}
