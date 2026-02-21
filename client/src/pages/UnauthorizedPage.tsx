import { Button } from "@/components/ui/button";
import { Link } from "react-router-dom";
import { ShieldX } from "lucide-react";

const UnauthorizedPage = () => {
  return (
    <div className="flex min-h-screen flex-col items-center justify-center bg-background gap-4">
      <ShieldX className="h-16 w-16 text-destructive" />
      <h1 className="text-2xl font-bold text-foreground">Acesso Negado</h1>
      <p className="text-muted-foreground">
        Você não tem permissão para acessar esta página.
      </p>
      <Button asChild>
        <Link to="/login">Voltar ao Login</Link>
      </Button>
    </div>
  );
};

export default UnauthorizedPage;
