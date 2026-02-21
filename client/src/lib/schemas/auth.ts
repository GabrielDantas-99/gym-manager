import { z } from "zod";

export const loginSchema = z.object({
  email: z.string().trim().email("Email inválido"),
  senha: z.string().min(1, "Senha é obrigatória"),
});

export const registerSchema = z.object({
  nome: z
    .string()
    .trim()
    .min(3, "Nome deve ter no mínimo 3 caracteres")
    .max(100, "Nome muito longo"),
  email: z.string().trim().email("Email inválido"),
  senha: z.string().min(6, "Senha deve ter no mínimo 6 caracteres"),
  telefone: z.string().trim().optional().or(z.literal("")),
  role: z.enum(["ADMIN", "PERSONAL", "ALUNO"], {
    required_error: "Selecione um perfil",
  }),
});

export type LoginFormData = z.infer<typeof loginSchema>;
export type RegisterFormData = z.infer<typeof registerSchema>;
