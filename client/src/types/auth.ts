import { Role } from "./common";

export interface RegisterRequest {
  nome: string;
  email: string;
  senha: string;
  telefone?: string;
  role: Role;
}

export interface LoginRequest {
  email: string;
  senha: string;
}

export interface AuthResponse {
  token: string;
  type: "Bearer";
  userId: number;
  nome: string;
  email: string;
  role: Role;
}

export interface UsuarioResponse {
  id: number;
  nome: string;
  email: string;
  telefone: string | null;
  role: Role;
  ativo: boolean;
  dataCadastro: string;
}

export interface UserSession {
  userId: number;
  nome: string;
  email: string;
  role: Role;
}
