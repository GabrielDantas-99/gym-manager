export interface AcademiaResponse {
  id: number;
  nome: string;
  endereco: string | null;
  telefone: string | null;
  adminId: number;
  ativa: boolean;
  dataCadastro: string;
}

export interface CreateAcademiaRequest {
  nome: string;
  endereco?: string;
  telefone?: string;
}
