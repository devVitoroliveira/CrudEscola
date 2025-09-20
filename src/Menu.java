import javax.swing.JOptionPane;

public class Menu {
    // Menu principal
    public static void main(String[] args) {
        // Icône personalizado
        javax.swing.ImageIcon iconeOriginal = new javax.swing.ImageIcon("CrudEscola/src/Toque de Formatura Elegante.png");
        java.awt.Image imagem = iconeOriginal.getImage().getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH); 
        javax.swing.ImageIcon icone = new javax.swing.ImageIcon(imagem);

        while (true) {
            String[] opcoesMenu = { "Gerenciar Aluno", "Gerenciar Professor", "Sair" };
            int opcao = JOptionPane.showOptionDialog(null,
                    "Sistema de cadastro de Alunos/professores\nEscolha uma opção:", "Menu Principal",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icone, opcoesMenu, opcoesMenu[0]);

            if (opcao == 2 || opcao == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Saindo do sistema", "Menu Principal", JOptionPane.INFORMATION_MESSAGE, icone);
                System.exit(0); // Encerra o programa
            }

            if (opcao == 0) { // Aluno
                String[] opcoesAluno = { "Cadastrar aluno", "Excluir aluno", "Atualizar aluno", "Listar alunos",
                        "Buscar aluno", "Sair" };
                int opcaoAluno = JOptionPane.showOptionDialog(null,
                        "Sistema de gerenciamento de Alunos\nEscolha a opção desejada:", "Menu Aluno",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icone, opcoesAluno,
                        opcoesAluno[0]);

                switch (opcaoAluno) {
                    case 0:
                        Aluno.addAluno();
                        break;
                    case 1:
                        Aluno.removeAluno();
                        break;
                    case 2:
                        Aluno.editAluno();
                        break;
                    case 3:
                        Aluno.listAluno();
                        break;
                    case 4:
                        Aluno.buscAluno(0); 
                        break;
                    case 5:
                    case JOptionPane.CLOSED_OPTION:
                        continue;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida", "Menu Aluno", JOptionPane.ERROR_MESSAGE, icone);
                        break;
                }
            } else if (opcao == 1) { // Professor
                String[] opcoesProfessor = { "Cadastrar professor", "Excluir professor", "Atualizar professor",
                        "Listar professores", "Buscar professor", "Sair" };
                int opcaoProfessor = JOptionPane.showOptionDialog(null,
                        "Sistema de gerenciamento de Professores\nEscolha a opção desejada:", "Menu Professor",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icone, opcoesProfessor,
                        opcoesProfessor[0]);

                switch (opcaoProfessor) {
                    case 0:
                        Professor.addProfessor();
                        break;
                    case 1:
                        Professor.removeProfessor();
                        break;
                    case 2:
                        Professor.editProfessor();
                        break;
                    case 3:
                        Professor.listProfessor();
                        break;
                    case 4:
                        Professor.buscProfessor(0);
                        break;
                    case 5:
                    case JOptionPane.CLOSED_OPTION:
                        continue;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida", "Menu Professor", JOptionPane.ERROR_MESSAGE, icone);
                        break;
                }
            }
        }
    }
}
