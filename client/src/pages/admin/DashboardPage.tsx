import { Building2, ClipboardList, DollarSign, Users } from "lucide-react";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";

import { getSession } from "@/lib/auth/session";

const AdminDashboardPage = () => {
  const session = getSession();

  const stats = [
    { label: "Academias", value: "—", icon: Building2, color: "text-primary" },
    { label: "Usuários", value: "—", icon: Users, color: "text-blue-500" },
    {
      label: "Mensalidades",
      value: "—",
      icon: DollarSign,
      color: "text-amber-500",
    },
    {
      label: "Fichas Ativas",
      value: "—",
      icon: ClipboardList,
      color: "text-purple-500",
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
              <div className="text-2xl font-bold text-foreground">
                {stat.value}
              </div>
            </CardContent>
          </Card>
        ))}
      </div>

      <Card>
        <CardContent className="flex items-center justify-center py-16">
          <p className="text-muted-foreground">
            Dados do dashboard serão carregados nos próximos commits.
          </p>
        </CardContent>
      </Card>
    </div>
  );
};

export default AdminDashboardPage;
