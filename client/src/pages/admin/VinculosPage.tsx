import { Card, CardContent } from "@/components/ui/card";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import { Link2, Loader2, Plus, Search, Users } from "lucide-react";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { useCreateVinculo, useVinculosByAcademia } from "@/lib/api/vinculos";

import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Role } from "@/types/common";
import { Skeleton } from "@/components/ui/skeleton";
import { handleApiError } from "@/lib/api/error-handler";
import { toast } from "sonner";
import { useAcademias } from "@/lib/api/academias";
import { useState } from "react";
import { useUsers } from "@/lib/api/users";

const VinculosPage = () => {
  const [selectedAcademiaId, setSelectedAcademiaId] = useState<number | null>(
    null,
  );
  const [dialogOpen, setDialogOpen] = useState(false);
  const [search, setSearch] = useState("");

  // Form state
  const [formUsuarioId, setFormUsuarioId] = useState<string>("");
  const [formAcademiaId, setFormAcademiaId] = useState<string>("");
  const [formPersonalId, setFormPersonalId] = useState<string>("");

  const { data: academias } = useAcademias();
  const { data: users } = useUsers();
  const { data: vinculos, isLoading } =
    useVinculosByAcademia(selectedAcademiaId);
  const createMutation = useCreateVinculo();

  const selectedUser = users?.find((u) => u.id === Number(formUsuarioId));
  const personais = users?.filter((u) => u.role === Role.PERSONAL) ?? [];

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!formUsuarioId || !formAcademiaId) {
      toast.error("Selecione um usuário e uma academia.");
      return;
    }

    try {
      await createMutation.mutateAsync({
        usuarioId: Number(formUsuarioId),
        academiaId: Number(formAcademiaId),
        ...(formPersonalId ? { personalId: Number(formPersonalId) } : {}),
      });
      toast.success("Vínculo criado com sucesso!");
      setFormUsuarioId("");
      setFormAcademiaId("");
      setFormPersonalId("");
      setDialogOpen(false);
    } catch (error: any) {
      toast.error(handleApiError(error));
    }
  };

  const filtered =
    vinculos?.filter(
      (v) =>
        v.usuario.nome.toLowerCase().includes(search.toLowerCase()) ||
        v.usuario.email.toLowerCase().includes(search.toLowerCase()),
    ) ?? [];

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-2xl font-bold tracking-tight text-foreground">
            Vínculos
          </h1>
          <p className="text-muted-foreground">
            Gerencie os vínculos de usuários às academias.
          </p>
        </div>

        <Dialog open={dialogOpen} onOpenChange={setDialogOpen}>
          <DialogTrigger asChild>
            <Button>
              <Plus className="mr-2 h-4 w-4" />
              Novo Vínculo
            </Button>
          </DialogTrigger>
          <DialogContent>
            <DialogHeader>
              <DialogTitle>Criar Vínculo</DialogTitle>
              <DialogDescription>
                Vincule um usuário a uma academia.
              </DialogDescription>
            </DialogHeader>
            <form onSubmit={handleSubmit} className="space-y-4">
              <div className="space-y-2">
                <Label>Usuário *</Label>
                <Select value={formUsuarioId} onValueChange={setFormUsuarioId}>
                  <SelectTrigger>
                    <SelectValue placeholder="Selecione um usuário" />
                  </SelectTrigger>
                  <SelectContent>
                    {users?.map((u) => (
                      <SelectItem key={u.id} value={String(u.id)}>
                        {u.nome} ({u.role})
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>

              <div className="space-y-2">
                <Label>Academia *</Label>
                <Select
                  value={formAcademiaId}
                  onValueChange={setFormAcademiaId}
                >
                  <SelectTrigger>
                    <SelectValue placeholder="Selecione uma academia" />
                  </SelectTrigger>
                  <SelectContent>
                    {academias?.map((a) => (
                      <SelectItem key={a.id} value={String(a.id)}>
                        {a.nome}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>

              {selectedUser?.role === Role.ALUNO && (
                <div className="space-y-2">
                  <Label>Personal Trainer (opcional)</Label>
                  <Select
                    value={formPersonalId}
                    onValueChange={setFormPersonalId}
                  >
                    <SelectTrigger>
                      <SelectValue placeholder="Selecione um personal" />
                    </SelectTrigger>
                    <SelectContent>
                      {personais.map((p) => (
                        <SelectItem key={p.id} value={String(p.id)}>
                          {p.nome}
                        </SelectItem>
                      ))}
                    </SelectContent>
                  </Select>
                </div>
              )}

              <DialogFooter>
                <Button
                  type="button"
                  variant="outline"
                  onClick={() => setDialogOpen(false)}
                >
                  Cancelar
                </Button>
                <Button type="submit" disabled={createMutation.isPending}>
                  {createMutation.isPending && (
                    <Loader2 className="mr-2 h-4 w-4 animate-spin" />
                  )}
                  Vincular
                </Button>
              </DialogFooter>
            </form>
          </DialogContent>
        </Dialog>
      </div>

      {/* Filter by academia */}
      <div className="flex flex-col gap-4 sm:flex-row sm:items-end">
        <div className="space-y-2">
          <Label className="text-sm text-muted-foreground">
            Filtrar por academia
          </Label>
          <Select
            value={selectedAcademiaId ? String(selectedAcademiaId) : ""}
            onValueChange={(v) => setSelectedAcademiaId(v ? Number(v) : null)}
          >
            <SelectTrigger className="w-[260px]">
              <SelectValue placeholder="Selecione uma academia" />
            </SelectTrigger>
            <SelectContent>
              {academias?.map((a) => (
                <SelectItem key={a.id} value={String(a.id)}>
                  {a.nome}
                </SelectItem>
              ))}
            </SelectContent>
          </Select>
        </div>

        {selectedAcademiaId && (
          <div className="relative max-w-sm flex-1">
            <Search className="absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
            <Input
              placeholder="Buscar por nome ou email..."
              className="pl-10"
              value={search}
              onChange={(e) => setSearch(e.target.value)}
            />
          </div>
        )}
      </div>

      {!selectedAcademiaId ? (
        <Card>
          <CardContent className="flex flex-col items-center justify-center py-16 text-center">
            <Link2 className="mb-4 h-12 w-12 text-muted-foreground/50" />
            <h3 className="text-lg font-semibold text-foreground">
              Selecione uma academia
            </h3>
            <p className="mt-1 text-sm text-muted-foreground">
              Escolha uma academia acima para visualizar seus vínculos.
            </p>
          </CardContent>
        </Card>
      ) : isLoading ? (
        <Card>
          <CardContent className="p-6 space-y-3">
            {[...Array(3)].map((_, i) => (
              <Skeleton key={i} className="h-12 w-full" />
            ))}
          </CardContent>
        </Card>
      ) : filtered.length === 0 ? (
        <Card>
          <CardContent className="flex flex-col items-center justify-center py-16 text-center">
            <Users className="mb-4 h-12 w-12 text-muted-foreground/50" />
            <h3 className="text-lg font-semibold text-foreground">
              Nenhum vínculo encontrado
            </h3>
            <p className="mt-1 text-sm text-muted-foreground">
              {search
                ? "Tente outro termo de busca."
                : "Vincule usuários a esta academia."}
            </p>
          </CardContent>
        </Card>
      ) : (
        <Card>
          <Table>
            <TableHeader>
              <TableRow>
                <TableHead>Usuário</TableHead>
                <TableHead>Email</TableHead>
                <TableHead>Perfil</TableHead>
                <TableHead>Personal</TableHead>
                <TableHead>Status</TableHead>
                <TableHead>Data Vínculo</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {filtered.map((v) => (
                <TableRow key={v.id}>
                  <TableCell className="font-medium">
                    {v.usuario.nome}
                  </TableCell>
                  <TableCell className="text-muted-foreground">
                    {v.usuario.email}
                  </TableCell>
                  <TableCell>
                    <Badge variant="secondary">{v.usuario.role}</Badge>
                  </TableCell>
                  <TableCell className="text-muted-foreground">
                    {v.personal ? v.personal.nome : "—"}
                  </TableCell>
                  <TableCell>
                    <Badge variant={v.ativo ? "default" : "secondary"}>
                      {v.ativo ? "Ativo" : "Inativo"}
                    </Badge>
                  </TableCell>
                  <TableCell className="text-muted-foreground">
                    {new Date(v.dataVinculo).toLocaleDateString("pt-BR")}
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

export default VinculosPage;
