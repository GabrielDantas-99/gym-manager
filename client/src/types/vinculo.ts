import type { UsuarioResponse } from "./auth";

export interface VinculoResponse {
  id: number;
  usuario: UsuarioResponse;
  academiaId: number;
  personal: UsuarioResponse | null;
  ativo: boolean;
  dataVinculo: string;
}

export interface CreateVinculoRequest {
  usuarioId: number;
  academiaId: number;
  personalId?: number;
}
