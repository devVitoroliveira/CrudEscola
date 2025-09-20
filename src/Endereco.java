
import javax.swing.JOptionPane;

public class Endereco {
    private String logradouro;
    private int rua;
    private int numero;
    private String bairro;
    private String cidade;
    private Estado estado;
    private int cep;
    private String pais;

    public Endereco(String logradouro, int rua, int numero, String bairro, String cidade, Estado estado, int cep,
            String pais) { // Construtor
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.pais = pais;
        this.rua = rua;
    }

    // Getters e Setters
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getRua() {
        return rua;
    }

    public void setRua(int rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    // Ícone personalizado
    static javax.swing.ImageIcon iconeOriginal = new javax.swing.ImageIcon("CrudEscola/src/Toque de Formatura Elegante.png");
    static java.awt.Image imagem = iconeOriginal.getImage().getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);                                                                                                               
    static javax.swing.ImageIcon icone = new javax.swing.ImageIcon(imagem);

    // Métodos estáticos para manipular endereços
    public static Endereco addEndereco() {
        while (true) {
            try {
                String pais = null;
                while (pais == null) {
                    pais = (String) JOptionPane.showInputDialog(null, "Digite o país:", "Adicionar Endereço",
                            JOptionPane.QUESTION_MESSAGE, icone, null, null);
                    if (pais == null || pais.equalsIgnoreCase("sair")) {
                        Menu.main(null); // Volta ao menu principal
                        return null;
                    }
                    if (pais.isEmpty() || !pais.matches("[a-zA-Z ]+")) {
                        JOptionPane.showMessageDialog(null, "País inválido. Deve conter apenas letras.",
                                "Adicionar Endereço", JOptionPane.ERROR_MESSAGE);
                        pais = null;
                    }
                }
                Estado estado = Estado.addEstado(); // Chama o método para obter o estado

                String cidade = null;
                while (cidade == null) {
                    cidade = (String) JOptionPane.showInputDialog(null, "Digite a cidade:", "Adicionar Endereço",
                            JOptionPane.QUESTION_MESSAGE, icone, null, null);
                    if (cidade == null || cidade.equalsIgnoreCase("sair")) {
                        Menu.main(null);
                        return null;
                    }
                    if (cidade.isEmpty() || !cidade.matches("[a-zA-Z ]+")) {
                        JOptionPane.showMessageDialog(null, "Cidade inválida. Deve conter apenas letras.",
                                "Adicionar Endereço", JOptionPane.ERROR_MESSAGE);
                        cidade = null;
                    }
                }

                String bairro = null;
                while (bairro == null) {
                    bairro = (String) JOptionPane.showInputDialog(null, "Digite o bairro:", "Adicionar Endereço",
                            JOptionPane.QUESTION_MESSAGE, icone, null, null);
                    if (bairro == null || bairro.equalsIgnoreCase("sair")) {
                        Menu.main(null);
                        return null;
                    }
                    if (bairro.isEmpty() || !bairro.matches("[a-zA-Z ]+")) {
                        JOptionPane.showMessageDialog(null, "Bairro inválido. Deve conter apenas letras.",
                                "Adicionar Endereço", JOptionPane.ERROR_MESSAGE);
                        bairro = null;
                    }
                }

                String logradouro = null;
                while (logradouro == null) {
                    logradouro = (String) JOptionPane.showInputDialog(null, "Digite o logradouro:",
                            "Adicionar Endereço",
                            JOptionPane.QUESTION_MESSAGE, icone, null, null);
                    if (logradouro == null || logradouro.equalsIgnoreCase("sair")) {
                        Menu.main(null);
                        return null;
                    }
                    if (logradouro.isEmpty() || !logradouro.matches("[a-zA-Z ]+")) {
                        JOptionPane.showMessageDialog(null, "Logradouro inválido. Deve conter apenas letras.",
                                "Adicionar Endereço", JOptionPane.ERROR_MESSAGE);
                        logradouro = null;
                    }
                }
                int rua = 0;
                while (rua == 0) {
                    try {
                        String ruaStr = (String) JOptionPane.showInputDialog(null, "Digite o número da rua:",
                                "Adicionar Endereço",
                                JOptionPane.QUESTION_MESSAGE, icone, null, null);
                        if (ruaStr == null) {
                            Menu.main(null);
                            return null;
                        }
                        rua = Integer.parseInt(ruaStr);
                        if (rua <= 0) {
                            JOptionPane.showMessageDialog(null, "Número negativo. Tente novamente.",
                                    "Adicionar Endereço", JOptionPane.ERROR_MESSAGE);
                            rua = 0;
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Número inválido. Tente novamente.", "Adicionar Endereço",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

                int numero = 0;
                while (numero == 0) {
                    try {
                        String numeroStr = (String) JOptionPane.showInputDialog(null, "Digite o número de sua casa:",
                                "Adicionar Endereço",
                                JOptionPane.QUESTION_MESSAGE, icone, null, null);
                        if (numeroStr == null) {
                            Menu.main(null);
                            return null;
                        }
                        numero = Integer.parseInt(numeroStr);
                        if (numero <= 0) {
                            JOptionPane.showMessageDialog(null, "Número negativo. Tente novamente.",
                                    "Adicionar Endereço", JOptionPane.ERROR_MESSAGE);
                            numero = 0;
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Número inválido. Tente novamente.", "Adicionar Endereço",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

                int cep = 0;
                while (cep == 0) {
                    try {
                        String cepStr = (String) JOptionPane.showInputDialog(null, "Digite o CEP (somente números):",
                                "Adicionar Endereço",
                                JOptionPane.QUESTION_MESSAGE, icone, null, null);
                        if (cepStr == null) {
                            Menu.main(null);
                            return null;
                        }
                        cep = Integer.parseInt(cepStr);
                        if (cep <= 0 || cepStr.length() != 8 || !cepStr.matches("\\d{8}")) {
                            JOptionPane.showMessageDialog(null, "CEP negativo ou zero. Tente novamente.",
                                    "Adicionar Endereço", JOptionPane.ERROR_MESSAGE);
                            cep = 0;
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "CEP inválido. Tente novamente.", "Adicionar Endereço",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

                return new Endereco(logradouro, rua, numero, bairro, cidade, estado, cep, pais); // Retorna o novo endereço
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar endereço: " + e.getMessage() + "Tente Novamente",
                        "Adicionar Endereço",
                        JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
    }

    public static void editEndereco(Endereco endereco) {
        while (true) {
            try {
                String[] opcoes = { "Logradouro", "Nº Casa", "Nº Rua", "Bairro", "Cidade", "Estado", "CEP", "Pais",
                        "Sair" };
                String opcao = (String) JOptionPane.showInputDialog(
                        null,
                        "Escolha o campo a ser editado:",
                        "Editar Endereço",
                        JOptionPane.DEFAULT_OPTION,
                        icone,
                        opcoes,
                        opcoes[0]);
                if (opcao == null || opcao.equals("Sair")) {
                    JOptionPane.showMessageDialog(null, "Saindo do editor de endereço", "Editar Endereço",
                            JOptionPane.INFORMATION_MESSAGE, icone);
                    return; // Sai do método sem fazer alterações
                }
                switch (opcao) {
                    case "Logradouro":
                        String novoLogradouro = null;
                        while (novoLogradouro == null) {
                            novoLogradouro = (String) JOptionPane.showInputDialog(null, "Digite o novo logradouro:",
                                    "Editar Endereço",
                                    JOptionPane.QUESTION_MESSAGE, icone, null, null);
                            if (novoLogradouro == null || novoLogradouro.equalsIgnoreCase("sair")) {
                                Menu.main(null);
                            }
                            if (novoLogradouro.isEmpty() || !novoLogradouro.matches("[a-zA-Z ]+")) {
                                JOptionPane.showMessageDialog(null, "Logradouro inválido. Deve conter apenas letras.",
                                        "Editar Endereço", JOptionPane.ERROR_MESSAGE);
                                novoLogradouro = null;
                            }
                            endereco.setLogradouro(novoLogradouro);
                        }
                        break;
                    case "Nº Rua":
                        int novaRua = 0;
                        while (novaRua == 0) {
                            try {
                                String ruaStr = (String) JOptionPane.showInputDialog(null,
                                        "Digite o novo número da rua:", "Editar Endereço",
                                        JOptionPane.QUESTION_MESSAGE, icone, null, null);
                                if (ruaStr == null) {
                                    Menu.main(null);
                                }
                                novaRua = Integer.parseInt(ruaStr);
                                if (novaRua <= 0) {
                                    JOptionPane.showMessageDialog(null, "Número negativo. Tente novamente.",
                                            "Editar Endereço", JOptionPane.ERROR_MESSAGE);
                                    novaRua = 0;
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Número inválido. Tente novamente.",
                                        "Editar Endereço", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        endereco.setRua(novaRua);
                        break;
                    case "Nº Casa":
                        int novoNumero = 0;
                        while (novoNumero == 0) {
                            try {
                                String numeroStr = (String) JOptionPane.showInputDialog(null,
                                        "Digite o novo número de sua casa:", "Editar Endereço",
                                        JOptionPane.QUESTION_MESSAGE, icone, null, null);
                                if (numeroStr == null) {
                                    Menu.main(null);
                                }
                                novoNumero = Integer.parseInt(numeroStr);
                                if (novoNumero <= 0) {
                                    JOptionPane.showMessageDialog(null, "Número negativo. Tente novamente.",
                                            "Editar Endereço", JOptionPane.ERROR_MESSAGE);
                                    novoNumero = 0;
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Número inválido. Tente novamente.",
                                        "Editar Endereço", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        endereco.setNumero(novoNumero);
                        break;
                    case "Bairro":
                        String novoBairro = null;
                        while (novoBairro == null) {
                            novoBairro = (String) JOptionPane.showInputDialog(null, "Digite o novo bairro:",
                                    "Editar Endereço",
                                    JOptionPane.QUESTION_MESSAGE, icone, null, null);
                            if (novoBairro == null || novoBairro.equalsIgnoreCase("sair")) {
                                Menu.main(null);
                            }
                            if (novoBairro.isEmpty() || !novoBairro.matches("[a-zA-Z ]+")) {
                                JOptionPane.showMessageDialog(null, "Bairro inválido. Deve conter apenas letras.",
                                        "Editar Endereço", JOptionPane.ERROR_MESSAGE);
                                novoBairro = null;
                            }
                            endereco.setBairro(novoBairro);
                        }
                        break;
                    case "Cidade":
                        String novaCidade = null;
                        while (novaCidade == null) {
                            novaCidade = (String) JOptionPane.showInputDialog(null, "Digite a nova cidade:",
                                    "Editar Endereço",
                                    JOptionPane.QUESTION_MESSAGE, icone, null, null);
                            if (novaCidade == null || novaCidade.equalsIgnoreCase("sair")) {
                                Menu.main(null);
                            }
                            if (novaCidade.isEmpty() || !novaCidade.matches("[a-zA-Z ]+")) {
                                JOptionPane.showMessageDialog(null, "Cidade inválida. Deve conter apenas letras.",
                                        "Editar Endereço", JOptionPane.ERROR_MESSAGE);
                                novaCidade = null;
                            }
                            endereco.setCidade(novaCidade);
                        }
                        break;
                    case "Estado":
                        Estado novoEstado = Estado.editEstado(); // Chama o método para editar o estado
                        endereco.setEstado(novoEstado);
                        JOptionPane.showMessageDialog(null, "Estado atualizado com sucesso!", "Editar Endereço",
                                JOptionPane.INFORMATION_MESSAGE, icone);
                        break;
                    case "CEP":
                        int novoCep = 0;
                        while (novoCep == 0) {
                            try {
                                String cepStr = (String) JOptionPane.showInputDialog(null,
                                        "Digite o novo CEP (somente números):", "Editar Endereço",
                                        JOptionPane.QUESTION_MESSAGE, icone, null, null);
                                if (cepStr == null) {
                                    Menu.main(null);
                                }
                                novoCep = Integer.parseInt(cepStr);
                                if (novoCep <= 0 || cepStr.length() != 8 || !cepStr.matches("\\d{8}")) {
                                    JOptionPane.showMessageDialog(null, "CEP negativo ou zero. Tente novamente.",
                                            "Editar Endereço", JOptionPane.ERROR_MESSAGE);
                                    novoCep = 0;
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "CEP inválido. Tente novamente.", "Editar Endereço",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        endereco.setCep(novoCep);
                        break;
                    case "Pais":
                        String novoPais = null;
                        while (novoPais == null) {
                            novoPais = (String) JOptionPane.showInputDialog(null, "Digite o novo país:",
                                    "Editar Endereço",
                                    JOptionPane.QUESTION_MESSAGE, icone, null, null);
                            if (novoPais == null || novoPais.equalsIgnoreCase("sair")) {
                                Menu.main(null);
                            }
                            if (novoPais.isEmpty() || !novoPais.matches("[a-zA-Z ]+")) {
                                JOptionPane.showMessageDialog(null, "País inválido. Deve conter apenas letras.",
                                        "Editar Endereço", JOptionPane.ERROR_MESSAGE);
                                novoPais = null;
                            }
                            endereco.setPais(novoPais);
                        }
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                        break;
                }
                JOptionPane.showMessageDialog(null, "Endereço atualizado com sucesso!",
                                        "Editar Dados do Professor", JOptionPane.INFORMATION_MESSAGE, icone);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao editar endereço: " + e.getMessage());
            }
        }
    }
}