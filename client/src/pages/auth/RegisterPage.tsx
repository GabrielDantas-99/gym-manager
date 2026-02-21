import { useState } from "react";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { Link, useNavigate } from "react-router-dom";
import { Loader2, Dumbbell, Mail, Lock, User, Phone } from "lucide-react";
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
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { registerSchema, type RegisterFormData } from "@/lib/schemas/auth";
import { register as registerUser } from "@/lib/api/auth";
import { getDashboardByRole } from "@/lib/auth/session";
import { handleApiError } from "@/lib/api/error-handler";

const roleDescriptions = {
  ADMIN: "Gerencia academias, finanças e usuários",
  PERSONAL: "Cria fichas de treino para alunos",
  ALUNO: "Visualiza treinos e mensalidades",
};

const RegisterPage = () => {
  const navigate = useNavigate();
  const [isLoading, setIsLoading] = useState(false);

  const {
    register,
    handleSubmit,
    setValue,
    formState: { errors },
  } = useForm<RegisterFormData>({
    resolver: zodResolver(registerSchema),
  });

  const getRoleLabel = (role: string) => {
    const labels = {
      ADMIN: "",
      PERSONAL: "",
      ALUNO: "",
    };
    return role ?? labels[role];
  };

  const onSubmit = async (data: RegisterFormData) => {
    setIsLoading(true);
    try {
      const payload = {
        ...data,
        telefone: data.telefone || undefined,
      };
      const response = await registerUser(payload as any);
      toast.success("Conta criada com sucesso!");
      navigate(getDashboardByRole(response.role));
    } catch (error) {
      toast.error(handleApiError(error));
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="flex min-h-screen items-center justify-center bg-background px-4 py-8">
      <div className="w-full max-w-md space-y-8">
        <div className="flex flex-col items-center gap-2">
          <div className="flex h-14 w-14 items-center justify-center rounded-2xl bg-primary">
            <Dumbbell className="h-7 w-7 text-primary-foreground" />
          </div>
          <h1 className="text-2xl font-bold tracking-tight text-foreground">
            GymManager
          </h1>
          <p className="text-sm text-muted-foreground">Crie sua conta</p>
        </div>

        <Card>
          <CardHeader className="gap-0">
            <CardTitle className="text-xl">Cadastro</CardTitle>
            <CardDescription>
              Preencha os dados para criar sua conta
            </CardDescription>
          </CardHeader>

          <form onSubmit={handleSubmit(onSubmit)}>
            <CardContent className="space-y-4">
              <div className="space-y-2">
                <Label htmlFor="nome">Nome</Label>
                <div className="relative">
                  <User className="absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
                  <Input
                    id="nome"
                    placeholder="Seu nome completo"
                    className="pl-10"
                    {...register("nome")}
                  />
                </div>
                {errors.nome && (
                  <p className="text-sm text-destructive">
                    {errors.nome.message}
                  </p>
                )}
              </div>

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
                    placeholder="Mínimo 6 caracteres"
                    className="pl-10"
                    {...register("senha")}
                  />
                </div>
                {errors.senha && (
                  <p className="text-sm text-destructive">
                    {errors.senha.message}
                  </p>
                )}
              </div>

              <div className="space-y-2">
                <Label htmlFor="telefone">Telefone (opcional)</Label>
                <div className="relative">
                  <Phone className="absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
                  <Input
                    id="telefone"
                    placeholder="(11) 99999-9999"
                    className="pl-10"
                    {...register("telefone")}
                  />
                </div>
              </div>

              <div className="space-y-2">
                <Label>Perfil</Label>
                <Select
                  onValueChange={(value) => setValue("role", value as any)}
                >
                  <SelectTrigger className="w-full">
                    <SelectValue placeholder="Selecione seu perfil" />
                  </SelectTrigger>
                  <SelectContent>
                    {Object.entries(roleDescriptions).map(([role, desc]) => (
                      <SelectItem key={role} value={role}>
                        <div className="flex items-center gap-1">
                          <span className="font-medium">
                            {getRoleLabel(role)} -
                          </span>
                          <span className="text-xs text-muted-foreground">
                            {desc}
                          </span>
                        </div>
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
                {errors.role && (
                  <p className="text-sm text-destructive">
                    {errors.role.message}
                  </p>
                )}
              </div>
            </CardContent>

            <CardFooter className="flex flex-col gap-4 mt-6">
              <Button type="submit" className="w-full" disabled={isLoading}>
                {isLoading && <Loader2 className="mr-2 h-4 w-4 animate-spin" />}
                Criar conta
              </Button>
              <p className="text-sm text-muted-foreground">
                Já tem uma conta?{" "}
                <Link
                  to="/login"
                  className="font-medium text-primary hover:underline"
                >
                  Entrar
                </Link>
              </p>
            </CardFooter>
          </form>
        </Card>
      </div>
    </div>
  );
};

export default RegisterPage;
