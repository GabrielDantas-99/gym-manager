import { Dumbbell, LogOut } from "lucide-react";
import { getSession, logout } from "@/lib/auth/session";

import { Button } from "../ui/button";
import { ReactNode } from "react";

interface AppLayoutProps {
  children: ReactNode;
}

const AppLayout = ({ children }: AppLayoutProps) => {
  const session = getSession();

  if (!session) return null;

  return (
    <>
      <header className="flex h-14 justify-between shrink-0 items-center gap-2 border-b border-border px-4">
        <div className="rounded-lg p-2 bg-primary">
          <Dumbbell className="text-primary-foreground" />
        </div>
        <Button onClick={logout}>
          <LogOut className="h-4 w-4" />
          <span>Sair</span>
        </Button>
      </header>
      <main className="flex-1 p-6">{children}</main>
    </>
  );
};

export default AppLayout;
