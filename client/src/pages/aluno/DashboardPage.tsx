import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { ClipboardList, DollarSign } from "lucide-react";

import { getSession } from "@/lib/auth/session";

const AlunoDashboardPage = () => {
  const session = getSession();

  const stats = [
    {
      label: "Treinos Hoje",
      value: "—",
      icon: ClipboardList,
      color: "text-primary",
    },
    {
      label: "Mensalidades",
      value: "—",
      icon: DollarSign,
      color: "text-amber-500",
    },
  ];

  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-2xl font-bold tracking-tight text-foreground">
          Olá, {session?.nome}! 💪
        </h1>
        <p className="text-muted-foreground">
          Acompanhe seus treinos e pagamentos.
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

export default AlunoDashboardPage;
