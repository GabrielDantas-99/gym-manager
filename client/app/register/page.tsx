"use client";

import {
  Card,
  CardContent,
  CardDescription,
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

import { Button } from "@/components/ui/button";
import { Dumbbell } from "lucide-react";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import Link from "next/link";

export default function RegisterPage() {
  return (
    <div className="flex min-h-screen items-center justify-center bg-background px-4 py-8">
      <div className="w-full max-w-md">
        <div className="mb-8 flex flex-col items-center gap-3">
          <div className="flex h-14 w-14 items-center justify-center rounded-2xl bg-primary">
            <Dumbbell className="h-7 w-7 text-primary-foreground" />
          </div>
          <div className="text-center">
            <h1 className="text-2xl font-bold text-foreground">GymManager</h1>
            <p className="mt-1 text-sm text-muted-foreground">
              Crie sua conta para comecar
            </p>
          </div>
        </div>

        <Card>
          <CardHeader className="text-center">
            <CardTitle>Criar Conta</CardTitle>
            <CardDescription>
              Preencha os dados abaixo para se registrar
            </CardDescription>
          </CardHeader>
          <CardContent>
            <form className="space-y-4">
              <div>
                <Label>Nome</Label>
                <Input placeholder="Seu nome completo" />
              </div>
              <div>
                <Label>E-mail</Label>
                <Input type="email" placeholder="seu@email.com" />
              </div>
              <div>
                <Label>Senha</Label>
                <div className="relative">
                  <Input type={"password"} placeholder="Minimo 6 caracteres" />
                </div>
              </div>
              <div>
                <Label>Telefone (opcional)</Label>
                <Input placeholder="(11) 99999-9999" />
              </div>
              <div>
                <Label>Perfil</Label>
                <Select>
                  <SelectTrigger>
                    <SelectValue placeholder="Selecione seu perfil" />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="ADMIN">Administrador</SelectItem>
                    <SelectItem value="PERSONAL">Personal Trainer</SelectItem>
                    <SelectItem value="ALUNO">Aluno</SelectItem>
                  </SelectContent>
                </Select>
              </div>
              <Button type="submit" className="w-full">
                Criar Conta
              </Button>
            </form>
            <div className="mt-6 text-center text-sm text-muted-foreground">
              Ja tem uma conta?{" "}
              <Link
                href="/login"
                className="font-medium text-primary hover:underline"
              >
                Entrar
              </Link>
            </div>
          </CardContent>
        </Card>
      </div>
    </div>
  );
}
