import "./globals.css";

import type { Metadata, Viewport } from "next";

import { Inter } from "next/font/google";
import { Toaster } from "sonner";

const inter = Inter({ subsets: ["latin"], variable: "--font-inter" });

export const metadata: Metadata = {
  title: "GymManager - Sistema de Gestao de Academias",
  description:
    "Plataforma completa para gerenciamento de academias, personais e alunos.",
};

export const viewport: Viewport = {
  themeColor: "#e97316",
  width: "device-width",
  initialScale: 1,
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="pt-BR">
      <body className={`${inter.variable} font-sans antialiased`}>
        {children}
        <Toaster richColors position="top-right" />
      </body>
    </html>
  );
}
