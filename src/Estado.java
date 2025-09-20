import javax.swing.*;

public class Estado {
    private String sigla;

    public Estado(String sigla) {
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    // Ícone personalizado
    static ImageIcon iconeOriginal = new ImageIcon("CrudEscola/src/Toque de Formatura Elegante.png");
    static java.awt.Image imagem = iconeOriginal.getImage().getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);
    static ImageIcon icone = new ImageIcon(imagem);

    // Lista de siglas dos estados brasileiros
    private static final String[] SIGLAS_ESTADOS = {
            "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO",
            "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI",
            "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
    };

    // Métodos estáticos para adicionar e editar estados
    public static Estado addEstado() {
        // Criar combo box
        JComboBox<String> comboBox = new JComboBox<>(SIGLAS_ESTADOS);
        comboBox.setSelectedIndex(0); 

        // Criar painel com título e combo
        JPanel panel = new JPanel();
        panel.add(new JLabel("Selecione o Estado:")); 
        panel.add(comboBox);

        // Mostrar diálogo com painel e ícone
        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Escolha de Estado", 
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icone 
        );

        // Tratar escolha
        if (result == JOptionPane.OK_OPTION) {
            String siglaSelecionada = (String) comboBox.getSelectedItem();
            if (siglaSelecionada != null) {
                return new Estado(siglaSelecionada); // Retorna o estado selecionado
            }
        }
        if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
            Menu.main(null); // voltar ao menu
        }
        return null;
    }

    public static Estado editEstado() {
        // Criar combo box
        JComboBox<String> comboBox = new JComboBox<>(SIGLAS_ESTADOS);
        comboBox.setSelectedIndex(0); // Seleciona o primeiro item por padrão

        // Criar painel com título e combo
        JPanel panel = new JPanel();
        panel.add(new JLabel("Selecione o Estado:")); 
        panel.add(comboBox);

        // Mostrar diálogo com painel e ícone
        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Escolha de Estado", 
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icone 
        );

        // Tratar escolha
        if (result == JOptionPane.OK_OPTION) {
            String siglaSelecionada = (String) comboBox.getSelectedItem();
            if (siglaSelecionada != null) {
                return new Estado(siglaSelecionada);
            }
        }
        if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
            Menu.main(null); // voltar ao menu
        }
        return null;
    }
}
