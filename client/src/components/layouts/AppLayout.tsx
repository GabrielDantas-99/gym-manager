import {
  Building2,
  ClipboardList,
  DollarSign,
  Dumbbell,
  LayoutDashboard,
  Link2,
  LogOut,
  TrendingDown,
  User,
  Users,
} from "lucide-react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import {
  Sidebar,
  SidebarContent,
  SidebarFooter,
  SidebarGroup,
  SidebarGroupContent,
  SidebarGroupLabel,
  SidebarHeader,
  SidebarInset,
  SidebarMenu,
  SidebarMenuButton,
  SidebarMenuItem,
  SidebarProvider,
  SidebarTrigger,
} from "@/components/ui/sidebar";
import { getSession, logout } from "@/lib/auth/session";

import { ReactNode } from "react";
import { Role } from "@/types/common";
import { Separator } from "@/components/ui/separator";

interface NavItem {
  label: string;
  href: string;
  icon: React.ElementType;
}

const adminNav: NavItem[] = [
  { label: "Dashboard", href: "/admin/dashboard", icon: LayoutDashboard },
  { label: "Academias", href: "/admin/academias", icon: Building2 },
  { label: "Usuários", href: "/admin/usuarios", icon: Users },
  { label: "Vínculos", href: "/admin/vinculos", icon: Link2 },
  { label: "Mensalidades", href: "/admin/mensalidades", icon: DollarSign },
  { label: "Despesas", href: "/admin/despesas", icon: TrendingDown },
];

const personalNav: NavItem[] = [
  { label: "Dashboard", href: "/personal/dashboard", icon: LayoutDashboard },
  { label: "Meus Alunos", href: "/personal/alunos", icon: Users },
  { label: "Fichas de Treino", href: "/personal/treinos", icon: ClipboardList },
];

const alunoNav: NavItem[] = [
  { label: "Dashboard", href: "/aluno/dashboard", icon: LayoutDashboard },
  { label: "Meus Treinos", href: "/aluno/treinos", icon: ClipboardList },
  { label: "Mensalidades", href: "/aluno/mensalidades", icon: DollarSign },
];

function getNavByRole(role: Role): NavItem[] {
  switch (role) {
    case Role.ADMIN:
      return adminNav;
    case Role.PERSONAL:
      return personalNav;
    case Role.ALUNO:
      return alunoNav;
  }
}

function getRoleLabel(role: Role): string {
  switch (role) {
    case Role.ADMIN:
      return "Administrador";
    case Role.PERSONAL:
      return "Personal Trainer";
    case Role.ALUNO:
      return "Aluno";
  }
}

interface AppLayoutProps {
  children: ReactNode;
}

const AppLayout = ({ children }: AppLayoutProps) => {
  const session = getSession();
  const location = useLocation();

  if (!session) return null;

  const navItems = getNavByRole(session.role);

  return (
    <SidebarProvider>
      <Sidebar variant="inset">
        <SidebarHeader>
          <SidebarMenu>
            <SidebarMenuItem>
              <SidebarMenuButton size="lg" asChild>
                <Link to={`/${session.role.toLowerCase()}/dashboard`}>
                  <div className="flex h-8 w-8 items-center justify-center rounded-lg bg-primary">
                    <Dumbbell className="h-4 w-4 text-primary-foreground" />
                  </div>
                  <div className="flex flex-col gap-0.5 leading-none">
                    <span className="font-semibold">GymManager</span>
                    <span className="text-xs text-muted-foreground">
                      {getRoleLabel(session.role)}
                    </span>
                  </div>
                </Link>
              </SidebarMenuButton>
            </SidebarMenuItem>
          </SidebarMenu>
        </SidebarHeader>

        <SidebarContent>
          <SidebarGroup>
            <SidebarGroupLabel>Menu</SidebarGroupLabel>
            <SidebarGroupContent>
              <SidebarMenu>
                {navItems.map((item) => (
                  <SidebarMenuItem key={item.href}>
                    <SidebarMenuButton
                      asChild
                      isActive={location.pathname === item.href}
                      tooltip={item.label}
                    >
                      <Link to={item.href}>
                        <item.icon className="h-4 w-4" />
                        <span>{item.label}</span>
                      </Link>
                    </SidebarMenuButton>
                  </SidebarMenuItem>
                ))}
              </SidebarMenu>
            </SidebarGroupContent>
          </SidebarGroup>
        </SidebarContent>

        <SidebarFooter>
          <SidebarMenu>
            <SidebarMenuItem>
              <SidebarMenuButton
                className="py-6 hover:cursor-pointer"
                asChild
                tooltip="Perfil"
              >
                <div className="flex items-center gap-2">
                  <div className="flex h-8 w-8 items-center justify-center rounded-full bg-muted">
                    <User className="h-4 w-4 text-muted-foreground" />
                  </div>
                  <div className="flex flex-col leading-none">
                    <span className="text-sm font-medium">{session.nome}</span>
                    <span className="text-xs text-muted-foreground">
                      {session.email}
                    </span>
                  </div>
                </div>
              </SidebarMenuButton>
            </SidebarMenuItem>
            <SidebarMenuItem>
              <SidebarMenuButton
                className="py-6 hover:cursor-pointer text-red-500"
                onClick={logout}
                tooltip="Sair"
              >
                <div className="flex h-8 w-8 items-center justify-center rounded-full">
                  <LogOut className="h-4 w-4" />
                </div>
                <span>Sair</span>
              </SidebarMenuButton>
            </SidebarMenuItem>
          </SidebarMenu>
        </SidebarFooter>
      </Sidebar>

      <SidebarInset>
        <header className="flex h-14 shrink-0 items-center gap-2 border-b border-border px-4">
          <SidebarTrigger className="-ml-1" />
          <Separator orientation="vertical" className="mr-2 h-4" />
        </header>
        <main className="flex-1 p-6">{children}</main>
      </SidebarInset>
    </SidebarProvider>
  );
};

export default AppLayout;
