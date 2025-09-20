import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.datatransfer.StringSelection;

public class Aluno extends Pessoa {

    private long matricula;
    private String pai;
    private String mae;
    private String turno;
    private Endereco endereco;
    private Graduacao graduacao;

    private static ArrayList<Aluno> alunos = new ArrayList<>();

    public Aluno(String nome, String telefone, long cpf, long matricula, String pai, String mae,
            String turno, Endereco endereco, Graduacao graduacao, String email, String nascimento) { // Construtor
        super(nome, telefone, email, nascimento, cpf);
        this.matricula = matricula;
        this.pai = pai;
        this.mae = mae;
        this.turno = turno;
        this.endereco = endereco; // inicializa o campo endereco
        this.graduacao = graduacao;
    }

    // Getters e Setters
    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public static ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public static void setAlunos(ArrayList<Aluno> alunos) {
        Aluno.alunos = alunos;
    }

    public String getLogradouro() {
        return endereco.getLogradouro();
    }

    public int getRua() {
        return endereco.getRua();
    }

    public int getNumero() {
        return endereco.getNumero();
    }

    public String getBairro() {
        return endereco.getBairro();
    }

    public String getCidade() {
        return endereco.getCidade();
    }

    public Estado getEstado() {
        return endereco.getEstado();
    }

    public int getCep() {
        return endereco.getCep();
    }

    public String getPais() {
        return endereco.getPais();
    }

    public Graduacao getGraduacao() {
        return graduacao;
    }

    public void setGraduacao(Graduacao graduacao) {
        this.graduacao = graduacao;
    }

    // Ícone personalizado
    static javax.swing.ImageIcon iconeOriginal = new javax.swing.ImageIcon("CrudEscola/src/Toque de Formatura Elegante.png");
    static java.awt.Image imagem = iconeOriginal.getImage().getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);                                                                                                                    
    static javax.swing.ImageIcon icone = new javax.swing.ImageIcon(imagem);

    // Métodos estáticos para manipular alunos
    public static void addAluno() {
        while (true) {
            try {
                String nome = null;
                while (nome == null) {
                    nome = (String) JOptionPane.showInputDialog(null,
                            "Digite o nome do aluno (ou 'sair' para voltar ao menu principal):", "Adicionar Aluno",
                            JOptionPane.QUESTION_MESSAGE, icone, null, null);
                    if (nome == null || nome.equalsIgnoreCase("sair")) {
                        Menu.main(null); // Voltar ao menu principal
                    }
                    if (nome.isEmpty() || !nome.matches("[a-zA-ZáéíóúãõâêîôûàèìòùçÁÉÍÓÚÃÕÂÊÎÔÛÀÈÌÒÙÇ ]+")) {
                        JOptionPane.showMessageDialog(null, "Nome não pode ser vazio e deve conter apenas letras.",
                                "Adicionar Aluno", JOptionPane.ERROR_MESSAGE);
                        nome = null;
                    }
                }

                String telefone = null;
                while (telefone == null) {
                    try {
                        telefone = (String) JOptionPane.showInputDialog(null, "Digite o telefone (11 números):",
                                "Adicionar Aluno", JOptionPane.QUESTION_MESSAGE, icone, null, null);
                        if (telefone == null) {
                            Menu.main(null); // Voltar ao menu principal se cancelar
                        }
                        if (telefone.isEmpty() || telefone.length() != 11 || !telefone.matches("\\d+")) {
                            throw new IllegalArgumentException(
                                    "Telefone não pode ser vazio e/ou deve conter 11 números.");
                        }
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage() + " Tente novamente.",
                                "Adicionar Aluno", JOptionPane.ERROR_MESSAGE);
                        telefone = null;
                    }
                }
                Graduacao graduacao = Graduacao.escolhaGraduacao();
                long cpf = 0;
                boolean cpfExiste;
                while (cpf == 0) {
                    try {
                        String cpfStr = (String) JOptionPane.showInputDialog(null, "Digite o CPF (11 números):",
                                "Adicionar Aluno", JOptionPane.QUESTION_MESSAGE, icone, null, null);

                        if (cpfStr == null) {
                            Menu.main(null); // Voltar ao menu principal se cancelar
                        }

                        // Valida formato
                        if (cpfStr.isEmpty() || cpfStr.length() != 11 || !cpfStr.matches("\\d+")) {
                            throw new IllegalArgumentException("CPF deve conter exatamente 11 números.");
                        }

                        // Valida CPFs inválidos (todos iguais)
                        if (cpfStr.equals("00000000000") || cpfStr.equals("11111111111") ||
                                cpfStr.equals("22222222222") || cpfStr.equals("33333333333") ||
                                cpfStr.equals("44444444444") || cpfStr.equals("55555555555") ||
                                cpfStr.equals("66666666666") || cpfStr.equals("77777777777") ||
                                cpfStr.equals("88888888888") || cpfStr.equals("99999999999")) {
                            throw new IllegalArgumentException("CPF inválido. Tente novamente.");
                        }

                        // Converte para long
                        cpf = Long.parseLong(cpfStr);

                        // Verifica duplicidade
                        cpfExiste = false;
                        for (Aluno aluno : alunos) {
                            if (aluno.getCpf() == cpf) {
                                cpfExiste = true;
                                break;
                            } else {
                                for (Professor professor : Professor.getProfessores()) {
                                    if (professor.getCpf() == cpf) {
                                        cpfExiste = true;
                                        break;
                                    }
                                }
                            }
                        }

                        if (cpfExiste) {
                            throw new IllegalArgumentException("CPF já cadastrado. Tente novamente.");
                        }

                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage() + " Tente novamente.",
                                "Adicionar Aluno", JOptionPane.ERROR_MESSAGE);
                        cpf = 0; // força repetir o loop
                    }
                }

                // Gerar matrícula aleatória de 11 dígitos e garantir que não exista repetida
                java.util.Random random = new java.util.Random();
                long matricula;
                boolean existeMatricula;
                do {
                    matricula = 10000000000L + (long) (random.nextDouble() * 10000000000L); // 11 dígitos
                    existeMatricula = false;
                    for (Aluno aluno : alunos) {
                        if (aluno.getMatricula() == matricula) {
                            existeMatricula = true;
                            break;
                        }
                    }
                } while (existeMatricula);
                JOptionPane.showMessageDialog(null, "Matrícula gerada automaticamente: " + matricula, "Adicionar Aluno",
                        JOptionPane.INFORMATION_MESSAGE, icone);

                String pai = null;
                while (pai == null) {
                    try {
                        pai = (String) JOptionPane.showInputDialog(null, "Digite o nome do pai:", "Adicionar Aluno",
                                JOptionPane.QUESTION_MESSAGE, icone, null, null);
                        if (pai == null) {
                            Menu.main(null); // Voltar ao menu principal se cancelar
                        }
                        if (pai == null || pai.isEmpty()
                                || !pai.matches("[a-zA-ZáéíóúãõâêîôûàèìòùçÁÉÍÓÚÃÕÂÊÎÔÛÀÈÌÒÙÇ ]+")) {
                            throw new IllegalArgumentException(
                                    "Nome do pai não pode ser vazio e deve conter apenas letras.");
                        }
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage() + " Tente novamente.",
                                "Adicionar Aluno", JOptionPane.ERROR_MESSAGE);
                        pai = null;
                    }
                }

                String mae = null;
                while (mae == null) {
                    try {
                        mae = (String) JOptionPane.showInputDialog(null, "Digite o nome da mãe:", "Adicionar Aluno",
                                JOptionPane.QUESTION_MESSAGE, icone, null, null);
                        if (mae == null) {
                            Menu.main(null); // Voltar ao menu principal se cancelar
                        }
                        if (mae == null || mae.isEmpty()
                                || !mae.matches("[a-zA-ZáéíóúãõâêîôûàèìòùçÁÉÍÓÚÃÕÂÊÎÔÛÀÈÌÒÙÇ ]+")) {
                            throw new IllegalArgumentException(
                                    "Nome da mãe não pode ser vazio e deve conter apenas letras.");
                        }
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage() + " Tente novamente.",
                                "Adicionar Aluno", JOptionPane.ERROR_MESSAGE);
                        mae = null;
                    }
                }

                String turno = null;
                while (turno == null) {
                    try {
                        String[] op = { "Matutino", "Vespertino", "Noturno" };
                        int r = JOptionPane.showOptionDialog(null, "Escolha o turno:", "Adicionar Aluno",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, icone, op, op[0]);
                        if (r == 0) {
                            turno = "Matutino";
                        } else if (r == 1) {
                            turno = "Vespertino";
                        } else if (r == 2) {
                            turno = "Noturno";
                        } else {
                            throw new IllegalArgumentException("Turno inválido. Tente novamente.");
                        }
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage() + " Tente novamente.",
                                "Adicionar Aluno", JOptionPane.ERROR_MESSAGE);
                        turno = null;
                    }
                }

                String email = null;
                while (email == null) {
                    email = (String) JOptionPane.showInputDialog(null, "Digite o email:", "Adicionar Aluno",
                            JOptionPane.QUESTION_MESSAGE, icone, null, null);
                    if (email == null) {
                        Menu.main(null); // Voltar ao menu principal
                    }

                    String domain = "@gmail.com";
                    String namePart = email.contains(domain) ? email.substring(0, email.indexOf(domain)) : "";
                    if (email.isEmpty() || !email.endsWith(domain) || !namePart.matches("[a-zA-Z]{5,20}")) {
                        JOptionPane.showMessageDialog(null,
                                "Email inválido. Tente Novamente.", "Adicionar Aluno", JOptionPane.ERROR_MESSAGE);
                        email = null;
                    }
                }
                String nascimento = null;
                while (nascimento == null) {
                    nascimento = (String) JOptionPane.showInputDialog(null, "Digite a data de nascimento:",
                            "Adicionar Aluno",
                            JOptionPane.QUESTION_MESSAGE, icone, null, null);
                    if (nascimento == null || nascimento.equalsIgnoreCase("sair")) {
                        Menu.main(null);
                        return;
                    }
                    if (nascimento.isEmpty() || !nascimento.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
                        JOptionPane.showMessageDialog(null, "Data de nascimento inválida. Use o formato dd/mm/aaaa.",
                                "Adicionar Aluno", JOptionPane.ERROR_MESSAGE);
                        nascimento = null;
                    }
                }
                
                Endereco endereco = Endereco.addEndereco(); // Chama o método para obter o endereço
                alunos.add(new Aluno(nome, telefone, cpf, matricula, pai, mae, turno, endereco, graduacao, email,
                        nascimento)); // Adiciona o novo aluno à lista
                JOptionPane.showMessageDialog(null, "Aluno adicionado com sucesso!", "Adicionar Aluno",
                        JOptionPane.INFORMATION_MESSAGE, icone);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "Adicionar Aluno",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void removeAluno() {
        try {
            long matricula = 0; 
            while (matricula == 0) {
                try {
                    String matriculaStr = (String) JOptionPane.showInputDialog(null,
                            "Digite a matrícula do aluno que deseja remover (11 números) ou 'sair' para voltar ao menu principal:",
                            "Remover Aluno", JOptionPane.QUESTION_MESSAGE, icone, null, null);

                    
                    if (matriculaStr == null || matriculaStr.equalsIgnoreCase("sair")) {
                        Menu.main(null);
                        return;
                    }

                    // Validação
                    if (matriculaStr.isEmpty() || matriculaStr.length() != 11 || !matriculaStr.matches("\\d+")) {
                        throw new IllegalArgumentException("A matrícula deve conter exatamente 11 números.");
                    }

                    matricula = Long.parseLong(matriculaStr);
                    if (matricula < 0) {
                        throw new IllegalArgumentException("A matrícula não pode ser negativa.");
                    }

                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage() + " Tente novamente:",
                            "Remover Aluno", JOptionPane.ERROR_MESSAGE);
                    matricula = 0; // Reseta para repetir
                }
            }

            boolean encontrado = false;
            var iterator = alunos.iterator(); // Usar iterador para remoção segura
            while (iterator.hasNext()) {
                Aluno aluno = iterator.next();
                if (aluno.getMatricula() == matricula) {
                    iterator.remove(); // Remoção segura durante a iteração
                    JOptionPane.showMessageDialog(null, "Aluno removido com sucesso!", "Remover Aluno",
                            JOptionPane.INFORMATION_MESSAGE, icone);
                    encontrado = true;
                    Menu.main(null); // Voltar ao menu principal
                }
            }

            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado.", "Remover Aluno",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage() + " Tente novamente:", "Remover Aluno",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
        }
    }

    public static void listAluno() {
    if (alunos.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Nenhum aluno encontrado.", "Lista de Alunos",
            JOptionPane.INFORMATION_MESSAGE, icone);
        return;
    }

        // Colunas da tabela
        String[] colunas = {
                "Matrícula", "CPF", "Nome", "Curso", "Tipo", "Duração", "Email", "Nascimento",
                "Pai", "Mãe", "Turno", "Telefone",
                "Casa e Rua", "Bairro", "Cidade", "Estado", "CEP", "País"
        };

        // Modelo da tabela não editável
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Adicionando cada aluno ao modelo
        for (Aluno a : alunos) {
            String casaRua = a.getEndereco().getLogradouro() + " " + a.getEndereco().getRua() + ", "
                    + a.getEndereco().getNumero();

            Object[] linha = {
                    a.getMatricula(),
                    a.getCpf(),
                    a.getNome(),
                    a.getGraduacao().getCurso(),
                    a.getGraduacao().getTipo(),
                    a.getGraduacao().getDuracao(),
                    a.getEmail(),
                    a.getNascimento(),
                    a.getPai(),
                    a.getMae(),
                    a.getTurno(),
                    a.getTelefone(),
                    casaRua,
                    a.getEndereco().getBairro(),
                    a.getEndereco().getCidade(),
                    a.getEndereco().getEstado().getSigla(),
                    a.getEndereco().getCep(),
                    a.getEndereco().getPais()
            };
            model.addRow(linha);
        }

        // JTable com tooltip
        JTable table = new JTable(model) {
            @Override
            public String getToolTipText(MouseEvent e) {
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                if (rowIndex >= 0 && colIndex >= 0) {
                    Object value = getValueAt(rowIndex, colIndex);
                    return value != null ? value.toString() : null;
                }
                return null;
            }
        };

        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setFillsViewportHeight(true);

        // Copiar para área de transferência ao clicar com o botão direito
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());
                    
                    if (col >= 0 && row >= 0) {
                        Object value = table.getValueAt(row, col);
                        if (value != null) {
                            StringSelection selection = new StringSelection(value.toString());
                            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
                            JOptionPane.showMessageDialog(table, "Texto copiado para a área de transferência!", "", JOptionPane.INFORMATION_MESSAGE, icone);
                        }
                    }
                }
            }
        });

        // Largura das colunas
        int[] larguras = { 100, 100, 175, 300, 200, 170, 150, 270, 100, 175, 175, 70, 110, 200, 100, 125, 75, 80, 100 };
        for (int col = 0; col < table.getColumnCount(); col++) {
            table.getColumnModel().getColumn(col).setPreferredWidth(larguras[col]);
        }

        JScrollPane scrollPane = new JScrollPane(table);

        JFrame frame = new JFrame("Lista de Alunos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(1000, 600);
        frame.add(scrollPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Mantendo a janela aberta
        while (frame.isVisible()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void exibirInfo() {
        Endereco e = this.endereco;

        // Criar um painel principal com borda e layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE);

        // Título
        JLabel titleLabel = new JLabel("INFORMAÇÕES DO ALUNO");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 51, 102));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        // Painel para informações pessoais
        JPanel infoPanel = createStyledPanel("DADOS PESSOAIS");
        addInfoRow(infoPanel, "Matrícula:", String.valueOf(this.matricula));
        addInfoRow(infoPanel, "CPF:", formatCPF(String.valueOf(this.cpf)));
        addInfoRow(infoPanel, "Nome:", this.nome);
        addInfoRow(infoPanel, "Telefone:", formatTelefone(this.telefone));
        addInfoRow(infoPanel, "Email:", this.email);
        addInfoRow(infoPanel, "Nascimento:", this.nascimento);
        addInfoRow(infoPanel, "Pai:", this.pai);
        addInfoRow(infoPanel, "Mãe:", this.mae);
        addInfoRow(infoPanel, "Turno:", this.turno);
        addInfoRow(infoPanel, "Curso:", this.graduacao.getCurso());
        addInfoRow(infoPanel, "Tipo:", this.graduacao.getTipo());
        addInfoRow(infoPanel, "Duração:", this.graduacao.getDuracao());

        // Painel para informações de endereço
        JPanel addressPanel = createStyledPanel("ENDEREÇO");
        addInfoRow(addressPanel, "Logradouro:", e.getLogradouro());
        addInfoRow(addressPanel, "Rua:", String.valueOf(e.getRua()));
        addInfoRow(addressPanel, "Número:", String.valueOf(e.getNumero()));
        addInfoRow(addressPanel, "Bairro:", e.getBairro());
        addInfoRow(addressPanel, "Cidade:", e.getCidade());
        addInfoRow(addressPanel, "Estado:", String.valueOf(e.getEstado().getSigla()));
        addInfoRow(addressPanel, "CEP:", formatCEP(String.valueOf(e.getCep())));
        addInfoRow(addressPanel, "País:", e.getPais());

        // Adicionar componentes ao painel principal
        mainPanel.add(titleLabel);
        mainPanel.add(infoPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(addressPanel);

        // Criar scroll pane
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setPreferredSize(new Dimension(600, 500));
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Criar e exibir o diálogo
        JDialog dialog = new JDialog();
        dialog.setTitle("Informações do Aluno - " + this.nome);
        dialog.add(scrollPane);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        dialog.setVisible(true);
    }

    // Método auxiliar para criar painéis estilizados
    private JPanel createStyledPanel(String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200)),
                        title,
                        javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                        javax.swing.border.TitledBorder.DEFAULT_POSITION,
                        new Font("SansSerif", Font.BOLD, 14),
                        new Color(0, 51, 102)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        return panel;
    }

    // Método auxiliar para adicionar linhas de informação
    private void addInfoRow(JPanel panel, String label, String value) {
        JPanel rowPanel = new JPanel(new BorderLayout(10, 0));
        rowPanel.setBackground(Color.WHITE);
        rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        rowPanel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

        JLabel infoLabel = new JLabel(label);
        infoLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        infoLabel.setPreferredSize(new Dimension(100, 20));

        JLabel infoValue = new JLabel(value != null ? value : "Não informado");
        infoValue.setFont(new Font("SansSerif", Font.PLAIN, 12));

        rowPanel.add(infoLabel, BorderLayout.WEST);
        rowPanel.add(infoValue, BorderLayout.CENTER);

        panel.add(rowPanel);
    }

    // Métodos auxiliares para formatação
    private String formatCPF(String cpf) {
        if (cpf == null || cpf.length() != 11)
            return cpf;
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" + cpf.substring(9);
    }

    private String formatTelefone(String telefone) {
        if (telefone == null || telefone.length() != 11)
            return telefone;
        return "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 7) +
                "-" + telefone.substring(7);
    }

    private String formatCEP(String cep) {
        if (cep == null || cep.length() != 8)
            return cep;
        return cep.substring(0, 5) + "-" + cep.substring(5);
    }

         
    public static void editAluno() {
        while (true) {
            try {
                String matriculaStr = (String) JOptionPane.showInputDialog(
                        null,
                        "Digite a matrícula do aluno que deseja editar (ou 'sair' para voltar ao menu principal):",
                        "Editar Dados do Aluno", 
                        JOptionPane.QUESTION_MESSAGE, icone, null, null);
                if (matriculaStr == null || matriculaStr.equalsIgnoreCase("sair")) {
                    Menu.main(null); // Voltar ao menu principal
                }
                if (matriculaStr.length() != 11 || !matriculaStr.matches("\\d+")) {
                    throw new IllegalArgumentException("A matrícula deve conter exatamente 11 números.");
                }
                long matricula = Long.parseLong(matriculaStr);

                boolean encontrado = false;
                for (Aluno aluno : alunos) {
                    if (aluno.getMatricula() == matricula) {
                        encontrado = true;
                        aluno.exibirInfo();

                        while (true) {
                            String[] opcoes = {
                                    "Nome", "Telefone", "CPF", "Curso", "Nome do Pai", "Nome da Mãe", "Turno",
                                    "Endereço", "Email", "Nascimento",
                                    "Finalizar edição"
                            };
                            String opcao = (String) JOptionPane.showInputDialog(
                                    null,
                                    "Qual dado você deseja editar?",
                                    "Editar Dados do Aluno", 
                                    JOptionPane.QUESTION_MESSAGE,
                                    icone,
                                    opcoes,
                                    opcoes[0]);

                            if (opcao == null || opcao.equals("Finalizar edição")) {
                                JOptionPane.showMessageDialog(null, "Edição finalizada.", "Editar Dados do Aluno",
                                        JOptionPane.INFORMATION_MESSAGE, icone); 
                                Menu.main(null); // Voltar ao menu principal
                            }

                            switch (opcao) {
                                case "Nome":
                                    String novoNome = null;
                                    while (novoNome == null) {
                                        novoNome = (String) JOptionPane.showInputDialog(
                                                null,
                                                "Digite o novo nome:",
                                                "Editar Dados do Aluno", 
                                                JOptionPane.QUESTION_MESSAGE, icone, null, null);
                                        if (novoNome == null) {
                                            break; 
                                        }
                                        if (novoNome != null && !novoNome.trim().isEmpty()
                                                && novoNome.matches("[a-zA-ZáéíóúãõâêîôûàèìòùçÁÉÍÓÚÃÕÂÊÎÔÛÀÈÌÒÙÇ ]+") && !novoNome.equals(aluno.getNome())) { // Verifica se o dado digitado é diferente do atual
                                            aluno.setNome(novoNome);
                                            JOptionPane.showMessageDialog(null, "Nome atualizado com sucesso!",
                                                    "Editar Dados do Aluno", JOptionPane.INFORMATION_MESSAGE, icone); 
                                           
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Nome inválido. Tente novamente.",
                                                    "Editar Dados do Aluno", JOptionPane.ERROR_MESSAGE); 
                                                                                                         
                                            novoNome = null; // Reiniciar o loop se o nome for inválido
                                        }
                                    }
                                    break;
                                case "Telefone":
                                    String novoTelefone = null;
                                    while (novoTelefone == null) {
                                        novoTelefone = (String) JOptionPane.showInputDialog(
                                                null,
                                                "Digite o novo telefone (11 números):",
                                                "Editar Dados do Aluno", 
                                                JOptionPane.QUESTION_MESSAGE, icone, null, null);
                                        if (novoTelefone == null) {
                                            break; 
                                        }
                                        if (novoTelefone != null && novoTelefone.matches("\\d{11}") && !novoTelefone.equals(aluno.getTelefone())) {
                                            aluno.setTelefone(novoTelefone);
                                            JOptionPane.showMessageDialog(null, "Telefone atualizado com sucesso!",
                                                    "Editar Dados do Aluno", JOptionPane.INFORMATION_MESSAGE, icone); 
                                            
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Telefone inválido. Tente novamente.",
                                                    "Editar Dados do Aluno", JOptionPane.ERROR_MESSAGE); 
                                                                                                         
                                            novoTelefone = null; // Reiniciar o loop se o telefone for inválido
                                        }
                                    }
                                    break;
                                case "CPF":
                                    long novoCpf = 0;
                                    boolean cpfExiste;

                                    while (novoCpf == 0) {
                                        String novoCpfStr = (String) JOptionPane.showInputDialog(
                                                null,
                                                "Digite o novo CPF (11 números):",
                                                "Editar Dados do Aluno", 
                                                JOptionPane.QUESTION_MESSAGE, icone, null, null);

                                        if (novoCpfStr == null) {
                                           break;
                                            
                                        }

                                        // Verifica se tem 11 dígitos
                                        if (!novoCpfStr.matches("\\d{11}")) {
                                            JOptionPane.showMessageDialog(null,
                                                    "CPF inválido. Deve conter exatamente 11 números.",
                                                    "Editar Dados do Aluno", JOptionPane.ERROR_MESSAGE); 
                                                                                                         
                                            continue; // reinicia o loop
                                        }

                                        // Verifica se todos os dígitos são iguais
                                        if (novoCpfStr.matches("(\\d)\\1{10}")) {
                                            JOptionPane.showMessageDialog(null, "CPF inválido. Tente novamente.",
                                                    "Editar Dados do Aluno", JOptionPane.ERROR_MESSAGE); 
                                                                                                         
                                            continue; // reinicia o loop
                                        }

                                        // Converte para long
                                        novoCpf = Long.parseLong(novoCpfStr);

                                        // Verifica duplicidade
                                        cpfExiste = false;
                                        for (Aluno a : alunos) {
                                            if (a.getCpf() == novoCpf) {
                                                cpfExiste = true;
                                                break;
                                            } else {
                                                for (Professor professor : Professor.getProfessores()) {
                                                    if (professor.getCpf() == novoCpf) {
                                                        cpfExiste = true;
                                                        break;
                                                    }
                                                }
                                            }
                                        }

                                        if (cpfExiste) {
                                            JOptionPane.showMessageDialog(null, "CPF já cadastrado. Tente novamente.",
                                                    "Editar Dados do Aluno", JOptionPane.ERROR_MESSAGE);
                                                                                                         
                                            novoCpf = 0; // reinicia o loop
                                        } else {
                                            aluno.setCpf(novoCpf);
                                            JOptionPane.showMessageDialog(null, "CPF atualizado com sucesso!",
                                                    "Editar Dados do Aluno", JOptionPane.INFORMATION_MESSAGE, icone); 
                                           
                                        }
                                    }
                                    break;
                                case "Curso":
                                    Graduacao novaGraduacao = Graduacao.editGraduacao(); // Chama o método estático para editar
                                    aluno.setGraduacao(novaGraduacao);
                                    break;
                                case "Nome do Pai":
                                    String novoPai = null;
                                    while (novoPai == null) {
                                        novoPai = (String) JOptionPane.showInputDialog(
                                                null,
                                                "Digite o novo nome do pai:",
                                                "Editar Dados do Aluno", 
                                                JOptionPane.QUESTION_MESSAGE, icone, null, null);
                                        if (novoPai == null) {
                                           break; 
                                        }
                                        if (novoPai != null && !novoPai.trim().isEmpty()
                                                && novoPai.matches("[a-zA-ZáéíóúãõâêîôûàèìòùçÁÉÍÓÚÃÕÂÊÎÔÛÀÈÌÒÙÇ ]+") && !novoPai.equals(aluno.getPai())) {
                                            aluno.setPai(novoPai);
                                            JOptionPane.showMessageDialog(null, "Nome do pai atualizado com sucesso!",
                                                    "Editar Dados do Aluno", JOptionPane.INFORMATION_MESSAGE, icone); 
                                            
                                        } else {
                                            JOptionPane.showMessageDialog(null,
                                                    "Nome do pai inválido. Tente novamente.",
                                                    "Editar Dados do Aluno", JOptionPane.ERROR_MESSAGE); 
                                                                                                         
                                            novoPai = null; // Reiniciar o loop se o nome do pai for inválido
                                        }
                                    }
                                    break;
                                case "Nome da Mãe":
                                    String novaMae = null;
                                    while (novaMae == null) {
                                        novaMae = (String) JOptionPane.showInputDialog(
                                                null,
                                                "Digite o novo nome da mãe:",
                                                "Editar Dados do Aluno", 
                                                JOptionPane.QUESTION_MESSAGE, icone, null, null);
                                        if (novaMae == null) {
                                            break; 
                                        }
                                        if (novaMae != null && !novaMae.trim().isEmpty()
                                                && novaMae.matches("[a-zA-ZáéíóúãõâêîôûàèìòùçÁÉÍÓÚÃÕÂÊÎÔÛÀÈÌÒÙÇ ]+") && !novaMae.equals(aluno.getMae())) {
                                            aluno.setMae(novaMae);
                                            JOptionPane.showMessageDialog(null, "Nome da mãe atualizado com sucesso!",
                                                    "Editar Dados do Aluno", JOptionPane.INFORMATION_MESSAGE, icone); 
                                            
                                        } else {
                                            JOptionPane.showMessageDialog(null,
                                                    "Nome da mãe inválida. Tente novamente.",
                                                    "Editar Dados do Aluno", JOptionPane.ERROR_MESSAGE); 
                                                                                                         
                                            novaMae = null; // Reiniciar o loop se o nome da mãe for inválido
                                        }
                                    }
                                    break;
                                case "Turno":
                                    String novoTurno = null;
                                    while (novoTurno == null) {
                                        try {
                                            String[] op = { "Matutino", "Vespertino", "Noturno" };
                                            int r = JOptionPane.showOptionDialog(
                                                    null,
                                                    "Escolha o turno:",
                                                    "Editar Dados do Professor",
                                                    JOptionPane.DEFAULT_OPTION,
                                                    JOptionPane.QUESTION_MESSAGE,
                                                    icone,
                                                    op,
                                                    op[0]);
                                            if (r == 0) {
                                                novoTurno = "Matutino";
                                            } else if (r == 1) {
                                                novoTurno = "Vespertino";
                                            } else if (r == 2) {
                                                novoTurno = "Noturno";
                                            } else {
                                                throw new IllegalArgumentException("Turno inválido. Tente novamente.");
                                            }
                                        } catch (IllegalArgumentException e) {
                                            JOptionPane.showMessageDialog(null,
                                                    "Erro: " + e.getMessage() + " Tente novamente.",
                                                    "Editar Dados do Aluno", JOptionPane.ERROR_MESSAGE);
                                            novoTurno = null;
                                        }
                                    }
                                    aluno.setTurno(novoTurno);
                                    JOptionPane.showMessageDialog(null, "Turno atualizado com sucesso!",
                                            "Editar Dados do Professor", JOptionPane.INFORMATION_MESSAGE, icone);

                                    break;
                                case "Endereço":
                                    Endereco.editEndereco(aluno.getEndereco()); // Edita o endereço existente
                                    break;
                                case "Email":
                                    String novoEmail = null;
                                    while (novoEmail == null) {
                                        novoEmail = (String) JOptionPane.showInputDialog(
                                                null,
                                                "Digite o novo email:",
                                                "Editar Dados do Aluno", 
                                                JOptionPane.QUESTION_MESSAGE, icone, null, null);
                                        if (novoEmail == null) {
                                            break; 
                                        }
                                        String domain = "@gmail.com";
                                        String namePart = novoEmail.contains(domain) ? novoEmail.substring(0, novoEmail.indexOf(domain)) : "";
                                        if (novoEmail != null && !novoEmail.trim().isEmpty()
                                                && novoEmail
                                                        .matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$") && !novoEmail.equals(aluno.getEmail())
                                                && !namePart.isEmpty() && !novoEmail.endsWith(domain) ) {
                                            aluno.setEmail(novoEmail);
                                            JOptionPane.showMessageDialog(null, "Email atualizado com sucesso!",
                                                    "Editar Dados do Aluno", JOptionPane.INFORMATION_MESSAGE, icone); 
                                            
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Email inválido. Tente novamente.",
                                                    "Editar Dados do Aluno", JOptionPane.ERROR_MESSAGE); 
                                                                                                        
                                            novoEmail = null; // Reiniciar o loop se o email for inválido
                                        }
                                    }
                                    break;
                                case "Nascimento":
                                    String novoNascimento = null;
                                    while (novoNascimento == null) {
                                        novoNascimento = (String) JOptionPane.showInputDialog(
                                                null,
                                                "Digite a nova data de nascimento:",
                                                "Editar Dados do Aluno", 
                                                JOptionPane.QUESTION_MESSAGE, icone, null, null);
                                        if (novoNascimento == null) {
                                            break;
                                        }
                                        if (novoNascimento != null && !novoNascimento.trim().isEmpty()
                                                && novoNascimento.matches("^\\d{2}/\\d{2}/\\d{4}$") && !novoNascimento.equals(aluno.getNascimento())) {
                                            aluno.setNascimento(novoNascimento);
                                            JOptionPane.showMessageDialog(null,
                                                    "Data de nascimento atualizada com sucesso!",
                                                    "Editar Dados do Aluno", JOptionPane.INFORMATION_MESSAGE, icone); 
                                           
                                        } else {
                                            JOptionPane.showMessageDialog(null,
                                                    "Data de nascimento inválida. Tente novamente.",
                                                    "Editar Dados do Aluno", JOptionPane.ERROR_MESSAGE); 
                                                                                                         
                                            novoNascimento = null; // Reiniciar o loop se a data for inválida
                                        }
                                    }
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.",
                                            "Editar Dados do Aluno", JOptionPane.ERROR_MESSAGE);
                                    break;
                            }
                        }
                    }
                }

                if (!encontrado) {
                    JOptionPane.showMessageDialog(null, "Aluno não encontrado.", "Editar Dados do Aluno",
                            JOptionPane.ERROR_MESSAGE, icone); 
                }
                // Tratar outras exceções
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage() + "Tente Novamente",
                        "Editar Dados do Aluno",
                        JOptionPane.ERROR_MESSAGE); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "Editar Dados do Aluno",
                        JOptionPane.ERROR_MESSAGE); 
            }
        }
    }

    // Método para buscar aluno
    public static void buscAluno(long matricula) {
        while (true) {
            try {
                String matriculaStr = (String) JOptionPane
                        .showInputDialog(null, "Digite a matrícula do aluno que deseja buscar (11 números):",
                                "Buscar Aluno", JOptionPane.QUESTION_MESSAGE, icone, null, null);
                if (matriculaStr == null || matriculaStr.equalsIgnoreCase("sair")) {
                    Menu.main(null); // Voltar ao menu principal
                }
                if (matriculaStr.length() != 11 || !matriculaStr.matches("\\d+")) {
                    throw new IllegalArgumentException("A matrícula deve conter exatamente 11 números.");
                }
                matricula = Long.parseLong(matriculaStr);

                boolean encontrado = false;
                for (Aluno aluno : alunos) {
                    if (aluno.getMatricula() == matricula) {
                        encontrado = true;
                        aluno.exibirInfo(); // Exibe as informações do aluno encontrado
                        break;
                    }
                }

                if (!encontrado) {
                    JOptionPane.showMessageDialog(null, "Aluno não encontrado.", "Buscar Aluno",
                            JOptionPane.ERROR_MESSAGE, icone); 
                }
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage() + " Tente novamente.", "Buscar Aluno",
                        JOptionPane.ERROR_MESSAGE); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
            }
        }
    }
}
