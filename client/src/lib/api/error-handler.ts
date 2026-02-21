export function handleApiError(error: any): string {
  if (error.response?.data?.message) {
    return error.response.data.message;
  }
  if (error.response?.status === 401) {
    return "Sessão expirada. Faça login novamente.";
  }
  if (error.response?.status === 403) {
    return "Você não tem permissão para esta ação.";
  }
  if (error.response?.status === 404) {
    return "Recurso não encontrado.";
  }
  return "Erro ao processar requisição. Tente novamente.";
}
