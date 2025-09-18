public abstract class Pessoa {
    String nome;
    String telefone;
    String email;
    String nascimento;
    long cpf;
    public Pessoa(String nome, String telefone,  String email, String nascimento, long cpf) {
        this.nome = nome;
        this.telefone = telefone; 
        this.email = email;
        this.nascimento = nascimento;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNascimento() {
        return nascimento;
    }
    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }
             public abstract void exibirInfo();
   }
  

