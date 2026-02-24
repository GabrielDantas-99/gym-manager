import { Card, CardContent } from "@/components/ui/card";
import { Mail, Phone, Search, Users } from "lucide-react";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";

import { Badge } from "@/components/ui/badge";
import { Input } from "@/components/ui/input";
import { Role } from "@/types/common";
import { Skeleton } from "@/components/ui/skeleton";
import { useState } from "react";
import { useUsers } from "@/lib/api/users";

const roleBadgeVariant = (role: Role) => {
  switch (role) {
    case Role.ADMIN:
      return "destructive" as const;
    case Role.PERSONAL:
      return "default" as const;
    case Role.ALUNO:
      return "secondary" as const;
  }
};

const roleLabel = (role: Role) => {
  switch (role) {
    case Role.ADMIN:
      return "Admin";
    case Role.PERSONAL:
      return "Personal";
    case Role.ALUNO:
      return "Aluno";
  }
};

const UsuariosPage = () => {
  const [search, setSearch] = useState("");
  const { data: users, isLoading } = useUsers();

  const filtered =
    users?.filter(
      (u) =>
        u.nome.toLowerCase().includes(search.toLowerCase()) ||
        u.email.toLowerCase().includes(search.toLowerCase()),
    ) ?? [];

  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-2xl font-bold tracking-tight text-foreground">
          Usuários
        </h1>
        <p className="text-muted-foreground">
          Todos os usuários cadastrados no sistema.
        </p>
      </div>

      <div className="relative max-w-sm">
        <Search className="absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
        <Input
          placeholder="Buscar por nome ou email..."
          className="pl-10"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />
      </div>

      {isLoading ? (
        <Card>
          <CardContent className="p-6 space-y-3">
            {[...Array(4)].map((_, i) => (
              <Skeleton key={i} className="h-12 w-full" />
            ))}
          </CardContent>
        </Card>
      ) : filtered.length === 0 ? (
        <Card>
          <CardContent className="flex flex-col items-center justify-center py-16 text-center">
            <Users className="mb-4 h-12 w-12 text-muted-foreground/50" />
            <h3 className="text-lg font-semibold text-foreground">
              {search
                ? "Nenhum usuário encontrado"
                : "Nenhum usuário cadastrado"}
            </h3>
            <p className="mt-1 text-sm text-muted-foreground">
              {search
                ? "Tente buscar por outro nome ou email."
                : "Usuários aparecerão aqui após se cadastrarem."}
            </p>
          </CardContent>
        </Card>
      ) : (
        <Card>
          <Table>
            <TableHeader>
              <TableRow>
                <TableHead>Nome</TableHead>
                <TableHead>Email</TableHead>
                <TableHead>Telefone</TableHead>
                <TableHead>Perfil</TableHead>
                <TableHead>Status</TableHead>
                <TableHead>Cadastro</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {filtered.map((user) => (
                <TableRow key={user.id}>
                  <TableCell className="font-medium">{user.nome}</TableCell>
                  <TableCell>
                    <span className="flex items-center gap-1 text-muted-foreground">
                      <Mail className="h-3 w-3" />
                      {user.email}
                    </span>
                  </TableCell>
                  <TableCell>
                    {user.telefone ? (
                      <span className="flex items-center gap-1 text-muted-foreground">
                        <Phone className="h-3 w-3" />
                        {user.telefone}
                      </span>
                    ) : (
                      <span className="text-muted-foreground">—</span>
                    )}
                  </TableCell>
                  <TableCell>
                    <Badge variant={roleBadgeVariant(user.role)}>
                      {roleLabel(user.role)}
                    </Badge>
                  </TableCell>
                  <TableCell>
                    <Badge variant={user.ativo ? "default" : "secondary"}>
                      {user.ativo ? "Ativo" : "Inativo"}
                    </Badge>
                  </TableCell>
                  <TableCell className="text-muted-foreground">
                    {new Date(user.dataCadastro).toLocaleDateString("pt-BR")}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </Card>
      )}
    </div>
  );
};

export default UsuariosPage;
