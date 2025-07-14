import type { Metadata } from "next";
import "./styles/globals.css";

import Header from "@/components/header";

export const metadata: Metadata = {
  title: "Ghazal Moveis",
  description: "Site de vendas da Ghazal Moveis.",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body>
        <Header />
        {children}
      </body>
    </html>
  );
}
