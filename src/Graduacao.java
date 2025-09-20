import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Graduacao {

    private String curso;
    private String duracao;
    private String tipo;

    public Graduacao(String curso, String duracao, String tipo) { // Construtor
        this.curso = curso;
        this.duracao = duracao;
        this.tipo = tipo;
    }

    // Getters e Setters
    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Ícone personalizado
    static javax.swing.ImageIcon iconeOriginal = new javax.swing.ImageIcon("CrudEscola/src/Toque de Formatura Elegante.png");
    static java.awt.Image imagem = iconeOriginal.getImage().getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);                                                                                                              
    static javax.swing.ImageIcon icone = new javax.swing.ImageIcon(imagem);

    // Métodos estáticos para manipular graduações
    public static Graduacao escolhaGraduacao() {
        JPanel panel = new JPanel();
        panel.add(new javax.swing.JLabel("Selecione o curso de graduação:"));
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Analise e Desenvolvimento de Sistemas");
        comboBox.addItem("Ciência da Computação");
        comboBox.addItem("Engenharia da Computação");
        comboBox.addItem("Engenharia de Software");
        comboBox.addItem("Gestão da Tecnologia da Informação");
        comboBox.addItem("Jogos Digitais");
        panel.add(comboBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Escolha a Graduação", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, icone);
        if (result == JOptionPane.OK_OPTION) {
            int opcao = comboBox.getSelectedIndex();
            // Retorna a graduação selecionada
            if (opcao == 0) {
                return new Graduacao("Analise e Desenvolvimento de Sistemas", "5 Semestres", "Tecnólogo");
            } else if (opcao == 1) {
                return new Graduacao("Ciência da Computação", "8 Semestres", "Bacharelado");
            } else if (opcao == 2) {
                return new Graduacao("Engenharia da Computação", "10 Semestres", "Bacharelado");
            } else if (opcao == 3) {
                return new Graduacao("Engenharia de Software", "10 Semestres", "Bacharelado");
            } else if (opcao == 4) {
                return new Graduacao("Gestão da Tecnologia da Informação", "5 Semestres", "Tecnólogo");
            } else if (opcao == 5) {
                return new Graduacao("Jogos Digitais", "5 Semestres", "Tecnólogo");
            }
        }
        if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
            Menu.main(null);
        }
        return null;
    }

    public static Graduacao editGraduacao() {
        JPanel panel = new JPanel();
        panel.add(new javax.swing.JLabel("Selecione o curso de graduação:"));
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Analise e Desenvolvimento de Sistemas");
        comboBox.addItem("Ciência da Computação");
        comboBox.addItem("Engenharia da Computação");
        comboBox.addItem("Engenharia de Software");
        comboBox.addItem("Gestão da Tecnologia da Informação");
        comboBox.addItem("Jogos Digitais");
        panel.add(comboBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Escolha a Graduação", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, icone);
        if (result == JOptionPane.OK_OPTION) {
            int opcao = comboBox.getSelectedIndex();
            // Retorna a graduação selecionada
             JOptionPane.showMessageDialog(null, "Curso atualizado com sucesso!",
                                            "Editar Dados do Aluno", JOptionPane.INFORMATION_MESSAGE, icone);
            if (opcao == 0) {
                return new Graduacao("Analise e Desenvolvimento de Sistemas", "5 Semestres", "Tecnólogo");
            } else if (opcao == 1) {
                return new Graduacao("Ciência da Computação", "8 Semestres", "Bacharelado");
            } else if (opcao == 2) {
                return new Graduacao("Engenharia da Computação", "10 Semestres", "Bacharelado");
            } else if (opcao == 3) {
                return new Graduacao("Engenharia de Software", "10 Semestres", "Bacharelado");
            } else if (opcao == 4) {
                return new Graduacao("Gestão da Tecnologia da Informação", "5 Semestres", "Tecnólogo");
            } else if (opcao == 5) {
                return new Graduacao("Jogos Digitais", "5 Semestres", "Tecnólogo");
            }
        }
        if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
            return null;
        }
        return null;
    }
}