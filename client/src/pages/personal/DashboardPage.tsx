import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { ClipboardList, Users } from "lucide-react";

import { getSession } from "@/lib/auth/session";

const PersonalDashboardPage = () => {
  const session = getSession();

  const stats = [
    { label: "Meus Alunos", value: "—", icon: Users, color: "text-primary" },
    {
      label: "Fichas Criadas",
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
          Gerencie seus alunos e fichas de treino.
        </p>
      </div>

      <div className="grid gap-4 sm:grid-cols-2">
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

export default PersonalDashboardPage;
