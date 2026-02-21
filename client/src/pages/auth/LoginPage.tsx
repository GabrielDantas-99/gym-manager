import { useState } from "react";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { Link, useNavigate } from "react-router-dom";
import { Loader2, Dumbbell, Mail, Lock } from "lucide-react";
import { toast } from "sonner";

import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { loginSchema, type LoginFormData } from "@/lib/schemas/auth";
import { login } from "@/lib/api/auth";
import { getDashboardByRole } from "@/lib/auth/session";
import { handleApiError } from "@/lib/api/error-handler";

const LoginPage = () => {
  const navigate = useNavigate();
  const [isLoading, setIsLoading] = useState(false);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<LoginFormData>({
    resolver: zodResolver(loginSchema),
  });

  const onSubmit = async (formData: LoginFormData) => {
    setIsLoading(true);
    try {
      const response = await login({
        email: formData.email,
        senha: formData.senha,
      });
      toast.success(`Bem-vindo, ${response.nome}!`);
      navigate(getDashboardByRole(response.role));
    } catch (error: any) {
      toast.error(handleApiError(error));
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="flex min-h-screen items-center justify-center bg-background px-4">
      <div className="w-full max-w-md space-y-8">
        <div className="flex flex-col items-center gap-2">
          <div className="flex h-14 w-14 items-center justify-center rounded-2xl bg-primary">
            <Dumbbell className="h-7 w-7 text-primary-foreground" />
          </div>
          <h1 className="text-2xl font-bold tracking-tight text-foreground">
            GymManager
          </h1>
          <p className="text-sm text-muted-foreground">
            Sistema de Gestão de Academias
          </p>
        </div>

        <Card>
          <CardHeader className="gap-0">
            <CardTitle className="text-xl">Entrar</CardTitle>
            <CardDescription>Acesse sua conta para continuar</CardDescription>
          </CardHeader>

          <form onSubmit={handleSubmit(onSubmit)}>
            <CardContent className="space-y-4">
              <div className="space-y-2">
                <Label htmlFor="email">Email</Label>
                <div className="relative">
                  <Mail className="absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
                  <Input
                    id="email"
                    type="email"
                    placeholder="seu@email.com"
                    className="pl-10"
                    {...register("email")}
                  />
                </div>
                {errors.email && (
                  <p className="text-sm text-destructive">
                    {errors.email.message}
                  </p>
                )}
              </div>

              <div className="space-y-2">
                <Label htmlFor="senha">Senha</Label>
                <div className="relative">
                  <Lock className="absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
                  <Input
                    id="senha"
                    type="password"
                    placeholder="••••••"
                    className="pl-10"
                    {...register("senha")}
                  />
                </div>
                {errors.senha && (
                  <p className="text-sm text-destructive">
                    {errors.senha.message}
                  </p>
                )}
                <div className="w-full text-right">
                  <Link className="underline" to={"/recuperar-senha"}>
                    Esqueceu a senha?
                  </Link>
                </div>
              </div>
            </CardContent>

            <CardFooter className="flex flex-col gap-4 mt-4">
              <Button type="submit" className="w-full" disabled={isLoading}>
                {isLoading && <Loader2 className="mr-2 h-4 w-4 animate-spin" />}
                Entrar
              </Button>
              <p className="text-sm text-muted-foreground">
                Não tem uma conta?{" "}
                <Link
                  to="/register"
                  className="font-medium text-primary hover:underline"
                >
                  Cadastre-se
                </Link>
              </p>
            </CardFooter>
          </form>
        </Card>
      </div>
    </div>
  );
};

export default LoginPage;
