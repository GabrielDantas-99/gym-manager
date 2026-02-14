import { z } from "zod";

export const loginSchema = z.object({
  email: z.string().email("E-mail invalido"),
  senha: z.string().min(6, "A senha deve ter pelo menos 6 caracteres"),
});

export const registerSchema = z.object({
  nome: z.string().min(3, "O nome deve ter pelo menos 3 caracteres"),
  email: z.string().email("E-mail invalido"),
  senha: z.string().min(6, "A senha deve ter pelo menos 6 caracteres"),
  telefone: z.string().optional(),
  role: z.enum(["ADMIN", "PERSONAL", "ALUNO"], {
    required_error: "Selecione um perfil",
  }),
});

export const academiaSchema = z.object({
  nome: z.string().min(3, "O nome deve ter pelo menos 3 caracteres"),
  endereco: z.string().min(3, "O endereco deve ter pelo menos 3 caracteres"),
  telefone: z.string().min(8, "Telefone invalido"),
});

export const vinculoSchema = z.object({
  usuarioId: z.number({ required_error: "Selecione um usuario" }),
  academiaId: z.number({ required_error: "Selecione uma academia" }),
  personalId: z.number().optional(),
});

export type LoginFormData = z.infer<typeof loginSchema>;
export type RegisterFormData = z.infer<typeof registerSchema>;
export type AcademiaFormData = z.infer<typeof academiaSchema>;
export type VinculoFormData = z.infer<typeof vinculoSchema>;
