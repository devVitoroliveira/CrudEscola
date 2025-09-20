import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Professor extends Pessoa {

    private long matricula;
    private String turno;
    private Endereco endereco;
    private Graduacao graduacao;
    private static ArrayList<Professor> professores = new ArrayList<>();

    public Professor(String nome, String telefone, long cpf, long matricula, String turno,
            Endereco endereco, Graduacao graduacao, String email, String nascimento) { // Construtor
        super(nome, telefone, email, nascimento, cpf);

        this.matricula = matricula;
        this.turno = turno;
        this.endereco = endereco;
        this.graduacao = graduacao;
    }

    // Getters e Setters
    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
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

    public Graduacao getGraduacao() {
        return graduacao;
    }

    public void setGraduacao(Graduacao graduacao) {
        this.graduacao = graduacao;
    }

    public static ArrayList<Professor> getProfessores() {
        return professores;
    }

    public static void setProfessores(ArrayList<Professor> professores) {
        Professor.professores = professores;
    }

    // Ícone personalizado
    static javax.swing.ImageIcon iconeOriginal = new javax.swing.ImageIcon("CrudEscola/src/Toque de Formatura Elegante.png");
    static java.awt.Image imagem = iconeOriginal.getImage().getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);                                                                                                                 
    static javax.swing.ImageIcon icone = new javax.swing.ImageIcon(imagem);

    // Métodos estáticos para manipular professores
    public static void addProfessor() {
        while (true) {
            try {
                String nome = null;
                while (nome == null) {
                    nome = (String) JOptionPane.showInputDialog(
                            null,
                            "Digite o nome do professor (ou 'sair' para voltar ao menu principal):",
                            "Adicionar Professor",
                            JOptionPane.QUESTION_MESSAGE, icone, null, null);
                    if (nome == null || nome.equalsIgnoreCase("sair")) {
                        Menu.main(null); // Voltar ao menu principal
                    }
                    if (nome.isEmpty() || !nome.matches("[a-zA-ZáéíóúãõâêîôûàèìòùçÁÉÍÓÚÃÕÂÊÎÔÛÀÈÌÒÙÇ ]+")) {
                        JOptionPane.showMessageDialog(null, "Nome não pode ser vazio e deve conter apenas letras.",
                                "Adicionar Professor", JOptionPane.ERROR_MESSAGE, icone);
                        nome = null;
                    }
                }

                String telefone = null;
                while (telefone == null) {
                    telefone = (String) JOptionPane.showInputDialog(
                            null,
                            "Digite o telefone (11 números):",
                            "Adicionar Professor",
                            JOptionPane.QUESTION_MESSAGE, icone, null, null);
                    if (telefone == null) {
                        Menu.main(null); // Voltar ao menu principal
                    }
                    if (telefone.isEmpty() || telefone.length() != 11 || !telefone.matches("\\d+")) {
                        JOptionPane.showMessageDialog(null, "Telefone não pode ser vazio e deve conter 11 números.",
                                "Adicionar Professor", JOptionPane.ERROR_MESSAGE);
                        telefone = null;
                    }
                }
                Graduacao graduacao = Graduacao.escolhaGraduacao();
                long cpf = 0;
                boolean cpfExiste;

                while (cpf == 0) {
                    try {
                        String cpfStr = (String) JOptionPane.showInputDialog(
                                null,
                                "Digite o CPF (11 números):",
                                "Adicionar Professor",
                                JOptionPane.QUESTION_MESSAGE, icone, null, null);

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
                        for (Professor professor : professores) {
                            if (professor.getCpf() == cpf) {
                                cpfExiste = true;
                                break;
                            } else {
                                for (Aluno aluno : Aluno.getAlunos()) {
                                    if (aluno.getCpf() == cpf) {
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
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Adicionar Professor",
                                JOptionPane.ERROR_MESSAGE);
                        cpf = 0; // força repetir o loop
                    }
                }

                java.util.Random random = new java.util.Random();
                long matricula;
                boolean existeMatricula;
                do {
                    matricula = 100000L + (long) (random.nextDouble() * 100000L); 
                    existeMatricula = false;
                    for (Professor professor : professores) {
                        if (professor.getMatricula() == matricula) {
                            existeMatricula = true;
                            break;
                        }
                    }
                } while (existeMatricula);
                JOptionPane.showMessageDialog(null, "Matrícula gerada automaticamente: " + matricula,
                        "Adicionar Professor", JOptionPane.INFORMATION_MESSAGE, icone);

                String turno = null;
                while (turno == null) {
                    try {
                        String[] op = { "Matutino", "Vespertino", "Noturno" };
                        int r = JOptionPane.showOptionDialog(
                                null,
                                "Escolha o turno:",
                                "Adicionar Professor",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                icone,
                                op,
                                op[0]);
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
                                "Adicionar Professor", JOptionPane.ERROR_MESSAGE);
                        turno = null;
                    }
                }
                String email = null;
                while (email == null) {
                    email = (String) JOptionPane.showInputDialog(
                            null,
                            "Digite o email:",
                            "Adicionar Professor",
                            JOptionPane.QUESTION_MESSAGE, icone, null, null);
                    if (email == null) {
                        Menu.main(null); // Voltar ao menu principal
                    }

                    String domain = "@gmail.com";
                    String namePart = email.contains(domain) ? email.substring(0, email.indexOf(domain)) : "";
                    if (email.isEmpty() || !email.endsWith(domain) || !namePart.matches("[a-zA-Z]{5,20}")) {
                        JOptionPane.showMessageDialog(null, "Email inválido. Tente Novamente.", "Adicionar Professor",
                                JOptionPane.ERROR_MESSAGE);
                        email = null;
                    }
                }

                String nascimento = null;
                while (nascimento == null) {
                    nascimento = (String) JOptionPane.showInputDialog(
                            null,
                            "Digite a data de nascimento (dd/mm/aaaa):",
                            "Adicionar Professor",
                            JOptionPane.QUESTION_MESSAGE, icone, null, null);
                    if (nascimento == null || nascimento.equalsIgnoreCase("sair")) {
                        Menu.main(null); // Voltar ao menu principal
                    }
                    if (nascimento.isEmpty()
                            || !nascimento.matches("^([0-2][0-9]|(3)[0-1])/((0)[0-9]|(1)[0-2])/\\d{4}$")) {
                        JOptionPane.showMessageDialog(null, "Data de nascimento inválida. Tente novamente.",
                                "Adicionar Professor", JOptionPane.ERROR_MESSAGE);
                        nascimento = null;
                    }
                }

                Endereco endereco = Endereco.addEndereco();
                professores.add(new Professor(nome, telefone, cpf, matricula, turno, endereco, graduacao, email,
                        nascimento));
                javax.swing.JOptionPane.showMessageDialog(null, "Professor adicionado com sucesso!",
                        "Adicionar Professor", JOptionPane.INFORMATION_MESSAGE, icone);

            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(),
                        "Adicionar Professor", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void removeProfessor() {
        try {
            long matriculaValida = 0;
            while (matriculaValida == 0) {
                try {
                    String entrada = (String) JOptionPane.showInputDialog(null,
                            "Digite a matrícula do professor que deseja remover (6 números) ou 'sair' para voltar ao menu principal:",
                            "Remover Professor", JOptionPane.QUESTION_MESSAGE, icone, null, null);
                    if (entrada == null || entrada.equalsIgnoreCase("sair")) {
                        Menu.main(null); // Voltar ao menu principal
                    }
                    if (entrada.isEmpty() || entrada.length() != 6 || !entrada.matches("\\d+")) {
                        throw new IllegalArgumentException("A matrícula deve conter exatamente 6 números.");
                    }
                    matriculaValida = Long.parseLong(entrada);
                    if (matriculaValida < 0) {
                        throw new IllegalArgumentException("A matrícula não pode ser negativa.");
                    }
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage() + " Tente novamente:",
                            "Remover Professor", JOptionPane.ERROR_MESSAGE);
                    matriculaValida = 0;
                }
            }

            boolean encontrado = false;
            var iterator = professores.iterator();
            while (iterator.hasNext()) {
                Professor professor = iterator.next();
                if (professor.getMatricula() == matriculaValida) {
                    iterator.remove();
                    JOptionPane.showMessageDialog(null, "Professor removido com sucesso!",
                            "Remover Professor", JOptionPane.INFORMATION_MESSAGE, icone);
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "Professor não encontrado.", "Remover Professor",
                        JOptionPane.INFORMATION_MESSAGE, icone);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
        }
    }

    public static void listProfessor() {
        try {
            if (professores.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum professor encontrado.", "Lista de Professores",
                        JOptionPane.INFORMATION_MESSAGE, icone);
                return;
            }

            // Colunas da tabela
            String[] colunas = {
                    "Matrícula", "CPF", "Nome", "Disciplina", "Tipo", "Duração", "Telefone", "Email", "Nascimento",
                    "Turno",
                    "Casa e Rua", "Bairro", "Cidade", "Estado", "CEP", "País"
            };

            // Modelo não editável
            DefaultTableModel model = new DefaultTableModel(colunas, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            // Adicionando cada professor
            for (Professor p : professores) {
                String casaRua = p.getEndereco().getLogradouro() + " " + p.getEndereco().getRua() + ", "
                        + p.getEndereco().getNumero();
                Object[] linha = {
                        p.getMatricula(),
                        p.getCpf(),
                        p.getNome(),
                        p.getGraduacao().getCurso(),
                        p.getGraduacao().getTipo(),
                        p.getGraduacao().getDuracao(),
                        p.getTelefone(),
                        p.getEmail(),
                        p.getNascimento(),
                        p.getTurno(),
                        casaRua,
                        p.getEndereco().getBairro(),
                        p.getEndereco().getCidade(),
                        p.getEndereco().getEstado().getSigla(),
                        p.getEndereco().getCep(),
                        p.getEndereco().getPais()
                };
                model.addRow(linha);
            }

            // JTable com tooltip
            JTable table = new JTable(model) {
                @Override
                public String getToolTipText(java.awt.event.MouseEvent e) {
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

            // Copia para área de transferência ao clicar com o botão direito
            table.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent e) {
                    if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
                        int row = table.rowAtPoint(e.getPoint());
                        int col = table.columnAtPoint(e.getPoint());
                        
                        if (row >= 0 && col >= 0) {
                            table.setRowSelectionInterval(row, row);
                            table.setColumnSelectionInterval(col, col);
                            Object value = table.getValueAt(row, col);
                            if (value != null) {
                                StringSelection selection = new StringSelection(value.toString());
                                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
                                JOptionPane.showMessageDialog(null, "Texto copiado para a área de transferência: ", "", JOptionPane.INFORMATION_MESSAGE, icone);
                            }
                        }
                    }
                }
            });

            table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
            table.setFillsViewportHeight(true);

            // Larguras das colunas
            int[] larguras = { 100, 100, 175, 300, 200, 170, 70, 150, 270, 175, 110, 200, 100, 125, 75, 80 };
            for (int col = 0; col < table.getColumnCount(); col++) {
                table.getColumnModel().getColumn(col).setPreferredWidth(larguras[col]);
            }

            JScrollPane scrollPane = new JScrollPane(table);

            JFrame frame = new JFrame("Lista de Professores");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setSize(1000, 600);
            frame.add(scrollPane);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Mantendo o while
            while (frame.isVisible()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ie) {
                    break;
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
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
        JLabel titleLabel = new JLabel("INFORMAÇÕES DO PROFESSOR");
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
        scrollPane.setPreferredSize(new Dimension(550, 450));
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Criar e exibir o diálogo
        JDialog dialog = new JDialog();
        dialog.setTitle("Informações do Professor - " + this.nome);
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

    public static void editProfessor() {
        try {
            long matriculaValida = 0;
            while (matriculaValida == 0) {
                try {
                    String entrada = (String) JOptionPane.showInputDialog(
                            null,
                            "Digite a matrícula do professor que deseja editar (6 números) ou 'sair' para voltar ao menu principal:",
                            "Editar Dados do Professor",
                            JOptionPane.QUESTION_MESSAGE, icone, null, null);
                    if (entrada == null || entrada.equalsIgnoreCase("sair")) {
                        Menu.main(null); // Voltar ao menu principal
                    }
                    if (entrada.isEmpty() || entrada.length() != 6 || !entrada.matches("\\d+")) {
                        throw new IllegalArgumentException("A matrícula deve conter exatamente 6 números.");
                    }
                    matriculaValida = Long.parseLong(entrada);
                    if (matriculaValida < 0) {
                        throw new IllegalArgumentException("A matrícula não pode ser negativa.");
                    }
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage() + " Tente novamente:",
                            "Editar Dados do Professor", JOptionPane.ERROR_MESSAGE);
                    matriculaValida = 0;
                }
            }

            boolean encontrado = false;
            for (Professor professor : professores) {
                if (professor.getMatricula() == matriculaValida) {
                    encontrado = true;
                    professor.exibirInfo();
                    while (true) {
                        String[] opcoes = {
                                "Nome", "Telefone", "CPF", "Disciplina", "Turno", "Email", "Nascimento", "Endereço",
                                "Finalizar edição"
                        };
                        String opcao = (String) JOptionPane.showInputDialog(
                                null,
                                "Qual dado você deseja editar?",
                                "Editar Dados do Professor",
                                JOptionPane.QUESTION_MESSAGE,
                                icone,
                                opcoes,
                                opcoes[0]);

                        if (opcao == null || opcao.equals("Finalizar edição")) {
                            JOptionPane.showMessageDialog(null, "Edição finalizada.", "Editar Dados do Professor",
                                    JOptionPane.INFORMATION_MESSAGE, icone);
                            Menu.main(null); // Voltar ao menu principal
                        }

                        switch (opcao) {
                            case "Nome":
                                String novoNome = (String) JOptionPane.showInputDialog(
                                        null,
                                        "Digite o novo nome:",
                                        "Editar Dados do Professor",
                                        JOptionPane.QUESTION_MESSAGE, icone, null, null);
                                if (novoNome == null) {
                                    break;
                                }
                                if (novoNome != null && !novoNome.trim().isEmpty()
                                        && novoNome.matches("[a-zA-ZáéíóúãõâêîôûàèìòùçÁÉÍÓÚÃÕÂÊÎÔÛÀÈÌÒÙÇ ]+") && !novoNome.equals(professor.getNome())) { // Verifica se o dado digitado é diferente do atual
                                    professor.setNome(novoNome);
                                    JOptionPane.showMessageDialog(null, "Nome atualizado com sucesso!",
                                            "Editar Dados do Professor", JOptionPane.INFORMATION_MESSAGE, icone);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Nome inválido. Tente novamente.",
                                            "Editar Dados do Professor", JOptionPane.ERROR_MESSAGE);
                                    novoNome = null;
                                }
                                break;
                            case "Telefone":
                                String novoTelefone = (String) JOptionPane.showInputDialog(
                                        null,
                                        "Digite o novo telefone (11 números):",
                                        "Editar Dados do Professor",
                                        JOptionPane.QUESTION_MESSAGE, icone, null, null);
                                if (novoTelefone == null) {
                                    break;
                                }
                                if (novoTelefone != null && novoTelefone.matches("\\d{11}") && !novoTelefone.equals(professor.getTelefone())) {
                                    professor.setTelefone(novoTelefone);
                                    JOptionPane.showMessageDialog(null, "Telefone atualizado com sucesso!",
                                            "Editar Dados do Professor", JOptionPane.INFORMATION_MESSAGE, icone);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Telefone inválido. Tente novamente.",
                                            "Editar Dados do Professor", JOptionPane.ERROR_MESSAGE);
                                    novoTelefone = null;
                                }
                                break;
                            case "CPF":
                                long novoCpf = 0;
                                boolean cpfExiste;

                                while (novoCpf == 0) {
                                    String novoCpfStr = (String) JOptionPane.showInputDialog(
                                            null,
                                            "Digite o novo CPF (11 números):",
                                            "Editar Dados do Professor",
                                            JOptionPane.QUESTION_MESSAGE, icone, null, null);

                                    if (novoCpfStr == null) {
                                        break; 
                                    }

                                    // Verifica se tem 11 dígitos
                                    if (!novoCpfStr.matches("\\d{11}")) {
                                        JOptionPane.showMessageDialog(null,
                                                "CPF inválido. Deve conter exatamente 11 números.",
                                                "Editar Dados do Professor", JOptionPane.ERROR_MESSAGE);
                                        continue; // reinicia o loop
                                    }

                                    // Verifica se todos os dígitos são iguais
                                    if (novoCpfStr.matches("(\\d)\\1{10}")) {
                                        JOptionPane.showMessageDialog(null, "CPF inválido. Tente novamente.",
                                                "Editar Dados do Professor", JOptionPane.ERROR_MESSAGE);
                                        continue; // reinicia o loop
                                    }

                                    // Converte para long
                                    novoCpf = Long.parseLong(novoCpfStr);

                                    // Verifica duplicidade
                                    cpfExiste = false;
                                    for (Professor p : professores) {
                                        if (p.getCpf() == novoCpf) {
                                            cpfExiste = true;
                                            break;
                                        } else {
                                            for (Aluno aluno : Aluno.getAlunos()) {
                                                if (aluno.getCpf() == novoCpf) {
                                                    cpfExiste = true;
                                                    break;
                                                }
                                            }
                                        }
                                    }

                                    if (cpfExiste) {
                                        JOptionPane.showMessageDialog(null, "CPF já cadastrado. Tente novamente.",
                                                "Editar Dados do Professor", JOptionPane.ERROR_MESSAGE);
                                        novoCpf = 0; // reinicia o loop
                                    } else {
                                        professor.setCpf(novoCpf);
                                        JOptionPane.showMessageDialog(null, "CPF atualizado com sucesso!",
                                                "Editar Dados do Professor", JOptionPane.INFORMATION_MESSAGE, icone);
                                    }
                                }
                                break;
                            case "Disciplina":
                                Graduacao novaGraduacao = Graduacao.editGraduacao(); // Chama o método estático para editar
                                professor.setGraduacao(novaGraduacao);
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
                                                "Editar Dados do Professor", JOptionPane.ERROR_MESSAGE);
                                        novoTurno = null;
                                    }
                                }
                                professor.setTurno(novoTurno);
                                JOptionPane.showMessageDialog(null, "Turno atualizado com sucesso!",
                                        "Editar Dados do Professor", JOptionPane.INFORMATION_MESSAGE, icone);
                                break;
                            case "Endereço":
                                Endereco.editEndereco(professor.getEndereco()); // Edita o endereço existente
                                break;
                            case "Email":
                                String novoEmail = null;
                                while (novoEmail == null) {
                                    novoEmail = (String) JOptionPane.showInputDialog(
                                            null,
                                            "Digite o novo email:",
                                            "Editar Dados do Professor",
                                            JOptionPane.QUESTION_MESSAGE, icone, null, null);
                                    if (novoEmail == null) {
                                        break;
                                    }
                                    String domain = "@gmail.com";
                                    String namePart = novoEmail.contains(domain)
                                            ? novoEmail.substring(0, novoEmail.indexOf(domain))
                                            : "";

                                    if (novoEmail != null && !novoEmail.trim().isEmpty() && novoEmail.endsWith(domain)
                                            && namePart.matches("[a-zA-Z]{5,20}") && !novoEmail.equals(professor.getEmail())) {
                                        professor.setEmail(novoEmail);
                                        JOptionPane.showMessageDialog(null, "Email atualizado com sucesso!",
                                                "Editar Dados do Professor", JOptionPane.INFORMATION_MESSAGE, icone);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Email inválido. Tente novamente.",
                                                "Editar Dados do Professor", JOptionPane.ERROR_MESSAGE);
                                        novoEmail = null;
                                    }
                                }
                                break;
                            case "Nascimento":
                                String novoNascimento = (String) JOptionPane.showInputDialog(
                                        null,
                                        "Digite a nova data de nascimento (dd/mm/aaaa):",
                                        "Editar Dados do Professor",
                                        JOptionPane.QUESTION_MESSAGE, icone, null, null);
                                if (novoNascimento == null) {
                                    break;
                                }
                                if (novoNascimento != null && !novoNascimento.trim().isEmpty()
                                        && novoNascimento
                                                .matches("^([0-2][0-9]|(3)[0-1])/((0)[0-9]|(1)[0-2])/\\d{4}$") && !novoNascimento.equals(professor.getNascimento())) {
                                    professor.setNascimento(novoNascimento);
                                    JOptionPane.showMessageDialog(null, "Data de nascimento atualizada com sucesso!",
                                            "Editar Dados do Professor", JOptionPane.INFORMATION_MESSAGE, icone);
                                } else {
                                    JOptionPane.showMessageDialog(null,
                                            "Data de nascimento inválida. Tente novamente.",
                                            "Editar Dados do Professor", JOptionPane.ERROR_MESSAGE);
                                    novoNascimento = null;
                                }
                                break;
                            case "Finalizar edição":
                                JOptionPane.showMessageDialog(null, "Edição finalizada.", "Editar Dados do Professor",
                                        JOptionPane.INFORMATION_MESSAGE, icone);
                                Menu.main(null); // Voltar ao menu principal
                                return; // Sair do loop de edição

                            default:
                                JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.",
                                        "Editar Dados do Professor", JOptionPane.ERROR_MESSAGE);
                                break;
                        }
                    }
                }
            }

            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "Professor não encontrado.", "Editar Dados do Professor",
                        JOptionPane.ERROR_MESSAGE, icone);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "Editar Dados do Professor",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void buscProfessor(long matricula) {
        try {
            long matriculaValida = 0;
            while (matriculaValida == 0) {
                try {
                    String entrada = (String) JOptionPane.showInputDialog(null,
                            "Digite a matrícula do professor que deseja buscar (6 números) ou 'sair' para voltar ao menu principal:",
                            "Buscar Professor", JOptionPane.QUESTION_MESSAGE, icone, null, null);
                    if (entrada == null || entrada.equalsIgnoreCase("sair")) {
                        Menu.main(null); // Voltar ao menu principal
                    }
                    if (entrada.isEmpty() || entrada.length() != 6 || !entrada.matches("\\d+")) {
                        throw new IllegalArgumentException("A matrícula deve conter exatamente 6 números.");
                    }
                    matriculaValida = Long.parseLong(entrada);
                    if (matriculaValida < 0) {
                        throw new IllegalArgumentException("A matrícula não pode ser negativa.");
                    }
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage() + " Tente novamente:",
                            "Buscar Professor", JOptionPane.ERROR_MESSAGE);
                    matriculaValida = 0;
                }
            }

            boolean encontrado = false;
            for (Professor professor : professores) {
                if (professor.getMatricula() == matriculaValida) {
                    encontrado = true;
                    professor.exibirInfo(); // Exibe as informações do professor
                    break;
                }
            }

            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "Professor não encontrado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
        }
    }
}
