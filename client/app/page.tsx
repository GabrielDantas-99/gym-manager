"use client";

import { Dumbbell, Loader2 } from "lucide-react";

import type { Role } from "@/lib/types";
import { useAuth } from "@/lib/hooks/use-auth";
import { useEffect } from "react";
import { useRouter } from "next/navigation";

export default function Home() {
  const router = useRouter();
  const { isAuthenticated, isReady, user } = useAuth();

  useEffect(() => {
    if (!isReady) return;

    if (!isAuthenticated) {
      router.replace("/login");
      return;
    }

    if (user) {
      const redirectMap: Record<Role, string> = {
        ADMIN: "/admin",
        PERSONAL: "/personal",
        ALUNO: "/aluno",
      };
      router.replace(redirectMap[user.role]);
    }
  }, [isReady, isAuthenticated, user, router]);

  return (
    <div className="flex min-h-screen flex-col items-center justify-center gap-4 bg-background">
      <div className="flex h-16 w-16 items-center justify-center rounded-2xl bg-primary">
        <Dumbbell className="h-8 w-8 text-primary-foreground" />
      </div>
      <Loader2 className="h-6 w-6 animate-spin text-primary" />
      <p className="text-sm text-muted-foreground">Carregando...</p>
    </div>
  );
}
