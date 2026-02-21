import {
  AlertCircle,
  Building2,
  CheckCircle,
  ClipboardList,
  DollarSign,
  TrendingDown,
  TrendingUp,
  Users,
} from "lucide-react";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { formatCurrency, formatNumber } from "@/lib/utils/format";

import { Skeleton } from "@/components/ui/skeleton";
import { getSession } from "@/lib/auth/session";
import { useAcademias } from "@/lib/api/academias";
import { useDashboard } from "@/lib/api/dashboard";

const AdminDashboardPage = () => {
  const session = getSession();
  const { data: dashboard, isLoading: isDashboardLoading } = useDashboard();
  const { data: academias, isLoading: isAcademiasLoading } = useAcademias();

  const totalAcademias = academias?.length || 0;

  const stats = [
    {
      label: "Academias",
      value: isAcademiasLoading ? "—" : formatNumber(totalAcademias),
      icon: Building2,
      color: "text-primary",
      isLoading: isAcademiasLoading,
    },
    {
      label: "Total Alunos",
      value: isDashboardLoading
        ? "—"
        : formatNumber(dashboard?.totalAlunos || 0),
      icon: Users,
      color: "text-blue-500",
      isLoading: isDashboardLoading,
    },
    {
      label: "Total Personais",
      value: isDashboardLoading
        ? "—"
        : formatNumber(dashboard?.totalPersonais || 0),
      icon: ClipboardList,
      color: "text-purple-500",
      isLoading: isDashboardLoading,
    },
    {
      label: "Mensalidades Pendentes",
      value: isDashboardLoading
        ? "—"
        : formatNumber(dashboard?.mensalidadesPendentes || 0),
      icon: AlertCircle,
      color: "text-amber-500",
      isLoading: isDashboardLoading,
    },
  ];

  const financialStats = [
    {
      label: "Receita Mensal",
      value: isDashboardLoading
        ? "—"
        : formatCurrency(dashboard?.receitaMensal || 0),
      icon: TrendingUp,
      color: "text-green-600",
      bgColor: "bg-green-50 dark:bg-green-950",
      isLoading: isDashboardLoading,
    },
    {
      label: "Despesas",
      value: isDashboardLoading
        ? "—"
        : formatCurrency(dashboard?.totalDespesas || 0),
      icon: TrendingDown,
      color: "text-red-600",
      bgColor: "bg-red-50 dark:bg-red-950",
      isLoading: isDashboardLoading,
    },
    {
      label: "Receita Líquida",
      value: isDashboardLoading
        ? "—"
        : formatCurrency(dashboard?.receitaLiquida || 0),
      icon: DollarSign,
      color:
        (dashboard?.receitaLiquida || 0) >= 0
          ? "text-green-600"
          : "text-red-600",
      bgColor:
        (dashboard?.receitaLiquida || 0) >= 0
          ? "bg-green-50 dark:bg-green-950"
          : "bg-red-50 dark:bg-red-950",
      isLoading: isDashboardLoading,
    },
  ];

  const mensalidadesStats = [
    {
      label: "Mensalidades Pagas",
      value: isDashboardLoading
        ? "—"
        : formatNumber(dashboard?.mensalidadesPagas || 0),
      icon: CheckCircle,
      color: "text-green-600",
      isLoading: isDashboardLoading,
    },
    {
      label: "Mensalidades Pendentes",
      value: isDashboardLoading
        ? "—"
        : formatNumber(dashboard?.mensalidadesPendentes || 0),
      icon: AlertCircle,
      color: "text-amber-600",
      isLoading: isDashboardLoading,
    },
  ];

  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-2xl font-bold tracking-tight text-foreground">
          Olá, {session?.nome}! 👋
        </h1>
        <p className="text-muted-foreground">
          Visão geral do sistema de gestão.
        </p>
      </div>

      {/* Cards de Métricas Gerais */}
      <div className="grid gap-4 sm:grid-cols-2 lg:grid-cols-4">
        {stats.map((stat) => (
          <Card key={stat.label}>
            <CardHeader className="flex flex-row items-center justify-between pb-2">
              <CardTitle className="text-sm font-medium text-muted-foreground">
                {stat.label}
              </CardTitle>
              <stat.icon className={`h-4 w-4 ${stat.color}`} />
            </CardHeader>
            <CardContent>
              {stat.isLoading ? (
                <Skeleton className="h-8 w-20" />
              ) : (
                <div className="text-2xl font-bold text-foreground">
                  {stat.value}
                </div>
              )}
            </CardContent>
          </Card>
        ))}
      </div>

      {/* Cards Financeiros */}
      <div className="grid gap-4 sm:grid-cols-1 lg:grid-cols-3">
        {financialStats.map((stat) => (
          <Card key={stat.label} className={stat.bgColor}>
            <CardHeader className="flex flex-row items-center justify-between pb-2">
              <CardTitle className="text-sm font-medium text-muted-foreground">
                {stat.label}
              </CardTitle>
              <stat.icon className={`h-5 w-5 ${stat.color}`} />
            </CardHeader>
            <CardContent>
              {stat.isLoading ? (
                <Skeleton className="h-10 w-32" />
              ) : (
                <div className={`text-3xl font-bold ${stat.color}`}>
                  {stat.value}
                </div>
              )}
            </CardContent>
          </Card>
        ))}
      </div>

      {/* Cards de Mensalidades */}
      <div className="grid gap-4 sm:grid-cols-1 lg:grid-cols-2">
        {mensalidadesStats.map((stat) => (
          <Card key={stat.label}>
            <CardHeader className="flex flex-row items-center justify-between pb-2">
              <CardTitle className="text-sm font-medium text-muted-foreground">
                {stat.label}
              </CardTitle>
              <stat.icon className={`h-5 w-5 ${stat.color}`} />
            </CardHeader>
            <CardContent>
              {stat.isLoading ? (
                <Skeleton className="h-8 w-20" />
              ) : (
                <div className="text-2xl font-bold text-foreground">
                  {stat.value}
                </div>
              )}
            </CardContent>
          </Card>
        ))}
      </div>
    </div>
  );
};

export default AdminDashboardPage;
