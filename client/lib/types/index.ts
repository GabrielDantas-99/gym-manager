export type Role = "ADMIN" | "PERSONAL" | "ALUNO";

export interface User {
  id: number;
  nome: string;
  email: string;
  telefone?: string;
  role: Role;
}

export interface AuthResponse {
  token: string;
  userId: number;
  nome: string;
  email: string;
  role: Role;
}

export interface Academia {
  id: number;
  nome: string;
  endereco: string;
  telefone: string;
}

export interface Vinculo {
  id: number;
  usuario: User;
  academia: Academia;
  personal?: User;
  tipo: string;
}

export interface RegisterPayload {
  nome: string;
  email: string;
  senha: string;
  telefone?: string;
  role: Role;
}

export interface LoginPayload {
  email: string;
  senha: string;
}

export interface AcademiaPayload {
  nome: string;
  endereco: string;
  telefone: string;
}

export interface VinculoPayload {
  usuarioId: number;
  academiaId: number;
  personalId?: number;
}

export interface DashboardStats {
  totalAcademias: number;
  totalPersonais: number;
  totalAlunos: number;
  totalVinculos: number;
}
